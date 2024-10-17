/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.dao;

import java.util.List;
import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.kfayun.app.witkey.condition.TaskCondition;
import com.kfayun.app.witkey.model.Project;
import com.kfayun.app.witkey.model.TaskFile;
import com.kfayun.app.witkey.model.TaskInfo;

/**
 * 任务相关实体Mapper
 * 
 * @author billy (billy_zh@126.com)
 */
public interface TaskMapper {
    
    public void insertTaskInfo(TaskInfo info);

    public void insertTaskFile(TaskFile file);

    public int updateTaskInfo(TaskInfo info);

    public int updateTaskTopTicks();

    public int deleteTaskFiles(
        @Param("taskId")int taskId);

    public int findTaskInfoCount(
        @Param("cond")TaskCondition cond);

    public List<TaskInfo> findTaskInfoList(
        @Param("cond")TaskCondition cond, 
        @Param("offset")int offset, 
        @Param("limit")int limit);

    public TaskInfo getTaskInfo(
        @Param("id")int id);

    public List<TaskFile> getTaskFileList(
        @Param("taskId")int taskId);

    public List<TaskInfo> getTaskInfoListByUser(
        @Param("userId")int userId, 
        @Param("limit")int limit);

    public int deleteTaskInfo(
        @Param("id")int taskId, 
        @Param("userId")int userId);

    public int getTaskApprovedCount();

    public BigDecimal getTaskApprovedAmount();


}
