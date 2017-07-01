package com.guangxunet.shop.base.mapper;

import com.guangxunet.shop.base.domain.Banner;
import com.guangxunet.shop.base.domain.Brand;
import com.guangxunet.shop.base.query.BannerQueryObject;
import com.guangxunet.shop.base.query.BrandQueryObject;

import java.util.List;

public interface BrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Brand record);

    Brand selectByPrimaryKey(Long id);

    List<Brand> selectAll();

    int updateByPrimaryKey(Brand record);

	Long selectCountByCondition(BrandQueryObject qo);

	List<Brand> selectByCondition(BrandQueryObject qo);
}