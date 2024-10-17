/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.util.Config;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;

/**
 * 图形验证码配置
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Configuration
public class KaptchaConfig {
    
    @Bean
	public Producer defaultKaptcha() {
		DefaultKaptcha kaptcha = new DefaultKaptcha(); 
		  Properties properties = new Properties(); 
		  properties.setProperty("kaptcha.border", "yes"); 
		  properties.setProperty("kaptcha.border.color", "105,179,90"); 
		  properties.setProperty("kaptcha.textproducer.font.color", "blue"); 
		  properties.setProperty("kaptcha.image.width", "90"); 
		  properties.setProperty("kaptcha.image.height", "30"); 
		  properties.setProperty("kaptcha.textproducer.font.size", "24"); 
		  properties.setProperty("kaptcha.session.key", "code"); 
		  properties.setProperty("kaptcha.textproducer.char.length", "4"); 
		  //properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑"); 
		  Config config = new Config(properties); 
		  kaptcha.setConfig(config); 
		  return kaptcha; 
	}
}
