package com.guangxunet.shop.business.mapper;

import com.guangxunet.shop.business.domain.Category;
import java.util.List;
import java.util.Map;

public interface CategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    Category selectByPrimaryKey(Long id);

    List<Category> selectAll();
    
    /**
     * 选择所有一级类目
     * @return
     */
    List<Category> selectOneAll();

    int updateByPrimaryKey(Category record);

    /**
	 * 根据父级id查询二级类目
	 * @param pid
	 * @return
	 */
	List<Map<String,Object>> selectByParentId(String pid);
}