/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kfayun.app.witkey.*;
import com.kfayun.app.witkey.condition.*;
import com.kfayun.app.witkey.manage.aop.*;
import com.kfayun.app.witkey.util.*;
import com.kfayun.app.witkey.service.*;
import com.kfayun.app.witkey.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户管理Controller
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
@RequestMapping("/manage/usr")
@Controller
public class ManageUserController extends ManageBaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private ZuopinService zuopinService;

    /**
     * 设计师列表页
     * 
     * @param pageNo
     * @return
     */
    @GetMapping("designerlist")
    public ModelAndView designerList(
            @RequestParam(value="mobile", required=false, defaultValue="")String mobile,
            @RequestParam(value="username", required=false, defaultValue="")String username,
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo) {

        ModelAndView mv = new ModelAndView();

        UserCondition cond = new UserCondition();
        cond.setClazz( 1 );
        cond.setMobile( mobile );
        cond.setUsername( username );

        int pageSize = 15;
        PageList<User> pglist = userService.findUserList(cond, pageNo, pageSize);
        PagerInfo pager = new PagerInfo(pageNo, pageSize);
        pager.setTotalCount(pglist.getTotal());
        pager.setBaseUrl("designerlist" + cond.getQueryString());

        mv.addObject("userList", pglist.getList());
        mv.addObject("pager", pager);
        mv.addObject("cond", cond);

        mv.setViewName("manage/usr/designerlist");
        return mv;
    }

    /**
     * 设置设计师排序号
     * 
     * @param id
     * @param location
     * @param request
     * @return
     */
    @PostMapping("setdesignerlocation")
    @ResponseBody
    public JsonResult<Integer> setDesignerLocation(
        @RequestParam("id")int id, 
        @RequestParam("location")int location,
        HttpServletRequest request) {

        try {
            int topNo = 0;
            if (location == 0) {
                // do nothing.
            } else if (location > 0) {
                int maxNo = userService.getMaxTopNo();
                if (maxNo < 0) {
                    maxNo = 0;
                }
                topNo = maxNo + 10;
            } else if (location < 0) {
                int minNo = userService.getMinTopNo();
                if (minNo > 0) {
                    minNo = 0;
                }
                topNo = minNo - 10;
            }

            int n = userService.updateUserTopNo(id, topNo);
            return JsonResult.ok(n);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * 雇主列表页
     * 
     * @param pageNo
     * @return
     */
    @GetMapping("employerlist")
    public ModelAndView employerList(
            @RequestParam(value="mobile", required=false, defaultValue="")String mobile,
            @RequestParam(value="username", required=false, defaultValue="")String username,
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo) {

        ModelAndView mv = new ModelAndView();

        UserCondition cond = new UserCondition();
        cond.setClazz( 2 );
        cond.setMobile( mobile );
        cond.setUsername( username );

        int pageSize = 15;
        PageList<User> pglist = userService.findUserList(cond, pageNo, pageSize);
        PagerInfo pager = new PagerInfo(pageNo, pageSize);
        pager.setTotalCount(pglist.getTotal());
        pager.setBaseUrl("employerlist" + cond.getQueryString());

        mv.addObject("userList", pglist.getList());
        mv.addObject("pager", pager);
        mv.addObject("cond", cond);

        mv.setViewName("manage/usr/employerlist");
        return mv;
    }

    /**
     * 实名认证记录页
     * 
     * @param pageNo
     * @return
     */
    @GetMapping("realauthentlist")
    public ModelAndView realAuthentList(
            @RequestParam(value="username", required=false, defaultValue="")String username,
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo) {

        ModelAndView mv = new ModelAndView();

        RealAuthCondition cond = new RealAuthCondition();
        cond.setUsername(username);
        
        int pageSize = 15;
        PageList<RealAuthent> pglist = userService.findRealAuthentList(cond, pageNo, pageSize);
                
        PagerInfo pager = new PagerInfo(pageNo, pageSize);
        pager.setTotalCount(pglist.getTotal());
        pager.setBaseUrl("realauthentlist" + cond.getQueryString());

        mv.addObject("authentList", pglist.getList());
        mv.addObject("pager", pager);
        mv.addObject("cond", cond);

        mv.setViewName("manage/usr/realauthentlist");
        return mv;
    }

    /**
     * 实名认证详情页
     * 
     * @param id
     * @return
     */
    @GetMapping("realauthentdetail")
    public ModelAndView realAuthentDetail(
            @RequestParam(value="id")int id) {

        ModelAndView mv = new ModelAndView();

        RealAuthent authent = userService.getRealAuthent(id);
        if (authent ==  null) {
            throw new ForbiddenException("无效的参数。");
        }
        mv.addObject("authent", authent);

        mv.setViewName("manage/usr/realauthentdetail");
        return mv;
    }

    /**
     * 实名认证审核页
     * 
     * @param id
     * @return
     */
    @GetMapping("realauthentaudit")
    public ModelAndView realAuthentAudit(
            @RequestParam(value="id")int id) {

        ModelAndView mv = new ModelAndView();

        RealAuthent authent = userService.getRealAuthent(id);
        if (authent ==  null) {
            throw new ForbiddenException("无效的参数。");
        }
        mv.addObject("authent", authent);

        mv.setViewName("manage/usr/realauthentaudit");
        return mv;
    }

    /**
     * 实名认证审核提交
     * 
     * @param id
     * @param state
     * @param remark
     * @return
     */
    @ManageOperate(ManageAction.USR_REALAUTHENT_AUDIT)
    @PostMapping("realauthentreaudit")
    @ResponseBody
    public JsonResult<Integer> realAuthentAudit(
            @RequestParam(value="id")int id,
            @RequestParam(value="state")int state,
            @RequestParam(value="remark", required = false, defaultValue = "")String remark,
            HttpServletRequest request) {
        try {
            RealAuthent authent = userService.getRealAuthent(id);
            if (authent ==  null) {
                return JsonResult.fail(-1, "无效的参数。");
            }

            Admin admin = getCurrentAdmin(request);

            authent.setAuthState( state == 1 ? 2 : 3 );
            authent.setAuthTime( new Date() );
            authent.setAuthOperator( admin.getName() );
            authent.setAuthMemo( remark );

            int n = userService.updateRealAuthent(authent);
            return JsonResult.ok(n);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * 实力认证记录页
     * 
     * @param pageNo
     * @return
     */
    @GetMapping("abilityauthentlist")
    public ModelAndView abilityAuthentList(
             @RequestParam(value="username", required=false, defaultValue="")String username,
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo) {

        ModelAndView mv = new ModelAndView();

        AbilityAuthCondition cond = new AbilityAuthCondition();
        cond.setUsername(username);

        int pageSize = 15;
        PageList<AbilityAuthent> pglist = userService.findAbilityAuthentList(cond, pageNo, pageSize);
                
        PagerInfo pager = new PagerInfo(pageNo, pageSize);
        pager.setTotalCount(pglist.getTotal());
        pager.setBaseUrl("abilityauthentlist" + cond.getQueryString() );

        mv.addObject("authentList", pglist.getList());
        mv.addObject("pager", pager);
        mv.addObject("cond", cond);

        mv.setViewName("manage/usr/abilityauthentlist");
        return mv;
    }

    /**
     * 实力认证详情页
     * 
     * @param id
     * @return
     */
    @GetMapping("abilityauthentdetail")
    public ModelAndView abilityAuthenDetail(
            @RequestParam(value="id")int id) {

        ModelAndView mv = new ModelAndView();

        AbilityAuthent authent = userService.getAbilityAuthent(id);
        if (authent ==  null) {
            throw new ForbiddenException("无效的参数。");
        }
        mv.addObject("authent", authent);

        mv.setViewName("manage/usr/abilityauthentdetail");
        return mv;
    }

    /**
     * 实力认证审核页
     * 
     * @param id
     * @return
     */
    @GetMapping("abilityauthentaudit")
    public ModelAndView abilityAuthentAudit(
            @RequestParam(value="id")int id) {

        ModelAndView mv = new ModelAndView();

        AbilityAuthent authent = userService.getAbilityAuthent(id);
        if (authent ==  null) {
            throw new ForbiddenException("无效的参数。");
        }
        mv.addObject("authent", authent);

        mv.setViewName("manage/usr/abilityauthentaudit");
        return mv;
    }

    /**
     * 实力认证审核提交
     * 
     * @param id
     * @param state
     * @param remark
     * @return
     */
    @ManageOperate(ManageAction.USR_ABILITYAUTHENT_AUDIT)
    @PostMapping("abilityauthentreaudit")
    @ResponseBody
    public JsonResult<Integer> abilityAuthentAudit(
            @RequestParam(value="id")int id,
            @RequestParam(value="grade")int grade,
            @RequestParam(value="state")int state,
            @RequestParam(value="remark", required = false, defaultValue = "")String remark,
            HttpServletRequest request) {
        try {
            AbilityAuthent authent = userService.getAbilityAuthent(id);
            if (authent ==  null) {
                return JsonResult.fail(-1, "无效的参数。");
            }

            Admin admin = getCurrentAdmin(request);

            authent.setAuthGrade( grade );
            authent.setAuthState( state == 1 ? 2 : 3);
            authent.setAuthTime( new Date() );
            authent.setAuthOperator( admin.getName() );
            authent.setAuthMemo( remark );

            int n = userService.updateAbilityAuthent(authent);

            return JsonResult.ok(n);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * 作品列表页
     * 
     * @param keyword
     * @param pageNo
     * @return
     */
    @GetMapping("zuopinlist")
    public ModelAndView zuopinList(
        @RequestParam(value="keyword", required=false, defaultValue="")String keyword,
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo) {

        ModelAndView mv = new ModelAndView();

        ZuopinCondition cond = new ZuopinCondition();
        cond.setKeyword(keyword);

        int pageSize = 10;
        PageList<ZuoPinInfo> pglist = zuopinService.findZuoPinInfoList(cond, pageNo, pageSize);

        PagerInfo pager = new PagerInfo(pageNo, 10);
        pager.setTotalCount(pglist.getTotal());
        pager.setBaseUrl("zuopinlist" + cond.getQueryString());
        
        mv.addObject("zuopinList", pglist.getList());
        mv.addObject("pager", pager);
        mv.addObject("cond", cond);

        mv.setViewName("manage/usr/zuopinlist");
        return mv;
    }

    /**
     * 设置作品排序号
     * 
     * @param id
     * @param location
     * @param request
     * @return
     */
    @PostMapping("setzuopinlocation")
    @ResponseBody
    public JsonResult<Integer> setZuoPinLocation(
        @RequestParam("id")int id, 
        @RequestParam("location")int location,
        HttpServletRequest request) {

        try {
            int topNo = 0;
            if (location == 0) {
                // do nothing.
            } else if (location > 0) {
                int maxNo = zuopinService.getMaxTopNo();
                if (maxNo < 0) {
                    maxNo = 0;
                }
                topNo = maxNo + 10;
            } else if (location < 0) {
                int minNo = zuopinService.getMinTopNo();
                if (minNo > 0) {
                    minNo = 0;
                }
                topNo = minNo - 10;
            }

            int n = zuopinService.updateZuoPinTopNo(id, topNo);
            return JsonResult.ok(n);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

}
