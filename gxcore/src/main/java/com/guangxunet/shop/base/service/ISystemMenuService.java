package com.guangxunet.shop.base.service;

import com.guangxunet.shop.base.domain.SystemMenu;

import java.util.List;

/**
 * Created by chenmy on 2017/6/12.
 * 菜单服务
 *
 */
public interface ISystemMenuService {
    /**
     * 所有菜单
     * @return
     */
    List<SystemMenu> queryMenu();
}
