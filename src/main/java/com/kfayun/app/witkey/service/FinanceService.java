/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service;

import java.math.BigDecimal;

import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.model.*;
import com.kfayun.app.witkey.condition.*;

/**
 * 财务服务接口
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public interface FinanceService {
    
    /**
     * 获取帐户号
     * 
     * @param accountNo
     * @return
     */
    public String getAccountNo(String accountNo);

    /**
     * 获取提现状态文本
     * 
     * @param state
     * @return
     */
    public String getWithdrawalStateText(int state);

    /**
     * 查找付款列表
     * 
     * @param cond
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageList<Payment> findPaymentList(
        PaymentCondition cond, int pageNo, int pageSize);

    /**
     * 获取付款信息
     * 
     * @param id
     * @return
     */
    public Payment getPayment(int id);

    /**
     * 保存付款
     * 
     * @param payment
     */
    public void savePayment(Payment payment);

    /**
     * 更新付款信息
     * 
     * @param payment
     * @return
     */
    public int updatePayment(Payment payment);

    /**
     * 检查交易的付款情况
     * 
     * @param tradeNo 交易编号
     * @return
     */
    public int checkPaymentForTrade(String tradeNo);
    
    /**
     * 获取任务的付款信息
     * 
     * @param taskId
     * @param userId
     * @return
     */
    public Payment getPaymentByTaskAndUser(int taskId, int userId);

    /**
     * 获取任务的发票信息
     * 
     * @param taskId
     * @return
     */
    public Invoice getInvoiceByTask(int taskId);

    /**
     * 保存发票信息
     * 
     * @param invoice
     */
    public void saveInvoice(Invoice invoice);

    /**
     * 获取交易Hash码
     * 
     * @param userId
     * @param bank
     * @param amount
     * @return
     */
    public String getWithdrawHash(int userId, int bank, BigDecimal amount);

    /**
     * 查找提现列表
     * 
     * @param cond
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageList<Withdrawal> findWithdrawalList(
        WithdrawalCondition cond, int pageNo, int pageSize);

    /**
     * 获取提现信息
     * 
     * @param id
     * @return
     */
    public Withdrawal getWithdrawal(int id);

    /**
     * 更新提现信息
     * 
     * @param withdrawal
     * @return
     */
    public int updateWithdrawal(Withdrawal withdrawal);

    /**
     * 获取付款对应的提现信息
     * 
     * @param paymentId
     * @return
     */
    public Withdrawal getWithdrawalByPayment(int paymentId);

    /**
     * 提现处理
     * 
     * @param userId
     * @param amount
     * @param account
     * @return
     */
    public int doWithdraw(int userId, int bankId, BigDecimal amount);

}
