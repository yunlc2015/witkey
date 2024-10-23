<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
	<div class="place">您所在的位置: 用户管理 - 设计师列表</div>
	<!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="GET">
            <div class="layui-inline">
                <label class="layui-form-label">手机号</label>
                <div class="layui-input-inline">
                    <input type="text" name="mobile" value="${cond.mobile!}" class="layui-input">
                </div>
             </div>
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
                <th>手机号</th>
                <th>昵称</th>
                <th>地点</th>
                <th>账户余额</th>
                <th>排序号</th>
                <th width="120">注册时间</th>
                <th width="120">最后登录</th>
                <th width="110">操作</th>
            </tr>
            </thead>
            <#list userList as user >
            <tr>
                <td class="left">${user.id}</td>
                <td class="left">${user.mobile}</td>
                <td class="left">${user.nickname}</td>
                <td class="left">${user.province!} ${user.city!}</td>
                <td class="right">${user.balance}</td>
                <td class="right">${user.topNo}</td>
                <td class="left">${user.regTime?datetime}</td>
                <td class="left"><#if user.lastLogin?? >${user.lastLogin?datetime}</#if></td>
                <td>
                    <a href="javascript:setDesignerLocation(${user.id}, 1);" class="layui-btn layui-btn-normal layui-btn-sm">置顶</a>
                    <a href="javascript:setDesignerLocation(${user.id}, -1);" class="layui-btn layui-btn-normal layui-btn-sm">置底</a>
                    <a href="javascript:setDesignerLocation(${user.id}, 0);" class="layui-btn layui-btn-normal layui-btn-sm">取消置顶/底</a>
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
    <script>
        function setDesignerLocation(uid, location) {
            $.ajax({
                url: "setdesignerlocation",
                type: "POST",
                data: { id: uid, location: location },
                success: function (json) {
                    if (json.errCode == 0) {
                        layer.msg('操作成功。', {time:1000}, function() { window.location.reload(); } );
                    } else {
                        alert('操作失败。');
                    }
                }
            });
        }
    </script>
</@layout.foot>
