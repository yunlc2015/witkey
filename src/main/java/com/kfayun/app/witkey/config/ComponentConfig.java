/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.config;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 组件配置
 * 
 * @author billy (billy_zh@126.com)
 */
@Configuration
public class ComponentConfig {
	
	private static Logger log = LoggerFactory.getLogger(ComponentConfig.class);
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		om.setDateFormat(fmt);

		return om;
	}

	@Bean
	public RestTemplate restTemplate() {
		
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(3000);
        requestFactory.setReadTimeout(3000);
        RestTemplate restTemplate = createRestTemplate(requestFactory);
        
		return restTemplate;
	}

	private RestTemplate createRestTemplate(ClientHttpRequestFactory requestFactory) {

		RestTemplate restTemplate = new RestTemplate();
		if (requestFactory != null) {
			restTemplate.setRequestFactory(requestFactory);
		}

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>(restTemplate.getInterceptors());

		interceptors.add(new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				log.info("RequestUrl: {}", request.getURI());
				return execution.execute(request, body);
			}
		});
		restTemplate.setInterceptors(interceptors);

		return restTemplate;
	}
}
