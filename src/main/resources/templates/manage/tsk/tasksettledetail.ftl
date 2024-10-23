<#import "/manage/layout/dialog.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
    <!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="post">
       		<input type="hidden" name="id" value="${task.id}" />

            <div class="layui-form-item ">
                 <label class="layui-form-label">任务</label>
                <div class="layui-input-block">
                    <input type="text" name="requirement" disabled="disabled"
                           autocomplete="off" class="layui-input" value="${task.requirement!}">
                </div>
            </div>
            
            <div class="layui-form-item ">
                 <label class="layui-form-label">总金额</label>
                <div class="layui-input-block">
                    <input type="text" name="totalamount" disabled="disabled"
                           autocomplete="off" class="layui-input" value="${task.totalAmount!}">
                </div>
            </div>
            
            <div class="layui-form-item ">
                 <label class="layui-form-label">设计费</label>
                <div class="layui-input-block">
                    <input type="text" name="designamount" disabled="disabled"
                           autocomplete="off" class="layui-input" value="${task.designAmount!}">
                </div>
            </div>
            
            <table id="table1" class="layui-table">
                <thead>
                <tr>
                    <th>设计师</th>
                    <th>状态</th>
                    <th>金额</th>
                </tr>
                </thead>
                <#list projectList as project >
                <tr>
                    <td class="left">${project.designerName}</td>
                    <td class="left"><#if project.selected==1>雇主选中<#else>未选中</#if></td>
                    <td class="right">${project.settleAmount}</td>
                </tr>
                </#list>
            </table>
        </form>
    </div>

</@layout.body>
<@layout.foot>
</@layout.foot>