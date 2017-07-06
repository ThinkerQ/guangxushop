package com.guangxunet.shop.base.mapper;

import com.guangxunet.shop.base.domain.ProductDetail;
import java.util.List;

public interface ProductDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductDetail record);

    ProductDetail selectByPrimaryKey(Long id);

    List<ProductDetail> selectAll();

    int updateByPrimaryKey(ProductDetail record);
}