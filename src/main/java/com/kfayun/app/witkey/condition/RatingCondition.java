/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.condition;

import java.util.ArrayList;
import java.util.List;

import com.kfayun.app.witkey.util.StrUtil;

public class RatingCondition {

    private int toUserId;
    private int type;
    
    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getQueryString() {
        List<String> list = new ArrayList<>();
        if (toUserId > 0) {
            list.add("touserid=" + toUserId);
        }
        if (type > 0) {
            list.add("type=" + type);
        }
        if (list.isEmpty()) {
            return "";
        }
        return "?" + StrUtil.join(list, "&");
    }
    
}
