/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage;

import com.kfayun.app.witkey.manage.aop.ManageAction;
import com.kfayun.app.witkey.model.Admin;

/**
 * 权限异常类
 * 
 * author: billy zhang
 */
public class PermissionException extends RuntimeException {

    private ManageAction action;
    private Admin admin;

    public PermissionException(Admin admin, ManageAction action) {
        super("没有访问权限：" + action.getDescr());
        this.action = action;
        this.admin = admin;
    }

    public ManageAction getAction() {
        return action;
    }

    public Admin getAdmin() {
        return admin;
    }

}