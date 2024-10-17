/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ManageErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(ManageErrorHandler.class);

    public ModelAndView requestMethodNotSupportedException(
    		HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
		
		log.warn(ex.getMessage() + ", " + req.getRequestURL().toString());

		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMsg", "不支持的请求方式。");
		
		mv.setViewName("manage/err4xx");
		
		return mv;
    }

    public ModelAndView requestNotReadableHandler(
    		HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
		
		log.warn(ex.getMessage() + ", " + req.getRequestURL().toString());
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMsg", "缺少请求参数或参数类型不符。");
		
		mv.setViewName("manage/err4xx");
		
		return mv;
	}
	
    public ModelAndView requestNotFoundHandler(
    		HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
		
		log.warn(ex.getMessage() + ", " + req.getRequestURL().toString());

		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("manage/err4xx");

		return mv;
		
	}
    public ModelAndView requestForbiddenHandler(
        HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
        
        log.warn(ex.getMessage() + ", " + req.getRequestURL().toString());

        ModelAndView mv = new ModelAndView();
        
        mv.setViewName("manage/err4xx");

        return mv;
        
    }

	public ModelAndView requestErrorHandler(
			HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {

		log.error(ex.getMessage() + ", " + req.getRequestURL().toString(), ex);

		ModelAndView mv = new ModelAndView();
		
		mv.addObject("exception", ex.toString());
		
		mv.setViewName("manage/err5xx");

		return mv;
	}

}