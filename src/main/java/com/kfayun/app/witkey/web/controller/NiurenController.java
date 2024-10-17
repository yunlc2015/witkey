/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.controller;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.PagerInfo;
import com.kfayun.app.witkey.condition.UserCondition;
import com.kfayun.app.witkey.condition.ZuopinCondition;
import com.kfayun.app.witkey.model.*;
import com.kfayun.app.witkey.service.*;
import com.kfayun.app.witkey.web.vo.UserVO;

/**
 * 牛人Controller
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
@Controller
@RequestMapping("/niuren")
public class NiurenController extends BaseController {

    @Autowired
    private CmsService cmsService;
    @Autowired
    private ZuopinService zuopinService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommonService commonService;

    /**
     * 作品页
     * 
     * @param params
     * @return
     */
    @GetMapping(value = {"/", "index"})
    public ModelAndView index(
			@RequestParam Map<String, String> params){

		ModelAndView mv = new ModelAndView();

        List<Banner> bannerList = cmsService.getBannersByLocation("niuren");
        mv.addObject("bannerList", bannerList);

        List<CityInfo> provList = commonService.getProvinceList();
        mv.addObject("provList", provList);
        
        ZuopinCondition cond = new ZuopinCondition();
        cond.setCate1( getInt(params.get("c1"), 0) );
        cond.setCate2( getInt(params.get("c2"), 0) );
        cond.setProvince( params.getOrDefault("cp", "") );
        cond.setCityId( getInt(params.get("ct"), 0));
        cond.setUserGrade( getInt(params.get("grade"), 0) );
        cond.setKeyword( params.getOrDefault("wd", "") );
        cond.setSalesSort( getInt(params.get("sas"), 0) );
        cond.setPriceSort( getInt(params.get("prs"), 0) ); 
        cond.setCommentSort( getInt(params.get("cms"), 0) );
        if (cond.getSalesSort() == 0 && cond.getPriceSort() == 0 && cond.getCommentSort() == 0) {
            cond.setSalesSort( 1 ); // 默认排序
        }

        List<Category> cateList = commonService.getCategoryList(0);
        List<Category> cateList2 = Collections.emptyList();
        if (cond.getCate1() > 0) {
            cateList2 = commonService.getCategoryListByParent(cond.getCate1());
        }
        mv.addObject("cateList", cateList);
        mv.addObject("cateList2", cateList2);

        int page = getInt(params.get("page"), 1);
        int pageSize = 20;
        PageList<ZuoPinInfo> pglist = zuopinService.findZuoPinInfoList(cond, page, pageSize);
        PagerInfo pager = new PagerInfo(page, pageSize);
        pager.setTotalCount(pglist.getTotal());;
        pager.setBaseUrl("/niuren/index?");

        mv.addObject("zuopinList", pglist.getList());
        mv.addObject("pager", pager);
        mv.addObject("cond", cond);
        
        mv.setViewName("niuren/index");
		return mv;
    }

    /**
     * 设计师页
     * 
     * @param params
     * @return
     */
    @GetMapping("designer")
    public ModelAndView designer(
			@RequestParam Map<String, String> params){
        ModelAndView mv = new ModelAndView();

        List<Banner> bannerList = cmsService.getBannersByLocation("niuren");
        mv.addObject("bannerList", bannerList);

        List<CityInfo> provList = commonService.getProvinceList();
        mv.addObject("provList", provList);

        UserCondition cond = new UserCondition();
        cond.setType( 1 ); //个人
        cond.setClazz( 1 ); //设计师
        cond.setCate1( getInt(params.get("c1"), 0) );
        cond.setCate2( getInt(params.get("c2"), 0) );
        cond.setProvince( params.getOrDefault("cp", "") );
        cond.setCityId( getInt(params.get("ct"), 0) );
        cond.setGrade( getInt(params.get("grade"), 0) );
        cond.setKeyword( params.getOrDefault("wd", "") );
        cond.setSalesSort( getInt(params.get("sas"), 0) );
        cond.setPriceSort( getInt(params.get("prs"), 0) );
        cond.setCommentSort( getInt(params.get("cms"), 0) );
        if (cond.getSalesSort() == 0 && cond.getPriceSort() == 0 && cond.getCommentSort() == 0) {
            cond.setSalesSort( 1 ); // 默认排序
        }

        List<Category> cateList = commonService.getCategoryList(0);
        List<Category> cateList2 = Collections.emptyList();
        if (cond.getCate1() > 0) {
            cateList2 = commonService.getCategoryListByParent(cond.getCate1());
        }
        mv.addObject("cateList", cateList);
        mv.addObject("cateList2", cateList2);

        int page = getInt(params.get("page"), 1);
        int pageSize = 20;
        PageList<User> pglist = userService.findUserList(cond, page, pageSize);
        List<UserVO> volist = pglist.getList().stream()
            .map((user)->{
                UserVO vo = new UserVO(user);
                vo.setZuopinList(zuopinService.getZuoPinInfoListByUser(user.getId(), 3));
                return vo;
            }).collect(Collectors.toList());
        PagerInfo pager = new PagerInfo(page, pageSize);
        pager.setTotalCount(pglist.getTotal());;
        pager.setBaseUrl("/niuren/designer?");

        mv.addObject("userList", volist);
        mv.addObject("pager", pager);
        mv.addObject("cond", cond);

        mv.setViewName("niuren/designer");
		return mv;
    }

    /**
     * 设计团队页
     * 
     * @param params
     * @return
     */
    @GetMapping("team")
    public ModelAndView team(
			@RequestParam Map<String, String> params){
        ModelAndView mv = new ModelAndView();

        List<Banner> bannerList = cmsService.getBannersByLocation("niuren");
        mv.addObject("bannerList", bannerList);

        List<CityInfo> provList = commonService.getProvinceList();
        mv.addObject("provList", provList);

        UserCondition cond = new UserCondition();
        cond.setType( 2 ); //团队
        cond.setClazz( 1 ); //设计师
        cond.setCate1( getInt(params.get("c1"), 0) );
        cond.setCate2( getInt(params.get("c2"), 0) );
        cond.setProvince( params.getOrDefault("cp", "") );
        cond.setCityId( getInt(params.get("ct"), 0) );
        cond.setGrade( getInt(params.get("grade"), 0) );
        cond.setKeyword( params.getOrDefault("wd", "") );
        cond.setSalesSort( getInt(params.get("sas"), 0) );
        cond.setPriceSort( getInt(params.get("prs"), 0) );
        cond.setCommentSort( getInt(params.get("cms"), 0) );
        if (cond.getSalesSort() == 0 && cond.getPriceSort() == 0 && cond.getCommentSort() == 0) {
            cond.setSalesSort(1); // 默认排序
        }

        List<Category> cateList = commonService.getCategoryList(0);
        List<Category> cateList2 = Collections.emptyList();
        if (cond.getCate1() > 0) {
            cateList2 = commonService.getCategoryListByParent(cond.getCate1());
        }
        mv.addObject("cateList", cateList);
        mv.addObject("cateList2", cateList2);

        int page = getInt(params.get("page"), 1);
        int pageSize = 20;
        PageList<User> pglist = userService.findUserList(cond, page, pageSize);
        List<UserVO> volist = pglist.getList().stream()
            .map((user)->{
                UserVO vo = new UserVO(user);
                vo.setZuopinList(zuopinService.getZuoPinInfoListByUser(user.getId(), 3));
                return vo;
            }).collect(Collectors.toList());
        PagerInfo pager = new PagerInfo(page, pageSize);
        pager.setTotalCount(pglist.getTotal());;
        pager.setBaseUrl("/niuren/team?");

        mv.addObject("userList", volist);
        mv.addObject("pager", pager);
        mv.addObject("cond", cond);

        mv.setViewName("niuren/team");
        return mv;
    }

    @GetMapping("trademark")
    public ModelAndView trademark(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("niuren/trademark");
        return mv;
    }

}
