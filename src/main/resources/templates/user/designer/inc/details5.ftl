<#--
  云联创威客系统
  Copyright 2015 云联创科技

  author: Billy Zhang (billy_zh@126.com)
-->
<div class="top">
	<h4>￥${project.task.designAmount} ${project.task.requirement}</h4>
	<div class="brief">${project.task.designDetails!}</div>
	<div class="info">
		<span class="time">未上传作品</span>
		<span class="view">雇主未浏览</span>
	</div>
</div>
<div class="btn">
    <#if project.waitingAccept >
	    <a href="javascript:void(0)" onclick="rejectTask(${project.taskId})" class="g-btn7 g-btn-blue ani mr20">委婉拒绝</a>
	    <a href="javascript:void(0)" onclick="acceptTask(${project.taskId})" class="g-btn7 g-btn-blue ani">确认接标</a>
    <#elseif project.cancelled >
        <a href="javascript:void(0)" class="g-btn7 g-btn-blue ani">任务已拒绝</a>
	<#else>
        <a href="javascript:void(0)" class="g-btn7 g-btn-blue ani">等待雇主付款</a>
    </#if>
	<a href="javascript:void(0);" class="tz-a js-detUpBtn"><span>收起</span></a>
</div>