package com.guangxunet.shop.gxmgrsite.mvc.listeners;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.guangxunet.shop.base.service.ILogininfoService;

/**Spring监听启动事件：初始化创建第一个管理员
 * 实现ApplicationListener的都是SPring的监听器
 * 上下文监听事件ContextRefreshedEvent
 * Created by King on 2017/6/6.
 */
@Component
public class InitAdminListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ILogininfoService loginfoService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.loginfoService.initAdmin();//初始化创建管理员
    }
}
