<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
	<div class="place">您所在的位置: 内容管理 - 推广管理</div>
	<!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="GET">
            <div class="layui-inline">
                <a href="javascript:openDialog('bdinfoadd', '添加推广', '800px', '600px');" class="layui-btn layui-btn-normal">添加推广</a>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-inline">
                    <input type="text" name="keyword" value="${keyword!}" class="layui-input">
                </div>
             </div>
            <div class="layui-inline">
                <button class="layui-btn  layui-btn-normal">查询</button>
            </div>
        </form>
        <table id="table1" class="layui-table">
        <thead>
            <tr>
                <th width="200"> 图片 </th>
                <th> 标题  </th>
                <th> 位置  </th>
                <th> 价格 </th>
                <th>  URL  </th>
                <th width="80"> 排序  </th>
                <th width="130">  </th>
            </tr>
            </thead>
            <#list bdinfoList as bdinfo >
            <tr>
                <td class="left"><img src="${bdinfo.imageUrl}" style="width:200px"/></td>
                <td class="left">${(bdinfo.title)!}</td>
                <td class="left">${(bdinfo.name)!}</td>
                <td class="left">${bdinfo.price}</td>
                <td class="left">${bdinfo.targetUrl}</td>
                <td class="left">${bdinfo.sortNo}</td>
                <td>
                    <a href="javascript:openDialog('bdinfoedit?id=${(bdinfo.id)?c}', '编辑推广', '800px', '600px');" class="layui-btn layui-btn-normal layui-btn-sm">编辑</a>
                    <a href="javascript:deleteConfirm('bdinfodelete?id=${(bdinfo.id)?c}', '确定要删除此推广记录吗？');" class="layui-btn layui-btn-danger layui-btn-sm">删除</a>
                </td>
            </tr>
            </#list>
        </table>
        <div id="pager"></div>
    </div>
</@layout.body>
<@layout.foot>
    <script type="text/javascript">
		<#include "/manage/inc/pagerscript.ftl" >
    </script>
</@layout.foot>
