<ul class="layui-nav layui-nav-tree" lay-filter="test">

	<li class="layui-nav-item <#if path?starts_with("/manage/index")>layui-this</#if>">
        <a href="${contextPath}/manage/index">首页</a>
    </li>
	<li class="layui-nav-item layui-nav-itemed">
        <a class="" href="javascript:;">VOD管理</a>
        <dl class="layui-nav-child">
			<dd <#if path?starts_with("/manage/video")>class="layui-this"</#if>>
                <a href="${contextPath}/manage/videolist">视频管理</a>
            </dd>
            <dd <#if path?starts_with('/manage/auth')>class="layui-this"</#if>>
                <a href="${contextPath}/manage/authlist">授权记录</a>
            </dd>
        </dl>
    </li>
</ul>

 <script>
     //JavaScript代码区域
     layui.use('element', function () {
         var element = layui.element;
     });
 </script>
