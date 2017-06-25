package com.guangxunet.shop.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guangxunet.shop.base.domain.Logininfo;
import com.guangxunet.shop.base.service.ILogininfoService;
import com.guangxunet.shop.base.service.IVerifyCodeService;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.base.util.UserContext;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月7日 下午9:14:53 
* 类说明 前台系统用户登录
*/
@Controller
@RequestMapping("Login")
public class LoginInfoController {
	@Autowired
	private ILogininfoService iLogininfoService;
	@Autowired
    private IVerifyCodeService verifyCodeService;
	/**
	 * 客户端用户登录
	 * @param username
	 * @param password
	 * @param request
	 * @return 登录成功则返回登录者信息
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public JsonResult login(String mobile, String password, HttpServletRequest request){
		JsonResult result = null;
		
		//非空检验
		if (StringUtils.isEmpty(mobile)) {
			throw new RuntimeException("手机号为空！");
		}
		
		if (StringUtils.isEmpty(password)) {
			throw new RuntimeException("密码为空！");
		}
		
		//登录校验
		Logininfo login = iLogininfoService.login(mobile, password, request.getRemoteAddr(), Logininfo.USER_NORMAL);
		
		if (login == null) {
			result = new JsonResult("手机号或密码错误，请重试！");
		}else{
			Logininfo current = UserContext.getCurrent();
			Map<String,Object> userInfo = new HashMap<String,Object>();
			userInfo.put("UId", current.getId());
			userInfo.put("userName", current.getUsername());
			userInfo.put("mobile", current.getMobile());
			userInfo.put("userType", current.getUserType());
			result = new JsonResult(true,"登录成功！");
			result.setResult(userInfo);
		}
		return result;
	}
	
	/**
	 * 忘记密码第二步：跳转到输入验证码页面
	 * @param phoneNumber
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/findPwd2.screen")
	public String findPwd2(String phoneNumber,Model model) throws Exception{
		model.addAttribute("phoneNumber", phoneNumber);
		verifyCodeService.sendVerifyCode(phoneNumber);
		return "findPwd2";
	}
	
	/**
	 * 忘记密码第三步：跳转到重置密码页面
	 * @param phoneNumber
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/findPwd3.screen")
	public String findPwd3(String phoneNumber,Model model) throws Exception{
		model.addAttribute("phoneNumber", phoneNumber);
		return "findPwd3";
	}
}
