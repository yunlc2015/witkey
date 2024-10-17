<#--
  云联创威客系统
  Copyright 2015 云联创科技

  需求提交页
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
		<@mobHeader current="requires" />
	</div>
	<aside id="aside">
		<#include "/inc/mobnav.ftl" />
	</aside>
	<div id="nv">
		<@webNav current="requires" />
	</div>

	<div id="ban-2" style="background-image: url(/lib/images/ban-public.jpg);">
		<div class="wp">
			<div class="txt">
				<div class="tz-tit1">
					<h3>真感情莫过于“不满意，80%无理由退款”</h3>
					<p>实力设计师管家匹配，百万设计师为您在线服务</p>
				</div>
				<a href="/task/publish" class="g-btn5 ani">发布需求</a>
			</div>
		</div>
	</div>

	<div id="bd">
		<!-- 管家服务 -->
		<div class="tz-public tz-public-row1">
			<div class="wp">
				<div class="tz-tit1">
					<h3>首创堪比4A的管家服务</h3>
					<p>开创线上项目管家服务，为双方交易提供全面保障</p>
				</div>
				<ul class="tz-list3">
					<li>
						<img src="/lib/images/tz-ico3.png" alt="" class="ico">
						<h4>需求雇主</h4>
					</li>
					<li>
						<img src="/lib/images/tz-ico4.png" alt="" class="ico">
						<h4>项目管家</h4>
					</li>
					<li>
						<img src="/lib/images/tz-ico5.png" alt="" class="ico">
						<h4>创意人</h4>
					</li>
				</ul>

				<a href="javascript:void(0);" class="tz-btn1 js-slideDown"><span>了解更多管家服务</span></a>
				<div class="tz-serviceCon">
					<h4>管家服务细则</h4>
					<p>为给客户营造一个安全、极致、快捷的交易氛围，实施“设计师+项目管家+客户”三维一体的参与式管理服务模式。</p>
					<p>1、会员制服务：我们实行专业认证筛选的设计师会员制体系，每个成员都是通都是通过实名以及实力审核认可的设计师。</p>
					<p>2、1对1管家协作推进。在创意完成过程中，为项目提供私人定制式的1对1服务流程，全程为你解决创意服务、交易协作，法律支持等服务，保障项目顺利完成。</p>
					<p>3、360°专业项目管家助力推进管理。资深项目管家全程监理，协作项目有效进行。</p>
					<h4>管家服务流程</h4>
					<p>第一：项目管家咨询。获得项目管家服务的项目，平台会提供项目操作过程中的资源、市场、法律及专业咨询服务。</p>
					<p>第二：签订三方协议。明确合作过程中的三方权责，履行建议、监管与验收职责，项目管家参与项目的全程监理。</p>
					<p>第三：协作三方评价。项目管家协调设计师与客户的协作推进，交易完成时，提供透明化的三方评价系统，寄予一个公正、公平、透明化的服务体验。</p>
					<a href="javascript:void(0);" class="tz-btn1 js-slideUp"><span class="up">收起</span></a>
				</div>
			</div>
		</div>

		<div class="tz-public tz-public-row2">
			<div class="wp">
				<div class="tz-tit1">
					<h3>真省心莫过“一对多，精准索骥”</h3>
					<p>XXX</p>
				</div>
				<img src="/lib/images/tz-public1.png" alt="">
			</div>
		</div>

		<div class="tz-public tz-public-row3" style="background-image: url(/lib/images/tz-public2.jpg);">
			<div class="wp">
				<div class="tz-tit1">
					<h3>真靠谱莫过“实力派创意高手见招拆招”</h3>
					<p>帮你认领业内顶尖设计师，让你少花钱，做最专业</p>
				</div>
			</div>
		</div>

		<div class="tz-public-row4 bg1">
			<div class="wp">
				<div class="m-butler">
					<div class="left">
						<div class="pic"><img src="/lib/images/m1.jpg" alt=""></div>
						<div class="txt">
							<h3>100名项目管家</h3>
							<p>整装待发，为您随时提供保姆式的设计管家服务，为你的每一分投入，保驾护航。三分钟为您找到众多设计师，为您服务。</p>
							<a href="#" class="btn-h2">在线咨询顾问</a>
							<p class="fc0">或拨打电话<em>4006-335-115</em></p>
						</div>
					</div>
					<div class="demand">
						<h3>快速提交您的需求</h3>
						<form name="quickForm" id="quickForm">
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
                            <p class="fc_blue">今日已有100位雇主申请，本月申请人数已达5000人</p>
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