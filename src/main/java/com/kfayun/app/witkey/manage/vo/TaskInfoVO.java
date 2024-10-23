package com.kfayun.app.witkey.manage.vo;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.kfayun.app.witkey.model.AuditState;
import com.kfayun.app.witkey.model.TaskInfo;
import com.kfayun.app.witkey.model.TaskState;
import com.kfayun.app.witkey.util.DateUtil;

public class TaskInfoVO {
    
    private TaskInfo task;
    private int projectCount;

    public TaskInfoVO(TaskInfo taskInfo) {
        this.task = taskInfo;
    }

    public void setProjectCount(int count) {
        this.projectCount = count;
    }
    
    public long getProjectCount() {
        return projectCount;
    }

    public int getId() {
        return task.getId();
    }

    public int getCateId() {
        return task.getCateId();
    }

    public int getUserId() {
        return task.getUserId();
    }

    public String getRequirement() {
        return task.getRequirement();
    }

    public String getDesignDetails() {
        return task.getDesignDetails() != null ? task.getDesignDetails().replace("\n", "<br/>") : "";
    }

    public String getDesignContent() {
        return task.getDesignContent();
    }

    public int getHopeDays() {
        return task.getHopeDays();
    }

    public int getService() {
        return task.getService();
    }

    public int getTaskMode() {
        return task.getTaskMode();
    }

    public BigDecimal getDesignBudget() {
        return task.getDesignBudget();
    }

    public String getOtherService() {
        return task.getOtherService();
    }

    public int getInvoice() {
        return task.getInvoice();
    }

    public String getInvTitle() {
        return task.getInvTitle();
    }

    public String getInvAddress() {
        return task.getInvAddress();
    }

    public String getInvRecipients() {
        return task.getInvRecipients();
    }

    public String getInvCellphone() {
        return task.getInvCellphone();
    }

    public BigDecimal getTotalAmount() {
        return task.getTotalAmount();
    }

    public BigDecimal getDesignAmount() {
        return task.getDesignAmount();
    }

    public BigDecimal getServiceAmount() {
        return task.getServiceAmount();
    }

    public BigDecimal getFaxAmount() {
        return task.getFaxAmount();
    }

    public TaskState getTaskState() {
        return task.getTaskState();
    }

    public AuditState getAuditState() {
        return task.getAuditState();
    }

    public String getAuditMemo() {
        return task.getAuditMemo();
    }

    public int getLimitDesigner() {
        return task.getLimitDesigner();
    }

    public Date getChooseTime() {
        return task.getChooseTime();
    }

    public Date getAddTime() {
        return task.getAddTime();
    }

    public long getTopTicks() {
        return task.getTopTicks();
    }

    public String getUsername() {
        return task.getUsername();
    }

    public String getUserAvatar() {
        return task.getUserAvatar();
    }

    public String getUserMobile() {
        return task.getUserMobile();
    }

    public String getCateName() {
        return task.getCateName();
    }

    public int getAcceptCount() {
        return task.getAcceptCount();
    }

    public String getBeginDate() {
        return DateUtil.toDateString(task.getAddTime());
    }
    
    public Date getDueTime() {
        return task.getDueTime();
    }
    
    public String getServiceText() {
        if (task.getService() == 2) {
            return "管家标";
        }

        return "市场标";
    }

    public String getDueTimeText() {
        
        if (task.getDueTime().before(new Date())) {
            return "已截止";
        }

        Duration ts = Duration.between(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()),
                LocalDateTime.ofInstant(task.getDueTime().toInstant(), ZoneId.systemDefault()) );
        if (ts.toDays() > 0) {
            return ts.toDays() + "天后截止";
        } else if (ts.toHours() > 0) {
            return ts.toHours() + "小时后截止";
        } else if (ts.toMinutes() > 0) {
            return ts.toMinutes() + "分钟后截止";
        } 
          
        return ts.getSeconds() + "秒后截止";
    }

    public String getLimitDesignerText() {
        if (task.getLimitDesigner() > 0) {
            return task.getLimitDesigner() + " 人";
        }

        return "不限";
    }

    public boolean isNoPay() {
        return task.getTaskState() == TaskState.Unpay;
    }

    public boolean isNeedAudit() {
        return task.getTaskState()==TaskState.Paid && 
            task.getAuditState() == AuditState.Waiting;
    }

    public boolean isCanSettle() {
        return task.getTaskState() == TaskState.Acceptance;
    }

    public boolean isFinished() {
        return task.getTaskState() == TaskState.Finished;
    }

    public boolean isCanRefund() {
        return task.getTaskState() != TaskState.Acceptance &&
                task.getTaskState() != TaskState.Finished &&
                task.getTaskState() != TaskState.Cancelled &&
                task.getTaskState() != TaskState.Unpay;
    }

}
