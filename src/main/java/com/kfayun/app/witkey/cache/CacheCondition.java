/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.cache;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.yaml.snakeyaml.Yaml;

import com.kfayun.app.witkey.util.StrUtil;

/**
 * Cache 条件注解类
 * 根据属性配置中cache.provider给定的值来生成Cache相关的Bean
 * 
 * @author billy (billy_zh@126.com)
 */
public abstract class CacheCondition implements Condition {

	private static final String CACHE_PROVIDER = "cache.provider";
	private static final String DEFAULT_CACHE = "ehcache";
	
	protected abstract String getName();
	
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		
		String provider = getCacheProvider(context.getEnvironment());
		
		if ( !StrUtil.isEmpty(provider) ) {
			return getName().equals( provider );
		}
		
		return DEFAULT_CACHE.equals(getName());  // default
	}
	
	@SuppressWarnings("unchecked")
    private String getCacheProvider(Environment env) {
		
		String provider = null;
		String profile = env.getProperty("spring.profiles.active"); // 优先从环境变量加载外部配置
		try {
            if (StrUtil.isEmpty(profile)) {
                Yaml yaml = new Yaml();
                Map<String, Object> dict = yaml.loadAs( new ClassPathResource("application.yml").getInputStream(), Map.class );
                Map<String, Object> springDict = (Map<String, Object>)dict.get("spring");
                Map<String, Object> profilesDict = (Map<String, Object>)springDict.get("profiles");
                profile = (String)profilesDict.get("active");

                if (dict.containsKey("app")) {
                    Map<String, Object> appDict = (Map<String, Object>)dict.get("app");
                    if (appDict.containsKey("cache")) {
                        Map<String, Object> cacheDict = (Map<String, Object>)appDict.get("cache");
                        if (cacheDict.containsKey("provider")) {
                            provider = (String)cacheDict.get("provider");
                        }
                    }
                }
            } 
            
            if (!StrUtil.isEmpty(profile)) {
                Yaml yaml = new Yaml();
                Map<String, Object> dict = yaml.loadAs( new ClassPathResource("application-" + profile + ".yml").getInputStream(), Map.class );
                if (dict.containsKey("app")) {
                    Map<String, Object> appDict = (Map<String, Object>)dict.get("app");
                    if (appDict.containsKey("cache")) {
                        Map<String, Object> cacheDict = (Map<String, Object>)appDict.get("cache");
                        if (cacheDict.containsKey("provider")) {
                            provider = (String)cacheDict.get("provider");
                        }
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

		return provider;
	}
	
	/**
	 * Redis 条件注解
	 */
	public final static class Redis extends CacheCondition {
		
		@Override
		public String getName() {
			return "redis";
		}
	}
	
	/**
	 * EhCache 条件注解
	 */
	public final static class EhCache extends CacheCondition {
		
		@Override
		public String getName() {
			return "ehcache";
		}
	};
	
	
}
