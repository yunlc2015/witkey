/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.condition.*;
import com.kfayun.app.witkey.model.*;

/**
 * 内容服务接口
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public interface CmsService {

    public Map<String, String> getBdNameList();

    @Cacheable("banner")
    public List<Banner> getBannersByLocation(String location);

    public List<BdInfo> getBdInfosByName(String name, int num);

    public PageList<BdInfo> findBdInfoList(
                String name, int pageNo, int pageSize);

    public PageList<Article> findArticleList(
            ArticleCondition cond, int pageNo, int pageSize);

    @Cacheable("article")
    public Article getArticleByPath(String path);

    @CacheEvict(cacheNames="article", allEntries=true)
    public void saveArticle(Article article);

    @CacheEvict(cacheNames="article", allEntries=true)
    public int updateArticle(Article article);

    public Article getArticle(int artId);

    @CacheEvict(cacheNames="banner", allEntries=true)
    public void saveBanner(Banner banner);

    @CacheEvict(cacheNames="banner", allEntries=true)
    public int updateBanner(Banner banner);

    public Banner getBanner(int bid);

    @CacheEvict(cacheNames="banner", allEntries=true)
    public int deleteBanner(int bid);

    public void saveBdInfo(BdInfo banner);

    public int updateBdInfo(BdInfo banner);

    public BdInfo getBdInfo(int bid);

    public int deleteBdInfo(int bid);


}
