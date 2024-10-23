<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
	<div class="place">您所在的位置: 财务管理 - 付款记录</div>
	<!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="GET">
             <div class="layui-inline">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" value="" class="layui-input">
                </div>
             </div>
            <div class="layui-inline">
                <button class="layui-btn layui-btn-normal">查询</button>
            </div>
        </form>
        <table id="table1" class="layui-table">
        <thead>
            <tr>
                <th>ID </th>
                <th>用户 </th>
                <th>摘要 </th>
                <th>金额 </th>
                <th width="150">时间 </th>
                <th width="100">操作 </th>
            </tr>
            </thead>
            <#list paymentList as payment >
            <tr>
                <td class="left">${payment.id}</td>
                <td class="left">${payment.username}</td>
                <td class="left">${payment.summary}</td>
                <td class="left">${payment.amount}</td>
                <td class="left">${payment.payTime?datetime}</td>
                <td>
                    <a href="#" class="layui-btn layui-btn-normal layui-btn-sm">详情</a>
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
