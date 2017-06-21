package com.guangxunet.shop.gxmgrsite.mvc.controller;

import com.guangxunet.shop.base.page.PageResult;
import com.guangxunet.shop.base.service.IBannerService;
import com.guangxunet.shop.base.query.BannerQueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 广告管理控制
 * Created by chenmy on 2017/6/19.
 */
@Controller
@RequestMapping("/supervisor/banner")
public class BannerController extends SupervisorController{

    @Autowired
    private IBannerService bannerService;

    @RequestMapping("/home")
    public String home(){
        System.out.println("BannerController.home");
        return "/banner/banner";
    }

    /**
     * 广告列表
     * @return
     */
    @RequestMapping("/bannerList")
    @ResponseBody
    public PageResult bannerList(BannerQueryObject qo){
        PageResult result = bannerService.selectByCondition(qo);
        //model.addAttribute("banners",bannerService.selectAll());
        return result;
    }


}
