package com.guangxunet.shop.base.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月8日 下午11:55:50 
* 类说明 短信验证码相关服务
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class VerifyCodeServiceTest {
	@Autowired
	private IVerifyCodeService iVerifyCodeService;
	
	@Test
	public void testVerify() throws Exception {
//		iVerifyCodeService.batchPublishSMSMessage("18211674995");
	}
	
	/**
	 * 发送手机验证码
	 * @throws Exception
	 */
	@Test
	public void testVerifyCode() throws Exception {
//		iVerifyCodeService.sendVerifyCode("18211674995");
	}
	
	/**
	 * 验证是否正确
	 * @throws Exception
	 */
	@Test
	public void VerifyCodeTest() throws Exception {
//		iVerifyCodeService.verifyCode("18211674995", "7d64");
	}public VerifyCodeServiceTest() {
	}
	
	@Test
	public void testSession() throws Exception {
//		HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
//		System.out.println(session);
	}
	
	@Test
	public void testMap() throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("success", true);
		params.put("message", "获取数据成功！");
		List<String> list = new ArrayList<String>();
		list.add("King");
		list.add("King2");
		list.add("King3");
		params.put("result", list);
		System.out.println("==================params="+params);
	}
}
