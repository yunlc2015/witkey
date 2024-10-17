/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kfayun.app.witkey.Constants;
import com.kfayun.app.witkey.config.AppConfig;
import com.kfayun.app.witkey.model.User;
import com.kfayun.app.witkey.web.UserAuth;

/**
 * Controller基类
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
public class BaseController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	protected AppConfig config;

    protected Logger getLog() {
        return log;
    }

	protected int getInt(String str, int defaultVal) {
		if (str == null || str.length() == 0) {
			return defaultVal;
		}
		try {
			return Integer.parseInt(str);
		} catch (Exception ex) {
			log.warn(ex.getMessage(), ex);
		}
		return defaultVal;
	}

	/**
	 * 获取当前登录用户
	 * 
	 * @param request
	 * @return
	 */
	protected static User getCurrentUser(HttpServletRequest request) {
		UserAuth auth = (UserAuth)request.getAttribute(Constants.USER_AUTH);
		return auth != null ? auth.getUser() : null;
	}

	protected static String getHost(HttpServletRequest request) {
		
		String host = request.getServerName();
		if (80 != request.getServerPort()) {
			host += ":" + request.getServerPort();
		}
		
		return host;
	}

	/**
	 * 处理错误
	 * 
	 * @param request
	 * @param ex
	 */
	protected void handleError(HttpServletRequest request, Exception ex) {
		log.error(ex.getMessage(), ex);
	}
	
}
