package com.guangxunet.shop.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class LoginInfoController {
	@Autowired
	private ILogininfoService iLogininfoService;
	@Autowired
	private IVerifyCodeService iVerifyCodeService;
	
	/**
	 * 客户端用户登录
	 * @param username
	 * @param password
	 * @param request
	 * @return 登录成功则返回登录者信息
	 */
	@RequestMapping("/login")
	@ResponseBody
	public JsonResult login(String mobile, String password, HttpServletRequest request){
		JsonResult result = null;
		
		//非空检验
		if (StringUtils.isEmpty(mobile)) {
			throw new RuntimeException("用户名为空！");
		}
		
		if (StringUtils.isEmpty(password)) {
			throw new RuntimeException("密码为空！");
		}
		
		//登录校验
		Logininfo login = iLogininfoService.login(mobile, password, request.getRemoteAddr(), Logininfo.USER_NORMAL);
		
		if (login == null) {
			result = new JsonResult("用户名或密码错误，请重试！");
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
	 * 给指定手机号发送短信验证码
	 * @param phone
	 * @return
	 */
	@RequestMapping("/send")
	@ResponseBody
	public JsonResult sendVerifyCode(String phone){
		JsonResult result = null;
		try {
			if (StringUtils.isEmpty(phone)) {
				result = new JsonResult("手机号为空！");
			} else {
				System.out.println("=======================手机号为===" + phone);
				iVerifyCodeService.sendVerifyCode((phone));
				System.out.println("=======================发送结果===");
				result = new JsonResult(true, "发送完成");
			} 
		} catch (Exception e) {
			result = new JsonResult(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 根据手机号和验证码验证验证码的正确性
	 * @param phone
	 * @param code
	 * @return
	 */
	@RequestMapping("/verify")
	@ResponseBody
	public JsonResult verifyCode(String phone,String code){
		JsonResult result =null;
		try {
			if (StringUtils.isEmpty(phone)) {
				throw new RuntimeException("手机号为空！");
			}
			
			if (StringUtils.isEmpty(code)) {
				throw new RuntimeException("验证码为空！");
			}
		
			System.out.println("=======================入参===phone="+phone + ",code="+code);
			boolean verifyCode = iVerifyCodeService.verifyCode(phone, code);
			System.out.println("=======================验证结果===" + verifyCode);
			result = new JsonResult(verifyCode, "验证结果" + verifyCode);
		} catch (Exception e) {
			result = new JsonResult(e.getMessage());
		}
		return result;
	}
	
}
