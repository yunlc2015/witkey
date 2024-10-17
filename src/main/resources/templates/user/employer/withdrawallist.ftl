<#--
  云联创威客系统
  Copyright 2015 云联创科技

  提现列表页
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
                        <li>
                            <h3><a href="">交易管理</a></h3>
                            <dl>
                                <dd><a href="/task/publish">发布任务</a></dd>
                                <dd><a href="tasklist">我的订单</a></dd>
                                <dd><a href="tousu">交易投诉</a></dd>
                            </dl>
                        </li>
                        <li>
                            <h3><a href="">账务中心</a></h3>
                            <dl>
                                <dd><a href="wallet">我的钱包</a></dd>
                                <dd><a href="withdrawallist">提现记录</a></dd>
                            </dl>
                        </li>
                        <li>
                            <h3><a href="">账户信息</a></h3>
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
                    <h3 class="tz-tit2"><span>提现记录</span></h3>
                    <#if withdrawalList?size &gt; 0 >
                    <ul class="tz-list5">
                        <#list withdrawalList as wd>
                        <li>
                            <dl>
                                <dd class="s1">
                                    ${wd.requestDate}</dd>
                                <dd class="s2">账户提现</dd>
                                <dd class="s3"><em class="dollar fc_orange">
                                    ${wd.amount}</em></dd>
                                <dd class="s4"><span class="state">
                                    ${wd.stateText}</span></dd>
                                <dd class="s5"><a href="javascript:void(0);" class="tz-a1 js-detBtn">详情</a></dd>
                            </dl>
                            <div class="pop hide">
                                <div class="det">
                                    ${wd.detail!}</div>
                            </div>
                        </li>
                        </#list>
                    </ul>
                    <#include "/inc/listpager.ftl" />
                    <#else>
                    <div style="padding:30px;font-size:1.3em;background-color:#fff;">
                        暂无提现记录。
                    </div>
                    </#if>
                </div>
                <br />

            </div>
        </div>
    </div>
    <div class="h30"></div>

</@layout.body>
<@layout.foot>

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
