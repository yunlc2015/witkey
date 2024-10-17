<#--
  云联创威客系统
  Copyright 2015 云联创科技

  能力认证页
  author: Billy Zhang (billy_zh@126.com)
-->
<#import "/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body class="bg-eb">

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
		<div class="wp bgf">
            <div class="yt-smbox">
                <div class="tit">
                    <ul class="tit-ul TAB_CLICK" id=".tab-table">
                        <li class="on"><a href="javascript:;">实力认证</a></li>
                    </ul>
                    <div class="tit-r">
                        <a href="/user/designer/index">< 返回个人中心</a>
                    </div>
                </div>
                
    			<form id="form1" onsubmit="return doSubmit()">
                    <div class="tab-table"> 
                        <div class="accountSet">
                            <div class="sec-con">
                                <table class="tz-table2">
                                    <tr>
                                        <th><em>*</em> 作品链接：</th>
                                        <td><input type="text" class="inp inp-w" id="link" name="link">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><em>*</em> 申请说明：</th>
                                        <td>
                                            <textarea class="inp tex-box" id="describe" name="describe"></textarea>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
        			<div class="accountSet-foot">
        				<input type="submit" value="提交审核" class="g-btn3 y-btn1 ani">
        			</div>
    			</form>
            </div>
		</div>
	</div>

</@layout.body>
<@layout.foot>
    
    <script>
        function doSubmit() {

            if ($("#link").val() == '') {
                alert('请输入链接地址。');
                $("#link").focus();
                return false;
            }

            if ($("#describe").val() == '') {
                alert('请输入说明。');
                $("#describe").focus();
                return false;
            }

            $.ajax({
                type: 'post',
                url: 'abilityauth',
                data: $("#form1").serialize(),
                cache: false,
                dataType: 'json',
                success: function (ret) {
                    if (ret.status == 1) {
                        alert('提交成功，请等待审核。');
                        location.href = '/user/designer/index';
                    } else {
                        alert('提交失败，请稍后重试。');
                    }
                }
            });

            return false;
        }
    </script>

</@layout.foot>
