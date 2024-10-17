/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.model;
import java.util.Date;

/**
 * 实力认证
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public class AbilityAuthent {
    private int id;

    private int userId;

    private String link;

    private String describe;

    private int authState; //1:已提交，2：认证通过 3:认证不通过

    private int authGrade; //1:认证设计师，2：官方设计师 3: 明星设计师

    private Date submitTime;

    private Date authTime;

    private String authMemo;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getAuthState() {
        return authState;
    }

    public void setAuthState(int authState) {
        this.authState = authState;
    }

    public int getAuthGrade() {
        return authGrade;
    }

    public void setAuthGrade(int authGrade) {
        this.authGrade = authGrade;
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

    public String getAuthOperator() {
        return authOperator;
    }

    public void setAuthOperator(String authOperator) {
        this.authOperator = authOperator;
    }
}
