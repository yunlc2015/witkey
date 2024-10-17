<#--
  云联创威客系统
  Copyright 2015 云联创科技

  移动端页头模版
  author: Billy Zhang (billy_zh@126.com)
-->
<div class="wp">
    <a href="/" class="m-logo">
        <img src="<#if settings.appMobLogo?has_content>${settings.appMobLogo}<#else>/lib/images/m-logo.png</#if>" alt=""></a>
    <ul class="menu">
        <li <#if current==''>class="on"</#if>><a href="/">首页</a></li>
        <li <#if current=='task'>class="on"</#if>><a href="/task/">任务大厅</a></li>
        <li <#if current=='niuren'>class="on"</#if>><a href="/niuren/">牛人大厅</a></li>
        <li <#if current=='requires'>class="on"</#if>><a href="/requires">发布需求</a></li>
        <li <#if current=='join'>class="on"</#if>><a href="/join">成为牛人</a></li>
    </ul>
    <a href="/task/publish" class="g-subBtn"><span>发布设计需求</span></a>
</div>