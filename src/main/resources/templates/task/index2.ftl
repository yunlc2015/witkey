<#--
  云联创威客系统
  Copyright 2015 云联创科技

  任务列表页（管家标）
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
        <@mobHeader curent="task" />
    </div>
    <aside id="aside">
        <#include "/inc/mobnav.ftl" />
    </aside>
    <div id="nv">
        <@webNav current="task" />
    </div>

    <div id="ban">
        <div class="slider">
            <#list bannerList as item>
            <div class="slide" style="background-image: url(${banner.imageUrl});">
                <a href="${banner.targetUrl}"><div class="wp"></div></a>
            </div>
            </#list>
        </div>
    </div>

    <div id="bd">
        <div class="wp">
            <ul class="tab-ico">
                <li>
                    <div class="ico"><a href="index">
                        <img src="/lib/images/tz-ico1.png" alt=""></a></div>
                    抢市场标</li>
                <li class="on">
                    <div class="ico"><a href="javascript:void(0);">
                        <img src="/lib/images/tz-ico2.png" alt=""></a></div>
                    抢管家标</li>
            </ul>
            <div class="g-tit2">
                <h3>筛选</h3>
            </div>
            <div class="filterBox">
                <form id="searchForm" action="index" method="get">
                    <label for="" class="inp-col inp-col-l">
                        <span>任务类型</span>
                        <select name="c1" id="c1">
                            <option value="0">全部</option>
                            <#list cateList as cate>
                            <option value='${cate.id?c}' <#if cond.cate1==cate.id>selected='selected'</#if>>${cate.name}</option>
                            </#list>
                        </select>
                        <select name="c2" id="c2">
                            <option value="0">全部</option>
                            <#list cateList2 as cate>
                            <option value='${cate.id?c}' <#if cond.cate2==cate.id>selected='selected'</#if>>${cate.name}</option>
                            </#list>
                        </select>
                    </label>
                    <label for="" class="inp-col inp-col-c">
                        <span>佣金状态</span>
                        <select name="ps" id="ps">
                            <option value="0">已托管赏金</option>
                            <option value="1">已提高赏金</option>
                            <option value="2">已付款</option>
                        </select>
                    </label>
                    <label for="" class="inp-col inp-col-r">
                        <span>任务模式</span>
                        <select name="tm" id="tm">
                            <option value="0">全部</option>
                            <option value="1" <#if cond.mode==1>selected='selected'</#if>>悬赏模式</option>
                            <option value="2" <#if cond.mode==2>selected='selected'</#if>>投标模式</option>
                        </select>
                    </label>
                    <div class="inp-box">
                        <button class="g-btn2 ani" id="btnPubTime">发布时间</button>
                        <button class="g-btn2 ani" id="btnLeftTime">剩余时间</button>
                        <button class="g-btn2 ani" id="btnPrice">价格</button>
                        <div class="soBox">
                            <input type="text" value="${cond.keyword}" id="wd" name="wd" class="so-inp">
                            <input type="submit" value="搜索" class="so-btn">
                        </div>
                    </div>
                    <input type="hidden" id="pts" name="pts" value="${cond.pubTimeSort}" />
                    <input type="hidden" id="lts" name="lts" value="${cond.leftTimeSort}" />
                    <input type="hidden" id="prs" name="prs" value="${cond.priceSort}" />
                </form>
            </div>

            <ul class="tz-list1">
            <#if taskList?size == 0>
                <li id="liNoData" runat="server">
                    没有找到您想要的内容，请重新搜索
                </li>
            <#else>
                <#list taskList as task>
                <li>
                    <h4><b>￥${task.designAmount}</b> <a href="t${task.id}" target="_blank">${task.requirement}</a>
                        <div class="tag"><span>按时完成</span><span>保证原创</span><span>实名认证</span><span>官方认证</span><span>管家服务</span></div>
                    </h4>
                    <div class="txt">
                        <div class="det">
                            <p>
                                <span>项目类型：管家标</span>
                                <span>佣金状态：已付款</span>
                                <span>项目周期：${task.hopeDays}天</span>
                                <span>奖金分配比例：中标80%，参与20%。</span>
                            </p>
                            <p>此项目已经过项目管家服务，为客户解决创意顾问、交易协作，法律支持等服务。</p>
                        </div>
                    </div>
                    <div class="info">
                        <div class="words">
                            <span class="time">${task.beginDate}</span><span>${task.dueTimeText}</span>
                    <p>招标 | 已报名：${task.acceptCount}人 | 投标限额：${task.limitDesignerText}</p>
                        </div>
                        <a href="t${task.id}" class="g-btn3 ani">${task.actionText}</a>
                    </div>
                </li>
                </#list>
            </ul>

            <#include "/inc/listpager.ftl" />
        </#if>
        </div>
    </div>

</@layout.body>
<@layout.foot>

    <link rel="stylesheet" href="/lib/css/slick.css">
    <script src="/lib/js/slick.min.js"></script>
    <script>
    $(document).ready(function ($) {
        // ban滚动
        $('#ban .slider').slick({
            dots: true,
            arrows: false,
            autoplay: true,
            slidesToShow: 1,
            autoplaySpeed: 5000,
            pauseOnHover: false,
            lazyLoad: 'ondemand'
        });

        $('.tab-ico .ico').click(function (event) {
            $(this).parents('li').addClass('on').siblings('li').removeClass('on');
        });

        $('#c1').val(${cond.cate1});
        $('#c2').val(${cond.cate2});
        $('#tm').val(${cond.mode});

        <#if cond.pubTimeSort== 1 >
        $('#btnPubTime').addClass('down');
        <#elseif cond.pubTimeSort==2 >
        $('#btnPubTime').addClass('up');
        </#if>

        <#if cond.leftTimeSort == 1 >
        $('#btnLeftTime').addClass('down');
        <#elseif cond.leftTimeSort==2 >
        $('#btnLeftTime').addClass('up');
        </#if>

        <#if cond.priceSort == 1 >
        $('#btnPrice').addClass('down');
        <#elseif cond.priceSort == 2 >
        $('#btnPrice').addClass('up');
        </#if>

        $('#c1').change(function () {
            $('#searchForm').submit();
        });

        $('#c2').change(function () {
            $('#searchForm').submit();
        });

        $('#ps').change(function () {
            $('#searchForm').submit();
        });

        $('#tm').change(function () {
            $('#searchForm').submit();
        });

        $('#btnPubTime').click(function () {
            if ($('#btnPubTime').hasClass('down')) {
                $('#pts').val(2);
            } else {
                $('#pts').val(1);
            }
            $('#lts').val(0);
            $('#prs').val(0);
            $('#searchForm').submit();
        });

        $('#btnLeftTime').click(function () {
            if ($('#btnLeftTime').hasClass('down')) {
                $('#lts').val(2);
            } else {
                $('#lts').val(1);
            }
            $('#pts').val(0);
            $('#prs').val(0);
            $('#searchForm').submit();
        });

        $('#btnPrice').click(function () {
            if ($('#btnPrice').hasClass('down')) {
                $('#prs').val(2);
            } else {
                $('#prs').val(1);
            }
            $('#pts').val(0);
            $('#lts').val(0);
            $('#searchForm').submit();
        });
    });
    </script>
</@layout.foot>