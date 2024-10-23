/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage;

import com.kfayun.app.witkey.model.Admin;

/**
 * 管理员认证，在模板页面内使用
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
public class AdminAuth {

    private Admin admin;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getName() {
        return admin != null ? admin.getName() : "Unknown";
    }

    public boolean isLogon() {
    	return admin != null;
    }

    public boolean hasPermission(String perm) {
    	if (admin == null)
    		return false;
    	
        return true;
    }

}
