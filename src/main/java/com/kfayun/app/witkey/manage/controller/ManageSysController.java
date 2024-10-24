/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage.controller;

import java.util.Date;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
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
import com.kfayun.app.witkey.condition.VerifyCodeCondition;
import com.kfayun.app.witkey.manage.aop.*;
import com.kfayun.app.witkey.util.*;
import com.kfayun.app.witkey.service.*;
import com.kfayun.app.witkey.model.*;
import com.kfayun.app.witkey.manage.vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统管理Controller
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
@RequestMapping("/manage/sys")
@Controller
public class ManageSysController extends ManageBaseController {

    @Autowired
    private SysService sysService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private VerifyService verifyService;

    /**
     * 一般设置页
     * 
     * @return
     */
    @ManageOperate(ManageAction.SYS_GENERALSETTINGS_VIEW)
    @GetMapping("settings")
    public ModelAndView settings() {

        ModelAndView mv = new ModelAndView();

        List<SettingItem> list = sysService.getSettingItemList()
				.stream()
				.filter((item)->"general".equalsIgnoreCase(item.getScope()))
				.collect(Collectors.toList());
        mv.addObject("list", list);

        List<String> imglist = list.stream()
					.filter((item)->item.getType().equalsIgnoreCase("image"))
					.map(SettingItem::getKey)
					.collect(Collectors.toList());
        mv.addObject("imglist", imglist);

        mv.setViewName("manage/sys/settings");
        return mv;
    }

    /**
     * 一般设置修改提交
     * 
     * @return
     */
    @ManageOperate(ManageAction.SYS_GENERALSETTINGS_UPDATE)
    @PostMapping( "settings")
	@ResponseBody
	public JsonResult<Integer> settingsPost(
			@RequestParam Map<String, String> data,
            HttpServletRequest request) {

        try {
            int n = sysService.updateSettings(data);

            return JsonResult.ok(n);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
	}

    /**
     * 支付设置页
     * 
     * @return
     */
    @ManageOperate(ManageAction.SYS_PAYSETTINGS_VIEW)
    @GetMapping("paysettings")
    public ModelAndView paysettings() {

        ModelAndView mv = new ModelAndView();

        List<SettingItem> list = sysService.getSettingItemList()
				.stream()
				.filter((item)->"pay".equalsIgnoreCase(item.getScope()))
				.collect(Collectors.toList());
        mv.addObject("list", list);

        mv.setViewName("manage/sys/paysettings");
        return mv;
    }

    /**
     * 修改设置修改提交
     * 
     * @return
     */
    @ManageOperate(ManageAction.SYS_PAYSETTINGS_UPDATE)
    @PostMapping( "paysettings")
	@ResponseBody
	public JsonResult<Integer> paysettingsPost(
			@RequestParam Map<String, String> data,
            HttpServletRequest request) {
        try {
            int n = sysService.updateSettings(data);

            return JsonResult.ok(n);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
	}

    /**
     * 操作日志页
     * 
     * @param pageNo 页数
     * @Param pageSize 页记录数
     * @return
     */
    @ManageOperate(ManageAction.SYS_ACTIONLOG_VIEW)
	@GetMapping(value = "actionlist")
    public ModelAndView actionList(
			@RequestParam(value = "pageno", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pagesize", defaultValue = "15", required = false) int pageSize) {
        ModelAndView model = new ModelAndView();

        PageList<ActionLog> plist = sysService.findActionLogList("", "", pageNo, pageSize);
        
        PagerInfo pager = new PagerInfo(pageNo, pageSize);
        pager.setTotalCount(plist.getTotal());
        pager.setBaseUrl("actionlist");
        
        model.addObject("logList", plist.getList());
        model.addObject("pager", pager);
        
        model.setViewName("manage/sys/actionlist");

        return model;
    }

	@GetMapping(value="actiondetail")
	public ModelAndView actionDetail(@RequestParam("id")int id) {

		ActionLog log = sysService.getActionLog(id);

		ModelAndView mv = new ModelAndView();
		mv.addObject("log", log);
		mv.setViewName("manage/sys/actiondetail");
		return mv;
	}

    /**
     * 管理员列表页
     * 
     * @return
     */
    @ManageOperate(ManageAction.SYS_ADMIN_VIEW)
	@GetMapping(value = "adminlist")
    public ModelAndView adminList() {
        ModelAndView model = new ModelAndView();

        List<Admin> list = adminService.getAdminList();
        
        model.addObject("adminList", list);
        
        model.setViewName("manage/sys/adminlist");

        return model;
    }

    /**
     * 管理员添加页
     * 
     * @return
     */
    @ManageOperate(ManageAction.SYS_ADMIN_ADD)
    @GetMapping("adminadd")
    public ModelAndView adminAdd() {

        ModelAndView mv = new ModelAndView();

        mv.addObject("actionMap", ManageAction.actionGroupMap());

        mv.setViewName("manage/sys/adminadd");
        return mv;
    }

     /**
     * 管理员添加提交
     * 
     * @return
     */
    @ManageOperate(ManageAction.SYS_ADMIN_ADD)
    @PostMapping("adminadd")
    @ResponseBody
    public JsonResult<Integer> adminAddPost(@RequestParam Map<String, String> params,
            HttpServletRequest request) {

        try {
            String password = params.get("password");
            String password2 = params.get("password2");
            if (StrUtil.isEmpty(password)) {
                return JsonResult.fail(-1, "密码不能为空");
            }
            if (!password.equals(password2)) {
                return JsonResult.fail(-1, "密码确认不一致");
            }
            if (password.length() < 8) {
                return JsonResult.fail(-1, "密码字符至少8位");
            }

            Admin admin = new Admin();
            admin.setName(params.get("name"));
            admin.setDescription( params.get("description") );
            admin.setPwdSalt( UUID.randomUUID().toString().replace("-", "").substring(8, 20).toLowerCase() );
            admin.setPasswd( CryptoUtil.MD5(CryptoUtil.MD5(password) + admin.getPwdSalt()) );
            admin.setPermissions( params.get("perm") );
            admin.setAddTime(new Date());

            adminService.saveAdmin(admin);
            return JsonResult.ok(0);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * 管理员编辑页
     * 
     * @param id 管理员ID
     * @return
     */
    @ManageOperate(ManageAction.SYS_ADMIN_EDIT)
    @GetMapping("adminedit")
    public ModelAndView adminEdit(@RequestParam("id")int id) {

        ModelAndView mv = new ModelAndView();

        Admin admin = adminService.getAdmin(id);
            if (admin ==  null) {
            throw new ForbiddenException("无效的参数。");
        }

        mv.addObject("admin", admin);

        String permissions = !StrUtil.isEmpty(admin.getPermissions()) ? admin.getPermissions() : "";
        List<String> permissionList = Arrays.asList(permissions.split(","));
       
        Map<String, List<ManageActionVO>> actionMap = ManageAction.actionGroupMap();
        for (List<ManageActionVO> list : actionMap.values()) {
            list.forEach((action)->{
                action.setGrant(permissionList.contains(action.getName()));
            });
        }
        mv.addObject("actionMap", actionMap);

        mv.setViewName("manage/sys/adminedit");
        return mv;
    }

    /**
     * 管理员编辑提交
     * 
     * @return
     */
    @ManageOperate(ManageAction.SYS_ADMIN_EDIT)
    @PostMapping("adminedit")
    @ResponseBody
    public JsonResult<Integer> adminEditPost(@RequestParam Map<String, String> params,
            HttpServletRequest request) {

        try {
            int id = getInt(params.get("id"), 0);
            Admin admin = adminService.getAdmin(id);
            if (admin ==  null) {
                return JsonResult.fail(-1, "无效的参数。");
            }

            String password = params.get("password");
            String password2 = params.get("password2");
            if (!StrUtil.isEmpty(password) || !StrUtil.isEmpty(password2)) {
                // 要修改密码
                if (!password.equals(password2)) {
                    return JsonResult.fail(-1, "密码确认不一致");
                }
                if (password.length() < 8) {
                    return JsonResult.fail(-1, "密码字符至少8位");
                }
                admin.setPasswd( CryptoUtil.MD5(CryptoUtil.MD5(password) + admin.getPwdSalt()) );
            }

            admin.setDescription( params.get("description") );
            admin.setState( getInt(params.get("state"), 0));
            admin.setPermissions( params.get("perm") );

            int n = adminService.updateAdmin(admin);
            return JsonResult.ok(n);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * 管理员删除提交
     * 
     * @param id 管理员ID
     * @return
     */
    @ManageOperate(ManageAction.SYS_ADMIN_DELETE)
    @PostMapping("admindelete")
    @ResponseBody
    public JsonResult<Integer> adminDelete(@RequestParam("id")int id,
            HttpServletRequest request) {

        try {
            int n = adminService.deleteAdmin(id);
            return JsonResult.ok(n);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * 校验码列表页
     * 
     * @return
     */
    @ManageOperate(ManageAction.SYS_VERIFYCODE_VIEW)
	@GetMapping(value = "vcodelist")
    public ModelAndView vcodeList(
        @RequestParam(value = "sendto", defaultValue = "", required = false) String sendTo,
        @RequestParam(value = "pageno", defaultValue = "1", required = false) int pageNo) {
        ModelAndView model = new ModelAndView();

        VerifyCodeCondition cond = new VerifyCodeCondition();
        cond.setSendTo(sendTo);

        int pageSize = 15;
        PageList<VerifyCode> pglist = verifyService.findVerifyCodeList(cond, pageNo, pageSize);
        PagerInfo pager = new PagerInfo(pageNo, pageSize);
        pager.setTotalCount(pglist.getTotal());
        pager.setBaseUrl("vcodelist");

        model.addObject("vcodeList", pglist.getList());
        model.addObject("pager", pager);
        model.addObject("cond", cond);
        
        model.setViewName("manage/sys/vcodelist");

        return model;
    }

}
