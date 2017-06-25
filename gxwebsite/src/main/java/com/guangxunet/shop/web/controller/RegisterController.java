package com.guangxunet.shop.web.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guangxunet.shop.base.service.ILogininfoService;
import com.guangxunet.shop.base.service.IVerifyCodeService;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.base.util.PhoneFormatCheckUtils;
import com.guangxunet.shop.base.util.UserContext;
import com.guangxunet.shop.base.vo.VerifyCodeVO;

import shaded.org.apache.commons.lang3.StringUtils;

/**注册相关服务
 * Created by Administrator on 2016/9/30.
 */
@Controller
@RequestMapping("Register")
public class RegisterController {
    @Autowired
    private ILogininfoService logininfoService;
    @Autowired
    private IVerifyCodeService iVerifyCodeService;
    /**
     * 前台新用户只能通过手机号注册
     * @param mobile
     * @param password
     * @return
     */
    @RequestMapping("/register.screen")
    @ResponseBody
    public JsonResult register(String mobile,String verifycode, String password,String confirmPwd){
        JsonResult result = null;
        try {
        	if (StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)||
        			StringUtils.isEmpty(confirmPwd)||
        			StringUtils.isEmpty(verifycode)
    			) {
				throw new RuntimeException("参数为空");
			}
        	
        	if (!StringUtils.equals(password, confirmPwd)) {
        		throw new RuntimeException("两次输入的密码不一致，请重新输入！");
			}
        	
        	//校验短信验证码是否正确
        	boolean isCodePoss = iVerifyCodeService.verifyCode(mobile,verifycode);
        	if (!isCodePoss) {
        		throw new RuntimeException("短信验证不通过！");
			}
        	
            logininfoService.register(mobile, password);
            result = new JsonResult(true,"注册成功！");
            Map<String,Object> resultmap  = new HashMap<String,Object>();
            resultmap.put("mobile", mobile);
            result.setResult(resultmap);
        } catch (Exception e) {
            result = new JsonResult(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 检查手机号是否已经被注册
     * @param username
     * @return
     */
    @RequestMapping("/checkUserByPhoneNumber.screen")
    @ResponseBody
    public JsonResult checkUserPhoneNumberExist(String mobile){
    	JsonResult result = null;
    	
    	try {
			//验证手机号
			if (StringUtils.isEmpty(mobile)) {
				throw new RuntimeException("手机号为空！");
			}
			//手机号规则校验
			if (!PhoneFormatCheckUtils.isChinaPhoneLegal(mobile)) {
				throw new RuntimeException("手机号不符合规则，仅支持大陆手机号注册！");
			}
			
			boolean exist = logininfoService.checkUserPhoneNumberExist(mobile);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("isExist", exist);
			
			result = new JsonResult(true,"调用成功！");
			result.setResult(resultMap);
		} catch (Exception e) {
			result = new JsonResult(false,e.getMessage());
		}  
		return result;
    }
    
    /**
     * 检查手机号是否被注册
     * @param mobile
     * @return Boolean
     */
    @RequestMapping("/checkUserByPhoneNumberIsExist.screen")
    @ResponseBody
    public Boolean checkUserPhoneNumberIsExist(String mobile){
    	Boolean isMobile = PhoneFormatCheckUtils.isChinaPhoneLegal(mobile);//手机号合法性校验
    	
    	return !logininfoService.checkUserPhoneNumberExist(mobile);
    }

}
