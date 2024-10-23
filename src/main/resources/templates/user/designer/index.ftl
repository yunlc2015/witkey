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
                <li class="on"><a href="index">个人中心</a></li>
                <li><a href="zuopin">作品</a></li>
                <li><a href="tasklist">交易</a></li>
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
                        <div class="cash">账户余额： <span class="dollar"><em>
                            ${user.balance}</em> 元</span>     创意币：<em>0</em></div>
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
                <h3 class="tz-tit5">基本信息</h3>
                <div class="tz-col-l">
                    <div class="tz-padd tz-account3">
                        <dl class="tz-basicInfo">
                            <dd><span>昵称：</span>${user.nickname!}</dd>
                            <dd><span>性别：</span>${user.gender!}</dd>
                            <dd><span>手机：</span>${user.mobile2!}</dd>
                            <dd><span>QQ：</span>${user.im!}</dd>
                            <dd><span>工作年限：</span>${user.workYear!}</dd>
                            <dd><span>工作地点：</span>${user.location!}</dd>
                            <dd><span>院校/奖项：</span>${user.education!}</dd>
                            <dd><span>工作单位：</span>${user.company!}</dd>
                        </dl>
                    </div>
                </div>
                <div class="tz-col-r">
                    <div class="tz-padd tz-account3">
                        <table class="tz-table3">
                            <tr>
                                <th>认证状况</th>
                                <td>
                                    <#if authState==10 >未认证（实名认证后才能接市场标）<a href="realauth" class="tz-a">去实名认证</a>
                                    <#elseif authState==11 >实名认证审核中...
                                    <#elseif authState==12 >已实名认证 (实力认证后才能接管家标）<a href="abilityauth" class="tz-a">去实力认证</a>
                                    <#elseif authState==13 >实名认证未通过 <a href="realauth" class="tz-a">重新认证</a>
                                    <#elseif authState==21 >已实名认证 (实力认证后才能接管家标） 实力认证审核中...
                                    <#elseif authState==22 >已实名认证 高级设计师
                                    <#elseif authState==23 >已实名认证 (实力认证后才能接管家标） 实力认证未通过 <a href="abilityauth" class="tz-a">重新认证</a>
                                    </#if>
                                </td>
                            </tr>
                            <tr>
                                <th>个人擅长</th>
                                <td>${user.special!'暂无内容'}</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="h20"></div>

            <div class="tz-row">
                <h3 class="tz-tit5">个人简介</h3>
                <div class="tz-padd tz-account4">
                    <p>${user.intro!'暂无内容'}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="h50"></div>

</@layout.body>
<@layout.foot>
</@layout.foot>
