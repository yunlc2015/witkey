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

    public String getThumbailUrl() {
        
        String ext = taskFile.getType();
        if ("jpg".equalsIgnoreCase(ext) || 
                "gif".equalsIgnoreCase(ext) || 
                "png".equalsIgnoreCase(ext)) {
            return "/task/" + taskFile.getUrl();
        } 

        if ("zip".equalsIgnoreCase(ext) || 
                "rar".equalsIgnoreCase(ext)) {
            return "/lib/images/zip.jpg";
        }

        if ("doc".equalsIgnoreCase(ext) || 
            "docx".equalsIgnoreCase(ext)) {
            return "/lib/images/16-img8.jpg";
        }

        if ("pdf".equalsIgnoreCase(ext)) {
            return "/lib/images/16-img7.jpg";
        }

        if ("ppt".equalsIgnoreCase(ext) ||
            "pptx".equalsIgnoreCase(ext)) {
            return "/lib/images/16-img9.jpg";
        }

        else if ("xls".equalsIgnoreCase(ext) ||
            "xlsx".equalsIgnoreCase(ext))  {
            return "/lib/images/16-img10.jpg";
        }

        return "/lib/images/16-img6.jpg";
           
    }
}
