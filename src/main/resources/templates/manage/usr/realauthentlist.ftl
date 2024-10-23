<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
	<div class="place">您所在的位置: 用户管理 - 实名认证列表</div>
	<!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="GET">
             <div class="layui-inline">
                <label class="layui-form-label">名称</label>
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
                <th>姓名</th>
                <th>身份证</th>
                <th>认证类别</th>
                <th width="150"> 提交时间</th>
                <th>审核状态</th>
                <th>审核时间</th>
                <th>审核人</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <#list authentList as authent >
            <tr>
                <td class="left">${authent.id}</td>
                <td class="left">${authent.realname}</td>
                <td class="left">${authent.idcard}</td>
                <td class="left">${authent.category}</td>
                <td class="left">${authent.submitTime?datetime}</td>
                <td class="left"><#if authent.authState==1 >待审核
                    <#elseif authent.authState==2 ><span style="color:green">审核通过</span>
                    <#elseif authent.authState==3 ><span style="color:red">审核不通过</span>
                    </#if></td>
                <td class="left"><#if authent.authTime??>${authent.authTime?datetime}</#if></td>
                <td class="left">${authent.authOperator!}</td>
                <td>
                    <#if authent.authState == 1 >
                    <a href="javascript:openDialog('realauthentaudit?id=${authent.id?c}', '认证审核', '900px', '720px');" class="layui-btn layui-btn-normal layui-btn-sm">审核</a>
                    <#else>
                    <a href="javascript:openDialog('realauthentdetail?id=${authent.id?c}', '认证详情', '900px', '720px');" class="layui-btn layui-btn-normal layui-btn-sm">详情</a>
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
