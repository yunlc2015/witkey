<#--
  云联创威客系统
  Copyright 2015 云联创科技

  支付结果等待页
  author: Billy Zhang (billy_zh@126.com)
-->
<#import "/layout/dialog.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>

    <div id="pay-zfb" class="pop-fancy">
        <div class="pop-con">
            <div class="head">
                <a href="#" onclick="parent.closeDialog(0);" class="close"></a>
                <h4>支付提示</h4>
            </div>
            <div class="body">
                <p>请问您完成付款了吗？</p>
                <p>如没有，请在新打开的网上银行页面进行付款的操作。</p>
                <p>提示超过商户限额？请查看 <a href="" class="fc_blue">各银行网上支付限额</a></p>
            </div>
            <div class="foot">
                <a href="#" onclick="parent.closeDialog(0);" class="g-btn7 g-btn-grey">返回选择其他银行</a>
                <a id="check" href="#" class="g-btn7 g-btn-blue ml20">完成支付</a>
            </div>
        </div>
    </div>

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
                          url: 'paycheck?tradeno=${tradeNo}&dt=' + dt.getTime(),
                          type: 'get',
                          dataType: 'json',
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
                  url: 'paycheck?tradeno=${tradeNo}&dt=' + dt.getTime(),
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

</@layout>
