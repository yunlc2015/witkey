<#import "/manage/layout/dialog.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
    <!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="post">
       		<input type="hidden" name="id" value="${task.id}" />

            <div class="layui-form-item ">
                 <label class="layui-form-label">标题</label>
                <div class="layui-input-block">
                    <input type="text" name="requirement" readonly="readonly"
                           autocomplete="off" class="layui-input" value="${task.requirement!}">
                </div>
            </div>
            
            <div class="layui-form-item ">
                 <label class="layui-form-label">总金额</label>
                <div class="layui-input-block">
                    <input type="text" name="totalamount" disabled="disabled"
                           autocomplete="off" class="layui-input" value="${task.totalAmount!}">
                </div>
            </div>
            
            <div class="layui-form-item ">
                 <label class="layui-form-label">设计费</label>
                <div class="layui-input-block">
                    <input type="text" name="designamount" disabled="disabled"
                           autocomplete="off" class="layui-input" value="${task.designAmount!}">
                </div>
            </div>

            <div class="layui-form-item ">
                 <label class="layui-form-label">退费金额</label>
                <div class="layui-input-block">
                    <input type="text" name="refundamount" readonly="readonly"
                           autocomplete="off" class="layui-input" value="${refundAmount}">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button lay-filter="save" type="submit" lay-submit class="layui-btn layui-btn-danger ">提交</button>
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
	            ajaxSubmit('taskrefund', data);
	            return false;
	        });
	    });
        
    </script>    
</@layout.foot>