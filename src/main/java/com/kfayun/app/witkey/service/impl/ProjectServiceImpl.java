/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.condition.ProjectCondition;
import com.kfayun.app.witkey.model.Project;
import com.kfayun.app.witkey.model.ProjectFile;
import com.kfayun.app.witkey.model.ProjectState;
import com.kfayun.app.witkey.model.TaskState;
import com.kfayun.app.witkey.model.TaskInfo;

import com.kfayun.app.witkey.dao.ProjectMapper;
import com.kfayun.app.witkey.service.ProjectService;
import com.kfayun.app.witkey.service.TaskService;

/**
 * 项目服务接口实现
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private TaskService taskService;

    @Override
    public Project getProject(int id) {
        return projectMapper.getProject(id);
    }

    @Override
    public Project getProjectByTaskAndUser(int taskId, int userId) {
        return projectMapper.getProjectByTaskAndUser(taskId, userId);
    }

    @Override
    public void saveProject(Project project) {
        projectMapper.insertProject(project);
    }

    @Transactional
    @Override
    public int submitProject(Project project) {
        
        project.setSubmitTime(new Date());
        project.setState(ProjectState.Submitted);

        projectMapper.updateProject(project);

        if (project.getFileList() != null) {
            for (ProjectFile pf : project.getFileList()) {
                pf.setProjectId(project.getId());
                projectMapper.insertProjectFile(pf);
            }
        }

        return 1;
        
    }

    @Override
    public List<Project> getProjectListByTask(int taskId) {
        return projectMapper.getProjectListByTask(taskId);
    }

    @Override
    public PageList<Project> findProjectList(
            ProjectCondition cond, int pageNo, int pageSize) {
                PageList<Project> pglist = new PageList<>(pageNo, pageSize);
        pglist.setTotal(projectMapper.findProjectCount(cond));
        pglist.setList(projectMapper.findProjectList( cond, pglist.getOffset(), pageSize));
        return pglist;
    }

    @Override
    public List<ProjectFile> getProjectFileList(int projectId) {
        return projectMapper.getProjectFileList(projectId);
    }

    @Override
    public Map<String, Long> getProjectCountByTask(int taskId) {

        Map<String, Object> data = projectMapper.getProjectCountByTask(taskId);
        Map<String, Long> data2 = new HashMap<>();
        for (String name : data.keySet()) {
            data2.put(name, Long.parseLong(data.get(name).toString()));
        }
        return data2;
    }

    @Override
    public int getProjectCount2ByTask(int taskId) {
        return projectMapper.getProjectCount2ByTask(taskId);
    }

    @Override
    public List<Integer> getSelectedProposalsUserIdList(int taskId) {
        return projectMapper.getSelectedProposalsUserIdList(taskId);
    }

    @Transactional
    @Override
    public int chooseProposal(int taskId, int projectId) {
        List<Integer> list = projectMapper.getSelectedProposalsUserIdList(taskId);
        if (list.isEmpty()) { // 无人中标
            Project proj = projectMapper.getProject(projectId);
            if (proj != null) {
                
                proj.setSelected(1);
                projectMapper.updateProject(proj);

                TaskInfo ti = taskService.getTaskInfo(taskId);
                ti.setTaskState(TaskState.Selected);
                ti.setChooseTime(new Date()); // 选稿时间
                taskService.updateTaskInfo(ti);

                return 1;
            }
            return 0;
        } 
        
        return 2;
    }

    @Override
    public int updateProject(Project project) {
        return projectMapper.updateProject(project);
    }

    @Transactional
    @Override
    public int updateProposal(Project project) {
        
        project.setReaded( 0 );
        project.setSubmitTime( new Date() );
        projectMapper.updateProject( project);
        projectMapper.deleteProjectFiles( project.getId() );

        if (project.getFileList() != null) {
            for (ProjectFile pf : project.getFileList()) {
                pf.setProjectId( project.getId() );
                projectMapper.insertProjectFile(pf);
            }
        }

        return 1;
    }
}
