package com.guangxunet.shop.gxmgrsite.util;

import com.guangxunet.shop.base.domain.Logininfo;
import com.guangxunet.shop.base.domain.Permission;
import com.guangxunet.shop.base.domain.SystemMenu;
import com.guangxunet.shop.base.util.BidConst;
import com.guangxunet.shop.base.util.Constants;
import com.guangxunet.shop.base.util.UserContext;
import com.guangxunet.shop.gxmgrsite.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Component
public class PermissionUtils {

    private static IPermissionService permissionService;

    @Autowired
    public void setPermissionService(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }
    /**
     * 拼接方法的权限表达式
     */
    public static String buildExpression(Method method) {
        //获取当前Method所在类的全限定名称
        String className=method.getDeclaringClass().getName();
        String methodName=method.getName();
        return className+":"+methodName;
    }

    //是否有权限或者无须权限
    public static boolean checkPermission(String function) {
        Logininfo logininfo = UserContext.getCurrent();
        //如果用户是超级管理员,直接放行
        if (logininfo.getUsername().equals(BidConst.DEFAULT_ADMIN_NAME)) {
            return true;
        }
        Permission permissionl = permissionService.queryByResource(function);
        if (permissionl != null) {
            //需要权限控制的处理
            //查看用户拥有的权限
            List<Permission> permissions = (List<Permission>) UserContext.getSession().getAttribute(Constants.PERMISSION_IN_SESSION);
            String allPermission = (function.split(":")[0] + ":" + "all");
            if (permissions != null) {//有权限
                for (Permission permission : permissions) {
                    //判断是否有该权限
                    if (permission.getResource().equals(function) || permission.getResource().equals(allPermission)) {
                        return true;
                    }
                }
            }
            return false;
        }
        //不需要权限控制,可直接放行
        return true;
    }

    public static void getMenuForPermission(List<SystemMenu> menus) {
        //因为在删除集合的时候,索引发生改变,如果从0开始索引的改变会造成有的数据删不了,从后删除,则索引不影响
        SystemMenu menu = null;
        for (int i = menus.size() - 1; i >= 0; i--) {
            menu = menus.get(i);
            //查看是否有权限
            if (checkPermission(menu.getAttributes())) {
                //子菜单再查看
                /*if (menu.getChildren() != null && menu.getChildren().size() > 0) {
                    getMenuForPermission(menu.getChildren());//递归操作
                }*/
            } else {
                //没有就删除
                menus.remove(i);
            }

        }
    }
}
