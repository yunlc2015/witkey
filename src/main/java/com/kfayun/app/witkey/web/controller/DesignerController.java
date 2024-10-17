/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kfayun.app.witkey.ForbiddenException;
import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.PagerInfo;
import com.kfayun.app.witkey.condition.RatingCondition;
import com.kfayun.app.witkey.condition.ZuopinCondition;
import com.kfayun.app.witkey.model.*;
import com.kfayun.app.witkey.service.*;
import com.kfayun.app.witkey.util.*;
import com.kfayun.app.witkey.web.vo.UserVO;

/**
 * 设计师Controller
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
@Controller
@RequestMapping("/designer")
public class DesignerController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private ZuopinService zuopinService;
    @Autowired
    private CommonService commonService;

    /**
     * 设计师主页
     * 
     * @param userId 用户ID
     * @param page 分页数
     * @param request
     * @param response
     * @return
     */
    @GetMapping("u{userid:\\d+}")
    public ModelAndView home(@PathVariable("userid")int userId,
                @RequestParam(value="page", required=false, defaultValue="1")int page,
            HttpServletRequest request,
            HttpServletResponse response) {

        ModelAndView mv = new ModelAndView();

        User user = userService.getUser(userId);
        if (user == null) {
            // 403
            throw new ForbiddenException("无效的参数。");
        }
        mv.addObject("user", new UserVO(user));
        
        String abilityDescribe = userService.getAbilityGradeText(user.getAbilityGrade());
                
        String mobile = "";
        String mobileMask = "";
        if (user.getMobile2Pub() == 1) {
            mobile = StrUtil.isEmpty(user.getMobile2()) ? user.getMobile() : user.getMobile2();
            mobileMask = StrUtil.maskMobile(mobile);
        } else {
            Settings sett = (Settings)request.getAttribute("settings");
            mobile = sett.getServiceTel();
            mobileMask = mobile;
        }
        mv.addObject("abilityDescribe", abilityDescribe);
        mv.addObject("mobile", mobile);
        mv.addObject("mobileMask", mobileMask);

        ZuopinCondition cond = new ZuopinCondition();
        cond.setUserId(user.getId());

        int pageSize = 12;
        PageList<ZuoPinInfo> pglist = zuopinService.findZuoPinInfoList(cond, page, pageSize);
        PagerInfo pager = new PagerInfo(page, pageSize);
        pager.setTotalCount(pglist.getTotal());
        pager.setBaseUrl("/designer/home");

        mv.addObject("zuopinList", pglist.getList());
        mv.addObject("pager", pager);

        mv.setViewName("designer/home");
        return mv;
    }

    /**
     * 设计师评价页
     * 
     * @param userId 用户ID
     * @param type 评价类型
     * @param page 分页数
     * @param request
     * @param response
     * @return
     */
    @GetMapping("u{userid:\\d+}/rating")
    public ModelAndView rating(@PathVariable("userid")int userId,
            @RequestParam(value="type", required = false, defaultValue = "0")int type,
            @RequestParam(value="page", required = false, defaultValue = "1")int page,
            HttpServletRequest request,
            HttpServletResponse response) {

        ModelAndView mv = new ModelAndView();

        User user = userService.getUser(userId);
        if (user == null) {
            // 403
            throw new ForbiddenException("无效的参数。");
        }
        mv.addObject("user", new UserVO(user));

        String mobile = "";
        String mobileMask = "";
        if (user.getMobile2Pub() == 1) {
            mobile = StrUtil.isEmpty(user.getMobile2()) ? user.getMobile() : user.getMobile2();
            mobileMask = StrUtil.maskMobile(mobile);
        } else {
            Settings sett = (Settings)request.getAttribute("settings");
            mobile = sett.getServiceTel();
            mobileMask = mobile;
        }
        mv.addObject("mobile", mobile);
        mv.addObject("mobileMask", mobileMask);

        int rating5Percent = 100;
        int rating3Percent = 0;
        int rating1Percent = 0;
        List<Integer> counts = commonService.getRatingCountByToUser(user.getId());
        if (counts.get(0) > 0)
        {
            rating5Percent = (int)(Math.round(counts.get(1) * 100.0 / counts.get(0)));
            rating3Percent = (int)(Math.round(counts.get(2) * 100.0 / counts.get(0)));
            rating1Percent = (int)(Math.round(counts.get(3) * 100.0 / counts.get(0)));
        }
        mv.addObject("rating5Percent", rating5Percent);
        mv.addObject("rating3Percent", rating3Percent);
        mv.addObject("rating1Percent", rating1Percent);
        mv.addObject("ratingCount", counts);

        RatingCondition cond = new RatingCondition();
        cond.setToUserId(user.getId());
        cond.setType(type);
        
        int pageSize = 10;
        PageList<Rating> pglist = commonService.findRatingList(cond, page, pageSize);
        
        PagerInfo pager = new PagerInfo(page, pageSize);
        pager.setTotalCount(pglist.getTotal());
        pager.setBaseUrl("/designer/u"+user.getId()+"/rating?type="+type);

        mv.addObject("ratingList", pglist.getList());
        mv.addObject("pager", pager);

        mv.setViewName("designer/rating");
        return mv;
    }
}
