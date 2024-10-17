<#--
  云联创威客系统
  Copyright 2015 云联创科技

  投诉页
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
				<h3 class="tz-tit2"><span>交易投诉</span></h3>
				<div class="reflectBox">
                    <form id="form1" onsubmit="return doSubmit()">
					<textarea placeholder="请说出您的意见" name="content"></textarea>
					<input type="submit" value="确认，并提交的宝贵意见" class="g-btn5 ani">
                        </form>
				</div>
				<div class="blankBox">
					<h5><img src="/lib/images/ico-face.png" alt="">暂无投诉记录</h5>
					<p>
						平台将在<span class="fc_blue">2个工作日</span>内介入争议进行仲裁。期间，交易双方可自行协商解决，若仍无法达成一致，<br>请在投诉页面填写争议原因。
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="h30"></div>

</@layout.body>
<@layout.foot>

	 <script>
	     function doSubmit() {

	         if ($("#content").val() == '') {
	             alert('请输入内容。');
	             $("#content").focus();
	             return false;
	         }

	         $.ajax({
	             type: 'post',
	             url: 'tousu',
                data: $("#form1").serialize(),
                cache: false,
                dataType: 'json',
                success: function (resp) {
                    if (resp.data == 1) {
                        alert('提交成功，请等待客服处理。');
                        location.href = '/user/employer/index';
                    } else {
                        alert('提交失败，请稍后重试。');
                    }
                }
            });

            return false;
        }
    </script>

</@layout.foot>