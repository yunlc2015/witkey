<#--
  云联创威客系统
  Copyright 2015 云联创科技

  页顶部模板
  author: Billy Zhang (billy_zh@126.com)
-->
<div class="wp">
    <span class="top-l">设计师在线服务平台</span>
    <div class="top-r">
        <#if !auth.logon>
            <a href="/login">请登录</a>
            <a href="/reg">免费注册</a>
        <#else>
            <span>${auth.mobile}</span>
            <a href="/logout">退出</a>
            <span class="a-user">
                <img src="/lib/images/ico-user.png" alt="">
                <ul>
                    <#if auth.userClazz==1 >
                        <li><a href="/user/designer/index">个人中心</a></li>
                        <li><a href="/user/designer/jiaoyi">交易管理</a></li>
                        <li><a href="/user/designer/zuopin">作品管理</a></li>
                        <li><a href="/user/designer/shezhi">账户信息</a></li>
                    </#if>
                     <#if auth.userClazz==2 >
                        <li><a href="/user/employer/index">个人中心</a></li>
                        <li><a href="/user/employer/tasklist">我的订单</a></li>
                        <li><a href="/user/employer/shezhi">账户信息</a></li>
                    </#if>
                </ul>
            </span>
        </#if>
    </div>
</div>