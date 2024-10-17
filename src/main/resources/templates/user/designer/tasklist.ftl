<#--
  云联创威客系统
  Copyright 2015 云联创科技

  任务列表页
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
    <aside id="aside">
        <#include "/inc/mobnav.ftl" />
    </aside>

    <div id="ban_user">
        <div class="wp">
            <div class="user_avatar">
                <img src="${user.avatar!'/lib/images/avatar.jpg'}" id="avatar" alt="">
                <div class="txt">
                    <h4>${user.nickname!}</h4>
                    <a href="" class="a-card"></a>
                </div>
            </div>
            <ul class="tab-user">
                <li><a href="index">个人中心</a></li>
                <li><a href="zuopin">作品</a></li>
                <li class="on"><a href="tasklist">交易</a></li>
                <li><a href="shezhi">账号设置</a></li>
                <li><a href="/designer/u${user.id}">我的主页</a></li>
            </ul>
        </div>
    </div>

    <div id="bd">
        <div class="wp">
            <div class="tz-row">
                <div class="tz-col-l">
                    <div class="tz-padd tz-account1">
                        <div class="cash">
                            账户余额： <span class="dollar"><em>
                                ${user.balance}</em> 元</span>     创意币：<em>0</em>
                        </div>
                        <div class="btn">
                            <a href="../withdraw" class="tz-btn3 g-btn-blue">提 现</a>
                            <a href="accountdetail" class="tz-btn3">收支明细</a>
                        </div>
                    </div>
                </div>
                <div class="tz-col-r">
                    <div class="tz-padd tz-account2">
                        <#if authState==0 >
                            <h4>您当前还不能产生交易</h4>
                            <p>请先进行实名认证。</p>
                        <#elseif authState==1 >
                            <h4>您当前还不能产生交易</h4>
                            <p>您的认证信息已提交，审核团队将在2个工作日内审核完毕，请保持手机等联系方式通畅。</p>
                        <#elseif authState==2 >
                            <h4>您已经可以进行设计交易</h4>
                        </#if>
                    </div>
                </div>
            </div>
            <div class="h20"></div>

            <div class="tz-row">
                <div class="tab_tit-user">
                    <ul class="TAB" id=".tab-user-con">
                        <li class="on"><a href="javascript:void(0);">我的任务</a></li>
                        <li><a href="accountdetail">账户明细</a></li>
                        <li><a href="withdrawallist">提现记录</a></li>
                    </ul>
                </div>
                <div class="tab_tit-con">
                    <#if projectList?size &gt; 0>
                    <div class="tab-user-con">
                        <ul class="tz-list4">
                            <#list projectList as project>
                            <li>
                                <dl>
                                    <dd class="s1">任务日期<span>${project.taskDate}</span></dd>
                                    <dd class="s2"><#if project.task.service==2>管家标<#else>市场标</#if><span>
                                        ${project.task.requirement}</span></dd>
                                    <dd class="s3">设计费<span>￥${project.task.designAmount}</span></dd>
                                    <dd class="s4">任务状态<span class="state">${project.task.taskState}</span></dd>
                                    <dd class="s5"><a href="javascript:void(0);" class="tz-a1 js-detBtn">详情</a></dd>
                                </dl>
                                <div class="pop hide">
                                    <#include "/user/designer/inc/${project.viewName}.ftl" >
                                </div>
                            </li>
                            </#list>
                        </ul>
                        <#include "/inc/listpager.ftl" />
                    </div>
                    <#else>
                    <div  style="padding:30px;font-size:1.3em;">
                        暂无记录。
                    </div>
                    </#if>
                </div>
            </div>
        </div>
    </div>
    <div class="h50"></div>

</@layout.body>
<@layout.foot>
    
    <link rel="stylesheet" href="/lib/css/slick.css">
    <script src="/lib/js/slick.min.js"></script>
	<script>
	    $(document).ready(function () {
	        // 滚动
	        $('.botScroll').slick({
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
	    });
	</script>

    <link rel="stylesheet" href="/lib/css/colorbox.css">
    <script src="/lib/js/jquery.colorbox.js"></script>
    <script>
        function closeDialog(ret) {
            if (ret == 1) { //操作成功
                location.reload();
            } else {
                $.fn.colorbox.close();
            }
        }

        $(".changeProposal").colorbox({
            iframe: true,
            innerWidth: 800,
            innerHeight: 500,
            escKey: false,
            overlayClose: false,
            onLoad: function () {
                $('#cboxClose').remove();
            }
        });
    </script>

	<script>
	    $(document).ready(function ($) {
	        $('.js-detBtn').click(function (event) {
	            $(this).parents('li').siblings('li').find('.pop').addClass('hide');
	            $(this).parents('li').find('.pop').toggleClass('hide');
	        });
	        $('.js-detUpBtn').click(function (event) {
	            $(this).parents('.pop').addClass('hide');
	        });
	    });
	</script>

    <script>
        function rejectTask(tid) {
            $.ajax({
                type: 'post',
                url: 'rejecttask',
                data: { taskid:tid },
                cache: false,
                dataType: 'json',
                success: function (ret) {
                    if (ret.data == 1) {
                        alert('任务已拒绝。');
                        location.reload();
                    } else {
                        alert('提交失败，请稍后重试。');
                    }
                }
            });
        }

        function acceptTask(tid) {
            $.ajax({
                type: 'post',
                url: 'accepttask',
                data: { taskid:tid },
                cache: false,
                dataType: 'json',
                success: function (ret) {
                    if (ret.data == 1) {
                        alert('任务已接受，请等待雇主付款。');
                        location.reload();
                    } else {
                        alert('提交失败，请稍后重试。');
                    }
                }
            });
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
