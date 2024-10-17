/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.kfayun.app.witkey.condition.TradeCondition;
import com.kfayun.app.witkey.model.Trade;

/**
 * 交易实体Mapper
 * 
 * @author billy (billy_zh@126.com)
 */
public interface TradeMapper {

    public Trade getTrade(
        @Param("tradeNo")String tradeNo);

    public void insertTrade(Trade trade);

    public int updateTrade(Trade trade);

    public Trade getTradeByTask(
        @Param("taskId")int taskId);

    public String getTradeNoByTask(
        @Param("taskId")int taskId);

    public int findTradeCount(
        @Param("cond")TradeCondition cond);

    public List<Trade> findTradeList(
        @Param("cond")TradeCondition cond, 
        @Param("offset")int offset, 
        @Param("limit")int limit);

}
