package com.guangxunet.shop.base.mapper;

import com.guangxunet.shop.base.domain.Permission;
import com.guangxunet.shop.base.query.QueryObject;

import java.util.List;

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