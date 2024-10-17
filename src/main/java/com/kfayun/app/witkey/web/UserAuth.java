/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web;

import com.kfayun.app.witkey.model.User;
import com.kfayun.app.witkey.util.StrUtil;

/**
 * 用户认证类，在模板页面内使用
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public class UserAuth {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUserId() {
        return user!=null ? user.getId() : 0;
    }

    public boolean isLogon() {
        return user != null;
    }
    
    public String getNickname() {
        return user != null ? user.getNickname() : "";
    }
    
    public String getMobile() {
        return user != null ? StrUtil.maskMobile(user.getMobile()) : "";
    }
    
    public int getUserClazz() {
        return user != null ? user.getClazz() : 0;
    }
    
    public String getUserCenterUrl() {
        if (user.getClazz() == 1)
            return "/user/designer/index";
        else if (user.getClazz() == 2)
            return "/user/employer/index";

        return "#";
    }

    /**
     * 获取当前用户的认证状态
     */
    public boolean hasRole(String role) {
    	if (user == null)
    		return false;
    	
        return true;
    }

}
