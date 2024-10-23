/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kfayun.app.witkey.Constants;
import com.kfayun.app.witkey.config.AppConfig;
import com.kfayun.app.witkey.manage.AdminAuth;
import com.kfayun.app.witkey.util.CryptoUtil;
import com.kfayun.app.witkey.util.WebUtil;
import com.kfayun.app.witkey.model.*;
import com.kfayun.app.witkey.service.*;

import java.util.Map;

/**
 * 管理请求拦截器
 * 需在Web配置中进行设置。
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
public class ManageInterceptor implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(ManageInterceptor.class);

	@Autowired
	private AppConfig config;
    @Autowired
    private SysService sysService;
    @Autowired
    private AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        request.setAttribute("path", request.getRequestURI());
        request.setAttribute( "contextPath", config.getContextPath());
        request.setAttribute( "appVersion", config.getAppVersion());
        
        Settings settings = sysService.getSettings();
        request.setAttribute("settings", settings);

        Admin loginAdmin = null;
        String cookieStr = WebUtil.readCookie(request, settings.getCookiePrefix()+"_admin");
        if (!StringUtils.isEmpty(cookieStr)) {
            try {
                String idStr = CryptoUtil.decryptDES(settings.getCookieSecret(), cookieStr);
                int userId = Integer.parseInt(idStr);
                loginAdmin = adminService.getAdmin(userId);
            } catch (Exception ex) {
                log.warn(ex.getMessage(), ex);
            }
        }

        if (loginAdmin == null) {
            response.sendRedirect("/manage/login");
            return false;
        }

        String[] strs = request.getRequestURI().substring(1).split("/");
        String module = strs.length > 1 ? strs[1] : "main";
        request.setAttribute("module", module);

        AdminAuth auth = new AdminAuth();
        auth.setAdmin(loginAdmin);
        request.setAttribute(Constants.ADMIN_AUTH, auth);

        return true;

    }

}
