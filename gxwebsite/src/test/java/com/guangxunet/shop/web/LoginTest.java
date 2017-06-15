package com.guangxunet.shop.web;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guangxunet.shop.base.domain.Logininfo;
import com.guangxunet.shop.base.service.ILogininfoService;
import com.guangxunet.shop.base.util.LoggerUtil;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月7日 下午9:32:46 
* 类说明 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class LoginTest {
	@Autowired
	private ILogininfoService iLogininfoService;
	
	/**
	 * 前台新用户注册
	 * @throws Exception
	 */
	@Test
	public void testRegister() throws Exception {
//		iLogininfoService.register("admin", "admin");
//		System.out.println("===注册成功success===");
	}
	
	@Test
	public void testLogin() throws Exception {
//		Logininfo login = iLogininfoService.login("18211674998", "1234", "121.121.121", Logininfo.USER_NORMAL);
//		System.out.println(login);
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLogger() throws Exception {
		LoggerUtil.info("===========测试参数==============");
		LoggerUtil.warn("===========测试参数warn==============");
		LoggerUtil.getCodeLocation();
	}
}
