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
                            <li class="ok">
                                <div class="cir">3</div>
                                <span class="tp">提交订单</span>
                                <div class="line"></div>
                            </li>
                            <li class="ok">
                                <div class="cir">4</div>
                                <div class="c"></div>
                                <span class="tp">支付完成</span>
                                <div class="line"></div>
                            </li>
                        </ul>
                    </div>
                    <div class="pay-bank">
                        <div class="tit">
                            <div class="titl">
                                提取余额到银行卡
                            </div>
                            <div class="titr">
                                <span>余额：<strong>${user.balance}</strong>元
                                </span>
                                <a href="#" class="j-btn">+ 添加银行卡</a>
                            </div>
                        </div>
                        <div class="con">
                            <form id="form1" action="withdrawconfirm" method="post">
                                <input type="hidden" id="balance" name="balance" />
                            <table>
                                <tr>
                                    <th>选择银行卡：</th>
                                    <td>
                                        <ul>
                                            <#list accountList as account >
                                            <li>
                                                <div class="bank-li">
                                                    <label for="r1">
                                                        <input type="radio" id='bank${account.bankId}' name="bank" value='${account.bankId}'></label>
                                                    <div class="pic">
                                                        <img src='/lib/images/${account.bankId}.gif' alt="" >
                                                        <span>尾号：${account.accountNo!}</span>
                                                        <div class="sh">
                                                            快捷 
                                                        </div>
                                                        <div class="day">
                                                            <em></em>次日 
                                                        </div>
                                                        <span class="sc">限额20000元/次</span>
                                                    </div>
                                                </div>
                                            </li>
                                            </#list>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <th>提取金额：</th>
                                    <td>
                                        <input class="inp1" type="text" name="amount" id="amount" value=" 输入金额" onfocus="if (value ==' 输入金额'){value =''}" onblur="if (value ==''){value=' 输入金额'}">
                                        元
                                    </td>
                                </tr>
                                <tr>
                                    <th>到账时间：</th>
                                    <td>
                                        <div class="bank-li bank-li2">
                                            <label for="r1">
                                                <input type="checkbox" id="checkbox1" name="time" id=""></label>
                                            <div class="pic">
                                                次日到账</em>
                                                <p></p>
                                            </div>
                                        </div>
                                        <p class="p-zf">提现时平台将扣除发票税金6%，提现银行手续费1.2%（手续费不足10元时以10元计）</a></p>
                                    </td>
                                </tr>
                                <tr>
                                    <th></th>
                                    <td>
                                        <a class="inp-btn inp-btn3 h-btn" id="submit-next" href="javascript:;">
                                            <span></span>
                                            下一步
                                        </a>
                                    </td>
                                </tr>
                            </table>
                                </form>
                        </div>

                        <div class="bank-pop">
                            <div class="tit2">
                                <strong>添加新银行卡</strong>
                                <span>提现仅支持如下13家银行的借记卡</span>
                                <a href="#" class="close"></a>
                            </div>
                            <div class="pop-con">
                                <form id="form2" runat="server">
                                    <ul>
                                        <li>
                                            <div class="bank-li">
                                                <label for="r1">
                                                    <input type="radio" id="bank4" name="bank2" value="4"></label>
                                                <div class="pic">
                                                    <img src="/lib/images/4.gif" alt="">
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="bank-li">
                                                <label for="r1">
                                                    <input type="radio" id="bank5" name="bank2" value="5"></label>
                                                <div class="pic">
                                                    <img src="/lib/images/5.gif" alt="">
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="bank-li">
                                                <label for="r1">
                                                    <input type="radio" id="bank6" name="bank2" value="6"></label>
                                                <div class="pic">
                                                    <img src="/lib/images/6.gif" alt="">
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="bank-li">
                                                <label for="r1">
                                                    <input type="radio" id="bank7" name="bank2" value="7"></label>
                                                <div class="pic">
                                                    <img src="/lib/images/7.gif" alt="">
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="bank-li">
                                                <label for="r1">
                                                    <input type="radio" id="bank8" name="bank2" value="8"></label>
                                                <div class="pic">
                                                    <img src="/lib/images/8.gif" alt="">
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="bank-li">
                                                <label for="r1">
                                                    <input type="radio" id="bank9" name="bank2" value="9"></label>
                                                <div class="pic">
                                                    <img src="/lib/images/9.gif" alt="">
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="bank-li">
                                                <label for="r1">
                                                    <input type="radio" id="bank12" name="bank2" value="12"></label>
                                                <div class="pic">
                                                    <img src="/lib/images/12.gif" alt="">
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="bank-li">
                                                <label for="r1">
                                                    <input type="radio" id="bank13" name="bank2" value="13"></label>
                                                <div class="pic">
                                                    <img src="/lib/images/13.gif" alt="">
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="bank-li">
                                                <label for="r1">
                                                    <input type="radio" id="bank14" name="bank2" value="14"></label>
                                                <div class="pic">
                                                    <img src="/lib/images/14.gif" alt="">
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="bank-li">
                                                <label for="r1">
                                                    <input type="radio" id="bank16" name="bank2" value="16"></label>
                                                <div class="pic">
                                                    <img src="/lib/images/16.gif" alt="">
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="bank-li">
                                                <label for="r1">
                                                    <input type="radio" id="bank18" name="bank2" value="18"></label>
                                                <div class="pic">
                                                    <img src="/lib/images/18.gif" alt="">
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="bank-li">
                                                <label for="r1">
                                                    <input type="radio" id="bank20" name="bank2" value="20"></label>
                                                <div class="pic">
                                                    <img src="/lib/images/20.gif" alt="">
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="bank-li">
                                                <label for="r1">
                                                    <input type="radio" id="bank29" name="bank2" value="29"></label>
                                                <div class="pic">
                                                    <img src="/lib/images/29.gif" alt="">
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                    <div class="c"></div>
                                    <a class="inp-btn inp-btn4 h-btn" id="addbank">
                                        <span></span>
                                        下一步
                                    </a>
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

            $("#amount").blur(function () {
                var val = $(this).val();
                if (isNaN(val)) {
                    alert('无效的金额，请修改。');
                    $(this).focus();
                }
            });

            var once = 0;

            $("#submit-next").click(function () {
                var bank = $('input[name="bank"]:checked').val();
                if (bank == undefined) {
                    alert('请选择一个银行。');
                    return;
                }

                var str = $("#amount").val();
                if (str == '' || str == ' 输入金额') {
                    alert('请输入金额。');
                    $("#amount").focus();
                    return;
                }

                var amount = parseFloat(str);
                if (amount <= 0.0) {
                    alert('无效的金额，请修改。');
                    $("#amount").focus();
                    return;
                }

                if (amount > parseFloat($("#balance").val())) {
                    alert('提现金额不能大于余额，请修改。');
                    $("#amount").focus();
                    return;
                }

                $("#form1").submit();

                return false;
            });

            $("#addbank").click(function () {
                var bank = $("input[name='bank2']:checked").val();
                if (bank == undefined) {
                    alert('请选择一个银行。');
                } else {
                    location.href = 'addbank?bank=' + bank;
                }
            });
        });
    </script>

</@layout.foot>
