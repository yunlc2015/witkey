<#--
  云联创威客系统
  Copyright 2015 云联创科技

  账户设置页
  author: Billy Zhang (billy_zh@126.com)
-->
<#import "/layout/page.ftl" as layout>
<@layout.head>
	<link href="/lib/webuploader/webuploader.css" rel="stylesheet" />
    <style type="text/css">
        .red {
            color:red;
        }
        .cate {
            display:inline-block;
            padding:5px 10px;
            font-size:16px;
            color:#959ca0;
        }
    </style>
</@layout.head>
<@layout.body class="bg8">

	<div id="top">
		<#include "/inc/top.ftl" />
	</div>
	<div id="hd">
		<#include "/inc/search.ftl" />
	</div>
	<aside id="aside">
		<#include "/inc/mobnav.ftl" />
	</aside>

	<div id="ban_user">
		<div class="wp">
			<div class="user_avatar">
				<img src="${user.avatar!'/lib/images/avatar.jpg'}" id="avatar" alt="">
				<div class="txt">
					<h4>${nickname}</h4>
					<a href="" class="a-card"></a>
				</div>
			</div>
			<ul class="tab-user">
				<li><a href="index">个人中心</a></li>
                <li><a href="zuopin">作品</a></li>
                <li><a href="tasklist">交易</a></li>
                <li class="on"><a href="shezhi">账号设置</a></li>
                <li><a href="/designer/u${user.id}">我的主页</a></li>
			</ul>
		</div>
	</div>

	<div id="bd">
		<div class="wp bgf">
			<form id="shezhi-form">
			<h3 class="tz-tit4">账号设置</h3>
			<div class="accountSet">
				<h4 class="sec-tit">基本信息</h4>
				<div class="sec-con">
					<div class="avatarBox">
						<img id="avatar2" src="${user.avatar!'/lib/images/avatar.jpg'}" alt="">
						<div class="txt">
							<div style="padding:20px 0px;" id="avatarPicker">修改头像</div>
							<p>
								（修改成功后自动保存）<br>
								格式：jpg, png <br>
								体积：小于1M <br>
								尺寸：推荐260 x 260 的正方形
							</p>
						</div>
					</div>
					<table class="tz-table2">
						<tr>
							<th><span class="red">*</span> 昵称：</th>
							<td><input type="text" class="inp" value="${user.nickname!}" name="nickname" /></td>
						</tr>
                        <tr>
							<th>&nbsp; 类别：</th>
							<td>
								<label><input type="radio" name="type" value="1" <#if type==1>checked="checked"</#if> />个人</label>
								<label><input type="radio" name="type" value="2" <#if type==2>checked="checked"</#if> />公司/设计室</label>
							</td>
						</tr>
						<tr id="trGender">
							<th>&nbsp; 性别：</th>
							<td>
								<label><input type="radio" name="gender" value="1" <#if user.gender==1>checked="checked"</#if> />男</label>
								<label><input type="radio" name="gender" value="2" <#if user.gender==2>checked="checked"</#if> />女</label>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span> 手机：</th>
							<td><input type="text" class="inp inp2" value="${mobile2}" name="mobile2" />&nbsp;
                                <input type="checkbox" name="mobile2pub"  />在个人主页显示
							</td>
						</tr>
						<tr>
							<th>&nbsp; QQ：</th>
							<td><input type="text" class="inp inp2" value="${user.im!}" name="im" /></td>
						</tr>
						<tr>
							<th>&nbsp; 工作年限：</th>
							<td><input type="text" class="inp inp3" value="${user.workYear!}" name="workyear" />年经验</td>
						</tr>
						<tr>
							<th><span class="red">*</span> 工作地点：</th>
							<td>
								<select id="sp" name="province" class="inp">
                                    <option value="0">请选择</option>
                                    <#list provList as city >
                                    <option value='${city.id}'>${city.province}</option>
                                    </#list>
								</select>
								<select id="sc" name="city" class="inp">
                                    <option value="0">请选择</option>
                                    <#list cityList as city >
                                    <option value='${city.id}'>${city.city}</option>
                                    </#list>
								</select>
							</td>
						</tr>
						<tr>
							<th>&nbsp; 工作单位：</th>
							<td><input type="text" class="inp" value="${user.company!}" name="company" /></td>
						</tr>
						<tr id="trEducation">
							<th>&nbsp; 毕业院校：</th>
							<td><input type="text" class="inp" value="${user.education!}" name="education" /></td>
						</tr>
						<tr id="trSpecial">
							<th><span class="red">*</span> 个人擅长：</th>
							<td><input type="text" class="inp" name="special" value="${user.special!}"/></td>
						</tr>
                        <tr id="trDescr">
                            <th>业绩描述：</th>
							<td><input type="text" class="inp" name="teamdescr" value="${user.teamDescr!}" /></td>
                        </tr>
                        <tr id="trSpecial2">
							<th><span class="red">*</span> 团队擅长：</th>
							<td><input type="text" class="inp" name="special2" value="${user.special!}" /></td>
						</tr>
                        <tr>
							<th><span class="red">*</span> 设计费：</th>
							<td><input type="text" class="inp" name="startingprice"  value="${user.startingPrice}" />元/起</td>
						</tr>
					</table>
				</div>
			</div>

			<div class="accountSet">
				<h4 class="sec-tit">个人简介</h4>
				<div class="sec-con">
					<table class="tz-table2">
						<tr>
							<th><span class="red">*</span> 关于我：</th>
							<td><textarea class="inp" name="intro">${user.intro!}</textarea></td>
						</tr>
					</table>
				</div>
			</div>

			<div class="accountSet">
				<h4 class="sec-tit">个人特长</h4>
				<div class="sec-con">
                    <#list cateList as cate>
                    <div class="cate"><label><input type="checkbox" name="usercate" value="${cate.id}" <#if cate.selected>checked="checkbox"</#if> />${cate.name}</label></div>
                    <#if cate_index % 4 == 3><br/></#if>
                    </#list>
				</div>
			</div>

			<div class="accountSet">
				<h4 class="sec-tit">密码修改</h4>
				<div class="sec-con">
					<table class="tz-table2">
						<tr>
							<th>输入当前密码：</th>
							<td><input type="password" class="inp" value="" name="oldpasswd"/></td>
						</tr>
						<tr>
							<th>输入新密码：</th>
							<td>
								<input type="password" class="inp" value="" name="newpasswd"/>
								<div class="levelBar2"><span class="on">弱</span><span>中</span><span>强</span></div>
							</td>
						</tr>
						<tr>
							<th>确认新密码：</th>
							<td><input type="password" class="inp" value="" name="newpasswd2" /></td>
						</tr>
					</table>
				</div>
			</div>

			<div class="accountSet-foot">
				<input type="submit" value="保存修改" class="g-btn3 ani" />
			</div>
			</form>
		</div>
	</div>

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
	        $("input[name='type']").click(function () {
	            var type = $("input[name='type']:checked").val();
                changeUI(type);
	        });

	        function changeUI(type) {
	            if (type == 1) {
	                $("#trGender").css('display', 'table-row');
	                $("#trEducation").css('display', 'table-row');
	                $("#trSpecial").css('display', 'table-row');
	                $("#trDescr").css('display', 'none');
	                $("#trSpecial2").css('display', 'none');
	            } else if (type == 2) {
	                $("#trGender").css('display', 'none');
	                $("#trEducation").css('display', 'none');
	                $("#trSpecial").css('display', 'none');
	                $("#trDescr").css('display', 'table-row');
	                $("#trSpecial2").css('display', 'table-row');
	            }
	        }

	        changeUI(${user.type});

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