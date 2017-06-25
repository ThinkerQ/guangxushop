package com.guangxunet.shop.base.service;


/**
 * 短信验证码相关服务
 * @author King
 *创建时间：2017年6月8日 下午23:50:05 
 */
public interface IVerifyCodeService {

	/**
	 * 发送短信验证码
	 * @param phoneNumber
     */
	void sendVerifyCode(String phoneNumber) throws Exception;

	/**
	 * 短信验证码校验
	 * @param phoneNumber
	 * @param verifyCode
	 * true:验证通过
	 * false:验证失败
	 */
	boolean verifyCode(String phoneNumber, String verifyCode) throws Exception;
	
	/**
	 * 批量发送短信（阿里云）
	 */
	void batchPublishSMSMessage(String phoneNumber,String code) throws Exception;

}
