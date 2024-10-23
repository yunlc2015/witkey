<div class="layui-logo" style="margin-left:20px;">后台管理</div>
<ul class="layui-nav layui-layout-left">
    <li class="layui-nav-item">
        <a href="/index" target="_blank">首页</a>
    </li>
     <li class="layui-nav-item">
        <a href="javascript:openDialog('/manage/about', '关于');">关于</a>
    </li>
</ul>
  <ul class="layui-nav layui-layout-right" style="margin-right:20px;">
      <li class="layui-nav-item">
          <a href="javascript:;">
               ${(auth.name)!}
          </a>
      </li>
  </ul>