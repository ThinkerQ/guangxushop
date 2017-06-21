package com.guangxunet.shop.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guangxunet.shop.base.service.IVerifyCodeService;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.base.util.LoggerUtil;

/**发送验证码控制器
 * Created by King on 2016/10/11.
 */
@Controller
public class SendVerifyCodeController {
    @Autowired
    private IVerifyCodeService verifyCodeService;

    /**
     * 发送短信验证码
     * @param phoneNumber
     * @return
     */
    @RequestMapping("/sendVerifyCode.screen")
    @ResponseBody
    public JsonResult sendVerifyCode(String phoneNumber){
        JsonResult result = null;
        try {
				verifyCodeService.sendVerifyCode(phoneNumber);
				result = new JsonResult(true,"发送成功");
        } catch (Exception e) {
            result  = new JsonResult(e.getMessage());
        }
        return result;
    }
    
    /**
	 * 根据手机号和验证码验证验证码的正确性
	 * @param phone
	 * @param code
	 * @return
	 */
	@RequestMapping("/verifyCode.do")
	@ResponseBody
	public JsonResult verifyCode(String phone,String code){
		JsonResult result =null;
		try {
			LoggerUtil.info("=======================入参===phone="+phone + ",code="+code);
			boolean verifyCode = verifyCodeService.verifyCode(phone, code);
			LoggerUtil.info("=======================验证结果===" + verifyCode);
			
			result = new JsonResult(verifyCode, "验证通过");
		} catch (Exception e) {
			result = new JsonResult(e.getMessage());
		}
		return result;
	}
}
