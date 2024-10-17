<#--
  云联创威客系统
  Copyright 2015 云联创科技

  author: Billy Zhang (billy_zh@126.com)
-->
<div class="top">
	<h4>￥${project.task.designAmount} ${project.task.requirement}</h4>
	<div class="brief">需求描述：${project.task.designDetails!}</div>
	<div class="info">
		<span class="time">${project.submitExplain!}</span>
		<span class="view ok">${project.readExplain!}</span>
	</div>	
</div>
<div class="state-box">
	<a href="tel:${project.employer.mobile}" class="tz-s2">联系雇主
		<span class="spop">
			<em class="tel">${project.employer.mobile}</em>
		</span>
	</a>
	<span class="tz-s1">方案已中标</span>
</div>
<div class="botScroll">
    <#list project.fileList as file>
	<div class="slide"><a href="/task/${file.url}" class="fancybox" data-fancybox-group="gallery_${project.id}"><img src="/task/${file.url}" alt=""></a></div>
	</#list>
</div>
<div class="botTxtScroll">
	<p>${project.proposalDescribe!}</p>
</div>
<div class="btn">
	<a href="javascript:void(0);" class="tz-a js-detUpBtn"><span>收起</span></a>
</div>
