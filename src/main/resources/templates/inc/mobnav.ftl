<#--
  云联创威客系统
  Copyright 2015 云联创科技

  移动端导航栏模板
  author: Billy Zhang (billy_zh@126.com)
-->
<a href="javascript:void(0);" class="closeBtn"></a>
<nav id="nav">
    <li class="home"><a href="/">首页</a></li>
    <li><a href="/task/">任务大厅 </a></li>
    <li><a href="/niuren/">牛人大厅</a></li>
    <li><a href="/requires">发布需求</a></li>
    <li><a href="/join">成为牛人</a></li>
    <li><a href="/sc/info">创意圈子</a></li>
    <#if !auth.logon>
    <li><a href="/login">登陆</a></li>
    <li><a href="/reg">注册</a></li>
    <#else>
    <li><a href="${auth.userCenterUrl}">个人中心</a></li>
    <li><a href="/logout">退出</a></li>
    </#if>
</nav>