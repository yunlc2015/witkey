/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.condition;

import java.util.ArrayList;
import java.util.List;

import com.kfayun.app.witkey.util.StrUtil;

public class ProjectCondition {
    private int userId;

    private int taskId;

    private int state;

    private int selected;

    private String username;
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

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
