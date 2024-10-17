<#--
  云联创威客系统
  Copyright 2015 云联创科技

  页脚模版
  author: Billy Zhang (billy_zh@126.com)
-->
<div class="wp">
<div class="fhelp">
    <div class="flogo"><img src="<#if settings.appFootLogo?has_content>${settings.appFootLogo}<#else>/lib/images/flogo.png</#if>" alt=""></div>
    <div class="fnav">
        <dl class="fm1">
            <dt><a href="#">新手指南</a></dt>
            <dd><a href="/reg">注册新用户</a></dd>
            <dd><a href="/user/designer/index">个人中心</a></dd>
            <dd><a href="/s2/service-agreement">服务协议</a></dd>
            <dd><a href="/s2/statement">免责说明</a></dd>
        </dl>
        <dl class="fm1">
            <dt><a href="#">我是雇主</a></dt>
            <dd><a href="/task/publish">发布需求</a></dd>
            <dd><a href="/niuren/designer">挑选服务商</a></dd>
            <dd><a href="/niuren/trademark">商标注册</a></dd>
            <dd><a href="#">管家服务</a></dd>
        </dl>
        <dl class="fm1">
            <dt><a href="#">我是服务商</a></dt>
            <dd><a href="/s3/service-guide">服务商入门</a></dd>
            <dd><a href="/s3/how-gettask">如何抢标</a></dd>
            <dd><a href="/user/designer/authentication">实力认证</a></dd>
            <dd><a href="/task/">开始赚钱</a></dd>
        </dl>
        <dl class="fm1">
            <dt><a href="#">交易保障</a></dt>
            <dd><a href="/s5/danbao-jiaoyi">担保交易</a></dd>
            <dd><a href="/s5/three-agreement">三方协议</a></dd>
            <dd><a href="/s5/zhengyi-zhongcai">争议仲裁</a></dd>
            <dd><a href="/s5/copyright">版权说明</a></dd>
        </dl>
    </div>
    <div class="ftel">
        <b>${settings.serviceTel!}</b>
        周一至周日9：00-23：00
        <a href="#" target="_blank" class="g-btn1 ani">联系在线客服</a>
    </div>
    <div class="fma"><img src="${settings.wxQrcode!'/lib/images/ma.jpg'}" alt=""></div>
</div>
<div class="fbottom">
    <div class="fguide">
        <a href="/s1/aboutus">关于我们</a>
        <a href="/s1/contact">联系我们</a>
        <a href="/task/publish">发布设计需求</a>
        <a href="/s1/statement">法律声明</a>
    </div>
    <div class="fshare">
        <a href="" class="fs2"></a>
        <a href="tel:${settings.serviceTel!}" class="fs3"></a>
    </div>
    <div class="fcopy">${settings.appName!} (${appVersion!}) &nbsp; &copy;版权所有 <a target="_blank" href="http://www.yunlc.com.cn/">云联创科技</a> <span>${settings.icpBeianNo!}</span> &nbsp;&nbsp;<span>[总时长:{{execute_duration}}, SQL时长:${_sqlDuration!}ms, SQL次数:${_sqlCount!}]</span>
    </div>
</div>
</div>