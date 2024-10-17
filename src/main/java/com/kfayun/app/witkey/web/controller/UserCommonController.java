package com.kfayun.app.witkey.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kfayun.app.witkey.Constants;
import com.kfayun.app.witkey.JsonResult;
import com.kfayun.app.witkey.model.*;
import com.kfayun.app.witkey.service.*;
import com.kfayun.app.witkey.web.aop.UserOperate;
import com.kfayun.app.witkey.web.aop.UserAction;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * 用户Controller
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Controller
@RequestMapping("/user")
public class UserCommonController extends BaseController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private VerifyService verifyService;
    @Autowired
    private FinanceService financeService;

    /**
     * 银行添加页
     * 
     * @param bank 银行ID
     * @return
     */
    @GetMapping("addbank")
    public ModelAndView addBank(@RequestParam("bank")int bank) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("bank", bank);
        mv.setViewName("user/addbank");

        return mv;
    }

    /**
     * 银行添加处理
     * 
     * @param params 表单参数
     * @return
     */
    @UserOperate(UserAction.USER_ADDBANK)
    @PostMapping("addbank")
    @ResponseBody
    public JsonResult<Integer> addBankPost(@RequestParam Map<String, String> params,
            HttpServletRequest request) {

        try {
            String mobile = params.get("mobile");
            String validcode = params.get("validcode");
            VerifyCode vcode = verifyService.getVerifyCodeByData(mobile, validcode, "bankadd");
            if (vcode == null) {
                return JsonResult.fail(-1, "验证码不正确。");
            }

            User user = getCurrentUser(request);

            BankAccount ba = new BankAccount();
            ba.setAccountName( params.get("accountname") );
            ba.setAccountNo( params.get("accountno") );
            ba.setIdCard( params.get("idcard") );
            ba.setMobile( mobile );
            ba.setUserId( user.getId());
            ba.setBankId( getInt(params.get("bank"), 0) );

            userService.saveBankAccount(ba);
            return JsonResult.ok(1);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
               
    }

    /**
     * 提现页
     * 
     * @return
     */
    @GetMapping("withdraw")
    public ModelAndView withdraw(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();

        User user = getCurrentUser(request);
        mv.addObject("user", user);

        List<BankAccount> accountList = userService.getBankAccountList(user.getId());
        mv.addObject("accountList", accountList);

        return mv;
    }

    /**
     * 提现确认页
     * 
     * @param params 表单参数
     * @return
     */
    @PostMapping("withdrawconfirm")
    public ModelAndView withdrawConfirm(@RequestParam Map<String, String> params,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        ModelAndView mv = new ModelAndView();

        int bank = getInt(params.get("bank"), 0);
        BigDecimal amount = new BigDecimal(params.get("amount"));

        if (bank == 0 || amount.compareTo(BigDecimal.ZERO) <= 0) {
            response.sendRedirect("withdraw");
            return mv;
        }

        User user = getCurrentUser(request);
        BankAccount bankAcc = userService.getBankAccount(bank, user.getId());
        if (bankAcc == null) {
            response.sendRedirect("withdraw");
            return mv;
        }

        mv.addObject("user", user);
        mv.addObject("account", bankAcc);

        String accountNo = financeService.getAccountNo(bankAcc.getAccountNo());
        String hcode = financeService.getWithdrawHash(user.getId(), bank, amount);

        SimpleDateFormat fmt = new SimpleDateFormat("MM月dd日24点");
        Calendar cal = Calendar.getInstance();
        if (cal.get(Calendar.HOUR_OF_DAY) > 15) {
            cal.add(Calendar.DAY_OF_YEAR, 1);
        }
        String dzTime = fmt.format(cal.getTime());

        mv.addObject("amount", amount);
        mv.addObject("accountNo", accountNo);
        mv.addObject("hcode", hcode);
        mv.addObject("dzTime", dzTime);

        mv.setViewName("user/withdrawconfirm");
        return mv;
    }

    /**
     * 提现操作
     * 
     * @param params 表单参数
     * @return
     */
    @UserOperate(UserAction.USER_WITHDRAW)
    @PostMapping("dowithdraw")
    @ResponseBody
    public JsonResult<Integer> doWithdraw(@RequestParam Map<String, String> params,
            HttpServletRequest request) {

        try {
            int bank = getInt(params.get("bank"), 0);
            BigDecimal amount = new BigDecimal(params.get("amount"));
            String hcode = params.get("hcode");
            String validcode = params.get("validcode");

            User user = getCurrentUser(request);
            String md5 = financeService.getWithdrawHash(user.getId(), bank, amount);
            if (!hcode.equals(md5)) {
                return JsonResult.fail(-1, "无效的请求。");
            }

            VerifyCode vcode = verifyService.getVerifyCodeByData(user.getMobile(), validcode, "withdraw");
            if (vcode == null) {
                return JsonResult.fail(-2, "验证码不正确。");
            }

            if (user.getBalance().compareTo(amount) < 0) {
                return JsonResult.fail(-3, "帐户余额不足。");
            }

            int n = financeService.doWithdraw(user.getId(), bank, amount);
            if (n == 1) { // 成功
                return JsonResult.ok(1);
            } else if (n == 2)  { // 余额不足
                return JsonResult.fail(-3, "帐户余额不足。");
            }
            
            return JsonResult.ok(0);
        } catch (Exception ex) {
            handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
        }
                     
    }
}
