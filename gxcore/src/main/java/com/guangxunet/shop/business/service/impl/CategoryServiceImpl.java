package com.guangxunet.shop.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guangxunet.shop.business.domain.Category;
import com.guangxunet.shop.business.mapper.CategoryMapper;
import com.guangxunet.shop.business.service.ICategoryService;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年7月4日 下午10:12:28 
* 类说明 
*/
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<Category> categoryOneListAll() {
		return categoryMapper.selectOneAll();
	}

	@Override
	public List<Map<String,Object>> selectByParentId(String id) {
		return categoryMapper.selectByParentId(id);
	}
	
	
}
