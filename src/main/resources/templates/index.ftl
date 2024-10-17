<#--
  云联创威客系统
  Copyright 2015 云联创科技

  首页
  author: Billy Zhang (billy_zh@126.com)
-->
<#import "/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
    <div id="top">
        <#include "/inc/top.ftl" />
    </div>
    <@bannerAd banner=banner1 /> 
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
        <@webNav dropDown="1" />
    </div>

    <div class="h30"></div>

    <div id="banner">
        <div class="slider">
            <#list bannerList as banner>
                    <div class="slide" id="dc" runat="server"><a href="${banner.targetUrl!}"></a></div>
            </#list>
        </div>
    </div>
    <div class="h20"></div>

    <div id="bd">
        <div class="wp">
            <ul class="m-list1">
                <#list bdinfoList as bdinfo>
                <li>
                    <div class="con">
                        <div class="pic">
                            <img src="${bdinfo.imageUrl!'/lib/images/pic-01.jpg'}" alt="" id="img" runat="server"><%--<b>推广</b>--%>
                        </div>
                        <div class="txt">
                            <h3>${bdinfo.title}</h3>
                            <p>预算：<span>￥{bdinfo.price}元</span></p>
                            <a href="/task/publish" class="btn-h1">发布一个类似的需求</a>
                        </div>
                    </div>
                </li>
                </#list>
            </ul>

            <div class="index-txt">
                <div class="slider m-list2">
                    <ul>
                        <#list taskList as task>
                        <li>
                            <div class="con">
                                <h3><span>￥${task.designAmount}</span> <a href="/task/t${task.id}">${task.requirement}</a></h3>
                                <p>${task.username}发布 ${task.projectCount}投标 </p>
                            </div>
                        </li>
                        </#list>
                    </ul>
                </div>
            </div>
            <div class="h50"></div>

            <!-- ===============找设计师=============== -->
            <div class="g-tit1">
                <a href="/niuren/designer" class="a1">更多>></a>
                <ul class="TAB" id=".design-tab-con">
                    <li><a href="javascript:void(0);">LOGO设计</a></li>
                    <li><a href="javascript:void(0);">VI设计</a></li>
                    <li><a href="javascript:void(0);">画册设计</a></li>
                    <li><a href="javascript:void(0);">包装设计</a></li>
                    <li><a href="javascript:void(0);">APP设计</a></li>
                </ul>
                <h3>找设计师<span>???位精英设计师为您在线工作</span></h3>
            </div>
            <!-- LOGO设计 -->
            <div class="design-tab-con">
                <ul class="m-list4 m-list4-1">
                    <#list userListForLogo as user>
                    <li>
                        <div class="con">
                            <div class="pic">
                                <img src="${user.avatar!'/lib/images/avatar.jpg'}" alt="" />
                            </div>
                            <div class="txt">
                                <h3>${user.nickname}</h3>
                                <p>设计费：<span>￥${user.startingPrice}元</span></p>
                                <p>擅长：${user.special!}</p>
                                <a href="/designer/u${user.id}" class="btn-h1">联系雇佣TA</a>
                            </div>
                        </div>
                    </li>
                    </#list>
                </ul>
            </div>

            <!-- VI设计 -->
            <div class="design-tab-con dn">
                <ul class="m-list4 m-list4-1">
                    <#list userListForVi as user>
                    <li>
                        <div class="con">
                            <div class="pic">
                                <img src="${user.avatar!'/lib/images/avatar.jpg'}" alt="" />
                            </div>
                            <div class="txt">
                                <h3>${user.nickname}</h3>
                                <p>设计费：<span>￥${user.startingPrice}元</span></p>
                                <p>擅长：${user.special!}</p>
                                <a href="/designer/u${user.id}" class="btn-h1">联系雇佣TA</a>
                            </div>
                        </div>
                    </li>
                    </#list>
                </ul>
            </div>

            <!-- 画册设计 -->
            <div class="design-tab-con dn">
                <ul class="m-list4 m-list4-1">
                    <#list userListForHc as user>
                    <li>
                        <div class="con">
                            <div class="pic">
                                <img src="${user.avatar!'/lib/images/avatar.jpg'}" alt="" />
                            </div>
                            <div class="txt">
                                <h3>${user.nickname}</h3>
                                <p>设计费：<span>￥${user.startingPrice}元</span></p>
                                <p>擅长：${user.special!}</p>
                                <a href="/designer/u${user.id}" class="btn-h1">联系雇佣TA</a>
                            </div>
                        </div>
                    </li>
                    </#list>
                </ul>
            </div>

            <!-- 包装设计 -->
            <div class="design-tab-con dn">
                <ul class="m-list4 m-list4-1">
                    <#list userListForBz as user>
                    <li>
                        <div class="con">
                            <div class="pic">
                                <img src="${user.avatar!'/lib/images/avatar.jpg'}" alt="" />
                            </div>
                            <div class="txt">
                                <h3>${user.nickname}</h3>
                                <p>设计费：<span>￥${user.startingPrice}元</span></p>
                                <p>擅长：${user.special!}</p>
                                <a href="/designer/u${user.id}" class="btn-h1">联系雇佣TA</a>
                            </div>
                        </div>
                    </li>
                    </#list>
                </ul>
            </div>

            <!-- APP设计 -->
            <div class="design-tab-con dn">
                <ul class="m-list4 m-list4-1">
                    <#list userListForApp as user>
                    <li>
                        <div class="con">
                            <div class="pic">
                                <img src="${user.avatar!'/lib/images/avatar.jpg'}" alt="" />
                            </div>
                            <div class="txt">
                                <h3>${user.nickname}</h3>
                                <p>设计费：<span>￥${user.startingPrice}元</span></p>
                                <p>擅长：${user.special!}</p>
                                <a href="/designer/u${user.id}" class="btn-h1">联系雇佣TA</a>
                            </div>
                        </div>
                    </li>
                    </#list>
                </ul>
            </div>
            <div class="h50"></div>

            <!-- ===============找设计团队=============== -->
            <div class="g-tit1">
                <a href="/niuren/team" class="a1">更多>></a>
                <ul class="TAB" id=".designTeam-tab-con">
                    <li><a href="javascript:void(0);">北京</a></li>
                    <li><a href="javascript:void(0);">上海</a></li>
                    <li><a href="javascript:void(0);">广州</a></li>
                    <li><a href="javascript:void(0);">深圳</a></li>
                    <li><a href="javascript:void(0);">重庆</a></li>
                </ul>
                <h3>找设计团队<span>???家创意团队等候您的邀请</span></h3>
            </div>
            <!-- 北京 -->
            <div class="designTeam-tab-con">
                <ul class="m-list4">
                    <#list bjUserList as user>
                    <li>
                        <div class="con">
                            <div class="pic">
                                <img src="${user.avatar!'/lib/images/avatar.jpg'}" alt="" />
                            </div>
                            <div class="txt">
                                <h3>${user.nickname}</h3>
                                <p>设计费：<span>￥${user.startingPrice}元</span></p>
                                <p>擅长：${user.special!}</p>
                                <a href="/designer/u${user.id}" class="btn-h1">联系雇佣TA</a>
                            </div>
                        </div>
                    </li>
                    </#list>
                </ul>
            </div>

            <!-- 上海 -->
            <div class="designTeam-tab-con dn">
                <ul class="m-list4">
                    <#list shUserList as user>
                    <li>
                        <div class="con">
                            <div class="pic">
                                <img src="${user.avatar!'/lib/images/avatar.jpg'}" alt="" />
                            </div>
                            <div class="txt">
                                <h3>${user.nickname}</h3>
                                <p>设计费：<span>￥${user.startingPrice}元</span></p>
                                <p>擅长：${user.special!}</p>
                                <a href="/designer/u${user.id}" class="btn-h1">联系雇佣TA</a>
                            </div>
                        </div>
                    </li>
                    </#list>
                </ul>
            </div>

            <!-- 广州 -->
            <div class="designTeam-tab-con dn">
                <ul class="m-list4">
                    <#list gzUserList as user>
                    <li>
                        <div class="con">
                            <div class="pic">
                                <img src="${user.avatar!'/lib/images/avatar.jpg'}" alt="" />
                            </div>
                            <div class="txt">
                                <h3>${user.nickname}</h3>
                                <p>设计费：<span>￥${user.startingPrice}元</span></p>
                                <p>擅长：${user.special!}</p>
                                <a href="/designer/u${user.id}" class="btn-h1">联系雇佣TA</a>
                            </div>
                        </div>
                    </li>
                    </#list>
                </ul>
            </div>

            <!-- 深圳 -->
            <div class="designTeam-tab-con dn">
                <ul class="m-list4">
                    <#list szUserList as user>
                    <li>
                        <div class="con">
                            <div class="pic">
                                <img src="${user.avatar!'/lib/images/avatar.jpg'}" alt="" />
                            </div>
                            <div class="txt">
                                <h3>${user.nickname}</h3>
                                <p>设计费：<span>￥${user.startingPrice}元</span></p>
                                <p>擅长：${user.special!}</p>
                                <a href="/designer/u${user.id}" class="btn-h1">联系雇佣TA</a>
                            </div>
                        </div>
                    </li>
                    </#list>
                </ul>
            </div>

            <!-- 重庆 -->
            <div class="designTeam-tab-con dn">
                <ul class="m-list4">
                    <#list cqUserList as user>
                    <li>
                        <div class="con">
                            <div class="pic">
                                <img src="${user.avatar!'/lib/images/avatar.jpg'}" alt="" />
                            </div>
                            <div class="txt">
                                <h3>${user.nickname}</h3>
                                <p>设计费：<span>￥${user.startingPrice}元</span></p>
                                <p>擅长：${user.special!}</p>
                                <a href="/designer/u${user.id}" class="btn-h1">联系雇佣TA</a>
                            </div>
                        </div>
                    </li>
                    </#list>

                </ul>
            </div>

            <@bannerAd banner=bannerAd2 />
			<div class="h20"></div>

            <div class="g-tit1">
                <a href="/niuren/index" class="a1">更多>></a>
                <h3>原创出品<span>帮???中小企业主提供创意解决方案</span></h3>
            </div>
            <ul class="m-list3">
                <#list zuopinList as zuopin>
                <li>
                    <div class="con">
                        <div class="pic">
                            <a href="/zuopin/${zuopin.id}">
                                <img src="/user/${zuopin.cover}" alt="" /></a>
                        </div>
                        <div class="bot">
                            <div class="face">
                                <a href="/designer/u${zuopin.userId}">
                                    <img src="${zuopin.userAvatar!'/lib/images/avatar.jpg'}" alt=""/></a>
                            </div>
                            <div class="txt">
                                <h3>${zuopin.title}</h3>
                                <p>${zuopin.username} / ${zuopin.province} ${zuopin.city}
                                </p>
                                <span class="price">￥${zuopin.price}</span>
                                <p>擅长：${zuopin.userSpecial!}</p>
                            </div>
                        </div>
                    </div>
                </li>
                </#list>
            </ul>
            
            <@bannerAd banner=bannerAd3 />
			<div class="h20"></div>

            <div class="g-tit1">
                <h3>首创项目管家交易保障体系<span>???名专业项目管家协助您获得优秀创意</span></h3>
            </div>
            <div class="m-butler">
                <div class="left">
                    <div class="pic">
                        <img src="/lib/images/m1.jpg" alt="">
                    </div>
                    <div class="txt">
                        <h3>??名项目管家</h3>
                        <p>整装待发，为您随时提供保姆式的设计管家服务，为你的每一分投入，保驾护航。三分钟为您找到众多设计师，为您服务。</p>
                        <a href="#" target="_blank" class="btn-h2">在线咨询顾问</a>
                        <p class="fc0">或拨打电话<em>${settings.serviceTel!}</em></p>
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
                    </form>
                </div>
            </div>

			<div class="h30"></div>
        </div>
    </div>
</@layout.body>
<@layout.foot>
    <link rel="stylesheet" href="/lib/css/slick.css">
    <script src="/lib/js/slick.min.js"></script>
</@layout.foot>
