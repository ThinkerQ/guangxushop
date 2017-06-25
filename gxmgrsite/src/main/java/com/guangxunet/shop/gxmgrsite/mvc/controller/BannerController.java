package com.guangxunet.shop.gxmgrsite.mvc.controller;

import com.guangxunet.shop.base.domain.Banner;
import com.guangxunet.shop.base.page.PageResult;
import com.guangxunet.shop.base.query.BannerQueryObject;
import com.guangxunet.shop.base.service.IBannerService;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.base.util.StringUtils;
import com.guangxunet.shop.gxmgrsite.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

/**
 * 广告管理控制
 * Created by chenmy on 2017/6/19.
 */
@Controller
@RequestMapping("/supervisor/banner")
public class BannerController extends SupervisorController{

    @Autowired
    private IBannerService bannerService;
    @Autowired
    private ServletContext servletContext;

    @RequestMapping("/home")
    public String home(){
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
        return result;
    }

    /**
     * 广告图片上传
     * @param file
     * @return
     */
    @RequestMapping("/bannerUpload")
    @ResponseBody
    public String upload(MultipartFile file){
        String dic = "/upload";
        String fileName = UploadUtil.upload(file, servletContext.getRealPath(dic));
        return fileName;
    }

    /**
     * 广告保存
     * @return
     */
    @RequestMapping("/bannerSave")
    @ResponseBody
    public JsonResult bannerSave(Banner banner){
        JsonResult jsonResult = new JsonResult();
        if(!StringUtils.hasLength(banner.getImagePath())){
            jsonResult.setMessage("请上传图片!");
        }
        if(!StringUtils.hasLength(banner.getType())){
            jsonResult.setMessage("请添加类型!");
        }
        int count = bannerService.insert(banner);
        if(count>0){
            jsonResult.setSuccess(true);
            jsonResult.setMessage("保存成功!");
        }else {
            jsonResult.setMessage("保存失败!");
        }
        return jsonResult;
    }

    /**
     * 广告修改
     * @return
     */
    @RequestMapping("/bannerUpdate")
    @ResponseBody
    public JsonResult bannerUpdate(Banner banner){
        JsonResult jsonResult = new JsonResult();
        if(!StringUtils.hasLength(banner.getImagePath())){
            jsonResult.setMessage("请上传图片!");
        }
        if(!StringUtils.hasLength(banner.getType())){
            jsonResult.setMessage("请添加类型!");
        }
        int count = bannerService.updateByPrimaryKey(banner);
        if(count>0){
            jsonResult.setSuccess(true);
            jsonResult.setMessage("保存成功!");
        }else {
            jsonResult.setMessage("保存失败!");
        }
        return jsonResult;
    }
}
