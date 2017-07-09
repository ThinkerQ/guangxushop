package com.guangxunet.shop.base.service;


import java.util.List;

import com.guangxunet.shop.base.domain.Systemlog;

/**
 * Created by Administrator on 2016/9/16. 
 */
public interface ISystemLogService {
    int save(Systemlog systemLog);

    List<Systemlog> selectAll();

//    PageResult selectByCondition(QueryObject qo);
}
