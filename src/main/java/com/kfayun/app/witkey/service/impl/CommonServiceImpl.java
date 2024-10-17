/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.condition.RatingCondition;
import com.kfayun.app.witkey.condition.RequireCondition;
import com.kfayun.app.witkey.condition.TouSuCondition;
import com.kfayun.app.witkey.model.CityInfo;
import com.kfayun.app.witkey.model.Like;
import com.kfayun.app.witkey.model.Rating;
import com.kfayun.app.witkey.model.Require;
import com.kfayun.app.witkey.model.TouSu;
import com.kfayun.app.witkey.service.CommonService;
import com.kfayun.app.witkey.util.DateUtil;
import com.kfayun.app.witkey.model.Category;

import com.kfayun.app.witkey.dao.CommonMapper;

/**
 * 通用服务接口实现
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public List<CityInfo> getProvinceList() {
        return commonMapper.getProvinceList();
    }

    @Override
    public List<CityInfo> getCityListByProvince(String province) {
        return commonMapper.getCityListByProvince(province);
    }

    @Override
    public CityInfo getCity(int cityId) {
        return commonMapper.getCity(cityId);
    }

    @Override
    public CityInfo getProvince(String province) {
        return commonMapper.getProvince(province);
    }

    @Override
    public List<Category> getCategoryList(int depth) {
        return commonMapper.getCategoryList(depth);
    }

    @Override
    public List<Category> getCategoryListByParent(int parentId) {
        return commonMapper.getCategoryListByParent(parentId);
    }

    @Override
    public Category getCategory(int id) {
        return commonMapper.getCategory(id);
    }

    @Override
    public void saveRequire(Require req) {
        commonMapper.insertRequire(req);
    }

    @Override
    public PageList<Require> findRequireList(
            RequireCondition cond, int pageNo, int pageSize) {
        PageList<Require> pglist = new PageList<>(pageNo, pageSize);
        pglist.setTotal(commonMapper.findRequireCount(cond));
        pglist.setList(commonMapper.findRequireList(cond, pglist.getOffset(), pageSize));

        return pglist;
    }

    @Override
    public int getRequireTotalByDate() {
        return commonMapper.getRequireTotalByDate(DateUtil.toDateString(new Date()));
    }

    @Override
    public int getRequireTotalByMonth() {
        return commonMapper.getRequireTotalByMonth(DateUtil.toMonthString(new Date()));
    }

    @Override
    public int checkLike(int userId, int objectId, String kind) {
        return commonMapper.checkLike(userId, objectId, kind);
    }

    @Transactional
    @Override
    public int doLike(int userId, int objectId, String kind, int likeUserId)
    {
        Like like = new Like();
        like.setUserId(userId);
        like.setObjectId(objectId);
        like.setKind(kind);
        like.setLikeUserId(likeUserId);
        like.setLikeTime( new Date() );
        commonMapper.insertLike(like);

        return commonMapper.getLikeCount(objectId, kind);
    }

    @Override
    public int getUserLikeCount(int userId){
        return commonMapper.getLikeCountByUser(userId);
    }

    @Override
    public void saveTouSu(TouSu ts) {
        commonMapper.insertTouSu(ts);
    }

    @Override
    public PageList<TouSu> findTouSuList(
        TouSuCondition cond, int pageNo, int pageSize) {
            PageList<TouSu> pglist = new PageList<>(pageNo, pageSize);
        pglist.setTotal(commonMapper.findTouSuCount(cond));
        pglist.setList(commonMapper.findTouSuList(cond, pglist.getOffset(), pageSize));

        return pglist;
    }

    @Override
    public void saveRating(Rating rt) {
        commonMapper.insertRating(rt);
    }

    @Override
    public Rating getRatingByProject(int projId) {
        return commonMapper.getRatingByProject(projId);
    }

    @Override
    public PageList<Rating> findRatingList(
        RatingCondition cond, int pageNo, int pageSize) {
            PageList<Rating> pglist = new PageList<>(pageNo, pageSize);
        pglist.setTotal(commonMapper.findRatingCount(cond));
        pglist.setList(commonMapper.findRatingList(cond, pglist.getOffset(), pageSize));

        return pglist;
    }

    @Override
    public List<Integer> getRatingCountByToUser(int toUserId) {
        Map<String, Object> countMap = commonMapper.getRatingCountByToUser(toUserId);
        
        List<Integer> list = new ArrayList<>();
        list.add(Integer.parseInt(countMap.get("totalCount").toString()));
        list.add(Integer.parseInt(countMap.get("count1").toString()));
        list.add(Integer.parseInt(countMap.get("count2").toString()));
        list.add(Integer.parseInt(countMap.get("count3").toString()));
        
        return list;
    }

    @Override
    public Category getCategoryByUser(int userId) {
        return commonMapper.getCategoryByUser(userId);
    }
}
