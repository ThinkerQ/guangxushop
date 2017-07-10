package com.guangxunet.shop.base.service.impl;

import com.guangxunet.shop.base.domain.Product;
import com.guangxunet.shop.base.mapper.ProductMapper;
import com.guangxunet.shop.base.page.PageResult;
import com.guangxunet.shop.base.query.ProductQueryObject;
import com.guangxunet.shop.base.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/** 
* @author 作者 chenmy
* @version 创建时间：2017年7月7日
* 类说明 首页Product相关服务
*/
@Service("productService")
public class ProductServiceImpl implements IProductService{
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return productMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Product product) {
		return productMapper.insert(product);
	}

	@Override
	public Product selectByPrimaryKey(Long id) {
		return productMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Product> selectAll() {
		return productMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Product product) {
		return productMapper.updateByPrimaryKey(product);
	}

	@Override
	public PageResult selectByCondition(ProductQueryObject qo) {
		PageResult result = null;
		Long count = productMapper.selectByCount();
		if (count > 0) {
			List<Product> products = productMapper.selectByCondition(qo);
			result = new PageResult(count, products);
			return result;
		} else {
			return PageResult.result;
		}
	}

}
