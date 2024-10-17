/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.pay;

import java.util.Date;
import java.util.Map;

import com.kfayun.app.witkey.JsonResult;
import com.kfayun.app.witkey.service.TradeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.kfayun.app.witkey.model.Trade;
import com.kfayun.app.witkey.third.wxpay.WXPay;
import com.kfayun.app.witkey.third.wxpay.WXPayUtil;

/**
 * 微信支付
 * 
 * @author billy (billy_zh@126.com)
 */
@Component
public class WeixinPay {

	private static Logger log = LoggerFactory.getLogger(WeixinPay.class);

	@Autowired
	private TradeService tradeService;
	@Autowired
	private PayConfig payConfig;

	/**
	 * 获取支付数据，通过统一下单接口
	 * 
	 * @param orderData
	 * @return
	 */
	public JsonResult<Map<String, String>> getPayOrderData(
			@RequestBody Map<String, String> orderData) {

		try {
			WXPay wxPay = new WXPay(payConfig.getWxpayConfig());
			Map<String, String> data = wxPay.unifiedOrder(orderData);

			return JsonResult.ok(data);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			return JsonResult.fail(-1, ex.getMessage());
		}
	}

	/**
	 * 校验通知回调数据
	 * 
	 * @param tradeStr
	 * @return
	 */
	public JsonResult<Trade> verifyTradeNotifying(
			@RequestBody String tradeStr) {
		try {
			Map<String, String> notifyData = WXPayUtil.xmlToMap(tradeStr);
	        
	        if (!notifyData.containsKey("transaction_id")) {
	            //若transaction_id不存在，则立即返回结果给微信支付后台
	            return JsonResult.fail(-1, "支付结果中微信订单号不存在");
	        }
	
	         WXPay wxPay = new WXPay(payConfig.getWxpayConfig());
		         
			if(!wxPay.isSignatureValid(notifyData)) {//验证成功
				//若订单查询失败，则立即返回结果给微信支付后台
	            return JsonResult.fail(-1, "订单查询失败");
	            
			} 
			
            String transaction_id = notifyData.get("transaction_id");
            String out_trade_no = notifyData.get("out_trade_no");
			String returnCode = notifyData.get("return_code");
			String buyerId = notifyData.get("openid");
            
		    Trade trade = tradeService.getTrade(out_trade_no);
		     
		    trade.setPayTime( new Date() );
	    	trade.setThirdNo( transaction_id );
	        trade.setThirdBuyer( buyerId );
	        trade.setNotifyStatus( returnCode );
	        trade.setNotifyTime( new Date() );

			if ("SUCCESS".equals(returnCode)) {
	        	trade.setPayState(1);
	        }
			tradeService.updateTrade(trade);
	
	        return JsonResult.ok(trade);
	
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			return JsonResult.fail(-1, ex.getMessage());
		}
	}
	
}
