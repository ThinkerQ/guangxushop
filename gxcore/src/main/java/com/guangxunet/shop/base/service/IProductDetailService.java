package com.guangxunet.shop.base.service;

import com.guangxunet.shop.base.domain.ProductDetail;

import java.util.List;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月11日 下午11:57:58 
* 类说明 首页ProductDetail
*/
public interface IProductDetailService {
	/**
	 * 删除ProductDetail
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入一条ProductDetail
     * @param productDetail
     * @return
     */
    int insert(ProductDetail productDetail);

    /**
     * 选择一个ProductDetail
     * @param id
     * @return
     */
    ProductDetail selectByPrimaryKey(Long id);

    /**
     * 获取所有ProductDetail列表
     * @return
     */
    List<ProductDetail> selectAll();

    /**
     * 修改ProductDetail记录
     * @param productDetail
     * @return
     */
    int updateByPrimaryKey(ProductDetail productDetail);

}
