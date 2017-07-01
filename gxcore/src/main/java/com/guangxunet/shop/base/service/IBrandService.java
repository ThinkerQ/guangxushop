package com.guangxunet.shop.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.guangxunet.shop.base.domain.Brand;
import com.guangxunet.shop.base.mapper.BrandMapper;
import com.guangxunet.shop.base.page.PageResult;
import com.guangxunet.shop.base.query.BannerQueryObject;
import com.guangxunet.shop.base.query.BrandQueryObject;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月30日 下午11:47:42 
* 类说明 品牌相关服务
*/
@Service
public interface IBrandService {
	
	int deleteByPrimaryKey(Long id);

    int insert(Brand record);

    Brand selectByPrimaryKey(Long id);

    List<Brand> selectAll();

    int updateByPrimaryKey(Brand record);

    /**
     * 条件显示/分页显示
     * @param qo
     * @return
     */
	PageResult selectByCondition(BrandQueryObject qo);
	
}
