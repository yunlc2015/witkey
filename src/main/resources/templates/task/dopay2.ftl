<#--
  云联创威客系统
  Copyright 2015 云联创科技

  微信支付页
  author: Billy Zhang (billy_zh@126.com)
-->
<#import "/layout/dialog.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>

    <form id="form1">
        <input type="hidden" id="tradeno" name="tradeno" value="${tradeNo}" />
    
         <div id="pay-weix" class="pop-fancy">
            <#if message?has_content>
            <div class="pop-con">
                <div class="head">
                    <a href="#" onclick="parent.closeDialog(0);" class="close"></a>
                    <h4>支付异常</h4>
                </div>
                <div class="body">
                    <p>交易号：${tradeNo!}</p>
                    <p>异常信息：${message}</p>
                </div>
            </div>
            <#else>
            <div class="pop-con">
                <div class="head">
                    <a href="#" onclick="parent.closeDialog(0);" class="close"></a>
                    <h4>支付提示</h4>
                </div>
                <div class="body">
                    <p>交易号：${tradeNo}</p>
                    <p>提交时间：${add_time?datetime}</p>
                    <p>请您在20分钟内支付，否则二维码将失效。</p>
                </div>
                <div class="foot">
                    <p>支付金额<em>￥<i>${amount}</i></em>，请使用微信扫描下方二维码完成支付</p>
                    <div class="weix">
                        <img src="/qrcode?url=${code_url} Style="width: 200px; height: 200px;" />
                        <a href="#" onclick="parent.closeDialog(0);" class="fc_grey">&lt;选择其他支付方式</a>
                    </div>
                </div>
            </div>
            </#if>
        </div>
    </form>
</@layout.body>
<@layout.foot>
    <script type="text/javascript">
        $(document).ready(function ($) {
            var once = 0;

            $("#check").click(function () {
                if (once == 0) {
                    once = 1;

                    var dt = new Date();
                    var options = {
                        url: 'paycheck&tradeno=${tradeNo}&dt=' + dt.getTime(),
                        type: 'get',
                        dataType: 'json',
                        data: $("#form1").serialize(),
                        success: function (resp) {
                            once = 0;

                            if (resp.data == 1) {
                                parent.closeDialog(1);
                            } else {
                                alert("交易未完成，请稍后重试。");
                            }
                        }
                    };
                    $.ajax(options);
                }

                return false;
            });
        });

        function checkState() {
            var dt = new Date();
            var options = {
                url: 'paycheck&tradeno=${tradeNo}&dt=' + dt.getTime(),
                type: 'get',
                dataType: 'json',
                success: function (resp) {
                    if (resp.data == 1) {
                        parent.closeDialog(1);
                    } else {
                        setTimeout("checkState()", 2000);
                    }
                }
            };
            $.ajax(options);
        }

        checkState();

    </script>
</@layout.foot>