<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>

	<div class="place">您所在的位置: 系统管理 - 支付设置</div>
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
				var url = 'paysettings';
				$.ajax({
					type: "post",
					url: url,
					data: data.field,
					dataType: 'json',
					success: function (resp) {
						if (resp.errCode == 0) {
							layer.msg('保存成功。', function() {location.href='paysettings';} );
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
	
</@layout.foot>