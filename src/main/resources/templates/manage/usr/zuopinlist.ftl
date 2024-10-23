<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
	<div class="place">您所在的位置: 用户管理 - 作品列表</div>
	<!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="GET">
            <div class="layui-inline">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="keyword" value="${cond.keyword!}" class="layui-input">
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
                <th>封面</th>
                <th>设计师</th>
                <th>标题</th>
                <th width="60"> 排序号 </th>
                <th width="110">操作</th>
            </tr>
            </thead>
            <#list zuopinList as zuopin >
            <tr>
                <td class="left">${zuopin.id?c}</td>
                <td class="left"><img src="/user/${zuopin.cover}" style="width:200px" /></td>
                <td class="left">${zuopin.username}</td>
                <td class="left">${zuopin.title}</td>
                <td class="left">${zuopin.topNo}</td>
                <td>
                    <a href="javascript:setZuoPinLocation(${zuopin.id}, 1);" class="layui-btn layui-btn-normal layui-btn-sm">置顶</a>
                    <a href="javascript:setZuoPinLocation(${zuopin.id}, -1);" class="layui-btn layui-btn-normal layui-btn-sm">置底</a>
                    <a href="javascript:setZuoPinLocation(${zuopin.id}, 0);" class="layui-btn layui-btn-normal layui-btn-sm">取消置顶/底</a>
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
        function setZuoPinLocation(uid, location) {
            $.ajax({
                url: "setzuopinlocation",
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
