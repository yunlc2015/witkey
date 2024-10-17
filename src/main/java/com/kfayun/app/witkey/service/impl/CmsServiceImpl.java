/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service.impl;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.condition.*;
import com.kfayun.app.witkey.model.*;

import com.kfayun.app.witkey.service.CmsService;
import com.kfayun.app.witkey.dao.CmsMapper;

/**
 * 内容服务接口实现
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Service
public class CmsServiceImpl implements CmsService {

    public static String Index_Bd1 = "index_bd1";

    private static Map<String, String> bdNames;
    
    @Autowired
    private CmsMapper cmsMapper;
    
    static {} {
        bdNames = new HashMap<String, String>();
        bdNames.put(Index_Bd1, "首页推广(图片)");
    }

    @Override
    public Map<String, String> getBdNameList() {
        return bdNames;
    }

    @Override
    public List<Banner> getBannersByLocation(String location)
    {
        return cmsMapper.getBannersByLocation(location);
    }

    @Override
    public List<BdInfo> getBdInfosByName(String name, int num)
    {
        return cmsMapper.getBdInfosByName(name, num);
    }

    @Override
    public PageList<BdInfo> findBdInfoList(
            String name, int pageNo, int pageSize) {
        PageList<BdInfo> pglist = new PageList<>(pageNo, pageSize);
        pglist.setTotal( cmsMapper.findBdInfoCount(name) );
        pglist.setList( cmsMapper.findBdInfoList(name, pglist.getOffset(), pageSize));
        return pglist;
    }

    @Override
    public PageList<Article> findArticleList(
            ArticleCondition cond, int pageNo, int pageSize) {
        PageList<Article> pglist = new PageList<>(pageNo, pageSize);
        pglist.setTotal( cmsMapper.findArticleCount(cond) );
        pglist.setList( cmsMapper.findArticleList(cond, pglist.getOffset(), pageSize));
        return pglist;
    }

    @Override
    public Article getArticleByPath(String path) {
        return cmsMapper.getArticleByPath(path);
    }

    @Override
    public void saveArticle(Article article) {
        cmsMapper.insertArticle(article);
    }

    @Override
    public int updateArticle(Article article) {
        return cmsMapper.updateArticle(article);
    }

    @Override
    public Article getArticle(int artId) {
        return cmsMapper.getArticle(artId);
    }

    @Override
    public void saveBanner(Banner banner) {
        cmsMapper.insertBanner(banner);
    }

    @Override
    public int updateBanner(Banner banner) {
        return cmsMapper.updateBanner(banner);
    }

    @Override
    public Banner getBanner(int bid) {
        return cmsMapper.getBanner(bid);
    }

    @Override
    public int deleteBanner(int bid) {
        return cmsMapper.deleteBanner(bid);
    }

    @Override
    public void saveBdInfo(BdInfo banner) {
        cmsMapper.insertBdInfo(banner);
    }

    @Override
    public int updateBdInfo(BdInfo banner) {
        return cmsMapper.updateBdInfo(banner);
    }

    @Override
    public BdInfo getBdInfo(int bid) {
        return cmsMapper.getBdInfo(bid);
    }

    @Override
    public int deleteBdInfo(int bid) {
        return cmsMapper.deleteBdInfo(bid);
    }

}