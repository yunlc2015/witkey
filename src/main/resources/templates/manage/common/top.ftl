<div class="layui-logo" style="margin-left:20px;">${systemName} VOD管理</div>
<ul class="layui-nav layui-layout-left">
    <li class="layui-nav-item">
        <a href="${consoleEndpoint}">控制台</a>
    </li>
    <li class="layui-nav-item">
        <a href="javascript:showPopup('about', '关于');">关于</a>
    </li>
</ul>
  <ul class="layui-nav layui-layout-right" style="margin-right:20px;">
      <li class="layui-nav-item">
          <a href="javascript:;">
               ${(auth.adminName)!}
          </a>
          <dl class="layui-nav-child">
              <dd><a href="${logoutEndpoint}">退出</a></dd>
          </dl>
      </li>
  </ul>
<script type="text/javascript">
    function showPopup(path, title) {
        //iframe层
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                type: 2,
                title: title,
                shadeClose: false,
                shade: 0.8,
                area: ['700px', '500px'],
                content: '${contextPath}/manage/' + path
            });
        });
    }
</script>