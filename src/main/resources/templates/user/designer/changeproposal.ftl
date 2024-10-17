<#--
  云联创威客系统
  Copyright 2015 云联创科技

  提案修改页
  author: Billy Zhang (billy_zh@126.com)
-->
<#import "/layout/dialog.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body class="bg8">

	<!-- 修改上传方案-弹出 -->
	<div id="changeBox" class="fancyBox">
		<a href="javascript:void(0);" onclick="parent.closeDialog(0)" class="fancyClose"></a>
		<div class="draft-con">
			<form id="uploadForm">
			<div class="draftBox">
			    <div class="t1">
			        <p>保证作品是由竞标者本人创作或参与创作，确认作品原创性。</p>
			        <p>遵守投标规范，提交无效稿件，将接受平台相应处罚。</p>
			    </div>
			    
			    <div class="tdbox">
			    	<h4>上传作品图片</h4>
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

                        <#list fileList as file >
                        <li>
                            <div class="li-con">
                                <div class="file-con">
                                    <div class="pic" id="pic" runat="server">
                                        <label class="n-file">
                                            <input type="file"></label>
                                        <a href="javascript:void(0);" class="jsDele"></a>
                                    </div>
                                    <div class="txt">
                                        <input type="hidden" id="key" name="key" value='${file.key}' />
                                        <input type="hidden" id="img" value='/task/${file.url}' />
                                        <textarea id="descr" name="descr" placeholder="请输入描述...">${file.description!}</textarea>
                                    </div>
                                </div>
                            </div>
                        </li>
                        </#list>

			        </ul>
			        <!-- 添加代码 -->
			        <div class="emptyLi">
			            <li>
			                <div class="li-con">
			                    <div class="file-con">
			                        <div class="pic">
			                            <label class="n-file"><input type="file"></label>
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

			        <div class="c"></div>
			        <h4>设计理念</h4>
			        <textarea id="descr2" name="descr2" placeholder="请填写设计说明（15到300字）"></textarea>
			        <div class="d-btn1">
			            <label for="s-ck1"><input id="hidden" name="hidden" type="checkbox" class="tz-radio">交稿隐藏</label>
			        </div>
			        <a class="d-btn2 h-btn" href="" id="submit">
			            <span></span>
			            确认修改
			        </a>
			        <div class="c"></div>
			    </div>
			</div>
                <input type="hidden" name="projectid" value="${project.id }" />
			</form>
		</div>
	</div>

</@layout.body>
<@layout.foot>

	<script>
	    $(document).ready(function ($) {

	        $('#descr2').val('${project.proposalDescribe}');
	        $('#hidden').val(${project.proposalHidden});

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
	                        url:"/task/proposal/upload", 
	                        type: "POST",
	                        data:{ img: this.result, name: files[0].name }, 
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
                                    myavatar.parents('li').find('input[id=\'key\']').val(ret.key);
                                    myavatar.parents('li').find('input[id=\'img\']').val(ret.img);
                                } else {
                                    alert('上传失败。');
                                }

                                showProgress(101);
                            }
                        });
                    }

                    reader.readAsDataURL(files[0]);
	            }
	        });

	        $('body').on('click', '.jsDele', function () {
	            var li = $(this).parents('li');
	            var key = li.find('input[id=\'key\']').val();
	            // 删除
	            $.post("/task/proposal_delpic", { key:key }, null, 'json');
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

	        $('#submit').click(function() {
	            var descr2 = $("#descr2").val();
	            if (descr2 == "") {
	                alert("请输入描述。");
	                return;
	            }

	            var formData = $("#uploadForm").serialize();
	            $.ajax({
	                type:'post',
	                url:'/task/proposal_update?projectid=${project.id}',
                    data:formData,
                    cache:false,
                    dataType:'json',
                    success:function (ret) {
                        if (ret.data == 1) {
                            alert('提交成功。');
                            parent.closeDialog(1);
                        } else {
                            alert('提交失败，请稍后重试。');
                        }
                    }
	            });

	            return false;
            });
	    });
	</script>

</@layout.foot>