<#--
  云联创威客系统
  Copyright 2015 云联创科技

  设计师评价页
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

    <div id="bd">
        <div class="peo-tbox">
            <div class="wp">
                <div class="peo-pictxt">
                    <div class="img">
                        <img src="${user.avatar!'/lib/images/avatar.jpg'}" alt="" /><#if user.realAuthStateEm?has_content><em>${user.realAuthStateEm}</em></#if>
                    </div>
                    <div class="txt">
                        <h3>${user.nickname}</h3>
                        <p>
						    ${user.realAuthStateText!}
						</p>
                        <p>${user.location!}</p>
                        <div class="lg-txtz">
                            <div class="txtz">
                                <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${user.im!}&site=qq&menu=yes" class="qq"><em>在线沟通</em></a>
								<a href="tel:${mobile}" class="tel">${mobileMask}</a>
                            </div>
                            <a href="/task/employ?uid=${user.id}" class="p-btn1 h-btn">
                                <span></span>
                                雇佣合作
                            </a>
                        </div>

                        <div class="xs-txtz">
                            <a href="/task/employ?uid=${user.id}" class="p-btn1 h-btn">
                                <span></span>
                                雇佣合作
                            </a>
                            <div class="txtz">
                                <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${user.im!}&site=qq&menu=yes" class="qq"></a>
								<a href="tel:${mobile}" class="tel">${mobileMask}</a>
                            </div>

                        </div>
                    </div>
                    <div class="c"></div>
                </div>
                <div class="peo-txtr">
                    <div class="p1">
                        <#if user.clazz==2>${user.teamDescr!}<#else>${user.education!}</#if><br>
                        ${user.workYear!} 年工作经验 / ${user.special!}
                   
                    </div>
                    <div class="p2 JsDet">
                        ${user.intro!}
                    </div>
                    <a class="more JsMore" href="javascript:void(0);">查看完整资料 >></a>
                </div>
                <div class="c"></div>
            </div>
        </div>
        <div class="wp">
            <div class="y-picBox">
                <div class="hd">
                    <ul>
                        <li><a href="/designer/u${user.id}">作  品</a></li>
                        <li class="on"><a href="">交易评价</a></li>
                    </ul>
                </div>
                <div class="bd">
                    <div class="scoreBox">
                        <div class="tit">
                            综合评分:
                        </div>
                        <div class="score-con">
                            <div class="sc-left">
                                <h1>${rating5Percent}%</h1>
                                <span>好评度</span>
                            </div>
                            <div class="sc-right">
                                <div class="pf">
                                    <p>好评：<span>${rating5Percent}%</span></p>
                                    <p>中评：<span>${rating3Percent}%</span></p>
                                    <p>差评：<span>${rating1Percent}%</span></p>
                                </div>
                                <ul>
                                    <li>
                                        <div class="line">
                                            <div class="jind">
                                                <span style="width: ${rating5Percent}%;"></span>
                                            </div>
                                            <img src="/lib/images/16-cir.png" alt="" class="c-btn">
                                        </div>
                                    </li>
                                    <li>
                                        <div class="line">
                                            <div class="jind">
                                                <span style="width: ${rating3Percent}%;"></span>
                                            </div>
                                            <img src="/lib/images/16-cir.png" alt="" class="c-btn c-btn2">
                                        </div>
                                    </li>
                                    <li>
                                        <div class="line">
                                            <div class="jind">
                                                <span style="width: ${rating1Percent}%;"></span>
                                            </div>
                                            <img src="/lib/images/16-cir.png" alt="" class="c-btn c-btn3">
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="evaluateBox">
                        <div class="tit">
                            来自客户的评价:
                        </div>
                        <div class="tit-tab">
                            <ul>
                                <li><a href="/designer/u${user.id}/rating">全部（${ratingCount[0]}）</a></li>
                                <li><a href="/designer/u${user.id}/rating?type=5">好评（${ratingCount[1]}）</a></li>
                                <li><a href="/designer/u${user.id}/rating?type=3">中评（${ratingCount[2]}）</a></li>
                                <li><a href="/designer/u${user.id}/rating?type=1">差评（${ratingCount[3]}）</a></li>
                            </ul>
                        </div>
                        <div class="con-list">
                            <ul>
                                <#list ratingList as rating>
                                <li>
                                    <div class="txtl">
                                        <div class="pic">
                                            <img src="${rating.userAvatar!'/lib/images/16-img4.jpg'}" style="width:64px;" alt="">
                                        </div>
                                        <div class="txt">
                                            <p>${rating.username}的评价</p>
                                            <span>${rating.content!}</span>
                                        </div>
                                    </div>
                                    <div class="li-r">
                                        1天前 
                                    </div>
                                </li>
                                </#list>
                            </ul>

                        </div>
                        <div id="pager">
                            <#include "/inc/listpager.ftl" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@layout.body>
<@layout.foot>

    <script>
        $(document).ready(function () {
            $('.JsMore').click(function (event) {
                $(this).toggleClass('open');
                if ($(this).hasClass('open')) {
                    $('.JsDet').css({ 'height': 'auto' });
                } else {
                    $('.JsDet').css({ 'height': '3em' });
                }
            });
        });
    </script>

    <link rel="stylesheet" href="/lib/css/slick.css">
    <script src="/lib/js/slick.min.js"></script>

</@layout.foot>
