/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.math.BigDecimal;

import com.kfayun.app.witkey.Constants;
import com.kfayun.app.witkey.ForbiddenException;
import com.kfayun.app.witkey.JsonResult;
import com.kfayun.app.witkey.PagerInfo;
import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.condition.PaymentCondition;
import com.kfayun.app.witkey.condition.ProjectCondition;
import com.kfayun.app.witkey.condition.WithdrawalCondition;
import com.kfayun.app.witkey.condition.ZuopinCondition;
import com.kfayun.app.witkey.config.AppConfig;
import com.kfayun.app.witkey.model.*;
import com.kfayun.app.witkey.service.*;
import com.kfayun.app.witkey.util.*;
import com.kfayun.app.witkey.web.ProposalUploadContext;
import com.kfayun.app.witkey.web.UserAuth;
import com.kfayun.app.witkey.web.ZuopinUploadContext;
import com.kfayun.app.witkey.web.aop.UserOperate;
import com.kfayun.app.witkey.web.aop.UserAction;
import com.kfayun.app.witkey.web.vo.CategoryVO;
import com.kfayun.app.witkey.web.vo.PaymentVO;
import com.kfayun.app.witkey.web.vo.ProjectFileVO;
import com.kfayun.app.witkey.web.vo.ProjectVO2;
import com.kfayun.app.witkey.web.vo.UserVO;
import com.kfayun.app.witkey.web.vo.WithdrawalVO;
import com.kfayun.app.witkey.web.vo.ZuoPinImageVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 设计师用户Controller
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Controller
@RequestMapping("/user/designer")
public class UserDesignerController extends BaseController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ZuopinService zuopinService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private FinanceService financeService;

	/**
	 * 设计师用户中心页
	 * 
	 */
	@GetMapping(value = {"/", "index"})
    public ModelAndView index(HttpServletRequest request){

		ModelAndView mv = new ModelAndView();

		User user = getCurrentUser(request);
		
		mv.addObject("user", new UserVO(user));

		int authState = 0;

		if (user.getAbilityGrade() > 0) { // 实力认证已通过
			authState = 22;  // 可以交易
		} else {
			AbilityAuthent abilityAuth = userService.getAbilityAuthentByUser(user.getId());
			if (abilityAuth != null) { 
				if (abilityAuth.getAuthState() == 1) { // 实力认证已提交
					authState = 21;
				} else if (abilityAuth.getAuthState() == 3) { // 实力认证不通过
					authState = 23;
				}
			} else {
				if (user.getRealAuthState() == 1) { // 已实名认证
					authState = 12;
				} else {
					RealAuthent realAuth = userService.getRealAuthentByUser(user.getId());
					if (realAuth != null) { 
						if (realAuth.getAuthState() == 1) { // 实名认证已提交
							authState = 11;
						} else if (realAuth.getAuthState() == 3) { // 实名认证不通过
							authState = 13;
						}
					} else {
						authState = 10;
					} 
				}
			}	
		}
		mv.addObject("authState", authState);

		mv.setViewName("user/designer/index");
		return mv;
    }
	
	/**
	 * 账号设置页
	 * 
	 */
	@GetMapping("shezhi")
    public ModelAndView shezhi(HttpServletRequest request){

		ModelAndView mv = new ModelAndView();

		User user = getCurrentUser(request);

		mv.addObject("user", user);

		String nickname = !StrUtil.isEmpty(user.getNickname()) ? user.getNickname() : user.getMobile();
        String mobile2 = !StrUtil.isEmpty(user.getMobile2()) ? user.getMobile2() : user.getMobile();
		mv.addObject("nickname", nickname);
		mv.addObject("mobile2", mobile2);

		int type = user.getType();
		if (type == 0) {
			type = 1;
		}
		mv.addObject("type", type);

		int provId = 0;
		int cityId = 0;
		List<CityInfo> provList = commonService.getProvinceList();
		List<CityInfo> cityList = Collections.emptyList();

		if (user.getCityId() > 0) {
			cityId = user.getCityId();
			CityInfo city = commonService.getCity(cityId);
			CityInfo province = commonService.getProvince(city.getProvince());
			provId = province.getId();

			cityList = commonService.getCityListByProvince(city.getProvince());
		}
		mv.addObject("provId", provId);
		mv.addObject("cityId", cityId);
		mv.addObject("provList", provList);
		mv.addObject("cityList", cityList);

		List<Category> cateList = commonService.getCategoryList(0);
		List<Integer> userCates = userService.getUserCateIdList(user.getId());
		List<CategoryVO> cateVoList = cateList.stream()
			.map(cate->{return new CategoryVO(cate, userCates.contains(cate.getId()));})
			.collect(Collectors.toList());
		mv.addObject("cateList", cateVoList);
		
		mv.setViewName("user/designer/shezhi");
		return mv;
	}

	/**
	 * 账号设置提交
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
			String nickname = params.getOrDefault("nickname", "");
			if (StrUtil.isEmpty(nickname)) {
				return JsonResult.fail(-1, "昵称不能为空。");
			}
			
			String mobile2 = params.getOrDefault("mobile2", "");
			if (StrUtil.isEmpty(mobile2)) {
				return JsonResult.fail(-1, "手机号不能为空。");
			}

			int cityId = getInt(params.get("city"), 0);
			if (cityId == 0) {
				return JsonResult.fail(-1, "请选择省份城市。");
			}
		
			String stPrice = params.getOrDefault("startingprice", "");
			if (stPrice=="") {
				return JsonResult.fail(-2, "设计费用格式不正确。");
			}
			BigDecimal startingPrice = null;
			try {
				startingPrice = new BigDecimal(stPrice);
			} catch (NumberFormatException nfe) {
				return JsonResult.fail(-2, "设计费用格式不正确。");
			}

			String intro = params.getOrDefault("intro", "");
			if (StrUtil.isEmpty(intro)) {
				return JsonResult.fail(-1, "关于我内容不能为空。");
			}

			String[] usercate = request.getParameterValues("usercate");
			if (usercate==null || usercate.length == 0) {
				return JsonResult.fail(-3, "请至少选择一项特长。");
			}
			List<Integer> cates = Arrays.asList(usercate).stream()
				.map((s)->Integer.parseInt(s)).collect(Collectors.toList());
			
			User user = getCurrentUser(request);
			
			user.setType( getInt(params.get("type"), 1) );
			if (user.getType() == 1) { // 个人
				String special = params.getOrDefault("special", "");
				if (StrUtil.isEmpty(special)) {
					return JsonResult.fail(-1, "个人擅长不能为空。");
				}
				user.setSpecial( special );
			} else {
				String special2 = params.getOrDefault("special2", "");
				if (StrUtil.isEmpty(special2)) {
					return JsonResult.fail(-1, "团队擅长不能为空。");
				}
				user.setSpecial( special2 );
			}

			String oldPasswd = params.getOrDefault("oldpasswd", "");
			String newPasswd = params.getOrDefault("newpasswd", "");
			String newPasswd2 = params.getOrDefault("newpasswd2", "");
			if (!StrUtil.isEmpty(oldPasswd) || 
				!StrUtil.isEmpty(newPasswd) ||
				!StrUtil.isEmpty(newPasswd2) ) { // 密码修改
				if (StrUtil.isEmpty(oldPasswd)) {
					return JsonResult.fail(-2, "请输入当前密码。");
				}

				if (StrUtil.isEmpty(newPasswd)) {
					return JsonResult.fail(-2, "请输入新密码。");
				}

				if (StrUtil.isEmpty(newPasswd2)) {
					return JsonResult.fail(-2, "请输入确认密码。");
				}

				if (!newPasswd.equals(newPasswd2)) {
					return JsonResult.fail(-2, "确认密码不正确。");
				}

				String encPasswd = CryptoUtil.MD5(CryptoUtil.MD5(oldPasswd) + user.getSalt());
				if (!(user.getPasswd().equals( encPasswd )) )  {
					return JsonResult.fail(-2, "当前密码不正确。");
				}

				user.setPasswd( CryptoUtil.MD5(CryptoUtil.MD5(newPasswd) + user.getSalt()) );
			}

			user.setNickname( nickname );
			user.setMobile2( mobile2 );
			user.setMobile2Pub( getInt(params.get("mobile2pub"), 0));
			user.setIm( params.getOrDefault("im", "") );
			user.setWorkYear( params.getOrDefault("workyear", "") );
			user.setCompany( params.getOrDefault("company", "") );
			user.setIntro( intro );
			user.setStartingPrice(startingPrice);

			if (user.getType() == 1) { // 个人
				user.setGender( getInt(params.get("gender"), 1) );
				user.setEducation( params.getOrDefault("education", "") );
			} else {
				user.setGender( 0 );
				user.setTeamDescr( params.getOrDefault("teamdescr", "") );
			}

			user.setCityId( cityId );

			userService.updateUser(user, cates);
		
			return JsonResult.ok(1);
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}

	/**
	 * 实名认证页
	 */
	@GetMapping("realauth")
    public ModelAndView realAuth(HttpServletRequest request){

		ModelAndView mv = new ModelAndView();

		User user = getCurrentUser(request);

		mv.addObject("user", user);

		
		mv.setViewName("user/designer/realauth");
		return mv;
	}

	/**
	 * 实名认证提交
	 * 
	 * @param params 表单参数
	 */
	@UserOperate(UserAction.USER_REAL_AUTH)
	@PostMapping("realauth")
	@ResponseBody
	public JsonResult<Integer> realAuthPost(
			@RequestParam Map<String, String> params,
			HttpServletRequest request) {
		try {
			User user = getCurrentUser(request);

			RealAuthent ra = new RealAuthent();
			ra.setUserId( user.getId() );
			ra.setRealname( params.get("realname") );
			ra.setIdcard( params.get("idcard") );
			ra.setSex( getInt(params.get("sex"), 0) );
			ra.setCategory( getInt(params.get("category"), 0) );
			if (ra.getCategory() == 1) {
				ra.setIdcard1( params.get("idcard11") );
				ra.setIdcard2( params.get("idcard12") );
				ra.setDueDate( getInt(params.get("duedate"), 0) );
				ra.setDueDate2( params.get("duedate2") );
				ra.setIndustry( params.get("industry") );
				ra.setAddress( params.get("address") );
			} else {
				ra.setIdcard1( params.get("idcard21") );
				ra.setIdcard2( params.get("idcard22") );
				ra.setCompany1( params.get("company1") );
				ra.setCompany( params.get("company") );
				ra.setLicenseDueDate( params.get("licenseduedate") );
				ra.setRegistrationNo( params.get("registrationno") );
				ra.setAddress( params.get("address2") );
			}
			ra.setMobile( params.get("mobile") );
			ra.setSubmitTime( new Date() );
			ra.setAuthState(1);

			userService.saveRealAuthent(ra);

			return JsonResult.ok(1);
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}

	/**
	 * 实力认证页
	 */
	@GetMapping("abilityauth")
	public ModelAndView abilityAuth(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		User user = getCurrentUser(request);

		mv.addObject("user", user);

		
		mv.setViewName("user/designer/abilityauth");
		return mv;
	}

	/**
	 * 实力认证提交
	 * 
	 * @param params 表单参数
	 * @return
	 */
	@UserOperate(UserAction.USER_ABILITY_AUTH)
	@PostMapping("abilityauth")
	@ResponseBody
	public JsonResult<Integer> abilityAuthPost(
			@RequestParam Map<String, String> params,
			HttpServletRequest request) {
		try {
			User user = getCurrentUser(request);

			AbilityAuthent ra = new AbilityAuthent();
			ra.setUserId( user.getId() );
			ra.setLink( params.get("link") );
			ra.setDescribe( params.get("describe") );
			ra.setSubmitTime( new Date() );
			ra.setAuthState( 1 );

			userService.saveAbilityAuthent(ra);

			return JsonResult.ok(1);
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}

	/**
	 * 任务列表页
	 * 
	 * @param params
	 * @return
	 */
	@GetMapping("tasklist")
    public ModelAndView tasklist(
			@RequestParam Map<String, String> params,
			HttpServletRequest request){

		ModelAndView mv = new ModelAndView();

		User user = getCurrentUser(request);
		mv.addObject("user", new UserVO(user));

		int authState = 0;
		if (user.getAbilityGrade() > 0) { // 实力认证已通过
			authState = 2;  // 可以交易
		} else {
			if (user.getRealAuthState() == 1) {
				authState = 2;  // 已认证
			} else {
				RealAuthent realAuth = userService.getRealAuthentByUser(user.getId());
				if (realAuth != null) {
					authState = 1; // 已提交未审核
				}
			}
		}
		mv.addObject("authState", authState);

		ProjectCondition cond = new ProjectCondition();
		cond.setUserId( user.getId() );
		cond.setState( -1 );

		int page = getInt(params.get("page"), 1);
		int pageSize = 10;
		PageList<Project> pglist = projectService.findProjectList(cond, page, pageSize);
		PagerInfo pager = new PagerInfo(page, pageSize);
		pager.setTotalCount(pglist.getTotal());
		pager.setBaseUrl("/user/designer/tasklist");

		List<ProjectVO2> volist = pglist.getList().stream()
			.map((project)->{
				TaskInfo task = taskService.getTaskInfo(project.getTaskId());
				ProjectVO2 vo = new ProjectVO2(project);
				vo.setTask( task );
				vo.setEmployer( userService.getUser(task.getUserId()) );
				vo.setFileList( projectService.getProjectFileList(project.getId() ));
                vo.setSelectedUserList( projectService.getSelectedProposalsUserIdList(project.getTaskId()) ); // 读取中标方案
				return vo;
			}).collect(Collectors.toList());
		mv.addObject("projectList", volist);
		mv.addObject("pager", pager);
		mv.addObject("cond", cond);

		mv.setViewName("user/designer/tasklist");
		return mv;
	}

	/**
     * 雇佣任务接受
     * 
     * @param taskId 任务ID
     * @return
     */
    @PostMapping("accepttask")
    @ResponseBody
    public JsonResult<Integer> acceptTask(
            @RequestParam("taskid")int taskId,
            HttpServletRequest request) {
        try {
            User user = getCurrentUser(request);

            if (user.getRealAuthState() == 0) {
                return JsonResult.fail(-1, "实名认证后才能抢标。");
            }
             
            int ret = taskService.acceptEmployTask(taskId, user.getId());
            return JsonResult.ok(ret);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

	/**
     * 雇佣任务拒绝
     * 
     * @param taskId 任务ID
     * @return
     */
    @PostMapping("rejecttask")
    @ResponseBody
    public JsonResult<Integer> rejectTask(
            @RequestParam("taskid")int taskId,
            HttpServletRequest request) {
        try {
            User user = getCurrentUser(request);

            int ret = taskService.rejectEmployTask(taskId, user.getId());
            return JsonResult.ok(ret);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

	/**
	 * 项目（任务提案）修改页
	 * 
	 * @param projectId 项目ID
	 * @return
	 */
	@GetMapping("changeproposal")
	public ModelAndView changeProposal(
			@RequestParam("projectid")int projectId,
			HttpServletRequest request) {
		Project project = projectService.getProject(projectId);
		if (project == null) {
            throw new ForbiddenException("无效的参数。");
		}

		User user = getCurrentUser(request);
		if (project.getUserId() != user.getId()) {
			throw new ForbiddenException("无效的参数。");
		}

		ProposalUploadContext ctx = ProposalUploadContext.getInstance(user.getId(), request);
		ctx.init();

		List<ProjectFile> fileList = projectService.getProjectFileList(projectId);
		List<ProjectFileVO> voList = fileList.stream()
			.map((file)->{
				ProjectFileVO vo = new ProjectFileVO(file);
				ctx.addFile(vo.getKey(), file.getUrl());
				return vo;
			}).collect(Collectors.toList());

		ModelAndView mv = new ModelAndView();
		mv.addObject("project", project);
		mv.addObject("fileList", voList);

		mv.setViewName("user/designer/changeproposal");

		return mv;
	}
	/**
	 * 账户明细页
	 * 
	 * @param params
	 * @return
	 */
	@GetMapping("accountdetail")
    public ModelAndView accountDetail(
			@RequestParam Map<String, String> params,
			HttpServletRequest request){

		ModelAndView mv = new ModelAndView();

		User user = getCurrentUser(request);
		mv.addObject("user", new UserVO(user));

		int authState = 0;
		if (user.getAbilityGrade() > 0) { // 实力认证已通过
			authState = 2;  // 可以交易
		} else {
			if (user.getRealAuthState() == 1) {
				authState = 2;  // 已认证
			} else {
				RealAuthent realAuth = userService.getRealAuthentByUser(user.getId());
				if (realAuth != null) {
					authState = 1; // 已提交未审核
				}
			}
		}
		mv.addObject("authState", authState);

		PaymentCondition cond = new PaymentCondition();
		cond.setUserId( user.getId() );

		int page = getInt(params.get("page"), 1);
		int pageSize = 6;
        PageList<Payment> pgList = financeService.findPaymentList(cond, page, pageSize);
                
		PagerInfo info = new PagerInfo(page, pageSize);
		info.setTotalCount(pgList.getTotal());
		info.setBaseUrl("/user/designer/accountdetail");

		List<PaymentVO> voList = pgList.getList().stream()
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

		mv.setViewName("user/designer/accountdetail");
		return mv;
	}

	/**
	 * 提现记录页
	 * 
	 * @param params
	 * @return
	 */
	@GetMapping("withdrawallist")
    public ModelAndView withdrawalList(
			@RequestParam Map<String, String> params,
			HttpServletRequest request){

		ModelAndView mv = new ModelAndView();

		User user = getCurrentUser(request);
		mv.addObject("user", new UserVO(user));
		mv.addObject("authState", getAuthState(user));

 		WithdrawalCondition cond = new WithdrawalCondition();
		cond.setUserId( user.getId() );

		int page = getInt(params.get("page"), 0);
		int pageSize = 6;

        PageList<Withdrawal> pgList = financeService.findWithdrawalList(cond, page, pageSize);
		PagerInfo info = new PagerInfo(page, pageSize);
		info.setTotalCount(pgList.getTotal());
		info.setBaseUrl("/user/designer/withdrawlist");

		List<WithdrawalVO> voList = pgList.getList().stream()
				.map((wd)->{
					WithdrawalVO vo = new WithdrawalVO(wd);
					vo.setDetail(wd.getBankName() +" （"+ financeService.getAccountNo(wd.getBankAccNo()) +"） "+ wd.getBankAccName() +" "+ wd.getAmount() + " 元" );
					vo.setStateText( financeService.getWithdrawalStateText(wd.getState()) );
					return vo;
				}).collect(Collectors.toList());
		mv.addObject("withdrawalList", voList);
		mv.addObject("pager", info);

		mv.setViewName("user/designer/withdrawallist");
		return mv;
	}

	private int getAuthState(User user) {
		int authState = 0;
		if (user.getAbilityGrade() > 0) { // 实力认证已通过
			authState = 2;  // 可以交易
		} else {
			if (user.getRealAuthState() == 1) {
				authState = 2;  // 已认证
			} else {
				RealAuthent realAuth = userService.getRealAuthentByUser(user.getId());
				if (realAuth != null) {
					authState = 1; // 已提交未审核
				}
			}
		}
		return authState;
	}

	/**
	 * 作品页
	 * 
	 * @return
	 */
	@GetMapping("zuopin")
    public ModelAndView zuopin(HttpServletRequest request){

		ModelAndView mv = new ModelAndView();

		User user = getCurrentUser(request);
		mv.addObject("user", new UserVO(user));
		
		int totalLike = zuopinService.getZuoPinTotalLikeByUser(user.getId());
		mv.addObject("totalLike", totalLike);

		List<Category> cateList = commonService.getCategoryList(0);
		mv.addObject("cateList", cateList);

		ZuopinCondition cond = new ZuopinCondition();
		cond.setUserId( user.getId() );
		PageList<ZuoPinInfo> pglist = zuopinService.findZuoPinInfoList(cond, 1, 100);
		mv.addObject("zuopinList", pglist.getList());

		mv.setViewName("user/designer/zuopin");
		return mv;
	}

	/**
	 * 作品上传初始化
	 * 
	 * @return
	 */
	@PostMapping("zuopinadd_init")
	@ResponseBody
	public String zuopinadd_init(HttpServletRequest request) {
		try {
			User user = getCurrentUser(request);
			ZuopinUploadContext.getInstance(user.getId(), request).init();

			getLog().info("用户{} 作品上传-初始化。", user.getId());
			return "{ }";
		} catch (Exception ex) {
			handleError(request, ex);
			return "{ }";
		}
	}

	/**
	 * 作品上传的图片删除
	 * 
	 * @param key 文件Key
	 * @return
	 */
	@PostMapping("zuopinadd_delpic")
	@ResponseBody
	public JsonResult<Integer> zuopinadd_delpic(
			@RequestParam("key")String key,
			HttpServletRequest request) {
		try {
			User user = getCurrentUser(request);
			ZuopinUploadContext.getInstance(user.getId(), request).delImage(key);

			getLog().info("用户{} 作品上传-删除图片。", user.getId());
			return JsonResult.ok(1);
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}

	/**
	 * 作品提交
	 * 
	 * @param params 表单提交
	 * @return
	 */
	@UserOperate(UserAction.ZUOPIN_ADD)
	@PostMapping("zuopinadd_submit")
	@ResponseBody
	public JsonResult<Integer> zuopinadd_submit(
			@RequestParam Map<String, String> params,
			HttpServletRequest request) {
		try {
			User user = getCurrentUser(request);

			ZuopinUploadContext ud = ZuopinUploadContext.getInstance(user.getId(), request);
						
			ZuoPinInfo zpInfo = null;
			if (ud.getZuopinId() == 0) { //new
				zpInfo = new ZuoPinInfo();
			} else {
				zpInfo = zuopinService.getZuoPinInfo(ud.getZuopinId());
			}

			int cateId = getInt(params.get("cate2"), 0);
			if (cateId == 0) {
				cateId = getInt(params.get("cate1"), 0);
			}

			zpInfo.setTitle( params.get("title") );
			zpInfo.setCateId( cateId );
			zpInfo.setPrice( new BigDecimal(params.get("price")) );
			zpInfo.setDescription( params.get("descr2") );
			zpInfo.setUserId( user.getId() );
			zpInfo.setCover( ud.getCover() );
			zpInfo.setLikeCount( 0 );
			zpInfo.setDealCount( 0 );
			zpInfo.setAddTime( new Date() );

			String[] keys = request.getParameterValues("key");
			String[] descrs = request.getParameterValues("descr");

			List<ZuoPinImage> imgList = new ArrayList<>();
			for (int i = 0; i < keys.length; i++) {
				String key = keys[i];
				if (!StrUtil.isEmpty(key)) {
					ZuoPinImage zpImg = new ZuoPinImage();
					zpImg.setUrl( ud.getImage(key) );
					zpImg.setDescription( descrs[i] );
					imgList.add(zpImg);
				}
			}
			zpInfo.setImageList( imgList );

			int n = zuopinService.saveZuoPinInfo(zpInfo);

			ud.init();

			getLog().info("用户{} 作品上传-提交，作品ID：{}", user.getId(), zpInfo.getId());

			return JsonResult.ok(n);
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}

	/**
	 * 作品编辑页
	 * 
	 * @param id 作品ID
	 * @return
	 */
	@GetMapping("zuopinedit")
	public ModelAndView zuopinEdit(@RequestParam("id")int id,
			HttpServletRequest request) {
		User user = getCurrentUser(request);

		ZuoPinInfo zuopin = zuopinService.getZuoPinInfo(id);

		if (zuopin == null || zuopin.getUserId() != user.getId() ) {
			throw new ForbiddenException("无效的参数。");
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("zuopin", zuopin);

		ZuopinUploadContext ud = ZuopinUploadContext.getInstance(user.getId(), request);
		ud.setCover(zuopin.getCover());

		List<ZuoPinImage> imageList = zuopinService.getZuoPinImageList(id);
		List<ZuoPinImageVO> voList = imageList.stream()
			.map((image)->{
				ZuoPinImageVO vo = new ZuoPinImageVO(image);
				ud.addImage(vo.getKey(), image.getUrl());
				return vo;
			}).collect(Collectors.toList());
		mv.addObject("imageList", voList);

		List<Category> cateList = commonService.getCategoryList(0);
		int cate1 = (zuopin.getCateId() / 1000) * 1000;
		List<Category> cateList2 = commonService.getCategoryListByParent(cate1);
		int cate2 = (cate1 != zuopin.getCateId()) ? zuopin.getCateId() : 0;
		mv.addObject("cateList", cateList);
		mv.addObject("cateList2", cateList2);
		mv.addObject("cate1", cate1);
		mv.addObject("cate2", cate2);

		mv.setViewName("user/designer/zuopinedit");
		return mv;
	}

	/**
	 * 作品编辑提交
	 * 
	 * @param params 表单参数
	 * @return
	 */
	@UserOperate(UserAction.ZUOPIN_EDIT)
	@PostMapping("zuopinedit")
	@ResponseBody
	public JsonResult<Integer> zuopinEditPost(@RequestParam Map<String, String> params,
			HttpServletRequest request) {

		try {
			User user = getCurrentUser(request);

			int id = getInt(params.get("id"), 0);

			ZuopinUploadContext ud = ZuopinUploadContext.getInstance(user.getId(), request);

			ZuoPinInfo zpInfo = zuopinService.getZuoPinInfo(id);
			zpInfo.setTitle( params.get("title") );
			zpInfo.setCateId( getInt(params.get("cate2"),0) );
			zpInfo.setPrice( new BigDecimal(params.get("price")) );
			zpInfo.setDescription( params.get("descr2") );
			zpInfo.setCover( ud.getCover() );
			//zpInfo.setAddTime( new Date() );

			String[] keys = request.getParameterValues("key");
			String[] descrs = request.getParameterValues("descr");

			List<ZuoPinImage> imgList = new ArrayList<>();
			for (int i = 0; i < keys.length; i++) {
				String key = keys[i];
				if (!StrUtil.isEmpty(key)) {
					ZuoPinImage zpImg = new ZuoPinImage();
					zpImg.setUrl( ud.getImage(key) );
					zpImg.setDescription( descrs[i] );
					imgList.add(zpImg);
				}
			}
			zpInfo.setImageList( imgList );

			zuopinService.updateZuoPinInfo(zpInfo);

			ud.init();

			return JsonResult.ok(1);
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}

	}
}
