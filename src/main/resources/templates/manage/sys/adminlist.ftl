<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
	<div class="place">您所在的位置: 财务管理 - 发票记录</div>
	<!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="GET">
            <div class="layui-inline">
                <a href="javascript:openDialog('adminadd', '添加管理员', '700px', '800px');" class="layui-btn layui-btn-normal">添加管理员</a>
            </div>
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
                <th> ID</th>
                <th>名称</th>
                <th>描述</th>
                <th>状态</th>
                <th width="150">最后登录</th>
                <th width="110">操作</th>
            </tr>
            </thead>
            <#list adminList as admin >
            <tr>
                <td class="left">${admin.id}</td>
                <td class="left">${admin.name}</td>
                <td class="left">${admin.description!}</td>
                <td class="left">${admin.state}</td>
                <td class="left"><#if admin.lastLogin?? >${admin.lastLogin!}</#if></td>
                <td><#if admin.name != 'admin' >
                    <a href="javascript:openDialog('adminedit?id=${(admin.id)?c}', '编辑管理员', '700px', '800px');" class="layui-btn layui-btn-normal layui-btn-sm">编辑</a>
                    <a href="javascript:deleteConfirm('admindelete?id=${(admin.id)?c}', '确定要删除此管理员吗？');" class="layui-btn layui-btn-danger layui-btn-sm">删除</a>
                    <#else>
                    <a href="javascript:void(0);" class="layui-btn layui-btn-disabled layui-btn-sm">编辑</a>
                    <a href="javascript:void(0);" class="layui-btn layui-btn-disabled layui-btn-sm">删除</a>
                    </#if>
                </td>
            </tr>
            </#list>
        </table>
    </div>
</@layout.body>
<@layout.foot>
    <script>
    </script>
</@layout.foot>
