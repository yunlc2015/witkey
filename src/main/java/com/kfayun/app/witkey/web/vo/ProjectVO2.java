/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.vo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.kfayun.app.witkey.model.*;
import com.kfayun.app.witkey.util.DateUtil;

public class ProjectVO2 {

    private Project project;
    private List<ProjectFile> fileList;
    private Rating rating;
    private TaskInfo taskInfo;
    private List<Integer> selectedUserList;
    private User employer;

    public ProjectVO2(Project project) {
        this.project = project;
    }

    public List<ProjectFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<ProjectFile> fileList) {
        this.fileList = fileList;
    }

    public TaskInfo getTask() {
        return taskInfo;
    }

    public void setTask(TaskInfo taskInfo) {
        this.taskInfo = taskInfo;
    }

    public List<Integer> getSelectedUserList() {
        return selectedUserList;
    }

    public void setSelectedUserList(List<Integer> userList) {
        this.selectedUserList = userList;
    }

    public User getEmployer() {
        return employer;
    }

    public void setEmployer(User employer) {
        this.employer = employer;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public int getId() {
        return project.getId();
    }

    public int getTaskId() {
        return project.getTaskId();
    }

    public int getUserId() {
        return project.getUserId();
    }

    public Date getAcceptTime() {
        return project.getAcceptTime();
    }

    public ProjectState getState() {
        return project.getState();
    }

    public Date getSubmitTime() {
        return project.getSubmitTime();
    }

    public String getProposalDescribe() {
        return project.getProposalDescribe();
    }

    public int getProposalHidden() {
        return project.getProposalHidden();
    }

    public int getReaded() {
        return project.getReaded();
    }

    public int getSelected() {
        return project.getSelected();
    }

    public String getDesignerName() {
        return project.getDesignerName();
    }

    public String getDesignerAvatar() {
        return project.getDesignerAvatar();
    }

    public String getDesignerMobile() {
        return project.getDesignerMobile();
    }

    public String getDesignerSpecial() {
        return project.getDesignerSpecial();
    }

    public boolean isSubmitted() {
        return project.getState() == ProjectState.Submitted;
    }

    public boolean isWaitingAccept() {
        return project.getState() == ProjectState.WaitingAccept;
    }

    public boolean isCancelled() {
        return project.getState() == ProjectState.Cancelled;
    }

    public String getTaskDate() {
        return DateUtil.toDateString(taskInfo.getAddTime());
    }

    public String getStateExplain() {
        String explain = "";
        switch (project.getState()) {
            case WaitingSubmit:
                explain = "等待交稿...";
                break;
            case Submitted:
                explain = "交稿已隐藏";
                break;
            case Cancelled:
                explain = "已取消";
                break;
        }

        return explain;
    }

    public String getSubmitExplain() {
        String explain = "";
        switch (project.getState()) {
            case WaitingSubmit:
                explain = "未交稿";
                break;
            case Submitted:
                Duration ts = Duration.between(LocalDateTime.ofInstant(project.getSubmitTime().toInstant(), ZoneId.systemDefault()),
                    LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()) );
                if (ts.toDays() > 0) {
                    explain = ts.toDays() + "天前上传";
                } else if (ts.toHours() > 0) {
                    explain = ts.toHours() + "小时前上传";
                } else if (ts.toMinutes() > 0) {
                    explain = ts.toMinutes() + "分钟前上传";
                } else {
                    explain = ts.getSeconds() + "秒前上传";
                }
                break;
            case Cancelled:
                explain = "未交稿";
                break;
        }
        return explain;
    }

    public String getReadExplain() {
        return project.getReaded() == 1 ? "雇主已浏览" : "雇主未浏览";
    }

    /**
     * 获取项目（任务提案）详情的模板文件名
     * 用于设计师的任务列表页面。
     * 
     * @return
     */
    public String getViewName() {
        String view = "";

        if (project.getState() == ProjectState.WaitingAccept  // 等待接标
                || taskInfo.getTaskState() == TaskState.Unpay // 等待付款
                || taskInfo.getTaskState() == TaskState.Cancelled) { // 已取消
            view = "details5";
        } else {
            if (selectedUserList != null && !selectedUserList.isEmpty()) {
                if (selectedUserList.contains(project.getUserId())) { // 自己中标
                    view = "details4";
                } else { // 他人中标
                    if (project.getState() == ProjectState.WaitingSubmit) {
                        view = "details2";
                    }  else {
                        view = "details1";
                    }
                }
            } else {
                if (project.getState() == ProjectState.WaitingSubmit) { // 等待上传
                    view = "details2";
                } else { // 等待选定
                    view = "details3";
                }
            }
        }

        return view;
    }
}
