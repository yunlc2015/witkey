<#--
  云联创威客系统
  Copyright 2015 云联创科技

  任务详情页
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
    <div id="header">
        <@mobHeader current="task" />
    </div>
    <aside id="aside">
        <#include "/inc/mobnav.ftl" />
    </aside>
    <div id="nv">
        <@webNav current="task" />
    </div>

    <div id="ban">
        <div class="slider">
            <#list bannerList as banner>
            <div class="slide" style="background-image: url(${banner.imageUrl!'/lib/images/ban.jpg'});">
                <a href="${banner.targetUrl!}"><div class="wp"></div></a>
            </div>
            </#list>
        </div>
    </div>

    <div id="bd">
        <div class="wp">
            <div class="g-tit1">
                <h3>任务大厅>LOGO设计</h3>
            </div>
            <div class="y-con1">
                <a href="" class="img">
                    <img src="${user.avatar!'/lib/images/y-pic1.png'}" width="120" alt=""></a>
                <div class="c1-txt">
                    <h3><span>${user.nickname}：</span>${task.requirement}</h3>
                    <div class="t2">
                        <p><span>设计费：</span><strong>￥${task.designAmount}</strong> <em class="f-btn">已付款</em></p>
                        <p><span>项目周期：</span>${task.hopeDays}天</p>
                        <p><span>奖金分配比例：</span><#if task.service==2>中标80%，参与20%。<#else>中标100%</#if></p>
                    </div>
                    <div class="t3">
                        <p>剩余时间：<span>${task.dueTimeText}</p>
                        <ul>
                            <li>按时完成</li>
                            <li>保证原创</li>
                            <li>实名认证</li>
                            <#if task.service==2 >
                                <li>官方认证</li>
                                <li>管家服务</li>
                            </#if>
                        </ul>
                        <div class="c"></div>
                    </div>
                </div>
            </div>
            <div class="num-list">
                <ul>
                    <li class="ok" id="li1">
                        <em class="cir">1</em>
                        <h4>发布需求，托管赏金</h4>
                        <div class="line">
                            <div class="line-on" id="div1">
                                <span></span>
                            </div>
                        </div>
                    </li>
                    <li <#if taskStep?size gt 1>class="${taskStep[1]}"</#if> id="li2" >
                        <em class="cir">2</em>
                        <h4>服务商交稿</h4>
                        <div class="line">
                            <#if taskStep?size gt 1 && taskStep[1]=="ok" >
                            <div class="line-on" id="div2">
                                <span></span>
                            </div>
                            </#if>
                        </div>
                    </li>
                    <li <#if taskStep?size gt 2>class="${taskStep[2]}"</#if> id="li3">
                        <em class="cir">3</em>
                        <h4>雇主选稿</h4>
                        <div class="line">
                            <#if taskStep?size gt 2 && taskStep[2]=="ok" >
                            <div class="line-on" id="div3">
                                <span></span>
                            </div>
                            </#if>
                        </div>
                    </li>
                    <li <#if taskStep?size gt 3>class="${taskStep[3]}"</#if> id="li4">
                        <div class="cir">4</div>
                        <h4>中标公示</h4>
                        <div class="line">
                            <#if taskStep?size gt 3 && taskStep[3]=="ok" >
                            <div class="line-on" id="div4">
                                <span></span>
                            </div>
                            </#if>
                        </div>
                    </li>
                    <li <#if taskStep?size gt 4>class="${taskStep[4]}"</#if> id="li5">
                        <div class="cir">5</div>
                        <h4>验收并付款</h4>
                        <div class="line">
                            <#if taskStep?size gt 4 && taskStep[4]=="ok" >
                            <div class="line-on" id="div5">
                                <span></span>
                            </div>
                            </#if>
                        </div>
                    </li>
                    <li <#if taskStep?size gt 5>class="${taskStep[5]}"</#if> id="li6">
                        <div class="cir">6</div>
                        <h4>评价</h4>
                        <div class="line">
                            <#if taskStep?size gt 5 && taskStep[5]=="ok" >
                            <div class="line-on" id="div6">
                                <span></span>
                            </div>
                            </#if>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="TxtBox">
                <div class="txt-list txt-list1">
                    <p><span>订单编号：</span>${tradeNo}</p>
                    <p><span>需求类目：</span>${cateName!}</p>
                    <p><span>工作量：</span>${task.designContent}&nbsp;&nbsp;
                        <span>完成时间：${task.hopeDays}天
                    </p>
                    <br>
                    <p><span>具体要求：</span></p>
                    <p>${task.designDetails}</p>
                </div>
                <div class="txt-list txt-list2">
                    <h3>附件:</h3>
                    <ul>
                        <#list fileList as file>
                        <li>
                            <a id="hlnk" runat="server">
                                <img style="height: 60px; border: 0px;" src="/images/16-img5.jpg" id="img" runat="server" alt=""></a></li>

                        </#list>
                    </ul>
                </div>
                <div class="c"></div>
                <div class="txt-list">
                    <h3>交稿说明：</h3>
                    <p>1、交稿作品一经采用，其所有权、修改权和使用权均归客户所有，设计者不得再用其它商业行为；</p>
                    <p>2、参与者所提交的作品必须由参赛者本人创作或参与创作，参赛者应确认其作品的原创性，平台不承担因作品侵犯他人（或单位）的权利而产生的法律责任，其法律责任由参赛者本人承担。</p>
                    <br>
                    <div class="cred">
                        <h3>温馨提示：</h3>
                        <p>请将您的作品进行全面的展示，您将有更多机会获得客户认可，作品被客户认可后，请主动与客户取得联系，提供完美创意服务，在此预祝您马到成功。</p>
                    </div>
                </div>
            </div>
            <div class="draft-con" style="display:none;">
                <h3>我要交稿</h3>
                <div class="draftBox">
                    <form id="uploadForm">
                        <div class="t1">
                            <p>保证作品是由竞标者本人创作或参与创作，确认作品原创性。</p>
                            <p>遵守投标规范，提交无效稿件，将受到平台相应处罚。</p>
                        </div>

                        <div class="tdbox">
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
                                                <input type="hidden" id="file" value="" />
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
                                <label for="s-ck1">
                                    <input type="checkbox" id="hidden" name="hidden" value="1" class="tz-radio">交稿隐藏</label>
                            </div>
                            <a class="d-btn2 h-btn" id="submit">
                                <span></span>
                                确认交稿
                            </a>
                            <div class="c"></div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="btns">
            <#if taskTodo=="accept" >
                <button class="h-btn" id="accept" ><span></span>确认抢标</button></#if>
            <#if taskTodo=="upload" >    
                <button class="h-btn btn-hv2" id="upload" ><span></span>上传提案</button></#if>
            <#if taskTodo=="waiting" >
                <button class="h-btn btn-hv2" id="waiting" ><span></span>等待选稿</button></#if>
            <#if taskTodo=="finished" >
                <button class="h-btn btn-hv3" id="finished" ><span></span>交易完成</button></#if>
            </div>
            <div class="draft-list">
                <div class="tit">
                    <h3>交稿</h3>
                    <div class="tab">
                        <ul>
                            <li>
                                <a id="ht1">全部</a></li>
                            <li>
                                <a id="ht2">中标(${projectTotal["selectedCount"]})</a></li>
                            <li>
                                <a id="ht3">已交稿(${projectTotal["submittedCount"]})</a></li>
                            <li>
                                <a id="ht4">未交稿(${projectTotal["waitingCount"]})</a></li>
                        </ul>
                    </div>
                </div>
                <div id="proposals">
                </div>
            </div>
        </div>
    </div>

</@layout.body>
<@layout.foot>

    <link rel="stylesheet" href="/lib/css/slick.css">
    <script src="/lib/js/slick.min.js"></script>
    <script>
        $(document).ready(function ($) {
            // ban滚动
            $('#ban .slider').slick({
                dots: true,
                arrows: false,
                autoplay: true,
                slidesToShow: 1,
                autoplaySpeed: 5000,
                pauseOnHover: false,
                lazyLoad: 'ondemand'
            });

            var once = 0;

            $('#accept').click(function () {
                
                <#if !auth.logon >
                    alert('请先登录。');
                    return;
                <#else >
                    var uid = ${auth.userId};
                </#if>

                if (once == 0) {
                    once = 1;

                    var options = {
                        url: 'accept',
                        type: 'post',
                        dataType: 'json',
                        data: {taskid:${task.id}},
                        success: function (resp) {
                            once = 0;

                            if (resp.data == 1) {
                                alert("抢标成功。");
                                location.href = 't${task.id}';
                            } else if (resp.data == 2) {
                                alert("已经抢标了。");
                            } else {
                                if (resp.errMsg) {
                                    alert(resp.errMsg);
                                } else {
                                    alert("未知错误。");
                                }
                            }
                        }
                    };
                    $.ajax(options);
                }
            });

            $('#upload').click(function() {
                $('.draft-con').show();

            });

            <#if !auth.logon >
            // 初始化服务程序.
            $.post("proposal_init", {  }, null, 'json');
            </#if>

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
                        // 提交到服务程序
                        $.ajax({
                            url:"proposal/upload", 
                            type: "POST",
                            data:{ file: this.result, name: files[0].name }, 
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
                                        "background-image": "url(" + json.url + ")"
                                    });
                                    myavatar.parents('li').find('input[id=\'key\']').val(ret.key);
                                    myavatar.parents('li').find('input[id=\'file\']').val(ret.url);
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
                $.post("proposal_delpic", { key:key }, null, 'json');
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

                $.ajax({
                    type:'post',
                    url:'proposal_submit?taskid=${task.id}',
                    data:$("#uploadForm").serialize(),
                    cache:false,
                    dataType:'json',
                    success:function (resp) {
                        if (resp.data == 1) {
                            alert('提交成功。');
                            location.href = 't${task.id}';
                        } else {
                            alert('提交失败，请稍后重试。');
                        }
                    }
                });
            });

            $('#ht1').click(function() {getProposals(${task.id}, 0, 1);});
            $('#ht2').click(function() {getProposals(${task.id}, 1, 1);});
            $('#ht3').click(function() {getProposals(${task.id}, 2, 1);});
            $('#ht4').click(function() {getProposals(${task.id}, 3, 1);});

            function getProposals(tid, type, page) {
                $.get('proposals?taskid=${task.id}&type=' + type + '&page=' + page,
                    function(ret) {
                        $('#proposals').html(ret);

                        $('#proposals').find('.botScroll').slick({
                            arrows: true,
                            slidesToShow: 5,
                            slidesToScroll: 1,
                            focusOnSelect: true,
                            responsive: [
                                {
                                    breakpoint: 1024,
                                    settings: {
                                        slidesToShow: 3,
                                        slidesToScroll: 1,
                                        infinite: true
                                    }
                                },
                                {
                                    breakpoint: 767,
                                    settings: {
                                        dots: true,
                                        arrows: false,
                                        slidesToShow: 1,
                                        slidesToScroll: 1
                                    }
                                }
                            ]
                        });
                    }
                );
            }

            getProposals(${task.id}, 0, 1);
        });
    </script>

     <!-- 弹出大图 -->
	<link rel="stylesheet" href="/lib/css/jquery.fancybox.css">
	<script src="/lib/js/jquery.fancybox.pack.js"></script>
	<script>
	    $(document).ready(function () {
	        $('.fancybox').fancybox({
	            'padding': 0
	        });
	    });
	</script>

</@layout.foot>
