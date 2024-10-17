/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.spi;

import java.util.Map;

/**
 * 校验码发送者配置
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public class VerifySenderConfig {
    private String senderClazz;
    private Map<String, String> config;
    
    public String getSenderClazz() {
        return senderClazz;
    }
    public void setSenderClazz(String senderClazz) {
        this.senderClazz = senderClazz;
    }
    public Map<String, String> getConfig() {
        return config;
    }
    public void setConfig(Map<String, String> config) {
        this.config = config;
    }

    
}
