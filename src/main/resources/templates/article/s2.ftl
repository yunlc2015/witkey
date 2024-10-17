<#--
  云联创威客系统
  Copyright 2015 云联创科技

  文章页
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
	<div id="nv" class="g-borb">
		<@webNav />
	</div>
	<div class="h30"></div>

	<div id="bd">
		<div class="wp">
			<div class="side-col">
				<h2 class="g-tit3">我是雇主</h2>
				<ul class="snav-ul">
					<li><a class="s1" href="/reg">注册新用户</a></li>
					<li <#if name=="employer-guide">class="on"</#if>><a class="s1" href="/s2/employer-guide">雇主入门</a></li>
					<li <#if name=="service-agreement">class="on"</#if>><a class="s1" href="/s2/service-agreement">服务协议</a></li>
                    <li <#if name=="statement">class="on"</#if>><a class="s1" href="/s2/statement">免责说明</a></li>
				</ul>
			</div>

			<div class="main-col">
				<h3 class="g-tit4">${article.title }</h3>
				<div class="tz-cont">
					<div class="aboutBox">
						${article.content }
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="h30"></div>

</@layout.body>
<@layout.foot>
</@layout.foot>