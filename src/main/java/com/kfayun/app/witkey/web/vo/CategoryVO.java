/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.vo;

import com.kfayun.app.witkey.model.Category;

public class CategoryVO {
    
    private Category cate;
    private boolean selected;

    public CategoryVO(Category cate, boolean selected) {
        this.cate = cate;
        this.selected = selected;
    }

    public int getId() {
        return cate.getId();
    }

    public String getName() {
        return cate.getName();
    }

    public boolean isSelected() {
        return selected;
    }
    
}
