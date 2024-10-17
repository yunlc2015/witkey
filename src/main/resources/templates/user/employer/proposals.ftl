<#--
  云联创威客系统
  Copyright 2015 云联创科技

  项目提案模板
  author: Billy Zhang (billy_zh@126.com)
-->
<#list projectList as project>
<div class="con">
    <div class="top">
        <div class="avatar"><a href="/designer/u${project.userId}">
            <img src="${project.designerAvatar!'/lib/images/avatar.jpg'}" alt="" ><em id="em1" runat="server">v</em></a></div>
        <div class="txt">
            <h4>${project.designerName!}</h4>
            <div class="favor">擅长领域：${project.designerSpecial!}</div>
            <div class="info">
                <span class="time">${project.submitExplain!"未上传"}</span>
                <span class="view ok">${project.readExplain!"雇主已浏览"}</span>
            </div>
        </div>
        
        <#if project.selected==1>
        <div class="state-box" id="choose2">
			<span class="tz-s1">联系设计师
				<div class="spop">
					<a href="" id="hQQ" class="qq">在线沟通</a>
					<a href="tel:${project.designerMobile}" id="hMob" class="tel">${project.designerMobile}</a>
				</div>
			</span>
			<a href="javascript:;" id="commentBtn" class="tz-s2" onclick="toggleComment(${project.taskId}, 'comments_${project.id}');">付款并评价</a>
		</div>
        <#else>
        <a href="javascript:;" onclick="chooseProposal('${boxId}', ${project.taskId}, ${project.id});" class="state" id="choose">选定TA的方案</a>
        </#if>
    </div>
    <div class="botScroll">
        <#list project.fileList as file>
            <div class="slide"><a href="/task/${file.url}" class="fancybox" data-fancybox-group="gallery_${project.id}">
                <img src="/task/${file.url}" alt="" style="border:0"></a></div>
        </#list>
    </div>
    <div class="botTxtScroll">
		<p>${project.proposalDescribe!}</p>
	</div>

    <#if project.selected==1 >
    <#if project.rating?? >
    <div class="tz-comment" id="comment2">
        <h4>我的评价</h4>
        <div class="com-con">
            ${project.rating.content!}
        </div>
     </div>
     <#else>
    <div class="tz-comment" id="comment_${project.id}">
		<h4>我要评价</h4>
		<div class="com-con">
			<form onsubmit="return doRating('rating_${project.id}', ${project.taskId}, '${boxId}');" id="rating_${project.id}">
                <input type="hidden" name="projectid" id="pid" value="${project.id}" />
			<p>其他雇主，需要你的建议哦！</p>
			<div class="inp-box">
				评分<span class="m-score"><ul value="0"></ul><input type="hidden" name="star" value="0"></span>
			</div>
			<span class="pl10">评论</span>
			<textarea name="content" class="inp"></textarea>
			<label for="c1"><input type="checkbox" id="c1" class="inp-c">匿名提交</label>
			<input type="submit" value="确认评论" class="g-btn7 g-btn-blue ani r">
			</form>
		</div>
	</div>
     </#if>
     </#if>
</div>
</#list>
