/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.kfayun.app.witkey.model.Payment;
import com.kfayun.app.witkey.util.DateUtil;

public class PaymentVO {

    private Payment payment;
    private String stateText;
    private String detail;

    public PaymentVO(Payment payment) {
        this.payment = payment;
    }

    public String getStateText() {
        return stateText;
    }

    public void setStateText(String stateText) {
        this.stateText = stateText;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BigDecimal getAmount() {
        return payment.getAmount();
    }

    public String getSummary() {
        return payment.getSummary();
    }

    public String getPayDate() {
        return DateUtil.toDateString(payment.getPayTime());
    }

}
