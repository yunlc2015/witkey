<#--
  云联创威客系统
  Copyright 2015 云联创科技

  作品详情页
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
        <@mobHeader />
    </div>
    <aside id="aside">
        <#include "/inc/mobnav.ftl" />
    </aside>
    <div id="nv" class="g-borb">
        <@webNav />
    </div>
    
	<div id="bd">
		<div class="wp">
            <div class="y-Box">
                <div class="y-Boxright2  sm-Boxright2">
                    <div class="right-PeoBox">
                        <div class="tit">
                            作品设计师
                        </div>
                        <div class="con">
                            <div class="pictxt">
                                <div class="pic">
                                    <img src="${user.avatar!'/lib/images/avatar.jpg'}" alt="" />
                                </div>
                                <div class="txt">
                                    <h3>${user.nickname}</h3>
                                    <span>${authDescribe!}</span>
                                    <span class="cp1">${abilityDescribe!}</span>
                                    <div class="h"></div>
                                    <span class="cp2">${userEdu!}</span>
                                    <span>${user.company!}</span>
                                    <span>${location}</span>
                                    <div class="h"></div>
									<p><a class="cp3" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${user.im!}&site=qq&menu=yes" id="hQQ" runat="server"></a></p>
                                    <p><a class="cp4" href="tel:${mobile}">${mobileMask}</a></p>
                                    <!-- <p class="cp3"></p>
                                    <p class="cp4"></p> -->
                                </div>
                                <div class="peo-btn">
                                    <a href="/designer/u${user.id}" class="he-home h-btn" >
                                        <span></span>
                                        TA的首页
                                    </a>
                                    <a href="/task/employ?uid=${user.id}" class="h-btn" >
                                        <span></span>
                                        雇佣合作
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>  
                <div class="y-Boxleft2">
                      <div class="con-works">
                          <div class="tit">
                              <h3>${zuopin.title}</h3>
                              <span>上传时间：${durationText}  服务价格：￥${zuopin.price}</span>
                              <p>本作品为原创作品，版权为作品原作者所有，感谢上传者的分享！</p>
                          </div>
                          <h4>${zuopin.description}</h4>
                          <#list imageList as image>
                          <div>
                                <img src="/user/${image.url}" alt="" id="img"><br />
                                ${image.description}
                            </div>
                          </#list>
                          <div class="work-b">
						      <ul>
                                  <li class="li1"><a href="javascript:void(0);" class="weixBtn btn_weixin">微信分享</a></li>
                                  <li class="li3"><a href="javascript:void(0);" id="like">点赞(${zuopin.likeCount})</a></li>
                              </ul>
                              <p>本案例由 ${user.nickname} 原创，未经作者许可，禁止转载或商业使用</p>
                          </div>
                      </div>
                </div>
                <div class="y-Boxright2 lg-Boxright2">
                    <div class="right-PeoBox">
                        <div class="tit">
                            作品设计师
                        </div>
                        <div class="con">
                            <div class="pictxt">
                                <div class="pic">
                                    <img src="${user.avatar!'/lib/images/avatar.jpg'}" alt="" />
                                </div>
                                <div class="txt">
                                    <h3>${user.nickname}</h3>
                                    <span>${authDescribe!}</span>
                                    <span class="cp1">${abilityDescribe!}</span>
                                    <div class="h"></div>
                                    <span class="cp2">${userEdu!}</span>
                                    <span>${user.company!}</span>
                                    <span>${location!}</span>
                                    <div class="h"></div>
                                    <p><a class="cp3" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${user.im!}&site=qq&menu=yes">在线沟通</a></p>
                                    <p><a class="cp4" id="hMob3" runat="server" href="tel:${mobile}">${mobileMask}</a></p>
                                </div>
                                <div class="peo-btn">
                                    <a href="/designer/u${user.id}" class="he-home h-btn" >
                                        <span></span>
                                        TA的首页
                                    </a>
                                    <a href="/task/employ?uid=${user.id}" class="h-btn" >
                                        <span></span>
                                        雇佣合作
                                    </a>
                                </div>
                            </div>
                            <div class="h20"></div>
                            <div class="demandBox">
                                <h3>快速提交您的需求</h3>
                                <form name="quickForm" id="quickForm">
                                    <input type="text" placeholder="您的称呼" class="inp" name="title" id="title">
                                    <input type="text" placeholder="您的手机号" class="inp" name="mobile" id="mobile">
                                    <select name="cate" id="cate">
                                        <option value="0" selected style="display: none;">需求类型</option>
                                        <#list cateList as cate>
                                        <option value='${cate.id}'>${cate.name}</option>
                                        </#list>
                                    </select>
                                    <textarea placeholder="您可以在这里详细描述您的项目需求。" id="subscribe" name="subscribe"></textarea>
                                    <input type="submit" class="btn-h1" value="提　交" id="quickSubmit">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>  
            </div>   
        </div>
	</div>

</@layout.body>
<@layout.foot>

	<link rel="stylesheet" href="/lib/css/slick.css">
	<script src="/lib/js/slick.min.js"></script>
	
      <script>
          $(document).ready(function () {
              $("#like").click(function() {
                  var uid = ${auth.userId};
                  if (uid > 0) {
                      $.ajax({
                          type: 'post',
                          url: '/like',
                          data: { oid: ${zuopin.id}, kind: "zuopin", likeUser: ${user.id} },
                          cache: false,
                          dataType: 'json',
                          success: function (ret) {
                              if (ret.data == 1) {
                                  $('#like').text("点赞 (" + ret.likeCount + ")");
                              } else if (ret.data == 2) {
                                  alert('已经赞过啦。');
                              } else {
                                  alert('出现问题，请稍后重试。');
                              }
                          }
                      });
                  } else {
                      alert('请先登录。');
                  }
              });

              $("#quickSubmit").click(function () {
                  var title = $("#title").val();
                  if (title == '') {
                      alert("请输入您的称呼。");
                      return false;
                  }

                  var mobile = $("#mobile").val();
                  if (mobile == '') {
                      alert("请输入您的手机号。");
                      return false;
                  }
                  if (mobile.length != 13 && mobile.substring(0, 1) != '1') {
                      alert("无效的手机号。");
                      return false;
                  }

                  var cate = $("cate option:selected").val();
                  if (cate == '') {
                      alert("请选择需求类型。");
                      return false;
                  }

                  var subscribe = $("subscribe").val();
                  if (subscribe == '') {
                      alert("请输入需求描述。");
                      return false;
                  }

                  $("#quickSubmit").enable(false);
                  var options = {
                      url: 'quick',
                      type: 'post',
                      dataType: 'text',
                      data: $("#quickForm").serialize(),
                      success: function (data) {
                          if (data.length > 0) {
                              alert(data);
                          }
                          $("#quickSubmit").enable(true);
                      }
                  };
                  $.ajax(options);
                  return false;
              });
          });
    </script>

</@layout.foot>