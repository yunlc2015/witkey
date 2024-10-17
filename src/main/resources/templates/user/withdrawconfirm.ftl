<#--
  云联创威客系统
  Copyright 2015 云联创科技

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
    <div id="nv" class="g-borb">
        <@webNav />
    </div>
    
	<div id="bd">
		<div class="wp">
            <div class="y-Box">
                <div class="y-Boxleft">
                    <div class="pay-bank">
                        <div class="tit tit2">
                            <div class="titl">
                               确认提现信息
                            </div>
                        </div>
                        <div class="con con2 con3">
                            <div class="table">
                                <table>
                                    <tr>
                                        <th>银行卡信息：</th>
                                        <td>
                                            <p>${account.accountName}</p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <td>
                                            <p class="p2">   
                                                <img src="/lib/images/${account.bankId}.gif" alt="">                       
                                                （${accountNo}）
                                            </p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>提取金额：</th>
                                        <td>
                                            <strong class="st1">${amount}</strong> 元
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>到账时间：</th>
                                        <td>
                                            <strong class="st2">${dzTime}前</strong>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="con-z">
                                <form id="form1">
                                    <input type="hidden" name="bank" id="bank" value="${account.bankId}" />
                                    <input type="hidden" name="amount" id="amount" value="${amount}" />
                                    <input type="hidden" name="hcode" id="hcode" value="${hcode}" />
                                <table>
                                    <tr>
                                        <th>短信验证码：</th>
                                        <td>
                                            <input type="text" name="validcode" id="validcode" class="inp4">
                                            <a id="getValidCode" href="javascript:;" class="inp-ma js-getPass">发送验证码</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <td class="td-z">
                                            <a class="inp-btn inp-btn6  h-btn" id="confirm" href="javascript:;">
                                                <span></span>
                                                确认提现
                                            </a>
                                            <a href="javascript:history.go(-1)" class="back">返回修改</a>
                                        </td>
                                    </tr>
                                </table>
                                    </form>
                            </div>
                        </div>
                                               
                    </div>     
                </div>
                <div class="y-Boxright">
                    <div class="kf-cir">
                        <img src="/images/y-kf.png" alt="">
                        <h3>联系客服</h3>
                        <span>帮您发需求</span>
                    </div>
                    <div class="cou-us ">
                        <a href="http://webim.qiao.baidu.com//im/msg?siteid=8020855" target="_blank" class="h-btn"><span></span>联系在线客服</a>
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

    <script>
        $(document).ready(function ($) {
            $(".j-btn").click(function () {
                $(".bank-pop").show();
            });
            $(".bank-pop a.close").click(function () {
                $(".bank-pop").hide();
            });

            // 倒计时
            $('.js-getPass').click(function (event) {
                if ($('.js-getPass').hasClass('sending')) {
                    return;
                }

                var dt = new Date();
                $.ajax({
                    url: '/getvalidcode?kind=withdraw&bankid=${account.bankId}&tick=' + dt.getTime(),
                    type: 'post',
                    dataType: 'json',
                    success: function (resp) {
                        if (resp.errCode==0) {
                            alert(resp.data);
                        } else {
                            alert(resp.errMsg);
                        }
                    }
                });

                $(this).toggleClass('sending');
                $(this).text('99秒后重新发送');
                var num = 99;
                setInterval(function () {
                    if ($('.js-getPass').hasClass('sending')) {
                        if (num > 0) {
                            num = num - 1;
                            $('.js-getPass').text(num + '秒后重新发送');
                        } else {
                            $('.js-getPass').text('获取验证码').removeClass('sending');
                            num = 99;
                        }
                    }
                }, 1000);
            });

            var once = 0;

            $("#confirm").click(function () {

                var vc = $("#validcode").val();
                if (vc == '') {
                    alert('请输入验证码。');
                    $("#validcode").focus();
                    return;
                }

                if (once == 0) {
                    once = 1;

                    var options = {
                        url: 'dowithdraw',
                        type: 'post',
                        dataType: 'json',
                        data: $("#form1").serialize(),
                        success: function (json) {
                            once = 0;

                            var userClass = ${user.clazz};
                            if (json.data == 1) {
                                alert("提交成功，请等候处理。");
                                if (userClass == 2) {
                                    location.href = "/user/employer/index";
                                } else {
                                    location.href = "/user/designer/index";
                                }
                            } else {
                                if (json.errMsg) {
                                    alert(json.errMsg);
                                } else {
                                    alert("提交失败，请稍后重试。");
                                }
                            }
                        }
                    };
                    $.ajax(options);
                }

                return false;
            });
        });
    </script>
	
</@layout.foot>