<#--
  云联创威客系统
  Copyright 2015 云联创科技

  author: Billy Zhang (billy_zh@126.com)
-->
<div class="btn" style="border-top: 0;">
	<span class="blank fc_orange">
		<img src="/lib/images/ico-face3.png" alt="">订单被拒绝，可能您的赏金有点薄！
	</span>
    
	<a href="/niuren/index" class="g-btn3 g-btn-blue ani">重新发布</a>
	<a href="/task/edit2?taskid=${task.id}" class="g-btn3 g-btn-blue ani">修改订单</a>
	<a href="javascript:void(0);" onclick="deleteTask(${task.id?c})" class="g-btn3 g-btn-blue ani js-removeOrder">删除订单</a>
	<a href="javascript:void(0);" class="tz-a js-detUpBtn mt20"><span>收起</span></a>
</div>