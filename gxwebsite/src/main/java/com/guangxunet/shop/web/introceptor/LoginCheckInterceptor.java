package com.guangxunet.shop.web.introceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.guangxunet.shop.base.domain.Logininfo;
import com.guangxunet.shop.base.util.LoggerUtil;
import com.guangxunet.shop.base.util.UserContext;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月7日 下午9:06:04 
* 类说明 Spring拦截器：登录拦截器
* 
* 
*/
public class LoginCheckInterceptor extends HandlerInterceptorAdapter{
	
	/**
	 * 该方法会在Controller方法执行前被调用
	 * return true:继续执行Controller中的方法  false:则不会执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		LoggerUtil.info("======登录检查拦截器=====");
		Logininfo logininfo = (Logininfo) request.getSession().getAttribute(UserContext.LOGININFO_IN_SESSION);
		//用户登录拦截
		if (logininfo == null) {
			response.sendRedirect("/login.jsp");
			return false;
		}
		//其他：用户权限管理等
		return true;
	}
}
