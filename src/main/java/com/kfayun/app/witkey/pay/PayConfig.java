/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.kfayun.app.witkey.service.SysService;
import com.kfayun.app.witkey.model.Settings;
import com.kfayun.app.witkey.third.alipay.AlipayConfig;
import com.kfayun.app.witkey.third.wxpay.WXPayConfig;

/**
 * 支付配置
 * 
 * @author billy (billy_zh@126.com)
 */
@Component
public class PayConfig {
    
    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private SysService sysService;
    
    /**
     * 获取支付宝支付配置
     * 
     * @return
     */
    public AlipayConfig getAlipayConfig() {

        Settings sett = sysService.getSettings();
        String signType = sett.getAlipaySigntype();
        if ("MD5".equalsIgnoreCase(signType)) {
            return new AlipayConfig(
                    sett.getAlipaySellerEmail(),
                    sett.getAlipayAppid(),
                    sett.getAlipayMd5Key(),
                    "",
                    signType
            );
        } else {
            return new AlipayConfig(
                    sett.getAlipaySellerEmail(),
                    sett.getAlipayAppid(),
                    sett.getAlipayRsaPrivatekey(),
                    sett.getAlipayRsaPublickey(),
                    signType
            );
        }
    }

    /**
     * 获取微信支付配置
     * 
     * @return
     */
    public WXPayConfig getWxpayConfig() {

        Settings sett = sysService.getSettings();
        return new WXPayConfig(
                sett.getWxpayAppid(),
                sett.getWxpayAppsecret(),
                sett.getWxpayMchid(),
                sett.getWxpayMchkey()
        );

    }

    /**
     * 获取支付宝支付对象
     * 
     * @return
     */
    public AliPay getAliPay() {

        Settings sett = sysService.getSettings();
        String ver = sett.getAlipayVersion();
        if ("V1".equalsIgnoreCase(ver)) {
            AliPayV1 pay = new AliPayV1(this, sett.getAlipayNotifyUrl());
            appContext.getAutowireCapableBeanFactory().autowireBean(pay);
            return pay;
        }

        AliPayV2 payV2 = new AliPayV2(this, sett.getAlipayNotifyUrl());
        appContext.getAutowireCapableBeanFactory().autowireBean(payV2);
        return payV2;
    }

}
