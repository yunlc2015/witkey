/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kfayun.app.witkey.*;
import com.kfayun.app.witkey.condition.*;
import com.kfayun.app.witkey.manage.aop.*;
import com.kfayun.app.witkey.util.*;
import com.kfayun.app.witkey.service.*;
import com.kfayun.app.witkey.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 财务管理Controller
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
@RequestMapping("/manage/fin")
@Controller
public class ManageFinanceController {

    @Autowired
    private TradeService tradeService;
    @Autowired
    private FinanceService financeService;
    @Autowired
    private TaskService taskService;

    /**
     * 交易记录页
     * 
     * @param pageNo
     * @return
     */
    @GetMapping("tradelist")
    public ModelAndView tradeList(
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo) {

        ModelAndView mv = new ModelAndView();

        TradeCondition cond = new TradeCondition();
        cond.setPayState( -1 );  // all

        int pageSize = 15;
        PageList<Trade> pglist = tradeService.findTradeList(cond, pageNo, pageSize);
        PagerInfo pager = new PagerInfo(pageNo, pageSize);
        pager.setTotalCount(pglist.getTotal());
        pager.setBaseUrl("tradelist");

        mv.addObject("tradeList", pglist.getList());
        mv.addObject("pager", pager);

        mv.setViewName("manage/fin/tradelist");
        return mv;
    }

    /**
     * 付款记录页
     * 
     * @param pageNo
     * @return
     */
    @GetMapping("paymentlist")
    public ModelAndView paymentList(
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo) {

        ModelAndView mv = new ModelAndView();

        PaymentCondition cond = new PaymentCondition();

        int pageSize = 15;
        PageList<Payment> pglist = financeService.findPaymentList(cond, pageNo, pageSize);
        PagerInfo pager = new PagerInfo(pageNo, pageSize);
        pager.setTotalCount(pglist.getTotal());
        pager.setBaseUrl("paymentlist");

        mv.addObject("paymentList", pglist.getList());
        mv.addObject("pager", pager);

        mv.setViewName("manage/fin/paymentlist");
        return mv;
    }

    /**
     * 提现记录页
     * 
     * @param pageNo
     * @return
     */
    @GetMapping("withdrawallist")
    public ModelAndView withdrawalList(
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo) {

        ModelAndView mv = new ModelAndView();

        WithdrawalCondition cond = new WithdrawalCondition();

        int pageSize = 15;
        PageList<Withdrawal> pglist = financeService.findWithdrawalList(cond, pageNo, pageSize);
        PagerInfo pager = new PagerInfo(pageNo, pageSize);
        pager.setBaseUrl("withdrawallist");
      
        mv.addObject("withdrawalList", pglist.getList());
        mv.addObject("pager", pager);
        
        mv.setViewName("manage/fin/withdrawallist");
        return mv;
    }

    /**
     * 发票记录页
     * 
     * @param pageNo
     * @return
     */
    @GetMapping("invoicelist")
    public ModelAndView invoiceList(
            @RequestParam(value="pageno", required=false, defaultValue="1")int pageNo) {

        ModelAndView mv = new ModelAndView();

        TaskCondition cond = new TaskCondition();
        cond.setTaskState( TaskState.Finished.getConstant() );
        cond.setAuditState( AuditState.Approved.getConstant() );
        cond.setInvoice( 1 );
        
        int pageSize = 15;
        PageList<TaskInfo> pglist = taskService.findTaskInfoList(cond, pageNo, pageSize);
        PagerInfo pager = new PagerInfo(pageNo, pageSize);
        pager.setTotalCount(pglist.getTotal());
        pager.setBaseUrl("invoicelist");

        mv.addObject("taskList", pglist.getList());
        mv.addObject("pager", pager);

        mv.setViewName("manage/fin/invoicelist");
        return mv;
    }

    @GetMapping("withdrawalsettle")
    public ModelAndView withdrawalSettle() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("manage/fin/withdrawalsettle");
        return mv;
    }

    @ManageOperate(ManageAction.FIN_WITHDRAWAL_SETTLE)
    @PostMapping("withdrawalsettle")
    @ResponseBody
    public JsonResult<Integer> withdrawalSettlePost() {
        
        return JsonResult.fail(-1, "TODO:");
    }

}
