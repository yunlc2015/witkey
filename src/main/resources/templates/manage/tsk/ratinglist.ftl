<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
	<div class="place">您所在的位置: 任务管理 - 评价记录</div>
	<!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="GET">
             <div class="layui-inline">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" value="${cond.username!}" class="layui-input">
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
                 <th>用户</th>
                <th> 评分 </th>
                <th> 内容 </th>
                <th> 评价时间</th>
                <th width="80">操作</th>
            </tr>
            </thead>
            <#list ratingList as rating >
            <tr>
                <td class="left">${rating.id}</td>
                <td class="left">${rating.username}</td>
                <td class="left">${rating.star}</td>
                <td class="left">${rating.content}</td>
                <td class="left">${rating.addTime?datetime}</td>
                <td>
                </td>
            </tr>
            </#list>
        </table>
        <div id="pager"></div>
    </div>
</@layout.body>
<@layout.foot>
    <script>
        <#include "/manage/inc/pagerscript.ftl" >
    </script>
</@layout.foot>
