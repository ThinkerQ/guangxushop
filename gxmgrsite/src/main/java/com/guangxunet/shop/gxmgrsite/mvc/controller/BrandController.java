package com.guangxunet.shop.gxmgrsite.mvc.controller;

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
		int count = iBrandService.insert(brand);
		
		if (count > 0) {
			jsonResult = new JsonResult(true,"保存成功！");
		}else{
			jsonResult = new JsonResult(false,"保存失败！");
		}
		
		return jsonResult;
	}
	
	
}
