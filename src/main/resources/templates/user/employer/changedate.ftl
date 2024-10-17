<#--
  云联创威客系统
  Copyright 2015 云联创科技

  任务日期修改页
  author: Billy Zhang (billy_zh@126.com)
-->
<#import "/layout/dialog.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body class="bg8">

	<!-- 重新发标-弹出 -->
	<div id="rebidBox" class="fancyBox">
		<div class="head">
			<a href="javascript:void(0);" onclick="parent.closeDialog(0);" class="fancyClose"></a>
			<h3>重新发标</h3>
		</div>
		<div class="main">
			<table class="g-table1">
				<tr>
					<th style="width:5em;">任务ID：</th>
					<td>${task.id}</td>
				</tr>
				<tr>
					<th>需求类目：</th>
					<td>${cateName!}</td>
				</tr>
			</table>
			<table class="g-table1">
				<thead>
					<tr>
						<th>任务名称</th>
						<th>交易款</th>
						<th>交易状态</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${task.requirement!}</td>
						<td>￥${task.totalAmount!}</td>
						<td><span class="fc_orange">${task.taskState!}</span></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="foot">
            <form id="form1">
                <input type="hidden" name="taskid" value="${task.id}" />
			    <table class="g-table2">
				    <tr>
					    <th style="width:5em;">延长时间：</th>
					    <td><input id="days" name="days" type="text" placeholder="输入天数" class="inp">天</td>
				    </tr>
			    </table>
			    <div class="h20"></div>
			    <div class="tc">
				    <input type="button" id="submit" value="确认调整" class="g-btn7 g-btn-blue ani" >
			    </div>
            </form>
		</div>
	</div>

</@layout.body>
<@layout.foot>

     <script type="text/javascript">
         $(document).ready(function ($) {
             var once = 0;

             $("#submit").click(function () {

                 var obj = $('#days');
                 if (isNaN(obj.val())) {
                     alert('无效的天数，请修改。');
                     obj.focus();
                     return false;
                 }

                 if (once == 0) {
                     once = 1;

                     var options = {
                         url: 'changedate',
                         type: 'post',
                         dataType: 'json',
                         data: $("#form1").serialize(),
                         success: function (resp) {
                             once = 0;

                             if (resp.data == 1) {
                                 parent.closeDialog(1);
                             } else {
                                 alert("提交失败，请稍后重试。");
                             }
                         }
                     };
                     $.ajax(options);
                 }

                 return false;
             });
         });
    </script>

</@layout.foot>