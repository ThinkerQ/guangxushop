package com.guangxunet.shop.gxmgrsite.mvc.controller;

import com.guangxunet.shop.business.domain.Category;
import com.guangxunet.shop.business.service.ICategoryService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 商品管理管理控制器
 * Created by King on 2017/7/04.
 */
@Controller
@RequestMapping("/Category")
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

}
