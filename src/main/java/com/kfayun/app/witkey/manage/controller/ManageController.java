/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.kfayun.app.witkey.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kfayun.app.witkey.JsonResult;
import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.PagerInfo;
import com.kfayun.app.witkey.util.WebUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/manage")
public class ManageController {
	
	@Autowired
	private AppConfig config;

	@GetMapping("index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("manage/index");
		return mv;
	}


	@GetMapping("about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("manage/about");
		mv.addObject("appVersion", config.getAppVersion());

		return mv;
	}


}
