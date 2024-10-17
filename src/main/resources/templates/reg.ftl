<#--
  云联创威客系统
  Copyright 2015 云联创科技

  用户注册类型页
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
	<aside id="aside">
		<#include "/inc/mobnav.ftl" />
	</aside>

	<div id="bd">
		<div class="box-setRole">
			<div class="wp">
				<div class="sec-tit">
					<h2>您必须设置您在平台的角色才可以继续下一步</h2>
					<p class="fc_orange">角色选定之后将不可更改，且绑定的手机号码只对应一个账户，请慎重选择！</p>
				</div>
				<ul class="setRole-ul">
					<li>
						<a href="reg2?clazz=2" class="con">
							<img src="lib/images/tz-ico6.png" alt="" class="ico">
							<div class="txt">
								<h4>我是雇主</h4>
								<p>雇主角色只能发布需求、雇佣设计师，但不能接单；</p>
							</div>
						</a>
					</li>
					<li>
						<a href="reg2?clazz=1" class="con">
							<img src="lib/images/tz-ico7.png" alt="" class="ico">
							<div class="txt">
								<h4>我是设计师</h4>
								<p>设计师角色可以接受雇佣，但不能发布需求和雇佣设计师</p>
							</div>
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>

</@layout.body>
<@layout.foot>
</@layout.foot>