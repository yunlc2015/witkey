/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.model;

import java.util.Date;

/**
 * 操作日志
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public class ActionLog {
    
    private int id;
	private int userId;
	//类别
	private String category;
	//描述
	private String actionDescr;
	//操作名称
	private String actionName;
	//操作参数
	private String actionArgs;
	//执行时长
	private int duration;
	//
	private String methodName;
    
	//结果
	private String result;
	//日志时间
	private Date logTime;
	//日志IP
	private String logIp;
    
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
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getActionDescr() {
        return actionDescr;
    }
    public void setActionDescr(String actionDescr) {
        this.actionDescr = actionDescr;
    }
    public String getActionName() {
        return actionName;
    }
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
    public String getActionArgs() {
        return actionArgs;
    }
    public void setActionArgs(String actionArgs) {
        this.actionArgs = actionArgs;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public Date getLogTime() {
        return logTime;
    }
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
    public String getLogIp() {
        return logIp;
    }
    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }


}
