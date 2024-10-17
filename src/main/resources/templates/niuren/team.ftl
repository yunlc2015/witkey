<#--
  云联创威客系统
  Copyright 2015 云联创科技

  设计团队列表页
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
        <@mobHeader current="niuren" />
    </div>
    <aside id="aside">
        <#include "/inc/mobnav.ftl" />
    </aside>
    <div id="nv">
        <@webNav current="niuren" />
    </div>

    <div id="ban">
        <div class="slider">
            <#list bannerList as banner>
            <div class="slide" style="background-image: url(${banner.imageUrl!});">
                <a href="${banner.targetUrl!}"><div class="wp"></div></a>
            </div>
            </#list>
        </div>
    </div>

    <div id="bd">
        <div class="wp">
            <ul class="tab-tit">
                <li><a href="index">服务</a></li>
                <li><a href="designer">设计师</a></li>
                <li class="on"><a href="team">设计团队</a></li>
            </ul>
            <div class="g-tit2">
                <h3>筛选</h3>
            </div>
            <div class="filterBox">
                <form id="searchForm" method="get">
                    <label for="" class="inp-col inp-col-l">
                        <span>业务类型</span>
                        <select name="c1" id="c1">
                            <option value="0">全部</option>
                            <#list cateList as cate>
                            <option value='${cate.id}'>${cate.name}</option>
                            </#list>
                        </select>
                        <select name="c2" id="c2">
                            <option value="0">全部</option>
                            <#list cateList2 as cate>
                            <option value='${cate.id}'>${cate.name}</option>
                            </#list>
                        </select>
                    </label>
                    <label for="" class="inp-col inp-col-c">
                        <span>所在城市</span>
                        <select id="cp" name="cp">
                            <option value="">全部</option>
                            <#list provList as city>
                            <option value='${city.province}'>${city.province}</option>
                            </#list>
                        </select>
                    </label>
                    <label for="" class="inp-col inp-col-r">
                        <span>牛人类别</span>
                        <select name="grade" id="grade">
                            <option value="0">全部</option>
                            <option value="1">认证设计师</option>
                            <option value="2">官方设计师</option>
                            <option value="3">明星设计师</option>
                        </select>
                    </label>
                    <div class="inp-box">
                        <button class="g-btn2 ani" id="btnSales">销量</button>
                        <button class="g-btn2 ani" id="btnPrice">价格</button>
                        <button class="g-btn2 ani" id="btnComment">评论数</button>
                        <div class="soBox">
                            <input type="text" value="${cond.keyword}" id="wd" name="wd" class="so-inp">
                            <input type="submit" value="搜索" class="so-btn">
                        </div>
                    </div>
                    <input type="hidden" id="sas" name="sas" value="${cond.salesSort}" />
                    <input type="hidden" id="prs" name="prs" value="${cond.priceSort}" />
                    <input type="hidden" id="cms" name="cms" value="${cond.commentSort}" />
                </form>
            </div>

            <ul class="tz-list2">
                <#if userList?size == 0 >
                <li>
                    没有找到您想要的内容，请重新搜索
                </li>
                <#else>
                <#list userList as user>
                <li>
                    <div class="avatar">
                        <a href="/designer/u${user.id}">
                            <img src="${user.avatar!'/lib/images/avatar.jpg'}" alt=""></a><em>R</em>
                    </div>
                    <div class="txt">
                        <h4>${user.nickname} / ${user.location}" /></h4>
                        <div class="favor">擅长领域：${user.special!}</div>
                        <div class="info">
                            <span class="price">￥${user.startingPrice}</span>
                            成交量：${user.dealCount}笔；　好评数：${user.goodCount}个
                        </div>
                        <a href="/designer/u${user.id}" class="g-btn4 ani">联系雇佣TA</a>
                    </div>
                    <dl class="pic">
                        <#list user.zuopinList as zuopin>
                        <dd><a href="/zuopin/${zuopin.id}">
                            <img src="/user/${zuopin.cover}" alt="" /></a></dd>
                        </#list>
                    </dl>
                </li>
                </#list>
                </#if>

            </ul>

            <#include "/inc/listpager.ftl" />
            
        </div>
    </div>
    <div class="h20"></div>

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

            $('#c1').val(${cond.cate1});
            $('#c2').val(${cond.cate2});
            $('#cp').val('${cond.province}');
            //$('#ct').val(${cond.cityId});
            $('#grade').val(${cond.grade});

            <#if cond.salesSort==1 >
            $('#btnSales').addClass('down');
            <#elseif cond.salesSort==2 >
            $('#btnSales').addClass('up');
            </#if>

            <#if cond.priceSort==1 >
            $('#btnPrice').addClass('down');
            <#elseif cond.priceSort==2 >
            $('#btnPrice').addClass('up');
            </#if>

            <#if cond.commentSort==1 >
            $('#btnComment').addClass('down');
            <#elseif cond.commentSort==2 >
            $('#btnComment').addClass('up');
            </#if>

            $('#c1').change(function () {
                //$.ajax({
                //    type: 'get',
                //    url: '/getcates?cid=' + $(this).val(),
                //    cache: false,
                //    success: function (ret) {
                //        var jsonArr = jQuery.parseJSON(ret);
                //        var optStr = "<option value=\"0\">全部</option>";
                //        for (var i = 0; i < jsonArr.length; i++) {
                //            optStr += "<option value=\"" + jsonArr[i].value + "\">" + jsonArr[i].text + "</option>";
                //        }
                //        $('#c2').html(optStr);
                //    }
                //});
                $('#searchForm').submit();
            });

            $('#c2').change(function () {
                $('#searchForm').submit();
            });

            $('#cp').change(function () {
                $('#searchForm').submit();
            });

            $('#grade').change(function () {
                $('#searchForm').submit();
            });

            $('#btnSales').click(function () {
                if ($('#btnSales').hasClass('down')) {
                    $('#sas').val(2);
                } else {
                    $('#sas').val(1);
                }
                $('#prs').val(0);
                $('#cms').val(0);
                $('#searchForm').submit();
            });

            $('#btnPrice').click(function () {
                if ($('#btnPrice').hasClass('down')) {
                    $('#prs').val(2);
                } else {
                    $('#prs').val(1);
                }
                $('#sas').val(0);
                $('#cms').val(0);
                $('#searchForm').submit();
            });

            $('#btnComment').click(function () {
                if ($('#btnComment').hasClass('down')) {
                    $('#cms').val(2);
                } else {
                    $('#cms').val(1);
                }
                $('#sas').val(0);
                $('#prs').val(0);
                $('#searchForm').submit();
            });
        });
    </script>

</@layout.foot>
