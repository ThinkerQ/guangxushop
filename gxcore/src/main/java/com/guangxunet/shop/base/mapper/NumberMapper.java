package com.guangxunet.shop.base.mapper;

import com.guangxunet.shop.base.domain.Number;
import java.util.List;

public interface NumberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Number record);

    Number selectByPrimaryKey(Long id);

    List<Number> selectAll();

    int updateByPrimaryKey(Number record);
}