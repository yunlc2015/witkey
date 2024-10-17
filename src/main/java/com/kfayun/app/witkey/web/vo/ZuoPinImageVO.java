/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.vo;

import java.util.UUID;

import com.kfayun.app.witkey.model.ZuoPinImage;

public class ZuoPinImageVO {
    
    private ZuoPinImage image;
    private String key;

    public ZuoPinImageVO(ZuoPinImage image) {
        this.image = image;
        key = UUID.randomUUID().toString();
    }

    public String getKey() {
        return key;
    }

    public String getUrl() {
        return image.getUrl();
    }

    public String getDescription() {
        return image.getDescription();
    }
    
}
