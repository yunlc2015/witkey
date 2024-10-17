<#--
  云联创威客系统
  Copyright 2015 云联创科技

  支付页
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
        <@mobHeader current="task" />
    </div>
    <aside id="aside">
        <#include "/inc/mobnav.ftl" />
    </aside>
    <div id="nv" class="g-borb">
        <@webNav current="task" />
    </div>
    
	<div id="bd">
		<div class="wp">
            <div class="y-Box">
                <div class="y-Boxleft">
                    <form id="pay-form" target="_blank" method="post" action="dopay">
                        <input type="hidden" id="tradeno" name="tradeno" value="${trade.tradeNo }" />
                        <input type="hidden" id="tradecode" name="tradecode" value="${tradeCode }" />
                        <input type="hidden" id="wap" name="wap" value="0" />
                    <div class="num-list num-list2">
                        <ul>
                            <li class="ok">
                                <div class="cir">1</div>
                                <div class="c"></div>
                                <span class="tp">选择类目</span>
                                <div class="line"></div>
                            </li>
                            <li class="ok">
                                <div class="cir">2</div>
                                <span class="tp">完善需求</span>
                                <div class="line"></div>
                            </li>
                            <li class="on">
                                <div class="cir">3</div>
                                <span class="tp">提交订单</span>
                                <div class="line">
                                    <div class="line-on">
                                        <span></span>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="cir">4</div>
                                <div class="c"></div>
                                <span class="tp">支付完成</span>
                                <div class="line"></div>
                            </li>
                        </ul>
                    </div>
                    <div class="pay-table">
                        <table>
                            <tr>
                                <th>交易名称</th>
                                <th>交易编号</th>
                                <th align="center">交易金额</th>
                            </tr>
                            <tr>
                                <td>
                                    雇主 ${user.nickname}
                                    <br>
                                    <span>${trade.subject }</span>
                                </td>
                                <td>
                                    ${settings.appName }
                                    <br>
                                    <span>${trade.tradeNo }</span>
                                </td>
                                <td align="center">
                                    <strong>${trade.totalAmount }元</strong>
                                </td>
                            </tr>
                        </table>
                        <div class="table-zt">
                            <div class="coupon">
                                <input type="checkbox" name="usebalance" id="useBalance" value="1" />
                                使用余额(${user.balance }) <input type="text" id="balance" name="balance" style="display:none;" />
                            </div>
                            
                            <p>订单<span id="payAmount">${trade.totalAmount }</span>元使用以下方式付款</p>
                            <span>${settings.appName}郑重承诺不收取你和服务商的任何佣金，请放心付款</span>
                        </div>
                    </div> 
                    <div class="payment">
                        <h3>支付平台：</h3>
                        <ul>

                            <#if settings.alipayEnable==1 >
                            <li>
                                <div class="bank-li">
                                    <label for="r27"><input type="radio" id="alipay" name="paytype" value="101">
                                    <div class="pic">
                                        <img src="/lib/images/y-zf1.png" alt="">
                                        支付宝支付
                                    </div>
                                    </label>
                                </div>
                            </li>
                            </#if>
                            <#if settings.wxpayEnable==1 >
                            <li>
                                <div class="bank-li">
                                    <label for="r28"><input type="radio" id="wxpay" name="paytype" value="102">
                                    <div class="pic">
                                        <img src="/lib/images/y-zf2.png" alt="">
                                        微信支付
                                    </div>
                                    </label>
                                </div>
                            </li>
                            </#if>
                        </ul>
                    </div> 
                    <a class="inp-btn inp-btn2 h-btn" id="confirmPay">
                        <span></span>
                        确认支付
                    </a>    
                    </form>    
                </div>
                <div class="y-Boxright">
                    <div class="kf-cir">
                        <img src="/lib/images/y-kf.png" alt="">
                        <h3>联系客服</h3>
                        <span>帮您发需求</span>
                    </div>
                    <div class="cou-us ">
                        <a href="#" target="_blank" class="h-btn"><span></span>联系在线客服</a>
                        <p>周一至周五9:00-18:00</p>
                        <h2>${settings.serviceTel!}</h2>
                        <p>周一至周五9:00-18:00</p>
                    </div> 
                </div>  
            </div>   
        </div>
	</div>

</@layout.body>
<@layout.foot>

     <link rel="stylesheet" href="/lib/css/colorbox.css" />
    <script src="/lib/js/jquery.colorbox.js"></script>
    <script type="text/javascript">

        function closeDialog(ret) {
            if (ret == 1) { //支付成功
                location.href = '/user/employer/tasklist';
            } else {
                $.fn.colorbox.close();
            }
        }

        $(document).ready(function ($) {
            $('.radioBox label').click(function () {
                $(this).parents(".radioBox").find("label").removeClass("checked");
                $(this).addClass("checked");
            });

            <#if user.balance==0 >
            $("#useBalance").attr("disabled", "disabled");
            </#if>

            $("#useBalance").on('click', function () {
                var checked = $(this).prop("checked");
                if (checked) {
                    $("#balance").show();
                } else {
                    $("#balance").hide();
                    $("#balance").val('');
                    $("#payAmount").html("￥" + totalAmount);
                }
            });

            var userBalance = ${user.balance };
            var totalAmount = ${trade.totalAmount };

            $("#balance").blur(function () {
                var val = $(this).val();
                if (val == '')
                    return;

                if (isNaN(val)) {
                    alert('无效的金额，请修改。');
                    $(this).focus();
                } else {
                    if (val <= userBalance) {
                        var payAmount = totalAmount-val;
                        if (val > 0 && payAmount > 0) {
                            $("#payAmount").html("￥" + payAmount);
                        } else {
                            alert('错误的金额，请修改。');
                            $(this).focus();
                        }
                    } else {
                        alert('不能大于余额，请修改。');
                        $(this).focus();
                    }
                }
            });

            var browser = {
                versions: function () {
                    var u = navigator.userAgent, app = navigator.appVersion;
                    return {
                        trident: u.indexOf('Trident') > -1, //IE内核 
                        presto: u.indexOf('Presto') > -1, //opera内核 
                        webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核 
                        gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核 
                        mobile: !!u.match(/AppleWebKit.*Mobile.*/) || !!u.match(/AppleWebKit/), //是否为移动终端 
                        ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端 
                        android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器 
                        iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器 
                        iPad: u.indexOf('iPad') > -1, //是否iPad 
                        webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部 
                    };
                }()
            }

            var once = 0;
            $("#confirmPay").click(function () {

                if (once == 0) {

                    //var wap = (browser.versions.iPhone || browser.versions.android || browser.versions.iPad) ? 1 : 0;
                    //$("input[name=\"wap\"]").val(wap);

                    var balance=0.0;
                    var useBalance=0;
                    var checked = $("#useBalance").prop("checked");
                    if (checked) {
                        useBalance = 1;
                        balance = $("#balance").val();
                        if (balance == '' || isNaN(balance)) {
                            alert('请输入有效的金额。');
                            return;
                        }

                        if (balance == ${trade.totalAmount}) {
                            $('#pay-form').submit();
                            return;
                        }
                    }

                    var paytype = $("input[name='paytype']:checked").val();
                    if (paytype == undefined || paytype == '') {
                        alert('请选择一个支付方式。');
                        return;
                    }

                    if (paytype == 101) {  //支付宝
                        $.colorbox({
                            href: "waitforpay?tradeno=${trade.tradeNo}",
                            iframe: true,
                            innerWidth: 500,
                            innerHeight: 230,
                            escKey: false,
                            overlayClose: false,
                            onLoad: function () {
                                $('#cboxClose').remove();
                            }
                        });

                        once = 1;
                        $("#pay-form").submit();
                    } else if (paytype == 102) {  //微信
                        once = 1;
                        
                        $.colorbox({
                            href: "dopay2?tradeno=${trade.tradeNo}&tradecode=${tradeCode}&paytype=102&usebalance=" +useBalance + "&balance=" + balance,
                            iframe: true,
                            innerWidth: 500,
                            innerHeight: 480,
                            escKey: false,
                            overlayClose: false,
                            onLoad: function () {
                                $('#cboxClose').remove();
                            }
                        });
                    } else {
                        alert('请选择一个支付方式。');
                    }

                    once = 0;
                }
            });
        });

    </script>
	
</@layout.foot>