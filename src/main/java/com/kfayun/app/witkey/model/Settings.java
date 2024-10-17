/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.model;

/**
 * 配置
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public class Settings {

    // general setting.
	private String appName;
	private String appPcLogo;
	private String appMobLogo;
    private String appFootLogo;
    private String seoKeywords;
    private String seoDescription;
	private String cookieDomain;
	private String cookieName;
	private String cookieSecret;
    private String serviceTel; // 客服电话
    private String icpBeianNo; // ICP备案号
    private String wxQrcode;
	
	// pay setting
	private int alipayEnable;
    private String alipaySellerEmail;
	private String alipayVersion;
    private String alipayAppid;
    private String alipaySigntype;
    private String alipayRsaPrivatekey;
    private String alipayRsaPublickey;
    private String alipayMd5Key;
	private String alipayNotifyUrl;

	private int wxpayEnable;
    private String wxpayAppid;
    private String wxpayAppsecret;
    private String wxpayMchid;
    private String wxpayMchkey;
	private String wxpayNotifyUrl;

	/**
	 * 统计脚本
	 */
	private String statisScript;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppPcLogo() {
        return appPcLogo;
    }

    public void setAppPcLogo(String appPcLogo) {
        this.appPcLogo = appPcLogo;
    }

    public String getAppMobLogo() {
        return appMobLogo;
    }

    public void setAppMobLogo(String appMobLogo) {
        this.appMobLogo = appMobLogo;
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public String getCookieDomain() {
        return cookieDomain;
    }

    public void setCookieDomain(String cookieDomain) {
        this.cookieDomain = cookieDomain;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getCookieSecret() {
        return cookieSecret;
    }

    public void setCookieSecret(String cookieSecret) {
        this.cookieSecret = cookieSecret;
    }

    public String getAppFootLogo() {
        return appFootLogo;
    }

    public void setAppFootLogo(String appFootLogo) {
        this.appFootLogo = appFootLogo;
    }

    public String getWxQrcode() {
        return wxQrcode;
    }

    public void setWxQrcode(String wxQrcode) {
        this.wxQrcode = wxQrcode;
    }

    public int getAlipayEnable() {
        return alipayEnable;
    }

    public void setAlipayEnable(int alipayEnable) {
        this.alipayEnable = alipayEnable;
    }

    public String getAlipaySellerEmail() {
        return alipaySellerEmail;
    }

    public void setAlipaySellerEmail(String alipaySellerEmail) {
        this.alipaySellerEmail = alipaySellerEmail;
    }

    public String getAlipayVersion() {
        return alipayVersion;
    }

    public void setAlipayVersion(String alipayVersion) {
        this.alipayVersion = alipayVersion;
    }

    public String getAlipayAppid() {
        return alipayAppid;
    }

    public void setAlipayAppid(String alipayAppid) {
        this.alipayAppid = alipayAppid;
    }

    public String getAlipaySigntype() {
        return alipaySigntype;
    }

    public void setAlipaySigntype(String alipaySigntype) {
        this.alipaySigntype = alipaySigntype;
    }

    public String getAlipayRsaPrivatekey() {
        return alipayRsaPrivatekey;
    }

    public void setAlipayRsaPrivatekey(String alipayRsaPrivatekey) {
        this.alipayRsaPrivatekey = alipayRsaPrivatekey;
    }

    public String getAlipayRsaPublickey() {
        return alipayRsaPublickey;
    }

    public void setAlipayRsaPublickey(String alipayRsaPublickey) {
        this.alipayRsaPublickey = alipayRsaPublickey;
    }

    public String getAlipayMd5Key() {
        return alipayMd5Key;
    }

    public void setAlipayMd5Key(String alipayMd5Key) {
        this.alipayMd5Key = alipayMd5Key;
    }

    public String getAlipayNotifyUrl() {
        return alipayNotifyUrl;
    }

    public void setAlipayNotifyUrl(String alipayNotifyUrl) {
        this.alipayNotifyUrl = alipayNotifyUrl;
    }

    public int getWxpayEnable() {
        return wxpayEnable;
    }

    public void setWxpayEnable(int wxpayEnable) {
        this.wxpayEnable = wxpayEnable;
    }

    public String getWxpayAppid() {
        return wxpayAppid;
    }

    public void setWxpayAppid(String wxpayAppid) {
        this.wxpayAppid = wxpayAppid;
    }

    public String getWxpayAppsecret() {
        return wxpayAppsecret;
    }

    public void setWxpayAppsecret(String wxpayAppsecret) {
        this.wxpayAppsecret = wxpayAppsecret;
    }

    public String getWxpayMchid() {
        return wxpayMchid;
    }

    public void setWxpayMchid(String wxpayMchid) {
        this.wxpayMchid = wxpayMchid;
    }

    public String getWxpayMchkey() {
        return wxpayMchkey;
    }

    public void setWxpayMchkey(String wxpayMchkey) {
        this.wxpayMchkey = wxpayMchkey;
    }

    public String getWxpayNotifyUrl() {
        return wxpayNotifyUrl;
    }

    public void setWxpayNotifyUrl(String wxpayNotifyUrl) {
        this.wxpayNotifyUrl = wxpayNotifyUrl;
    }

    public String getStatisScript() {
        return statisScript;
    }

    public void setStatisScript(String statisScript) {
        this.statisScript = statisScript;
    }

    public String getServiceTel() {
        return serviceTel;
    }

    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel;
    }

    public String getIcpBeianNo() {
        return icpBeianNo;
    }

    public void setIcpBeianNo(String icpBeianNo) {
        this.icpBeianNo = icpBeianNo;
    }

    
}
