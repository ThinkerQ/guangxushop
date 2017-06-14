package com.guangxunet.shop.base.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guangxunet.shop.base.domain.Banner;
import com.guangxunet.shop.base.domain.Systemlog;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月13日 下午9:48:53 
* 类说明 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SystemLogTest {
	@Autowired
	private ISystemLogService iSystemLogService;
	
	@Test
	public void testBanner() throws Exception {
		/*Systemlog log = new Systemlog();
		log.setFunction("测试");
		log.setId(1L);
		log.setOptime(new Date());
		log.setParams("name");
		log.setOpuserId(1L);
		log.setOpip("101.101.101");
		iSystemLogService.save(log);*/
	}
	
}
