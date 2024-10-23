/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kfayun.app.witkey.condition.ZuopinCondition;
import com.kfayun.app.witkey.model.ZuoPinImage;
import com.kfayun.app.witkey.model.ZuoPinInfo;

/**
 * 作品相关实体Mapper
 * 
 * @author billy (billy_zh@126.com)
 */
public interface ZuopinMapper {

    public int insertZuoPinInfo(ZuoPinInfo info);
        
    public int updateZuoPinInfo(ZuoPinInfo info);

    public void insertZuoPinImage(ZuoPinImage img);

    public int deleteZuoPinImages(
        @Param("zuopinId")int zuopinId);

    public int findZuoPinInfoCount(
        @Param("cond")ZuopinCondition cond);

    public List<ZuoPinInfo> findZuoPinInfoList(
        @Param("cond")ZuopinCondition cond, 
        @Param("offset")int offset,
        @Param("limit")int limit);

    public ZuoPinInfo getZuoPinInfo(
        @Param("id")int id);

    public int getZuoPinTotalLikeByUser(
        @Param("userId")int userId);
    
    public List<ZuoPinImage> getZuoPinImageList(
        @Param("zuopinId")int zuopinId);

    public List<ZuoPinInfo> getZuoPinInfoListByUser(
        @Param("userId")int userId, 
        @Param("limit")int limit);

    public int deleteZuoPinInfo(
        @Param("id")int id, 
        @Param("userId")int userId);

    public int getZuoPinMaxTopNo();

    public int getZuoPinMinTopNo();

    public int updateZuoPinTopNo(
        @Param("id")int id,
        @Param("topNo")int topNo);

    public int getZuoPinTotal();
        
}
