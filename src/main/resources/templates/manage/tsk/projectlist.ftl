<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
	<div class="place">您所在的位置: 任务管理 - 提案（项目）列表</div>
	<!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="GET">
             <div class="layui-inline">
                <label class="layui-form-label">设计师名称</label>
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
                <th>设计师</th>
                <th>任务</th>
                <th>金额</th>
                <th>抢标时间</th>
                <th>状态</th>
                <th>提交时间</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <#list projectList as project >
            <tr>
                <td class="left">${project.id}</td>
                <td class="left">${project.designerName}</td>
                <td class="left">${project.task.requirement}</td>
                <td class="left">${project.task.designBudget}</td>
                <td class="left">${project.acceptTime?datetime}</td>
                <td class="left">${project.state}</td>
                <td class="left"><#if project.submitTime??>${project.submitTime?datetime}</#if></td>
                <td>
                    <a href="#" class="layui-btn layui-btn-normal layui-btn-sm">编辑</a>
                </td>
            </tr>
            <#if project.state.constant==1 >
            <tr>
                <td colspan="8" class="left">
                    设计描述：${project.proposalDescribe!}<br/>
                    <#list project.fileList as file >
                    <a target="_blank" href="/task/${file.url}"><img src="/task/${file.url}" style="height:80px;" /></a>&nbsp;
                    </#list>
                </td>
            </tr>
            </#if>
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
