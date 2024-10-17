<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>Saas产品</title>
  <script type="text/javascript" src="/manage/lib/js/jquery.js"></script>
  <script type="text/javascript" src="/manage/lib/js/jquery.form.js"></script>
  <script type="text/javascript" src="/lib/layui/layui.js"></script>
  <link rel="stylesheet" href="/lib/layui/css/layui.css">
  <link rel="stylesheet" href="/manage/lib/css/base.css">
  <style>
  </style>
</head>
<body>
   <form id="form1" action="/manage/fin/infoinput" method="post" style="padding: 20px 40px">
   	<input type="hidden" name="invid" value="${(invoice.id)?c}" />
    
    <div class="layui-form-item ">
         <label class="layui-form-label"><span style="color: red;">*</span>公司名称</label>
        <div class="layui-input-block">
            <input type="text" name="companyName" disabled="disabled" placeholder="公司名称"
                   autocomplete="off" class="layui-input" value="${invoice.companyName}">
        </div>
    </div>
    
    <div class="layui-form-item ">
         <label class="layui-form-label"><span style="color: red;">*</span>公司税号</label>
        <div class="layui-input-block">
            <input type="text" name="taxNo" disabled="disabled" placeholder="公司税号"
                   autocomplete="off" class="layui-input" value="${invoice.taxNo}">
        </div>
    </div>
    
    <div class="layui-form-item ">
         <label class="layui-form-label"><span style="color: red;">*</span>发票号</label>
        <div class="layui-input-block">
            <input type="text" name="ticketNo" required lay-verify="required" placeholder="发票号"
                   autocomplete="off" class="layui-input" value="">
        </div>
    </div>
    
    <div class="layui-form-item ">
         <label class="layui-form-label"><span style="color: red;">*</span>发票内容</label>
        <div class="layui-input-block">
            <input type="text" name="content" required lay-verify="required" placeholder="发票内容"
                   autocomplete="off" class="layui-input" value="">
        </div>
    </div>
    
	<div class="layui-form-item">
        <label class="layui-form-label">快递名称</label>
        <div class="layui-input-block">
            <input type="text" name="expressName" required lay-verify="required" placeholder="快递名称"
                   autocomplete="off" class="layui-input" value="">
        </div>
    </div>
    
    <div class="layui-form-item">
        <label class="layui-form-label">快递单号</label>
        <div class="layui-input-block">
            <input type="text" name="expressNo" required lay-verify="required" placeholder="快递单号"
                   autocomplete="off" class="layui-input" value="">
        </div>
    </div>
    
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button id="btn-save" class="layui-btn layui-btn-normal ">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
 	
	<script type="text/javascript">
		$(function () {
	        $('#btn-save').on('click', function () {
	             var ticketNo = $("#ticketNo").val();
                 if (ticketNo == '') {
	                	 alert('请输入发票号。');
	                	 return false;
                 }

 				var content = $("#content").val();
                 if (content == '') {
	                	 alert('请输入发票内容。');
	                	 return false;
                 }
                 
                 var expressName = $("#expressName").val();
                 if (expressName == '') {
	                	 alert('请输入快递名称。');
	                	 return false;
                 }
	           
	           var expressNo = $("#expressNo").val();
                 if (expressNo == '') {
	                	 alert('请输入快递单号。');
	                	 return false;
                 }
                 
	            $('#form1').ajaxSubmit({
	                dataType: 'json',
	                success: function (json) { 
		                layui.use('layer', function () {
				            var layer = layui.layer;
		                    if (json.errCode == 0) {
	                             layer.msg('操作已成功完成.', function(){parent.location.reload();} );
		                    } else {
		                        layer.msg(json.errMsg);
		                    }
		                });
	                }
	            });
	
	            return false;
	        });
	    });
	</script>
	
</body>

</html>
