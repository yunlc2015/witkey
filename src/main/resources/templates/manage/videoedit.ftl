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
            您所在的位置: 内容管理 > 编辑视频
        </div>
      <!-- 内容主体区域 -->
      <div class="manage" style="padding: 15px;">
        
        <form id="form1" method="post" action="videoedit?appid=${appId}&videoid=${video.id}">
        
            <div class="form-row">
              <div class="col-md-2">
		        <label for="oldpasswd">视频名称:</label></div>
		      <div class="col-md-10">
		        <input type="text" class="form-control" id="name" name="name" value="${video.name}"></div>
		    </div>
		    
		    <div class="form-row">
              <div class="col-md-2">
                  <label for="column">类别:</label></div>
              <div class="col-md-10">
                  <select class="form-control" id="category" name="category">
                   <option value="0">-请选择-</option>
                   <#if catelist?? && catelist?size &gt; 0>
                     <#list catelist as cate>
                      <option value="${cate.id}">${cate.name}</option>
                     </#list> 
                    </#if>  
                  </select></div>
            </div>
            
		    <div class="form-row">
              <div class="col-md-2"></div>
              <div class="col-md-10">
                <input type="button" id="submit" class="btn btn-primary" value=" 提交 " /></div>
            </div>
            
        </form>
    		
		 </div>
       
    </div>
  </div>
    
	<script type="text/javascript">
		$(function ($) {

            $("#category").val(${video.categoryId});
            
            function checkField(obj, msg) {
                var val = obj.val();
                if (val == '') {
                    alert(msg);
                    obj.focus();
                    return true;
                }
                return false;
            }

            $("#submit").click(function () {
            	return submit();
            });
            
            function submit() {
				if (checkField($("#name"), "请输入名称。"))
                    return false;
                
                layer.msg('正在处理...');

                $('#form1').ajaxSubmit({
                    success: function (json) {
                          
                        if (json.errCode == 0) {
                        	layer.msg('修改成功。', function() { location.href='${contextPath}/manage/videolist'; });
                        } else if (json.errCode < 0) {
                            layer.msg(json.errMsg);
                        } else {
                            layer.msg(json.errMsg);
                        }
                    }
                });

                return false;
             }
          });
	</script>
	  
</body>

</html>