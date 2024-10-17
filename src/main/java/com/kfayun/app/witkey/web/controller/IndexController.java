/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Arrays;

import com.kfayun.app.witkey.condition.*;
import com.kfayun.app.witkey.config.AppConfig;
import com.kfayun.app.witkey.model.*;
import com.kfayun.app.witkey.service.*;
import com.kfayun.app.witkey.util.*;
import com.kfayun.app.witkey.web.vo.TaskInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 首页Controller
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Controller
public class IndexController extends BaseController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private CmsService cmsService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private ZuopinService zuopinService;
	@Autowired
	private ProjectService projectService;

    /**
     * 首页
     * 
     * @return
     */
	@GetMapping(value = {"/", "index"})
    public ModelAndView index(){

		ModelAndView mv = new ModelAndView();

        List<Banner> bannerList = cmsService.getBannersByLocation("index");
        mv.addObject("bannerList", bannerList);

        Banner bannerAd1 = null;
        Banner bannerAd2 = null;
        Banner bannerAd3 = null;
        List<Banner> banners = cmsService.getBannersByLocation("index_ad");
        if (banners.size() > 0) {
            bannerAd1 = banners.get(0);
        }
        if (banners.size() > 1) {
            bannerAd2 = banners.get(1);
        }
        if (banners.size() > 2) {
            bannerAd3 = banners.get(2);
        }
        mv.addObject("bannerAd1", bannerAd1);
        mv.addObject("bannerAd2", bannerAd2);
        mv.addObject("bannerAd3", bannerAd3);

        List<BdInfo> bdinfoList = cmsService.getBdInfosByName("index", 4);
        mv.addObject("bdinfoList", bdinfoList);

        ZuopinCondition cond = new ZuopinCondition();
        List<ZuoPinInfo> zuopinList = zuopinService.findZuoPinInfoList(cond, 20);
        mv.addObject("zuopinList", zuopinList);

        int clazz = 1; //设计师
        UserCondition userCond = new UserCondition();
        userCond.setType( 1 ); //个人
        userCond.setClazz( clazz ); 

        userCond.setCate1( 1000 );
        List<User> userListForLogo = userService.findUserList(userCond, 10);
        userCond.setCate1( 2000 );
        List<User> userListForVi = userService.findUserList(userCond, 10);
        userCond.setCate1( 3000 );
        List<User> userListForHc = userService.findUserList(userCond, 10);
        userCond.setCate1( 4000 );
        List<User>  userListForBz = userService.findUserList(userCond, 10);
        userCond.setCate1( 5000 );
        List<User> userListForApp = userService.findUserList(userCond, 10);
        mv.addObject("userListForLogo", userListForLogo);
        mv.addObject("userListForVi", userListForVi);
        mv.addObject("userListForHc", userListForHc);
        mv.addObject("userListForBz", userListForBz);
        mv.addObject("userListForApp", userListForApp);

        int type = 2;
        // 北京,上海,
        String provinceSet = "'北京市','上海市','重庆市'";
        List<User> userTopList = userService.getUserListTopForProvinceGroup(clazz, type, provinceSet, 10);
        List<User> bjUserList = userTopList.stream()
            .filter((user)->{return user.getProvince().equals("北京市");}).collect(Collectors.toList());
        List<User> shUserList = userTopList.stream()
            .filter((user)->{return user.getProvince().equals("上海市");}).collect(Collectors.toList());
        List<User> cqUserList = userTopList.stream()
            .filter((user)->{return user.getProvince().equals("重庆市");}).collect(Collectors.toList());
        mv.addObject("bjUserList", bjUserList);
        mv.addObject("shUserList", shUserList);
        mv.addObject("cqUserList", cqUserList);

        String citySet = "1008, 1009"; //广州，深圳
        userTopList = userService.getUserListTopForCityGroup(clazz, type, citySet, 10);
        List<User> gzUserList = userTopList.stream()
            .filter((user)->{return user.getCityId()==1008;}).collect(Collectors.toList());
        List<User> szUserList = userTopList.stream()
            .filter((user)->{return user.getCityId()==1009;}).collect(Collectors.toList());
        mv.addObject("gzUserList", gzUserList);
        mv.addObject("szUserList", szUserList);
                 
        List<Category> cateList = commonService.getCategoryList(0);
        mv.addObject("cateList", cateList);

        TaskCondition taskCond = new TaskCondition();
        List<TaskInfo> taskList = taskService.findTaskInfoList(taskCond, 16);
        List<TaskInfoVO> taskList2 = taskList.stream()
            .map((task)->{
                TaskInfoVO vo = new TaskInfoVO(task);
                vo.setProjectCount( projectService.getProjectCount2ByTask(task.getId()) );
                return vo;
            }).collect(Collectors.toList());
        mv.addObject("taskList", taskList2);        

		mv.setViewName("index");
		return mv;
    }
	
}
