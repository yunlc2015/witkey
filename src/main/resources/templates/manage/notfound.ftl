<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>NotFound</title>
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
            您所在的位置: 错误
        </div>
      <!-- 内容主体区域 -->
      <div class="manage" style="padding: 15px;">
	      
	      未找到数据，请检查参数。
      </div>
       
    </div>

  </div>
  
	<script type="text/javascript">
		
    </script>    
</body>

</html>