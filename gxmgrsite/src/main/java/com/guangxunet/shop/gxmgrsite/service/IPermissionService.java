package com.guangxunet.shop.gxmgrsite.service;

import java.util.List;

import com.guangxunet.shop.base.page.PageResult;
import com.guangxunet.shop.base.query.QueryObject;
import com.guangxunet.shop.gxmgrsite.domain.Permission;

/**
 * Created by chenmy on 2017/6/10.
 * 权限服务
 */
public interface IPermissionService {
    PageResult selectByCondition(QueryObject qo);

    PageResult queryByRid(Long rid);

    Permission queryByResource(String function);

    List<Permission> queryPermissionByEid(Long id);

    void reload();
}
