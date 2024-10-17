<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>后台管理</title>
<script type="text/javascript" src="${contextPath}/lib/js/jquery.js"></script>
<script type="text/javascript" src="${contextPath}/lib/layui/layui.js"></script>
<link rel="stylesheet" href="${contextPath}/lib/layui/css/layui.css">
<link rel="stylesheet" href="${contextPath}/lib/css/base.css">
</head>

<body class="layui-layout-body">
	<div class="layui-layout">
		
		<div style="margin:30px;">
			<h2>出错了，请稍后重试。</h2>
			
			${exception!}
		</div>

	</div>

</div>


</body>

</html>