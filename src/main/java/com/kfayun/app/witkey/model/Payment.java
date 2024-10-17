/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 付款
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public class Payment {
    private int id;

    private int userId;

    private BigDecimal amount;

    private String summary;

    /**
     * 种类
     * 1.订单消费、2.充值、3.结算、4.提现
     */
    private int kind;

    private Date payTime;

    /**
     * 任务ID
     */
    private int taskId;

    /**
     * 交易编号
     */
    private String tradeNo; 

    /**
     * 项目结算操作人员
     */
    private String operator;

    private String username; //表关联

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
}

