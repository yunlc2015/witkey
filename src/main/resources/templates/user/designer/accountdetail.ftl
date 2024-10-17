<#--
  云联创威客系统
  Copyright 2015 云联创科技

  账号详情页
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
                    <h4>${user.nickname}</h4>
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
                        <li><a href="tasklist">我的任务</a></li>
                        <li class="on"><a href="accountdetail">账户明细</a></li>
                        <li><a href="withdrawallist">提现记录</a></li>
                    </ul>
                </div>
                <div class="tab_tit-con">
                    <#if paymentList?size &gt; 0>
                    <div class="tab-user-con">
                        <ul class="tz-list5">
                            <#list paymentList as payment >
                            <li>
                                <dl>
                                    <dd class="s1">
                                        ${payment.payDate}</dd>
                                    <dd class="s2">
                                        ${payment.summary!}</dd>
                                    <dd class="s3"><em class="dollar fc_blue">
                                        ${payment.amount}</em></dd>
                                    <dd class="s4"><span class="state">
                                        ${payment.stateText}</span></dd>
                                    <dd class="s5"><a href="javascript:void(0);" class="tz-a1 js-detBtn">详情</a></dd>
                                </dl>
                                <div class="pop hide">
                                    <div class="det">
                                        ${payment.detail!}
                                    </div>
                                </div>
                            </li>
                            </#list>
                        </ul>
                        <#include "/inc/listpager.ftl" />
                    </div>
                    <#else>
                    <div style="padding:30px;font-size:1.3em;">
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

</@layout.foot>
