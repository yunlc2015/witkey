<#import "/manage/layout/dialog.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
    <!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="post">
       		<input type="hidden" name="id" value="${authent.id}" />
       		<input type="hidden" name="state" id="state" value="0" />

            <div class="layui-form-item ">
                 <label class="layui-form-label">链接</label>
                <div class="layui-input-block">
                    <a href="${authent.link}" target="_blank">${authent.link!}</a>
                </div>
            </div>
            
            <div class="layui-form-item ">
                 <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    ${authent.describe!}
                </div>
            </div>
            
            <div class="layui-form-item ">
                 <label class="layui-form-label">认定级别</label>
                <div class="layui-input-block">
                    <select name="grade" class="layui-input">
                        <option value="1">认证设计师</option>
                        <option value="2">官方设计师</option>
                        <option value="3">明星设计师</option>
                    </select>
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
	            ajaxSubmit('abilityauthentaudit', data);
	            return false;
	        });

            form.on('submit(reject)', function (data) {
	            ajaxSubmit('abilityauthentaudit', data);
	            return false;
	        });
	    });
        
    </script>    
</@layout.foot>