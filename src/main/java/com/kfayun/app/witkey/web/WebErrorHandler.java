/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.kfayun.app.witkey.Constants;
import com.kfayun.app.witkey.ForbiddenException;
import com.kfayun.app.witkey.config.AppConfig;
import com.kfayun.app.witkey.manage.ManageErrorHandler;
import com.kfayun.app.witkey.model.Settings;
import com.kfayun.app.witkey.model.User;
import com.kfayun.app.witkey.service.SysService;
import com.kfayun.app.witkey.service.UserService;
import com.kfayun.app.witkey.util.WebUtil;
import com.kfayun.app.witkey.util.CryptoUtil;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * WEB全局异常处理类
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@ControllerAdvice
public class WebErrorHandler implements TemplateExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(WebErrorHandler.class);

	@Autowired
	private ManageErrorHandler managerErrorHandler;
	@Autowired
	private AppConfig config;
	@Autowired
	private SysService sysService;
	@Autowired
	private UserService userService;

	private void setData(ModelAndView model, HttpServletRequest request) {
		model.addObject("path", request.getRequestURI());
        model.addObject( "contextPath", config.getContextPath());
        model.addObject( "appVersion", config.getAppVersion());

        Settings settings = sysService.getSettings();
        model.addObject("settings", settings);

		// 获取登录用户
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

        UserAuth auth = new UserAuth();
        auth.setUser(loginUser);
        model.addObject(Constants.USER_AUTH, auth);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView requestMethodNotSupportedException(
    		HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
		if (req.getRequestURI().startsWith("/manage")) {
			return managerErrorHandler.requestMethodNotSupportedException(req, resp, ex);
		}

		log.warn(ex.getMessage() + ", " + req.getRequestURL().toString());

		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMsg", "不支持的请求方式。");
		
		setData(mv, req);
		mv.setViewName("err4xx");
		
		return mv;
    }

	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ModelAndView requestNotReadableHandler(
    		HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
		if (req.getRequestURI().startsWith("/manage")) {
			managerErrorHandler.requestNotReadableHandler(req, resp, ex);
		}
		log.warn(ex.getMessage() + ", " + req.getRequestURL().toString());
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMsg", "缺少请求参数或参数类型不符。");
		
		setData(mv, req);
		mv.setViewName("err4xx");
		
		return mv;
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView requestNotFoundHandler(
    		HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
		if (req.getRequestURI().startsWith("/manage")) {
			return managerErrorHandler.requestNotFoundHandler(req, resp, ex);
		}
		log.warn(ex.getMessage() + ", " + req.getRequestURL().toString());

		ModelAndView mv = new ModelAndView();
		
		setData(mv, req);
		mv.setViewName("err4xx");

		return mv;
		
	}

	@ExceptionHandler(ForbiddenException.class)
    public ModelAndView requestForbiddenHandler(
    		HttpServletRequest req, HttpServletResponse resp, ForbiddenException ex) throws IOException {
		if (req.getRequestURI().startsWith("/manage")) {
			return managerErrorHandler.requestForbiddenHandler(req, resp, ex);
		}
		log.warn(ex.getMessage() + ", " + req.getRequestURL().toString());

		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMsg", ex.getMessage());

		setData(mv, req);
		mv.setViewName("err4xx");

		return mv;
		
	}

	@ExceptionHandler(value = Exception.class)
	public ModelAndView requestErrorHandler(
			HttpServletRequest req, HttpServletResponse resp, HandlerMethod method, Exception ex) throws IOException {
		if (req.getRequestURI().startsWith("/manage")) {
			return managerErrorHandler.requestErrorHandler(req, resp, method, ex);
		}
		log.error(ex.getMessage() + ", " + req.getRequestURL().toString(), ex);

		ModelAndView mv = new ModelAndView();
		
		mv.addObject("exception", ex.toString());
		
		setData(mv, req);
		mv.setViewName("err5xx");

		return mv;
	}
	
	/**
	 * 处理Freemarker的模板异常
	 * 需在application.yml中配置。
	 */
	@Override
    public void handleTemplateException(TemplateException te, Environment env, Writer out) throws TemplateException {
   
	    log.warn("[出错了，请联系网站管理员]", te);
	        
    }
}
