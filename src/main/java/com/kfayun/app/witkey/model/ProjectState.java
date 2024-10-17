/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.model;

import com.kfayun.app.witkey.BaseEnum;

/**
 * 项目状态
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public enum ProjectState implements BaseEnum {

    WaitingSubmit(0, "等待提交"),

    Submitted(1, "已提交"),

    Cancelled(2, "已中止"),

    WaitingAccept(3, "等待接标");

    private int constant;
    private String describe;

    private ProjectState(int constant, String describe) {
        this.constant = constant;
        this.describe = describe;
    }

    @Override
    public int getConstant() {
        return constant;
    }

    public String toString() {
        return describe;
    }

    public static ProjectState valueOf(int constant) {
        if (constant == WaitingSubmit.constant) {
            return WaitingSubmit;
        } else if (constant == Submitted.constant) {
            return Submitted;
        } else if (constant == Cancelled.constant) {
            return Cancelled;
        } else if (constant == WaitingAccept.constant) {
            return WaitingAccept;
        }
        return null;
    }

}
