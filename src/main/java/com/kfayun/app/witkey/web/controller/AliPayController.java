/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kfayun.app.witkey.JsonResult;
import com.kfayun.app.witkey.pay.PayConfig;
import com.kfayun.app.witkey.model.Trade;
import com.kfayun.app.witkey.service.TaskService;

/**
 * 支付宝支付Controller
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
@Controller
public class AliPayController extends BaseController {

    private static final String ALIPAY_SUCCESS = "success";
	private static final String ALIPAY_FAIL = "fail";
	
	@Autowired
	private PayConfig payConfig;
	@Autowired
	private TaskService taskService;
	@Autowired
	private ObjectMapper jsonMapper;

	/**
	 * 支付宝支付成功后回调
	 * 
	 */
    @RequestMapping(value="alipay/callback")
	@ResponseBody
    public void alipayCallback(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		//获取支付宝POST过来反馈信息
		HashMap<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = iter.next();
			String[] values = requestParams.get(name);
			String valueStr = String.join(",", values);

			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		JsonResult<Trade> jsonRet = payConfig.getAliPay().verifyTradeCallback(params);
			
		if (jsonRet.getErrCode()==0 && jsonRet.getData()!= null) {

			Trade trade = jsonRet.getData();

			if ( trade.getPayState() == 1 && trade.getTaskId() > 0 ) {
				taskService.updateTaskInfoForTrade(trade);

				response.sendRedirect( "/user/employer/tasklist" );
			} else {
					
				response.sendRedirect( "/task/payfail" );
			}
			
		} else {//验证失败
			getLog().error(jsonRet.getErrMsg());
			response.sendRedirect( "/task/payfail" );
		}
        
    }

	/**
	 * 支付宝支付成功后通知回调
	 * 
	 * @return
	 */
	@RequestMapping("alipay/notify")
	@ResponseBody
	public String aliPayNotify(HttpServletRequest request) {
		String result = ALIPAY_FAIL;
		
		try {
			
			//获取支付宝POST过来反馈信息
			HashMap<String,String> params = new HashMap<String,String>();
			Map<String,String[]> requestParams = request.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = iter.next();
				String[] values = requestParams.get(name);
				String valueStr = String.join(",", values);
				
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}

			getLog().info("verify tradedata: {}", jsonMapper.writeValueAsString(params));

	        JsonResult<Trade> jsonRet = payConfig.getAliPay().verifyTradeNotifying(params);
	
	        if (jsonRet.getErrCode()==0 && jsonRet.getData()!= null) {
				Trade trade = jsonRet.getData();
	        	
				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				result = ALIPAY_SUCCESS;
	
			}else{//验证失败
				// nothing;
			}
		} catch (Exception ex) {
			getLog().error(ex.getMessage(), ex);
			
			// nothing;
		}
		
		return result;
	}

}