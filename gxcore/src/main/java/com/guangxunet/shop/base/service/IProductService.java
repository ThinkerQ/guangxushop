package com.guangxunet.shop.base.service;

import com.guangxunet.shop.base.domain.Product;
import com.guangxunet.shop.base.page.PageResult;
import com.guangxunet.shop.base.query.ProductQueryObject;

import java.util.List;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月11日 下午11:57:58 
* 类说明 首页Product
*/
public interface IProductService {
	/**
	 * 删除Product
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入一条Product
     * @param product
     * @return
     */
    int insert(Product product);

    /**
     * 选择一个Product
     * @param id
     * @return
     */
    Product selectByPrimaryKey(Long id);

    /**
     * 获取所有Product列表
     * @return
     */
    List<Product> selectAll();

    /**
     * 修改Product记录
     * @param product
     * @return
     */
    int updateByPrimaryKey(Product product);

    /**
     * 条件查询获取列表
     * @param qo
     * @return
     */
    PageResult selectByCondition(ProductQueryObject qo);

}
