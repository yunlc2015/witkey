<#import "/manage/layout/page.ftl" as layout>
<@layout.head>
<style>
.layui-nav-tree .layui-nav-child .active, .layui-nav-tree .layui-nav-child dd:hover
	{
	background: #0695ff;
}
</style>
</@layout.head>
<@layout.body>
	<div style="padding:30px;">
		<h3>${errorMsg!"访问的内容不见了。"}</h3>
	</div>
</@layout.body>
<@layout.foot>
</@layout.foot>