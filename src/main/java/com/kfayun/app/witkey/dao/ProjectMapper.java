/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.dao;

import java.util.List;
import java.util.Map;

import com.kfayun.app.witkey.condition.ProjectCondition;

import org.apache.ibatis.annotations.Param;

import com.kfayun.app.witkey.model.Project;
import com.kfayun.app.witkey.model.ProjectFile;

/**
 * 项目相关实体Mapper
 * 
 * @author billy (billy_zh@126.com)
 */
public interface ProjectMapper {

    public Project getProject(
        @Param("id")int id);

    public Project getProjectByTaskAndUser(
        @Param("taskId")int taskId, 
        @Param("userId")int userId);

    public void insertProject(Project project);

    public List<Project> getProjectListByTask(
        @Param("taskId")int taskId);

    public int findProjectCount(
        @Param("cond")ProjectCondition cond);

    public List<Project> findProjectList(
        @Param("cond")ProjectCondition cond, 
        @Param("offset")int offset, 
        @Param("limit")int limit);

    public List<ProjectFile> getProjectFileList(
        @Param("projectId")int projectId);

    public Map<String, Object> getProjectCountByTask(
        @Param("taskId")int taskId);

    public int getProjectCount2ByTask(
        @Param("taskId")int taskId);

    public List<Integer> getSelectedProposalsUserIdList(
        @Param("taskId")int taskId);

    public int updateProject(Project proj);

    public void insertProjectFile(ProjectFile file);
    
    public int deleteProjectFiles(
        @Param("projectId")int projectId);
        
}
