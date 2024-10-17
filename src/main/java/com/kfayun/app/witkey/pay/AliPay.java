/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.pay;

import com.kfayun.app.witkey.model.Trade;
import com.kfayun.app.witkey.JsonResult;

import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Map;

/**
 * 支付宝支付接口
 * 
 * @author billy (billy_zh@126.com)
 */
public interface AliPay {

	/**
	 * 获取回调通知URL
	 * 
	 * @return
	 */
	String getNotifyUrl();

	/**
	 * 获取WEB端支付数据
	 * @param orderData
	 * @return
	 */
	JsonResult<String> getPayDataForWeb(
			@RequestBody Map<String, String> orderData) throws IOException;
	
	/**
	 * 获取WAP端支付数据
	 * 
	 * @param orderData
	 * @return
	 */
	JsonResult<String> getPayDataForWap(
			@RequestBody Map<String, String> orderData) throws IOException;
	
	/**
	 * 获取移动端（原生）支付数据
	 * 
	 * @param orderData
	 * @return
	 */
	JsonResult<Map<String, String>> getPaySignForMob(
			@RequestBody Map<String, String> orderData);
	
	/**
	 * 验证支付回调数据
	 * 
	 * @param tradeData
	 * @return
	 */
	JsonResult<Trade> verifyTradeCallback(
			 Map<String, String> tradeData);

	/**
	 * 验证支付通知数据
	 * 
	 * @param tradeData
	 * @return
	 */
	JsonResult<Trade> verifyTradeNotifying(
			 Map<String, String> tradeData);
}
