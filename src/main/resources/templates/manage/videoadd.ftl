<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>视频列表</title>
  <script type="text/javascript" src="${contextPath}/lib/js/jquery.js"></script>
  <script type="text/javascript" src="${contextPath}/lib/layui/layui.js"></script>
  <link rel="stylesheet" href="${contextPath}/lib/layui/css/layui.css">
  <link rel="stylesheet" href="${contextPath}/lib/css/base.css">
  <style>
  </style>
</head>

<body class="layui-layout-body">
  <div class="layui-layout layui-layout-admin">
    <div class="layui-header">
      <#include '/manage/common/top.ftl'>
    </div>

    <div class="layui-side layui-bg-black">
      <div class="layui-side-scroll">
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<#include '/manage/common/left.ftl' />
      </div>
    </div>

    <div class="layui-body">
        <div class="place">
            您所在的位置: 内容管理 > 添加视频
        </div>
      <!-- 内容主体区域 -->
      <div class="manage" style="padding: 15px;">
        
        <form class="layui-form" method="post">
        
        	<div class="layui-form-item ">
                 <label class="layui-form-label"><span style="color: red;">*</span>文件</label>
                <div class="layui-inline">
                    <input type="file" name="file" id="file" required lay-verify="required" class="layui-input"/>
                    <input type="hidden" id="video-id" name="videoid" value="" />
                    <button id="video-upload" disabled="true">开始上传</button><span id="file-progress"></span>
                </div>
            </div>
            
            <div class="layui-form-item ">
                 <label class="layui-form-label"><span style="color: red;">*</span>视频名称</label>
                <div class="layui-input-block">
                    <input type="text" id="name" name="name" required lay-verify="required" placeholder="标题"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            
             <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">类别</label>
                <div class="layui-input-block">
                    <select name="topic">
                    	  <option value="">--请选择--</option>
                    	  <#if catelist?? && catelist?size &gt; 0>
	                     <#list catelist as cate>
	                      <option value="${cate.id}">${cate.name}</option>
	                     </#list> 
	                    </#if>  
                    </select>
                </div>
            </div>
            
             <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">转码</label>
                <div class="layui-input-block">
                    <select id="transtemplate" name="transtemplate">
                    	  <option value="">--请选择--</option>
                    	  <#list transgrouplist as trans>
	                      <option value="${trans.templateId}">${trans.name}</option>
	                     </#list> 
                    </select>源片宽度小于转码指定宽度时，按源片宽度处理。
                </div>
            </div>
            
		    <div class="layui-form-item">
                <div class="layui-input-block">
	                <button lay-filter="save" type="submit" lay-submit class="layui-btn layui-btn-normal ">保存</button>
	            </div>
            </div>
            
        </form>
    		
		 </div>
       
    </div>
  </div>
  	
    <script src="${contextPath}/lib/aliyunupload/aliyun-upload-sdk-1.5.0.min.js"></script>
    <script src="${contextPath}/lib/aliyunupload/lib/es6-promise.min.js"></script>
    <script src="${contextPath}/lib/aliyunupload/lib/aliyun-oss-sdk-5.3.1.min.js"></script>
    <script type="text/javascript">
        //兼容IE11
        if (!FileReader.prototype.readAsBinaryString) {
            FileReader.prototype.readAsBinaryString = function (fileData) {
               var binary = "";
               var pt = this;
               var reader = new FileReader();      
               reader.onload = function (e) {
                   var bytes = new Uint8Array(reader.result);
                   var length = bytes.byteLength;
                   for (var i = 0; i < length; i++) {
                       binary += String.fromCharCode(bytes[i]);
                   }
                    //pt.result  - readonly so assign binary
                    pt.content = binary;
                    pt.onload()
                }
                reader.readAsArrayBuffer(fileData);
            }
        }
    
        $(function () {
            function createUploader () {
               var uploader = new AliyunUpload.Vod({
                   //阿里账号ID，必须有值 ，值的来源https://help.aliyun.com/knowledge_detail/37196.html
                   userId: 1210358803211641,
                   
                   //分片大小默认1M，不能小于100K              
                   partSize: 1048576,
                   
                   //并行上传分片个数，默认5
                   parallel: 5,
                   
                   //网络原因失败时，重新上传次数，默认为3
                   retryCount: 3,
                   
                   //网络原因失败时，重新上传间隔时间，默认为2秒
                   retryDuration: 2,
                   
                   //请求过期时间
                   timeout: 60000,
                   
                   // 配置项 region
                   region: 'cn-shanghai',
    
                    // 添加文件成功
                  addFileSuccess: function (uploadInfo) {
                    $('#video-upload').attr('disabled', false)
                    console.log("addFileSuccess: " + uploadInfo.file.name)
                  },
              
                   // 开始上传
                  'onUploadstarted': function (uploadInfo) {
                     console.log("onUploadStarted:" + uploadInfo.file.name + ", endpoint:" + uploadInfo.endpoint + ", bucket:" + uploadInfo.bucket + ", object:" + uploadInfo.object);
                     
                     //上传方式1, 需要根据uploadInfo.videoId是否有值，调用点播的不同接口获取uploadauth和uploadAddress，如果videoId有值，调用刷新视频上传凭证接口，否则调用创建视频上传凭证接口
                     if (uploadInfo.videoId) {
                        // 如果 uploadInfo.videoId 存在, 调用 刷新视频上传凭证接口(https://help.aliyun.com/document_detail/55408.html)
                        $.post("${contextPath}/manage/uploadauthrefresh?vodid=" + uploadInfo.videoId,
                            function(result) {
                                if (result.errCode==0) {
                                    uploadAuth = result.data.uploadAuth;
                                    uploadAddress = result.data.uploadAddress;
                                    
                                    //从点播服务获取的uploadAuth、uploadAddress和videoId,设置到SDK里
                                    uploader.setUploadAuthAndAddress(uploadInfo, uploadAuth, uploadAddress, uploadInfo.videoId);
                                } else {
                                    alert(result.errMsg);
                                }
                            }, 'json'
                        );
                     } else {
                        // 如果 uploadInfo.videoId 不存在,调用 获取视频上传地址和凭证接口(https://help.aliyun.com/document_detail/55407.html)
                        $.post("${contextPath}/manage/uploadauthget", { filename:uploadInfo.file.name},
                            function(result) {
                                if (result.errCode==0) {
                                    videoId = result.data.videoId;
                                    uploadAuth = result.data.uploadAuth;
                                    uploadAddress = result.data.uploadAddress;
                                    
                                    //从点播服务获取的uploadAuth、uploadAddress和videoId,设置到SDK里
                                    uploader.setUploadAuthAndAddress(uploadInfo, uploadAuth, uploadAddress, videoId);
                                } else {
                                    alert(result.errMsg);
                                }
                            }, 'json'
                        );
                     }
                  },
                  
                  // 文件上传成功
                  'onUploadSucceed': function (uploadInfo) {
                    console.log("onUploadSucceed: " + uploadInfo.file.name + ", endpoint:" + uploadInfo.endpoint + ", bucket:" + uploadInfo.bucket + ", object:" + uploadInfo.object);
                    $('#name').val(uploadInfo.file.name);
                    $('#video-id').val(uploadInfo.videoId);
                  },
                  
                  // 文件上传失败
                  'onUploadFailed': function (uploadInfo, code, message) {
                    console.log("onUploadFailed: file:" + uploadInfo.file.name + ",code:" + code + ", message:" + message);
                  },
                  
                  // 文件上传进度，单位：字节
                  'onUploadProgress': function (uploadInfo, totalSize, loadedPercent) {
                      console.log("onUploadProgress:file:" + uploadInfo.file.name + ", fileSize:" + totalSize + ", percent:" + Math.ceil(loadedPercent * 100) + "%");
                      $('#file-progress').text('上传进度：'+Math.ceil(loadedPercent * 1000) / 10 + '%');
                  },
                  
                  // 上传凭证超时
                  'onUploadTokenExpired': function (uploadInfo) {
                      console.log("onUploadTokenExpired");
                      //实现时，根据uploadInfo.videoId调用刷新视频上传凭证接口重新获取UploadAuth
                      //https://help.aliyun.com/document_detail/55408.html
                      //从点播服务刷新的uploadAuth,设置到SDK里    
                      $.post("${contextPath}/manage/uploadauthrefresh?vodid=" + uploadInfo.videoId,
                            function(result) {
                                if (result.errCode==0) {
                                    uploader.resumeUploadWithAuth(result.data.uploadAuth); 
                                }
                            }, 'json'
                        );
                    },
                  
                    //全部文件上传结束
                    'onUploadEnd':function(uploadInfo){
                         console.log("onUploadEnd: uploaded all the files");
                     }
               });
               
               return uploader;
          }
          
           var uploader = null;
           
           $('#file').on('change', function(event) {
                var file = event.target.files[0];
                if (!file) {
                  alert("请先选择需要上传的文件!");
                  return;
                }
                console.log(file);
                
                if (uploader) {
                  uploader.stopUpload();
                  $('#file-progress').text("");
                }
                uploader = createUploader();
        
                var userData = '{"Vod":{}}';
                uploader.addFile(file, null, null, null, userData);
                $('#video-upload').attr('disabled', false);
           });
           
           $('#video-upload').on('click', function() {
               if (uploader !== null) {
                    uploader.startUpload();
                    $('#video-upload').attr('disabled', true);
                }
                
                return false;
           });
           
        });
     </script>
     
	<script type="text/javascript">
		
        function checkField(obj, msg) {
            var val = obj.val();
            if (val == '') {
                alert(msg);
                obj.focus();
                return true;
            }
            return false;
        }

        layui.use('form', function () {
	        var form = layui.form;
	
	        form.on('submit(save)', function (data) {
				if (checkField($("#video-id"), "请上传视频文件。"))
                    return false;
                if (checkField($("#name"), "请输入视频名称。"))
                    return false;
                
               var url = '${contextPath}/manage/videoadd';
	            $.ajax({
	                type: "post",
	                url: url,
	                data: data.field,
	                dataType: 'json',
	                success: function (resp) {
	                    if (resp.errCode == 0) {
	                        location.href='${contextPath}/manage/videolist';
	                    } else {
	                        layer.msg(resp.errMsg);
	                    }
	                },
	                error: function () {
	                    layer.msg("出现错误，请稍后重试");
	                }
	            });
	            return false;
          });
      });
	</script>
	  
</body>

</html>
