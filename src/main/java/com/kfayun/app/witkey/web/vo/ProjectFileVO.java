/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.vo;

import java.util.UUID;

import com.kfayun.app.witkey.model.ProjectFile;

public class ProjectFileVO {
    
    private ProjectFile projectFile;
    private String key;

    public ProjectFileVO(ProjectFile projectFile) {
        this.projectFile = projectFile;
        key = UUID.randomUUID().toString();
    }

    public String getKey() {
        return key;
    }

    public String getUrl() {
        return projectFile.getUrl();
    }

    public String getDescription() {
        return projectFile.getDescription();
    }
}
