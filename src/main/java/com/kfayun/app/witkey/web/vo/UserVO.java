/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.vo;

import java.util.List;
import java.math.BigDecimal;

import com.kfayun.app.witkey.model.User;
import com.kfayun.app.witkey.model.ZuoPinInfo;
import com.kfayun.app.witkey.util.StrUtil;

public class UserVO {

    private User user;
    private List<ZuoPinInfo> zuopinList;

    public UserVO(User user) {
        this.user = user;
    }

    public List<ZuoPinInfo> getZuopinList() {
        return zuopinList;
    }

    public void setZuopinList(List<ZuoPinInfo> list) {
        zuopinList = list;
    }

    public int getId() {
        return user.getId();
    }
    
    public String getNickname() {
        return !StrUtil.isEmpty(user.getNickname()) 
            ? user.getNickname() : StrUtil.maskMobile(user.getMobile());
    }
        
    public int getClazz() {
        return user.getClazz();
    }

    public int getType() {
        return user.getType();
    }
    
    public String getGender() { 
        return (user.getGender() == 1 
            ? "男" : (user.getGender() == 2 ? "女" : ""));
    }
                
    public String getMobile2() {
        return user.getMobile2(); 
    }

    public String getIm() {
        return user.getIm(); 
    }

    public String getWorkYear() {
        return user.getWorkYear(); 
    }
                
    public String getLocation() {
        return (user.getProvince() != null ? user.getProvince() : "") + " " + 
            (user.getCity() != null ? user.getCity() : "");
    }

    public String getEducation() {
        return user.getEducation();
    }

    public String getCompany() { 
        return user.getCompany();
    }

    public String getSpecial() {
        return user.getSpecial();
    }

    public String getIntro() {
        return user.getIntro();
    }

    public BigDecimal getBalance() { 
        return user.getBalance(); 
    }

    public String getAvatar() {
        return user.getAvatar();
    }

    public BigDecimal getStartingPrice() {
        return user.getStartingPrice();
    }
    
    public int getGoodCount() {
        return user.getGoodCount();
    }

    public int getDealCount() {
        return user.getDealCount();
    }

    public String getRealAuthStateEm() {
        if (user.getRealAuthState() != 1) {
            return "";
        }
                
        return user.getAbilityGrade() > 0 ? "V" : "R";  
    }

    public String getRealAuthStateText() {
        if (user.getRealAuthState() != 1) {
            return "";
        }

        if (user.getAbilityGrade() > 0) {
            return (user.getType() == 2) ? "官方实力认证设计机构" : "官方实力认证设计大师";
        } else {
            return (user.getType() == 2) ? "实名认证设计机构" : "实名认证设计师";
        }
        
    }

}
