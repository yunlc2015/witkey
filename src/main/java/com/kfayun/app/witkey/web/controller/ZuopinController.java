/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.controller;

import java.util.List;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kfayun.app.witkey.ForbiddenException;
import com.kfayun.app.witkey.model.*;
import com.kfayun.app.witkey.service.*;
import com.kfayun.app.witkey.util.*;

/**
 * 作品Controller
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Controller
@RequestMapping("/zuopin")
public class ZuopinController extends BaseController {

    @Autowired
    private ZuopinService zuopinService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private UserService userService;

    /**
     * 作品详情页
     * 
     * @param id  作品ID
     */
    @GetMapping("{id:\\d+}")
    public ModelAndView detail(@PathVariable("id")int id,
            HttpServletRequest request,
            HttpServletResponse response) {

        ModelAndView mv = new ModelAndView();

        ZuoPinInfo zuopin = zuopinService.getZuoPinInfo(id);
        if (zuopin == null) {
            // 403
            throw new ForbiddenException("无效的参数。");
        }
        mv.addObject("zuopin", zuopin);
        
        List<ZuoPinImage> imageList = zuopinService.getZuoPinImageList(zuopin.getId());
        mv.addObject("imageList", imageList);

        List<Category> cateList = commonService.getCategoryList(0);
        mv.addObject("cateList", cateList);
        
        User user = userService.getUser(zuopin.getUserId());
        mv.addObject("user", user);

        String userEdu = (user.getType() == 1) ? user.getEducation() : user.getTeamDescr();               
        String location = "";
        if (user.getCityId() > 0) {
            CityInfo city = commonService.getCity(user.getCityId());
            location = city.getProvince() + user.getCity();
        }
        String abilityDescribe = userService.getAbilityGradeText(user.getAbilityGrade());
        String authDescribe = "";
        if (user.getRealAuthState() == 1){
            if (user.getAbilityGrade() > 0){
                authDescribe = (user.getType() == 2) ? "官方实力认证设计机构" : "官方实力认证设计大师";
            }else{
                authDescribe = (user.getType() == 2) ? "实名认证设计机构" : "实名认证设计师";
            }
        }

        String durationText = "";
        Duration ts = Duration.between(LocalDateTime.ofInstant(zuopin.getAddTime().toInstant(), ZoneId.systemDefault()),
                LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()) );
        if (ts.toDays() > 0) {
            durationText = ts.toDays() + "天前";
        } else if (ts.toHours() > 0) {
            durationText = ts.toHours() + "小时前";
        } else if (ts.toMinutes() > 0) {
            durationText = ts.toMinutes() + "分钟前";
        } else {
            durationText = ts.getSeconds() + "秒前";
        }
        
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

        mv.addObject("userEdu", userEdu);
        mv.addObject("location", location);
        mv.addObject("abilityDescribe", abilityDescribe);
        mv.addObject("authDescribe", authDescribe);
        mv.addObject("durationText", durationText);
        mv.addObject("mobile", mobile);
        mv.addObject("mobileMask", mobileMask);

        mv.setViewName("zuopin/detail");
        return mv;
    }

}
