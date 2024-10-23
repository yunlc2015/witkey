/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kfayun.app.witkey.Constants;
import com.kfayun.app.witkey.PagerInfo;
import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.JsonResult;
import com.kfayun.app.witkey.ForbiddenException;
import com.kfayun.app.witkey.condition.ProjectCondition;
import com.kfayun.app.witkey.condition.TaskCondition;
import com.kfayun.app.witkey.config.AppConfig;
import com.kfayun.app.witkey.model.*;
import com.kfayun.app.witkey.service.*;
import com.kfayun.app.witkey.util.*;
import com.kfayun.app.witkey.web.TaskPublishContext;
import com.kfayun.app.witkey.web.vo.ProjectVO;
import com.kfayun.app.witkey.web.vo.TaskFileVO;
import com.kfayun.app.witkey.web.vo.TaskInfoVO;
import com.kfayun.app.witkey.web.vo.UserVO;
import com.kfayun.app.witkey.pay.PayConfig;
import com.kfayun.app.witkey.pay.WeixinPay;
import com.kfayun.app.witkey.pay.AliPay;
import com.kfayun.app.witkey.web.ProposalUploadContext;
import com.kfayun.app.witkey.web.aop.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 任务Controller
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Controller
@RequestMapping("/task")
public class TaskController extends BaseController {
	
	@Autowired
	private CmsService cmsService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private TradeService tradeService;
    @Autowired
	private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ZuopinService zuopinService;
    @Autowired
    private PayConfig payConfig;
    @Autowired
    private WeixinPay weixinPay;
    @Autowired
    private ObjectMapper jsonMapper;

    /**
     * 
     * @param params
     * @return
     */
	@GetMapping(value = {"/", "index"})
    public ModelAndView index(
			@RequestParam Map<String, String> params){
        ModelAndView mv = new ModelAndView();

        List<Banner> bannerList = cmsService.getBannerListByLocation("task");
        mv.addObject("bannerList", bannerList);

        TaskCondition cond = new TaskCondition();
        cond.setService( 1 );
        cond.setAuditState( AuditState.Approved.getConstant() );
        cond.setCancelled( 1 ); // 不显示取消的
        cond.setCate1( getInt(params.get("c1"), 0) );
        cond.setCate2( getInt(params.get("c2"), 0) );
        cond.setMode( getInt(params.get("tm"), 0) );
        cond.setKeyword( params.getOrDefault("wd", "") );
        cond.setPubTimeSort( getInt(params.get("pts"),0) );
        cond.setLeftTimeSort( getInt(params.get("lts"), 0) );
        cond.setPriceSort( getInt(params.get("prs"), 0) );
        if (cond.getPubTimeSort() == 0 && cond.getLeftTimeSort() == 0 && cond.getPriceSort() == 0) {
            cond.setPubTimeSort( 1 ); // 默认排序
        }
        cond.setTopSort( 1 ); // 置顶排序

        List<Category> cateList = commonService.getCategoryList(0);
        List<Category> cateList2 = Collections.emptyList();
        if (cond.getCate1() > 0) {
            cateList2 = commonService.getCategoryListByParent(cond.getCate1());
        }
        mv.addObject("cateList", cateList);
        mv.addObject("cateList2", cateList2);
                
        int page = getInt(params.get("page"), 1);
        int pageSize = 15;
        
        PageList<TaskInfo> pglist = taskService.findTaskInfoList(cond, page, pageSize);
        PagerInfo pager = new PagerInfo(page, pageSize);
        pager.setTotalCount(pglist.getTotal());
        pager.setBaseUrl("index2?keyword=" + cond.getKeyword());

        List<TaskInfoVO> taskList = pglist.getList().stream()
                .map(TaskInfoVO::new).collect(Collectors.toList());
        mv.addObject("taskList", taskList);
        mv.addObject("pager", pager);
        mv.addObject("cond", cond);

        mv.setViewName("task/index");
        return mv;
    }

    /**
     * 
     * @param params
     * @return
     */
    @GetMapping("index2")
    public ModelAndView index2(
			@RequestParam Map<String, String> params){

		ModelAndView mv = new ModelAndView();

		List<Banner> bannerList = cmsService.getBannerListByLocation("task");
		mv.addObject("bannerList", bannerList);

		TaskCondition cond = new TaskCondition();
		cond.setService( 2 );
		cond.setAuditState( AuditState.Approved.ordinal() );
		cond.setCancelled( 1 ); // 不显示取消的
		cond.setCate1( getInt(params.get("c1"), 0) );
		cond.setCate2( getInt(params.get("c2"), 0) );
		cond.setMode( getInt(params.get("tm"), 0) );
		cond.setKeyword( params.getOrDefault("wd", "") );
		cond.setPubTimeSort( getInt(params.get("pts"), 0) );
		cond.setLeftTimeSort( getInt(params.get("lts"), 0) );
		cond.setPriceSort( getInt(params.get("prs"), 0) );
		
		if (cond.getPubTimeSort() == 0 && cond.getLeftTimeSort() == 0 && cond.getPriceSort() == 0) {
			cond.setPubTimeSort( 1 ); // 默认排序
			//cond.TopSort = 1; // 置顶排序
		}

		List<Category> cateList = commonService.getCategoryList(0);
		List<Category> cateList2 = Collections.emptyList();
		if (cond.getCate1() > 0) {
			cateList2 = commonService.getCategoryListByParent(cond.getCate1());
		}
		mv.addObject("cateList", cateList);
		mv.addObject("cateList2", cateList2);

		PagerInfo pinfo = new PagerInfo(1, 20);

		PageList<TaskInfo> pgList = taskService.findTaskInfoList(
			cond, pinfo.getPageNo(), pinfo.getPageSize());
		pinfo.setTotalCount(pgList.getTotal());
        pinfo.setBaseUrl("inderx?keyword=" + cond.getKeyword());

		List<TaskInfoVO> taskList = pgList.getList().stream()
			.map(TaskInfoVO::new).collect(Collectors.toList());

		mv.addObject("taskList", taskList);
		mv.addObject("pager", pinfo);
		mv.addObject("cond", cond);

		mv.setViewName("task/index2");
		return mv;
    }

    /**
     * 
     * @param taskId
     * @param request
     * @return
     */
	@GetMapping("t{taskid:\\d+}")
	public ModelAndView detail(@PathVariable("taskid")int taskId,
            HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();

		List<Banner> bannerList = cmsService.getBannerListByLocation("task");
		mv.addObject("bannerList", bannerList);

		TaskInfo task = taskService.getTaskInfo(taskId);
        if (task == null) {
            throw new ForbiddenException("无效的参数。");
        }
        mv.addObject("task", new TaskInfoVO(task));

        User user = userService.getUser(task.getUserId());
        mv.addObject("user", new UserVO(user));

        String tradeNo = tradeService.getTradeNoByTask(task.getId());
        mv.addObject("tradeNo", tradeNo);

        Category cate = commonService.getCategory(task.getCateId());
        mv.addObject("cateName", cate.getName());

        List<TaskFile> fileList = taskService.getTaskFileList(task.getId());
        List<TaskFileVO> voList = fileList.stream()
            .map(TaskFileVO::new).collect(Collectors.toList());
        mv.addObject("fileList", voList);

        Map<String, Long> projectTotal = projectService.getProjectCountByTask(task.getId());
        mv.addObject("projectTotal", projectTotal);

        // 任务当前状态, 及当前登录用户能做什么操作
        String taskTodo = "";
        if (projectTotal.get("selectedCount") > 0) { // 有选定的
            taskTodo = "finished";  // 显示交易完成
        } else {
            User currUser = getCurrentUser(request);

            while (true) {
                if (currUser != null && currUser.getClazz() == 1) {// 有登录,判断是否能抢标
                
                    Project proj = projectService.getProjectByTaskAndUser(task.getId(), currUser.getId());
                    if (proj != null) { // 已抢标
                        if (proj.getState() == ProjectState.WaitingSubmit) {
                            taskTodo = "upload"; // 上传提案
                            break;
                        } else if (proj.getState() == ProjectState.Submitted) {
                            taskTodo = "waiting"; // 等待选稿
                            break;
                        }
                    
                    } else {
                        if (task.getDueTime().after(new Date()) && task.getTaskMode() != 3) { // 未截止 & 非雇佣模式
                            if (task.getLimitDesigner() == 0 || 
                                (task.getLimitDesigner() > 0 && projectService.getProjectCount2ByTask(task.getId()) < task.getLimitDesigner()))
                            {
                                // 抢标人数未满
                                taskTodo = "accept";  // 显示抢标
                                break;
                            }
                        }
                    }
                }

                if (projectTotal.get("submittedCount") > 0) { // 有交稿
                    taskTodo = "waiting";
                    break;
                }

                break;
            }
        }
        mv.addObject("taskTodo", taskTodo);

        // 控制任务进度条的显示
        List<String> taskStep = new ArrayList<>();
        taskStep.add("ok"); //step1
        if (taskTodo.equals("finished")) {
            if (task.getTaskState() == TaskState.Selected) {
                taskStep.add("ok"); //step2
                taskStep.add("ok"); //step3
                taskStep.add("ok"); //step4
                taskStep.add("on"); //step5
            } else {
                taskStep.add("ok"); //step2
                taskStep.add("ok"); //step3
                taskStep.add("ok"); //step4
                taskStep.add("ok"); //step5
                taskStep.add("ok"); //step6
                taskStep.add("");
            }

        } else if (taskTodo.equals("waiting")) {
            taskStep.add("ok"); //step2
            taskStep.add("on"); //step3

        } else if (taskTodo.equals("upload")) {
            taskStep.add("on"); //step2
        }
        
        mv.addObject("taskStep", taskStep);

        mv.setViewName("task/detail");
        return mv;
	}

    /**
     * 任务提案页
     * 在任务页面使用AJAX方式调用。
     * 
     * @param taskId　任务ID
     * @param type　提案类型
     * @param page　页码
     * @return
     */
    @GetMapping("proposals")
    public ModelAndView proposals(
            @RequestParam("taskid")int taskId,
            @RequestParam("type")int type,
            @RequestParam("page")int page) {

        ProjectCondition cond = new ProjectCondition();
        cond.setTaskId(taskId);

        if (type == 0) {
            cond.setState( -1 );
        }  else if (type == 1) {
            cond.setState( ProjectState.Submitted.getConstant() );
            cond.setSelected( 1 );
        } else if (type == 2) {
            cond.setState( ProjectState.Submitted.getConstant() );
        } else if (type == 3) {
            cond.setState( ProjectState.WaitingSubmit.getConstant() );
        }

        int pageSize = 10;

        PageList<Project> pglist = projectService.findProjectList(cond, page, pageSize);
        PagerInfo pager = new PagerInfo(page, pageSize);
        pager.setTotalCount(pglist.getTotal());

        List<ProjectVO> projectList = pglist.getList().stream()
            .map((project)->{
                ProjectVO vo = new ProjectVO(project);
                vo.setFileList(projectService.getProjectFileList(project.getId()));
                return vo;
            }).collect(Collectors.toList());
            
        ModelAndView mv = new ModelAndView();
        mv.addObject("projectList", projectList);
        mv.addObject("totalPage", pager.getTotalPages());

        return mv;
    }

    /**
     * 任务接受处理
     * 
     * @param taskId 任务ID
     * @return
     */
    @PostMapping("accept")
    @ResponseBody
    public JsonResult<Integer> accept(
            @RequestParam("taskid")int taskId,
            HttpServletRequest request) {
        try {
            User user = getCurrentUser(request);

            if (user.getRealAuthState() == 0) {
                return JsonResult.fail(-1, "实名认证后才能抢标。");
            }
            
            TaskInfo ti = taskService.getTaskInfo(taskId);
            if (ti.getService() == 2 && user.getAbilityGrade() == 0)
            {
                return JsonResult.fail(-2, "实力认证后才能抢管家标。");
            }
                    
            int ret = taskService.acceptTask(taskId, user.getId());
            return JsonResult.ok(ret);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * 任务上传页
     * 
     */
	@GetMapping("publish")
    public ModelAndView publish(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView();

		User user = getCurrentUser(request);
		if (user.getClazz() != 2) {
			//403
			throw new ForbiddenException("雇主角色才能发布任务。");
		}

        List<Category> cateList = commonService.getCategoryList(0);
		mv.addObject("cateList", cateList);

        mv.setViewName("task/publish");
        return mv;
    }

    /**
     * 任务提交
     * 
     * @param params 上传任务参数
     * @return
     */
    @UserOperate(UserAction.TASK_PUBLISH)
	@PostMapping("publish")
	@ResponseBody
    public JsonResult<String> publishPost(
                @RequestParam Map<String, String> params,
            HttpServletRequest request,
			HttpServletResponse response){

        try {
            TaskInfo task = new TaskInfo();

            task.setUserId( getCurrentUser(request).getId() );
            task.setCateId( getInt(params.get("cate") , 0) );
            task.setRequirement( params.get("requirement") );
            task.setDesignDetails( params.get("designDetails") );
            task.setDesignContent( params.get("designContent") );
            task.setHopeDays( getInt(params.get("hopeDays"), 0) );
            task.setService( getInt(params.get("service"), 0) == 0 ? 1 : 2 );
            task.setDesignBudget( new BigDecimal(params.get("designBudget")) );
            task.setOtherService( params.get("otherService") );
            task.setInvoice( getInt(params.get("invoice"), 0) );
            task.setInvTitle( params.get("invTitle") );
            task.setInvAddress( params.get("invAddress") );
            task.setInvRecipients( params.get("invRecipients") );
            task.setInvCellphone( params.get("invCellphone") );
            task.setTotalAmount( new BigDecimal(params.get("amount1")) );
            BigDecimal totalAmount = task.getTotalAmount();
            task.setServiceAmount( BigDecimal.ZERO );
            task.setFaxAmount( BigDecimal.ZERO );
            if (task.getInvoice() == 1) {// 需要发票
                totalAmount = totalAmount.subtract(new BigDecimal(8));
                task.setFaxAmount( totalAmount.multiply(new BigDecimal(0.06)) );
            }
            if (task.getService() == 2) { // 管家标
                task.setServiceAmount( totalAmount.multiply(new BigDecimal(0.15)) );
            }
            task.setDesignAmount( totalAmount.subtract(task.getServiceAmount()).subtract(task.getFaxAmount()) );
                
            task.setTaskState( TaskState.Unpay );
            task.setTaskMode( getInt(params.get("invest-mode"), 0) );
            task.setAuditState( AuditState.Waiting );
            task.setAddTime( new Date() );

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DAY_OF_YEAR, task.getHopeDays());
            task.setDueTime( cal.getTime() );

            String[] keys = request.getParameterValues("key");
            List<TaskFile> fileList = new ArrayList<TaskFile>();
            if (keys != null) {
                for (String k : keys) {
                    TaskFile tf = new TaskFile();
                    tf.setUrl( TaskPublishContext.getInstance(task.getUserId(), request).getFile(k) );
                    if (!StrUtil.isEmpty(tf.getUrl())) {
                        String ext = "";
                        if (tf.getUrl().lastIndexOf(".") > 0) {
                            ext = tf.getUrl().substring(tf.getUrl().lastIndexOf(".") + 1);
                        }
                        tf.setType(ext);
                        fileList.add(tf);
                    }
                }
            }
            task.setFileList( fileList );

            taskService.saveTaskInfo(task);

            Trade trade = new Trade();
            trade.setTaskId( task.getId() );
            trade.setUserId( task.getUserId() );
            trade.setSubject( task.getRequirement() );
            trade.setAmount( task.getTotalAmount() );
            trade.setTotalAmount( task.getTotalAmount() );
            trade.setPayState( 0 );
            trade.setAddTime( new Date() );

            tradeService.saveTrade(trade);

            //Response.Write("{\"result\":1,\"tradeNo\":\"" + trade.TradeNo + "\"}");
            return JsonResult.ok(trade.getTradeNo());

        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * 任务上传初始化
     * 
     * @return
     */
    @PostMapping("publish_init")
	@ResponseBody
    public String publishInit(HttpServletRequest request)  {
        try {
            int uid = getCurrentUser(request).getId();
            TaskPublishContext.getInstance(uid, request).init();
            return "{}";
        } catch (Exception ex) {
            handleError(request, ex);
            return "{}";
        }
    }

    /**
     * 任务上传文件删除
     * 
     * @param key 文件Key
     * @return
     */
    @PostMapping("publish_delfile")
	@ResponseBody
    public String publishDelfile(
            @RequestParam("key") String key,
            HttpServletRequest request) {
        try {
            int uid = getCurrentUser(request).getId();
            TaskPublishContext.getInstance(uid, request).delFile(key);
            return "{}";
        } catch (Exception ex) {
            handleError(request, ex);
            return "{}";
        }
    }

    /**
     * 支付确认页
     * 
     * @param tradeNo 交易编号
     * @return
     */
    @GetMapping("pay")
    public ModelAndView pay(@RequestParam("tradeno")String tradeNo,
            HttpServletRequest request,
            HttpServletResponse response) {
        
        ModelAndView mv = new ModelAndView();

        User user = getCurrentUser(request);
        if (StrUtil.isEmpty(tradeNo)) {
            throw new ForbiddenException("无效的参数。");
        } 

        Trade trade = tradeService.getTrade(tradeNo);
        if (trade == null) {
            throw new ForbiddenException("无效的参数。");
        } 
        
        if (trade.getUserId() != user.getId()) {
            throw new ForbiddenException("非法的参数。");
        }

        String tradeCode = tradeService.getTradeHash(user.getId(), tradeNo, trade.getTotalAmount());
            
        mv.addObject("user", user);
        mv.addObject("trade", trade);
        mv.addObject("tradeCode", tradeCode);

        mv.setViewName("task/pay");
        return mv;
    }

    /**
     * 支付失败页
     * 
     * @return
     */
    @GetMapping("payfail")
    public ModelAndView payFail() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("task/payfail");
        return mv;
    }

    /**
     * 交易支付状态检查
     * 
     * @param tradeNo 交易编号
     * @return
     */
    @GetMapping("paycheck")
    @ResponseBody
    public JsonResult<Integer> payCheck(@RequestParam("tradeno")String tradeNo,
            HttpServletRequest request,
            HttpServletResponse response) {
        try {
            User user = getCurrentUser(request);

            Trade trade = tradeService.getTrade(tradeNo);
            if (trade == null) {
                // 404
                return JsonResult.fail(-1, "trade not found.");
            } 
            
            if (trade.getUserId() != user.getId()) {
                // 403
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return JsonResult.fail(-2, "illegal access.");
            }

            return JsonResult.ok(trade.getPayState());
        } catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
    }

    /**
     * 支付结果等待页
     * 
     * @param tradeNo 交易编号
     * @return
     */
    @GetMapping("waitforpay")
    public ModelAndView waitForPay(@RequestParam("tradeno")String tradeNo) {

        ModelAndView mv = new ModelAndView();
        mv.addObject("tradeNo", tradeNo);

        mv.setViewName("task/waitforpay");
        return mv;
    }

    /**
     * 支付处理（支付宝）
     * 
     * @param params 支付相关参数
     * @return
     */
    @PostMapping("dopay")
    public ModelAndView doPay(@RequestParam Map<String, String> params,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        User user = getCurrentUser(request);

        String tradeNo = params.get("tradeno");
        int wap = getInt(params.get("wap"), 0);
        int useBalance = getInt(params.get("usebalance"), 0); // 使用余额
        BigDecimal balance = BigDecimal.ZERO;
        if (useBalance == 1 && !StrUtil.isEmpty(params.get("balance")) ) {
            balance = new BigDecimal(params.get("balance"));
        }
        String payType = params.get("paytype");
        String tradecode = params.get("tradecode");

        ModelAndView mv = new ModelAndView();
        mv.setViewName("task/dopay");
        
        Trade trade = tradeService.getTrade(tradeNo);
        if (trade == null || 
            trade.getUserId() != user.getId() ||
            !tradecode.equals(tradeService.getTradeHash(user.getId(), tradeNo, trade.getTotalAmount())) ) {
            mv.addObject("errorMsg", "无效的参数。");
            return mv;
        }
            
        if (useBalance == 1 && user.getBalance().compareTo(balance) < 0) { // 检查余额
            mv.addObject("errorMsg", "无效的参数。");
            return mv;
        }

        trade.setBalance( useBalance == 1 ? balance : BigDecimal.ZERO );
        BigDecimal payAmount = trade.getTotalAmount().subtract( trade.getBalance() );
        if (payAmount.compareTo(BigDecimal.ZERO) < 0) { // 付款金额必须大于0或等于0
            mv.addObject("errorMsg", "无效的参数。");
            return mv;
        }

        if (payAmount.compareTo(trade.getAmount()) != 0) { // 与原有付款金额不一致，则更新数据。
            trade.setAmount( payAmount );
            tradeService.updateTrade(trade);
        }

        String callbackUrl = String.format(Constants.ALIPAY_CALLBACK_URL, getHost(request));

        if (payAmount.compareTo(BigDecimal.ZERO) > 0)  { // 发起支付
            if (payType.equals("101")) {
                String payForm = getAlipayForm(trade, wap, callbackUrl);
                mv.addObject("payForm", payForm);
            }
        } else {
            // 余额支付
            int n = tradeService.updateTradeForBalancePay(trade);
            if (n == 1) {
                response.sendRedirect("/user/employer/tasklist");
            } else {
                mv.addObject("errorMsg", "支付未完成。");
            }
        }
        
        return mv;
    }

    /**
     * 付款页面（微信支付）
     * 
     * @param params 支付相关参数
     */
    @RequestMapping("dopay2")
    public ModelAndView doPay2(@RequestParam Map<String, String> params,
            HttpServletRequest request) throws IOException {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("task/dopay2");

        String tradeNo = params.get("tradeno");
        int payType  =getInt(params.get("paytype"), 0);
        if (StrUtil.isEmpty(tradeNo) || payType != 102) { //微信
            mv.addObject("trade_no", tradeNo);
            mv.addObject("message", "无效参数。");
            return mv;
        }

        mv.addObject("tradeNo", tradeNo);

        String tradecode = params.get("tradecode"); 
        int useBalance = getInt(params.get("usebalance"), 0); // 使用余额
        BigDecimal balance = BigDecimal.ZERO;
        if (useBalance == 1 && !StrUtil.isEmpty(params.get("balance"))) {
            balance = new BigDecimal(params.get("balance"));
        }

        User user = getCurrentUser(request);
        Trade trade = tradeService.getTrade(tradeNo);
        if (trade == null || 
            trade.getUserId() != user.getId() ||
            !tradecode.equals(tradeService.getTradeHash(user.getId(), tradeNo, trade.getTotalAmount())) ) {
                mv.addObject("message", "无效参数。");
                return mv;
        }

        if (useBalance == 1 && user.getBalance().compareTo(balance)==-1) { // 检查余额
            mv.addObject("message", "余额不足。");
            return mv;
        }

        trade.setBalance( useBalance == 1 ? balance : BigDecimal.ZERO);
        BigDecimal payAmount = trade.getTotalAmount().subtract( trade.getBalance() );
        if (payAmount.compareTo(BigDecimal.ZERO) == -1) { // 付款金额必须大于0或等于0
            mv.addObject("message", "无效参数。");
            return mv;
        }

        if (payAmount.compareTo(trade.getAmount()) != 0) { // 与原有付款金额不一致，则更新数据。
            trade.setAmount( payAmount );
            tradeService.updateTrade(trade);
        }

        Date tradeTime = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 10);

        BigDecimal amount = "prod".equals(config.getProfile()) ? trade.getAmount() : new BigDecimal("0.1");
        String subject = "prod".equals(config.getProfile()) ? trade.getSubject() : "[测试]" + trade.getSubject();

        Settings settings = (Settings)request.getAttribute("settings");

        HashMap<String, String> reqData = new HashMap<>();
        reqData.put("body", subject);//商品描述
        reqData.put("attach", "nodata");//附加数据
        reqData.put("out_trade_no", trade.getTradeNo());//随机字符串
        reqData.put("total_fee", (int)(amount.floatValue() * 100) + "");//总金额
        reqData.put("time_start", DateUtil.toStringNoSep(tradeTime) );//交易起始时间
        reqData.put("time_expire", DateUtil.toStringNoSep(cal.getTime()) );//交易结束时间
        reqData.put("goods_tag", "nodata");//商品标记
        reqData.put("trade_type", "NATIVE");//交易类型
        reqData.put("product_id", "bean"); //商品ID
        reqData.put("notify_url", settings.getWxpayNotifyUrl()); //

        JsonResult<Map<String, String>> payRet = weixinPay.getPayOrderData(reqData);

        if (payRet.getErrCode() == 0) {
            getLog().info(jsonMapper.writeValueAsString(payRet.getData()));

            Map<String, String> respData = payRet.getData();
            String result_code = respData.get("result_code");
            if ("SUCCESS".equalsIgnoreCase(result_code)) {

                mv.addObject("code_url", respData.get("code_url") ); //获得统一下单接口返回的二维码链接
                mv.addObject("add_time", trade.getAddTime() );
                mv.addObject("amount", trade.getAmount() );
                
            } else if ("FAIL".equalsIgnoreCase(result_code)) {
                getLog().warn(respData.get("err_code_des"));

               mv.addObject("message", respData.get("err_code_des"));
                    
            }
        } else {
            getLog().warn(payRet.getErrMsg());

            mv.addObject("message", payRet.getErrMsg());

        }

        return mv;
           
    }

    /**
     * 支付宝支付表单获取
     * 
     * @param trade 交易
     * @param mobile 是否移动端
     * @param callbackUrl 回调URL
     * @return
     * @throws IOException
     */
    private String getAlipayForm(Trade trade, int mobile, String callbackUrl) throws IOException {

        //扩展功能参数——默认支付方式
        // String pay_mode = request.getParameter("pay_bank");
        String paymethod = "";        //默认支付方式，四个值可选：bankPay(网银); cartoon(卡通); directPay(余额); CASH(网点支付)
        String defaultbank = "";    //默认网银代号，代号列表见http://club.alipay.com/read.php?tid=8681379
        /*if(pay_mode.equals("directPay")){
            paymethod = "directPay";
        }
        else{
            paymethod = "bankPay";
            defaultbank = pay_mode;
        }*/

        //扩展功能参数——防钓鱼
        //请慎重选择是否开启防钓鱼功能
        //exter_invoke_ip、anti_phishing_key一旦被设置过，那么它们就会成为必填参数
        //开启防钓鱼功能后，服务器、本机电脑必须支持远程XML解析，请配置好该环境。
        //建议使用POST方式请求数据
        String anti_phishing_key  = "";                //防钓鱼时间戳
        String exter_invoke_ip= "";                    //获取客户端的IP地址，建议：编写获取客户端IP地址的程序
        //如：
        //anti_phishing_key = AlipayFunction.query_timestamp(partner);    //获取防钓鱼时间戳函数
        //exter_invoke_ip = "202.1.1.1";

        //扩展功能参数——其他
        String extra_common_param = "";                //自定义参数，可存放任何内容（除=、&等特殊字符外），不会显示在页面上
        String buyer_email = "";                    //默认买家支付宝账号
        String extend_param = "";

        //扩展功能参数——分润(若要使用，请按照注释要求的格式赋值)
        String royalty_type = "";                    //提成类型，该值为固定值：10，不需要修改
        String royalty_parameters ="";
        //提成信息集，与需要结合商户网站自身情况动态获取每笔交易的各分润收款账号、各分润金额、各分润说明。最多只能设置10条
        //各分润金额的总和须小于等于total_fee
        //提成信息集格式为：收款方Email_1^金额1^备注1|收款方Email_2^金额2^备注2
        //如：
        //royalty_type = "10"
        //royalty_parameters    = "111@126.com^0.01^分润备注一|222@126.com^0.01^分润备注二"

        //之前设置的1h将返回
        //错误描述: 抱歉，商户没有开通自定义超时权限，请联系您的商家。
        //错误代码: SELF_TIMEOUT_NOT_SUPPORT

        String it_b_pay="";

        //钱包token
        String extern_token = ""; //选填

        Map<String, String> sPara = new HashMap<>();

        BigDecimal amount = "prod".equals(config.getProfile()) ? trade.getAmount() : new BigDecimal("0.1");
        String subject = "prod".equals(config.getProfile()) ? trade.getSubject() : "[测试]" + trade.getSubject();

        JsonResult<String> payRet;
        AliPay alipay = payConfig.getAliPay();
        if (mobile == 0) {
            sPara.put("service","create_direct_pay_by_user");
            sPara.put("payment_type","1");
            //sPara.put("partner", config.partner);
            //sPara.put("seller_email", config.seller_email);
            sPara.put("return_url", callbackUrl);
            sPara.put("notify_url", alipay.getNotifyUrl());
            //sPara.put("_input_charset", AlipayConfig.input_charset);
            sPara.put("show_url", "");
            sPara.put("out_trade_no", trade.getTradeNo());
            sPara.put("subject", subject);
            sPara.put("body", subject);
            sPara.put("total_fee", amount + "");
            sPara.put("paymethod", paymethod);
            sPara.put("defaultbank", defaultbank);
            sPara.put("anti_phishing_key", anti_phishing_key);
            sPara.put("exter_invoke_ip", exter_invoke_ip);
            sPara.put("extra_common_param", extra_common_param);
            sPara.put("buyer_email", buyer_email);
            sPara.put("royalty_type", royalty_type);
            sPara.put("royalty_parameters", royalty_parameters);
            sPara.put("it_b_pay", it_b_pay);

            payRet = alipay.getPayDataForWeb(sPara);
        } else {
            sPara.put("service", "alipay.wap.create.direct.pay.by.user");
            sPara.put("payment_type", "1");
            //sPara.put("partner", config.partner);
            //sPara.put("seller_id", config.seller_email);
            //sPara.put("_input_charset", AlipayConfig.input_charset);
            sPara.put("notify_url", alipay.getNotifyUrl());
            sPara.put("return_url", callbackUrl);
            sPara.put("out_trade_no", trade.getTradeNo());
            sPara.put("subject", subject);
            sPara.put("total_fee", amount + "");
            sPara.put("show_url", "");
            sPara.put("body", subject);
            sPara.put("it_b_pay", "");
            sPara.put("extern_token", extern_token);

            payRet = alipay.getPayDataForWap(sPara);
        }

        return payRet.getData();
    }

    /**
     * 提案上传初始化
     * 
     * @return
     */
    @PostMapping("proposal_init")
	@ResponseBody
    public String proposalInit(HttpServletRequest request)  {
        try {
            int uid = getCurrentUser(request).getId();
            ProposalUploadContext.getInstance(uid, request).init();
            return "{}";
        } catch (Exception ex) {
            handleError(request, ex);
            return "{}";
        }
    }

    /**
     * 提案上传文件删除
     * 
     * @param key 文件Key
     * @return
     */
    @PostMapping("proposal_delfile")
	@ResponseBody
    public String proposalDelfile(
            @RequestParam("key") String key,
            HttpServletRequest request) {
        try {
            int uid = getCurrentUser(request).getId();
            ProposalUploadContext.getInstance(uid, request).delFile(key);
            return "{}";
        } catch (Exception ex) {
            handleError(request, ex);
            return "{}";
        }
    }

    /**
     * 任务提案提交
     * 
     * @param params 表单
     * @return
     */
    @PostMapping("proposal_submit")
	@ResponseBody
    public JsonResult<Integer> proposalSubmit(
            @RequestParam Map<String, String> params,
            HttpServletRequest request) {
        try {
            User user = getCurrentUser(request);
            
            int taskId = Integer.parseInt(params.get("taskid"));

            Project proj = projectService.getProjectByTaskAndUser(taskId, user.getId());
            if (proj == null) {
                return JsonResult.fail(-1, "proposal not found.");
            }
                    
            ProposalUploadContext ud = ProposalUploadContext.getInstance( user.getId(), request );

            proj.setProposalDescribe( params.getOrDefault("descr2", "") );
            proj.setProposalHidden( getInt(params.get("hidden"), 0) );

            String[] keys = request.getParameterValues("key");
            String[] descrs = request.getParameterValues("descr");

            List<ProjectFile> imgList = new ArrayList<>();
            for (int i = 0; i < keys.length; i++) {
                String key = keys[i];
                if (!StrUtil.isEmpty(key)) {
                    ProjectFile zpImg = new ProjectFile();
                    zpImg.setUrl(ud.getFile(key));
                    zpImg.setDescription(descrs[i]);
                    imgList.add(zpImg);
                }
            }
            proj.setFileList( imgList );

            projectService.submitProject(proj);

            ud.init();

            return JsonResult.ok(1);
                    
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * 任务提案更新
     * 
     * @param params
     * @return
     */
    @PostMapping("proposal_update")
    @ResponseBody
    public JsonResult<Integer> proposal_update(@RequestParam Map<String, String> params,
            HttpServletRequest request) {
        try {
            User user = getCurrentUser(request);
            
            int projId = getInt(params.get("projectid"), 0);

            Project proj = projectService.getProject(projId);
            if (proj == null || proj.getUserId() != user.getId()) { //new
                return JsonResult.fail(-1, "Project not found.");
            }

            ProposalUploadContext ctx = ProposalUploadContext.getInstance(user.getId(), request);

            proj.setProposalDescribe( params.get("descr2") );
            proj.setProposalHidden( getInt(params.get("hidden"), 0) );

            String[] keyArr = request.getParameterValues("key");
            String[] descrArr = request.getParameterValues("descr");

            List<ProjectFile> imgList = new ArrayList<>();
            for (int i = 0; i < keyArr.length; i++)  {
                if (!StrUtil.isEmpty(keyArr[i])) {
                    ProjectFile zpImg = new ProjectFile();
                    zpImg.setUrl( ctx.getFile(keyArr[i]) );
                    zpImg.setDescription( descrArr[i] );
                    imgList.add(zpImg);
                }
            }
            proj.setFileList( imgList );

            projectService.updateProposal(proj);

            ctx.init();

            return JsonResult.ok(1);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * 雇佣任务页
     * 
     * @param userId 用户ID
     * @return
     */
    @GetMapping("employ")
    public ModelAndView employ(@RequestParam("userid")int userId,
            HttpServletRequest request) {

        User user = getCurrentUser(request);
        if (user.getClazz() != 2) {
            // 403
            throw new ForbiddenException("雇主会员才能发布任务。");
        }

        User designer = userService.getUser(userId);
        if (designer == null)  {
            throw new RuntimeException("无效的参数。");
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("designer", designer);

        List<Category> cateList = commonService.getCategoryList(0);
        mv.addObject("cateList", cateList);

        List<ZuoPinInfo> zuopinList = zuopinService.getZuoPinInfoListByUser(designer.getId(), 2);
        mv.addObject("zuopinList", zuopinList);

        mv.setViewName("task/employ");
        return mv;
    }

    /**
     * 雇佣任务提交
     * 
     * @param params 表单参数
     * @return
     */
    @PostMapping("employ")
    @ResponseBody
    public JsonResult<Integer> employPost(@RequestParam Map<String, String> params,
            HttpServletRequest request) {
        try {
            
            int designerId = getInt(params.get("designerid"), 0);
            if (designerId == 0) {
                return JsonResult.fail(-1, "无效的参数。");
            }
            User user = getCurrentUser(request);

            TaskInfo task = new TaskInfo();

            task.setUserId(user.getId());
            task.setCateId( getInt(params.get("cate"), 0) );
            task.setRequirement( params.get("requirement") );
            task.setDesignDetails( params.get("designDetails") );
            task.setDesignContent( params.get("designContent") );
            task.setHopeDays( getInt(params.get("hopeDays"), 7) );
            task.setService( getInt(params.get("service"), 0) == 0 ? 1 : 2);
            task.setDesignBudget( new BigDecimal(params.get("designBudget")) );
            task.setOtherService( params.get("otherService") );
            task.setInvoice( getInt(params.get("invoice"), 0) );
            task.setInvTitle( params.get("invTitle") );
            task.setInvAddress( params.get("invAddress") );
            task.setInvRecipients( params.get("invRecipients") );
            task.setInvCellphone( params.get("invCellphone") );
            task.setTotalAmount( new BigDecimal(params.get("amount1")) ); 
            task.setFaxAmount( BigDecimal.ZERO );
            task.setServiceAmount( BigDecimal.ZERO );
            
            BigDecimal totalAmount = task.getTotalAmount();
            if (task.getInvoice() == 1) { // 需要发票
                totalAmount = totalAmount.subtract(new BigDecimal(8));  // -8
                task.setFaxAmount( totalAmount.multiply(new BigDecimal(0.06)) ); // x0.06
            }
            if (task.getService() == 2) { // 管家标
                task.setServiceAmount( totalAmount.multiply(new BigDecimal(0.15)) );
            }
            task.setDesignAmount( totalAmount.subtract(task.getServiceAmount()).subtract(task.getFaxAmount()));
            
            task.setTaskMode( 3 );
            task.setTaskState( TaskState.WaitingAccept);
            task.setLimitDesigner( 1 ); // 限制1人
            task.setAddTime( new Date() );

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DAY_OF_YEAR, task.getHopeDays());
            task.setDueTime( cal.getTime() );

            String[] keyArr = request.getParameterValues("key");
            List<TaskFile> fileList = new ArrayList<>();
            if (keyArr != null) {
                for (String key : keyArr) {
                    TaskFile tf = new TaskFile();
                    String fileUrl = TaskPublishContext.getInstance(user.getId(), request).getFile(key);
                    if (!StrUtil.isEmpty(fileUrl)) {
                        tf.setUrl( fileUrl );
                        int n = fileUrl.lastIndexOf(".");
                        if (n != -1) {
                            tf.setType( fileUrl.substring(n+1) );
                        }
                        fileList.add(tf);
                    }
                }
            }
            task.setFileList( fileList );

            taskService.submitEmployTask(task, designerId);
            return JsonResult.ok(1);

        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * 任务修改页
     * 
     * @param id 任务ID
     * @param request
     * @return
     */
    @GetMapping("edit")
    public ModelAndView edit(@RequestParam("id")int id,
            HttpServletRequest request) {

        User user = getCurrentUser(request);

        if (user.getClazz() != 2){
            throw new ForbiddenException("雇主会员才能发布任务。");
        }
        TaskInfo task = taskService.getTaskInfo(id );
        if (task == null) {
            throw new ForbiddenException("无效的参数。");
        }
        if (task.getUserId() != user.getId()) {
            throw new ForbiddenException("非法的访问。");
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("task", task);

        List<Category> cateList = commonService.getCategoryList(0);
        mv.addObject("cateList", cateList);

        TaskPublishContext ctx = TaskPublishContext.getInstance(user.getId(), request);
        ctx.init();

        List<TaskFile> list = taskService.getTaskFileList(id);
               List<TaskFileVO> volist = list.stream()
               .map((file)->{
                    TaskFileVO vo = new TaskFileVO(file);
                    ctx.addFile(vo.getKey(), file.getUrl());
                    return vo;
               }).collect(Collectors.toList());
        mv.addObject("fileList", volist);

        mv.setViewName("task/edit");
        return mv;
          
    }

    /**
     * 任务修改提交
     * 
     * @param params 表单参数
     * @return
     */
    @UserOperate(UserAction.TASK_EDIT)
    @PostMapping("edit")
    @ResponseBody
    public JsonResult<String> editPost(@RequestParam Map<String, String> params,
            HttpServletRequest request) {
                
        try
        {
            User user = getCurrentUser(request);

            if (user.getClazz() != 2){
                return JsonResult.fail(-1,"雇主会员才能发布任务。");
            }
            int id = getInt(params.get("id"), 0);
            TaskInfo task = taskService.getTaskInfo(id );
            if (task == null) {
                return JsonResult.fail(-1,"无效的参数。");
            }
            if (task.getUserId() != user.getId()) {
                return JsonResult.fail(-1,"非法的访问。");
            }

            task.setCateId( getInt(params.get("cate"), 0));
            task.setRequirement( params.get("requirement") );
            task.setDesignDetails( params.get("designDetails") );
            task.setDesignContent( params.get("designContent") );
            task.setHopeDays( getInt(params.get("hopeDays"), 7));
            task.setService( getInt(params.get("service"), 0) == 0 ? 1 : 2);
            task.setDesignBudget( new BigDecimal(params.get("designBudget")));
            task.setOtherService( params.get("otherService"));
            task.setInvoice( getInt(params.get("invoice"), 0) );
            task.setInvTitle( params.get("invTitle"));
            task.setInvAddress( params.get("invAddress") );
            task.setInvRecipients( params.get("invRecipients") );
            task.setInvCellphone( params.get("invCellphone") );
            task.setTotalAmount( new BigDecimal(params.get("amount1")) );
            task.setDesignAmount( BigDecimal.ZERO );
            task.setServiceAmount( BigDecimal.ZERO );
            task.setFaxAmount( BigDecimal.ZERO );

            BigDecimal totalAmount = task.getTotalAmount();
            if (task.getInvoice() == 1) { // 需要发票
                totalAmount = totalAmount.subtract(new BigDecimal(8));
                task.setFaxAmount( totalAmount.multiply(new BigDecimal(0.06)) ); // x0.06
            }
            if (task.getService() == 2) { // 管家标
                task.setServiceAmount( totalAmount.multiply(new BigDecimal(0.15)) ); // x0.15
            }
            task.setDesignAmount( totalAmount.subtract(task.getServiceAmount()).subtract(task.getFaxAmount()) );

            task.setTaskMode( getInt(params.get("invest-mode"), 0) );
            //task.AddTime = DateTime.Now;

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DAY_OF_YEAR, task.getHopeDays());
            task.setDueTime( cal.getTime() );

            String[] keys = request.getParameterValues("key");
            //string[] attachments = Request.Form.GetValues("attachment");
            List<TaskFile> fileList = new ArrayList<>();
            if (keys != null) {
                for (String k : keys)  {
                    TaskFile tf = new TaskFile();
                    tf.setUrl( TaskPublishContext.getInstance(user.getId(), request).getFile(k) );
                    if (!StrUtil.isEmpty(tf.getUrl())) {
                        int n = tf.getUrl().lastIndexOf(".");
                        if ( n > 0) {
                            tf.setType(tf.getUrl().substring(n+1));
                        }
                        fileList.add(tf);
                    }
                }
            }
            task.setFileList( fileList );

            Trade trade = tradeService.getTradeByTask(id);
            if (trade != null)  {
                trade.setBalance( BigDecimal.ZERO );
                trade.setAmount( task.getTotalAmount() );
                trade.setTotalAmount( task.getTotalAmount() );
            } else {
                trade = new Trade();
                trade.setTaskId( task.getId() );
                trade.setUserId( user.getId() );
                trade.setSubject( task.getRequirement() );
                trade.setAmount( task.getTotalAmount() );
                trade.setTotalAmount( task.getTotalAmount() );
                trade.setAddTime( new Date() );
            }

            taskService.updateTaskInfo2(task, trade);

            return JsonResult.ok( trade.getTradeNo() );
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
               
    }

    /**
     * 雇佣任务修改页
     * 
     * @return
     */
    @GetMapping("edit2")
    public ModelAndView edit2(@RequestParam("id")int id,
            HttpServletRequest request) {

        User user = getCurrentUser(request);

        if (user.getClazz() != 2){
            throw new ForbiddenException("雇主会员才能发布任务。");
        }

        TaskInfo task = taskService.getTaskInfo(id );
        if (task == null) {
            throw new ForbiddenException("无效的参数。");
        }
        if (task.getUserId() != user.getId()) {
            throw new ForbiddenException("非法的访问。");
        }

        List<Project> projectList = projectService.getProjectListByTask(id);
        if (projectList.isEmpty()) {
            throw new ForbiddenException("无效的参数。");
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("task", task);
 
        List<Category> cateList = commonService.getCategoryList(0);
        mv.addObject("cateList", cateList);

        TaskPublishContext ctx = TaskPublishContext.getInstance(user.getId(), request);
        ctx.init();

        List<TaskFile> list = taskService.getTaskFileList(id);
               List<TaskFileVO> volist = list.stream()
               .map((file)->{
                    TaskFileVO vo = new TaskFileVO(file);
                    ctx.addFile(vo.getKey(), file.getUrl());
                    return vo;
               }).collect(Collectors.toList());
        mv.addObject("fileList", volist);

        int uid = projectList.get(0).getUserId();
        User designer = userService.getUser(uid);
        List<ZuoPinInfo> zuopinList = zuopinService.getZuoPinInfoListByUser(uid, 2);
        mv.addObject("designer", designer);
        mv.addObject("zuopinList", zuopinList);

        mv.setViewName("task/edit2");
        return mv;
            
    }

    /**
     * 雇佣任务提交
     * 
     * @param params 表单参数
     * @return
     */
    @UserOperate(UserAction.TASK_EDIT)
    @PostMapping("edit2")
    @ResponseBody
    public JsonResult<String> edit2Post(@RequestParam Map<String, String> params,
            HttpServletRequest request) {

        try {
            User user = getCurrentUser(request);

            if (user.getClazz() != 2){
                return JsonResult.fail(-1,"雇主会员才能发布任务。");
            }
            int id = getInt(params.get("id"), 0);
            TaskInfo task = taskService.getTaskInfo(id );
            if (task == null) {
                return JsonResult.fail(-1,"无效的参数。");
            }
            if (task.getUserId() != user.getId()) {
                return JsonResult.fail(-1,"非法的访问。");
            }

            task.setCateId( getInt(params.get("cate"), 0));
            task.setRequirement( params.get("requirement") );
            task.setDesignDetails( params.get("designDetails") );
            task.setDesignContent( params.get("designContent") );
            task.setHopeDays( getInt(params.get("hopeDays"), 7));
            task.setService( getInt(params.get("service"), 0) == 0 ? 1 : 2);
            task.setDesignBudget( new BigDecimal(params.get("designBudget")));
            task.setOtherService( params.get("otherService"));
            task.setInvoice( getInt(params.get("invoice"), 0) );
            task.setInvTitle( params.get("invTitle"));
            task.setInvAddress( params.get("invAddress") );
            task.setInvRecipients( params.get("invRecipients") );
            task.setInvCellphone( params.get("invCellphone") );
            task.setTotalAmount( new BigDecimal(params.get("amount1")) );
            task.setDesignAmount( BigDecimal.ZERO );
            task.setServiceAmount( BigDecimal.ZERO );
            task.setFaxAmount( BigDecimal.ZERO );

            BigDecimal totalAmount = task.getTotalAmount();
            if (task.getInvoice() == 1) { // 需要发票
                totalAmount = totalAmount.subtract(new BigDecimal(8));
                task.setFaxAmount( totalAmount.multiply(new BigDecimal(0.06)) ); // x0.06
            }
            if (task.getService() == 2) { // 管家标
                task.setServiceAmount( totalAmount.multiply(new BigDecimal(0.15)) ); // x0.15
            }
            task.setDesignAmount( totalAmount.subtract(task.getServiceAmount()).subtract(task.getFaxAmount()) );
            //task.AddTime = DateTime.Now;
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DAY_OF_YEAR, task.getHopeDays());
            task.setDueTime( cal.getTime() );

            String[] keys = request.getParameterValues("key");
            //string[] attachments = Request.Form.GetValues("attachment");
            List<TaskFile> fileList = new ArrayList<>();
            if (keys != null) {
                for (String k : keys)  {
                    TaskFile tf = new TaskFile();
                    tf.setUrl( TaskPublishContext.getInstance(user.getId(), request).getFile(k) );
                    if (!StrUtil.isEmpty(tf.getUrl())) {
                        int n = tf.getUrl().lastIndexOf(".");
                        if ( n > 0) {
                            tf.setType(tf.getUrl().substring(n+1));
                        }
                        fileList.add(tf);
                    }
                }
            }
            task.setFileList( fileList );

            Trade trade = tradeService.getTradeByTask(id);
            if (trade != null) {
                trade.setBalance( BigDecimal.ZERO );
                trade.setAmount( task.getTotalAmount() );
                trade.setTotalAmount( task.getTotalAmount() );
            }

            task.setTaskState( TaskState.WaitingAccept );
            taskService.updateTaskInfo3(task, trade);

            if (trade != null) { // 去付款
                return JsonResult.ok(1, trade.getTradeNo());
            } else {
                return JsonResult.ok(2, "");
            }
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
               

    }
}
