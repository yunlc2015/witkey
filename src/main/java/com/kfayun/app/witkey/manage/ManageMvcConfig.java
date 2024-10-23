/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kfayun.app.witkey.config.AppConfig;
import com.kfayun.app.witkey.manage.interceptor.ManageInterceptor;

/**
 * Manage配置
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Configuration
public class ManageMvcConfig implements WebMvcConfigurer{

	@Autowired
	private AppConfig config;

	@Bean
	ManageInterceptor manageInterceptor() {
		return new ManageInterceptor();
	}
	
	/**
     * 拦截器的处理
	 * 
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
		
		// 管理拦截器
        registry.addInterceptor(manageInterceptor())
				.addPathPatterns("/manage/**")
				.excludePathPatterns("/manage/login", "/manage/about");

    }


}
