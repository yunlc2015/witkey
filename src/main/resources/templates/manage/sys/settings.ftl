<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
	<link rel="stylesheet" href="/lib/webuploader/webuploader.css" media="all">
</@layout.head>
<@layout.body>

	<div class="place">您所在的位置: 系统管理 - 一般设置</div>
	<!-- 内容主体区域 -->
	<div class="manage" style="padding: 15px;">

		<form class="layui-form" method="post">

			<#list list as item>
			<div class="layui-form-item ">
				<label class="layui-form-label">${item.description}</label>
				<div class="layui-input-block">
					<#if item.type=='textarea'>
					<textarea name="${item.key}" class="layui-textarea"
								width="100%" rows="5">${item.value!}</textarea>
					<#elseif item.type=='image'>
					<input type="hidden" id="${item.key}" name="${item.key}" value="${item.value!}" />
					<div style="display:inline;padding-top:10px;" id="picker_${item.key}">上传</div><span id="progress_${item.key}"></span>
					<br /><img id="${item.key}2" src="${item.value!}" width="80px" />
					<#else>
					<input type="text" name="${item.key}" placeholder=""
							autocomplete="off" class="layui-input" value="${item.value!}">
					</#if>
				</div>
			</div>
			</#list>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button lay-filter="save" type="submit" lay-submit class="layui-btn layui-btn-normal ">保存</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>

		</form>

	</div>

</@layout.body>
<@layout.foot>

	<script type="text/javascript">
		layui.use('form', function () {
			var form = layui.form;

			form.on('submit(save)', function (data) {
				var url = 'settings';
				$.ajax({
					type: "post",
					url: url,
					data: data.field,
					dataType: 'json',
					success: function (resp) {
						if (resp.errCode == 0) {
							layer.msg('保存成功。', function() {window.location.href='settings';} );
						} else {
							layer.msg(resp.errMsg);
						}
					},
					error: function () {
						layer.msg("出现错误，请稍后重试");
					}
				});
				return false;
			});
		});
	</script>
	
	<script src="/lib/webuploader/webuploader.js"></script>
	<script type="text/javascript">
		$(function() {
			<#list imglist as key>
			// Web Uploader实例
			webUploader_${key} = WebUploader.create({
				// 选完文件后，是否自动上传。
				auto: true,
				// swf文件路径
				swf: '/lib/webuploader/Uploader.swf',
				// 文件接收服务端。
				server: '/manage/file/upload?cate=image',
				// 选择文件的按钮。可选。
				// 内部根据当前运行是创建，可能是input元素，也可能是flash.
				pick: '#picker_${key}',
				//只允许选择图片
				accept: {
					title: '图片',
					extensions: 'jpg,jpeg,png,gif'
				}
			});
			// 文件上传过程中创建进度条实时显示。
			webUploader_${key}.on('uploadProgress', function(file, percentage) {
				$('#progress_${key}').text(Math.round(percentage * 1000) / 10 + '%');
			});
			// 文件上传失败，显示上传出错。
			webUploader_${key}.on('uploadError', function(file) {
				$('#progress_${key}').text('上传失败');
			});
			// 文件上传成功，给item添加成功class, 用样式标记上传成功。
			webUploader_${key}.on('uploadSuccess', function(file, response) {
				$('#${key}').val(response.fileUrl);
				$('#${key}2').attr('src', response.fileUrl);
			});
		</#list>
		});
	</script>

</@layout.foot>