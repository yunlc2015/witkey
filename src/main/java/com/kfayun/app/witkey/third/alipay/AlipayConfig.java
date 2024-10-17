package com.kfayun.app.witkey.third.alipay;

public class AlipayConfig {
	
	public AlipayConfig(String sellerEmail, String appId, String privateKey, String publicKey, String signType) {
	    this.seller_email = sellerEmail;
        this.partner = appId;
	    this.privateKey = privateKey;
	    this.publicKey = publicKey;
	    this.sign_type = signType;
    }
	 
    // 如何获取安全校验码和合作身份者ID
    // 1.访问支付宝商户服务中心(b.alipay.com)，然后用您的签约支付宝账号登陆.
    // 2.访问“技术服务”→“下载技术集成文档”（https://b.alipay.com/support/helperApply.htm?action=selfIntegration）
    // 3.在“自助集成帮助”中，点击“合作者身份(Partner ID)查询”、“安全校验码(Key)查询”

    // ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static String it_b_pay = "1h";

    public String partner = "";

    public static String service = "create_direct_pay_by_user";
    
    // 交易安全检验码，由数字和字母组成的32位字符串    
    public String privateKey = "";

    public String publicKey = "";
    
    // 签约支付宝账号或卖家收款支付宝帐户
    public String seller_email = "";
    // 读配置文件

    // 收款方名称，如：公司名称、网站名称、收款人姓名等
    public static String mainname = "收款方名称";
    // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "UTF-8";

    // 签名方式 不需修改
    public String sign_type = "MD5";

    // 访问模式,根据自己的服务器是否支持ssl访问，若支持请选择https；若不支持请选择http
    public static String transport = "http";
    
}
