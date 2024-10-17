/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.condition.RatingCondition;
import com.kfayun.app.witkey.condition.RequireCondition;
import com.kfayun.app.witkey.condition.TouSuCondition;
import com.kfayun.app.witkey.model.CityInfo;
import com.kfayun.app.witkey.model.Rating;
import com.kfayun.app.witkey.model.Require;
import com.kfayun.app.witkey.model.TouSu;
import com.kfayun.app.witkey.model.Category;

/**
 * 通用服务接口
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public interface CommonService {

    /**
     * 获取省份列表
     * 
     * @return
     */
    @Cacheable("cityinfo")
    public List<CityInfo> getProvinceList();

    /**
     * 获取省份下的城市列表
     * 
     * @param province 省份名称
     * @return
     */
    @Cacheable("cityinfo")
    public List<CityInfo> getCityListByProvince(String province);

    /**
     * 获取城市信息 城市ID
     * 
     * @param cityId
     * @return
     */
    public CityInfo getCity(int cityId);

    /**
     * 获取省份信息
     * 
     * @param province 省份名称
     * @return
     */
    public CityInfo getProvince(String province);

    /**
     * 获取类别列表
     * 
     * @param depth
     * @return
     */
    @Cacheable("category")
    public List<Category> getCategoryList(int depth);

    /**
     * 获取子类别列表
     * 
     * @param parentId 父类别ID
     * @return
     */
    @Cacheable("category")
    public List<Category> getCategoryListByParent(int parentId);

    /**
     * 获取类别信息
     * 
     * @param id
     * @return
     */
    public Category getCategory(int id);
    
    /**
     * 保存需求
     * 
     * @param require
     */
    public void saveRequire(Require require);

    /**
     * 查找需求列表
     * 
     * @param cond 查询条件
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageList<Require> findRequireList(
        RequireCondition cond, int pageNo, int pageSize);

    /**
     * 按日期统计需求数据
     * 
     * @return
     */
    public int getRequireTotalByDate();

    /**
     * 按月份统计需求数据
     * 
     * @return
     */
    public int getRequireTotalByMonth();

    /**
     * 检查赞数据
     * 
     * @param userId 用户ID
     * @param objId 对象ID
     * @param kind 种类
     * @return
     */
    public int checkLike(int userId, int objId, String kind);

    /**
     * 赞
     * 
     * @param userId
     * @param objId
     * @param kind
     * @param likeUserId
     * @return
     */
    public int doLike(int userId, int objId, String kind, int likeUserId);

    /**
     * 获取用户赞数量
     * 
     * @param userId
     * @return
     */
    public int getUserLikeCount(int userId);

    /**
     * 保存投诉
     * 
     * @param ts
     */
    public void saveTouSu(TouSu ts);

    /**
     * 查找投诉列表
     * 
     * @param cond
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageList<TouSu> findTouSuList(
        TouSuCondition cond, int pageNo, int pageSize);

    public void saveRating(Rating rt);

    /**
     * 获取项目评价
     * 
     * @param projectId
     * @return
     */
    public Rating getRatingByProject(int projectId);

    /**
     * 查找评价列表
     * 
     * @param cond
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageList<Rating> findRatingList(RatingCondition cond, int pageNo, int pageSize);

    /**
     * 获取用户评价数据（分组）
     * 
     * @param toUserId
     * @return
     */
    public List<Integer> getRatingCountByToUser(int toUserId);

    /**
     * 获取用户关联类别
     * 
     * @param userId
     * @return
     */
    public Category getCategoryByUser(int userId);
    
}
