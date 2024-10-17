/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.kfayun.app.witkey.service.CmsService;
import com.kfayun.app.witkey.model.Article;

/**
 * 文件Controller
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
@Controller
public class ArticleController extends BaseController {
    
    @Autowired
    private CmsService cmsService;

    @GetMapping("s1/{name}")
    public ModelAndView s1(@PathVariable("name")String name) {

        ModelAndView mv = new ModelAndView();

        Article art = cmsService.getArticleByPath(name);

        mv.addObject("article", art);
        mv.addObject("name", name);
        
        mv.setViewName("article/s1");
		return mv;

    }

    @GetMapping("s2/{name}")
    public ModelAndView s2(@PathVariable("name")String name) {

        ModelAndView mv = new ModelAndView();

        Article art = cmsService.getArticleByPath(name);

        mv.addObject("article", art);
        mv.addObject("name", name);
        
        mv.setViewName("article/s2");
		return mv;

    }

    @GetMapping("s3/{name}")
    public ModelAndView s3(@PathVariable("name")String name) {

        ModelAndView mv = new ModelAndView();

        Article art = cmsService.getArticleByPath(name);

        mv.addObject("article", art);
        mv.addObject("name", name);
        
        mv.setViewName("article/s3");
		return mv;

    }

    @GetMapping("s5/{name}")
    public ModelAndView s5(@PathVariable("name")String name) {

        ModelAndView mv = new ModelAndView();

        Article art = cmsService.getArticleByPath(name);

        mv.addObject("article", art);
        mv.addObject("name", name);
        
        mv.setViewName("article/s5");
		return mv;

    }
}
