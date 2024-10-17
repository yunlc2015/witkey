/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.model;

import java.util.Date;
import java.math.BigDecimal;

/**
 * 用户
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public class User {
    private int id;

    private String mobile;

    private String passwd;

    private String salt;

    private int clazz;

    private String nickname;

    private String avatar;

    private int type; // 1个人，2公司

    private int gender;  // 1男, 2女

    private String mobile2;

    private int mobile2Pub;  // 公开

    private String im;

    private int cityId;

    private String workYear;  // 工作年限

    private String company;

    private String education;

    private String special;

    private String teamDescr; // 团队描述

    private String intro;

    private BigDecimal startingPrice;

    private BigDecimal balance; // 余额

    private int state;

    private Date regTime;

    private Date lastLogin;

    private String province;  // city table.
    private String city;  // city table.

    private int realAuthState;  // 实名认证状态

    private int abilityGrade; // 实力级别

    private int topNo; // 置顶序号

    private int goodCount; // 好评数

    private int dealCount; // 交易计数

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public int getMobile2Pub() {
        return mobile2Pub;
    }

    public void setMobile2Pub(int mobile2Pub) {
        this.mobile2Pub = mobile2Pub;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getTeamDescr() {
        return teamDescr;
    }

    public void setTeamDescr(String teamDescr) {
        this.teamDescr = teamDescr;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRealAuthState() {
        return realAuthState;
    }

    public void setRealAuthState(int realAuthState) {
        this.realAuthState = realAuthState;
    }

    public int getAbilityGrade() {
        return abilityGrade;
    }

    public void setAbilityGrade(int abilityGrade) {
        this.abilityGrade = abilityGrade;
    }

    public int getTopNo() {
        return topNo;
    }

    public void setTopNo(int topNo) {
        this.topNo = topNo;
    }

    public int getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }

    public int getDealCount() {
        return dealCount;
    }

    public void setDealCount(int dealCount) {
        this.dealCount = dealCount;
    }

    
}

