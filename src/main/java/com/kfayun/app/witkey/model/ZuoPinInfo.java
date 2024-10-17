/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.model;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

/**
 * 作品信息
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public class ZuoPinInfo {
    private int id;

    private int userId;

    private String title;

    private String link;

    private int cateId;

    private String description;

    private String cover;

    private BigDecimal price;

    private Date addTime;

    private int viewCount;

    private int likeCount; // 赞计数 表关联

    private int dealCount; // 用户交易计数 表关联

    private int topNo; // 置顶序号

    private List<ZuoPinImage> imageList; // 保存时使用

    private String username; // user表关联
    private String userAvatar; // user表关联
    private String province; // user表关联
    private String city; // user表关联
    private String userSpecial; // user用户擅长

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public List<ZuoPinImage> getImageList() {
        return imageList;
    }

    public void setImageList(List<ZuoPinImage> imageList) {
        this.imageList = imageList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
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

    public int getTopNo() {
        return topNo;
    }

    public void setTopNo(int topNo) {
        this.topNo = topNo;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDealCount() {
        return dealCount;
    }

    public void setDealCount(int dealCount) {
        this.dealCount = dealCount;
    }

    public String getUserSpecial() {
        return userSpecial;
    }

    public void setUserSpecial(String userSpecial) {
        this.userSpecial = userSpecial;
    }

    
}
