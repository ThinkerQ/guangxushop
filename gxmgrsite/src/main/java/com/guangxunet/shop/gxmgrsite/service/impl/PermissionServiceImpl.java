package com.guangxunet.shop.gxmgrsite.service.impl;


import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.guangxunet.shop.base.page.PageResult;
import com.guangxunet.shop.base.query.QueryObject;
import com.guangxunet.shop.base.domain.Permission;
import com.guangxunet.shop.base.mapper.PermissionMapper;
import com.guangxunet.shop.gxmgrsite.mvc.controller.SupervisorController;
import com.guangxunet.shop.gxmgrsite.service.IPermissionService;
import com.guangxunet.shop.gxmgrsite.util.PermissionUtils;
import com.guangxunet.shop.gxmgrsite.util.RequiredPermission;

/**
 * Created by chenmy
 */
@Service
public class PermissionServiceImpl implements IPermissionService,ApplicationContextAware {
    @Autowired
    private PermissionMapper permissionMapper;
    private ApplicationContext ctx;
    public void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    public PageResult selectByCondition(QueryObject qo) {
        PageResult result = null;
        Long count = permissionMapper.selectByCount();
        if (count > 0) {
            List<Permission> permissions = permissionMapper.selectByCondition(qo);
            result = new PageResult(count, permissions);
            return result;
        } else {
            return PageResult.result;
        }
    }

    public PageResult queryByRid(Long rid) {
        List<Permission> result =permissionMapper.queryByRid(rid);
        return new PageResult(0L,result);
    }

    public Permission queryByResource(String function) {
        return permissionMapper.queryByResource(function);
    }

    public List<Permission> queryPermissionByEid(Long eid) {
        return permissionMapper.queryPermissionByEid(eid);
    }

    public void reload() {
        //0:查询出数据库所有的权限表达式
        List<Permission> ps = permissionMapper.selectAll();
        Set<String> expressions = new HashSet<>();//数据库中已经存在的权限表达式
        for (Permission p : ps) {
            expressions.add(p.getResource());
        }
        //1:扫描所有的SupervisorController的子类
        Map<String, SupervisorController> beansMap = ctx.getBeansOfType(SupervisorController.class);
        Collection<SupervisorController> actions = beansMap.values();
        //2:迭代每一个Action类
        for (SupervisorController action : actions) {
            //3:迭代每一个Action类中的方法
            Method[] ms = action.getClass().getDeclaredMethods();
            for (Method m : ms) {
                //4:判断当前方法上是否存在RequiredPermission标签
                RequiredPermission rp = m.getAnnotation(RequiredPermission.class);
                String exp = PermissionUtils.buildExpression(m);//获取该方法对应的权限表达式
                if (rp != null) {
                    if (!expressions.contains(exp)) {
                        String name = rp.value();//权限名称
                        Permission p = new Permission();
                        p.setName(name);
                        p.setResource(exp);
                        permissionMapper.insert(p);
                    }
                }
            }
        }
    }


}
