package com.guangxunet.shop.base.mapper;

import com.guangxunet.shop.base.domain.Banner;
import com.guangxunet.shop.base.query.BannerQueryObject;

import java.util.List;

public interface BannerMapper {
	/**
	 * 删除Banner
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入一条Banner
     * @param banner
     * @return
     */
    int insert(Banner banner);

    /**
     * 选择一个Banner
     * @param id
     * @return
     */
    Banner selectByPrimaryKey(Integer id);

    /**
     * 获取所有Banner列表
     * @return
     */
    List<Banner> selectAll();

    /**
     * 修改Banner记录
     * @param banner
     * @return
     */
    int updateByPrimaryKey(Banner banner);

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
    List<Banner> selectByCondition(BannerQueryObject qo);
}