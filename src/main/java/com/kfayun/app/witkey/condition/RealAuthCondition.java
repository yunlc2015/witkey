/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.condition;

import java.util.ArrayList;
import java.util.List;

import com.kfayun.app.witkey.util.StrUtil;

public class RealAuthCondition {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getQueryString() {
        List<String> list = new ArrayList<>();
        if (StrUtil.isEmpty(username)) {
            list.add("username=" + username);
        }
        if (list.isEmpty()) {
            return "";
        }
        return "?" + StrUtil.json(list, "&");
    }
}
