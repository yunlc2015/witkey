/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.model;

import java.util.Date;

/**
 * 实名信息
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public class RealAuthent
{
    private int id;

    private int userId;

    private String realname;

    private String idcard;

    private int sex;

    private int category;

    private String idcard1;

    private String idcard2;

    private int dueDate;

    private String dueDate2;

    private String industry;

    private String mobile;

    private String address;

    private String company1;

    private String company;

    private String licenseDueDate;

    private String registrationNo;

    private Date submitTime;

    private Date authTime;

    private String authMemo;

    private int authState;  // 1:提交, 2:通过, 3:不通过

    private String authOperator;

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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getIdcard1() {
        return idcard1;
    }

    public void setIdcard1(String idcard1) {
        this.idcard1 = idcard1;
    }

    public String getIdcard2() {
        return idcard2;
    }

    public void setIdcard2(String idcard2) {
        this.idcard2 = idcard2;
    }

    public int getDueDate() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueDate2() {
        return dueDate2;
    }

    public void setDueDate2(String dueDate2) {
        this.dueDate2 = dueDate2;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany1() {
        return company1;
    }

    public void setCompany1(String company1) {
        this.company1 = company1;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLicenseDueDate() {
        return licenseDueDate;
    }

    public void setLicenseDueDate(String licenseDueDate) {
        this.licenseDueDate = licenseDueDate;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public String getAuthMemo() {
        return authMemo;
    }

    public void setAuthMemo(String authMemo) {
        this.authMemo = authMemo;
    }

    public int getAuthState() {
        return authState;
    }

    public void setAuthState(int authState) {
        this.authState = authState;
    }

    public String getAuthOperator() {
        return authOperator;
    }

    public void setAuthOperator(String authOperator) {
        this.authOperator = authOperator;
    }

    
}

