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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.kfayun.app.witkey.service.TradeService;
import com.kfayun.app.witkey.third.alipay.AlipayConfig;
import com.kfayun.app.witkey.third.alipay.AlipayFunction;
import com.kfayun.app.witkey.JsonResult;

/**
 * 支付宝支付接口实现（V2新版）
 * 
 * @author billy (billy_zh@126.com)
 */
public class AliPayV2 implements AliPay {
    
	private static final Logger log = LoggerFactory.getLogger(AliPayV2.class);

	@Autowired
	private TradeService tradeService;

	private PayConfig payConfig;
	private String notifyUrl;

	public AliPayV2(PayConfig payConfig, String notifyUrl) {
		this.payConfig = payConfig;
		this.notifyUrl = notifyUrl;
	}

	@Override
	public String getNotifyUrl() {
		return notifyUrl;
	}

	@Override
	public JsonResult<Map<String, String>> getPaySignForMob(
			Map<String, String> orderData) {
	
		AlipayConfig config = payConfig.getAlipayConfig();
		
		orderData.put("partner", "\""+ config.partner + "\"");
		orderData.put("seller_id", "\""+ config.seller_email + "\"");
		orderData.put("_input_charset", "\""+ AlipayConfig.input_charset + "\"");
		
		try {
			String sign = AlipaySignature.rsaSign(orderData, config.privateKey, AlipayConfig.input_charset);
			
			Map<String, String> data = new HashMap<>();
			data.put("sign", sign);
			data.put("orderInfo", AlipayFunction.CreateLinkString(orderData));
			
			return JsonResult.ok(data);
		} catch (AlipayApiException ex) {
			log.error(ex.getErrMsg(), ex);
			return JsonResult.fail(-1, ex.getErrMsg());
		}
	}
	
	@Override
	public JsonResult<Trade> verifyTradeCallback(
			@RequestBody Map<String, String> tradeData) {
		
		//商户订单号
		String out_trade_no = tradeData.get("out_trade_no");

		Trade trade = tradeService.getTrade(out_trade_no);
    	
         AlipayConfig config = payConfig.getAlipayConfig();
         
		try {
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			//计算得出通知验证结果
			boolean verify_result = AlipaySignature.rsaCheckV1(tradeData, config.publicKey, AlipayConfig.input_charset);
			
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
			trade.setThirdStatus( trade_status );
			trade.setPayTime( new Date() );

			if ( trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS") ) {
				trade.setPayState(1);
				tradeService.updateTrade(trade);
			} else {
				tradeService.updateTrade(trade);
			}
			
			return JsonResult.ok(trade);
			
		} catch (AlipayApiException ex) {
			log.error(ex.getErrMsg(), ex);
			return JsonResult.fail(-1, ex.getErrMsg());
		}
	}

	@Override
	public JsonResult<Trade> verifyTradeNotifying(
			@RequestBody Map<String, String> tradeData) {
		
		//商户订单号
		String out_trade_no = tradeData.get("out_trade_no");

		Trade trade = tradeService.getTrade(out_trade_no);
    	
         AlipayConfig config = payConfig.getAlipayConfig();
         
		 try {
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			//计算得出通知验证结果
			boolean verify_result = AlipaySignature.rsaCheckV1(tradeData, config.publicKey, AlipayConfig.input_charset);
			
			if(verify_result) {
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
			trade.setThirdStatus( trade_status );
			trade.setPayTime( new Date() );

			if ( trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS") ) {
				trade.setPayState(1);
				tradeService.updateTrade(trade);
			} else {
				tradeService.updateTrade(trade);
			}
			
			return JsonResult.ok(trade);
			
		} catch (AlipayApiException ex) {
			return JsonResult.fail(-1, ex.getErrMsg());
		}
	}

	@Override
	public JsonResult<String> getPayDataForWeb(Map<String, String> orderData) throws IOException {
		return null;
	}

	@Override
	public JsonResult<String> getPayDataForWap(Map<String, String> orderData) throws IOException {
		return null;
	}

}
