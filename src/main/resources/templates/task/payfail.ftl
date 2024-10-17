<#--
  云联创威客系统
  Copyright 2015 云联创科技

  支付失败页
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
                    <h2>支付出现错误。</h2>
                </div>
                <div class="y-Boxright">
                    <div class="kf-cir">
                        <img src="/lib/images/y-kf.png" alt="">
                        <h3>联系客服</h3>
                        <span>帮您发需求</span>
                    </div>
                    <div class="cou-us ">
                        <a href="#" target="_blank" class="h-btn"><span></span>联系在线客服</a>
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
</@layout.foot>