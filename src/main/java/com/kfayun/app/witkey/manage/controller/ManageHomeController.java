/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.kfayun.app.witkey.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kfayun.app.witkey.*;
import com.kfayun.app.witkey.util.*;
import com.kfayun.app.witkey.service.*;
import com.kfayun.app.witkey.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ManageHomeController
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
@Controller
@RequestMapping("/manage")
public class ManageHomeController extends ManageBaseController {
	
	@Autowired
	private AppConfig config;
	@Autowired
	private AdminService adminService;
	@Autowired
	private SysService sysService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private ZuopinService zuopinService;
	@Autowired
	private UserService userService;

	/**
	 * 管理端首页
	 * 
	 * @param time
	 * @return
	 */
	@GetMapping("main")
	public ModelAndView main(
			@RequestParam(value="time", required=false, defaultValue="")String time) {

		ModelAndView mv = new ModelAndView("manage/main");

		int taskTotal = taskService.getTaskApprovedCount();
		int zuopinTotal = zuopinService.getZuoPinTotal();
		int designerTotal = userService.getUserCount(1);
		int employerTotal = userService.getUserCount(2);
		mv.addObject("taskTotal", taskTotal);
		mv.addObject("zuopinTotal", zuopinTotal);
		mv.addObject("designerTotal", designerTotal);
		mv.addObject("employerTotal", employerTotal);

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		if ("week".equalsIgnoreCase(time)) {
			// 3个月数据
			cal.add(Calendar.MONTH, -3);
		} else if ("month".equalsIgnoreCase(time)) {
			// 12个月数据
			cal.add(Calendar.MONTH, -12);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		} else {
			// 30天数据
			time = "day";
			cal.add(Calendar.DAY_OF_YEAR, -30);
		}
		List<Map<String, Object>> list = sysService.getViewStatisListForTime(cal.getTime(), time);
		
		Collections.reverse(list);
		mv.addObject("statislist", list);
		mv.addObject("time", time);

		return mv;
	}

	/**
	 * 管理端首页图表数据
	 * 
	 * @param time
	 * @return
	 */
	@GetMapping("viewstatis")
	@ResponseBody
    public JsonResult<Map<String, Object>> getViewStatis(
		@RequestParam(value="time", required=false, defaultValue="")String time) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		if ("week".equalsIgnoreCase(time)) {
			cal.add(Calendar.MONTH, -3);
		} else if ("month".equalsIgnoreCase(time)) {
			cal.add(Calendar.MONTH, -12);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		} else {
			time = "day";
			cal.add(Calendar.DAY_OF_YEAR, -30);
		}
		List<Map<String, Object>> list = sysService.getViewStatisListForTime(cal.getTime(), time);

		List<String> nameList = new ArrayList<>();
		List<Integer> uvList = new ArrayList<>();
		List<Integer> pvList = new ArrayList<>();
		for (Map<String, Object> item : list) {
			if ("day".equalsIgnoreCase(time)) {
				nameList.add(item.get("name").toString().substring(5));
			} else {
				nameList.add(item.get("name").toString()); 
			}
			uvList.add(Integer.parseInt(item.get("uv").toString()));
			pvList.add(Integer.parseInt(item.get("pv").toString()));
		}
		Map<String, Object> data = new HashMap<>();
		data.put("names", nameList);
		data.put("uvValues", uvList);
		data.put("pvValues", pvList);
		return JsonResult.ok(data);
	}

	/**
	 * 关于我们页
	 * 
	 * @return
	 */
	@GetMapping("about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("manage/about");

		mv.addObject("appVersion", config.getAppVersion());

		return mv;
	}

	/**
	 * 登录页
	 * @return
	 */
	@GetMapping("login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("manage/login");

		return mv;
	}

	/**
	 * 登录提交
	 * 
	 * @param params 表单参数
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping("login")
	@ResponseBody
	public JsonResult<String> loginPost(@RequestParam Map<String, String> params,
			HttpServletRequest request,
			HttpServletResponse response) {

		try  {

			String username = params.get("username");
			String password = params.get("password");

			Admin admin = adminService.getAdminByName(username);
			if (admin == null) {
				return JsonResult.fail(-1, "用户不存在。");
			}
			String encPasswd = CryptoUtil.MD5( CryptoUtil.MD5(password) + admin.getPwdSalt() );
			if (!encPasswd.equals(admin.getPasswd())) {
				return JsonResult.fail(-2, "密码错误。");
			}

			Settings settings = sysService.getSettings();
			WebUtil.writeCookie(response, 
				getCookieDomain(settings.getCookieDomain()), 
				"",
				settings.getCookiePrefix() + "_admin", 
				CryptoUtil.encryptDES(settings.getCookieSecret(), ""+admin.getId()),
				0);
			

			return JsonResult.ok("main");

		} catch (Exception ex) {
			handleError(request, ex);
			return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}

	/**
	 * 管理员退出登录处理
	 */
	@RequestMapping("logout")
    public void logout(
			HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		Settings settings = (Settings)request.getAttribute("settings");
		WebUtil.deleteCookie(
			response, 
			getCookieDomain(settings.getCookieDomain()), 
			"", 
			settings.getCookiePrefix() + "_manage");
		response.sendRedirect(config.getContextPath() + "/manage/login");
    }

	private String getCookieDomain(String domain) {
		if (config.getProfile().equalsIgnoreCase("prod")) {
			return domain;
		}
		return ""; // use localhost or ip.
	}

	/**
	 * 修改密码页
	 * @return
	 */
	@GetMapping("changepwd")
	public ModelAndView changePwd() {
		ModelAndView mv = new ModelAndView("manage/changepwd");

		return mv;
	}

	/**
	 * 修改密码提交
	 * 
	 * @param params 表单参数
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping("changepwd")
	@ResponseBody
	public JsonResult<Integer> changePwdPost(@RequestParam Map<String, String> params,
			HttpServletRequest request) {

		try  {

			String oldPasswd = params.get("oldpasswd");
			String newPasswd = params.get("newpasswd");
			String newPasswd2 = params.get("newpasswd2");

			if (StrUtil.isEmpty(newPasswd)) {
				return JsonResult.fail(-1, "密码不能为空。");
			}
			if (!newPasswd.equals(newPasswd2)) {
				return JsonResult.fail(-1, "密码确认不一致。");
			}
			if (newPasswd.length() < 8) {
				return JsonResult.fail(-1, "密码不能少于8位字符。");
			}

			Admin admin = getCurrentAdmin(request);

			String encPasswd = CryptoUtil.MD5( CryptoUtil.MD5(oldPasswd) + admin.getPwdSalt() );
			if (!encPasswd.equals(admin.getPasswd())) {
				return JsonResult.fail(-2, "原密码错误。");
			}

			String newEncPasswd = CryptoUtil.MD5( CryptoUtil.MD5(newPasswd) + admin.getPwdSalt() );
			admin.setPasswd(newEncPasswd);
			int n = adminService.updateAdmin(admin);

			return JsonResult.ok(n);

		} catch (Exception ex) {
			handleError(request, ex);
			return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}

}
