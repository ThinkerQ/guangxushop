package com.guangxunet.shop.base.mapper;

import com.guangxunet.shop.base.domain.SystemMenu;
import java.util.List;

public interface SystemMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemMenu record);

    SystemMenu selectByPrimaryKey(Long id);

    List<SystemMenu> selectAll();

    int updateByPrimaryKey(SystemMenu record);
}