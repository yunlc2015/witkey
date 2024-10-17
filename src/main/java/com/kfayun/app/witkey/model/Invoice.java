/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.model;

import java.util.Date;
import java.math.BigDecimal;

/**
 * 发票
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public class Invoice {
    private int id;

    private int taskId;  // 任务Id
    
    private int userId;

    private String invoiceNo;  // 发票号

    private String invTitle;  // 发标抬头

    private BigDecimal invAmount; // 发票金额

    private String expAddress; // 快递地址

    private String cellphone;  // 联系电话

    private String recipients;  // 快递收件人

    private String expressNo;  // 快递单号

    private Date createTime; // 开票时间

    private int state; // 1:正常 2:作废

    private String operator; //经办人

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvTitle() {
        return invTitle;
    }

    public void setInvTitle(String invTitle) {
        this.invTitle = invTitle;
    }

    public BigDecimal getInvAmount() {
        return invAmount;
    }

    public void setInvAmount(BigDecimal invAmount) {
        this.invAmount = invAmount;
    }

    public String getExpAddress() {
        return expAddress;
    }

    public void setExpAddress(String expAddress) {
        this.expAddress = expAddress;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    
}

