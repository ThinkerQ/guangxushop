package com.guangxunet.shop.gxmgrsite.mvc.controller;

import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.base.util.LoggerUtil;
import com.guangxunet.shop.business.domain.Category;
import com.guangxunet.shop.business.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import shaded.org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;


/**
 * 商品管理管理控制器
 * Created by King on 2017/7/04.
 */
@Controller
@RequestMapping("/supervisor/category")
public class CategoryController extends SupervisorController{

    @Autowired
    private ICategoryService iCategoryService;

    /**
     * 类目页面
     * @return
     */
    @RequestMapping("/list.do")
    public String home(){
        return "/category/category";
    }

    @RequestMapping("/systemDiction.do")
    public String systemDiction(){
    	return "/systemDictionary/systemDictionary";
    }

    /**
     * 一级类目列表
     * @return
     */
    @RequestMapping("/categoryOneListAll.do")
    @ResponseBody
    public List<Category> categoryOneListAll(){
    	return iCategoryService.categoryOneListAll();
    }
    
    /**
     * 二级类目列表
     * @return
     */
    @RequestMapping("/categoryTwoListSelectItemById.do")
    @ResponseBody
    public List<Map<String,Object>> categoryTwoListSelectItemById(String pid){
    	return iCategoryService.selectByParentId(pid);
    }
    
    /**
     * 更新商品类目
     */
    @RequestMapping("/categorysItemUpdate.do")
    @ResponseBody
    public JsonResult categorysItemUpdate(@RequestParam Map<String,Object> params){
    	JsonResult result = null;
    	LoggerUtil.info("-------------------params="+params);
    	//非空检验
    	String name = (String) params.get("name");
    	
    	try {
    		
			if (StringUtils.isEmpty(name)) {
				throw new RuntimeException("新类目名称为空！");
			}
			
			iCategoryService.updateByPrimaryKey(params);
			result = new JsonResult(true,"更新成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result = new JsonResult(false,e.getMessage());
		}
    	return result; 
    }
    
    /**
     * 添加商品类目
     */
    @RequestMapping("/categorysItemSave.do")
    @ResponseBody
    public JsonResult categorysItemSave(@RequestParam Map<String,Object> params){
    	JsonResult result = null;
    	LoggerUtil.info("-------------------父级params="+params);
    	//非空检验
    	String name = (String) params.get("name");
    	
    	try {
    		
			if (StringUtils.isEmpty(name)) {
				throw new RuntimeException("新类目名称为空！");
			}
			
			iCategoryService.insertCateGorysItem(params);
			result = new JsonResult(true,"保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result = new JsonResult(false,e.getMessage());
		}
    	
    	return result; 
    }
    
    /**
     * 删除商品类目
     */
    @RequestMapping("/categorysItemDelete.do")
    @ResponseBody
    public JsonResult delete(String id){
    	JsonResult result = null;
    	LoggerUtil.info("-------------------id="+id);
    	//非空检验
    	
    	try {
    		
			if (StringUtils.isEmpty(id)) {
				throw new RuntimeException("类目编号为空！");
			}
			
			Long idL = Long.valueOf(id);
			LoggerUtil.info("-------------------idL="+idL);
			
			iCategoryService.deleteByPrimaryKey(idL);
			result = new JsonResult(true,"删除成功！");
			
		} catch (Exception e) {
			e.printStackTrace();
			result = new JsonResult(false,e.getMessage());
		}
    	
    	return result; 
    }
    
}
