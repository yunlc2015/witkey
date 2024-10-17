<#--
  云联创威客系统
  Copyright 2015 云联创科技

  账户设置页
  author: Billy Zhang (billy_zh@126.com)
-->
<#import "/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body class="bg8">

	<div id="top">
		<#include "/inc/top.ftl" />
	</div>
	<div id="hd">
		<#include "/inc/search.ftl" />
	</div>
	<div id="header">
		<@mobHeader />
	</div>
    <aside id="aside">
        <#include "/inc/mobnav.ftl" />
    </aside>
	<div id="nv" class="g-borb">
		<@webNav />
	</div>
	<div class="h30"></div>

	<div id="bd">
		<div class="wp">
			<div class="side-col">
				<h2 class="g-tit3"><a href="index"><span class="ico-home">我的首页</span></a></h2>
				<div class="side-con">
					<ul class="userNav">
						<li><h3><a href="">交易管理</a></h3>
							<dl>
                                <dd><a href="/task/publish">发布任务</a></dd>
								<dd><a href="tasklist">我的订单</a></dd>
								<dd><a href="tousu">交易投诉</a></dd>
							</dl>
						</li>
						<li><h3><a href="">账务中心</a></h3>
							<dl>
								<dd><a href="wallet">我的钱包</a></dd>
								<dd><a href="withdrawallist">提现记录</a></dd>
							</dl>
						</li>
						<li><h3><a href="">账户信息</a></h3>
							<dl>
								<dd><a href="accountsafety">账户安全</a></dd>
								<dd><a href="shezhi">修改信息</a></dd>
							</dl>
						</li>
					</ul>
				</div>
			</div>

			<div class="main-col">
				<h3 class="tz-tit2"><span>修改信息</span></h3>
				<div class="tz-changeInfo">
					<form id="shezhi-form">
					<div class="avatarBox">
						<img id="avatar2" src="${user.avatar!'/lib/images/avatar.jpg'}" alt="">
						<div class="txt">
							<div style="padding:20px 0px;" id="avatarPicker">修改头像</div>
                            <p>
                                （修改成功后自动保存）<br>
                                格式：jpg, png, bmp <br>
                                体积：小于1M <br>
                                尺寸：推荐大于 300 x 300 的正方形
                            </p>
                        </div>
					</div>
					<table class="tz-table1">
						<tr>
							<th>　昵称：</th>
							<td><input type="text" class="inp" id="fn" name="nickname" value="${nickname}" /></td>
						</tr>
						<tr>
							<th>　性别：</th>
							<td>
								<label><input type="radio" name="gender" class="inp-r" value="1" <#if user.gender==1>checked="checked"</#if> />男</label>
								<label><input type="radio" name="gender" class="inp-r" value="2" <#if user.gender==2>checked="checked"</#if> />女</label>
							</td>
						</tr>
						<tr>
							<th><em style="color:red">*</em>手机：</th>
							<td><input type="text" class="inp" id="fm2" name="mobile2" value="${mobile2}" /></td>
						</tr>
						<tr>
							<th>　Q Q：</th>
							<td><input type="text" class="inp" id="fim" name="im" value="${user.im!}" /></td>
						</tr>
						<tr>
							<th>工作地点：</th>
							<td>
								<select id="sp" name="province" class="inp" style="width:120px">
                                    <option value="0">请选择</option>
                                    <#list provList as city >
                                    <option value='${city.id}'>${city.province}</option>
                                    </#list>
								</select>
								<select id="sc" name="city" class="inp" style="width:120px">
                                    <option value="0">请选择</option>
                                    <#list cityList as city >
                                    <option value='${city.id}'>${city.city}</option>
                                    </#list>
								</select>
							</td>
						</tr>
						<tr>
							<th>工作单位：</th>
							<td><input type="text" class="inp" id="fc" name="company" value="${user.company!}"></td>
						</tr>
						<tr>
							<th>当前密码：</th>
							<td><input type="password" class="inp" name="oldpasswd" /></td>
						</tr>
						<tr>
							<th>　新密码：</th>
							<td><input type="password" class="inp" name="newpasswd" /></td>
						</tr>
						<tr>
							<th>确认密码：</th>
							<td><input type="password" class="inp" name="newpasswd2" />
								<div class="levelBar2"><span class="on">弱</span><span>中</span><span>强</span></div>
							</td>
						</tr>
						<tr>
							<th></th>
							<td><input type="submit" value="确认修改" class="inp2" ></td>
						</tr>
					</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="h30"></div>

</@layout.body>
<@layout.foot>

    <script src="/lib/webuploader/webuploader.js"></script>
    <script type="text/javascript">
        $(function () {
            // Web Uploader实例
            webUploader = WebUploader.create({
                // 选完文件后，是否自动上传。
                auto: true,
                // swf文件路径
                swf: '/lib/webuploader/Uploader.swf',
                // 文件接收服务端。
                server: '/user/uploadavatar',
                // 选择文件的按钮。可选。
                // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                pick: '#avatarPicker',
                //只允许选择图片
                accept: {
                    title: '图片',
                    extensions: 'jpg,jpeg,png,gif'
                }
            });

            // 文件上传过程中创建进度条实时显示。
            webUploader.on('uploadProgress', function (file, percentage) {
                //$('#progress').text(Math.round(percentage * 1000) / 10 + '%');
            });

            // 文件上传失败，显示上传出错。
            webUploader.on('uploadError', function (file) {
                //$('#progress').text('上传失败');
            });

            // 文件上传成功。
            webUploader.on('uploadSuccess', function (file, response) {
                $('#avatar2').attr('src', response.fileUrl);
            });
        });
    </script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#shezhi-form").on('submit', function() {
				var options = {
                    url: 'shezhi',
                    type: 'post',
                    dataType: 'json',
                    data: $("#shezhi-form").serialize(),
                    success: function (resp) {
                        if (resp.errCode == 0) {
                            alert("Success.");
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
	<script type="text/javascript">
	    $(document).ready(function () {
	        $('#sp').val(${provId});
	        $('#sc').val(${cityId});

	        $('#sp').change(function () {
	            $.ajax({
	                type: 'get',
	                url: '/getcities?cid=' + $(this).val(),
	                cache: false,
					dataType: 'json',
	                success: function (ret) {
	                    if (ret.errCode!=0) {
							alert(ret.errMsg);
							return;
						}
	                    var optStr = "<option value=\"0\">请选择</option>";
						var cityArr = ret.data;
	                    for (var i = 0; i < cityArr.length; i++) {
	                        optStr += "<option value=\"" + cityArr[i].id + "\">" + cityArr[i].city + "</option>";
	                    }
	                    $('#sc').html(optStr);
	                }
	            });
	        });
	    });
    </script>

</@layout.foot>