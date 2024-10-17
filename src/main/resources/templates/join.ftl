<#--
  云联创威客系统
  Copyright 2015 云联创科技

  会员加入页
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
		<@mobHeader current="join" />
	</div>
	<aside id="aside">
		<#include "/inc/mobnav.ftl" />
	</aside>
	<div id="nv">
		<@webNav current="join" />
	</div>

	<div id="ban-2" style="background-image: url(/lib/images/tz-pic1.jpg);">
		<div class="wp">
			<div class="txt">
				<div class="tz-tit1">
					<h3>解放创意人，开创设计师自品牌</h3>
					<p>一个对创意人永久免费的平台</p>
				</div>
				<a href="/reg" class="g-btn5 g-btn-green ani">免费注册认证</a>
			</div>
		</div>
	</div>

	<div id="bd">
		<div class="tz-public tz-public-row1">
			<div class="wp">
				<div class="tz-tit1">
					<h3>聪明的设计师，都在${settings.appName}</h3>
					<p>落标有偿，20%总有你的份，为中国创意人找回遗忘的尊严</p>
				</div>
				<ul class="tz-list3">
					<li>
						<img src="/lib/images/tz-ico3.png" alt="" class="ico">
						<h4>需求雇主</h4>
					</li>
					<li>
						<img src="/lib/images/tz-ico4.png" alt="" class="ico">
						<h4>${settings.appName}</h4>
					</li>
					<li>
						<img src="/lib/images/tz-ico5.png" alt="" class="ico">
						<h4>创意人</h4>
					</li>
				</ul>

				<a href="javascript:void(0);" class="tz-btn1 js-slideDown"><span>了解落标有偿详情</span></a>
				<div class="tz-serviceCon">
					<h4> 参与有奖，落标有偿</h4>
					<p>为保障设计师利益，提高竞标参与积极性，平台特推出“参与有奖，落标有偿”竞标制度。<br>项目奖金总额80%作为中标设计的奖金，20%作为参与设计师有参与奖金。</p>
					<p>1、平台根据不同客户需求，将竞标方式分为“市场标与管家标”两类，按照标型的大小、场景不同，给予不同规则的服务。市场标普遍适用于各类自由标型，管家标为平台定制服务体系。</p>
					<p>2、设计师完成实名认证，就可参与市场标竞标。平台根据设计师任务完成情况给予相应的等级评定；完成50单服务后，即可有机会升级为实力设计师，平台会对不同等级的项目机会按照专业、规模、认证等级、忙闲等条件进行精准设计师匹配，保障每个设计师都获得公平、公正的接单机会。</p>
					<p>3、实力设计师在参与管家标，对于参与未中标的设计师根据作品原创性与客户需求进行参数对比，给予项目20%的共享有偿竞标费用，形成平台内部参与机制。</p>
					<a href="javascript:void(0);" class="tz-btn1 js-slideUp"><span class="up">收起</span></a>
				</div>
			</div>
		</div>

		<div class="tz-public tz-maker">
			<div class="wp">
				<div class="tz-tit1">
					<h3>自己当家做主人，接好活就是这么简单</h3>
					<p>再大的单（rou）也是限量版，从此羡慕死别人</p>
				</div>
				<img src="/lib/images/tz-pic2.png" alt="">
			</div>
		</div>

		<div class="tz-public tz-public-row3" style="background-image: url(/lib/images/tz-pic3.jpg);">
			<div class="wp">
				<div class="tz-tit1">
					<h3>${settings.appName}，一个在家设计的平台</h3>
					<p>最炫，最酷的创意人“生意”服务平台</p>
				</div>
			</div>
		</div>

		<div class="tz-public-row4 bg1">
			<div class="wp">
				<div class="m-butler">
					<div class="left">
						<div class="pic"><img src="/lib/images/m2.jpg" alt=""></div>
						<div class="txt">
							<h3>50名项目管家</h3>
							<p>整装待发，为您随时提供保姆式的设计管家服务，为你的每一分投入，保驾护航。三分钟为您找到众多设计师，为您服务。</p>
							<a href="#" class="btn-h2">在线咨询顾问</a>
							<p class="fc0">或拨打电话<em>4006-335-115</em></p>
						</div>
					</div>
					<div class="demand tz-demand">
						<h3>快速注册成为开创自品牌</h3>
						<form name="quick-form" id="quick-form">
                            <input type="text" placeholder="您的称呼" class="inp" name="title" id="title">
                            <input type="text" placeholder="您的手机号" class="inp" name="mobile" id="mobile">
                            <select name="cate" id="cate">
                                <option value="" selected style="display: none;">需求类型</option>
                                <#list cateList as cate>
                                <option value='${cate.id}'>${cate.name}</option>
                                </#list>
                            </select>
                            <textarea placeholder="您可以在这里详细描述您的项目需求。" id="subscribe" name="subscribe"></textarea>
                            <input type="submit" class="btn-h1" value="提　交" id="quickSubmit">
                            <p class="fc_blue">今日已有<asp:Literal id="ltQuick1" runat="server" />位雇主申请，本月申请人数已达<asp:Literal id="ltQuick2" runat="server" />人</p>
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
	                alert("请输入您的称呼。");
	                return false;
	            }

	            var mobile = $("#mobile").val();
	            if (mobile == '') {
	                alert("请输入您的手机号。");
	                return false;
	            }
	            if (mobile.length != 13 && mobile.substring(0, 1) != '1') {
	                alert("无效的手机号。");
	                return false;
	            }

	            var cate = $("cate option:selected").val();
	            if (cate == '') {
	                alert("请选择需求类型。");
	                return false;
	            }

	            var subscribe = $("subscribe").val();
	            if (subscribe == '') {
	                alert("请输入需求描述。");
	                return false;
	            }

	            $("#quickSubmit").enable(false);
	            var options = {
	                url: '/quick',
	                type: 'post',
	                dataType: 'text',
	                data: $("#quick-form").serialize(),
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