/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kfayun.app.witkey.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kfayun.app.witkey.Constants;
import com.kfayun.app.witkey.config.AppConfig;
import com.kfayun.app.witkey.manage.AdminAuth;
import com.kfayun.app.witkey.util.WebUtil;

import java.util.Map;

/**
 * author: billy zhang
 */
public class ManageInterceptor implements HandlerInterceptor {

	@Autowired
	private AppConfig config;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        request.setAttribute("path", request.getRequestURI());
        request.setAttribute( "contextPath", config.getContextPath());
        
        User loginUser = null;
        String tokenStr = "";//CookieUtil.readCookie(request, Constants.OIDC_ACCESS_TOKEN);
        if (!StringUtils.isEmpty(tokenStr)) {

            
        }

        if (loginUser == null) {
            response.sendRedirect("/login");
            return false;
        }

        //request.setAttribute(Constants.USER_ATTRID, loginUser);

        AdminAuth helper = new AdminAuth();
        helper.setUser(loginUser);
        request.setAttribute(Constants.ADMIN_AUTH, helper);

        return true;

    }

}
