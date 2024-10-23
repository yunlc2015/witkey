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
                    <input type="text" name="keyword" value="${keyword!}" class="layui-input">
                </div>
             </div>
            <div class="layui-inline">
                <button class="layui-btn layui-btn-normal">查询</button>
            </div>
        </form>
        <table id="table1" class="layui-table">
        <thead>
            <tr>
                <th>日期</th>
                <th>描述</th>
                <th>操作名称</th>
                <th>时长(ms)</th>
                <th>Kind</th>
                <th>操作人</th>
                <th>操作IP</th>
                <th>操作</th>
            </tr>
            </thead>
            <#list logList as log >
            <tr>
                <td class="left">${log.logTime?datetime}</td>
                <td class="left">${log.actionDescr!}</td>
                <td class="left">${log.methodName!}</td>
                <td class="right">${log.duration!}</td>
                <td class="left">${log.kind!}</td>
                <td class="left">${log.operator!}</td>
                <td class="left">${log.logIp}</td>
                <td></td>
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
