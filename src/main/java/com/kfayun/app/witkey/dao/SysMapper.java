/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kfayun.app.witkey.model.ActionLog;
import com.kfayun.app.witkey.model.SettingItem;
import com.kfayun.app.witkey.model.ViewLog;

/**
 * 系统相关实体Mapper
 * 
 * @author billy (billy_zh@126.com)
 */
public interface SysMapper {
    
    List<SettingItem> getSettingItemList();

    int updateSettingsByKey(
    		@Param("key") String key,
                @Param("value") String value);

    void insertActionLog(ActionLog log);

    int findActionLogCount(
            @Param("category") String category,
            @Param("keyword") String keyword);

    List<ActionLog> findActionLogList(
            @Param("category") String category,
            @Param("keyword") String keyword,
            @Param("offset") int offset,
            @Param("limit") int limit);

    ActionLog getActionLog(
            @Param("id") int id);

        void insertViewLog(ViewLog log);

        List<Map<String, Object>> getViewStatisListForDate(
                @Param("start") Date start);

        List<Map<String, Object>> getViewStatisListForWeek(
                @Param("start") Date start);
                
        List<Map<String, Object>> getViewStatisListForMonth(
                @Param("start") Date start);

}
