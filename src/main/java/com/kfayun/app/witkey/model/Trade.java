/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.model;

import java.util.Date;
import java.math.BigDecimal;

/**
 * 交易
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public class Trade {
    private int id;

    private String tradeNo;

    private int taskId;

    private int userId;

    private String subject;

    private BigDecimal amount;

    private BigDecimal balance; // 余额

    private BigDecimal totalAmount; // 总额

    private Date addTime;

    private int payType; // 付款方式  101:支付宝 103:微信 109:银行转帐

    private int payState; // 付款状态  0:unpay, 1:paid

    private int payFlag; // 付款标记，0:首次，1:提高金额

    private String thirdNo; // 第三方交易号

    private String thirdStatus; // 第三方交易状态

    private String thirdBuyer; // 买家

    private Date payTime;

    private String notifyStatus;

    private Date notifyTime;

    private String username; // 表关联

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getPayState() {
        return payState;
    }

    public void setPayState(int payState) {
        this.payState = payState;
    }

    public int getPayFlag() {
        return payFlag;
    }

    public void setPayFlag(int payFlag) {
        this.payFlag = payFlag;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public String getThirdNo() {
        return thirdNo;
    }

    public void setThirdNo(String thirdNo) {
        this.thirdNo = thirdNo;
    }

    public String getThirdStatus() {
        return thirdStatus;
    }

    public void setThirdStatus(String thirdStatus) {
        this.thirdStatus = thirdStatus;
    }

    public String getThirdBuyer() {
        return thirdBuyer;
    }

    public void setThirdBuyer(String thirdBuyer) {
        this.thirdBuyer = thirdBuyer;
    }

    public String getNotifyStatus() {
        return notifyStatus;
    }

    public void setNotifyStatus(String notifyStatus) {
        this.notifyStatus = notifyStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPayTypeText() {
        String text = "";
        switch (payType) 
        {
            case 101:
                text = "支付宝";
                break;
            case 102:
                text = "微信支付";
                break;
            case 109:
                text = "现金支付";
                break;
        }
        return text;
    }

    public String getPayStateText() {
        
        String text = "";
        switch (payState)
        {
            case 0:
                text = "未支付";
                break;
            case 1:
                text = "已支付";
                break;
        }
        return text;
    }
}

