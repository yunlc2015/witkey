/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kfayun.app.witkey.model.CityInfo;
import com.kfayun.app.witkey.model.Rating;
import com.kfayun.app.witkey.model.Require;
import com.kfayun.app.witkey.model.TouSu;
import com.kfayun.app.witkey.model.Category;
import com.kfayun.app.witkey.model.Like;

import com.kfayun.app.witkey.condition.RequireCondition;
import com.kfayun.app.witkey.condition.TouSuCondition;
import com.kfayun.app.witkey.condition.RatingCondition;

/**
 * 通用相关实体Mapper
 * 
 * @author billy (billy_zh@126.com)
 */
public interface CommonMapper {

    public List<CityInfo> getProvinceList();

    public List<CityInfo> getCityListByProvince(
        @Param("province")String province);

    public CityInfo getCity(
        @Param("id")int id);

    public CityInfo getProvince(
        @Param("province")String province);

    public List<Category> getCategoryList(
        @Param("depth")int depth);

    public List<Category> getCategoryListByParent(
        @Param("parentId")int parentId);

    public Category getCategory(
        @Param("id")int id);

    public void insertRequire(Require req);

    public int findRequireCount(
        @Param("cond")RequireCondition cond);

    public List<Require> findRequireList(
        @Param("cond")RequireCondition cond, 
        @Param("offset")int offset, 
        @Param("limit")int limit);

    public int getRequireTotalByDate(String date);

    public int getRequireTotalByMonth(String month);

    public int checkLike(
        @Param("userId")int userId, 
        @Param("objectId")int objectId, 
        @Param("kind")String kind);

    public int getLikeCount(
        @Param("objectId")int objectId, 
        @Param("kind")String kind);

    public void insertLike(Like like);

    public int getLikeCountByUser(
        @Param("userId")int userId);

    public void insertTouSu(TouSu ts);

    public int findTouSuCount(
        @Param("cond")TouSuCondition cond);

    public List<TouSu> findTouSuList(
        @Param("cond")TouSuCondition cond, 
        @Param("offset")int offset, 
        @Param("limit")int limit);

    public void insertRating(Rating rt);

    public Rating getRatingByProject(
        @Param("projectId")int projectId);

    public int findRatingCount(
        @Param("cond")RatingCondition cond);

    public List<Rating> findRatingList(
        @Param("cond")RatingCondition cond, 
        @Param("offset")int offset, 
        @Param("limit")int limit);

    public Map<String, Object> getRatingCountByToUser(
        @Param("toUserId")int toUserId);

    public Category getCategoryByUser(
        @Param("userId")int userId);

}
