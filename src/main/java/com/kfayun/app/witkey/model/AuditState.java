/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.model;

import com.kfayun.app.witkey.BaseEnum;

/** 
 * 任务审核状态
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public enum AuditState implements BaseEnum {
    Waiting(0, "待审核"),

    Approved(1, "审核通过"),

    Reject(2, "审核不通过");

    private int constant;
    private String describe;

    private AuditState(int constant, String describe) {
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

    public static AuditState valueOf(int constant) {
        if (constant == Approved.constant) {
            return Approved;
        } else if (constant == Reject.constant) {
            return Reject;
        } else if (constant == Waiting.constant) {
            return Waiting;
        }
        return null;
    }

}
