package com.guangxunet.shop.gxmgrsite.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guangxunet.shop.base.query.QueryObject;
import com.guangxunet.shop.gxmgrsite.domain.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int handlerRelation(@Param("rid") Long rid, @Param("pid") Long pid);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    List<Role> selectByCondition(QueryObject qo);

    Long selectByCount();

    int deletePermissionByRid(Long id);

    List<Long> queryRoleByLid(Long eid);
}