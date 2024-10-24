/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.condition;

import java.util.ArrayList;
import java.util.List;

import com.kfayun.app.witkey.util.StrUtil;

public class ZuopinCondition {
    public ZuopinCondition() {
        topSort = 1;
    }

    private int userId;

    private int cate1;

    private int cate2;

    private String province;

    private int cityId;

    private int userGrade;

    private String username;

    private String keyword;

    private int topSort;

    private int salesSort;

    private int priceSort;

    private int commentSort;

    private int idSort;

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

    public int getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(int userGrade) {
        this.userGrade = userGrade;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public int getIdSort() {
        return idSort;
    }

    public void setIdSort(int idSort) {
        this.idSort = idSort;
    }

    public String getQueryString() {
        List<String> list = new ArrayList<>();
        if (StrUtil.isEmpty(username)) {
            list.add("username=" + username);
        }
        if (StrUtil.isEmpty(keyword)) {
            list.add("keyword=" + keyword);
        }
        if (list.isEmpty()) {
            return "";
        }
        return "?" + StrUtil.join(list, "&");
    }

}
