<#--
  云联创威客系统
  Copyright 2015 云联创科技

  author: Billy Zhang (billy_zh@126.com)
-->
<div class="btn" style="border-top: 0;">
    <span class="blank">
        <img src="/lib/images/ico-face3.png" alt="">付款未完成。
    </span>
    <a href="/task/pay?tradeno=${task.tradeNo!}" target="_blank" class="g-btn7 g-btn-blue ani">马上付款</a>
    <a href="/task/edit?taskid=${task.id}" target="_blank" class="g-btn3 g-btn-blue ani">修改订单</a>
    <a href="javascript:void(0);" onclick="deleteTask(${task.id})" runat="server" class="g-btn3 g-btn-blue ani js-removeOrder">删除订单</a>
    <a href="javascript:void(0);" class="tz-a js-detUpBtn"><span>收起</span></a>
</div>