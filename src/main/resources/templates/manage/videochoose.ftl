<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title></title>
  <script type="text/javascript" src="${contextPath}/lib/js/jquery.js"></script>
  <script type="text/javascript" src="${contextPath}/lib/js/jquery.form.js"></script>
  <script type="text/javascript" src="${contextPath}/lib/layui/layui.js"></script>
  <link rel="stylesheet" href="${contextPath}/lib/layui/css/layui.css">
  <link rel="stylesheet" href="${contextPath}/lib/css/base.css">
  <style>
    .layui-nav-tree .layui-nav-child .active,
    .layui-nav-tree .layui-nav-child dd:hover {
      background: #0695ff;
    }
  </style>
</head>

<body>

      <!-- 内容主体区域 -->
      <div class="manage" style="padding: 15px;">
      
		<form action="videochoose" method="get">
            名称：<input type="text" name="keyword" value="${keyword!}" >
            类别：<select name="category">
                <option value="0">-不限-</option>
                <#if catelist?? >
                <#list catelist as cate>
                <option value="${cate.id}" <#if cate.id==category>selected</#if>>${cate.name}</option>
                </#list>
                </#if>
            </select>
            <input type="submit" value="查找"/>
        </form>
        
	      <table id="demo1" class="layui-table">
	      <thead>
	           <tr>
                    <th>标题</th>
                    <th>操作</th>
                </tr>
                </thead>

                <#list videolist as video >
                    <tr>
                         <td class="left">${(video.name)!}</td>
						<td>
							<a href="javascript:parent.videoChoosed(${video.id}, '${video.name}');" class="layui-btn  layui-btn-normal layui-btn-sm">选择</a>
						</td>
                    </tr>
                    
                </#list>

 		  </table>
 		  
          <div id="pager"></div>
      </div>
   
    <script type="text/javascript">
        layui.use(['laypage'], function () {
            var laypage = layui.laypage
                
            var count = [[${pager.totalCount}]];
            var pageNum = [[${pager.pageNo}]];

            var pageSize = [[${pager.pageSize}]];
            //调用分页

            laypage.render({
                elem: 'pager'
                , theme: '#1E9FFF'
                , count: count
                , curr: pageNum
                , limit: pageSize //每页显示的条数
                , layout: ['count', 'prev', 'page', 'next', 'skip']
                , jump: function (obj, first) {
                    if (!first) {
                       location.href = "${contextPath}/manage/videochoose?pageNo=" + obj.curr;
                    }
                }
            });
        })		
    </script>

</body>

</html>


