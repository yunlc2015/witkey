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
          <dl class="layui-nav-child">
              <dd><a href="javascript:openDialog('/manage/changepwd', '修改密码');">修改密码</a></dd>
              <dd><a href="/manage/logout">退出</a></dd>
          </dl>
      </li>
  </ul>