/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.spi.impl;

import java.util.Date;
import java.util.Map;

import com.kfayun.app.witkey.model.VerifyCode;
import com.kfayun.app.witkey.spi.VerifyCodeSender;

/**
 * 假的短信发送者
 * 用于开发阶段。
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public class FakeSmsSender implements VerifyCodeSender {
    
    @Override
    public void init(Map<String, String> config) {

    }

    @Override
    public int sendVerifyCode(VerifyCode vcode){
        vcode.setData("666888");
        vcode.setState(1);
        vcode.setSendTime(new Date());
        return 1;
    }

}
