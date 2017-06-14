package com.guangxunet.shop.gxmgrsite.mapper;

import java.util.List;

import com.guangxunet.shop.base.query.QueryObject;
import com.guangxunet.shop.gxmgrsite.domain.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    List<Permission> selectByCondition(QueryObject qo);

    Long selectByCount();

    List<Permission> queryByRid(Long rid);

    Permission queryByResource(String function);

    List<Permission> queryPermissionByEid(Long lid);
}