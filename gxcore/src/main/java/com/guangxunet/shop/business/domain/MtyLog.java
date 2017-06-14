package com.guangxunet.shop.business.domain;
/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月13日 下午8:57:24 
* 类说明 日志处理类
*/
public class MtyLog {
	 //在类里面写方法，方法名诗可以任意的。此处我用标准的before和after来表示  
    public void before(){  
              System.out.println("被拦截方法调用之前调用此方法，输出此语句");  
    }  
    
    public void after(){  
                System.out.println("被拦截方法调用之后调用此方法，输出此语句");  
    }  
}
