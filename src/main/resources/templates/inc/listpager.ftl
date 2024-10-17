<#--
  云联创威客系统
  Copyright 2015 云联创科技

  列表分页模版
  author: Billy Zhang (billy_zh@126.com)
-->
<ul class="g-page">
    <li><a href="${pager.getPrevUrl()}" class="prev">&laquo;</a></li>
    <#if pager.totalPages gt 0 >
    <#list (pager.begin)..(pager.end) as i >
    <#if i==pager.pageNo >
    <li><a href="${pager.getPageUrl(i)}" class="on">${i}</a></li>
    <#else>
    <li><a href="${pager.getPageUrl(i)}">${i}</a></li>
    </#if>
    </#list>
    </#if>
    <li><a href="${pager.getNextUrl()}" class="next">&raquo;</a></li>
</ul>