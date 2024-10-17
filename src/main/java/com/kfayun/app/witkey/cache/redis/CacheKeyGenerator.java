/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.cache.redis;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.util.ClassUtils;

/**
 * 缓存Key生成器
 * 
 * @author billy (billy_zh@126.com)
 */
public class CacheKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(Object target, Method method, Object... params) {
		StringBuilder builder = new StringBuilder();
		builder.append("witkey-");
		builder.append(target.getClass().getSimpleName()).append(':')
			.append(method.getName()).append(':');
		
		if (params.length == 0) {
			return builder.toString();
		}
		
		for (int i=0; i<params.length; i++) {
			Object param = params[i];
			if (param == null) {
				continue;
			}
			
			if (ClassUtils.isPrimitiveOrWrapper(param.getClass()) || param instanceof String) {
				builder.append(param);
			} else if (ClassUtils.isPrimitiveArray(param.getClass())) {
				int length = Array.getLength(param);
				for (int j=0; j<length; j++) {
					builder.append(Array.get(param, j)).append(',');
				}
			} else {
				builder.append(param.toString());
			}
			
			builder.append('-');
		}
		
		return builder.toString();
	}
}

