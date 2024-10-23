/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.kfayun.app.witkey.Constants;
import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.condition.TradeCondition;
import com.kfayun.app.witkey.model.AuditState;
import com.kfayun.app.witkey.model.Payment;
import com.kfayun.app.witkey.model.TaskInfo;
import com.kfayun.app.witkey.model.TaskState;
import com.kfayun.app.witkey.model.Trade;
import com.kfayun.app.witkey.dao.TradeMapper;
import com.kfayun.app.witkey.service.TaskService;
import com.kfayun.app.witkey.service.UserService;
import com.kfayun.app.witkey.service.TradeService;
import com.kfayun.app.witkey.service.FinanceService;
import com.kfayun.app.witkey.util.CryptoUtil;
import com.kfayun.app.witkey.util.DateUtil;

/**
 * 交易服务接口实现
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Service
public class TradeServiceImpl implements TradeService {
    
    @Autowired
    private TradeMapper tradeMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private FinanceService financeService;

    @Override
    public Trade getTrade(String tradeNo) {
        return tradeMapper.getTrade( tradeNo);
    }

    @Override
    public String getTradeHash(int userId, String tradeNo, BigDecimal amount)
    {
        String str = String.format("%s_%d_%s_%f", Constants.MD5_KEY, userId, tradeNo, amount);
        return CryptoUtil.MD5(str);
    }

    @Override
    public void saveTrade(Trade trade) {
        trade.setTradeNo( getTradeNo() );
        tradeMapper.insertTrade(trade);
    }

    private String getTradeNo() {
        Random rnd = new Random();
        return "T" + DateUtil.toDateStringNoSep(new Date()) + (10000+rnd.nextInt(89999)); 
    }

    @Override
    public int updateTrade(Trade trade) {
        return tradeMapper.updateTrade( trade);
    }

    /// <summary>
    /// 余额支付
    /// </summary>
    /// <param name="trade"></param>
    /// <returns></returns>
    @Transactional
    @Override
    public int updateTradeForBalancePay(Trade trade) {

        String lockStr = trade.getTradeNo().intern();
        synchronized (lockStr) {
            int n = financeService.checkPaymentForTrade(trade.getTradeNo());
            if (n > 0) {
                return 0; // 已处理
            }
            
            userService.updateUserBalance(trade.getUserId(), trade.getBalance());

            Payment pay = new Payment();
            pay.setUserId( trade.getUserId() );
            pay.setTaskId( trade.getTaskId() );
            pay.setTradeNo( trade.getTradeNo() );
            pay.setKind( 1 );  // 订单
            pay.setAmount( trade.getBalance() );
            pay.setSummary( "订单消费" );
            pay.setPayTime( new Date() );
            financeService.savePayment(pay);

            trade.setPayState( 1 );
            tradeMapper.updateTrade(trade);

            TaskInfo tinfo = taskService.getTaskInfo(trade.getTaskId());
            if (trade.getPayFlag() == 0) {
                tinfo.setTaskState(TaskState.Paid);
                if (tinfo.getService() == 1) { // 市场标
                    tinfo.setAuditState( AuditState.Approved ); //自动审核通过
                }
            } else { // 提高赏金
                tinfo.setDesignBudget(tinfo.getDesignBudget().add(trade.getTotalAmount()) ); // 增加预算

                if (tinfo.getService() == 1) { //市场标
                    if (tinfo.getInvoice() == 1) { //发票
                        tinfo.setFaxAmount( tinfo.getDesignBudget().multiply( new BigDecimal(0.06)) );  // 6%
                        tinfo.setDesignAmount( tinfo.getDesignBudget().subtract(tinfo.getFaxAmount()) );
                        tinfo.setServiceAmount( BigDecimal.ZERO );
                        tinfo.setTotalAmount( tinfo.getDesignBudget().add(new BigDecimal(8)) );
                    } else {
                        tinfo.setDesignAmount(tinfo.getDesignBudget());
                        tinfo.setServiceAmount( BigDecimal.ZERO );
                        tinfo.setFaxAmount( BigDecimal.ZERO );
                        tinfo.setTotalAmount( tinfo.getDesignBudget() );
                    }
                } else if (tinfo.getService() == 2) { //管家标
                    if (tinfo.getInvoice() == 1) { //发票
                        tinfo.setServiceAmount( tinfo.getDesignBudget().multiply(new BigDecimal(0.15)) ); // 15%
                        tinfo.setFaxAmount( tinfo.getDesignBudget().multiply(new BigDecimal(0.06)) );  // 6%
                        tinfo.setDesignAmount( tinfo.getDesignBudget().subtract(tinfo.getFaxAmount()).subtract(tinfo.getServiceAmount()) );
                        tinfo.setTotalAmount( tinfo.getDesignBudget().add(new BigDecimal(8)) );
                    } else {
                        tinfo.setServiceAmount(tinfo.getDesignBudget().multiply(new BigDecimal(0.15)) ); // 15%
                        tinfo.setFaxAmount( BigDecimal.ZERO );
                        tinfo.setDesignAmount( tinfo.getDesignBudget().subtract(tinfo.getServiceAmount()) );
                        tinfo.setTotalAmount( tinfo.getDesignBudget() );
                    }
                }
            }

            taskService.updateTaskInfo( tinfo);

            return 1;
        }
    }

    @Transactional
    @Override
    public int updateTradeForPaySuccess(Trade trade)  {
        
        String lockStr = trade.getTradeNo().intern();
        synchronized (lockStr) {
            int n = financeService.checkPaymentForTrade(trade.getTradeNo());
            if (n > 0)
                return 0; // 已处理

            tradeMapper.updateTrade(trade);

            Payment pay = new Payment();
            pay.setUserId( trade.getUserId() );
            pay.setTaskId( trade.getTaskId() );
            pay.setTradeNo( trade.getTradeNo() );
            pay.setKind( 1 );  // 订单
            pay.setAmount( trade.getAmount() );
            pay.setSummary( "订单消费" );
            pay.setPayTime( new Date() );
            financeService.savePayment(pay);

            if (trade.getBalance().compareTo(BigDecimal.ZERO) == 1) {
                userService.updateUserBalance(trade.getUserId(), trade.getBalance());

                pay = new Payment();
                pay.setUserId( trade.getUserId() );
                pay.setTaskId( trade.getTaskId() );
                pay.setTradeNo( trade.getTradeNo() );
                pay.setKind( 1 );  // 订单
                pay.setAmount( trade.getBalance() );
                pay.setSummary( "订单消费" );
                pay.setPayTime( new Date() );
                financeService.savePayment(pay);
            }

            TaskInfo tinfo = taskService.getTaskInfo(trade.getTaskId());
            if (trade.getPayFlag() == 0) {
                tinfo.setTaskState(TaskState.Paid);
                if (tinfo.getService() == 1) { // 市场标
                    tinfo.setAuditState( AuditState.Approved ); //自动审核通过
                }
            } else { // 提高赏金
                tinfo.setDesignBudget(tinfo.getDesignBudget().add(trade.getAmount()) ); // 增加预算

                if (tinfo.getService() == 1) { //市场标
                    if (tinfo.getInvoice() == 1) { //发票
                        tinfo.setFaxAmount( tinfo.getDesignBudget().multiply(new BigDecimal(0.06)) );  // 6%
                        tinfo.setDesignAmount( tinfo.getDesignBudget().subtract(tinfo.getFaxAmount()) );
                        tinfo.setServiceAmount( BigDecimal.ZERO );
                        tinfo.setTotalAmount( tinfo.getDesignBudget().add(new BigDecimal(8)) );
                    } else {
                        tinfo.setDesignAmount( tinfo.getDesignBudget() );
                        tinfo.setServiceAmount( BigDecimal.ZERO );
                        tinfo.setFaxAmount( BigDecimal.ZERO );
                        tinfo.setTotalAmount( tinfo.getDesignBudget() );
                    }
                } else if (tinfo.getService() == 2) { //管家标
                    if (tinfo.getInvoice() == 1) { //发票
                        tinfo.setServiceAmount( tinfo.getDesignBudget().multiply(new BigDecimal(0.15)) ); // 15%
                        tinfo.setFaxAmount( tinfo.getDesignBudget().multiply(new BigDecimal(0.06)) );  // 6%
                        tinfo.setDesignAmount( tinfo.getDesignBudget().subtract(tinfo.getFaxAmount()).subtract(tinfo.getServiceAmount()) );
                        tinfo.setTotalAmount( tinfo.getDesignBudget().add(new BigDecimal(8)) );
                    } else {
                        tinfo.setServiceAmount( tinfo.getDesignBudget().multiply(new BigDecimal(0.15)) ); // 15%
                        tinfo.setFaxAmount( BigDecimal.ZERO );
                        tinfo.setDesignAmount( tinfo.getDesignBudget().subtract(tinfo.getServiceAmount()) );
                        tinfo.setTotalAmount( tinfo.getDesignBudget() );
                    }
                }
            }

            taskService.updateTaskInfo(tinfo);

            return 1;
            
        }
    }

    @Transactional
    @Override
    public int updateTradeForTransferPay(Trade trade) {

        String lockStr = trade.getTradeNo().intern();
        synchronized (lockStr) {
            int n = financeService.checkPaymentForTrade(trade.getTradeNo());
            if (n > 0) {
                return 0; // 已处理
            }
            tradeMapper.updateTrade( trade);

            Payment pay = new Payment();
            pay.setUserId( trade.getUserId() );
            pay.setTaskId( trade.getTaskId() );
            pay.setTradeNo( trade.getTradeNo() );
            pay.setKind( 1 );  // 订单
            pay.setAmount( trade.getAmount() );
            pay.setSummary( "订单消费" );
            pay.setPayTime( new Date() );
            financeService.savePayment(pay);

            TaskInfo tinfo = taskService.getTaskInfo(trade.getTaskId());
            
            tinfo.setTaskState( TaskState.Paid );
            if (tinfo.getService() == 1) { // 市场标
                tinfo.setAuditState( AuditState.Approved ); //自动审核通过
            }

            taskService.updateTaskInfo(tinfo);

            return 1;
        }
    }

    @Override
    public Trade getTradeByTask(int taskId) {
        return tradeMapper.getTradeByTask( taskId);
    }

    @Override
    public String getTradeNoByTask(int taskId) {
        return tradeMapper.getTradeNoByTask( taskId);
    }

    @Override
    public PageList<Trade> findTradeList(
        TradeCondition cond, int pageNo, int pageSize) {

        PageList<Trade> pglist = new PageList<>(pageNo, pageSize);
        pglist.setTotal(tradeMapper.findTradeCount(cond));
        pglist.setList(tradeMapper.findTradeList(cond, pglist.getOffset(), pageSize));
        return pglist;
    }

        
}
