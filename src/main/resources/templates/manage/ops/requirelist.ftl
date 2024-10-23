<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
	<div class="place">您所在的位置: 系统管理 - 需求列表</div>
	<!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
		<form class="layui-form" method="GET">
            <div class="layui-inline">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-inline">
                    <input type="text" name="keyword" value="${cond.keyword!}" class="layui-input">
                </div>
             </div>
            <div class="layui-inline">
                <button class="layui-btn layui-btn-normal">查询</button>
            </div>
        </form>
		<table id="table1" class="layui-table">
        <thead>
            <tr>
                <th>ID</th>
				<th>称呼</th>
				<th>手机号</th>
				<th>需求类型</th>
				<th>需求描述</th>
				<th width="150">提交时间</th>
				<th></th>
            </tr>
            </thead>
            <#list requireList as require >
            <tr>
                <td class="left">${require.id}</td>
                <td class="left">${require.title}</td>
                <td class="left">${require.mobile}</td>
                <td class="left">${require.category}</td>
                <td class="left">${require.subscribe}</td>
                <td class="left">${require.addTime?datetime}</td>
                <td>
                    <a href="#" class="layui-btn layui-btn-normal layui-btn-sm">编辑</a>
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