<html><head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
    <title>后台登录</title>
    <link rel="stylesheet" type="text/css" href="/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="/lib/css/manage-login.css">
    <style>

        element.style {
        }
        img {
            display: inline-block;
            border: none;
            vertical-align: middle;
        }
        img[Attributes Style] {
            width: 116px;
            height: 36px;
        }
        .form_code .code {
            position: absolute;
            right: 80px;
            top: 195px;
            cursor: pointer;
        }
        .login_btn {
            width: 100%;
        }

    </style>
</head>
<body>
    <div class="m-login-bg">
        <div class="m-login">
            <h3>管理登录</h3>
            <div class="m-login-warp">
                <form class="layui-form"  action="" method="post">
                    <div class="layui-form-item">
                        <input type="text" name="username" lay-verify="required"  placeholder="用户名" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-item">

                        <input type="password" name="password"  lay-verify="required"  placeholder="密码" autocomplete="off" class="layui-input">

                    </div>
                    <div class="layui-form-item">

                        <input type="text" id="vcode" name="vcode"  lay-verify="required"  placeholder="验证码" autocomplete="off" class="layui-input" style="width:200px;display:inline-block">
						<img src="/captcha" id="captcha" onclick="this.src='/captcha?'"/>
                    </div>
                    <div class="layui-form-item m-login-btn">
                        <div class="layui-inline">
                            <button class="layui-btn layui-btn-normal" lay-submit="login" lay-filter="login">登录</button>

                        </div>
                        <div class="layui-inline">
                            <button type="reset" class="layui-btn layui-btn-primary">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="/lib/layui/layui.js" type="text/javascript" charset="utf-8"></script>
    <script src="/lib/js/jquery.js"></script>
    <script>
        layui.use(['form', 'layedit', 'laydate'], function() {
            var form = layui.form,
                layer = layui.layer;

            //监听提交
            form.on('submit(login)', function(data) {
                var datas = "username=" + data.field.username + "&password=" + data.field.password + "&vcode=" + data.field.vcode;
                $.ajax({
                    type: "POST",
                    url: "login",
                    data: datas,
                    dataType: "json",
                    success: function (result) {
                        if (result.errCode == 0) {//登录成功
                            location.href = result.data;
                        } else if (result.errCod == -2) {
                        		layer.msg(result.errMsg, {icon: 5});
                        } else {
                            layer.msg(result.errMsg, {icon: 5});
                        }
                    }
                });
                return false;
            });
        });
    </script>
</body>
</html>