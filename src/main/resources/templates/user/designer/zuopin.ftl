<#--
  云联创威客系统
  Copyright 2015 云联创科技

  我的作品页
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
    <aside id="aside">
        <#include "/inc/mobnav.ftl" />
    </aside>

    <div id="ban_user">
        <div class="wp">
            <div class="user_avatar">
                <img src="${user.avatar!'/lib/images/avatar.jpg'}" id="avatar" alt="">
                <div class="txt">
                    <h4>${user.nickname}</h4>
                    <a href="" class="a-card"></a>
                </div>
            </div>
            <ul class="tab-user">
                <li><a href="index">个人中心</a></li>
                <li class="on"><a href="zuopin">作品</a></li>
                <li><a href="tasklist">交易</a></li>
                <li><a href="shezhi">账号设置</a></li>
                <li><a href="/designer/u${user.id}">我的主页</a></li>
            </ul>
        </div>
    </div>

    <div id="bd">
        <div class="wp">
            <div class="picBox-bar">
                <span>目前已被点赞：${totalLike}次</span>
                <div class="inp-work">
                    <a href="javascript:void(0);" class="h-btn jsBtn"><span></span><em>上传作品</em></a>
                </div>
            </div>

            <#if zuopinList?size gt 0>
            <div class="y-picBox" id="picBox">
                <div class="bd">
                    <ul class="m-list3 y-bdpic">
                        <#list zuopinList as zuopin>
                        <li>
                            <div class="con">
                                <a href="zuopinedit?id=${zuopin.id}" class="sign zp_edit">编 辑</a>
                                <div class="pic">
                                    <a href="/zuopin/${zuopin.id}" target="_blank">
                                        <img src="/user/${zuopin.cover}" alt=""></a>
                                </div>
                                <div class="bot">
                                    <div class="txt">
                                        <h3><a href="/zuopin/${zuopin.id}">${zuopin.title}</a></h3>
                                        <div class="txt-j">
                                            <span class="price">￥${zuopin.price}</span>
                                            <div class="r">
                                                <p class="zan"><a href="javascript:;">点赞 (${zuopin.likeCount})</a></p>
                                                <a href="javascript:;" onClick="return confirm('确定要删除此作品吗？');" class="dustbin"></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                        </#list>
                    </ul>
                </div>
            </div>
            <#else>
            <div class="no-data-box" id="noDataBox">
                <img src="/lib/images/blank.png" alt="">
                <p class="no-data-tips">您的作品页空空如也，被匹配到的几率≈0%</p>
            </div>
            </#if>

            <div class="uploadBox JsPop">
                <form id="uploadForm">
                    <div class="con">
                        <div class="tit">
                            <ul>
                                <li class="on">
                                    <b>1</b>上传作品
                                </li>
                                <li>
                                    <b>2</b>设置封面
                                </li>
                                <li>
                                    <b>3</b>添加描述
                                </li>
                            </ul>
                            <a href="javascript:void(0);" class="close"></a>
                        </div>
                        <div class="upload-con">
                            <!-- 上传作品 -->
                            <div class="bd">
                                <div class="bd-con">
                                    <ul class="JsPara">
                                        <li>
                                            <div class="li-con JsAdd">
                                                <div class="japic">
                                                    <em></em>
                                                    <strong>添加图片</strong>
                                                    <p>JPEG/GIF/PNG，大小不超过2M尺寸：推荐宽度800 像素的图片</p>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>

                                    <!-- 添加代码 -->
                                    <div class="emptyLi">
                                        <li>
                                            <div class="li-con">
                                                <div class="file-con">
                                                    <div class="pic">
                                                        <label class="n-file">
                                                            <input type="file"></label>
                                                        <a href="javascript:void(0);" class="jsDele"></a>
                                                    </div>
                                                    <div class="txt">
                                                        <input type="hidden" id="key" name="key" value="" />
                                                        <input type="hidden" id="img" value="" />
                                                        <textarea id="descr" name="descr" placeholder="请输入描述..."></textarea>
                                                    </div>
                                                </div>
                                                <div class="percent"><span><em class="JsCount">0</em>%</span></div>
                                            </div>
                                        </li>
                                    </div>
                                    <!-- 添加代码 end -->
                                </div>
                                <div class="c"></div>
                                <div class="Btns">
                                    <input type="hidden" class="step" value="1" />
                                    <a href="javascript:void(0);" class="next-btn h-btn jsNext">
                                        <span></span>
                                        下一步
                                    </a>
                                </div>
                            </div>
                            <!-- 上传作品 end -->

                            <!-- 设置封面 -->
                            <div class="bd hide">
                                <div class="bd-con">
                                    <div class="cropCon">
                                        <div class="bigPic">
                                            <img class="img-responsive" id="image" src="/lib/images/y-pic5.png" alt="Picture">
                                        </div>
                                        <div class="smallPic">
                                            <div class="preview"></div>
                                        </div>
                                    </div>

                                    <div id="ifocus-btn">
                                        <div class="slides"><span>
                                            <img src="#"></span></div>
                                        <div class="slides"><span>
                                            <img src="#"></span></div>
                                        <div class="slides"><span>
                                            <img src="#"></span></div>
                                        <div class="slides"><span>
                                            <img src="#"></span></div>
                                        <div class="slides"><span>
                                            <img src="#"></span></div>
                                        <div class="slides"><span>
                                            <img src="#"></span></div>
                                        <div class="slides"><span>
                                            <img src="#"></span></div>
                                        <div class="slides"><span>
                                            <img src="#"></span></div>
                                        <div class="slides"><span>
                                            <img src="#"></span></div>
                                        <div class="slides"><span>
                                            <img src="#"></span></div>
                                    </div>
                                </div>

                                <div class="c"></div>
                                <div class="Btns">
                                    <input type="hidden" class="step" value="2" />
                                    <a href="javascript:void(0);" class="next-btn h-btn pre-btn jsPrev">
                                        <span></span>
                                        上一步
                                    </a>
                                    <a href="javascript:void(0);" class="next-btn h-btn jsNext">
                                        <span></span>
                                        下一步
                                    </a>
                                </div>
                            </div>
                            <!-- 设置封面 end -->

                            <!-- 添加描述 -->
                            <div class="bd hide">
                                <div class="bd-con">
                                    <div class="tabel">
                                        <table>
                                            <tr>
                                                <th>属性：</th>
                                                <td><span>原创</span>（设计师上传作品必须为原创）</td>
                                            </tr>
                                            <tr>
                                                <th>标题：</th>
                                                <td>
                                                    <input type="text" id="title" name="title" value="标题" onfocus="if (value =='标题'){value =''}" onblur="if (value ==''){value='标题'}"></td>
                                            </tr>
                                            <tr>
                                                <th>类目：</th>
                                                <td>
                                                    <select id="cate1" name="cate1" style="width:200px">
                                                        <option value="0">请选择类目</option>
                                                        <#list cateList as cate>
                                                        <option value='${cate.id}'>${cate.name}</option>
                                                        </#list>
                                                    </select>
                                                    <select id="cate2" name="cate2">
                                                        <option value="0">请选择类目</option>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>价格：</th>
                                                <td>
                                                    <input type="text" name="price" id="price" placeholder="价格"></td>
                                            </tr>
                                            <tr>
                                                <th>描述：</th>
                                                <td>
                                                    <textarea id="descr2" name="descr2" placeholder="描述"></textarea>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div class="c"></div>
                                <div class="Btns">
                                    <a href="javascript:void(0);" class="next-btn h-btn pre-btn jsPrev">
                                        <span></span>
                                        上一步
                                    </a>
                                    <a href="javascript:void(0);" class="next-btn h-btn jsOk">
                                        <span></span>
                                        完成
                                    </a>
                                </div>
                            </div>
                            <!-- 添加描述 end -->
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</@layout.body>
<@layout.foot>
    
    <!-- 设置封面 -->
    <link rel="stylesheet" href="/lib/css/cropper.css">
    <script src="/lib/js/cropper.js"></script>
     <style>
        .preview .cropper-hidden{display: block !important;}
    </style>
    <script>
        $(document).ready(function () {
            function myCrop(){
                var $previews = $('.preview');

                $('#image').cropper({
                    aspectRatio: 25 / 18,
                    build: function (e) {
                        var $clone = $(this).clone();

                        $clone.css({
                            display: 'block',
                            width: '100%',
                            minWidth: 0,
                            minHeight: 0,
                            maxWidth: 'none',
                            maxHeight: 'none'
                        });

                        $previews.css({
                            width: '100%',
                            overflow: 'hidden'
                        }).html($clone);
                    },
                    crop: function (e) {
                        var imageData = $(this).cropper('getImageData');
                        var previewAspectRatio = e.width / e.height;

                        $previews.each(function () {
                            var $preview = $(this);
                            var previewWidth = $preview.width();
                            var previewHeight = previewWidth / previewAspectRatio;
                            var imageScaledRatio = e.width / previewWidth;

                            $preview.height(previewHeight).find('img').css({
                                width: imageData.naturalWidth / imageScaledRatio,
                                height: imageData.naturalHeight / imageScaledRatio,
                                marginLeft: -e.x / imageScaledRatio,
                                marginTop: -e.y / imageScaledRatio
                            });
                        });
                    }
                });
            };
            myCrop();

            $('#ifocus-btn .slides').click(function(){
                var img = $(this).find('img');
                var _src = img.attr('src');
                $('#image').cropper('replace', _src);
            });

            $('.uploadBox .tit .close').click(function () {
                $(this).parents('.uploadBox').css('top', -9999999);;
            });
            $('.jsBtn').click(function () {
                if (!window.FileReader) {
                    alert("Not supported by your browser!");
                    return;
                }

                // 初始化服务程序.
                $.post("zuopinadd_init", {  }, null, 'json');

                $('.JsPop').css({
                    'top': $(window).scrollTop() + $(window).height() / 2 - $('.JsPop').height() / 2
                });
            });
            $('.jsNext').click(function () {
                if ($(this).parents('.uploadBox .bd').next('.bd').length) {
                    var _eq = $('.uploadBox .bd').index($(this).parents('.uploadBox .bd').next('.bd'));
                    $('.uploadBox .tit li').eq(_eq).addClass('on').siblings('li').removeClass('on');
                    $(this).parents('.uploadBox .bd').next('.bd').removeClass('hide');
                    $(this).parents('.uploadBox .bd').addClass('hide');

                    var step = $(this).parents('.uploadBox .bd').find('.step').val();
                    if(step==1) {  //upload
                        var _bd = $(this).parent('.uploadBox .bd').next('.bd');

                        var firstSrc = '';
                        var n = 0;
                        var imgArr = new Array();

                        $('input[id=\'img\']').each(function() {
                            var img = $(this).val();
                            if (img != '') {
                                imgArr.push(img);
                                if (n == 0) {
                                    firstSrc = img;
                                }
                                n++;
                            }
                        });

                        m = 0;
                        $('#ifocus-btn .slides').each(function() {
                            if (m < n) {
                                $(this).show();
                                $(this).find('img').attr('src', imgArr[m]);
                                m++;
                            } else {
                                $(this).hide();
                            }
                        });

                        $('#image').cropper('replace', firstSrc);
                    } else if (step == 2) { //cover
                        var result = $('#image').cropper('getCroppedCanvas').toDataURL();
                        $.post('/user/zuopin/uploadcover', {img:result}, null, 'json');
                    }
                }
            });
            
            $('.jsPrev').click(function () {
                var _eq = $('.uploadBox .bd').index( $(this).parents('.uploadBox .bd').prev('.bd') );
                $('.uploadBox .tit li').eq(_eq).addClass('on').siblings('li').removeClass('on');
                $(this).parents('.uploadBox .bd').prev('.bd').removeClass('hide');
                $(this).parents('.uploadBox .bd').addClass('hide');
            });

            $('.jsOk').click(function() { 
                //submit
                var title = $("#title").val();
                if (title == "") {
                    alert("请输入标题。");
                    return;
                }
                var cate1 = $("#cate1").val();
                var cate2 = $("#cate2").val();
                if (cate1 == "0" && cate2 == "0") {
                    alert("请选择分类。");
                    return;
                }
                var price = $("#price").val();
                if (price == "") {
                    alert("请输入价格。");
                    return;
                }

                $.ajax({
                    type:'post',
                    url:'zuopinadd_submit',
                    data:$("#uploadForm").serialize(),
                    cache:false,
                    dataType:'json',
                    success:function (resp) {
                        if (resp.errCode == 0) {
                            alert('发布成功。');
                            location.href = 'zuopin';
                        } else {
                            alert('发布失败，请稍后重试。');
                        }
                    }
                });
            });
        });
    </script>

    <!-- 上传作品 -->
    <script>
        $(document).ready(function ($) {
            // 上传图片
            $('.JsAdd').click(function (event) {
                var _html = $('.emptyLi').clone().html();
                $(this).parents('ul').append(_html);
                $('.JsPara li:last()').find('.n-file').trigger('click');
            });
            $('body').on('change', '.n-file :file', function () {
                var myavatar = $(this).parents('.pic');

                var files = !!this.files ? this.files : [];
                if (!files.length || !window.FileReader) return;

                var filesize = files[0].size;

                if (/^image/.test(files[0].type)) {
                    var loaded = 0;
                    
                    var reader = new FileReader();

                    reader.onloadend = function () {
                        //myavatar.css({
                        //    "background-image": "url(" + this.result + ")"
                        //});
                        //showProgress( 101 );

                        // 提交到服务程序
                        $.ajax({
                            url:"/user/zuopin/upload", 
                            type: "POST",
                            data: { img: this.result, name: files[0].name }, 
                            xhr: function() {
                                myXhr = $.ajaxSettings.xhr();
                                if (myXhr.upload) {
                                    myXhr.upload.addEventListener('progress', 
                                        function(e) {
                                            if (e.lengthComputable) {
                                                showProgress(Math.round(e.loaded * 1000 / e.total) / 10);
                                            }
                                        }, 
                                       false);
                                }
                                return myXhr;
                            },
                            success: function(ret) {
                                var json = ret;
                                if (json.key != '') {
                                    myavatar.css({
                                        "background-image": "url(" + json.img + ")"
                                    });

                                    myavatar.parents('li').find('input[id=\'key\']').val(json.key);
                                    myavatar.parents('li').find('input[id=\'img\']').val(json.img);
                                } else {
                                    alert('上传失败。');
                                }

                                showProgress(101);
                            }, 
                        });
                    }

                    reader.readAsDataURL(files[0]);
                }
            });
            $('body').on('click', '.jsDele', function () {
                var li = $(this).parents('li');
                var key = li.find('input[id=\'key\']').val();
                // 删除
                $.post("zuopinadd_delpic", { key:key }, null, 'json');
                li.remove();
            });

            function showProgress(val) {
                var _per = $('.JsPara .JsCount');
                if (val <= 100) {
                    _per.text(val);
                } else {
                    _per.parents('.percent').remove();
                }
            };

        });
    </script>

    <link rel="stylesheet" href="/lib/css/slick.css">
    <script src="/lib/js/slick.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#ifocus-btn').slick({
                dots: false,
                arrows: false,
                infinite: false,
                vertical: true,
                verticalSwiping: true,
                centerMode: true,
                centerPadding: 0,
                focusOnSelect: true,
                slidesToShow: 5
            });

        });
    </script>

    <link rel="stylesheet" href="/lib/css/colorbox.css" />
    <script src="/lib/js/jquery.colorbox.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".zp_edit").colorbox({ 
                iframe: true, 
                innerWidth: 780, 
                innerHeight: 550,
                escKey:false, 
                overlayClose:false,
                onLoad: function() {
                    $('#cboxClose').remove();
                }
            });
        });
    </script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#cate1').change(function() {
                $.ajax({
                    type: 'get',
                    url: 'getcates&cid=' + $(this).val(),
                    cache:false,
                    dataType: 'json',
                    success: function (ret) {
                        var cateArr = ret.data;
                        var optStr = "<option value=\"0\">请选择类目</option>";
                        for (var i=0; i<cateArr.length; i++) {
                            optStr += "<option value=\"" + cateArr[i].value + "\">" + cateArr[i].text + "</option>";
                        }
                        $('#cate2').html(optStr);
                    }
                });
            });
        });
    </script>
</@layout.foot>
