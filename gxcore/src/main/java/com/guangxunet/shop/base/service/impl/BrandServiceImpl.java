package com.guangxunet.shop.base.service.impl;

import com.guangxunet.shop.base.domain.Brand;
import com.guangxunet.shop.base.mapper.BrandMapper;
import com.guangxunet.shop.base.page.PageResult;
import com.guangxunet.shop.base.query.BrandQueryObject;
import com.guangxunet.shop.base.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月30日 下午11:51:06 
* 类说明 
*/
@Service("brandService")
public class BrandServiceImpl implements IBrandService {
	@Autowired
	private BrandMapper brandMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return brandMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Brand brand) {
		return brandMapper.insert(brand);
	}

	@Override
	public Brand selectByPrimaryKey(Long id) {
		return brandMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Brand> selectAll() {
		return brandMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Brand record) {
		return brandMapper.updateByPrimaryKey(record);
	}

	/**
	 * 分页显示/高级查询
	 */
	@Override
	public PageResult selectByCondition(BrandQueryObject qo) {
		PageResult result = null;
		Long count = brandMapper.selectCountByCondition(qo);
		if (count > 0) {
			List<Brand> banners = brandMapper.selectByCondition(qo);
			result = new PageResult(count, banners);
			return result;
		} else {
			return PageResult.result;
		}
	}
	
	
}
