<#import "/manage/layout/dialog.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
    <!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="post">
       		<input type="hidden" name="id" value="${task.id}" />
       		<input type="hidden" name="state" id="state" value="0" />

            <div class="layui-form-item ">
                 <label class="layui-form-label">标题</label>
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
                 <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea id="remark" name="remark" class="layui-input" style="height:80px"></textarea>
                </div>
            </div>
            
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button lay-filter="approve" type="submit" lay-submit class="layui-btn layui-btn-normal ">通过</button>
                    <button lay-filter="reject" type="submit" lay-submit class="layui-btn layui-btn-normal ">不通过</button>
                </div>
            </div>
        </form>
    </div>

</@layout.body>
<@layout.foot>
	<script type="text/javascript">
		
        layui.use('form', function () {
	        var form = layui.form;
	
	        form.on('submit(approve)', function (data) {
                data.field.state=1;
	            ajaxSubmit('taskaudit', data);
	            return false;
	        });

            form.on('submit(reject)', function (data) {
	            ajaxSubmit('taskaudit', data);
	            return false;
	        });
	    });
        
    </script>    
</@layout.foot>