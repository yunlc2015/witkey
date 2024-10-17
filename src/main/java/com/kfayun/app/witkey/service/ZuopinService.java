/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service;

import java.util.List;

import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.condition.ZuopinCondition;
import com.kfayun.app.witkey.model.ZuoPinImage;
import com.kfayun.app.witkey.model.ZuoPinInfo;

/**
 * 作品服务接口
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public interface ZuopinService {

    /**
     * 保存作品信息
     * 
     * @param info
     * @return
     */
    public int saveZuoPinInfo(ZuoPinInfo info);

    /**
     * 更新作品信息
     * 
     * @param info
     * @return
     */
    public int updateZuoPinInfo(ZuoPinInfo info);

    /**
     * 查找作品信息（分页）
     * 
     * @param cond
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageList<ZuoPinInfo> findZuoPinInfoList(
            ZuopinCondition cond, int pageNo, int pageSize);

    /**
     * 查找作品信息
     * 
     * @param cond
     * @param num
     * @return
     */
    public List<ZuoPinInfo> findZuoPinInfoList(
            ZuopinCondition cond, int num);

    /**
     * 获取作品信息
     * 
     * @param zuopinId
     * @return
     */
    public ZuoPinInfo getZuoPinInfo(int id);

    /**
     * 获取用户作品的赞总数
     * 
     * @param userId
     * @return
     */
    public int getZuoPinTotalLikeByUser(int userId);

    /**
     * 获取作品图片列表
     * 
     * @param zuopinId
     * @return
     */
    public List<ZuoPinImage> getZuoPinImageList(int zuopinId);

    /**
     * 获取用户作品列表
     * 
     * @param userId
     * @param num
     * @return
     */
    public List<ZuoPinInfo> getZuoPinInfoListByUser(int userId, int num);

    /**
     * 删除作品
     * 
     * @param zuopinId
     * @param userId
     * @return
     */
    public int deleteZuoPinInfo(int zuopinId, int userId);

    /**
     * 
     * @return
     */
    public int getMaxTopNo();

    /**
     * 
     * @return
     */
    public int getMinTopNo();
}
