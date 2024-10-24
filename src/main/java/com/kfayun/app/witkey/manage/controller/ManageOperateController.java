/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
 * 运营管理Controller
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
@RequestMapping("/manage/ops")
@Controller
public class ManageOperateController extends ManageBaseController {

    @Autowired
    private CmsService cmsService;
    @Autowired
    private CommonService commonService;

    /**
     * 文章列表页
     * 
     * @param keyword
     * @param pageNo
     * @return
     */
    @ManageOperate(ManageAction.OPS_ARTICLE_VIEW)
    @GetMapping("articlelist")
    public ModelAndView articleList(
        @RequestParam(value="keyword", required=false, defaultValue="")String keyword,
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo) {

        ModelAndView mv = new ModelAndView();

        int pageSize = 10;
        PageList<Article> pglist = cmsService.findArticleList(keyword, pageNo, pageSize);

        PagerInfo pager = new PagerInfo(pageNo, 10);
        pager.setTotalCount(pglist.getTotal());
        pager.setBaseUrl("articlelist?keyword=" + keyword);

        mv.addObject("articleList", pglist.getList());
        mv.addObject("pager", pager);
        mv.addObject("keyword", keyword);

        mv.setViewName("manage/ops/articlelist");
        return mv;
    }

    /**
     * 文章编辑页
     * 
     * @param id 文章ID
     * @return
     */
    @ManageOperate(ManageAction.OPS_ARTICLE_EDIT)
    @GetMapping("articleedit")
    public ModelAndView articleEdit(@RequestParam("id")int id) {

        ModelAndView mv = new ModelAndView();

        Article article = cmsService.getArticle(id);
        if (article ==  null) {
            throw new ForbiddenException("无效的参数。");
        }

        mv.addObject("article", article);

        mv.setViewName("manage/ops/articleedit");
        return mv;
    }

    @GetMapping("articlecontent")
    @ResponseBody
    public JsonResult<String> articleContent(@RequestParam("id")int id, HttpServletRequest request) {

        try {
            Article article = cmsService.getArticle(id);
            if (article ==  null) {
                throw new ForbiddenException("无效的参数。");
            }

            return JsonResult.ok(article.getContent());
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

     /**
     * 文章编辑提交
     * 
     * @return
     */
    @ManageOperate(ManageAction.OPS_ARTICLE_EDIT)
    @PostMapping("articleedit")
    @ResponseBody
    public JsonResult<Integer> articleEditPost(@RequestParam Map<String, String> params,
            HttpServletRequest request) {

        try {
            int id = getInt(params.get("id"), 0);
            Article article = cmsService.getArticle(id);
            if (article ==  null) {
                return JsonResult.fail(-1, "无效的参数。");
            }

            article.setContent(params.get("content"));

            int n = cmsService.updateArticle(article);
            return JsonResult.ok(n);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * 推广信息列表页
     * 
     * @param keyword
     * @param pageNo
     * @return
     */
    @ManageOperate(ManageAction.OPS_BDINFO_VIEW)
    @GetMapping("bdinfolist")
    public ModelAndView bdinfoList(
        @RequestParam(value="keyword", required=false, defaultValue="")String keyword,
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo) {

        ModelAndView mv = new ModelAndView();

        int pageSize = 10;
        PageList<BdInfo> pglist = cmsService.findBdInfoList(keyword, pageNo, pageSize);

        PagerInfo pager = new PagerInfo(pageNo, 10);
        pager.setTotalCount(pglist.getTotal());
        pager.setBaseUrl("bdinfolist?keyword=" + keyword);

        mv.addObject("bdinfoList", pglist.getList());
        mv.addObject("pager", pager);
        mv.addObject("keyword", keyword);

        mv.setViewName("manage/ops/bdinfolist");
        return mv;
    }

    /**
     * 推广添加页
     * 
     * @return
     */
    @ManageOperate(ManageAction.OPS_BDINFO_ADD)
    @GetMapping("bdinfoadd")
    public ModelAndView bdinfoAdd() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("manage/ops/bdinfoadd");
        return mv;
    }

     /**
     * 推广添加提交
     * 
     * @return
     */
    @ManageOperate(ManageAction.OPS_BDINFO_ADD)
    @PostMapping("bdinfoadd")
    @ResponseBody
    public JsonResult<Integer> bdinfoAddPost(@RequestParam Map<String, String> params,
            HttpServletRequest request) {

        try {
            BdInfo bdinfo  = new BdInfo();
            bdinfo.setName(params.get("name"));
            bdinfo.setTitle(params.get("title"));
            bdinfo.setImageUrl( params.get("imgpath") );
            bdinfo.setTargetUrl( params.get("targeturl") );
            bdinfo.setPrice( params.get("price") );
            bdinfo.setSortNo( getInt(params.get("sortno"), 0) );
            bdinfo.setAddTime(new Date());

            cmsService.saveBdInfo(bdinfo);
            return JsonResult.ok(0);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * 推广编辑页
     * 
     * @param id 推广ID
     * @return
     */
    @ManageOperate(ManageAction.OPS_BDINFO_EDIT)
    @GetMapping("bdinfoedit")
    public ModelAndView bdinfoEdit(@RequestParam("id")int id) {

        ModelAndView mv = new ModelAndView();

        BdInfo bdinfo  = cmsService.getBdInfo(id);
        if (bdinfo ==  null) {
            throw new ForbiddenException("无效的参数。");
        }

        mv.addObject("bdinfo", bdinfo);

        mv.setViewName("manage/ops/bdinfoedit");
        return mv;
    }

    /**
     * 推广编辑提交
     * 
     * @return
     */
    @ManageOperate(ManageAction.OPS_BDINFO_EDIT)
    @PostMapping("bdinfoedit")
    @ResponseBody
    public JsonResult<Integer> bdinfoEditPost(@RequestParam Map<String, String> params,
            HttpServletRequest request) {

        try {
            int id = getInt(params.get("id"), 0);
            BdInfo bdinfo = cmsService.getBdInfo(id);
            if (bdinfo ==  null) {
                return JsonResult.fail(-1, "无效的参数。");
            }

            bdinfo.setName(params.get("name"));
            bdinfo.setTitle(params.get("title"));
            bdinfo.setImageUrl( params.get("imgpath") );
            bdinfo.setTargetUrl( params.get("targeturl") );
            bdinfo.setPrice( params.get("price") );
            bdinfo.setSortNo( getInt(params.get("sortno"), 0) );
            
            int n = cmsService.updateBdInfo(bdinfo);
            return JsonResult.ok(n);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * 推广删除提交
     * 
     * @return
     */
    @ManageOperate(ManageAction.OPS_BDINFO_DELETE)
    @PostMapping("bdinfodelete")
    @ResponseBody
    public JsonResult<Integer> bdinfoDelete(@RequestParam("id")int id,
            HttpServletRequest request) {

        try {
            int n = cmsService.deleteBdInfo(id);
            return JsonResult.ok(n);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * Banner列表页
     * 
     * @param location
     * @return
     */
    @ManageOperate(ManageAction.OPS_BANNER_VIEW)
    @GetMapping("bannerlist")
    public ModelAndView bannerList(
        @RequestParam(value="location", required=false, defaultValue="")String location) {


        ModelAndView mv = new ModelAndView();

        List<Banner> bannerList = cmsService.getBannerListByLocation(location);

        mv.addObject("bannerList",bannerList);
        mv.addObject("location", location);

        mv.setViewName("manage/ops/bannerlist");
        return mv;
    }

    /**
     * Banner添加页
     * 
     * @return
     */
    @ManageOperate(ManageAction.OPS_BANNER_ADD)
    @GetMapping("banneradd")
    public ModelAndView bannerAdd() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("manage/ops/banneradd");
        return mv;
    }

     /**
     * Banner添加提交
     * 
     * @return
     */
    @ManageOperate(ManageAction.OPS_BANNER_ADD)
    @PostMapping("banneradd")
    @ResponseBody
    public JsonResult<Integer> bannerAddPost(@RequestParam Map<String, String> params,
            HttpServletRequest request) {

        try {
            Banner banner = new Banner();
            banner.setLocation(params.get("location"));
            banner.setImageUrl( params.get("imgpath") );
            banner.setTargetUrl( params.get("targeturl") );
            banner.setSortNo( getInt(params.get("sortno"), 0) );
            banner.setAddTime(new Date());

            cmsService.saveBanner(banner);
            return JsonResult.ok(0);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * Banner编辑页
     * 
     * @param id BannerID
     * @return
     */
    @ManageOperate(ManageAction.OPS_BANNER_EDIT)
    @GetMapping("banneredit")
    public ModelAndView bannerEdit(@RequestParam("id")int id) {

        ModelAndView mv = new ModelAndView();

        Banner banner = cmsService.getBanner(id);
        if (banner ==  null) {
            throw new ForbiddenException("无效的参数。");
        }

        mv.addObject("banner", banner);

        mv.setViewName("manage/ops/banneredit");
        return mv;
    }

    /**
     * Banner编辑提交
     * 
     * @return
     */
    @ManageOperate(ManageAction.OPS_BANNER_EDIT)
    @PostMapping("banneredit")
    @ResponseBody
    public JsonResult<Integer> bannerEditPost(@RequestParam Map<String, String> params,
            HttpServletRequest request) {

        try {
            int id = getInt(params.get("id"), 0);
            Banner banner = cmsService.getBanner(id);
            if (banner ==  null) {
                return JsonResult.fail(-1, "无效的参数。");
            }

            banner.setLocation(params.get("location"));
            banner.setImageUrl( params.get("imgpath") );
            banner.setTargetUrl( params.get("targeturl") );
            banner.setSortNo( getInt(params.get("sortno"), 0) );

            int n = cmsService.updateBanner(banner);
            return JsonResult.ok(n);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * Banner删除提交
     * 
     * @param id BannerID
     * @return
     */
    @ManageOperate(ManageAction.OPS_BANNER_DELETE)
    @PostMapping("bannerdelete")
    @ResponseBody
    public JsonResult<Integer> bannerDelete(@RequestParam("id")int id,
            HttpServletRequest request) {

        try {
            int n = cmsService.deleteBanner(id);
            return JsonResult.ok(n);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
    }

    /**
     * 需求列表页
     * 
     * @param keyword
     * @return
     */
    @GetMapping("requirelist")
    public ModelAndView requireList(
        @RequestParam(value="keyword", required=false, defaultValue="")String keyword,
        @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo) {

        ModelAndView mv = new ModelAndView();

        RequireCondition cond = new RequireCondition();
        cond.setKeyword(keyword);

        int pageSize = 15;
        PageList<Require> pglist = commonService.findRequireList(cond, pageNo, pageSize);
        PagerInfo pager = new PagerInfo(pageNo, pageSize);
        pager.setBaseUrl("requirelist" + cond.getQueryString() );
        
        mv.addObject("requireList", pglist.getList());
        mv.addObject("pager", pager);
        mv.addObject("cond", cond);

        mv.setViewName("manage/ops/requirelist");
        return mv;
    }

    /**
     * 投诉列表页
     * 
     * @param username
     * @return
     */
    @GetMapping("tousulist")
    public ModelAndView tousuList(
        @RequestParam(value="username", required=false, defaultValue="")String username,
        @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo) {

        ModelAndView mv = new ModelAndView();

        TouSuCondition cond = new TouSuCondition();
        cond.setUsername(username);

        int pageSize = 15;
        PageList<TouSu> pglist = commonService.findTouSuList(cond, pageNo, pageSize);
        PagerInfo pager = new PagerInfo(pageNo, pageSize);
        pager.setBaseUrl("tousulist" + cond.getQueryString() );
        
        mv.addObject("tousuList", pglist.getList());
        mv.addObject("pager", pager);
        mv.addObject("cond", cond);

        mv.setViewName("manage/ops/tousulist");
        return mv;
    }

}
