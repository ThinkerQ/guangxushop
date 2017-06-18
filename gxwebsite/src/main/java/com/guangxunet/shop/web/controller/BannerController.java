package com.guangxunet.shop.web.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.guangxunet.shop.base.domain.Banner;
import com.guangxunet.shop.base.service.IBannerService;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.base.util.LoggerUtil;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月11日 下午11:20:01 
* 类说明 广告Banner控制器
*/
@Controller
public class BannerController {
	@Autowired
	private IBannerService iBannerService;
	
	@Value("${gx.core.bannerLimitNumber}")
    private int bannerLimitNumber;
	
	/**
	 * 获取banner列表
	 */
	@RequestMapping("/getBannerList.do")
	@ResponseBody()
	public JsonResult getBannerList(){
		JsonResult result = null;
		try {
			LoggerUtil.info("==================获取banner列表=====start===");
			List<Banner> bannerList = iBannerService.selectAll();
			
			//限制最多存放6条banner信息
			if (bannerList.size() > bannerLimitNumber) {
				bannerList = bannerList.subList(0, bannerLimitNumber);
			}
			
			LoggerUtil.info("==================获取banner列表=====结果===" + bannerList);
			
			if (bannerList != null && bannerList.size() > 0) {//集合非空
				result = new JsonResult(true, "获取成功！");
				result.setResult(bannerList);
			} else {
				result = new JsonResult("获取列表失败！");
			}
		} catch (Exception e) {
			result = new JsonResult(e.getMessage());
		}
		return result;
		
	}
	
}
