/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Web处理工具类
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public class WebUtil {

	/**
	 * 读取Cookie
	 * 
	 * @param request
	 * @param name Cookie名称
	 * @return
	 */
    public static String readCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			return "";
		}
		
		int len = cookies.length;

		for (int i = 0; i < len; ++i) {
			Cookie cookie = cookies[i];
			if (name.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		
		return "";
    }
    
	/**
	 * 写入Cookie
	 * 
	 * @param response
	 * @param domain
	 * @param path
	 * @param name
	 * @param value
	 * @param expiry
	 */
    public static void writeCookie(HttpServletResponse response, String domain, String path, String name, String value, int expiry) {
	    Cookie cookie = new Cookie(name, value);
		if (!StrUtil.isEmpty(domain)) {
			cookie.setDomain(domain);
		}
	    cookie.setPath(StrUtil.isEmpty(path) ? "/" : path);
		if (expiry == 0) {
			expiry = -1;
		}
		cookie.setMaxAge(expiry);
		response.addCookie(cookie);
    }
    
	/**
	 * 删除Cookie
	 * 
	 * @param response
	 * @param domain
	 * @param path
	 * @param name
	 */
    public static void deleteCookie(HttpServletResponse response, String domain, String path, String name) {
		Cookie cookie = new Cookie(name, "");
		if (!StrUtil.isEmpty(domain)) {
			cookie.setDomain(domain);
		}
	    cookie.setPath(StrUtil.isEmpty(path) ? "/" : path);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
    }

	/**
	 * 获取真实访问IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getRealIP(HttpServletRequest request) {  
		String ip = request.getHeader("x-forwarded-for");
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
	    if (ip.contains(",")) {
	        return ip.split(",")[0];
	    } else {
	        return ip;
	    }
	}  
}
