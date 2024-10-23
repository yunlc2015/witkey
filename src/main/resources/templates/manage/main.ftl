<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
<style>
.layui-nav-tree .layui-nav-child .active, .layui-nav-tree .layui-nav-child dd:hover
	{
	background: #0695ff;
}
</style>
</@layout.head>
<@layout.body>
	<div class="place">您所在的位置: 管理首页</div>
	<!-- 内容主体区域 -->
	<div class="manage" style="padding: 15px;">
		<div class="layui-row" style="margin-bottom: 20px;">
			<div class="layui-col-sm3" style="padding-left:10px;padding-right:10px">
				<div class="layui-card" style="border: 1px solid #ddd">
					<div class="layui-card-header" style="background-color:#eee">任务数</div>
					<div class="layui-card-body" style="font-size:30px;padding:20px;">${taskTotal}</div>
				</div>
			</div>
			<div class="layui-col-sm3" style="padding-left:10px;padding-right:10px">
				<div class="layui-card" style="border: 1px solid #ddd">
					<div class="layui-card-header" style="background-color:#eee">作品数</div>
					<div class="layui-card-body" style="font-size:30px;padding:20px;">${zuopinTotal}</div>
				</div>
			</div>
			<div class="layui-col-sm3" style="padding-left:10px;padding-right:10px">
				<div class="layui-card" style="border: 1px solid #ddd">
					<div class="layui-card-header" style="background-color:#eee">设计师数</div>
					<div class="layui-card-body" style="font-size:30px;padding:20px;">${designerTotal}</div>
				</div>
			</div>
			<div class="layui-col-sm3" style="padding-left:10px;padding-right:10px">
				<div class="layui-card" style="border: 1px solid #ddd">
					<div class="layui-card-header" style="background-color:#eee">雇主数</div>
					<div class="layui-card-body" style="font-size:30px;padding:20px;">${employerTotal}</div>
				</div>
			</div>
		</div>

		<div>
			<a class="layui-btn layui-btn-normal" href="accesstotal">按天</a>
			<a class="layui-btn layui-btn-normal" href="accesstotal?time=week">按周</a>
			<a class="layui-btn layui-btn-normal" href="accesstotal?time=month">按月</a>
		</div>
		<div id="viewStatisChart" style="height:300px;width:100%;"></div>

		<table id="demo1" class="layui-table">
			<thead>
			<tr>
				<th>日期</th>
				<th>UV</th>
				<th>PV</th>
			</tr>
			</thead>
			<#list statislist as map >
				<tr>
					<td class="left">${map["name"]}</td>
					<td class="left">${map["uv"]}</td>
					<td class="left">${map["pv"]}</td>
				</tr>
			</#list>
		</table>
	</div>
</@layout.body>
<@layout.foot>
	<script type="text/javascript" src="/lib/echarts/echarts.min.js"></script>
	<script>
		var viewStatisChart = echarts.init(document.getElementById('viewStatisChart'));
		$.get("viewstatis?time=${time}", function (result) {
			viewStatisChart.hideLoading();

			if (result.errCode != 0) {
				console.log(result.errMsg);
				return;
			}

			viewStatisOption = {
				tooltip: {
					trigger: 'axis',
					axisPointer: {
						type: 'cross',
						crossStyle: {
							color: '#999'
						}
					}
				},
				legend: {
					data: ['UV', 'PV']
				},
				xAxis: [
					{
						type: 'category',
						data: result.data.names,
						axisPointer: {
							type: 'shadow'
						}
					}
				],
				yAxis: [
					{
						type: 'value',
						name: '数量',
						axisLabel: {
							formatter: '{value}'
						}
					}
				],
				series: [
					{
						name: 'UV',
						type: 'line',
						smooth: true,
						data: result.data.uvValues
					},
					{
						name: 'PV',
						type: 'line',
						smooth: true,
						data: result.data.pvValues
					}
				]
			};

			// 使用刚指定的配置项和数据显示图表。
			viewStatisChart.setOption(viewStatisOption);
		});

		 window.addEventListener("resize", function() {
            viewStatisChart.resize();
        });
	</script>
</@layout.foot>