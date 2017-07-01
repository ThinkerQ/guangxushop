package com.guangxunet.shop.business.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guangxunet.shop.base.domain.Logininfo;
import com.guangxunet.shop.base.domain.Systemlog;
import com.guangxunet.shop.base.service.ISystemLogService;
import com.guangxunet.shop.base.util.LoggerUtil;
import com.guangxunet.shop.base.util.UserContext;

/**日志工具类
 * Created by Administrator on 2016/9/16.
 */
public class LogUtil {
    @Autowired
    private ISystemLogService systemLogService;

    public void writeLog(JoinPoint joinPoint) throws Exception {
    	LoggerUtil.info("==系统日志记录====begin=");
        if (joinPoint.getTarget() instanceof ISystemLogService){//如果切入的对象是当前的service实现类，就之久返回，避免死循环
            return;
        }
        Systemlog log = new Systemlog();
        log.setOptime(new Date());
        HttpServletRequest request = UserContext.get();
       //如果此处不加个判断,test测试的时候会出现死循环,因为测试环境使用到了service 会被aop捕捉到,就不会放行
        if(request==null){
            return;
        }
        Logininfo Logininfo = (Logininfo)request.getSession().getAttribute(UserContext.LOGININFO_IN_SESSION);
        log.setOpip(request.getRemoteAddr());//ip
                
//        log.setOpip("120.102.102");//ip
        
        Object target = joinPoint.getTarget();//当前请求的目标对象
//        String function = target.getClass().getName() + ":" + joinPoint.getSignature().getName();
        String function = "方法名";
        log.setFunction(function);
//        log.setOpuser(emp);
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);//排除掉空字符串
//        String paramValue = mapper.writeValueAsString(joinPoint.getArgs());//将对象转换成json字符串
        log.setParams("入参");
        log.setOpuserId(Logininfo.getId());
        try {
        	LoggerUtil.info("==系统日志记录====log="+log);
        	systemLogService.save(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
