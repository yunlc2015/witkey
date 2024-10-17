/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.condition;

public class TaskCondition {
    public TaskCondition() {
        taskState = -1;
        auditState = -1;
        keyword = "";
    }

    private int userId;

    private int cate1;

    private int cate2;

    private int mode;

    private int service;

    private int cancelled;

    private String keyword;

    private String username;

    private int taskState;

    private int auditState;

    private int invoice;

    private int PubTimeSort;

    private int leftTimeSort;

    private int priceSort;

    private int topSort;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCate1() {
        return cate1;
    }

    public void setCate1(int cate1) {
        this.cate1 = cate1;
    }

    public int getCate2() {
        return cate2;
    }

    public void setCate2(int cate2) {
        this.cate2 = cate2;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public int getCancelled() {
        return cancelled;
    }

    public void setCancelled(int cancelled) {
        this.cancelled = cancelled;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTaskState() {
        return taskState;
    }

    public void setTaskState(int taskState) {
        this.taskState = taskState;
    }

    public int getAuditState() {
        return auditState;
    }

    public void setAuditState(int auditState) {
        this.auditState = auditState;
    }

    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    public int getPubTimeSort() {
        return PubTimeSort;
    }

    public void setPubTimeSort(int pubTimeSort) {
        PubTimeSort = pubTimeSort;
    }

    public int getLeftTimeSort() {
        return leftTimeSort;
    }

    public void setLeftTimeSort(int leftTimeSort) {
        this.leftTimeSort = leftTimeSort;
    }

    public int getPriceSort() {
        return priceSort;
    }

    public void setPriceSort(int priceSort) {
        this.priceSort = priceSort;
    }

    public int getTopSort() {
        return topSort;
    }

    public void setTopSort(int topSort) {
        this.topSort = topSort;
    }

    
}
