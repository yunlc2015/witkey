/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage;

import java.util.Arrays;
import java.util.List;

import com.kfayun.app.witkey.model.Admin;
import com.kfayun.app.witkey.util.StrUtil;

/**
 * 管理员认证，在模板页面内使用
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
public class AdminAuth {

    private Admin admin;
    private List<String> permissionList;
    
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;

        if (admin != null && 
            !"admin".equals(admin.getName()) &&
            !StrUtil.isEmpty(admin.getPermissions())) {
            // 不是admin, 则设置权限列表。
            permissionList = Arrays.asList(admin.getPermissions().split(","));
        }
    }

    public String getName() {
        return admin != null ? admin.getName() : "Unknown";
    }

    public boolean isLogon() {
    	return admin != null;
    }

    public boolean hasPermission(String perm) {
    	if (admin == null) {
    		return false;
        }
        if ("admin".equals(admin.getName())) {
            return true;
        }
        if (permissionList == null || permissionList.isEmpty()) {
            return false;
        }
        
        return permissionList.contains(perm);
    }

    public boolean hasAnyPermission(String module) {
        if (admin == null) {
    		return false;
        }
        if ("admin".equals(admin.getName())) {
            return true;
        }
        if (permissionList == null || permissionList.isEmpty()) {
            return false;
        }
        
        return permissionList.stream().anyMatch((perm)->perm.startsWith(module));
    }

}
