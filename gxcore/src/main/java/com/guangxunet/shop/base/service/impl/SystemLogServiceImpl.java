package com.guangxunet.shop.base.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guangxunet.shop.base.domain.Systemlog;
import com.guangxunet.shop.base.mapper.SystemlogMapper;
import com.guangxunet.shop.base.service.ISystemLogService;

/**系统日志实现类
 * Created by King on 2016/9/16.
 */
@Service
public class SystemLogServiceImpl implements ISystemLogService {
    @Autowired
    private SystemlogMapper systemLogMapper;

    @Override
    public int save(Systemlog systemLog) {
        return systemLogMapper.insert(systemLog);
    }

    @Override
    public List<Systemlog> selectAll() {
        return systemLogMapper.selectAll();
    }

   /* @Override
    public PageResult selectByCondition(QueryObject qo) {
        int count = systemLogMapper.selectCountByCondition();
        if(count>0){
            java.util.List<SystemLog> result = systemLogMapper.selectByCondition(qo);
            return new PageResult(count,result);
        }
        return PageResult.EMPTY;
    }*/

}
