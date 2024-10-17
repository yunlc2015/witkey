/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage;

import com.kfayun.app.witkey.model.User;

/**
 * 帮助类，在FTL页面内使用
 * author: billy zhang
 */
public class AdminAuth {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAdminName() {
        return user != null ? user.getNickname() : "Unknown";
    }

    public boolean isLogon() {
    	return user != null;
    }
    
    /**
     * 获取当前用户的认证状态
     */
    public boolean hasRole(String role) {
    	if (user == null)
    		return false;
    	
        return true;
    }

    public boolean hasPermission(String perm) {
    	if (user == null)
    		return false;
    	
        return true;
    }

}
