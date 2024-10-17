/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 任务信息
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public class TaskInfo
{
    private int id;

    private int cateId;

    private int userId;

    private String requirement;

    private String designDetails;

    private String designContent;

    private int hopeDays;

    private int service; // 1:市场标 2:管家标

    private int taskMode; // 1:竟标 2:雇佣 3:雇佣

    private BigDecimal designBudget;

    private String otherService;

    private int invoice;

    private String invTitle;

    private String invAddress;

    private String invRecipients;

    private String invCellphone;

    private BigDecimal totalAmount;  // 总金额

    private BigDecimal designAmount;  // 设计费

    private BigDecimal serviceAmount;  // 服务费

    private BigDecimal faxAmount;  // 税费

    private TaskState taskState;  // 任务状态

    private AuditState auditState;

    private String auditMemo; // 审核备注

    private int limitDesigner; // 限制设计人数

    private Date chooseTime; // 选稿时间

    private Date addTime;

    private Date dueTime;
    
    private long topTicks; // 置顶序号

    private String username; // 表关联

    private String userAvatar; // 表关联

    private String userMobile;

    private String cateName; // 表关联

    private int acceptCount; // 抢标人数

    private List<TaskFile> fileList; // 保存时使用

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getDesignDetails() {
        return designDetails;
    }

    public void setDesignDetails(String designDetails) {
        this.designDetails = designDetails;
    }

    public String getDesignContent() {
        return designContent;
    }

    public void setDesignContent(String designContent) {
        this.designContent = designContent;
    }

    public int getHopeDays() {
        return hopeDays;
    }

    public void setHopeDays(int hopeDays) {
        this.hopeDays = hopeDays;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public int getTaskMode() {
        return taskMode;
    }

    public void setTaskMode(int taskMode) {
        this.taskMode = taskMode;
    }

    public BigDecimal getDesignBudget() {
        return designBudget;
    }

    public void setDesignBudget(BigDecimal designBudget) {
        this.designBudget = designBudget;
    }

    public String getOtherService() {
        return otherService;
    }

    public void setOtherService(String otherService) {
        this.otherService = otherService;
    }

    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    public String getInvTitle() {
        return invTitle;
    }

    public void setInvTitle(String invTitle) {
        this.invTitle = invTitle;
    }

    public String getInvAddress() {
        return invAddress;
    }

    public void setInvAddress(String invAddress) {
        this.invAddress = invAddress;
    }

    public String getInvRecipients() {
        return invRecipients;
    }

    public void setInvRecipients(String invRecipients) {
        this.invRecipients = invRecipients;
    }

    public String getInvCellphone() {
        return invCellphone;
    }

    public void setInvCellphone(String invCellphone) {
        this.invCellphone = invCellphone;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getDesignAmount() {
        return designAmount;
    }

    public void setDesignAmount(BigDecimal designAmount) {
        this.designAmount = designAmount;
    }

    public BigDecimal getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(BigDecimal serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public BigDecimal getFaxAmount() {
        return faxAmount;
    }

    public void setFaxAmount(BigDecimal faxAmount) {
        this.faxAmount = faxAmount;
    }

    public TaskState getTaskState() {
        return taskState;
    }

    public void setTaskState(TaskState taskState) {
        this.taskState = taskState;
    }

    public AuditState getAuditState() {
        return auditState;
    }

    public void setAuditState(AuditState auditState) {
        this.auditState = auditState;
    }

    public String getAuditMemo() {
        return auditMemo;
    }

    public void setAuditMemo(String auditMemo) {
        this.auditMemo = auditMemo;
    }

    public int getLimitDesigner() {
        return limitDesigner;
    }

    public void setLimitDesigner(int limitDesigner) {
        this.limitDesigner = limitDesigner;
    }

    public Date getChooseTime() {
        return chooseTime;
    }

    public void setChooseTime(Date chooseTime) {
        this.chooseTime = chooseTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public long getTopTicks() {
        return topTicks;
    }

    public void setTopTicks(long topTicks) {
        this.topTicks = topTicks;
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

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public int getAcceptCount() {
        return acceptCount;
    }

    public void setAcceptCount(int acceptCount) {
        this.acceptCount = acceptCount;
    }

    public List<TaskFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<TaskFile> fileList) {
        this.fileList = fileList;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }
    
    
}
