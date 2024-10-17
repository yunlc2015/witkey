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
	<a href="" class="state">雇主已选其他方案</a>
</div>
<div class="jsBotSlide">
    <div class="botScroll">
        <#list project.fileList as file>
		<div class="slide"><a href="/task/${file.url}" class="fancybox" data-fancybox-group="gallery_${project.id}"><img src="/task/${file.url}" alt=""></a></div>
        </#list>
    </div>
</div>
<div class="botTxtScroll">
	<p>${project.proposalDescribe!}</p>
</div>
<div class="btn">
	<a href="/task/t${project.taskId}" class="g-btn7 g-btn-blue ani">查看选定方案</a>
	<a href="javascript:void(0);" class="tz-a js-detUpBtn"><span>收起</span></a>
</div>