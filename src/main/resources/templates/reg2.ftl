<#--
  云联创威客系统
  Copyright 2015 云联创科技

  用户注册表单页
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
				<h2>加入<br>实现你我共同的设计梦想</h2>
				<div class="loginBox">
					<h3>欢迎注册</h3>
					<div class="con">
                        <form id="reg-form" action="reg2">
                            <input type="hidden" name="clazz" value="${clazz}"/>
						<div class="inp-row">
							<input type="text" placeholder="手机号" class="inp" name="mobile" id="mobile">
						</div>
						<div class="inp-row inp-yzm">
							<input type="text" placeholder="验证码" class="inp" name="gcode" id="gcode">
							<img id="captcha" src="captcha" onclick="refreshimage();" />
						</div>
						<div class="inp-row inp-yzm">
							<input type="text" placeholder="手机验证码" class="inp" name="vcode" id="vcode">
							<a href="javascript:void(0);" class="tz-btn2 js-getPass">获取验证码</a>
						</div>
						<div class="inp-row">
							<input type="password" placeholder="密码" class="inp" name="password" id="password">
						</div>
						<label for="license">　<input type="checkbox" name="license" id="license" value="1">我已阅读并接受</label>
						<div class="inp-row">
							<input type="submit" value="免费注册" class="g-btn6 ani">
						</div>
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

        $(document).ready(function ($) {

            var sending = false;

            // 倒计时
            $('.js-getPass').click(function (event) {
                if ($('.js-getPass').hasClass('sending')) {
                    return;
                }

                var mobile = $('#mobile').val();
                if (mobile == '') {
                    alert("请输入手机号。");
                    return;
                }

                if (mobile.length != 13 && mobile.substring(0, 1) != '1') {
                    alert("无效的手机号。");
                    return;
                }

                var vcode = $('#gcode').val();
                if (vcode == '') {
                    alert("请输入验证码。");
                    return;
                }

                var options = {
                    url: 'getvalidcode2',
                    type: 'post',
                    dataType: 'json',
                    data: { mobile: mobile, vcode: vcode, kind: 'reg' },
                    success: function (resp) {
                        if (resp.errCode==0) {
                            alert(resp.data);
                        } else {
                            alert(resp.errMsg);
                        }
                    }
                };
                $.ajax(options);

                $(this).toggleClass('sending');
                $(this).text('99秒后重新发送');
                var num = 99;
                setInterval(function () {
                    if ($('.js-getPass').hasClass('sending')) {
                        if (num > 0) {
                            num = num - 1;
                            $('.js-getPass').text(num + '秒后重新发送');
                        } else {
                            $('.js-getPass').text('获取验证码').removeClass('sending');
                            num = 99;
                        }
                    }
                }, 1000);
            });
        });

        $(document).ready(function() {
            $("#reg-form").on('submit', function() {

                var mobile = $('#mobile').val();
                if (mobile == '') {
                    alert("请输入手机号。");
                    return false;
                }

                if (mobile.length != 11 || mobile.substring(0, 1) != '1') {
                    alert("无效的手机号。");
                    return false;
                }

                var gcode = $('#gcode').val();
                if (gcode == '') {
                    alert("请输入验证码。");
                    return false;
                }

                var vcode = $('#vcode').val();
                if (vcode == '') {
                    alert("请输入手机验证码。");
                    return false;
                }

                if (vcode.length != 6) {
                    alert("无效的验证码。");
                    return false;
                }

                var pwd = $('#password').val();
                if (pwd == '') {
                    alert("请输入密码。");
                    return false;
                }

                if (pwd.length < 5) {
                    alert("密码不能少于5个字母或数字。");
                    return false;
                }

                /* var lic = $('#license').val();
                if (lic != '1') {
                    alert("请阅读注册协议并同意。");
                    return false;
                } */

                var options = {
                    url: 'reg2',
                    type: 'post',
                    dataType: 'json',
                    data: $("#reg-form").serialize(),
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

        function refreshimage() {
            var cap = document.getElementById('captcha');
            cap.src = cap.src + '?';
        }
    </script>

</@layout.foot>