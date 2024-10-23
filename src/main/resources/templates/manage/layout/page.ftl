
<#macro head>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台管理</title>
    <link rel="stylesheet" href="/lib/layui/css/layui.css">
    <link rel="stylesheet" href="/lib/css/manage.css">
    <script type="text/javascript" src="/lib/js/jquery.js"></script>
    <script type="text/javascript" src="/lib/layui/layui.js"></script>
    <#nested >
</head>
</#macro>
<#macro body>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
            <#include '/manage/inc/top.ftl'>
        </div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<#include '/manage/inc/left.ftl'>
			</div>
		</div>

		<div class="layui-body">
			<#nested >
		</div>
	</div>
</#macro>
<#macro foot>
	<script>
		//JavaScript代码区域
		var layer;

		layui.use(['element','layer'], function () {
			var element = layui.element;
			layer = layui.layer;
		});

		function openDialog(url, title, width='640px', height='480px') {
			layer.open({
				type: 2,
				title: title,
				area: [width, height],
				shade: 0.8,
				shadeClose: false,
				content: url
			});
		}

		function closeDialog(reload=0) {
			layer.closeAll();
			if (reload==1) {
				location.reload();
			}
		}

		function deleteConfirm(url, title) {
 			layer.confirm(title,  { 
					btnAlign: 'c',
					btn: [ '确定', '取消' ] }, 
					function() {
						$.ajax({
							type: 'POST',
							url: url,
							dataType: 'json',
							success: function(resp) {
								if (resp.errCode == 0) {
									window.location.reload();
								} else {
									layer.alert(resp.errMsg);
								}
							} 
						});
					}, 
					function() {
					}
          		);
		}

	</script>
    <#nested >
</body>
</html>
</#macro>