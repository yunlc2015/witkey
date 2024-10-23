<#--
  云联创威客系统
  Copyright 2015 云联创科技

  作品列表页
  author: Billy Zhang (billy_zh@126.com)
-->
<#import "/layout/page.ftl" as layout>
<@layout.head>
    <link rel="stylesheet" href="/lib/css/slick.css">
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
            <div class="slide" style="background-image: url(${banner.imageUrl});">
                <a href="${banner.targetUrl}" ><div class="wp"></div></a>
            </div>
            </#list>
        </div>
    </div>

    <div id="bd">
        <div class="wp">
            <ul class="tab-tit3">
                <li class="on"><a href="index">服务</a></li>
                <li><a href="designer">设计师</a></li>
                <li><a href="team">设计团队</a></li>
            </ul>
            <div class="g-tit2">
                <h3>筛选</h3>
            </div>
            <div class="filterBox">
                 <form id="search-form" method="get">
                    <label for="" class="inp-col inp-col-l">
                        <span>业务类型</span>
                        <select name="cate1" id="c1">
                            <option value="0">全部</option>
                            <#list cateList as cate>
                            <option value='${cate.id}'>${cate.name}</option>
                            </#list>
                        </select>
                        <select name="cate2" id="c2">
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
                            <option value="1">实名认证设计师</option>
							<option value="2">官方实力认证设计大师</option>
							<option value="3">实名认证设计机构</option>
							<option value="4">官方实力认证设计机构</option>
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

            <ul class="m-list3">
                <#if zuopinList?size == 0 >
                <li>
                    没有找到您想要的内容，请重新搜索
                </li>
                <#else>
                <#list zuopinList as zuopin>
                <li>
                    <div class="con">
                        <div class="pic">
                            <a href="/zuopin/${zuopin.id}">
                                <img src="/user/${zuopin.cover}" alt="${zuopin.title}"/></a>
                        </div>
                        <div class="bot">
                            <div class="face">
                                <a href="/designer/u${zuopin.userId}">
                                    <img src="${zuopin.userAvatar!'/lib/images/avatar.jpg'}" alt="" /></a>
                            </div>
                            <div class="txt">
                                <h3>${zuopin.title}</h3>
                                <p>${zuopin.username} / ${zuopin.province!}${zuopin.city!}</p>
                                <span class="price">￥${zuopin.price}</span>
                                <p>成交量：${zuopin.dealCount}笔；好评数：${zuopin.likeCount}个</p>
                            </div>
                        </div>
                    </div>
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
            $('#grade').val(${cond.userGrade});

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
                $('#search-form').submit();
            });

            $('#c2').change(function () {
                $('#search-form').submit();
            });

            $('#cp').change(function () {
                $('#search-form').submit();
            });

            $('#grade').change(function () {
                $('#search-form').submit();
            });

            $('#btnSales').click(function () {
                if ($('#btnSales').hasClass('down')) {
                    $('#sas').val(2);
                } else {
                    $('#sas').val(1);
                }
                $('#prs').val(0);
                $('#cms').val(0);
                $('#search-form').submit();
            });

            $('#btnPrice').click(function () {
                if ($('#btnPrice').hasClass('down')) {
                    $('#prs').val(2);
                } else {
                    $('#prs').val(1);
                }
                $('#sas').val(0);
                $('#cms').val(0);
                $('#search-form').submit();
            });

            $('#btnComment').click(function () {
                if ($('#btnComment').hasClass('down')) {
                    $('#cms').val(2);
                } else {
                    $('#cms').val(1);
                }
                $('#sas').val(0);
                $('#prs').val(0);
                $('#search-form').submit();
            });
        });
    </script>

</@layout.foot>
