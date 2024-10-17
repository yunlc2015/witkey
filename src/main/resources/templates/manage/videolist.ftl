<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>视频列表</title>
  <script type="text/javascript" src="${contextPath}/lib/js/jquery.js"></script>
  <script type="text/javascript" src="${contextPath}/lib/layui/layui.js"></script>
  <link rel="stylesheet" href="${contextPath}/lib/layui/css/layui.css">
  <link rel="stylesheet" href="${contextPath}/lib/css/base.css">
  <style>
  </style>
</head>

<body class="layui-layout-body">
  <div class="layui-layout layui-layout-admin">
    <div class="layui-header">
      <#include '/manage/common/top.ftl'>
    </div>

    <div class="layui-side layui-bg-black">
      <div class="layui-side-scroll">
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<#include '/manage/common/left.ftl' />
      </div>
    </div>

    <div class="layui-body">
        <div class="place">
            您所在的位置: 内容管理 > 视频列表
        </div>
      <!-- 内容主体区域 -->
      <div class="manage" style="padding: 15px;">
        <form class="layui-form" action="videolist" method="get">
        <div class="layui-inline">
             <a href="${contextPath}/manage/videoadd" class="layui-btn  layui-btn-normal">添加视频</a>
	     </div>
	     <div class="layui-inline">
        	<label class="layui-form-label">名称</label>
        	<div class="layui-input-inline">
            	<input type="text" name="keyword" value="${keyword!}"  class="layui-input">
            </div>	
         </div>
	     <div class="layui-inline">
         	<label class="layui-form-label">类别</label>
        	<div class="layui-input-inline">
            <select name="category">
                <option value="0">-不限-</option>
            </select></div>
     	</div>
     	<div class="layui-inline">
            <button class="layui-btn  layui-btn-normal">查询</button>
         </div>
        </form>
        
        <#if videolist?? >
        <table id="demo1" class="layui-table">
            <thead>
              <tr>
                <th style="width:160px;"></th>
                <th>名称</th>
                <th style="width:100px;">类别</th>
                <th style="width:80px;">操作</th>
              </tr>
            </thead>
            <tbody>
              <#list videolist as video>
              <tr>
                <td><img src="${(video.coverUrl)!}" width="150px" /></td>
                <td>${video.name}<br/>
                    时长：${(video.duration2)!}</td>
                <td>${(video.categoryName)!}</td>
                <td><a href="videoedit?videoid=${video.id}">编辑</a><br/>
                    <a href="videoinfo?videoid=${video.id}">详情</a><br/>
                    <a href="javascript:deleteVideo(${video.id})">删除</a></td>
              </tr>
              </#list>
            </tbody>
        </table>
          <div id="pager"></div>
         </#if>
      </div>
       
    </div>
  </div>
  
	<script type="text/javascript">
		function deleteVideo(videoId) {
           layui.use('layer', function () {
          	  var layer = layui.layer;
              layer.confirm('确定删除此视频吗？', { btn: ['确定','取消'] },
                   function() {
                       $.post('videodelete', {videoid:videoId}, function(json) {
                           if (json.errCode==0) {
                               layer.msg('删除成功。', function() { location.reload(); } );
                           } else {
                               layer.msg(json.errMsg);
                           }
                       });
                   },
                   function() {
                   }
              );
           });
       }
	</script>
  
	<script type="text/javascript">
		
        layui.use(['laypage'], function () {
            var laypage = layui.laypage
                
            var count = [[${pager.totalCount}]];
            var pageNum = [[${pager.pageNo}]];

            var pageSize = [[${pager.pageSize}]];
            //调用分页

            laypage.render({
                elem: 'pager'
                , theme: '#1E9FFF'
                , count: count
                , curr: pageNum
                , limit: pageSize //每页显示的条数
                , layout: ['count', 'prev', 'page', 'next', 'skip']
                , jump: function (obj, first) {
                    if (!first) {
                       location.href = "${contextPath}/manage/videolist?pageNo=" + obj.curr;
                    }
                }
            });
        })
    </script>    
</body>

</html>