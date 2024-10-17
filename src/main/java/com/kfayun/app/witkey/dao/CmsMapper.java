/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kfayun.app.witkey.condition.*;
import com.kfayun.app.witkey.model.*;

/**
 * 内容相关实体Mapper
 * 
 * @author billy (billy_zh@126.com)
 */
public interface CmsMapper {

    public List<Banner> getBannersByLocation(
        @Param("location")String location);

    public List<BdInfo> getBdInfosByName(
        @Param("name")String name, 
        @Param("limit")int num);

    public int findBdInfoCount(
        @Param("name")String name);

    public List<BdInfo> findBdInfoList(
        @Param("name")String name, 
        @Param("offset")int offset, 
        @Param("limit")int limit);

    public int findArticleCount(
            @Param("cond")ArticleCondition cond);

    public List<Article> findArticleList(
            @Param("cond")ArticleCondition cond, 
            @Param("offset")int offset, 
            @Param("limit")int limit);

    public Article getArticleByPath(
        @Param("path")String path);

    public void insertArticle(Article article);

    public int updateArticle(Article article);

    public Article getArticle(
        @Param("id")int artId);

    public void insertBanner(Banner banner);

    public int updateBanner(Banner banner);

    public Banner getBanner(
        @Param("id")int bid);

    public int deleteBanner(
        @Param("id")int bid);

    public void insertBdInfo(BdInfo banner);

    public int updateBdInfo(BdInfo banner);

    public BdInfo getBdInfo(
        @Param("id")int bid);

    public int deleteBdInfo(
        @Param("id")int bid);


}
