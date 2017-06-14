package com.guangxunet.shop.base.service.impl;

import com.guangxunet.shop.base.domain.SystemMenu;
import com.guangxunet.shop.base.mapper.SystemMenuMapper;
import com.guangxunet.shop.base.service.ISystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */
@Service
public class SystemMenuServiceImpl implements ISystemMenuService {

    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Override
    public List<SystemMenu> queryMenu() {
        List<SystemMenu> menus = systemMenuMapper.selectAll();
        List<SystemMenu> systemMenuList = new ArrayList<>();
        //添加第一层菜单
        for (SystemMenu menu : menus) {
            if(menu.getParentId() == null){
                systemMenuList.add(menu);
            }
        }
        //第二层菜单
        for (SystemMenu systemMenu : systemMenuList) {
            List<SystemMenu> children = new ArrayList<>();
            for (SystemMenu menu : menus) {
                if(systemMenu.getId()==menu.getParentId()){
                    children.add(menu);
                }
            }
            systemMenu.setChildren(children);
        }

        return systemMenuList;
    }
}
