/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kfayun.app.witkey.spi.VerifySenderConfig;

/**
 * 应用配置
 * 绑定application.yml, application-{profile}.yml内的配置数据。
 * 
 * @author billy (billy_zh@126.com)
 */
@Configuration
public class AppConfig {

    @Value("${app.version}")
    private String appVersion;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${app.upload-path}")
    private String uploadPath;

    @Value("${app.tasktopticks-update-cron}")
    private String taskTopTicksUpdateCron;

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getTaskTopTicksUpdateCron() {
        return taskTopTicksUpdateCron;
    }

    @Bean
    @ConfigurationProperties(prefix = "app.spi.verify-sender")
    public Map<String, VerifySenderConfig> verifyCodeConfig() {
        Map<String, VerifySenderConfig> configMap = new HashMap<>();
        return configMap;
    }

}
