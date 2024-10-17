/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.model;

/**
 * 作品附件图片
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public class ZuoPinImage {
    private int id;

    private int zuopinId;

    private String url;

    private String description;

    private int cover;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZuopinId() {
        return zuopinId;
    }

    public void setZuopinId(int zuopinId) {
        this.zuopinId = zuopinId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    
}