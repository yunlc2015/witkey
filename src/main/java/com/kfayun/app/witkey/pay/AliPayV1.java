/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.pay;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.kfayun.app.witkey.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.kfayun.app.witkey.service.TradeService;
import com.kfayun.app.witkey.third.alipay.AlipayConfig;
import com.kfayun.app.witkey.third.alipay.AlipayFunction;
import com.kfayun.app.witkey.third.alipay.AlipayNotify;
import com.kfayun.app.witkey.third.alipay.AlipayService;
import com.kfayun.app.witkey.third.alipay.RsaEncrypt;
import com.kfayun.app.witkey.JsonResult;

/**
 * 支付宝支付接口实现
 * 
 * @author billy (billy_zh@126.com)
 */
public class AliPayV1 implements AliPay {
    
	@Autowired
	private TradeService tradeService;

	private PayConfig payConfig;
	private String notifyUrl;

	public AliPayV1(PayConfig payConfig, String notifyUrl) {
		this.payConfig = payConfig;
		this.notifyUrl = notifyUrl;
	}

	@Override
	public String getNotifyUrl() {
		return notifyUrl;
	}

	@Override
	public JsonResult<String> getPayDataForWeb(
			@RequestBody Map<String, String> orderData) throws IOException {
	
		AlipayConfig config = payConfig.getAlipayConfig();  // MD5
		orderData.put("partner", config.partner);
		orderData.put("seller_email", config.seller_email);
		orderData.put("_input_charset", AlipayConfig.input_charset);
		
        String form = AlipayService.BuildForm(config, orderData);
		
		return JsonResult.ok(form);
		
	}

	@Override
	public JsonResult<String> getPayDataForWap(
			@RequestBody Map<String, String> orderData) throws IOException {
	
		AlipayConfig config = payConfig.getAlipayConfig();  // MD5
		orderData.put("partner", config.partner);
		orderData.put("seller_id", config.seller_email);
		orderData.put("_input_charset", AlipayConfig.input_charset);
		
		String form = AlipayService.BuildForm(config, orderData);
		
		return JsonResult.ok(form);
		
	}

	@Override
	public JsonResult<Map<String, String>> getPaySignForMob(
			@RequestBody Map<String, String> orderData) {
	
		AlipayConfig config = payConfig.getAlipayConfig();  // RSA
		
		orderData.put("partner", "\""+config.partner + "\"");
		orderData.put("seller_id", "\""+config.seller_email + "\"");
		orderData.put("_input_charset", "\""+AlipayConfig.input_charset + "\"");
		
		String orderInfo = AlipayFunction.CreateLinkString(orderData);
		
        String sign = RsaEncrypt.sign(orderInfo, config.privateKey, "utf-8");

        Map<String, String> data = new HashMap<>();
        data.put("sign", sign);
        data.put("orderInfo", orderInfo);
        
        return JsonResult.ok(data);
	}
	
	/**
	 * 支付宝交易回调验证
	 */
	@Override
	public JsonResult<Trade> verifyTradeCallback(
			@RequestBody Map<String, String> tradeData) {
		
		//商户订单号
		String out_trade_no = tradeData.get("out_trade_no");

		Trade trade = tradeService.getTrade(out_trade_no);
    	
		String signType = "MD5";
		if (tradeData.containsKey("sign_type")) {
			signType = tradeData.get("sign_type");
		}
		
		boolean verify_result = false;
		AlipayConfig config = null;
		
		if ("MD5".equalsIgnoreCase(signType)) {
			config = payConfig.getAlipayConfig();
         
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			//计算得出通知验证结果
			verify_result = AlipayNotify.Verify(config, tradeData, tradeData.get("notify_id"), tradeData.get("sign"));
		} else if ("RSA".equalsIgnoreCase(signType)) {
			config = payConfig.getAlipayConfig();
			
			Map<String, String> paramNew = AlipayFunction.ParaFilter(tradeData);
			String content = AlipayFunction.CreateLinkString(paramNew);
			verify_result = RsaEncrypt.verify(content, tradeData.get("sign"), config.publicKey, "utf-8");
		}
		
		if(!verify_result) {
			return JsonResult.fail(-1, "验证失败。");
		}

		//验证成功
		//////////////////////////////////////////////////////////////////////////////////////////
		//请在这里加上商户的业务逻辑程序代码
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		
		//支付宝交易号
		String trade_no = tradeData.get("trade_no");
		//交易状态
		String trade_status = tradeData.get("trade_status");
		//买家
		String buyer_email = tradeData.get("buyer_email");

		// 交易状态
		trade.setThirdNo( trade_no );
		trade.setThirdBuyer( buyer_email );
		trade.setThirdStatus( trade_status );
		trade.setPayTime( new Date() );

		if ( trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS") ) {
			trade.setPayState(1);
			tradeService.updateTrade(trade);

		} else {
			tradeService.updateTrade(trade);
		}

		return JsonResult.ok(trade);
		
	}

	/**
	 * 支付宝交易通知验证
	 */
	@Override
	public JsonResult<Trade> verifyTradeNotifying(
			@RequestBody Map<String, String> tradeData) {
		
		//商户订单号
		String out_trade_no = tradeData.get("out_trade_no");

		Trade trade = tradeService.getTrade(out_trade_no);
    	
		String signType = "MD5";
		if (tradeData.containsKey("sign_type")) {
			signType = tradeData.get("sign_type");
		}
		
		boolean verify_result = false;
		AlipayConfig config = null;
		
		if ("MD5".equalsIgnoreCase(signType)) {
			config = payConfig.getAlipayConfig();
         
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			//计算得出通知验证结果
			verify_result = AlipayNotify.Verify(config, tradeData, tradeData.get("notify_id"), tradeData.get("sign"));
		} else if ("RSA".equalsIgnoreCase(signType)) {
			config = payConfig.getAlipayConfig();
			
			Map<String, String> paramNew = AlipayFunction.ParaFilter(tradeData);
			String content = AlipayFunction.CreateLinkString(paramNew);
			verify_result = RsaEncrypt.verify(content, tradeData.get("sign"), config.publicKey, "utf-8");
		}
		
		if(!verify_result) {
			return JsonResult.fail(-1, "验证失败。");
		}

		//验证成功
		//////////////////////////////////////////////////////////////////////////////////////////
		//请在这里加上商户的业务逻辑程序代码
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		
		//支付宝交易号
		String trade_no = tradeData.get("trade_no");
		//交易状态
		String trade_status = tradeData.get("trade_status");
		//买家
		String buyer_id = tradeData.get("buyer_email");

		// 交易状态
		trade.setThirdNo( trade_no );
		trade.setThirdBuyer( buyer_id );
		trade.setNotifyStatus("SUCCESS");
		trade.setNotifyTime( new Date() );

		if ( trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS") ) {
			trade.setPayState(1);
			tradeService.updateTrade(trade);

		} else {
			tradeService.updateTrade(trade);
		}

		return JsonResult.ok(trade);
		
	}
}
