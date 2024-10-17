/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.model;

/**
 * 城市信息
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public class CityInfo {
    private int id;

    /**
     * 省份/直辖市
     */
    private String province;

    /**
     * 市/区
     */
    private String city;

    /**
     * 标识
     * 0:市/区 1:省份/直辖市
     */
    private int flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    
}

