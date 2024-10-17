<#--
  云联创威客系统
  Copyright 2015 云联创科技

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

	<div id="ban-2" style="background-image: url(/lib/images/16-p5.jpg);">
		<div class="wp">
			<div class="txt">
				<div class="tz-tit1">
					<h3>知识产权保护，就找专家团队</h3>
				</div>
                <ul class="tz-list3 yt-ban1-list">
                    <li>
                        <div class="txt-bor">
                            <h2>???</h2>
                            <h3>商标注册</h3>
                            <span>传统价:1500</span>
                        </div>
                    </li>
                    <li>
                        <div class="txt-bor">
                            <h2>???</h2>
                            <h3>作品版权</h3>
                            <span>传统价:1000</span>
                        </div>
                    </li>
                    <li>
                        <div class="txt-bor">
                            <h2>???</h2>
                            <h3>外观专利</h3>
                            <span>传统价:1200</span>
                        </div>
                    </li>
                </ul>
				<a href="/task/publish?cate=11000" class="g-btn5 g-btn-green ani">马上申请</a>
			</div>
		</div>
	</div>

	<div id="bd">
		<div class="tz-public tz-public-row1 yt-ban2" style="background-color:#fafafa;">
			<div class="wp">
                <div class="tz-tit1">
                    <h3>专注品牌保护的知识产权服务</h3>
                    <p>已帮助???余家商标注册服务，一站式平台更值得信任</p>
                </div>
                <img src="/images/16-p2.jpg" alt="">
            </div>
		</div>

		<div class="tz-public tz-maker yt-ban3" style="background-color:#01a7b5;">
			<div class="wp">
				<div class="tz-tit1">
					<h3>知识产权注册没有奇迹，便宜快捷才是理</h3>
					<p>通过整合平台资源优势，我们比传统代办节省更多</p>
				</div>
				<img src="/lib/images/16-p1.jpg" alt="">
			</div>
		</div>

		<div class="tz-public tz-public-row3" style="background-image: url(/lib/images/16-p4.jpg);">
			<div class="wp">
				<div class="tz-tit1">
					<h3 style="color:#ffffff;">商标不注册，品牌是非多</h3>
					<p>有管家，当天申报当天受理，简单，快，成功率高</p>
				</div>
			</div>
		</div>

		<div class="tz-public-row4 bg1 yt-ban4">
			<div class="wp">
				<div class="m-butler">
					<div class="left">
						<h1>加急商标注册服务</h1>
                        <h3>设计前预约查询+商标注册解决方案+1对1商标官家服务三个工作日拿到注册申请号</h3>
					</div>
					<div class="demand tz-demand yt-demand">
						<h3>商标免费查询</h3>
                        <h4>专业人工查询，结果分析更精准。</h4>
                        <form name="quickForm" id="quickForm">
                            <div class="inp-list">
                                <ul>
                                    <li>
                                        <span><em>* </em>商标名称：</span>
                                        <input type="text" id="subscribe" name="subscribe" placeholder="">
                                    </li>
                                    <li>
                                        <span><em>* </em>联系电话：</span>
                                        <input type="text" name="mobile" id="mobile" placeholder="接收查询结果及建议，仅官方可见">
                                    </li>
                                    <li>
                                        <span>&nbsp;&nbsp;&nbsp;联系人：</span>
                                        <input type="text" name="title" id="title" placeholder="如：张先生/女生">
                                    </li>
                                </ul>
                            </div>
						    <input type="hidden" name="cate" value="11000" />
						    <input type="submit" class="btn-h1" value="获取查询结果" id="quickSubmit">
						    <div class="tc">设计师在线服务交易平台</div>
                        </form>
					</div>
				</div>
			</div>
		</div>

	</div>

</@layout.body>
<@layout.foot>

	<script>
	    $(document).ready(function ($) {
	        $('.js-slideDown').click(function (event) {
	            $(this).siblings('.tz-serviceCon').stop().slideDown();
	        });
	        $('.js-slideUp').click(function (event) {
	            $(this).parents('.tz-serviceCon').stop().slideUp();
	        });

	        $("#quickSubmit").click(function () {
	            var title = $("#title").val();
	            if (title == '') {
	                alert("请输入联系人。");
	                return false;
	            }

	            var mobile = $("#mobile").val();
	            if (mobile == '') {
	                alert("请输入联系电话。");
	                return false;
	            }

	            var subscribe = $("subscribe").val();
	            if (subscribe == '') {
	                alert("请输入商标名称。");
	                return false;
	            }

	            $("#quickSubmit").enable(false);
	            var options = {
	                url: '/quick',
	                type: 'post',
	                dataType: 'text',
	                data: $("#quickForm").serialize(),
	                success: function (data) {
	                    if (data.length > 0)
	                        alert(data);

	                    $("#quickSubmit").enable(true);
	                }
	            };
	            $.ajax(options);
	            return false;
	        });

	    });
	</script>
	
</@layout.foot>