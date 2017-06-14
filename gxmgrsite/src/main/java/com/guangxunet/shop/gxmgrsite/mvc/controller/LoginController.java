package com.guangxunet.shop.gxmgrsite.mvc.controller;

import com.guangxunet.shop.base.domain.Logininfo;
import com.guangxunet.shop.base.domain.SystemMenu;
import com.guangxunet.shop.base.service.ILogininfoService;
import com.guangxunet.shop.base.service.ISystemMenuService;
import com.guangxunet.shop.base.util.Constants;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by chenmy on 2017/6/12.
 */
@Controller
public class LoginController extends SupervisorController{

    @Autowired
    private ILogininfoService iLogininfoService;

    @Autowired
    private ISystemMenuService systemMenuService;

    @RequestMapping("/home")
    public String index(){
        System.out.println("LoginController.index");
        return "home";
    }

    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login(String username, String password, HttpServletRequest request){
        JsonResult result = null;
        Logininfo logininfo = iLogininfoService.login(username, password, request.getRemoteAddr(), Logininfo.USER_MANAGER);

        if (logininfo == null) {
            result = new JsonResult("用户名或密码错误，请重试！");
        }else{
            //把用户放到session中
            UserContext.putCurrent(logininfo);
            //查询用户的所有权限,放到session中
            /*List<Permission> userPermission = permissionService.queryPermissionByEid(logininfo.getId());
            request.getSession().setAttribute(Constants.PERMISSION_IN_SESSION, userPermission);*/
            //获取菜单,然后删除用户没有权限的菜单
            List<SystemMenu> systemMenus = systemMenuService.queryMenu();
            //PermissionUtils.getMenuForPermission(systemMenus);
            request.getSession().setAttribute(Constants.MENU_IN_SESSION, systemMenus);
            result = new JsonResult(true,"登录成功！");
        }
        return result;
    }

    @RequestMapping("/menu")
    @ResponseBody
    public List<SystemMenu> menu(HttpSession session){
        return (List<SystemMenu>) session.getAttribute(Constants.MENU_IN_SESSION);
    }
}
