<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>修改密码</title>
    <script type="text/javascript" src="/manage/lib/js/jquery.js"></script>
    <script type="text/javascript" src="/lib/layui/layui.js"></script>
    <link rel="stylesheet" href="/lib/layui/css/layui.css">
    <link rel="stylesheet" href="/lib/css/manage.css">
    <style>
        .layui-nav-tree .layui-nav-child .active,
        .layui-nav-tree .layui-nav-child dd:hover {
            background: #0695ff;
        }

        .layui-input {
            width: 70%;
        }
    </style>
</head>

<body class="layui-layout-body">

<!-- 内容主体区域 -->
<div class="manage" style="padding: 15px;">
<#--<form id="form1" class="layui-form" action="/manage/changepwd"  method="post">-->
    <form class="layui-form" method="post">

        <div class="layui-form-item ">
            <label class="layui-form-label">原密码<span style="color: red;">*</span></label>
            <div class="layui-input-block">
                <input type="password" name="passwd" required lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item ">
            <label class="layui-form-label">新密码<span style="color: red;">*</span></label>
            <div class="layui-input-block">
                <input type="password" name="passwd2" required lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button lay-filter="go" type="submit" lay-submit
                        class="layui-btn  layui-btn-normal ">保存
                </button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>

</div>

</div>

<script type="text/javascript">
    layui.use('form', function () {
        var form = layui.form;

        form.on('submit(go)', function (data) {
            var url = '/manage/changepwd';
            $.ajax({
                type: "post",
                url: url,
                // async: false,//同步提交。不设置则默认异步，异步的话，最后执行ajax
                data: data.field,
                dataType: 'json',
                success: function (data) {
                    if (data.errCode == -1) {
                        layer.alert('原密码不正确！');
                    } else {
                        layer.msg('密码修改成功。', function () {
                            parent.layer.closeAll();
                        });
                    }
                },
                error: function () {
                    layer.msg("请刷新后重试");
                }
            });
            return false;
        });
    });


</script>

</body>

</html>


