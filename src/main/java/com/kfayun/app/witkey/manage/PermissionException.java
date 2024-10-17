/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage;

import com.kfayun.app.witkey.model.Admin;

/**
 * 帮助类，在FTL页面内使用
 * author: billy zhang
 */
public class PermissionException extends RuntimeException {

    private String permission;

    public PermissionException(String permission) {
        this.permission = permission;
    }

}