<#import "/manage/layout/dialog.ftl" as layout>
<@layout.head>
    <link href="/lib/webuploader/webuploader.css" rel="stylesheet" />
</@layout.head>
<@layout.body>
    <!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="post">
       		
            <div class="layui-form-item ">
                 <label class="layui-form-label"><span style="color: red;">*</span>位置</label>
                <div class="layui-input-block">
                    <select name="location" class="layui-input">
                        <option value="">--全部--</option>
                        <option value="index">首页Banner (1920 x 500)</option>
                        <option value="task">任务页Banner (1920 x 300)</option>
                        <option value="niuren">牛人页Banner (1920 x 300)</option>
                        <option value="index_top">首页顶部广告位 (1400 x 80)</option>
                        <option value="index_ad">首页内部广告位 (1400 x 80)</option>
                        <option value="employer_index_ad">雇主首页广告 (273 x 167)</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item ">
                 <label class="layui-form-label"><span style="color: red;">*</span>图片</label>
                <div class="layui-input-block">
                    <input type="hidden" id="imgpath" name="imgpath" value="" />
            			<div style="display:inline;padding-top:10px;" id="coverPicker">上传</div><span id="progress"></span>
                		<br/><img id="imgpath2" style="max-width: 500px;" />
                </div>
            </div>

            <div class="layui-form-item ">
                 <label class="layui-form-label"><span style="color: red;">*</span>URL</label>
                <div class="layui-input-block">
                    <input type="text" name="targeturl" required lay-verify="required" placeholder="Url"
                           autocomplete="off" class="layui-input" value="">
                </div>
            </div>
            
            <div class="layui-form-item ">
                 <label class="layui-form-label">排序号</label>
                <div class="layui-input-block">
                    <input type="text" name="sortno" placeholder=""
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
<script src="/lib/webuploader/webuploader.js"></script>
	<script type="text/javascript">
		$(function () {

             // Web Uploader实例
             webUploader = WebUploader.create({
                 // 选完文件后，是否自动上传。
                 auto: true,

                 // swf文件路径
                 swf: '/lib/webuploader/Uploader.swf',

                 // 文件接收服务端。
                 server: '/manage/uploadimage',

                 // 选择文件的按钮。可选。
                 // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                 pick: '#coverPicker',

                 //只允许选择图片
                 accept: {
                     title: '图片',
                     extensions: 'jpg,jpeg,png,gif'
                 }
             });

             // 文件上传过程中创建进度条实时显示。
             webUploader.on('uploadProgress', function (file, percentage) {
                 $('#progress').text(Math.round(percentage * 1000) / 10 + '%');
             });

             // 文件上传失败，显示上传出错。
             webUploader.on('uploadError', function (file) {
                 $('#progress').text('上传失败');
             });

             // 文件上传成功，给item添加成功class, 用样式标记上传成功。
             webUploader.on('uploadSuccess', function (file, response) {
		        $('#imgpath').val(response.fileUrl);
		        $('#imgpath2').attr('src', response.fileUrl);
             });
         });
     </script>
     
	<script type="text/javascript">
		
        layui.use('form', function () {
	        var form = layui.form;
	
	        form.on('submit(save)', function (data) {
	            ajaxSubmit('banneradd', data);
	            return false;
	        });
	    });
        
    </script>    
</@layout.foot>