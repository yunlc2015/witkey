/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service.impl;

import java.util.Date;
import java.util.Map;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.kfayun.app.witkey.Constants;
import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.util.CryptoUtil;
import com.kfayun.app.witkey.condition.PaymentCondition;
import com.kfayun.app.witkey.condition.WithdrawalCondition;
import com.kfayun.app.witkey.model.BankAccount;
import com.kfayun.app.witkey.model.Invoice;
import com.kfayun.app.witkey.model.Payment;
import com.kfayun.app.witkey.model.User;
import com.kfayun.app.witkey.model.TaskInfo;
import com.kfayun.app.witkey.model.TaskState;
import com.kfayun.app.witkey.model.Withdrawal;
import com.kfayun.app.witkey.dao.FinanceMapper;
import com.kfayun.app.witkey.service.UserService;
import com.kfayun.app.witkey.service.FinanceService;
import com.kfayun.app.witkey.service.TaskService;

/**
 * 财务服务接口实现
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private FinanceMapper financeMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @Override
    public String getAccountNo(String accountNo) {
        if (accountNo.length() < 5) {
            return accountNo;
        }
        
        StringBuffer sb = new StringBuffer(accountNo);
        int n = sb.length() > 16 ? 16 : 12;
        for (int i = 4; i < n; i++) {
            sb.replace(i, i+1, "*");
        }

        while (n > 0) {
            sb.insert(n, ' ');
            n -= 4;
        }

        return sb.toString();
    }

    @Override
    public String getWithdrawalStateText(int state) {
        String text = "";
        switch (state)
        {
            case 0:
                text = "等待处理";
                break;
            case 1:
                text = "交易成功";
                break;
        }

        return text;
    }

    @Override
    public PageList<Payment> findPaymentList(
        PaymentCondition cond, int pageNo, int pageSize) {
            PageList<Payment> pglist = new PageList<>(pageNo, pageSize);
        pglist.setTotal( financeMapper.findPaymentCount(cond) );
        pglist.setList( financeMapper.findPaymentList(cond, pglist.getOffset(), pageSize) );
            return pglist;
    }

    @Override
    public void savePayment(Payment pay) {
        financeMapper.insertPayment(pay);
    }

    @Override
    public Payment getPayment(int payId) {
        return financeMapper.getPayment(payId);
    }

    @Override
    public int updatePayment(Payment pay) {
        return financeMapper.updatePayment(pay);
    }

    @Override
    public int checkPaymentForTrade(String tradeNo) {
        return financeMapper.checkPaymentForTrade(tradeNo);
    }

    @Override
    public Payment getPaymentByTaskAndUser(int taskId, int userId) {
        return financeMapper.getPaymentByTaskAndUser(taskId, userId);
    }

    @Override
    public Invoice getInvoiceByTask(int taskId) {
        return financeMapper.getInvoiceByTask(taskId);
    }

    @Override
    public void saveInvoice(Invoice inv) {
        financeMapper.insertInvoice(inv);
    }

    @Override
    public String getWithdrawHash(int userId, int bank, BigDecimal amount) {
        String str = String.format("%s_%d_%d_%f", Constants.MD5_KEY, userId, bank, amount);
        return CryptoUtil.MD5(str);
    }

    @Override
    public PageList<Withdrawal> findWithdrawalList(
        WithdrawalCondition cond, int pageNo, int pageSize) {
        PageList<Withdrawal> pglist = new PageList<>(pageNo, pageSize);
        pglist.setTotal( financeMapper.findWithdrawalCount(cond) );
        pglist.setList( financeMapper.findWithdrawalList(cond, pglist.getOffset(), pageSize) );
        return pglist;
    }

    @Override
    public Withdrawal getWithdrawal(int id) {
        return financeMapper.getWithdrawal(id);
    }

    @Override
    public int updateWithdrawal(Withdrawal wd) {
        return financeMapper.updateWithdrawal(wd);
    }

    @Override
    public Withdrawal getWithdrawalByPayment(int payId) {
        return financeMapper.getWithdrawalByPayment(payId);
    }

    @Transactional
    @Override
    public int doWithdraw(int userId, int bankId, BigDecimal amount) {
        
        User user = userService.getUser(userId);
        if (user.getBalance().compareTo(amount)  == -1) { // 余额不足
            return 2;
        }

        BankAccount account = userService.getBankAccount(bankId, userId);

        // step 1 减余额(加负数)
        userService.updateUserBalance(user.getId(), BigDecimal.ZERO.subtract(amount) );

        // step 2 生成Payment
        Payment pay = new Payment();
        pay.setAmount(amount);
        pay.setUserId(userId);
        pay.setKind(4); // 提现
        pay.setSummary( "提现" );
        pay.setPayTime( new Date() );
        financeMapper.insertPayment(pay);

        // step 3 生成Withdrawal
        Withdrawal wd = new Withdrawal();
        wd.setUserId(userId);
        wd.setAmount(amount);
        wd.setPaymentId(pay.getId());
        wd.setBankId(account.getBankId());
        wd.setBankAccName(account.getAccountName());
        wd.setBankAccNo(account.getAccountNo());
        wd.setRequestTime( new Date() );
        financeMapper.insertWithdrawal(wd);

        return 1;
        
    }

}
