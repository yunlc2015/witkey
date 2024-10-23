<#import "/manage/layout/dialog.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
    <!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
    
        <div>任务：${task.requirement}<br/>
        金额：${task.designBudget}</div>

        <table id="table1" class="layui-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>设计师</th>
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
                <td class="left">${project.acceptTime?datetime}</td>
                <td class="left">${project.state}</td>
                <td class="left"><#if project.submitTime??>${project.submitTime?datetime}</#if></td>
                <td>
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
    </div>
</@layout.body>
<@layout.foot>
</@layout.foot>