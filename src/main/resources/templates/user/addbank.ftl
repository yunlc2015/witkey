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
                               添加银行卡
                            </div>
                        </div>
                        <div class="con con2">
                            <form id="form1">
                                <input type="hidden" id="bank" name="bank" value="${bank}" />
                            <table>
                                <tr>
                                    <th></th>
                                    <td>
                                        <div class="p-cr">
                                            仅限持卡人本人操作，请如实填写以下信息用于实名身份认证。
                                        </div> 
                                    </td>
                                </tr>
                                <tr class="tz-bank-tr">
                                    <th>开户银行：</th>
                                    <td>
                                        <img src='/lib/images/${bank}.gif' alt="">
                                    </td>
                                </tr>
                                <tr>
                                    <th>姓名：</th>
                                    <td>
                                        <input type="text" name="accountname" id="accountname" >
                                    </td>
                                </tr>
                                <tr>
                                    <th>证件：</th>
                                    <td>
                                        <input type="text" name="idcard" id="idcard" class="inp2" onFocus="if (value =='身份证 '){value =''}" onBlur="if (value ==''){value='身份证 '}">
                                    </td>
                                </tr>
                                <tr>
                                    <th>储蓄卡号：</th>
                                    <td><input type="text" name="accountno" id="accountno"></td>
                                </tr>
                                <tr>
                                    <th>手机号码：</th>
                                    <td><input type="text" name="mobile" id="mobile" value="${auth.mobile!}" readonly="readonly" /></td>
                                </tr>
                                <tr>
                                    <th>效验码：</th>
                                    <td><input type="text" name="validcode" id="validcode" class="inp3">
                                        <a href="javascript:;" class="inp-ma js-getPass">免费获取</a>
                                    </td>
                                </tr>
                                <tr>
                                    <th></th>
                                    <td>
                                        <a class="inp-btn inp-btn5 h-btn" href="javascript:;" id="addBank">
                                            <span></span>
                                            统一协议并开通
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <th></th>
                                    <td><a href="" class="a-xy">《快捷支付业务线上服务协议》</a></td>
                                </tr>
                            </table>
                                </form>
                        </div>                       
                    </div>     
                </div>
                <div class="y-Boxright">
                    <div class="kf-cir">
                        <img src="/lib/images/y-kf.png" alt="">
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
                    url: '/getvalidcode?kind=bankadd&tick=' + dt.getTime(),
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

            $("#addBank").click(function () {

                var accname = $("#accountname").val();
                if (accname == '') {
                    alert('请输入姓名。');
                    $("#accname").focus();
                    return;
                }

                var idcard = $("#idcard").val();
                if (idcard == '') {
                    alert('请输入身份证号。');
                    $("#idcard").focus();
                    return;
                } 

                var accountno = $("#accountno").val();
                if (accountno == '') {
                    alert('请输入银行帐号。');
                    $("#accountno").focus();
                    return;
                }

                var mobile = $("#mobile").val();
                if (mobile == '') {
                    alert('请输入手机号。');
                    $("#mobile").focus();
                    return;
                }

                var vc = $("#validcode").val();
                if (vc == '') {
                    alert('请输入验证码。');
                    $("#validcode").focus();
                    return;
                }

                if (once == 0) {
                    once = 1;

                    var options = {
                        url: 'addbank',
                        type: 'post',
                        dataType: 'json',
                        data: $("#form1").serialize(),
                        success: function (ret) {
                            once = 0;

                            if (ret.data == 1) {
                                alert("银行帐户添加成功。");
                                location.href = "withdraw";
                            } else {
                                if (ret.errMsg) {
                                    alert(ret.errMsg);
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
