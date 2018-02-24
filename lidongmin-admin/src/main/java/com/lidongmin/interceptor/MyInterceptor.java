package com.lidongmin.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {
		System.out.println("=========½øÈëÀ¹½ØÆ÷==========");
		Cookie[] myCookies = req.getCookies();
		for(Cookie c : myCookies){
			if("login".equals(c.getName())){
				if("true".equals(c.getValue())){
					return true;
				}
			}
		}
		return false;
	}

}
