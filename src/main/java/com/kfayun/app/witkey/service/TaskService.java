/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.condition.TaskCondition;
import com.kfayun.app.witkey.model.*;

/**
 * 任务服务接口
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public interface TaskService {


    public String getTaskModeText(int model);

    /**
     * 接受任务
     * 
     * @param taskId
     * @param userId
     * @return
     */
    public int acceptTask(int taskId, int userId);

    /**
     * 接受雇佣任务
     * 
     * @param taskId
     * @param userId
     * @return
     */
    public int acceptEmployTask(int taskId, int userId);

    /**
     * 拒绝雇佣任务
     * 
     * @param taskId
     * @param userId
     * @return
     */
    public int rejectEmployTask(int taskId, int userId);

    /**
     * 保存任务信息
     * 
     * @param taskInfo
     * @return
     */
    public int saveTaskInfo(TaskInfo taskInfo);

    /**
     * 更新任务信息
     * 
     * @param taskInfo
     * @return
     */
    public int updateTaskInfo(TaskInfo taskInfo);

    /**
     * 更新任务信息
     * 
     * @param trade
     * @return
     */
    public int updateTaskInfoForTrade(Trade trade);
    
    /**
     * 更新任务Ticks
     */
    public int updateTaskTopTicks();

    /**
     * 更新任务信息2
     * 
     * @param taskInfo
     * @param trade
     * @return
     */
    public int updateTaskInfo2(TaskInfo taskInfo, Trade trade);

    /**
     * 更新任务信息3
     * 
     * @param taskInfo
     * @param trade
     * @return
     */
    public int updateTaskInfo3(TaskInfo taskInfo, Trade trade);

    /**
     * 修改任务时间
     * 
     * @param taskInfo
     * @param addDays
     * @return
     */
    public int changeTaskDate(TaskInfo taskInfo, int addDays);

    /**
     * 查找任务列表（分页）
     * 
     * @param cond
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageList<TaskInfo> findTaskInfoList(TaskCondition cond, int pageNo, int pageSize);

    /**
     * 查找任务列表
     * 
     * @param cond
     * @param num
     * @return
     */
    public List<TaskInfo> findTaskInfoList(TaskCondition cond, int num);

    /**
     * 获取任务信息
     * 
     * @param taskId
     * @return
     */
    public TaskInfo getTaskInfo(int id);

    /**
     * 获取任务文件列表
     * 
     * @param taskId
     * @return
     */
    public List<TaskFile> getTaskFileList(int taskId);

    /**
     * 获取用户任务列表
     * 
     * @param userId
     * @param num
     * @return
     */
    public List<TaskInfo> getTaskInfoListByUser(int userId, int num);

    /**
     * 删除任务信息
     * 
     * @param taskId
     * @param userId
     */
    public void deleteTaskInfo(int taskId, int userId);

    /**
     * 提交雇佣任务
     * 
     * @param taskInfo
     * @param employUserId
     * @return
     */
    public int submitEmployTask(TaskInfo taskInfo, int employUserId);
    
    /**
     * 获取已审核通过任务总数
     * 
     * @return
     */
    public int getTaskApprovedCount();

    /**
     * 获取已审核通过任务总金额
     * 
     * @return
     */
    public BigDecimal getTaskApprovedAmount();

    /**
     * 任务结算
     * 
     * @param taskInfo
     * @param dict
     * @return
     */
    public int settleTask(TaskInfo taskInfo, Map<Integer, BigDecimal> dict);

    /**
     * 任务退款
     * 
     * @param taskInfo
     * @param refundAmount
     * @return
     */
    public int refundTask(TaskInfo taskInfo, BigDecimal refundAmount);
    
} 
