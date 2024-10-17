<#--
  云联创威客系统
  Copyright 2015 云联创科技

  账户安全页
  author: Billy Zhang (billy_zh@126.com)
-->
<#import "/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body class="bg8">

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
				<h2 class="g-tit3"><a href="index"><span class="ico-home">我的首页</span></a></h2>
				<div class="side-con">
					<ul class="userNav">
						<li><h3><a href="">交易管理</a></h3>
							<dl>
                                <dd><a href="/task/publish">发布任务</a></dd>
								<dd><a href="tasklist">我的订单</a></dd>
								<dd><a href="tousu">交易投诉</a></dd>
							</dl>
						</li>
						<li><h3><a href="">账务中心</a></h3>
							<dl>
								<dd><a href="wallet">我的钱包</a></dd>
								<dd><a href="withdrawallist">提现记录</a></dd>
							</dl>
						</li>
						<li><h3><a href="">账户信息</a></h3>
							<dl>
								<dd><a href="accountsafety">账户安全</a></dd>
								<dd><a href="shezhi">修改信息</a></dd>
							</dl>
						</li>
					</ul>
				</div>
			</div>

			<div class="main-col">
				<div class="bg8">
					<h3 class="tz-tit2"><span>账户安全</span></h3>
					<div class="tz-accountSafe">
						<div class="avatar">
							<img src="${user.avatar!'/lib/images/avatar.jpg'}" alt="" />${user.nickname}
						</div>
						<table>
							<tr>
								<th>您的基础信息</th>
								<td>
									登录账号：<span class="fc_blue">${user.nickname}　</span>
									手机号码：<span class="fc_blue">${user.mobile}</span><br>
									密保邮箱：<span class="fc_orange">无</span>
									<a href="" class="tz-a2 ani">立即绑定</a>
								</td>
							</tr>
							<tr>
								<th>账号安全程度</th>
								<td>
									<div class="levelBar">
										<span>弱</span><span class="on">中</span><span>强</span>
									</div>
								</td>
							</tr>
						</table>
					</div>

					<div class="tz-safeService">
						<h4>安全服务</h4>
						<ul class="tz-list6">
							<li>
								<span class="ico1">登录密码</span>
								定期更换密码，可以更好的保护账号安全
								<a href="">立即修改</a>
							</li>
							<li>
								<span class="ico2">邮箱验证</span>
								验证邮箱后能快速找回密码，且接收账户资金变动提醒
								<a href="">立即绑定</a>
							</li>
							<li>
								<span class="ico3">手机验证</span>
								手机验证可保障交易通畅和账户安全
								<a href="">重新验证</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
			<div class="h"></div>
	</div>
	<div class="h30"></div>

</@layout.body>
<@layout.foot>
</@layout.foot>