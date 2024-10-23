<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
	<div class="place">您所在的位置: 内容管理 - 文章管理</div>
	<!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <table id="table1" class="layui-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>标题</th>
                <th>路径</th>
                <th>操作</th>
            </tr>
            </thead>
            <#list articleList as article >
            <tr>
                <td class="left">${article.id}</td>
                <td class="left">${(article.title)!}</td>
                <td class="left">${article.path}</td>
                <td>
                    <a href="javascript:openDialog('articleedit?id=${(article.id)?c}', '文章编辑', '850px', '780px');" class="layui-btn layui-btn-normal layui-btn-sm">编辑</a>
                </td>
            </tr>
            </#list>
        </table>
        <div id="pager"></div>
    </div>
</@layout.body>
<@layout.foot>
    <script type="text/javascript">
    <#include "/manage/inc/pagerscript.ftl" >
    </script>
</@layout.foot>
