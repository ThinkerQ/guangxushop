package com.guangxunet.shop.base.service.impl;

import com.guangxunet.shop.base.domain.ProductDetail;
import com.guangxunet.shop.base.mapper.ProductDetailMapper;
import com.guangxunet.shop.base.service.IProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author 作者 chenmy
 * @version 创建时间：2017年7月7日
 * 类说明 首页ProductDetail相关服务
*/
@Service("productDetailDetailService")
public class ProductDetailServiceImpl implements IProductDetailService{
	@Autowired
	private ProductDetailMapper productDetailMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return productDetailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ProductDetail productDetail) {
		return productDetailMapper.insert(productDetail);
	}

	@Override
	public ProductDetail selectByPrimaryKey(Long id) {
		return productDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ProductDetail> selectAll() {
		return productDetailMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(ProductDetail productDetail) {
		return productDetailMapper.updateByPrimaryKey(productDetail);
	}

}
