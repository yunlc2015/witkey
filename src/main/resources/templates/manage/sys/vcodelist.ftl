<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
	<div class="place">您所在的位置: 系统管理 - 校验码列表</div>
	<!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
		<form class="layui-form" method="GET">
             <div class="layui-inline">
                <label class="layui-form-label">接收手机/Email</label>
                <div class="layui-input-inline">
                    <input type="text" name="sendto" value="${cond.sendTo!}" class="layui-input">
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
				<th>接收手机/Email</th>
				<th>校验类型</th>
				<th>校验码</th>
				<th>类别</th>
				<th>发送时间</th>
				<th>状态</th>
				<th>ClientIP</th>
                <th></th>
            </tr>
            </thead>
            <#list vcodeList as vcode >
            <tr>
                <td class="left">${vcode.id}</td>
                <td class="left">${vcode.sendTo!}</td>
                <td class="left">${vcode.type!}</td>
                <td class="left">${vcode.data!}</td>
                <td class="left">${vcode.kind!}</td>
                <td class="left">${vcode.sendTime?datetime}</td>
                <td class="left">${vcode.state}</td>
                <td class="left">${vcode.clientIp}</td>
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