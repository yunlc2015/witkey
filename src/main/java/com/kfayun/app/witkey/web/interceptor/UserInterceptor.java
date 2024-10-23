/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kfayun.app.witkey.model.Settings;
import com.kfayun.app.witkey.model.User;
import com.kfayun.app.witkey.service.SysService;
import com.kfayun.app.witkey.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kfayun.app.witkey.Constants;
import com.kfayun.app.witkey.config.AppConfig;
import com.kfayun.app.witkey.dao.DaoContextHolder;
import com.kfayun.app.witkey.util.WebUtil;
import com.kfayun.app.witkey.util.CryptoUtil;
import com.kfayun.app.witkey.web.UserAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户请求拦截器
 * 需在Web配置中进行设置。
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
public class UserInterceptor implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(FrontInterceptor.class);
    
	@Autowired
	private AppConfig config;
    @Autowired
	private SysService sysService;
    @Autowired
    private UserService userService;

    /**
     * 请求前置处理。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        DaoContextHolder.setList(new ArrayList<Long>());

        request.setAttribute("path", request.getRequestURI());
        request.setAttribute( "contextPath", config.getContextPath());
        request.setAttribute( "appVersion", config.getAppVersion());

        Settings settings = sysService.getSettings();
        request.setAttribute("settings", settings);

        // 获取登录用户信息。
        User loginUser = null;
        String cookieStr = WebUtil.readCookie(request, settings.getCookiePrefix()+"_user");
        if (!StringUtils.isEmpty(cookieStr)) {
            try {
                String idStr = CryptoUtil.decryptDES(settings.getCookieSecret(), cookieStr);
                int userId = Integer.parseInt(idStr);
                loginUser = userService.getUser(userId);
            } catch (Exception ex) {
                log.warn(ex.getMessage(), ex);
            }
        }

        // 无用户信息，跳转到登录页。
        if (loginUser == null) {
            response.sendRedirect("/login");
            return false;
        }
        
        // 设置用户认证类，在模板页内使用。
        UserAuth auth = new UserAuth();
        auth.setUser(loginUser);
        request.setAttribute(Constants.USER_AUTH, auth);

        return true;
    }

    /**
     * 请求完成后处理（在页面渲染前）。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        
        // 获取数据库查询耗时记录，在模板页上输出。

        int sqlCount = 0;
        long sqlDuration = 0;
        List<Long> durationList = DaoContextHolder.getList();
        if (durationList != null && !durationList.isEmpty()) {
            sqlCount = durationList.size();
            sqlDuration = durationList.stream().mapToLong(Long::longValue).sum();
        }
        request.setAttribute("_sqlCount", sqlCount);
        request.setAttribute("_sqlDuration", sqlDuration);
    }

}
