<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
	<div class="place">您所在的位置: 任务管理 - 市场标列表</div>
	<!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="GET">
            <div class="layui-inline">
                <label class="layui-form-label">任务名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="keyword" value="${cond.keyword!}" class="layui-input">
                </div>
             </div>
             <div class="layui-inline">
                <label class="layui-form-label">雇主名称</label>
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
                <th>客户</th>
                <th>任务</th>
                <th>任务金额</th>
                <th>任务模式</th>
                <th>总金额</th>
                <th>发布时间</th>
                <th>任务状态</th>
                <th>审核状态</th>
                <th>抢标人数</th>
                <th>选稿时间</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <#list taskList as task >
            <tr>
                <td class="left">${task.id}</td>
                <td class="left">${task.username}</td>
                <td class="left"><a href="/task/t${task.id}" target="_blank">${task.requirement}</a></td>
                <td class="left">${task.designBudget}</td>
                <td class="left">${task.taskMode}</td>
                <td class="left">${task.totalAmount}</td>
                <td class="left">${task.addTime?datetime}</td>
                <td class="left">${task.taskState}</td>
                <td class="left">${task.auditState}</td>
                <td class="left">${task.projectCount}</td>
                <td class="left"><#if task.chooseTime??>${task.chooseTime?datetime}</#if></td>
                <td>
                    <#if task.noPay >
                    <a href="javascript:openDialog('taskpayment?id=${task.id}', '转帐支付', '600px', '400px');" class="layui-btn layui-btn-normal layui-btn-sm">转帐支付</a>
                    </#if>
                    <#if task.needAudit >
                    <a href="javascript:openDialog('taskaudit?id=${task.id}', '任务审核', '600px', '400px');" class="layui-btn layui-btn-normal layui-btn-sm">审核</a>
                    </#if>
                    <#if task.projectCount &gt; 0 >
                    <a href="javascript:openDialog('taskprojectlist?id=${task.id}', '任务提案', '900px', '720px');" class="layui-btn layui-btn-normal layui-btn-sm">提案</a>
                    </#if>
                    <#if task.canSettle >
                    <a href="javascript:openDialog('tasksettle?id=${task.id}', '任务结算', '800px', '600px');" class="layui-btn layui-btn-normal layui-btn-sm">结算</a>
                    </#if>
                    <#if task.finished >
                    <a href="javascript:openDialog('tasksettledetail?id=${task.id}', '结算明细', '800px', '600px');" class="layui-btn layui-btn-normal layui-btn-sm">结算明细</a>
                    </#if>
                    <#if task.canRefund >
                    <a href="javascript:openDialog('taskrefund?id=${task.id}', '任务退款', '600px', '400px');" class="layui-btn layui-btn-danger layui-btn-sm">退款</a>
                    </#if>
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
