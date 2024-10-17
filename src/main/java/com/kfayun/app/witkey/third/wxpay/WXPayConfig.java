package com.kfayun.app.witkey.third.wxpay;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class WXPayConfig {

    private byte[] certData;
    private String appId;
    private String appSecret;
    private String mchId;
    private String mchKey;
    
    public WXPayConfig(String appId, String appSecret, String mchId, String mchKey) {
	    	this.appId = appId;
	    	this.appSecret = appSecret;
	    	this.mchId = mchId;
	    	this.mchKey = mchKey;
    	
        try {
        	 	Resource resource = new ClassPathResource("apiclient_cert.p12");
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	        IOUtils.copy(resource.getInputStream(), outStream);
	        this.certData = outStream.toByteArray();
        } catch (Exception ex) {
        		ex.printStackTrace();
        }
    }

    public String getAppID() {
        return appId;
    }

    public String getAppSecret() { return appSecret; }

    public String getMchID() {
        return mchId;
    }

    public String getMchKey() {
        return mchKey;
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }


    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    public WXPayDomainSimple getWXPayDomain() {
        return WXPayDomainSimple.instance();
    }

    public String getPrimaryDomain() {
        return "api.mch.weixin.qq.com";
    }

    public String getAlternateDomain() {
        return "api2.mch.weixin.qq.com";
    }

    public int getReportWorkerNum() {
        return 1;
    }

    public int getReportBatchSize() {
        return 2;
    }
    
    public boolean shouldAutoReport() {
        return true;
    }

    public int getReportQueueMaxSize() {
        return 10000;
    }



}
