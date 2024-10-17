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
            您所在的位置: 内容管理 > 视频信息
        </div>
      <!-- 内容主体区域 -->
      <div class="manage" style="padding: 15px;">
        
        <#if playAuth?? >
          <link rel="stylesheet" href="//g.alicdn.com/de/prismplayer/2.9.3/skins/default/aliplayer-min.css" />
          <script type="text/javascript" src="//g.alicdn.com/de/prismplayer/2.9.3/aliplayer-min.js"></script>
        
        <div class="prism-player" id="J_prismPlayer"></div> 
        <script> 
        var player = new Aliplayer({ 
            id: "J_prismPlayer",
            useH5Prism: true,
            encryptType: 1,
            autoplay: false,
            width:"100%", 
            height: "500px",
            format:"m3u8",
            vid:"${video.vodId}", 
            playauth:'${playAuth}', 
            cover:'' 
        }); 
        </script>
        </#if>
        
		<br/><br/>
		<br/><br/>
		<br/><br/>
		
    </div>
    <!-- /.row -->
  </div>
  
  </body>

</html>