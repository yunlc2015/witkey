/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.vo;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import com.kfayun.app.witkey.model.AuditState;
import com.kfayun.app.witkey.model.TaskInfo;
import com.kfayun.app.witkey.model.TaskState;
import com.kfayun.app.witkey.util.DateUtil;

public class TaskInfoVO {
    
    private TaskInfo task;
    private long projectCount;
    private Map<String, Long> projectTotal;
    private String tradeNo;

    public TaskInfoVO(TaskInfo task) {
        this.task = task;
    }

    public void setProjectCount(int count) {
        this.projectCount = count;
    }
    
    public long getProjectCount() {
        return projectCount;
    }
    
    public Map<String, Long> getProjectTotal() {
        return projectTotal;
    }

    public void setProjectTotal(Map<String, Long> projectTotal) {
        this.projectTotal = projectTotal;
        this.projectCount = projectTotal.get("totalCount");
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
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

    public String getActionText() {
        if (task.getTaskState() == TaskState.Selected ||
            task.getTaskState() == TaskState.Acceptance ||
            task.getTaskState() == TaskState.Finished) {
            return "交易完成";
        }
            
        if (task.getDueTime().before(new Date())) {
            return "已截止";
        }

        if (task.getLimitDesigner() > 0) {
            if (task.getAcceptCount() >= task.getLimitDesigner()) {
                return "名额已满";
            }
        }  
                
        return "抢标";
    }

    /**
     * 获取任务操作的模板文件名
     * 用于雇主的任务详情页面。
     * 
     * @return
     */
    public String getActionBarName() {
        String actionBarName = "";
        if (task.getTaskMode() == 3 &&
                (task.getTaskState() == TaskState.WaitingAccept || task.getTaskState() == TaskState.Unpay || task.getTaskState() == TaskState.Cancelled)) {
            switch (task.getTaskState()) {
                case WaitingAccept:
                    actionBarName = "taskactionbar4";
                    break;
                case Unpay:
                    actionBarName = "taskactionbar5";
                    break;
                case Cancelled:
                    actionBarName = "taskactionbar6";
                    break;
            }
        } else {
            switch (task.getTaskState()) {
                case Unpay:
                    actionBarName = "taskactionbar1";
                    break;
                case Paid:
                    actionBarName = projectCount == 0 ? "taskactionbar2" : "taskactionbar3";
                    break;
                case Selected:
                case Finished:
                    actionBarName = "taskactionbar3_2";
                    break;
                default:
                    actionBarName = "taskactionbar3";
                    break;
            }
        }

        return actionBarName;
    }
}

