<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>授权列表</title>
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
            您所在的位置: 内容管理 > 授权列表
        </div>
      <!-- 内容主体区域 -->
      <div class="manage" style="padding: 15px;">
        <form class="layui-form" action="videolist" method="get">

	     <div class="layui-inline">
        	<label class="layui-form-label">名称</label>
        	<div class="layui-input-inline">
            	<input type="text" name="vodid" value="${vodId!}"  class="layui-input">
            </div>	
         </div>

     	<div class="layui-inline">
            <button class="layui-btn  layui-btn-normal">查询</button>
         </div>
        </form>
        
        <#if authlist?? >
        <table id="demo1" class="layui-table">
            <thead>
              <tr>
                <th>VodId</th>
                <th>请求IP</th>
                  <th>请求时间</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <#list authlist as auth>
              <tr>
                <td>${auth.vodId}</td>
                <td>${auth.clientIp}</td>
                <td>${auth.addTime?datetime}</td>
                  <td></td>
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
                       location.href = "${contextPath}/manage/authlist?vodid=${vodId}&pageNo=" + obj.curr;
                    }
                }
            });
        })
    </script>    
</body>

</html>