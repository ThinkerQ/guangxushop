package com.guangxunet.shop.gxmgrsite.mvc.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guangxunet.shop.base.domain.Brand;
import com.guangxunet.shop.base.page.PageResult;
import com.guangxunet.shop.base.query.BannerQueryObject;
import com.guangxunet.shop.base.query.BrandQueryObject;
import com.guangxunet.shop.base.service.IBrandService;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.base.util.LoggerUtil;
import com.guangxunet.shop.base.util.StringUtils;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月30日 下午11:31:56 
* 类说明 品牌管理
*/
@Controller
@RequestMapping("/Brand")
public class BrandController extends SupervisorController{
	@Autowired
	private IBrandService iBrandService; 
	
	/**
	 * 返回片品牌页面
	 * @return
	 */
	@RequestMapping("/brand.do")
    public String home(){
        return "/brand/brand";
    }
	
	/**
	 * 品牌列表
	 * @param qo
	 * @return
	 */
	@RequestMapping("/brandList.do")
	@ResponseBody
	public PageResult brandList(BrandQueryObject qo){
        PageResult result = iBrandService.selectByCondition(qo);
        return result;
    }
	
	/**
	 * 更新品牌
	 * @param id
	 */
	@RequestMapping("/brandUpdate.do")
	@ResponseBody
	public JsonResult brandUpdate(Brand brand){
		try {
			String name =  StringUtils.getReCharSet(brand.getName());
			brand.setName(name);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		LoggerUtil.info("----------Brand----------"+brand.getName());
		
		JsonResult jsonResult = null;
		int count = iBrandService.updateByPrimaryKey(brand);
		
		if (count > 0) {
			jsonResult = new JsonResult(true,"保存成功！");
		}else{
			jsonResult = new JsonResult(false,"保存失败！");
		}
		
		return jsonResult;
	}
	
	@RequestMapping("/brandSave.do")
	@ResponseBody
	public JsonResult brandSave(Brand brand){
		JsonResult jsonResult = null;
		String name =  StringUtils.getReCharSet(brand.getName());
		brand.setName(name);
		int count = iBrandService.insert(brand);
		
		if (count > 0) {
			jsonResult = new JsonResult(true,"保存成功！");
		}else{
			jsonResult = new JsonResult(false,"保存失败！");
		}
		
		return jsonResult;
	}
	
	/**
	 *删除品牌
	 * @param brand
	 * @return
	 */
	@RequestMapping("/brandDelete.do")
	@ResponseBody
	public JsonResult brandDelete(Brand brand){
		JsonResult jsonResult = null;
		try {
			int count = iBrandService.deleteByPrimaryKey(brand.getId());
			if (count > 0) {
				jsonResult = new JsonResult(true, "删除成功！");
			} else {
				jsonResult = new JsonResult(false, "删除失败！");
			} 
		} catch (Exception e) {
			jsonResult = new JsonResult(false, "系统异常，请联系管理员！");
			e.printStackTrace();
		}
		return jsonResult;
	}
	
}
