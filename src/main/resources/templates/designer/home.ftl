<#--
  云联创威客系统
  Copyright 2015 云联创科技

  设计师首页
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
                        <li class="on"><a href="javascript:;">作  品</a></li>
                        <li><a href="/designer/u${user.id}/rating">交易评价</a></li>
                    </ul>
                </div>
                <div class="bd">
                    <ul class="m-list3 y-bdpic">
                        <#list zuopinList as zuopin>
                        <li>
                            <div class="con">
                                <div class="pic"><a href="/zuopin/${zuopin.id}">
                                    <img src="/user/${zuopin.cover}" id="img" alt=""></a></div>
                                <div class="bot">
                                    <div class="txt">
                                        <h3><a href="/zuopin/${zuopin.id}">${zuopin.title}</a></h3>
                                        <div class="txt-j">
                                            <span class="price">￥${zuopin.price}</span>
                                            <p><a href="javascript:;" id="like" onclick="like(${zuopin.id}, {user.id}, 'like_${zuopin.id}');">点赞 (${zuopin.likeCount})</a></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                        </#list>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</@layout.body>
<@layout.foot>

    <link rel="stylesheet" href="/lib/css/slick.css">
    <script src="/lib/js/slick.min.js"></script>

    <script type="text/javascript">
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

        function like(zpId, likeUser, a) {
            var uid = ${auth.userId};
            if (uid > 0) {
                $.ajax({
                    type: 'post',
                    url: '/like',
                    data: {oid: zpId, kind:"zuopin", likeUser:likeUser},
                    cache: false,
                    dataType: 'json',
                    success: function (ret) {
                        if (ret.status == 1) {
                            $('#'+a).text("点赞 (" + ret.likeCount + ")"); 
                        } else if (ret.status == 2) {
                            alert('已经赞过啦。');
                        } else {
                            alert('出现问题，请稍后重试。');
                        }
                    }
                });
            } else {
                alert('请先登录。');
            }
        }
    </script>

</@layout.foot>
