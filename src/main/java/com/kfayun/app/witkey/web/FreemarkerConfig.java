/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;

import com.kfayun.app.witkey.web.control.BannerAd;
import com.kfayun.app.witkey.web.control.WebNav;
import com.kfayun.app.witkey.web.control.MobHeader;

/**
 * Freemarker配置
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Configuration
public class FreemarkerConfig {

	@Resource
	private freemarker.template.Configuration config;

	@Resource
	private BannerAd bannerAd;
	@Resource
	private WebNav webNav;
	@Resource
	private MobHeader mobHeader;

	 @PostConstruct
	 public void init() {

		 config.setDateTimeFormat("yyyy-MM-dd HH:mm");  
		 config.setDateFormat("yyyy-MM-dd");  
		 config.setTimeFormat("HH:mm:ss"); 
		 config.setNumberFormat("#");
		 config.setLogTemplateExceptions(false);

		 config.setSharedVariable("bannerAd", bannerAd);//自定义标签
		 config.setSharedVariable("webNav", webNav);//自定义标签
		 config.setSharedVariable("mobHeader", mobHeader);//自定义标签
	 }
}
