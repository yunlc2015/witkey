/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.controller;

import java.util.HashMap;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kfayun.app.witkey.JsonResult;
import com.kfayun.app.witkey.pay.WeixinPay;
import com.kfayun.app.witkey.model.Trade;
import com.kfayun.app.witkey.service.TaskService;
import com.kfayun.app.witkey.third.wxpay.WXPayUtil;

/**
 * 微信支付Controller
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
@Controller
public class WxPayController extends BaseController {

    @Autowired
	private WeixinPay weixinPay;
	@Autowired
	private TaskService taskService;

	/**
	 * 微信支付成功后通知回调
	 * 
	 * @return
	 */
	@RequestMapping("wxpay/notify")
	@ResponseBody
	public String wxpayNotify(HttpServletRequest request) {
		
		try {
			  //接收从微信后台POST过来的数据
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
          
            int count = -1;
            byte[] buffer = new byte[1024];
            while ((count = inStream.read(buffer, 0, 1024)) != -1) {
                outStream.write(buffer, 0, count);
            }
            inStream.close();

            String notifyStr = new String(outStream.toByteArray(), "utf-8");
            outStream.close();

			getLog().info("verify tradedata: {}", notifyStr);

			JsonResult<Trade> jsonRet = weixinPay.verifyTradeNotifying(notifyStr);;
			if (jsonRet.getErrCode() ==0 && jsonRet.getData()!= null) {
				Trade trade = jsonRet.getData();
				if (trade.getPayState() == 1 && trade.getTaskId() > 0) {
					taskService.updateTaskInfoForTrade(trade);
				}
				HashMap<String, String> data = new HashMap<String, String>();
            	data.put("return_code", "SUCCESS");
            	data.put("return_msg", "OK");
            	return WXPayUtil.mapToXml(data);
			} else {
				HashMap<String, String> data = new HashMap<String, String>();
                data.put("return_code", "FAIL");
                data.put("return_msg", jsonRet.getErrMsg());
                return WXPayUtil.mapToXml(data);
			}
		} catch (Exception ex) {
			getLog().error(ex.getMessage(), ex);
			
			try {  
            	HashMap<String, String> data = new HashMap<String, String>();
            	data.put("return_code", "FAIL");
            	data.put("return_msg", "未知错误");
				return WXPayUtil.mapToXml(data);
			} catch (Exception ex2) {
				getLog().error(ex2.getMessage(), ex2);
	        }
		}
	
		return "<xml></xml>";
	}

}
