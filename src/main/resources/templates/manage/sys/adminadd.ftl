<#import "/manage/layout/dialog.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
    <!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="post">
       		
            <div class="layui-form-item ">
                 <label class="layui-form-label"><span style="color: red;">*</span>名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" required lay-verify="required" placeholder=""
                           autocomplete="off" class="layui-input" value="">
                </div>
            </div>
            
            <div class="layui-form-item ">
                 <label class="layui-form-label"><span style="color: red;">*</span>密码</label>
                <div class="layui-input-block">
                    <input type="text" name="password" required lay-verify="required" placeholder=""
                           autocomplete="off" class="layui-input" value="">
                </div>
            </div>

            <div class="layui-form-item ">
                 <label class="layui-form-label"><span style="color: red;">*</span>密码确认</label>
                <div class="layui-input-block">
                    <input type="text" name="password2" required lay-verify="required" placeholder=""
                           autocomplete="off" class="layui-input" value="">
                </div>
            </div>

            <div class="layui-form-item ">
                 <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    <input type="text" name="description" placeholder=""
                           autocomplete="off" class="layui-input" value="">
                </div>
            </div>
            
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
	            ajaxSubmit('adminadd', data);
	            return false;
	        });
	    });
        
    </script>    
</@layout.foot>