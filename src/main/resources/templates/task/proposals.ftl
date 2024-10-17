<#--
  云联创威客系统
  Copyright 2015 云联创科技

  提案列表模板
  author: Billy Zhang (billy_zh@126.com)
-->
<div class="ul-list">
    <ul>
        <#list projectList as project>
        <li>
            <#include "/task/inc/${project.viewName}.ftl" >
        </li>
        </#list>
    </ul>
</div>
<div class="page">
<#if totalPage gt 0>
    <ul>
        <#list 1..totalPage as page >
        <li class="on"><a href="">${page}</a></li>
        </#list>
    </ul>
</#if>
</div>