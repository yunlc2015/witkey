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
 * 项目
 * 用户接受任务后提交的提案。
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public class Project {
    private int id;

    private int taskId;

    private int userId;

    private Date acceptTime; // 抢标时间

    private ProjectState state; // 状态

    private Date submitTime; // 提交时间

    private String proposalDescribe; // 提案描述

    private int proposalHidden;  // 提案隐藏

    private int readed; // 已读

    private int selected; // 雇主选中提案

    private String designerName; // 表关联
    private String designerAvatar; // 表关联
    private String designerMobile;
    private String designerSpecial; //

    // private String taskName; // 表关联
    // private String taskRequire; // 
    // private String taskDetails;
    // private int taskService;
    // private BigDecimal taskAmount; // 表关联
    // private Date taskPubTime; // 表关联
    // private TaskState taskState; // 表关联
    // private String employer; //表关联
    // private int taskUserId; //表关联

    private List<ProjectFile> fileList;


    
    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }



    public int getTaskId() {
        return taskId;
    }



    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }



    public int getUserId() {
        return userId;
    }



    public void setUserId(int userId) {
        this.userId = userId;
    }



    public Date getAcceptTime() {
        return acceptTime;
    }



    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }



    public ProjectState getState() {
        return state;
    }



    public void setState(ProjectState state) {
        this.state = state;
    }



    public Date getSubmitTime() {
        return submitTime;
    }



    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }



    public String getProposalDescribe() {
        return proposalDescribe;
    }



    public void setProposalDescribe(String proposalDescribe) {
        this.proposalDescribe = proposalDescribe;
    }



    public int getProposalHidden() {
        return proposalHidden;
    }



    public void setProposalHidden(int proposalHidden) {
        this.proposalHidden = proposalHidden;
    }



    public int getReaded() {
        return readed;
    }



    public void setReaded(int readed) {
        this.readed = readed;
    }



    public int getSelected() {
        return selected;
    }



    public void setSelected(int selected) {
        this.selected = selected;
    }



    public String getDesignerName() {
        return designerName;
    }



    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }



    public String getDesignerAvatar() {
        return designerAvatar;
    }



    public void setDesignerAvatar(String designerAvatar) {
        this.designerAvatar = designerAvatar;
    }

    public String getDesignerMobile() {
        return designerMobile;
    }



    public void setDesignerMobile(String designerMobile) {
        this.designerMobile = designerMobile;
    }


    public String getDesignerSpecial() {
        return designerSpecial;
    }



    public void setDesignerSpecial(String designerSpecial) {
        this.designerSpecial = designerSpecial;
    }

    // public String getTaskName() {
    //     return taskName;
    // }



    // public void setTaskName(String taskName) {
    //     this.taskName = taskName;
    // }



    // public String getTaskRequire() {
    //     return taskRequire;
    // }



    // public void setTaskRequire(String taskRequire) {
    //     this.taskRequire = taskRequire;
    // }



    // public String getTaskDetails() {
    //     return taskDetails;
    // }



    // public void setTaskDetails(String taskDetails) {
    //     this.taskDetails = taskDetails;
    // }



    // public int getTaskService() {
    //     return taskService;
    // }



    // public void setTaskService(int taskService) {
    //     this.taskService = taskService;
    // }



    // public BigDecimal getTaskAmount() {
    //     return taskAmount;
    // }



    // public void setTaskAmount(BigDecimal taskAmount) {
    //     this.taskAmount = taskAmount;
    // }



    // public Date getTaskPubTime() {
    //     return taskPubTime;
    // }



    // public void setTaskPubTime(Date taskPubTime) {
    //     this.taskPubTime = taskPubTime;
    // }



    // public TaskState getTaskState() {
    //     return taskState;
    // }



    // public void setTaskState(TaskState taskState) {
    //     this.taskState = taskState;
    // }



    // public String getEmployer() {
    //     return employer;
    // }



    // public void setEmployer(String employer) {
    //     this.employer = employer;
    // }



    // public int getTaskUserId() {
    //     return taskUserId;
    // }



    // public void setTaskUserId(int taskUserId) {
    //     this.taskUserId = taskUserId;
    // }



    public List<ProjectFile> getFileList() {
        return fileList;
    }



    public void setFileList(List<ProjectFile> fileList) {
        this.fileList = fileList;
    }



    public String getStateText() {
        
        String text = "";
        switch (state)
        {
            case WaitingSubmit:
                text = "等待交稿";
                break;
            case WaitingAccept:
                text = "等待接标";
                break;
            case Submitted:
                text = selected == 1 ? "已中标" : "已交稿";
                break;
                
        }
        return text;
    }
}


