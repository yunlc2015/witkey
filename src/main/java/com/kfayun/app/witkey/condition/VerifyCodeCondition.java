/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.condition;

import java.util.ArrayList;
import java.util.List;

import com.kfayun.app.witkey.util.StrUtil;

public class VerifyCodeCondition {

    private String sendTo;

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getQueryString() {
        List<String> list = new ArrayList<>();
        if (StrUtil.isEmpty(sendTo)) {
            list.add("sendto=" + sendTo);
        }
        if (list.isEmpty()) {
            return "";
        }
        return "?" + StrUtil.json(list, "&");
    }

}
