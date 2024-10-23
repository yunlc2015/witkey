<#import "/manage/layout/dialog.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
    <!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="post">
       		<input type="hidden" name="id" value="${task.id}" />

            <div class="layui-form-item ">
                 <label class="layui-form-label">任务</label>
                <div class="layui-input-block">
                    <input type="text" name="requirement" disabled="disabled"
                           autocomplete="off" class="layui-input" value="${task.requirement!}">
                </div>
            </div>
            
            <div class="layui-form-item ">
                <label class="layui-form-label">金额</label>
                <div class="layui-input-block">
                    <input type="text" name="totalamount" disabled="disabled"
                           autocomplete="off" class="layui-input" value="${task.totalAmount!}">
                </div>
            </div>
            
            <div class="layui-form-item ">
                <label class="layui-form-label"><span style="color: red;">*</span>银行与交易流水号</label>
                <div class="layui-input-block">
                    <input type="text" name="thirdno" required lay-verify="required"
                           autocomplete="off" class="layui-input" value="">
                </div>
            </div>
            
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button lay-filter="save" type="submit" lay-submit class="layui-btn layui-btn-danger ">确认</button>
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
	            ajaxSubmit('taskpayment', data);
	            return false;
	        });
	    });
        
    </script>    
</@layout.foot>