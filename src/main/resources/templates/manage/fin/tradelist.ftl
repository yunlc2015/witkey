<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
	<div class="place">您所在的位置: 财务管理 - 交易记录</div>
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
                <th>ID</th>
                <th>交易号</th>
                <th>用户</th>
                <th>任务</th>
                <th>金额</th>
                <th>付款方式</th>
                <th>支付状态</th>
                <th>第三方订单</th>
                <th width="150">时间</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <#list tradeList as trade >
            <tr>
                <td class="left">${trade.id}</td>
                <td class="left">${trade.tradeNo}</td>
                <td class="left">${trade.username}</td>
                <td class="left">${trade.subject}</td>
                <td class="left">${trade.amount}</td>
                <td class="left">${trade.payType}</td>
                <td class="left">${trade.payState}</td>
                <td class="left">${trade.thirdNo!}</td>
                <td class="left">${trade.addTime?datetime}</td>
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
