/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service;

import java.math.BigDecimal;
import java.util.List;

import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.condition.TradeCondition;
import com.kfayun.app.witkey.model.Trade;

/**
 * 交易服务接口
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public interface TradeService {

    public Trade getTrade(String tradeNo);

    public String getTradeHash(int userId, String tradeNo, BigDecimal amount);

    public void saveTrade(Trade trade);

    public int updateTrade(Trade trade);

    public Trade getTradeByTask(int taskId);

    public String getTradeNoByTask(int taskId);

    /// <summary>
    /// 余额支付
    /// </summary>
    /// <param name="trade"></param>
    /// <returns></returns>
    public int updateTradeForBalancePay(Trade trade);

    public int updateTradeForPaySuccess(Trade trade);

    public int updateTradeForCashPay(Trade trade);

    public PageList<Trade> findTradeList(TradeCondition cond, int pageNo, int pageSize);
        
} 
