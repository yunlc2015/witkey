<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
	<div class="place">您所在的位置: 内容管理 - Banner管理</div>
	<!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="GET">
            <div class="layui-inline">
                <a href="javascript:openDialog('banneradd', '添加Banner', '800px', '600px');" class="layui-btn layui-btn-normal">添加Banner</a>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">位置</label>
                <div class="layui-input-inline">
                    <select name="location" id="location" class="layui-input">
                        <option value="">--全部--</option>
                        <option value="index">首页Banner</option>
                        <option value="index_ad">首页广告位</option>
                        <option value="task">任务页Banner</option>
                        <option value="niuren">牛人页Banner</option>
                        <option value="employer_index_ad">雇主首页广告</option>
                    </select>
                </div>
             </div>
            <div class="layui-inline">
                <button class="layui-btn  layui-btn-normal">查询</button>
            </div>
        </form>
        <table id="table1" class="layui-table">
        <thead>
            <tr>
                <th width="200">图片</th>
                <th>URL</th>
                <th>位置</th>
                <th width="80">排序</th>
                <th width="110"></th>
            </tr>
            </thead>
            <#list bannerList as banner >
            <tr>
                <td class="left"><img src="${banner.imageUrl}" style="width:200px" /></td>
                <td class="left">${(banner.targetUrl)!}</td>
                <td class="left">${banner.location}</td>
                <td class="left">${banner.sortNo}</td>
                <td>
                    <a href="javascript:openDialog('banneredit?id=${(banner.id)?c}', '编辑Banner', '800px', '600px');" class="layui-btn layui-btn-normal layui-btn-sm">编辑</a>
                    <a href="javascript:deleteConfirm('bannerdelete?id=${(banner.id)?c}', '确定要删除此Banner吗？');" class="layui-btn layui-btn-danger layui-btn-sm">删除</a>
                </td>
            </tr>
            </#list>
        </table>
    </div>
</@layout.body>
<@layout.foot>
    <script>
        $('#location').val('${location!}');

        layui.use('form', function() {
            var form = layui.form;
            form.render('select');
        })
    </script>
</@layout.foot>
