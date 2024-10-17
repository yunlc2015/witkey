<#--
  云联创威客系统
  Copyright 2015 云联创科技

  实名认证页
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
                        <li class="on"><a href="javascript:;">实名认证</a></li>
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
                                        <th><em>*</em> 真实姓名：</th>
                                        <td><input type="text" class="inp" id="realname" name="realname">
                                            <span>为保障您的资金安全，实名认证姓名、身份证号须和您提现的银行卡户名保持一致</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><em>*</em> 身份证号码：</th>
                                        <td>
                                            <input type="text" class="inp" id="idcard" name="idcard">
                                            <span><em>身份证号码最后一位“X”,请注意大写。年龄不足18岁，不能认证。</em></span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><em>*</em> 类别：</th>
                                        <td>
                                            <label for="sex1" class="tz-JsRadio"><input type="radio" name="category" id="cate1" value="1" checked>个人</label>
                                            <label for="sex2" class="tz-JsRadio"><input type="radio" name="category" id="cate2" value="2">公司</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><em>*</em> 性别：</th>
                                        <td>
                                            <label for="sex1"><input type="radio" name="sex" id="sex1" value="1" checked>男</label>
                                            <label for="sex2"><input type="radio" name="sex" id="sex2" value="2">女</label>                     
                                        </td>
                                    </tr>
                                </table>
                                <table class="tz-table2 tz-JsRadio-con">
                                    <tr>
                                        <th><em>*</em> 证件照片：</th>
                                        <td>
                                            <div class="photo-box">
                                                <h3>手持身份证正面照</h3>
                                                <div class="photo-left">
                                                    <label class="n-file">
                                                        <input type="file">
                                                        <div class="img-b">
                                                            
                                                        </div>
                                                        <span>点击上传</span>
                                                        <input type="hidden" class="himg" id="idcard11" name="idcard11" />
                                                    </label>
                                            
                                                    <img src="/lib/images/16-img1.jpg" alt="">
                                                </div>
                                                <div class="txt-r">
                                                    <p>1.请上传本人手持身份证正面头部照片和上半身照片</p>
                                                    <p>2.照片为免冠、未化妆的数码照片原始图片，<em>请勿用任何软件编辑修改</em></p>
                                                    <p>3.必须看清证件信息，且证件信息不能被遮挡，持证人五官清晰可见</p>
                                                    <p>4.仅支持.jpg. bmp. png. gif的图片格式。建议图片大小<em>不超过3M</em></p>
                                                    <p>5.您提供的照片信息易极付将予以保护，不会用于其他用途。</p>
                                                    <p><a href="">实名认证帮助></a></p>
                                                </div>
                                            </div>
                                            <div class="photo-box">
                                                <h3>手持身份证反面照</h3>
                                                <div class="photo-left">
                                                    <label class="n-file">
                                                        <input type="file">
                                                        <div class="img-b">
                                                            
                                                        </div>
                                                        <span>点击上传</span>
                                                        <input type="hidden" class="himg" id="idcard12" name="idcard12" />
                                                    </label>
                                            
                                                    <img src="/lib/images/16-img2.jpg" alt="">
                                                </div>
                                                <div class="txt-r">
                                                    <p>1.必须看清证件信息，且证件信息不能被遮挡</p>
                                                    <p>2.仅支持.jpg. bmp. png. gif的图片格式。建议图片大小不超过3M</p>
                                                    <p>3.您提供的照片信息易极付将予以保护，不会用于其他用途。</p>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><em>*</em> 证件到期日期：</th>
                                        <td>
                                            <label for="sex1"><input type="radio" name="duedate" id="due1" value="1" checked><input type="text" id="duedate2" name="duedate2" class="inp inp-day"></label>
                                            <label for="sex2"><input type="radio" name="duedate" id="due2" value="2">长期</label> 
                                            <span class="ce">若证件有效期为长期，请勾选长期。例如：有效期为2016.11.16-长期，则勾选为长期。</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><em>*</em> 行业：</th>
                                        <td>
                                            <select name="industry" id="industry" class="inp">
                                                <option value="">请选择您的行业</option>
                                                <option value="计算机/互联网/通信/电子">计算机/互联网/通信/电子</option>
                                                <option value="销售/客服/技术支持">销售/客服/技术支持</option>
                                                <option value="生产/运营/采购/物流">生产/运营/采购/物流</option>
                                                <option value="会计/金融/银行/保险">会计/金融/银行/保险</option>
                                                <option value="广告/市场/媒体/艺术">广告/市场/媒体/艺术</option>
                                                <option value="生物/制药/医疗/护理">生物/制药/医疗/护理</option>
                                                <option value="建筑/房地产">建筑/房地产</option>
                                                <option value="人事/行政/高级管理">人事/行政/高级管理</option>
                                                <option value="资讯/法律/教育/科研">资讯/法律/教育/科研</option>
                                                <option value="服务业">服务业</option>
                                                <option value="公务员/翻译/其他">公务员/翻译/其他</option>
                                                <option value="职员">职员</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><em>*</em> 联系手机：</th>
                                        <td><input type="text" class="inp" id="mobile1" name="mobile"></td>
                                    </tr>
                                    <tr>
                                        <th><em>*</em> 家庭或工作单位地址：</th>
                                        <td><input type="text" class="inp" id="address" name="address">
                                    </tr>
                                </table>  
                                <table class="tz-table2 tz-JsRadio-con dn" >  
                                    <tr>
                                        <th><em>*</em> 证件照片：</th>
                                        <td>
                                            <div class="photo-box">
                                                <h3>手持身份证正面照</h3>
                                                <div class="photo-left">
                                                    <label class="n-file">
                                                        <input type="file">
                                                        <div class="img-b">
                                                            
                                                        </div>
                                                        <span>点击上传</span>
                                                        <input type="hidden" class="himg" id="idcard21" name="idcard21" />
                                                    </label>

                                                    <img src="/images/16-img1.jpg" alt="">
                                                </div>
                                                <div class="txt-r">
                                                    <p>1.请上传本人手持身份证正面头部照片和上半身照片</p>
                                                    <p>2.照片为免冠、未化妆的数码照片原始图片，<em>请勿用任何软件编辑修改</em></p>
                                                    <p>3.必须看清证件信息，且证件信息不能被遮挡，持证人五官清晰可见</p>
                                                    <p>4.仅支持.jpg. bmp. png. gif的图片格式。建议图片大小<em>不超过3M</em></p>
                                                    <p>5.您提供的照片信息易极付将予以保护，不会用于其他用途。</p>
                                                    <p><a href="">实名认证帮助></a></p>
                                                </div>
                                            </div>
                                            <div class="photo-box">
                                                <h3>手持身份证反面照</h3>
                                                <div class="photo-left">
                                                    <label class="n-file">
                                                        <input type="file">
                                                        <div class="img-b">
                                                            
                                                        </div>
                                                        <span>点击上传</span>
                                                        <input type="hidden" class="himg" id="idcard22" name="idcard22" />
                                                    </label>

                                                    <img src="/images/16-img2.jpg" alt="">
                                                </div>
                                                <div class="txt-r">
                                                    <p>1.必须看清证件信息，且证件信息不能被遮挡</p>
                                                    <p>2.仅支持.jpg. bmp. png. gif的图片格式。建议图片大小不超过3M</p>
                                                    <p>3.您提供的照片信息易极付将予以保护，不会用于其他用途。</p>
                                                </div>
                                            </div>
                                            <div class="photo-box">
                                                <h3>企业营业执照正面照</h3>
                                                <div class="photo-left">
                                                    <label class="n-file">
                                                        <input type="file">
                                                        <div class="img-b">
                                                            
                                                        </div>
                                                        <span>点击上传</span>
                                                        <input type="hidden" class="himg" id="company1" name="company1" />
                                                    </label>

                                                    <img src="/images/16-img3.jpg" alt="">
                                                </div>
                                                <div class="txt-r">
                                                    <p>
                                                        1.请上传企业法人营业执照正面照片<br>
                                                        2.照片为原件原始图片，<em>请勿用任何软件编辑修改</em><br>
                                                        3.必须看清证件信息，且证件信息不能被遮挡，清晰可见<br>
                                                        4.仅支持.jpg. bmp. png. gif的图片格式。建议图片大小<em>不超过3M</em> <br>
                                                        5.您提供的照片信息易极付将予以保护，不会用于其他用途。<br>
                                                        <a href="">企业认证帮助></a>
                                                    </p>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><em>*</em> 公司名称：</th>
                                        <td>
                                            <input type="text" class="inp" id="company" name="company">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><em>*</em> 营业执照营业期限：</th>
                                        <td><input type="text" class="inp" id="licenseduedate" name="licenseduedate"></td>
                                    </tr>
                                    <tr>
                                        <th><em>*</em> 公司注册号：</th>
                                        <td>
                                            <input type="text" class="inp" id="registrationno" name="registrationno">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><em>*</em> 联系手机：</th>
                                        <td><input type="text" class="inp" id="mobile2" name="mobile" value="" ></td>
                                    </tr>
                                    <tr>
                                        <th><em>*</em> 注册单位地址：</th>
                                        <td><input type="text" class="inp" id="address2" name="address2">
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
        $(document).ready(function () {
            $('.tz-JsRadio').click(function () {
                var _eq = $(this).index();
                $('.tz-JsRadio-con').eq(_eq).show().siblings('.tz-JsRadio-con').hide();
            });
        });

        $('#mobile1').val('${user.mobile}');
        $('#mobile2').val('${user.mobile}');

        $('body').on('change', '.n-file :file', function () {
            var thumb = $(this).parent().find('.img-b');

            var files = !!this.files ? this.files : [];
            if (!files.length || !window.FileReader) return;

            var filesize = files[0].size;

            if (/^image/.test(files[0].type)) {
                var loaded = 0;
                    
                var reader = new FileReader();

                reader.onloadend = function () {
                    thumb.css({
                        "background-size": "cover",
                        "background-image": "url(" + this.result + ")"
                    });
                    //showProgress( 101 );

                    // 提交到服务程序
                    $.post("/user/authent/upload", 
                        { img: this.result, name: files[0].name }, 
                        function(ret) {
                            if (ret.img != '') {
                                thumb.parent().find('.himg').val(ret.img);
                            } else {
                                alert('上传失败。');
                            }
                        }, 
                     'json');
                }

                reader.onloadstart = function () {
                    //showProgress( 0 );
                }

                reader.onprogress = function (e) {
                    loaded += e.loaded;
                    //showProgress( Math.round((loaded / filesize) * 100) );
                }

                reader.readAsDataURL(files[0]);
            }
        });

        function doSubmit() {
            
            if ($("#realname").val() == '') {
                alert('请输入姓名。');
                $("#realname").focus();
                return false;
            }

            if ($("#idcard").val() == '') {
                alert('请输入身份证。');
                $("#idcard").focus();
                return false;
            }

            var category = $("input[name='category']:checked").val();
            if (category == 1) {
                if ($("#idcard11").val() == '') {
                    alert('请上传身份证正面照。');
                    return false;
                }
                if ($("#idcard12").val() == '') {
                    alert('请上传身份证反面照。');
                    return false;
                }
                var duedate = $("input[name='duedate']:checked").val();
                if (duedate == 1) {
                    if ($("#duedate2").val() == '') {
                        alert('请输入身份证截止日期。');
                        return false;
                    }
                }

                if ($("#industry").val() == '') {
                    alert('请选择行业。');
                    $("#industry").focus();
                    return false;
                }

                if ($("#mobile1").val() == '') {
                    alert('请输入联系手机。');
                    $("#address").focus();
                    return false;
                }

                if ($("#address").val() == '') {
                    alert('请输入地址。');
                    $("#address").focus();
                    return false;
                }

            } else {
                if ($("#idcard21").val() == '') {
                    alert('请上传身份证正面照。');
                    return false;
                }
                if ($("#idcard22").val() == '') {
                    alert('请上传身份证反面照。');
                    return false;
                }
                if ($("#company1").val() == '') {
                    alert('请上传营业执照正面照。');
                    return false;
                }
                if ($("#company").val() == '') {
                    alert('请输入公司名称。');
                    $("#company").focus();
                    return false;
                }
                if ($("#licenseduedate").val() == '') {
                    alert('请输入营业执照营业期限。');
                    $("#licenseduedate").focus();
                    return false;
                }
                if ($("#registrationno").val() == '') {
                    alert('请输入公司注册号。');
                    $("#registrationno").focus();
                    return false;
                }
                if ($("#address2").val() == '') {
                    alert('请输入公司注册地址。');
                    $("#address2").focus();
                    return false;
                }
                if ($("#mobile2").val() == '') {
                    alert('请输入联系手机。');
                    $("#address").focus();
                    return false;
                }
            }

            $.ajax({
                type:'post',
                url:'realauth',
                data:$("#form1").serialize(),
                cache:false,
                dataType:'json',
                success:function (resp) {
                    if (resp.data == 1) {
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
