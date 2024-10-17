<#--
  云联创威客系统
  Copyright 2015 云联创科技

  4xx 错误页
  author: Billy Zhang (billy_zh@126.com)
-->
<#import "/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
    <div id="top">
        <#include "/inc/top.ftl" />
    </div>
    <div id="hd">
        <#include "/inc/search.ftl" />
    </div>
    <div id="header">
        <@mobHeader />
    </div>
    <aside id="aside">
        <#include "/inc/mobnav.ftl" />
    </aside>
    <div id="nv">
        <@webNav />
    </div>

    <div style="min-height:300px; padding:30px;">
        <h2>${errorMsg!"访问的内容不见了。"}</h2>
    </div>

    <div class="h30"></div>
    
</@layout.body>
<@layout.foot>
</@layout.foot>