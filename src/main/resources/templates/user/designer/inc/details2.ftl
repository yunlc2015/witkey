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
<div class="state-box">
	<a href="tel:${project.employer.mobile}" class="tz-s2">联系雇主
		<span class="spop">
			<em class="tel">${project.employer.mobile}</em>
		</span>
	</a>
</div>
<div class="btn">
	<a href="/task/t${project.taskId}" id="hlnkView" runat="server" class="g-btn7 g-btn-blue ani">上传提案文件</a>
	<a href="javascript:void(0);" class="tz-a js-detUpBtn"><span>收起</span></a>
</div>