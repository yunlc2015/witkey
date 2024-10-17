package com.kfayun.app.witkey.third.alipay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class AlipayNotify {

    public static boolean Verify(AlipayConfig config, Map<String, String> inputPara, String notify_id, String sign) {
        //获取返回时的签名验证结果
        boolean isSign = getSignVeryfy(config, inputPara, sign);
        //获取是否是支付宝服务器发来的请求的验证结果
        String responseTxt = "true";
        if (notify_id != null && notify_id.length() > 0) { 
        	responseTxt = Verify(config, notify_id); 
        }

        //写日志记录（若要调试，请取消下面两行注释）
        //string sWord = "responseTxt=" + responseTxt + "\n isSign=" + isSign.ToString() + "\n 返回回来的参数：" + GetPreSignStr(inputPara) + "\n ";
        //Core.LogResult(sWord);

        //判断responsetTxt是否为true，isSign是否为true
        //responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
        //isSign不是true，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
        if (responseTxt.equals("true") && isSign) {//验证成功
            return true;
        } else { //验证失败
            return false;
        }
    }

    private static boolean getSignVeryfy(AlipayConfig config, Map<String, String> inputPara, String sign) {
        
        //获得签名验证结果
        boolean isSgin = false;
        if (sign != null && sign.length() > 0) {
            switch (config.sign_type) {
                case "MD5":
                    isSgin = sign.equalsIgnoreCase( GetMysign(inputPara, config.privateKey) );
                    break;
                default:
                    break;
            }
        }

        return isSgin;
    }
    
    
	/**
     * *功能：根据反馈回来的信息，生成签名结果
     * @param Params 通知返回来的参数数组
     * @param key 安全校验码
     * @return 生成的签名结果
     */
    public static String GetMysign(Map<String, String> Params, String key){
        Map<String, String> sParaNew = AlipayFunction.ParaFilter(Params);//过滤空值、sign与sign_type参数
        String mysign = AlipayFunction.BuildMysign(sParaNew, key);//获得签名结果
        
        return mysign;
    }
    
    /**
    * *功能：获取远程服务器ATN结果,验证返回URL
    * @param notify_id 通知校验ID
    * @return 服务器ATN结果
    * 验证结果集：
    * invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空
    * true 返回正确信息
    * false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
    */
    public static String Verify(AlipayConfig config, String notify_id){
        //获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求
        String transport = AlipayConfig.transport;
        String partner = config.partner;
        String veryfy_url = "";
        if(transport.equals("https")){
            veryfy_url = "https://www.alipay.com/cooperate/gateway.do?service=notify_verify";
        } else{
            veryfy_url = "http://notify.alipay.com/trade/notify_query.do?";
        }
        veryfy_url = veryfy_url + "&partner=" + partner + "&notify_id=" + notify_id;
        
        String responseTxt = CheckUrl(veryfy_url);
        
        return responseTxt;
    }
    
    /**
    * *功能：获取远程服务器ATN结果
    * @param urlvalue 指定URL路径地址
    * @return 服务器ATN结果
    * 验证结果集：
    * invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空
    * true 返回正确信息
    * false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
    */
    public static String CheckUrl(String urlvalue){
        String inputLine = "";

        try {
            URL url = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection) url
                    .openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            inputLine = in.readLine().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inputLine;
    }
}
