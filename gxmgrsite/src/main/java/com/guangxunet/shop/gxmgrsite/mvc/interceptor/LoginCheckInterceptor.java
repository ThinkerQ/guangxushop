package com.guangxunet.shop.gxmgrsite.mvc.interceptor;

import com.guangxunet.shop.base.domain.Logininfo;
import com.guangxunet.shop.base.util.UserContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/** 
* @author 作者 chenmy
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
		System.out.println("======登录检查拦截器=====");
		Logininfo logininfo = (Logininfo) request.getSession().getAttribute(UserContext.LOGININFO_IN_SESSION);
		//用户登录拦截
		if (logininfo == null) {
			response.sendRedirect("/login.html");
			return false;
		}
		//静态资源handler对应的类型为DefaultServletHttpRequestHandler,此时不需要拦截;
		//handler对应的类型为HandlerMethod时,才需要拦截
		if (handler instanceof HandlerMethod) {
			//判断是否有该权限
			//先获取该请求的权限表达式
			HandlerMethod handlerMethod = (HandlerMethod) handler;//获取真实的类型
			String clzName = handlerMethod.getBean().getClass().getName();//获取权限定类型
			Method method = handlerMethod.getMethod();
			String methodName = method.getName();//获取方法名称
			String function = clzName + ":" + methodName;//拼接成权限表达式
			//判断是否有访问权限
			/*if (PermissionUtils.checkPermission(function)) {
				return true;
			} else {
				//没有权限,返回没有权限信息
				if (method.isAnnotationPresent(ResponseBody.class)) {
					response.sendRedirect("noPermission.json");
				} else {
					response.sendRedirect("noPermission.jsp");
				}
				return false;//拦截请求
			}*/
		}
		//其他：用户权限管理等
		return true;
	}
}
