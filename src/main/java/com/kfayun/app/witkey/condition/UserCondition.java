/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.condition;

import java.util.ArrayList;
import java.util.List;

import com.kfayun.app.witkey.util.StrUtil;

public class UserCondition
{
    public UserCondition() {
        topSort = 1;
    }

    private int type;

    private String province;

    private int cityId;

    private int clazz;

    private String mobile;

    private int cate1;

    private int cate2;

    private int grade;

    private String username;

    private int topSort;

    private int salesSort;

    private int priceSort;

    private int commentSort;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTopSort() {
        return topSort;
    }

    public void setTopSort(int topSort) {
        this.topSort = topSort;
    }

    public int getSalesSort() {
        return salesSort;
    }

    public void setSalesSort(int salesSort) {
        this.salesSort = salesSort;
    }

    public int getPriceSort() {
        return priceSort;
    }

    public void setPriceSort(int priceSort) {
        this.priceSort = priceSort;
    }

    public int getCommentSort() {
        return commentSort;
    }

    public void setCommentSort(int commentSort) {
        this.commentSort = commentSort;
    }

    public String getQueryString() {
        List<String> list = new ArrayList<>();
        if (StrUtil.isEmpty(username)) {
            list.add("username=" + username);
        }
        if (StrUtil.isEmpty(mobile)) {
            list.add("mobile=" + mobile);
        }
        if (list.isEmpty()) {
            return "";
        }
        return "?" + StrUtil.join(list, "&");
    }

}
