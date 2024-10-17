/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.model;

import java.util.Date;
import java.math.BigDecimal;

/**
 * 提现
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public class Withdrawal {
    private int id;

    private int userId;

    private int paymentId;

    private BigDecimal amount;

    private int bankId;

    private String bankAccName;

    private String bankAccNo;

    private int state; //0.待处理,1.成功

    private Date requestTime;

    private Date settleTime; // 结算时间

    private String settleMemo; // 结算备注

    private String settleOperator; //结算人员

    private String username; //表关联

    private String bankName; //表关联

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

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankAccName() {
        return bankAccName;
    }

    public void setBankAccName(String bankAccName) {
        this.bankAccName = bankAccName;
    }

    public String getBankAccNo() {
        return bankAccNo;
    }

    public void setBankAccNo(String bankAccNo) {
        this.bankAccNo = bankAccNo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(Date settleTime) {
        this.settleTime = settleTime;
    }

    public String getSettleMemo() {
        return settleMemo;
    }

    public void setSettleMemo(String settleMemo) {
        this.settleMemo = settleMemo;
    }

    public String getSettleOperator() {
        return settleOperator;
    }

    public void setSettleOperator(String settleOperator) {
        this.settleOperator = settleOperator;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    
}
