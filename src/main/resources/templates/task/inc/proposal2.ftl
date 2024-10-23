<#--
  云联创威客系统
  Copyright 2015 云联创科技

  author: Billy Zhang (billy_zh@126.com)
-->
<div class="drate-top fix">
    <a href="/designer/u${project.userId}" class="img">
        <img src="${project.designerAvatar!'/lib/images/y-pic2.png'}" style="width:100px" alt="">
        <em>v</em>
    </a>
    <div class="txt">
        <div class="txtl">
            <h3>${project.designerName!}</h3>
            <p>擅长领域：${project.designerSpecial!}</p>
            <div class="s-h">
                <span class="s1">${project.submitExplain!"未上传"}</span>
                <span class="s2">${project.readExplain!"雇主未浏览"}</span>
            </div>
            <div class="t-btn">
                ${project.stateExplain!"交稿已隐藏"}
            </div>
        </div>
        <#if project.selected==1 && project.state.constant==1>
        <div class="txtr cir-red">
            <span><em>中标<br>
                方案</em></span>
        </div>
        </#if>
    </div>
</div>
