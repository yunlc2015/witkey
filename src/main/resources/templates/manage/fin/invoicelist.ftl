<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
	<div class="place">您所在的位置: 财务管理 - 发票记录</div>
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
                <th>雇主 </th>
                <th>任务 </th>
                <th>设计费用 </th>
                <th>总金额 </th>
                <th> 发票抬头 </th>
                <th> 状态 </th>
                <th> 发票编号 </th>
                <th> 发票金额 </th>
                <th>  经办人 </th>
                <th width="100">操作 </th>
            </tr>
            </thead>
            <#list taskList as task >
            <tr>
                <td class="left">${task.id}</td>
                <td class="left">${task.username}</td>
                <td class="left">${task.requirement}</td>
                <td class="left">${task.designBudget}</td>
                <td class="left">${task.totalAmount}</td>
                <td class="left">${task.invTitle}</td>
                <td class="left">${task.invState}</td>
                <td class="left">${task.invoiceNo}</td>
                <td class="left">${task.invAmount}</td>
                <td class="left">${task.operator!}</td>
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
    <script>
        <#include "/manage/inc/pagerscript.ftl" >
    </script>
</@layout.foot>
