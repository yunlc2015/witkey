/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.model;

import com.kfayun.app.witkey.BaseEnum;

/**
 * 任务状态
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public enum TaskState implements BaseEnum {

    Unpay(0, "待支付"),

    Paid(1, "已付款"),

    Selected(2, "已选稿"),

    Acceptance(3, "验收"),

    Finished(4, "已完成"),

    WaitingAccept(5, "等待雇佣确认"),

    Cancelled(6, "已取消");

    private int constant;
    private String describe;

    private TaskState(int constant, String describe) {
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

    public static TaskState valueOf(int constant) {
        if (constant == Unpay.constant) {
            return Unpay;
        } else if (constant == Paid.constant) {
            return Paid;
        } else if (constant == Selected.constant) {
            return Selected;
        } else if (constant == Acceptance.constant) {
            return Acceptance;
        } else if (constant == Finished.constant) {
            return Finished;
        } else if (constant == WaitingAccept.constant) {
            return WaitingAccept;
        } else if (constant == Cancelled.constant) {
            return Cancelled;
        }
        return null;
    }
}
