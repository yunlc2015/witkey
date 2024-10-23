<#import "/manage/layout/dialog.ftl" as layout>
<@layout.head>

</@layout.head>
<@layout.body>
    <!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="post">
       		<input type="hidden" name="id" value="${article.id}" />

            <div class="layui-form-item ">
                 <label class="layui-form-label">标题</label>
                <div class="layui-input-block">
                    <input type="text" name="titile" readonly="readonly"
                           autocomplete="off" class="layui-input" value="${article.title!}">
                </div>
            </div>
            
            <div class="layui-form-item ">
                 <label class="layui-form-label">路径</label>
                <div class="layui-input-block">
                    <input type="text" name="path" readonly="readonly"
                           autocomplete="off" class="layui-input" value="${article.path!}">
                </div>
            </div>
            
            <div class="layui-form-item ">
                 <label class="layui-form-label">内容</label>
                <div class="layui-input-block">
                    <textarea id="content" name="content" class="layui-input" style="height:500px;"></textarea>
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
    <script type="text/javascript" src="/lib/kindeditor/kindeditor-all.js"></script>
    <script type="text/javascript" src="/lib/kindeditor/lang/zh-CN.js"></script>
    <script type="text/javascript">
        //KindEditor脚本
	    var editor;
	    KindEditor.ready(function(K) {
	        editor = K.create('#content', {
	        	uploadJson: '/manage/kkuploadimage',
	        	uploadJson2: '/manage/kkuploadfile',
	        	allowFileManager: false,
	        	afterBlur: function () { this.sync(); },
	        });
	        
	        $.get("articlecontent?id=${(article.id)?c}", function(resp) {
                if (resp.errCode==0) {
                    editor.html(resp.data);
                    editor.sync();
                } else {
                    console.log(resp.errMsg);
                }
            });
	    });
    </script>

	<script type="text/javascript">
		
        layui.use('form', function () {
	        var form = layui.form;
	
	        form.on('submit(save)', function (data) {
	            ajaxSubmit('articleedit', data);
	            return false;
	        });
	    });
        
    </script>    
</@layout.foot>