<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
	<div class="place">您所在的位置: 财务管理 - 提现记录</div>
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
                <th>金额 </th>
                <th>银行 </th>
                <th>帐户名称 </th>
                <th>账户号</th>
                <th>状态 </th>
                <th>申请时间 </th>
                <th>办理时间 </th>
                <th>经办人 </th>
                <th width="100">操作 </th>
            </tr>
            </thead>
            <#list withdrawalList as wd >
            <tr>
                <td class="left">${wd.id}</td>
                <td class="left">${wd.username}</td>
                <td class="left">${wd.amount}</td>
                <td class="left">${wd.bankName}</td>
                <td class="left">${wd.bankAccName}</td>
                <td class="left">${wd.bankAccNo}</td>
                <td class="left">${wd.state}</td>
                <td class="left">${wd.requestTime?datetime}</td>
                <td class="left"><#if wd.settleTime?? >${wd.settleTime?datetime}</#if></td>
                <td class="left">${wd.settleOperator!}</td>
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
