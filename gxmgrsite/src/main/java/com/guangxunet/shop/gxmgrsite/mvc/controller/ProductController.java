package com.guangxunet.shop.gxmgrsite.mvc.controller;

import com.guangxunet.shop.base.domain.Product;
import com.guangxunet.shop.base.page.PageResult;
import com.guangxunet.shop.base.query.ProductQueryObject;
import com.guangxunet.shop.base.service.IProductService;
import com.guangxunet.shop.base.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/** 
* @author 作者 Chenmy
* @version 创建时间：2017年7月7日
* 类说明 商品管理
*/
@Controller
@RequestMapping("/supervisor/product")
public class ProductController extends SupervisorController{
	@Autowired
	private IProductService iProductService; 
	
	/**
	 * 返回片商品页面
	 * @return
	 */
	@RequestMapping("/product.do")
    public String home(){
        return "/product/product";
    }
	
	/**
	 * 商品列表
	 * @param qo
	 * @return
	 */
	@RequestMapping("/productList.do")
	@ResponseBody
	public PageResult productList(ProductQueryObject qo){
        PageResult result = iProductService.selectByCondition(qo);
        return result;
    }
	
	/**
	 * 更新商品
	 * @param product
	 */
	@RequestMapping("/productUpdate.do")
	@ResponseBody
	public JsonResult productUpdate(Product product){
		/*try {
			String name =  StringUtils.getReCharSet(product.getName());
			product.setName(name);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		LoggerUtil.info("----------Product----------"+product.getName());
		*/
		JsonResult jsonResult = null;
		int count = iProductService.updateByPrimaryKey(product);
		
		if (count > 0) {
			jsonResult = new JsonResult(true,"保存成功！");
		}else{
			jsonResult = new JsonResult(false,"保存失败！");
		}
		
		return jsonResult;
	}
	
	@RequestMapping("/productSave.do")
	@ResponseBody
	public JsonResult productSave(Product product){
		JsonResult jsonResult = null;
		/*String name =  StringUtils.getReCharSet(product.getName());
		product.setName(name);*/
		int count = iProductService.insert(product);
		
		if (count > 0) {
			jsonResult = new JsonResult(true,"保存成功！");
		}else{
			jsonResult = new JsonResult(false,"保存失败！");
		}
		
		return jsonResult;
	}
	
	/**
	 *删除商品
	 * @param product
	 * @return
	 */
	@RequestMapping("/productDelete.do")
	@ResponseBody
	public JsonResult productDelete(Product product){
		JsonResult jsonResult = null;
		try {
			int count = iProductService.deleteByPrimaryKey(product.getId());
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
