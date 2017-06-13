package com.guangxunet.shop.base.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guangxunet.shop.base.domain.Banner;
import com.guangxunet.shop.base.util.LoggerUtil;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月12日 上午12:02:58 
* 类说明 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class BannerTest {
	@Autowired
	private IBannerService iBannerService;
	
	@Test
	public void testBanner() throws Exception {
		List<Banner> selectAll = iBannerService.selectAll();
		for (Banner banner : selectAll) {
			System.out.println(banner);
		}
	}
	
	@Test
	public void testLogger() throws Exception {
		LoggerUtil.info("===========测试参数==============");
	}
}
