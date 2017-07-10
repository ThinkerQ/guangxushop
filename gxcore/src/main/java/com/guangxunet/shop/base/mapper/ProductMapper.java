package com.guangxunet.shop.base.mapper;

import com.guangxunet.shop.base.domain.Product;
import com.guangxunet.shop.base.query.ProductQueryObject;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product record);

    /**
     * 查询总条数
     * @return
     */
    Long selectByCount();

    /**
     * 条件查询
     * @param qo
     * @return
     */
    List<Product> selectByCondition(ProductQueryObject qo);
}