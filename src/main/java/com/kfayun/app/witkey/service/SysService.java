/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.model.ActionLog;
import com.kfayun.app.witkey.model.SettingItem;
import com.kfayun.app.witkey.model.Settings;
import com.kfayun.app.witkey.model.ViewLog;

/**
 * 系统服务接口
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public interface SysService {

    /*** Settings ****/

    /**
     * 获取配置项列表
     * 
     * @return
     */
	List<SettingItem> getSettingItemList();

    /**
     * 获取配置
     * 
     * @return
     */
    @Cacheable("settingitem")
	Settings getSettings();

    /**
     * 更新配置项
     * 
     * @param data
     * @return
     */
    @CacheEvict(value="settingitem", allEntries=true)
    int updateSettings(Map<String, String> data);
    
    /*** ActionLog ***/

    /**
     * 查找操作日志列表
     * 
     * @param category
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageList<ActionLog> findActionLogList(
            String category, String keyword, int pageNo, int pageSize);

    /**
     * 获取查找日志
     * 
     * @param id
     * @return
     */
    ActionLog getActionLog(int id);

    /**
     * 保存操作日志
     * 
     * @param log
     */
    void saveActionLog(ActionLog log);
    
    /**
     * 保存查看日志
     */
    void saveViewLog(ViewLog log);

    /**
     * 获取查看统计数据
     * @param start
     * @param kind
     * @return
     */
    List<Map<String, Object>> getViewStatisListForTime(Date start, String kind);

}
