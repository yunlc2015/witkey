/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kfayun.app.witkey.config.AppConfig;
import com.kfayun.app.witkey.web.interceptor.FrontInterceptor;
import com.kfayun.app.witkey.web.interceptor.UserInterceptor;

/**
 * Web配置
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Autowired
	private AppConfig config;

	@Bean
	FrontInterceptor frontInterceptor() {
		return new FrontInterceptor();
	}

	@Bean
	UserInterceptor userInterceptor() {
		return new UserInterceptor();
	}

	/**
	 * 静态资源的处理
	 * 对于upload目录内除image文件夹外的内容，由相关Controller类单独处理，防非未授权的文件访问！
	 * 
	 * @param registry
	 */
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/*.txt").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/*.ico").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/lib/**").addResourceLocations("classpath:/static/lib/");
		registry.addResourceHandler("/upload/image/**")
			.addResourceLocations("file:" + (config.getUploadPath() + "/image/").replace("//", "/"));

	}

	/**
     * 拦截器的处理
	 * 
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(frontInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/lib/**")
			.excludePathPatterns("/lib2/**")
			.excludePathPatterns("/upload/**")
			.excludePathPatterns("/user/**")
			.excludePathPatterns("/error")
			.excludePathPatterns("/manage/**");

		// 用户拦截器，需要用户身份的请求，应置于此拦截器下。
		registry.addInterceptor(userInterceptor())
			.addPathPatterns("/user/**")
			.addPathPatterns("/task/publish", "/task/publish_*", "/task/proposal_*")
			.addPathPatterns("/task/pay")
			.addPathPatterns("/user/zuopin/upload", "/user/zuopin/uploadcover")
			.excludePathPatterns("/user/zuopin/**")
			.addPathPatterns("/user/file/upload")
			.excludePathPatterns("/user/file/**")
			.addPathPatterns("/user/proposal/upload")
			.excludePathPatterns("/user/proposal/**");

    }


}
