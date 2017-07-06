package com.guangxunet.shop.base.mapper;

import com.guangxunet.shop.base.domain.Period;
import java.util.List;

public interface PeriodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Period record);

    Period selectByPrimaryKey(Long id);

    List<Period> selectAll();

    int updateByPrimaryKey(Period record);
}