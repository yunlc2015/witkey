<#--
  云联创威客系统
  Copyright 2015 云联创科技

  用户登录页
  author: Billy Zhang (billy_zh@126.com)
-->
<#import "/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>

	<div id="top">
		<#include "/inc/top.ftl" />
	</div>

	<div id="hd">
	    <#include "/inc/search.ftl" />
	</div>

	<aside id="aside">
		<#include "/inc/mobnav.ftl" />
	</aside>

	<div id="bd">
		<div class="bg-login" style="background-image: url(lib/images/ban-login.jpg);">
			<div class="wp">
				<h2>官方实力认证<br>开创落标有偿设计诚信时代</h2>
				<div class="loginBox">
					<h3>欢迎登录</h3>
					<div class="con">
                        <form id="login-form">
						<div class="inp-row">
							<input type="text" placeholder="手机号/已注册账号" class="inp" id="mobile" name="mobile">
						</div>
						<div class="inp-row">
							<input type="password" placeholder="密码" class="inp" id="password" name="password">
						</div>
						<label for="">　<input type="checkbox" id="remember" name="remember" value="15">两周内自动登录</label>
						<a href="forgetpwd" class="fc_blue r">忘记密码？</a>
						<div class="inp-row">
							<input type="submit" value="登 录" class="g-btn6 ani">
						</div>
						<div class="inp-row tc"><a href="reg" class="fc_blue">立即注册</a>，入驻${settings.appName}</div>
                            </form>
					</div>
					<div class="tc">高端设计师在线服务交易平台</div>
				</div>
			</div>
		</div>
	</div>

</@layout.body>
<@layout.foot>
    <script type="text/javascript">

        $(document).ready(function() {
            $("#login-form").on('submit', function() {
				var mobile = $('#mobile').val();
				if (mobile == '') {
					alert("请输入手机号。");
					return false;
				}

				if (mobile.length != 11 || mobile.substring(0, 1) != '1') {
					alert("无效的手机号。");
					return false;
				}

				var pwd = $('#password').val();
				if (pwd == '') {
					alert("请输入密码。");
					return false;
				}

				var options = {
                    url: 'login',
                    type: 'post',
                    dataType: 'json',
                    data: $("#login-form").serialize(),
                    success: function (resp) {
                        if (resp.errCode == 0) {
                            location.href = resp.data;
                        } else {
                            alert(resp.errMsg);
                        }
                    }
                };
                $.ajax(options);

				return false;
			});
        });
    </script>

</@layout.foot>
