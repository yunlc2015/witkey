/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.vo;

import java.util.UUID;

import com.kfayun.app.witkey.model.TaskFile;

public class TaskFileVO {
    
    private TaskFile taskFile;
    private String key;

    public TaskFileVO(TaskFile taskFile) {
        this.taskFile = taskFile;
        key = UUID.randomUUID().toString();
    }

    public String getKey() {
        return key;
    }

    public String getUrl() {
        return taskFile.getUrl();
    }

}
