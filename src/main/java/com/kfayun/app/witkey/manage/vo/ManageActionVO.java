/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage.vo;

import com.kfayun.app.witkey.manage.aop.ManageAction;

/**
 * ManageActionVO
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
public class ManageActionVO {

    private ManageAction action;
    private boolean grant;

    public ManageActionVO(ManageAction action) {
        this.action = action;
    }

    public void setGrant(boolean grant) {
        this.grant = grant;
    }

    public boolean isGrant() {
        return grant;
    }

    public String getCategory() {
        return action.getCategory();
    }
    
    public String getName() {
        return action.getName();
    }

    public String getDescr() {
        return action.getDescr();
    }

}
