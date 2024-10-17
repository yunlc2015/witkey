/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.controller;

import java.util.Collections;
import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.math.BigDecimal;

import com.kfayun.app.witkey.Constants;
import com.kfayun.app.witkey.JsonResult;
import com.kfayun.app.witkey.PagerInfo;
import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.condition.PaymentCondition;
import com.kfayun.app.witkey.condition.ProjectCondition;
import com.kfayun.app.witkey.condition.TaskCondition;
import com.kfayun.app.witkey.condition.WithdrawalCondition;
import com.kfayun.app.witkey.config.AppConfig;
import com.kfayun.app.witkey.model.*;
import com.kfayun.app.witkey.service.*;
import com.kfayun.app.witkey.util.*;
import com.kfayun.app.witkey.web.UserAuth;
import com.kfayun.app.witkey.web.aop.UserAction;
import com.kfayun.app.witkey.web.aop.UserOperate;
import com.kfayun.app.witkey.web.vo.PaymentVO;
import com.kfayun.app.witkey.web.vo.ProjectVO;
import com.kfayun.app.witkey.web.vo.TaskInfoVO;
import com.kfayun.app.witkey.web.vo.WithdrawalVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 雇主用户Controller
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
@Controller
@RequestMapping("/user/employer")
public class UserEmployerController extends BaseController {
	
	@Autowired
	private CmsService cmsService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private TradeService tradeService;
	@Autowired
	private UserService userService;
	@Autowired
	private FinanceService financeService;

	/**
	 * 雇主用户中心页
	 * 
	 * @param params
	 * @return
	 */
	@GetMapping(value = {"/", "index"})
    public ModelAndView index(
			@RequestParam Map<String, String> params,
			HttpServletRequest request){

		ModelAndView mv = new ModelAndView();

		User user = getCurrentUser(request);
		mv.addObject("user", user);

		List<Banner> bannerList = cmsService.getBannersByLocation("employer_index_ad");
		Banner banner = !bannerList.isEmpty() ? bannerList.get(0) : null;
		mv.addObject("banner", banner);

		TaskCondition cond = new TaskCondition();
		cond.setUserId( getCurrentUser(request).getId() );
		cond.setAuditState( -1 );

		List<TaskInfo> taskList = taskService.findTaskInfoList(cond, 3);
		List<TaskInfoVO> taskVoList = taskList.stream()
				.map((task)->{
					TaskInfoVO vo = new TaskInfoVO(task);
					if (task.getTaskState() != TaskState.Unpay && task.getTaskState() != TaskState.Cancelled) {
						Map<String, Long> projectTotal = projectService.getProjectCountByTask(task.getId());
						vo.setProjectTotal(projectTotal);
					}
					if (task.getTaskState() == TaskState.Unpay) {
						vo.setTradeNo(tradeService.getTradeNoByTask(task.getId()));
					}
					return vo;
				}).collect(Collectors.toList());
		mv.addObject("taskList", taskVoList);

		mv.setViewName("user/employer/index");
		return mv;
    }
	
	/**
	 * 任务列表页
	 * 
	 * @param params
	 * @return
	 */
	@GetMapping("tasklist")
    public ModelAndView taskList(
			@RequestParam Map<String, String> params,
			HttpServletRequest request){

		ModelAndView mv = new ModelAndView();

		TaskCondition cond = new TaskCondition();
		cond.setUserId( getCurrentUser(request).getId() );
		cond.setAuditState( -1 );

		int page = getInt(params.get("page"), 1);
		int pageSize = 10;
		PageList<TaskInfo> pglist = taskService.findTaskInfoList(cond, page, pageSize);
		PagerInfo pager = new PagerInfo(page, pageSize);
		pager.setTotalCount(pglist.getTotal());
		pager.setBaseUrl("/user/employer/tasklist");

		List<TaskInfoVO> taskList = pglist.getList().stream()
				.map((task)->{
					TaskInfoVO vo = new TaskInfoVO(task);
					if (task.getTaskState() != TaskState.Unpay && task.getTaskState() != TaskState.Cancelled) {
						Map<String, Long> projectTotal = projectService.getProjectCountByTask(task.getId());
						vo.setProjectTotal(projectTotal);
					}
					if (task.getTaskState() == TaskState.Unpay) {
						vo.setTradeNo(tradeService.getTradeNoByTask(task.getId()));
					}
					return vo;
				}).collect(Collectors.toList());
		mv.addObject("taskList", taskList);
		mv.addObject("pager", pager);
		mv.addObject("cond", cond);

		mv.setViewName("user/employer/tasklist");
		return mv;
    }

	/**
	 * 任务修改时间页
	 * 
	 * @param taskId 任务ID
	 * @return
	 */
	@GetMapping("changedate")
	public ModelAndView changeDate(@RequestParam("taskid")int taskId,
		HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();

		User user = getCurrentUser(request);
		mv.addObject("user", user);

		TaskInfo task = taskService.getTaskInfo(taskId);
		if (task!= null && task.getUserId() == user.getId()) {
			
			mv.addObject("task", task);

			Category cate = commonService.getCategory(task.getCateId());
			mv.addObject("cateName", cate.getName());
		}
		
		return mv;
	}

	/**
	 * 任务修改时间提交
	 * 
	 * @param params 表单参数
	 * @return
	 */
	@UserOperate(UserAction.TASK_CHANGE_DATE)
	@PostMapping("changedate")
	@ResponseBody
	public JsonResult<Integer> changeDatePost(
		@RequestParam Map<String, String> params,
		HttpServletRequest request) {
		try {
			User user = getCurrentUser(request);

			int taskId = getInt( params.get("taskid"), 0 );
			if (taskId == 0) {
				return JsonResult.fail(-1, "parameter error.");
			}

			TaskInfo tinfo = taskService.getTaskInfo(taskId);
			if (tinfo== null || tinfo.getUserId() != user.getId()) {
				return JsonResult.fail(-1, "parameter error.");
			}

			int days = Integer.parseInt(params.get("days"));
			
			int n = taskService.changeTaskDate(tinfo, days);

			return JsonResult.ok(n);
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}

	/**
	 * 任务修改价格页
	 * 
	 * @param taskId 任务ID
	 * @return
	 */
	@GetMapping("raiseprice")
	public ModelAndView raisePrice(@RequestParam("taskid")int taskId,
		HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();

		User user = getCurrentUser(request);
		mv.addObject("user", user);

		TaskInfo task = taskService.getTaskInfo(taskId);
		if (task!= null && task.getUserId() == user.getId()) {
			
			mv.addObject("task", task);

			Category cate = commonService.getCategory(task.getCateId());
			mv.addObject("cateName", cate.getName());
		}

		return mv;
	}

	/**
	 * 任务修改价格提交
	 * 
	 * @param params 表单参数
	 * @return
	 */
	@UserOperate(UserAction.TASK_CHANGE_AMOUNT)
	@PostMapping("raiseprice")
	@ResponseBody
	public JsonResult<String> raisePricePost(
		@RequestParam Map<String, String> params,
		HttpServletRequest request) {
		try {
			User user = getCurrentUser(request);

			int taskId = getInt( params.get("taskid"), 0 );
			if (taskId == 0) {
				return JsonResult.fail(-1, "参数错误。");
			}
			String amountStr = params.get("amount");
			if (StrUtil.isEmpty(amountStr)) {
				return JsonResult.fail(-1, "金额不能为空。");
			}

			TaskInfo tinfo = taskService.getTaskInfo(taskId);
			if (tinfo== null || tinfo.getUserId() != user.getId()) {
				return JsonResult.fail(-1, "参数错误。");
			}

			BigDecimal amount = new BigDecimal(amountStr);

			Trade trade = new Trade();
			trade.setTaskId( taskId );
			trade.setUserId( tinfo.getUserId() );
			trade.setSubject( tinfo.getRequirement() );
			trade.setAmount( amount );
			trade.setTotalAmount( amount );
			trade.setAddTime( new Date() );
			trade.setPayFlag( 1 );

			tradeService.saveTrade(trade);

			return JsonResult.ok( trade.getTradeNo() );
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}

	/**
	 * 任务提交获取页
	 * 
	 * @param taskId 任务ID
	 * @param type 提交状态类别
	 * @param page 分页数
	 * @param boxId
	 * @return
	 */
	@GetMapping("proposals")
	public ModelAndView proposals(@RequestParam("taskid")int taskId,
			@RequestParam("type")int type,
			@RequestParam("page")int page,
			@RequestParam("boxid")String boxId) {
				
		ProjectCondition cond = new ProjectCondition();
		cond.setTaskId(taskId);
		if (type == 0) {
			cond.setState( -1 );
		} else if (type == 1) {
			cond.setState( ProjectState.Submitted.getConstant() );
			cond.setSelected(1);
		} else if (type == 2) {
			cond.setState( ProjectState.Submitted.getConstant() );
		} else if (type == 3) {
			cond.setState( ProjectState.WaitingSubmit.getConstant() );
		}

		int pageSize = 100;
		PageList<Project> pglist = projectService.findProjectList(cond, page, pageSize);
		PagerInfo pinfo = new PagerInfo(page, pageSize);
		pinfo.setTotalCount(pglist.getTotal());

        List<ProjectVO> projectList = pglist.getList().stream()
            .map((project)->{
                ProjectVO vo = new ProjectVO(project);
                vo.setFileList(projectService.getProjectFileList(project.getId()));
				vo.setRating(commonService.getRatingByProject(project.getId()));
                return vo;
            }).collect(Collectors.toList());

		ModelAndView mv = new ModelAndView();
		mv.addObject("projectList", projectList);
		mv.addObject("pager", pinfo);
		mv.addObject("cond", cond);
		mv.addObject("boxId", boxId);

		mv.setViewName("user/employer/proposals");
		return mv;
            
	}

	/**
	 * 任务提案选定操作
	 * 
	 * @param taskId 任务ID
	 * @param projectId 项目ID
	 * @return
	 */
	@UserOperate(UserAction.PROPOSAL_CHOOSE)
	@PostMapping("proposal_choose")
	@ResponseBody
	public JsonResult<Integer> proposalChoose(
			@RequestParam("taskid")int taskId,
			@RequestParam("projectid")int projectId,
			HttpServletRequest request) {
		try {
			int ret = projectService.chooseProposal(taskId, projectId);
			return JsonResult.ok(ret);
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}

	/**
	 * 任务提案付款操作
	 * 
	 * @param taskId 任务ID
	 * @return
	 */
	@UserOperate(UserAction.PROPOSAL_PAYMENT)
	@PostMapping("proposal_payment")
	@ResponseBody
	public JsonResult<Integer> proposalPayment(@RequestParam("taskid")int taskId,
			HttpServletRequest request) {
		try {
			User user = getCurrentUser(request);
			
			if (taskId == 0 || user== null) {
				return JsonResult.fail(-1, "parameter error.");
			}

			TaskInfo tinfo = taskService.getTaskInfo(taskId);
			if (tinfo.getUserId() != user.getId()) {
				return JsonResult.fail(-2, "illegal operation.");
			}

			if (tinfo.getTaskState() == TaskState.Selected) {
				tinfo.setTaskState( TaskState.Acceptance );

				taskService.updateTaskInfo(tinfo);
				return JsonResult.ok(1);
			}
			
			return JsonResult.ok(0);
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}

	/**
	 * 任务提案评价处理
	 * 
	 * @param projectId 项目ID
	 * @param star 评分（1-5）
	 * @param content 评价内容
	 * @return
	 */
	@UserOperate(UserAction.PROPOSAL_RATING)
	@PostMapping("proposal_rating")
	@ResponseBody
	public JsonResult<Integer> proposalRating(
			@RequestParam("projectid")int projectId,
			@RequestParam("star")int star,
			@RequestParam("content")String content,
			HttpServletRequest request) {
		try {
			User user = getCurrentUser(request);
			
			if (projectId == 0 || user == null) {
				return JsonResult.fail(-1, "parameter error.");
			}

			Project proj = projectService.getProject(projectId);

			Rating rt = new Rating();
			rt.setUserId( user.getId() );
			rt.setProjectId( projectId );
			rt.setTaskId( proj.getTaskId() );
			rt.setToUserId( proj.getUserId() );
			rt.setContent( content );
			rt.setStar( star );
			rt.setAddTime( new Date() );

			commonService.saveRating(rt);
			return JsonResult.ok(1);
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}

	/**
	 * 任务删除处理
	 * 
	 * @param taskId 任务ID
	 * @return
	 */
	@UserOperate(UserAction.TASK_DELETE)
	@PostMapping("deletetask")
	@ResponseBody
	public JsonResult<Integer> deleteTask(
			@RequestParam("taskid")int taskId,
			HttpServletRequest request) {
		try {
			User user = getCurrentUser(request);

			TaskInfo task = taskService.getTaskInfo(taskId);
			if (task == null || task.getUserId() != user.getId()) {
				return JsonResult.fail(-1, "参数错误。");
			}

			if (task.getTaskState() != TaskState.Unpay) {
				return JsonResult.fail(-2, "只能删除未支付状态的订单。");
			}

			taskService.deleteTaskInfo(taskId, user.getId());

			return JsonResult.ok(0);
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}

	/**
	 * 投诉页
	 * 
	 */
	@GetMapping("tousu")
	public ModelAndView tousu(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();

		User user = getCurrentUser(request);
		mv.addObject("user", user);

		mv.setViewName("user/employer/tousu");
		return mv;
	}

	/**
	 * 投诉处理
	 * 
	 * @param params 表单参数
	 * @return
	 */
	@PostMapping("tousu")
	@ResponseBody
	public JsonResult<Integer> tousuPost(
			@RequestParam Map<String, String> params,
			HttpServletRequest request) {
		try {
			User user = getCurrentUser(request);

			TouSu ts = new TouSu();
			ts.setUserId( user.getId() );
			ts.setContent( params.get("content") );
			ts.setAddTime(new Date() );
			
			commonService.saveTouSu(ts);
			return JsonResult.ok(1);
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}

	/**
	 * 钱包页
	 * 
	 * @param params
	 * @return
	 */
	@GetMapping("wallet")
	public ModelAndView wallet(
			@RequestParam Map<String, String> params,
			HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();

		User user = getCurrentUser(request);
		mv.addObject("user", user);

		PaymentCondition cond = new PaymentCondition();
		cond.setUserId( user.getId() );

		int page = getInt( params.get("page"), 1 );
		int pageSize = 6;
		PageList<Payment> pglist = financeService.findPaymentList(cond, page, pageSize);
		PagerInfo info = new PagerInfo(page, pageSize);
		info.setTotalCount(pglist.getTotal());
		info.setBaseUrl("/user/employer/wallet");

		List<PaymentVO> voList = pglist.getList().stream()
			.map((payment)->{
				PaymentVO vo = new PaymentVO(payment);

				if (payment.getKind() == 1 || payment.getKind() == 3) { // 订单消费, 任务结算
					TaskInfo ti = taskService.getTaskInfo(payment.getTaskId());
					vo.setDetail( "￥" + ti.getDesignBudget() + "   " + ti.getRequirement() );
				}  else if (payment.getKind() == 4) { //提现 
					Withdrawal wd = financeService.getWithdrawalByPayment(payment.getId());
					vo.setStateText( financeService.getWithdrawalStateText(wd.getState()) );

					String detail = wd.getBankName() + " （" + financeService.getAccountNo(wd.getBankAccNo()) + 
						"） " + wd.getBankAccName() + " " + wd.getAmount() + " 元";
					vo.setDetail( detail );
				}

				return vo;
			}).collect(Collectors.toList());
		mv.addObject("paymentList", voList);
		mv.addObject("pager", info);
		mv.addObject("cond", cond);

		mv.setViewName("user/employer/wallet");
		return mv;
	}

	/**
	 * 提现列表页
	 * 
	 * @param params
	 * @return
	 */
	@GetMapping("withdrawallist")
	public ModelAndView withdrawalList(
			@RequestParam Map<String, String> params,
			HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();

		User user = getCurrentUser(request);
		mv.addObject("user", user);

		 WithdrawalCondition cond = new WithdrawalCondition();
		cond.setUserId( user.getId() );

		int page = getInt( params.get("page"), 1 );
		int pageSize = 10;
		PageList<Withdrawal> pglist = financeService.findWithdrawalList(cond, page, pageSize);
		PagerInfo info = new PagerInfo(page, pageSize);
		info.setTotalCount(pglist.getTotal());
		info.setBaseUrl("/user/employer/withdrawallist");

		List<WithdrawalVO> voList = pglist.getList().stream()
				.map((wd)->{
					WithdrawalVO vo = new WithdrawalVO(wd);
					vo.setDetail(wd.getBankName() +" （"+ financeService.getAccountNo(wd.getBankAccNo()) +"） "+ wd.getBankAccName() +" "+ wd.getAmount() + " 元" );
					vo.setStateText( financeService.getWithdrawalStateText(wd.getState()) );
					return vo;
				}).collect(Collectors.toList());
		mv.addObject("withdrawalList", voList);
		mv.addObject("pager", info);

		mv.setViewName("user/employer/withdrawallist");
		return mv;
	}

	/**
	 * 账户安全页
	 * 
	 */
	@GetMapping("accountsafety")
	public ModelAndView accountSafety(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();

		User user = getCurrentUser(request);
		mv.addObject("user", user);

		mv.setViewName("user/employer/accountsafety");
		return mv;
	}

	/**
	 * 账户设置页
	 * 
	 */
	@GetMapping("shezhi")
	public ModelAndView shezhi(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();

		User user = getCurrentUser(request);
		mv.addObject("user", user);

		String nickname = !StrUtil.isEmpty(user.getNickname()) 
			? user.getNickname() : user.getMobile();
		String mobile2 = !StrUtil.isEmpty(user.getMobile2()) 
			? user.getMobile2() : user.getMobile();
		mv.addObject("nickname", nickname);
		mv.addObject("mobile2", mobile2);

		int cityId = 0;
		int provId = 0;
        List<CityInfo> provList = commonService.getProvinceList();
		List<CityInfo> cityList = Collections.emptyList();
		if (user.getCityId() > 0) {
			cityId = user.getCityId();
			CityInfo city = commonService.getCity(cityId);
			CityInfo province = commonService.getProvince(city.getProvince());
			provId = province.getId();

			cityList = commonService.getCityListByProvince(city.getProvince());
		}
		mv.addObject("cityId", cityId);
		mv.addObject("provId", provId);
		mv.addObject("cityList", cityList);
		mv.addObject("provList", provList);

		mv.setViewName("user/employer/shezhi");
		return mv;
	}

	/**
	 * 账户设置提交处理
	 * 
	 * @param params 表单参数
	 * @return
	 */
	@UserOperate(UserAction.USER_SETTING)
	@PostMapping("shezhi")
	@ResponseBody
	public JsonResult<Integer> shezhiPost(
			@RequestParam Map<String, String> params,
			HttpServletRequest request) {
		try {
			int cityId = getInt(params.get("city"), 0);
			if (cityId == 0) {
				return JsonResult.fail(-1, "请选择省份城市。");
			}

			User user = getCurrentUser(request);

			String oldPasswd = params.get("oldpasswd");
			String newPasswd = params.get("newpasswd");
			String newPasswd2 = params.get("newpasswd2");

			if (!StrUtil.isEmpty(oldPasswd) ||
				!StrUtil.isEmpty(newPasswd) ||
				!StrUtil.isEmpty(newPasswd2)) { // 密码修改
			
				if (StrUtil.isEmpty(oldPasswd)) {
					return JsonResult.fail(-1, "请输入当前密码。");
				}

				if (StrUtil.isEmpty(newPasswd)) {
					return JsonResult.fail(-1, "请输入新密码。");
				}

				if (StrUtil.isEmpty(newPasswd2)) {
					return JsonResult.fail(-1, "请输入确认密码。");
				}

				if (!newPasswd.equals(newPasswd2)) {
					return JsonResult.fail(-1, "确认密码不正确。");
				}

				if (!(user.getPasswd().equals(CryptoUtil.MD5(CryptoUtil.MD5(oldPasswd) + user.getSalt())))) {
					return JsonResult.fail(-1, "当前密码不正确。");
				}

				user.setPasswd( CryptoUtil.MD5(CryptoUtil.MD5(newPasswd) + user.getSalt()) );
			}

			user.setNickname( params.get("nickname") );
			user.setGender( getInt(params.get("gender"), 0));
			user.setMobile2( params.get("mobile2") );
			user.setIm( params.get("im") );
			user.setCompany( params.get("company") );
			user.setCityId( cityId );

			userService.updateUser(user);

			return JsonResult.ok(0);
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}

}
