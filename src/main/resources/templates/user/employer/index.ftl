<#--
  云联创威客系统
  Copyright 2015 云联创科技

  个人中心页
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
					<div class="tzBox">
						<div class="tz-userInfo">
							<div class="top">
								<a href=""><img src="${user.avatar!'/lib/images/avatar.jpg'}" alt="" class="avatar"></a>
								<span class="name">${user.nickname}</span>
							</div>
							<div class="info">
								<span class="tel">${user.mobile}</span>
							</div>
						</div>
						
						<div class="tz-userAccount">
							<div class="con">
								账户余额：<em>${user.balance}</em> 元<br>
								创意币：<em>0</em> <br>
								<a href="/user/withdraw" class="tz-btn3 g-btn-blue">提 现</a>
								<a href="wallet" class="tz-btn3">收支明细</a>
							</div>
						</div>
						
						<div class="tz-adBox">
						<#if banner??>
						<a href="${banner.targetUrl}"><img src="${banner.imageUrl}" style="border:0px" alt=""></a>
						</#if>
						</div>
					</div>
					
					<h3 class="tz-tit2"><span>我的订单</span></h3>
					<ul class="tz-list4">
                        <#list taskList as task>
						<li>
							<dl>
								<dd class="s1">订单日期<span>${task.beginDate}</span></dd>
								<dd class="s2">${task.serviceText}
									<span>
										<a href="/task/t${task.id}" target="_blank">${task.requirement}</a></span></dd>
								<dd class="s3">交易款<span>￥${task.totalAmount?c}</span></dd>
								<dd class="s4">交易状态<span class="state">${task.taskState}</span></dd>
								<dd class="s5"><a id="det_${task.id}" onclick="toggerDetail('det_${task.id}', '', ${task.id})" href="javascript:void(0);" class="tz-a1 js-detBtn">详情</a></dd>
							</dl>
							<div class="pop hide">
								<#include "/user/employer/inc/${task.actionBarName}.ftl" />
							</div>
						</li>
						</#list>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="h30"></div>

</@layout.body>
<@layout.foot>
	
	<link rel="stylesheet" href="/lib/css/slick.css">
	<script src="/lib/js/slick.min.js"></script>
	<script>
	    $(document).ready(function () {
	        // 滚动
	        $('.botScroll').slick({
	            dots: false,
	            arrows: true,
	            slidesToShow: 5,
	            slidesToScroll: 1,
	            responsive: [
                    {
                        breakpoint: 1024,
                        settings: {
                            slidesToShow: 3,
                            slidesToScroll: 1,
                            infinite: true
                        }
                    },
                    {
                        breakpoint: 767,
                        settings: {
                            dots: true,
                            arrows: false,
                            slidesToShow: 1,
                            slidesToScroll: 1
                        }
                    }
	            ]
	        });
	    });
	</script>

	<script>
	    $(document).ready(function ($) {
	        /*$('.js-detBtn').click(function (event) {
	            $(this).parents('li').siblings('li').find('.pop').addClass('hide');
	            $(this).parents('li').find('.pop').toggleClass('hide');
	        });*/
	        $('.js-detUpBtn').click(function (event) {
	            $(this).parents('.pop').addClass('hide');
	        });
	    });

	    function toggerDetail(id, div, tid) {
	        $('#' + id).parents('li').siblings('li').find('.pop').addClass('hide');
	        $('#' + id).parents('li').find('.pop').toggleClass('hide');

	        if (div != '' && !$('#' + id).parents('li').find('.pop').hasClass('hide'))
	            getProposals(div, tid, 0, 1);
	    }

	    function getProposals(boxid, tid, type, page) {
	        $.get('proposals?boxid=' + boxid + '&taskid=' + tid + '&type=' + type + '&page=' + page,
                   function (ret) {
                       $('#'+boxid).html(ret);

                       $('#'+boxid).find('.botScroll').slick({
                           arrows: true,
                           slidesToShow: 5,
                           slidesToScroll: 1,
                           focusOnSelect: true,
                           responsive: [
                               {
                                   breakpoint: 1024,
                                   settings: {
                                       slidesToShow: 3,
                                       slidesToScroll: 1,
                                       infinite: true
                                   }
                               },
                               {
                                   breakpoint: 767,
                                   settings: {
                                       dots: true,
                                       arrows: false,
                                       slidesToShow: 1,
                                       slidesToScroll: 1
                                   }
                               }
                           ]
                       });
                   }
               );
	    }

	    var once = 0;
	    function chooseProposal(boxid, tid, pid) {

	        if (once == 0) {
	            once = 1;

	            var options = {
	                url: 'proposal_choose',
	                type: 'post',
	                dataType: 'json',
	                data: { 'taskid': tid, 'projectid': pid },
	                success: function (resp) {
	                    once = 0;

	                    if (resp.data == 1) {
	                        alert("方案已选定。");
	                        getProposals(boxid, tid, 0, 1);
	                    } else if (resp.data == 2) {
	                        alert("只能选择一个方案。");
	                    } else {
	                        alert("提交失败，请稍后重试。");
	                    }
	                }
	            };
	            $.ajax(options);
	        }
	    }

	    function toggleComment(tid, boxid) {
	        $.ajax({
	            type: 'post',
	            url: 'proposal_payment',
	            data: { taskid: tid },
	            cache: false,
	            dataType: 'json',
	            success: function (ret) {
	            }
	        });

	        $('#'+boxid).toggle();
	    }

	    function doRating(formid, taskid, div) {

	        var form = $('#' + formid);
	        var content = form.find('textarea[name=\'content\']');

	        if (content.val() == '') {
	            alert('请输入内容。');
	            content.focus();
	            return false;
	        }

	        $.ajax({
	            type: 'post',
	            url: 'proposal_rating',
	            data: form.serialize(),
	            cache: false,
	            dataType: 'json',
	            success: function (resp) {
	                if (resp.data == 1) {
	                    alert('提交成功，谢谢您的评价。');
	                    getProposals(div, taskid, 0, 1);
	                } else {
	                    alert('提交失败，请稍后重试。');
	                }
	            }
	        });

	        return false;
	    }

	    function deleteTask(taskId) {
	        if (confirm('确定要删除此任务吗？')) {
	            $.ajax({
	                type: 'post',
	                url: 'deletetask',
	                data: { taskid: taskId },
	                cache: false,
	                dataType: 'json',
	                success: function (resp) {
	                    if (resp.errCode == 0) {
	                        alert('任务已删除。');
	                        location.reload();
	                    } else {
	                        alert(resp.errMsg);
	                    }
	                }
	            });
	        }
	    }

	</script>
	
    <!-- 弹出大图 -->
	<link rel="stylesheet" href="/lib/css/jquery.fancybox.css">
	<script src="/lib/js/jquery.fancybox.pack.js"></script>
	<script>
	    $(document).ready(function () {
	        $('.fancybox').fancybox({
	            'padding': 0
	        });
	    });
	</script>
</@layout.foot>