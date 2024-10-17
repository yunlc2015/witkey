<#--
  云联创威客系统
  Copyright 2015 云联创科技

  任务发布页
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
    <div id="nv" class="g-borb">
        <@webNav current="task" />
    </div>

	<div id="bd">
		<div class="wp">
            <div class="y-Box">
                <div class="y-Boxleft">
                    <div class="num-list num-list2">
                        <ul>
                            <li class="ok">
                                <div class="cir">1</div>
                                <div class="c"></div>
                                <span class="tp">选择类目</span>
                                <div class="line"></div>
                            </li>
                            <li class="on">
                                <div class="cir">2</div>
                                <span class="tp">完善需求</span>
                                <div class="line">
                                    <div class="line-on">
                                        <span></span>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="cir">3</div>
                                <span class="tp">提交订单</span>
                                <div class="line"></div>
                            </li>
                            <li>
                                <div class="cir">4</div>
                                <div class="c"></div>
                                <span class="tp">支付完成</span>
                                <div class="line"></div>
                            </li>
                        </ul>
                    </div>
                    <form id="task-form">
                    <div class="inpBox">
                        <div class="inp1-box">
                            <div class="select-m">
                                <input type="hidden" name="" id="" value="1">
                                <div class="xtit">选择你的需求类目：</div>
                                <div class="arr">选择/修改</div>
                                
                                <div class="cur">
                                    <select name="cate" id="cate">
                                        <option value="0" selected="" style="display:none;">需求类型</option>
                                        <#list cateList as cate>
                                        <option value='${cate.id}'>${cate.name}</option>
                                        </#list>
                                    </select>
                                </div>   
                            </div>
                        </div>
                        <div class="inp2-box">
                            <span>请您用一句话描述需求：</span>
                            <input type="text" name="requirement" id="requirement" placeholder="例如：新成立的科技公司的LOGO设计" value="">
                        </div>
                        <div class="inp3-box">
                            <span>需求详情：</span>
                            <p class="textarea-holder">项目受众<br>项目背景<br>设计要求<br>设计风格</p>
                            <textarea name="designDetails" id="designDetails"></textarea>
                        </div>
                        <div class="inp4-box">
                            <div class="n-file jsFile">
                                <span><em></em>添加附件</span>
                            </div>
                            <p>最多不超过5个附件，每个大小不超过5M。</p>
                        </div>

                        <!-- 增加 start -->
                        <ul class="tz-order-upload JsPara"></ul>
                        <!-- 增加 end -->

                        <!-- 添加代码 -->
                        <div class="emptyLi">
                            <li>
                                <div class="li-con">
                                    <div class="file-con">
                                        <div class="pic">
                                            <label class="n-file"><input type="file"></label>
                                            <a href="javascript:void(0);" class="jsDele"></a>
                                        </div>
                                    </div>
                                    <div class="percent"><span><em class="JsCount">0</em>%</span></div>
                                    <input type="hidden" id="key" name="key" value="" />
                                    <input type="hidden" id="url" value="" />
                                </div>
                            </li>
                        </div>
                        <!-- 添加代码 end -->
                        <div class="h20"></div>

                        <div class="inp5-box">
                            <div class="inp-kl inp-n">
                                <span>您的需求工作量：</span>
                                <input type="text" name="designContent" id="designContent" placeholder="例如：画册20页或LOGO一个" value="">
                            </div>
                            <div class="inp-kr inp-n">
                                <span>您的期望时间：</span>
                                <input type="text" name="hopeDays" id="hopeDays" placeholder="7" value="">
								<i>天</i>
                            </div>
                            <div class="c"></div>
                        </div>
                        <div class="inp6-box radioBox">
                            <span>平台推荐项目管家服务：</span>
                            <div class="rad-k jsRadio">
                                <label><input type="checkbox" id="service" name="service" value="1"></label>
                            </div>
                        </div>
                        <div class="inp7-box">
                            <div class="inp-n inp7-k">
                                <span>您的设计预算：</span>
                                <input type="text" name="designBudget" id="designBudget" >
                            </div>
                            <em>元</em>
                            <div class="txt jsRadioTxt">
                                <div class="ok">
                                    <p class="blue">启用管家服务您的需求将由平台认证实力设计师完成，金额不得低于1000元，管家将对出品质量进行全程把控，如创意不满意平台将为您再次推荐优秀设计师为您服务。</p>
                                </div>
                                <div class="unok">
                                    <p class="blue">市场标未经过项目管家托管，自由发布，平台不收取您任务费用，赏金多少只会影响优秀设计师的参与度，平台将对发布项目进行自由匹配。</p>
                                </div>
                            </div>
                        </div>

                        <!-- 增加部分 start -->
                        <div class="inp8-box">
                            <div class="inp-n">
                                <span>选择交易模式：</span>
                            </div>
                            <ul class="tz-inp-box">
                                <li>
                                    <label><input type="radio" name="invest-mode" value="2">投标模式</label>
                                    <p>先选中标服务商，TA再工作，<br>适合印刷制作、商标注册、网站开发等工作。</p>
                                </li>
                                <li>
                                    <label><input type="radio" name="invest-mode" value="1" checked>悬赏模式</label>
                                    <p>服务商们先工作，再选中标作品，<br>适合LOGO设计、包装设计、画册设计等工作。</p>
                                </li>
                            </ul>
                        </div>
                        <div class="h20"></div>
                        <!-- 增加部分 end -->

                        <div class="inp8-box">
                            <div class="inp-n">
                                <span>其它服务（选填）：</span>
                                <input type="text" name="otherService" id="otherService" >
                            </div>
                        </div>
                        <div class="inp9-box">
                            <div class="p9-rad">
                                <div class="inp-k jsCheck">
                                    <label for=""><input type="checkbox" name="invoice" id="invoice" value="1" class="tz-radio"></label>
                                </div>
                                <div class="txt">
                                    <h3>我要开具发票并快递</h3>
                                    <div class="bm">
                                        <div class="sm">
                                            <em></em>说明
                                        </div>
                                    </div>
                                    <div class="c"></div>
                                    <p>本平台设计师大多为个人高端设计师，不具备开发票资格。所以雇主索要发票为本平台代开。交易结束后的3-7个工作日开具发票并安排寄发，请注意查收！</p>
                                </div>
                            </div>   
                        </div>
                        <div class="jsCheckTxt">
                            <div class="inp10-box">
                                <div class="inp-n">
                                    <span>发票抬头：</span>
                                    <input type="text" name="invTitle" id="invTitle" >
                                </div>
                                <em>个人抬头可填写姓名，单位抬头请务必确认准确无误。 </em> 
                            </div>
                            <div class="inp11-box">
                                <div class="inp-n put-w1">
                                    <span>快递地址：</span>
                                    <input type="text" name="invAddress" id="invAddress" >
                                </div>
                                <div class="inp-n put-w2">
                                    <span>收件人：</span>
                                    <input type="text" name="invRecipients" id="invRecipients" >
                                </div>
                                <div class="inp-n put-w3">
                                    <span>联系电话：</span>
                                    <input type="text" name="invCellphone" id="invCellphone" >
                                </div>
                            </div>
                        </div>
                        <div class="c"></div>
                        <div class="box12">
                            <div class="tit">
                                我的需求清单
                            </div>
                            <div class="txtcon">
                                <p>费用明细：</p>
                                <table>
                                    <tr>
                                        <td width="50%" align="left">设计师服务费</td>
                                        <td width="35%">1个</td>
                                        <td width="15%" align="left">¥<span id="designAmount">0</span> </td>
                                    </tr>
                                    <tr id="trService" style="display: none;">
                                        <td width="50%" align="left">项目管家服务费</td>
                                        <td width="35%">1项</td>
                                        <td width="15%" align="left">¥<span id="serviceAmount">0</span> </td>
                                    </tr>
                                    <tr id="trFax" style="display: none;">
                                        <td width="50%" align="left">税金</td>
                                        <td width="35%">1项</td>
                                        <td width="15%" align="left">¥<span id="faxAmount">0</span> </td>
                                    </tr>
                                    <tr id="trInvoice" style="display: none;">
                                        <td width="50%" align="left">我要开具发票并快递</td>
                                        <td width="35%">1份</td>
                                        <td width="15%" align="left">¥8  </td>
                                    </tr>
                                </table>

                            </div>
                            <div class="tit btz">
                                <p>费用合计：</p><span>¥<span id="totalAmount">0</span> </span>
                                <input type="hidden" id="amount1" name="amount1" value="0" />
                                <input type="hidden" id="amount2" name="amount2" value="0" />
                                <input type="hidden" id="amount3" name="amount3" value="0" />
                                <input type="hidden" id="amount4" name="amount4" value="0" />
                            </div>
                        </div>
                        <a class="inp-btn h-btn" href="" id="submit">
                            <span></span>
                            确认，并提交定单
                        </a>
                    </div> 
                    <input type="hidden" id="uid" name="uid" value="${auth.userId}" />
                    </form>
                </div>
                <div class="y-Boxright">
                    <div class="kf-cir">
                        <img src="/lib/images/y-kf.png" alt="">
                        <h3>联系客服</h3>
                        <span>帮您发需求</span>
                    </div>
                    <div class="cou-us">
                        <a href="#" class="h-btn"><span></span>联系在线客服</a>
                        <p>周一至周五9:00-18:00</p>
                        <h2>${settings.serviceTel!}</h2>
                        <p>周一至周五9:00-18:00</p>
                    </div> 
                </div>  
            </div>   
        </div>
	</div>

</@layout.body>
<@layout.foot>

    <!-- 上传作品 -->
    <script>
        $(document).ready(function ($) {
            var atmCount = 0;
            
            // 初始化上传.
            $.post("publish_init", { }, null, 'json');

            // 上传图片
            $('.jsFile').click(function (event) {
                var _html = $('.emptyLi').clone().html();
                $('.JsPara').append(_html);
                $('.JsPara li:last()').find('.n-file').trigger('click');
            });
            $('body').on('change', '.n-file :file', function () {
                if (atmCount == 5) {
                    alert('已经上传了5个附件。');
                    return;
                }

                var obj = $(this);
                var mystring = obj.val();
                var myarray = mystring.split(".");
                var stuff = myarray[myarray.length - 1];
                var loaded = 0;
                var myavatar = $(this).parents('.pic');

                var files = !!this.files ? this.files : [];
                if (!files.length || !window.FileReader) return;

                var reader = new FileReader();

                reader.onloadend = function () {
                    $.ajax({
                        url:"file/upload", 
                        type: "POST",
                        data: { file: this.result, name: files[0].name  }, 
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
                                switch (stuff.toLowerCase()) {
                                    case 'jpg':
                                        case 'jpeg':
                                        case 'gif':
                                        case 'png':
                                        myavatar.css({ "background-image": "url(" + json.url + ")" });
                                    case 'docx':
                                        myavatar.addClass('doc');
                                        break;
                                    case 'doc':
                                        myavatar.addClass('doc');
                                        break;
                                    case 'xls':
                                        myavatar.addClass('xls');
                                        break;
                                    case 'xlsx':
                                        myavatar.addClass('xls');
                                        break;
                                    case 'ppt':
                                        myavatar.addClass('ppt');
                                        break;
                                    case 'pptx':
                                        myavatar.addClass('ppt');
                                        break;
                                    case 'pdf':
                                        myavatar.addClass('pdf');
                                        break;
                                    case 'pdfx':
                                        myavatar.addClass('pdf');
                                        break;
                                    default:
                                        myavatar.addClass('unknown');
                                        break;
                                }

                                myavatar.parents('li').find('input[id=\'key\']').val(json.key);
                                myavatar.parents('li').find('input[id=\'url\']').val(json.url);
                            } else {
                                alert('上传失败。');
                            }

                            showProgress(101);

                            atmCount++;
                        }, 
                    });
                }

                reader.onloadstart = function () {
                    //showProgress( 0 );
                }

                reader.onprogress = function (e) {
                    loaded += e.loaded;
                    //showProgress( Math.round((loaded / filesize) * 100) );
                }

                reader.readAsDataURL(files[0]);
            });

            $('body').on('click', '.jsDele', function () {
                //$(this).parents('li').remove();

                var li = $(this).parents('li');
                var key = li.find('input[id=\'key\']').val();
                // 删除
                $.post("publish_delpic", { key:key  }, null, 'json');
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

    <script>
        $(document).ready(function ($) {
            $('.inp3-box textarea').focusin(function (event) {
                $(this).siblings('.textarea-holder').hide();
            });
            $('.inp3-box textarea').focusout(function (event) {
                var _val = $(this).val();
                if (_val) {
                    $(this).siblings('.textarea-holder').hide();
                } else {
                    $(this).siblings('.textarea-holder').show();
                }
            });

            $('.jsRadio').click(function () {
                $(this).toggleClass('ok');
                if ($(this).hasClass('ok')) {
                    $('.jsRadioTxt').find('.ok').show();
                    $('.jsRadioTxt').find('.unok').hide();
                    $("#trService").css("display", "table-row");
                } else {
                    $('.jsRadioTxt').find('.ok').hide();
                    $('.jsRadioTxt').find('.unok').show();
                    $("#trService").hide();
                }
                computeTotal();
            });

            $('.jsCheck').click(function () {
                $(this).toggleClass('ok');
                if ($(this).hasClass('ok')) {
                    $('.jsCheckTxt').show();
                    $("#trInvoice").css("display", "table-row");
                    $("#trFax").css("display", "table-row");
                } else {
                    $('.jsCheckTxt').hide();
                    $("#trInvoice").hide();
                    $("#trFax").hide();
                }
                computeTotal();
            });

            function computeTotal() {
                var budget = $("#designBudget").val();
                var amount = 0;
                if (budget != '')
                    amount = parseFloat(budget);
                var totalAmount = amount;
                var designAmount = 0;
                var serviceAmount = 0;
                var faxAmount = 0;

                if ($("#invoice").prop('checked')) {
                    totalAmount += 8;
                    faxAmount = amount * 0.06;
                    $("#faxAmount").html(faxAmount);
                }

                if ($("#service").prop('checked')) {
                    serviceAmount = amount * 0.15;
                    $("#serviceAmount").html(serviceAmount);
                }

                designAmount = amount - serviceAmount - faxAmount;

                $("#designAmount").html(designAmount);
                $("#totalAmount").html(totalAmount);

                $("#amount1").val(totalAmount);
                $("#amount2").val(designAmount);
                $("#amount3").val(serviceAmount);
                $("#amount4").val(faxAmount);
            };

            $("#designBudget").blur(function () {
                var val = $(this).val();
                if (isNaN(val)) {
                    alert('无效的金额，请修改。');
                    $(this).focus();
                } else {
                    $("#devAmount").html(val);
                    computeTotal();
                }
            });

            $("#hopeDays").blur(function () {
                var val = $(this).val();
                if (isNaN(val)) {
                    alert('无效的天数，请修改。');
                    $(this).focus();
                }
            });

            var once = 0;

            function checkField(obj, msg) {
                var val = obj.val();
                if (val == '') {
                    alert(msg);
                    obj.focus();
                    return true;
                }
                return false;
            }

            $("#submit").click(function() {

                var cate = $("#cate").val();
                if (cate == undefined || cate == null || cate == '0') {
                    alert("请选择类别。");
                    $("#cate").focus();
                    return;
                }
                if (checkField($("#requirement"), "请输入任务需求。"))
                    return false;
                if (checkField($("#designDetails"), "请输入需求详情。"))
                    return false;
                if (checkField($("#designContent"), "请输入工作量。"))
                    return false;
                if (checkField($("#hopeDays"), "请输入期望时间。"))
                    return false;
                if (checkField($("#designBudget"), "请输入设计预算。"))
                    return false;

                var service = $("input[name=\"service\"]:checked").val();
                if (service == 1) {
                    var budget = parseFloat($("#designBudget").val());
                    if (budget < 1000) {
                        alert("选择管理服务的项目预算不能低于1000元。");
                        return false;
                    }
                }

                var invoice = $("input[name=\"invoice\"]:checked").val();
                if (invoice == 1) {
                    if (checkField($("#invTitle"), "请输入发票抬头。"))
                        return false;
                    if (checkField($("#invAddress"), "请输入快递地址。"))
                        return false;
                    if (checkField($("#invRecipients"), "请输入收件人。"))
                        return false;
                    if (checkField($("#invCellphone"), "请输入联系电话。"))
                        return false;
                }

                if (once == 0) {
                    once = 1;

                    var options = {
                        url: 'publish',
                        type: 'post',
                        dataType: 'json',
                        data: $("#task-form").serialize(),
                        success: function (resp) {
                            once = 0;

                            if (resp.errCode == 0) {
                                location.href = "pay?tradeno=" + resp.data;
                            } else {
                                alert("提交失败，请稍后重试。");
                            }
                        }
                    };
                    $.ajax(options);
                }

                return false;
            });

            $("#cate").val('${cateId!}');

            $("#service").attr("checked", true);
            $('.jsRadio').toggleClass('ok');
            $('.jsRadioTxt').find('.ok').show();
            $('.jsRadioTxt').find('.unok').hide();
            $("#trService").css("display", "table-row");
        });
    </script>
	
</@layout.foot>