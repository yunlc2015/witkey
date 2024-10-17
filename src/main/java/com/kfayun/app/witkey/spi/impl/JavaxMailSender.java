/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.spi.impl;

import java.util.Map;

import com.kfayun.app.witkey.model.VerifyCode;
import com.kfayun.app.witkey.spi.VerifyCodeSender;

/**
 * 邮件发送者
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public class JavaxMailSender implements VerifyCodeSender {

    @Override
    public void init(Map<String, String> config) {

    }

    @Override
    public int sendVerifyCode(VerifyCode vcode){
        return 0;
    }
    
}
