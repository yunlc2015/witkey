/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kfayun.app.witkey.*;
import com.kfayun.app.witkey.manage.aop.*;
import com.kfayun.app.witkey.condition.*;
import com.kfayun.app.witkey.util.*;
import com.kfayun.app.witkey.web.vo.ProjectVO;
import com.kfayun.app.witkey.manage.vo.TaskInfoVO;
import com.kfayun.app.witkey.service.*;
import com.kfayun.app.witkey.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 任务管理Controller
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
@RequestMapping("/manage/tsk")
@Controller
public class ManageTaskController extends ManageBaseController {
    
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private FinanceService financeService;
    @Autowired
    private CommonService commonService;

    /**
     * 任务列表页（市场标）
     * 
     * @param keyword
     * @param name
     * @param pageNo
     * @return
     */
    @ManageOperate(ManageAction.TSK_VIEW)
    @GetMapping("tasklist")
    public ModelAndView taskList(
        @RequestParam(value="keyword", required=false, defaultValue="")String keyword,
        @RequestParam(value="username", required=false, defaultValue="")String username,
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo) {

        ModelAndView mv = new ModelAndView();

        TaskCondition cond = new TaskCondition();
        cond.setService(1);
        cond.setAuditState(-1);
        cond.setKeyword(keyword);
        cond.setUsername(username);

        int pageSize = 10;
        PageList<TaskInfo> pglist = taskService.findTaskInfoList(cond, pageNo, pageSize);
        PagerInfo pager = new PagerInfo(pageNo, 10);
        pager.setTotalCount(pglist.getTotal());
        pager.setBaseUrl("tasklist" + cond.getQueryString() );

        List<TaskInfoVO> voList = pglist.getList().stream()
                .map((task)->{
                    TaskInfoVO vo = new TaskInfoVO(task);
                    vo.setProjectCount(projectService.getProjectCount2ByTask(task.getId()));
                    return vo;
                }).collect(Collectors.toList());
        mv.addObject("taskList", voList);
        mv.addObject("pager", pager);
        mv.addObject("cond", cond);

        mv.setViewName("manage/tsk/tasklist");
        return mv;
    }

    /**
     * 任务列表页（管家标）
     * 
     * @param keyword
     * @param name
     * @param pageNo
     * @return
     */
    @ManageOperate(ManageAction.TSK_VIEW)
    @GetMapping("task2list")
    public ModelAndView task2List(
        @RequestParam(value="keyword", required=false, defaultValue="")String keyword,
        @RequestParam(value="username", required=false, defaultValue="")String username,
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo) {

        ModelAndView mv = new ModelAndView();

        TaskCondition cond = new TaskCondition();
        cond.setService(2);
        cond.setAuditState(-1);
        cond.setKeyword(keyword);
        cond.setUsername(username);

        int pageSize = 10;
        PageList<TaskInfo> pglist = taskService.findTaskInfoList(cond, pageNo, pageSize);
        PagerInfo pager = new PagerInfo(pageNo, 10);
        pager.setTotalCount(pglist.getTotal());
        pager.setBaseUrl("task2list" + cond.getQueryString());
        
        List<TaskInfoVO> voList = pglist.getList().stream()
                .map((task)->{
                    TaskInfoVO vo = new TaskInfoVO(task);
                    vo.setProjectCount(projectService.getProjectCount2ByTask(task.getId()));
                    return vo;
                }).collect(Collectors.toList());
        mv.addObject("taskList", voList);
        mv.addObject("pager", pager);
        mv.addObject("cond", cond);

        mv.setViewName("manage/tsk/task2list");
        return mv;
    }

    /**
     * 任务审核页
     * 
     * @param id
     * @return
     */
    @ManageOperate(ManageAction.TSK_AUDIT)
    @GetMapping("taskaudit")
    public ModelAndView taskAudit(@RequestParam("id")int id) {

        ModelAndView mv = new ModelAndView();

        TaskInfo task = taskService.getTaskInfo(id);
        if (task ==  null) {
            throw new ForbiddenException("无效的参数。");
        }
        mv.addObject("task", task);

        mv.setViewName("manage/tsk/taskaudit");

        return mv;
    }

    /**
     * 任务审核提交
     * 
     * @param id
     * @param state
     * @param remark
     * @return
     */
    @ManageOperate(ManageAction.TSK_AUDIT)
    @PostMapping("taskaudit")
    @ResponseBody
    public JsonResult<Integer> taskAuditPost(
            @RequestParam(value="id")int id,
            @RequestParam(value="state")int state,
            @RequestParam(value="remark", required = false, defaultValue = "")String remark,
            HttpServletRequest request) {

        try {
            TaskInfo task = taskService.getTaskInfo(id);
            if (task ==  null) {
                return JsonResult.fail(-1, "无效的参数。");
            }
            task.setAuditState(state==1 ? AuditState.Approved : AuditState.Reject);
            task.setAuditMemo( remark );
        
            int n = taskService.updateTaskInfo(task);
            return JsonResult.ok(n);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * 任务付款页
     * 
     * @param id
     * @return
     */
    @ManageOperate(ManageAction.TSK_PAYMENT)
    @GetMapping("taskpayment")
    public ModelAndView taskPayment(@RequestParam("id")int id) {

        ModelAndView mv = new ModelAndView();

        TaskInfo task = taskService.getTaskInfo(id);
        if (task ==  null) {
            throw new ForbiddenException("无效的参数。");
        }
        mv.addObject("task", task);

        mv.setViewName("manage/tsk/taskpayment");

        return mv;
    }

    /**
     * 任务付款提交
     * 
     * @param id
     * @return
     */
    @ManageOperate(ManageAction.TSK_PAYMENT)
    @PostMapping("taskpayment")
    @ResponseBody
    public JsonResult<Integer> taskPaymentPost(
            @RequestParam(value="id")int id,
            @RequestParam("thirdno")String thirdNo,
            HttpServletRequest request) {
        try {
            TaskInfo task = taskService.getTaskInfo(id);
            if (task == null) {
                return JsonResult.fail(-1, "无效的参数。");
            }
            Trade trade = tradeService.getTradeByTask(id);
            if (trade == null) {
                return JsonResult.fail(-2, "无交易记录。");
            }
            trade.setPayType( 109 );
            trade.setThirdNo(thirdNo);
            trade.setPayState(1);

            int n = tradeService.updateTradeForTransferPay(trade);

            return JsonResult.ok(n);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * 任务退款页
     * 
     * @param id
     * @return
     */
    @ManageOperate(ManageAction.TSK_REFUND)
    @GetMapping("taskrefund")
    public ModelAndView taskRefund(@RequestParam("id")int id) {

        ModelAndView mv = new ModelAndView();

        TaskInfo task = taskService.getTaskInfo(id);
        if (task ==  null) {
            throw new ForbiddenException("无效的参数。");
        }
        mv.addObject("task", task);

        BigDecimal refundAmount = BigDecimal.ZERO;
        if (task.getService() == 2) {//管家标
            refundAmount = task.getDesignAmount().add(task.getFaxAmount());
        } else {
            refundAmount = task.getTotalAmount();
        }
        mv.addObject("refundAmount", refundAmount);

        mv.setViewName("manage/tsk/taskrefund");

        return mv;
    }

    /**
     * 任务退款提交
     * 
     * @param id 任务ID
     * @param refundamount 退款金额
     * @return
     */
    @ManageOperate(ManageAction.TSK_REFUND)
    @PostMapping("taskrefund")
    @ResponseBody
    public JsonResult<Integer> taskRefundPost(
            @RequestParam(value="id")int id,
            @RequestParam(value="refundamount")String refundAmount,
            HttpServletRequest request) {
        try {
            TaskInfo task = taskService.getTaskInfo(id);
            if (task ==  null) {
                return JsonResult.fail(-1, "无效的参数。");
            }

            BigDecimal refundAmount2 = new BigDecimal(refundAmount);
            int n = taskService.refundTask(task, refundAmount2);

            return JsonResult.ok(n);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * 任务结算页
     * 
     * @param id
     * @return
     */
    @ManageOperate(ManageAction.TSK_SETTLE)
    @GetMapping("tasksettle")
    public ModelAndView taskSettle(@RequestParam("id")int id) {

        ModelAndView mv = new ModelAndView();

        TaskInfo task = taskService.getTaskInfo(id);
        if (task ==  null) {
            throw new ForbiddenException("无效的参数。");
        }
        mv.addObject("task", task);

        List<Project> projectList = projectService.getProjectListByTask(id);
        int projectCount = projectList.size();
        List<ProjectVO> voList = projectList.stream()
                .map((project)->{
                    ProjectVO vo = new ProjectVO(project);
                    BigDecimal settleAmount = BigDecimal.ZERO;
                    if (task.getService() == 2) {
                        if (project.getSelected() == 1) {
                            vo.setSettleAmount(task.getDesignAmount().multiply(new BigDecimal(0.8)));  // 80%
                        } else {
                            if (projectCount > 1) {
                                vo.setSettleAmount(task.getDesignAmount().multiply(new BigDecimal(0.2))
                                    .divide(new BigDecimal(projectCount-1))); // 20% / projectCount -1;
                            }
                        }
                    } else {
                        vo.setSettleAmount(project.getSelected()==1?task.getDesignAmount():settleAmount);
                    }
                    return vo;
                }).collect(Collectors.toList());
        mv.addObject("projectList", voList);

        mv.setViewName("manage/tsk/tasksettle");

        return mv;
    }

    /**
     * 任务结算提交
     * 
     * @param id
     * @return
     */
    @ManageOperate(ManageAction.TSK_SETTLE)
    @PostMapping("tasksettle")
    @ResponseBody
    public JsonResult<Integer> taskSettlePost(
            @RequestParam Map<String, String> params,
            HttpServletRequest request) {

        try {
            int id = getInt(params.get("id"), 0);
            TaskInfo task = taskService.getTaskInfo(id);
            if (task ==  null) {
                return JsonResult.fail(-1, "无效的参数。");
            }

            Map<Integer, BigDecimal> settleMap = new HashMap<>();
            BigDecimal totalSettle = BigDecimal.ZERO;

            for (String name: params.keySet()) {
                if (name.startsWith("p-")) {
                    BigDecimal amount = new BigDecimal(params.get(name));
                    if (amount.compareTo(BigDecimal.ZERO) > 0) {
                        int userId = Integer.parseInt(name.substring(2));
                        settleMap.put(userId, amount);
                        totalSettle = totalSettle.add(amount);
                    }
                }
            }

            if (settleMap.size() == 0) {
                return JsonResult.fail(-1, "至少选择一个设计师。");
            }
            if (totalSettle.compareTo(task.getDesignAmount()) > 0) {
                return JsonResult.fail(-1, "结算金额不能大于设计费用。");
            }

            int n = taskService.settleTask(task, settleMap);
            return JsonResult.ok(n);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * 任务结算明细页
     * 
     * @param id
     * @return
     */
    @ManageOperate(ManageAction.TSK_VIEW)
    @GetMapping("tasksettledetail")
    public ModelAndView taskSettleDetail(@RequestParam("id")int id) {

        ModelAndView mv = new ModelAndView();

        TaskInfo task = taskService.getTaskInfo(id);
        if (task ==  null) {
            throw new ForbiddenException("无效的参数。");
        }
        mv.addObject("task", task);

        List<Project> projectList = projectService.getProjectListByTask(id);
        List<ProjectVO> voList = projectList.stream()
                .map((project)->{
                    ProjectVO vo = new ProjectVO(project);
                    Payment payment = financeService.getPaymentByTaskAndUser(id, project.getUserId());
                    vo.setSettleAmount(payment!=null ? payment.getAmount() : BigDecimal.ZERO);
                    return vo;
                }).collect(Collectors.toList());
        mv.addObject("projectList", voList);

        mv.setViewName("manage/tsk/tasksettledetail");

        return mv;
    }

     /**
     * 任务提案页
     * 
     * @param id
     * @return
     */
    @ManageOperate(ManageAction.TSK_VIEW)
    @GetMapping("taskprojectlist")
    public ModelAndView taskProjectList(
        @RequestParam(value="id")int id) {

        ModelAndView mv = new ModelAndView();
        TaskInfo task = taskService.getTaskInfo(id);
        if (task ==  null) {
            throw new ForbiddenException("无效的参数。");
        }
        mv.addObject("task", task);

        List<Project> projectList = projectService.getProjectListByTask(id);
        
        List<ProjectVO> voList = projectList.stream()
                .map((project)->{
                    ProjectVO vo = new ProjectVO(project);
                    vo.setFileList(projectService.getProjectFileList(project.getId()));
                    return vo;
                }).collect(Collectors.toList());
        mv.addObject("projectList", voList);

        mv.setViewName("manage/tsk/taskprojectlist");
        return mv;
    }

    /**
     * 项目列表页（任务提案）
     * 
     * @param name
     * @param pageNo
     * @return
     */
    @ManageOperate(ManageAction.TSK_PROJECT_VIEW)
    @GetMapping("projectlist")
    public ModelAndView projectList(
        @RequestParam(value="username", required=false, defaultValue="")String username,
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo) {

        ModelAndView mv = new ModelAndView();

        ProjectCondition cond = new ProjectCondition();
        cond.setState( -1 );
        cond.setUsername(username);

        int pageSize = 10;
        PageList<Project> pglist = projectService.findProjectList(cond, pageNo, pageSize);
        PagerInfo pager = new PagerInfo(pageNo, 10);
        pager.setTotalCount(pglist.getTotal());
        pager.setBaseUrl("projectlist" + cond.getQueryString());
        
        List<ProjectVO> voList = pglist.getList().stream()
                .map((project)->{
                    ProjectVO vo = new ProjectVO(project);
                    vo.setFileList(projectService.getProjectFileList(project.getId()));
                    vo.setTask(taskService.getTaskInfo(project.getTaskId()));
                    return vo;
                }).collect(Collectors.toList());
        mv.addObject("projectList", voList);
        mv.addObject("pager", pager);
        mv.addObject("cond", cond);

        mv.setViewName("manage/tsk/projectlist");
        return mv;
    }
    
    /**
     * 评价记录页
     * 
     * @param name
     * @param pageNo
     * @return
     */
    @ManageOperate(ManageAction.TSK_RATING_VIEW)
    @GetMapping("ratinglist")
    public ModelAndView ratingList(
            @RequestParam(value="name", required=false, defaultValue="")String name,
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo) {

        ModelAndView mv = new ModelAndView();

        RatingCondition cond = new RatingCondition();

        int pageSize = 15;
        PageList<Rating> pglist = commonService.findRatingList(cond, pageNo, pageSize);
        PagerInfo pager = new PagerInfo(pageNo, 10);
        pager.setTotalCount(pglist.getTotal());
        pager.setBaseUrl("ratinglist" + cond.getQueryString());

        mv.addObject("ratingList", pglist.getList());
        mv.addObject("pager", pager);
        mv.addObject("cond", cond);

        mv.setViewName("manage/tsk/ratinglist");
        return mv;
    }

}
