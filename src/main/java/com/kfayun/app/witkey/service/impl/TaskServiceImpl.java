/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.condition.TaskCondition;
import com.kfayun.app.witkey.model.*;
import com.kfayun.app.witkey.service.*;
import com.kfayun.app.witkey.dao.TaskMapper;

/**
 * 任务服务接口实现
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Service
public class TaskServiceImpl implements TaskService {
    
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private FinanceService financeService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    @Override
    public String getTaskModeText(int model) {
        String text = "";
        switch (model)
        {
            case 1:
                text = "悬赏(一对多)";
                break;
            case 2:
                text = "投标(一对多)";
                break;
            case 3:
                text = "雇佣(一对一)";
                break;
        }

        return text;
    }

    
    /**
     * 接受任务
     * 
     * @param taskId 任务ID
     * @param userId 用户ID
     * @return 
     */
    @Transactional
    @Override
    public int acceptTask(int taskId, int userId) {
        Project proj = projectService.getProjectByTaskAndUser(taskId, userId);
        if (proj != null) {
            return 2;
        }

        // 抢标，创建一个项目用于上传提案。
        proj = new Project();
        proj.setTaskId(taskId);
        proj.setUserId(userId);
        proj.setState(ProjectState.WaitingSubmit);
        proj.setAcceptTime(new Date());
        projectService.saveProject(proj);
        return 1;
    }

    /**
     * 接受雇佣任务
     * 
     * @param taskId 任务ID
     * @param userId 用户ID
     * @return 
     */
    @Transactional
    @Override
    public int acceptEmployTask(int taskId, int userId) {
        
        Project proj = projectService.getProjectByTaskAndUser(taskId, userId);
        if (proj == null) {
            return -1;
        }

        
        if (proj.getState() != ProjectState.WaitingAccept) {
            return 2;
        }

        proj.setState(ProjectState.WaitingSubmit);
        proj.setSelected( 0);
        projectService.updateProject(proj);

        TaskInfo tinfo = taskMapper.getTaskInfo(taskId);
        tinfo.setTaskState( TaskState.Unpay );
        taskMapper.updateTaskInfo(tinfo);

        Trade trade = new Trade();
        trade.setTaskId(tinfo.getId());
        trade.setUserId(tinfo.getUserId());
        trade.setSubject( tinfo.getRequirement());
        trade.setAmount( tinfo.getTotalAmount());
        trade.setAddTime( new Date() );
        tradeService.saveTrade(trade);

        return 1;
    }

    /**
     * 拒绝任务雇佣
     * 
     * @param taskId 任务ID
     * @param userId 用户ID
     * @return 
     */
    @Transactional
    @Override
    public int rejectEmployTask(int taskId, int userId) {
        Project proj = projectService.getProjectByTaskAndUser(taskId, userId);
        if (proj == null) {
            return -1;
        }

        if (proj.getState() != ProjectState.WaitingAccept) {
            return 2;
        }

        proj.setState( ProjectState.Cancelled );
        projectService.updateProject( proj);

        TaskInfo tinfo = taskMapper.getTaskInfo( taskId);
        tinfo.setTaskState( TaskState.Cancelled);
        taskMapper.updateTaskInfo(tinfo);

        return 1;
    }

    @Transactional
    @Override
    public int saveTaskInfo(TaskInfo info) {
        taskMapper.insertTaskInfo( info);

        if (info.getFileList() != null) {
            for (TaskFile tf : info.getFileList()) {
                tf.setTaskId( info.getId() );
                taskMapper.insertTaskFile( tf);
            }
        }

        return 1;
    }

    @Override
    public int updateTaskInfo(TaskInfo info) {
        return taskMapper.updateTaskInfo( info);
    }

    @Override
    public int updateTaskInfoForTrade(Trade trade) {
        TaskInfo info = taskMapper.getTaskInfo(trade.getTaskId());

        if (trade.getPayFlag() == 1) {
            
            return changeTaskAmount(info, trade.getTotalAmount());

        } else {
            info.setTaskState(TaskState.Paid);
            if (info.getService() == 1) {
                // 市场标，自动批准。
                info.setAuditState(AuditState.Approved);
                info.setAuditMemo("auto");
            }
            return taskMapper.updateTaskInfo(info);
        }
    }

    @Override
    public int updateTaskTopTicks() {
        return taskMapper.updateTaskTopTicks();
    }

    @Transactional
    @Override
    public int updateTaskInfo2(TaskInfo info, Trade trade) {
        taskMapper.updateTaskInfo(info);
        taskMapper.deleteTaskFiles(info.getId());

        if (info.getFileList() != null) {
            for (TaskFile tf : info.getFileList()) {
                tf.setTaskId( info.getId() );
                taskMapper.insertTaskFile(tf);
            }
        }

        if (trade == null) {
            return 2;
        }

        if (trade.getId() > 0)
            tradeService.updateTrade(trade);
        else
            tradeService.saveTrade(trade);

        return 1;
        
    }

    @Transactional
    @Override
    public int updateTaskInfo3(TaskInfo info, Trade trade) {
        taskMapper.updateTaskInfo(info);
        taskMapper.deleteTaskFiles(info.getId());

        if (info.getFileList() != null) {
            for (TaskFile tf : info.getFileList()) {
                tf.setTaskId( info.getId() );
                taskMapper.insertTaskFile(tf);
            }
        }

        List<Project> projectList = projectService.getProjectListByTask(info.getId());
        for (Project proj : projectList) {
            if (proj.getState() == ProjectState.Cancelled) {
                proj.setState( ProjectState.WaitingAccept );
                projectService.updateProject(proj);
                break;
            }
        }

        if (trade == null) {
            return 2;
        }

        if (trade.getId() > 0)
            tradeService.updateTrade(trade);
        else
            tradeService.saveTrade(trade);

        return 1;
        
    }

    private int changeTaskAmount(TaskInfo tinfo, BigDecimal addAmount) {
        // 提高任务金额。
        if (tinfo.getService() == 1) {
            tinfo.setTotalAmount( tinfo.getTotalAmount().add(addAmount) );
            tinfo.setDesignAmount( tinfo.getTotalAmount() );
            return taskMapper.updateTaskInfo(tinfo);
        } else {
            // TODO:
        }
        return 0;
    }

    @Override
    public int changeTaskDate(TaskInfo tinfo, int addDays) {

		tinfo.setHopeDays( tinfo.getHopeDays()+addDays );
		
		Calendar cal = Calendar.getInstance();
		cal.setTime( tinfo.getAddTime() );
		cal.add(Calendar.DAY_OF_YEAR, tinfo.getHopeDays() );
		tinfo.setDueTime( cal.getTime() );

		if (tinfo.getLimitDesigner() > 0) {
			tinfo.setLimitDesigner( tinfo.getLimitDesigner()+3 );
		}
		tinfo.setTopTicks(new Date().getTime());
		return taskMapper.updateTaskInfo(tinfo);
    }

    @Override
    public PageList<TaskInfo> findTaskInfoList(
        TaskCondition cond, int pageNo, int pageSize) {
        if (cond.getPubTimeSort() == 0 && cond.getLeftTimeSort() == 0 && cond.getPriceSort() == 0) {
            cond.setPubTimeSort(1);
        }

        PageList<TaskInfo> pglist = new PageList<>(pageNo, pageSize);
        pglist.setTotal(taskMapper.findTaskInfoCount(cond));
        pglist.setList( taskMapper.findTaskInfoList( cond, pglist.getOffset(), pageSize) );
        return pglist;
    }

    @Override
    public List<TaskInfo> findTaskInfoList(TaskCondition cond, int num) {
        if (cond.getPubTimeSort() == 0 && cond.getLeftTimeSort() == 0 && cond.getPubTimeSort() == 0)
            cond.setPubTimeSort( 1 );

        return taskMapper.findTaskInfoList( cond, 0, num);
    }

    @Override
    public TaskInfo getTaskInfo(int taskId) {
        return taskMapper.getTaskInfo(taskId);
    }

    @Override
    public List<TaskFile> getTaskFileList(int taskId) {
        return taskMapper.getTaskFileList(taskId);
    }

    @Override
    public List<TaskInfo> getTaskInfoListByUser(int userId, int num){
        return taskMapper.getTaskInfoListByUser( userId, num);
    }

    @Transactional
    @Override
    public void deleteTaskInfo(int taskId, int userId) {
        
        int n = taskMapper.deleteTaskInfo(taskId, userId);
        if (n == 1) {
            taskMapper.deleteTaskFiles( taskId);
        }
    }

    @Transactional
    @Override
    public int submitEmployTask(TaskInfo taskInfo, int employUserId) {
        saveTaskInfo(taskInfo);

        Project proj = new Project();
        proj.setTaskId( taskInfo.getId() );
        proj.setUserId( employUserId );
        proj.setAcceptTime( new Date() );
        proj.setState( ProjectState.WaitingAccept ); // 等待接标.
        proj.setSelected(  1 );
        
        projectService.saveProject(proj);

        return 1;
    }

    @Override
    public int getTaskApprovedCount() {
        return taskMapper.getTaskApprovedCount();
    }

    @Override
    public BigDecimal getTaskApprovedAmount() {
        return taskMapper.getTaskApprovedAmount();
    }

    @Transactional
    @Override
    public int settleTask(TaskInfo ti, Map<Integer, BigDecimal> dict) {
        ti.setTaskState( TaskState.Finished);
        taskMapper.updateTaskInfo( ti);

        for (int uid : dict.keySet()) {
            Payment pay = new Payment();
            pay.setUserId(uid);
            pay.setAmount(dict.get(uid));
            pay.setSummary("任务结算");
            pay.setKind(3);  // 结算
            pay.setPayTime(new Date());
            pay.setTaskId(ti.getId());
            financeService.savePayment(pay);

            // update user balance.
            userService.updateUserBalance(uid, pay.getAmount());
        }

        return 1;
        
    }
}
