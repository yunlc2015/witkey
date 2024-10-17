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

import com.kfayun.app.witkey.model.Project;
import com.kfayun.app.witkey.model.ProjectFile;
import com.kfayun.app.witkey.model.ProjectState;
import com.kfayun.app.witkey.model.Rating;

public class ProjectVO {

    private Project project;
    private List<ProjectFile> fileList;
    private Rating rating;

    public ProjectVO(Project project) {
        this.project = project;
    }

    public List<ProjectFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<ProjectFile> fileList) {
        this.fileList = fileList;
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
     * 获取项目（任务提案）详情的模板页面名
     * 用于任务详情页。
     * 
     * @return
     */
    public String getViewName() {
        if (project.getState() == ProjectState.Submitted && 
            (project.getProposalHidden() == 0 || project.getSelected() == 1)) { //交稿且(不隐藏或已被选)
            
            return "proposal1";
            
        } else {
            return "proposal2";
            
        }
    }
}
