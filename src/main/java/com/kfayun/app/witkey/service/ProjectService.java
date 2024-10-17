/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service;

import java.util.List;
import java.util.Map;

import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.condition.ProjectCondition;
import com.kfayun.app.witkey.model.Project;
import com.kfayun.app.witkey.model.ProjectFile;

/**
 * 项目（任务提案）服务接口
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public interface ProjectService {
    
    /**
     * 获取项目信息
     * 
     * @param id
     * @return
     */
    public Project getProject(int id);

    /**
     * 获取项目信息
     * 
     * @param taskId
     * @param userId
     * @return
     */
    public Project getProjectByTaskAndUser(int taskId, int userId);

    /**
     * 保存项目信息
     * 
     * @param project
     */
    public void saveProject(Project project);

    /**
     * 提交项目信息
     * 
     * @param project
     * @return
     */
    public int submitProject(Project project);

    /**
     * 获取任务的项目列表
     * 
     * @param taskId
     * @return
     */
    public List<Project> getProjectListByTask(int taskId);

    /**
     * 查找项目列表
     * 
     * @param cond
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageList<Project> findProjectList(
        ProjectCondition cond, int pageNo, int pageSize);

    /**
     * 获取项目的文件列表
     * 
     * @param projectId
     * @return
     */
    public List<ProjectFile> getProjectFileList(int projectId);

    /**
     * 获取任务的项目数（分组）
     * 
     * @param taskId
     * @return
     */
    public Map<String, Long> getProjectCountByTask(int taskId);

    /**
     * 获取任务的项目数
     * 
     * @param taskId
     * @return
     */
    public int getProjectCount2ByTask(int taskId);

    /**
     * 获取选中提案的用户ID列表
     * 
     * @param taskId
     * @return
     */
    public List<Integer> getSelectedProposalsUserIdList(int taskId);

    /**
     * 提案选中处理
     * 
     * @param taskId
     * @param projectId
     * @return
     */
    public int chooseProposal(int taskId, int projectId);

    /**
     * 更新项目信息
     * 
     * @param project
     * @return
     */
    public int updateProject(Project project);

    /**
     * 更新项目信息
     * 
     * @param project
     * @return
     */
    public int updateProposal(Project project);

}
