/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.dao;

import java.util.List;

import com.kfayun.app.witkey.condition.PaymentCondition;
import com.kfayun.app.witkey.condition.WithdrawalCondition;
import com.kfayun.app.witkey.model.Invoice;
import com.kfayun.app.witkey.model.Payment;
import com.kfayun.app.witkey.model.Withdrawal;

import org.apache.ibatis.annotations.Param;

/**
 * 财务相关实现Mapper
 * 
 * @author billy (billy_zh@126.com)
 */
public interface FinanceMapper {
    
    public int checkPaymentForTrade(
        @Param("tradeNo")String trade);

    public void insertPayment(Payment payment); 

    public int findPaymentCount(
        @Param("cond")PaymentCondition cond);

    public List<Payment> findPaymentList(
        @Param("cond")PaymentCondition cond, 
        @Param("offset")int offset, 
        @Param("limit")int limit);

    public Payment getPayment(
        @Param("id")int id);

    public int updatePayment(Payment payment);

    public Payment getPaymentByTaskAndUser(
        @Param("taskId")int taskId, 
        @Param("userId")int userId);

    public Invoice getInvoiceByTask(
        @Param("taskId")int taskId);

    public void insertInvoice(Invoice invoice);

    public int findWithdrawalCount(
        @Param("cond")WithdrawalCondition cond);

    public List<Withdrawal> findWithdrawalList(
        @Param("cond")WithdrawalCondition cond, 
        @Param("offset")int offset, 
        @Param("limit")int limit);

    public Withdrawal getWithdrawal(
        @Param("id")int id);

    public int insertWithdrawal(Withdrawal wd);

    public int updateWithdrawal(Withdrawal wd);

    public Withdrawal getWithdrawalByPayment(
        @Param("paymentId")int paymentId);


}
