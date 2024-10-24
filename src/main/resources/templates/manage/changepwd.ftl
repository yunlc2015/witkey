<#import "/manage/layout/dialog.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
<!-- 内容主体区域 -->
<div class="manage" style="padding: 20px;">
    <form class="layui-form" method="post">

        <div class="layui-form-item ">
            <label class="layui-form-label">原密码<span style="color: red;">*</span></label>
            <div class="layui-input-block">
                <input type="password" name="oldpasswd" required lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item ">
            <label class="layui-form-label">新密码<span style="color: red;">*</span></label>
            <div class="layui-input-block">
                <input type="password" name="newpasswd" required lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item ">
            <label class="layui-form-label">密码确认<span style="color: red;">*</span></label>
            <div class="layui-input-block">
                <input type="password" name="newpasswd2" required lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button lay-filter="go" type="submit" lay-submit class="layui-btn  layui-btn-normal ">保存
                </button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
</@layout.body>
<@layout.foot>
<script type="text/javascript">
    layui.use('form', function () {
        var form = layui.form;

        form.on('submit(go)', function (data) {
            ajaxSubmit('changepwd', data);
            return false;
        });
    });
</script>
</@layout.foot>


