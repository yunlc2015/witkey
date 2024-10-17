/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.vo;

import java.math.BigDecimal;

import com.kfayun.app.witkey.model.Withdrawal;
import com.kfayun.app.witkey.util.DateUtil;

public class WithdrawalVO {

    private Withdrawal withdrawal;
    private String detail;
    private String stateText;

    public WithdrawalVO(Withdrawal withdrawal) {
        this.withdrawal = withdrawal;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStateText() {
        return stateText;
    }

    public void setStateText(String stateText) {
        this.stateText = stateText;
    }

    public BigDecimal getAmount() {
        return withdrawal.getAmount();
    }

    public String getBankAccName() {
        return withdrawal.getBankAccName();
    }

    public String getBankAccNo() {
        return withdrawal.getBankAccNo();
    }

    public String getRequestDate() {
        return DateUtil.toDateString(withdrawal.getRequestTime());
    }
}
