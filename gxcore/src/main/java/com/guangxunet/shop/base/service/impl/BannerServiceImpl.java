package com.guangxunet.shop.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guangxunet.shop.base.domain.Banner;
import com.guangxunet.shop.base.mapper.BannerMapper;
import com.guangxunet.shop.base.page.PageResult;
import com.guangxunet.shop.base.query.BannerQueryObject;
import com.guangxunet.shop.base.service.IBannerService;


/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月12日 上午12:00:40 
* 类说明 首页Banner相关服务
*/
@Service("iBannerService")
public class BannerServiceImpl implements IBannerService{
	@Autowired
	private BannerMapper bannerMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return bannerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Banner banner) {
		return bannerMapper.insert(banner);
	}

	@Override
	public Banner selectByPrimaryKey(Integer id) {
		return bannerMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Banner> selectAll() {
		return bannerMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Banner banner) {
		return bannerMapper.updateByPrimaryKey(banner);
	}

	@Override
	public PageResult selectByCondition(BannerQueryObject qo) {
		PageResult result = null;
		Long count = bannerMapper.selectByCount();
		if (count > 0) {
			List<Banner> banners = bannerMapper.selectByCondition(qo);
			result = new PageResult(count, banners);
			return result;
		} else {
			return PageResult.result;
		}
	}

}
