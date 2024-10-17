/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.spi;

import java.util.Map;

import com.kfayun.app.witkey.model.VerifyCode;

/**
 * 校验码发送者
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public interface VerifyCodeSender {

    /**
     * 初始化
     * @param config 配置
     */
    public void init(Map<String, String> config);
    
    /**
     * 发送校验码
     * 
     * @param verifyCode 校验码对象
     * @return
     */
    public int sendVerifyCode(VerifyCode verifyCode);

}
