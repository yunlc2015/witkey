<#--
  云联创威客系统
  Copyright 2015 云联创科技

  author: Billy Zhang (billy_zh@126.com)
-->
<div class="wp">
    <a href="/" class="logo">
        <img src="<#if settings.appPcLogo?has_content>${settings.appPcLogo}<#else>/lib/images/logo.png</#if>" alt=""></a>
    <div class="m-hdr mobile">
        <a href="javascript:void(0);" class="soBtn"></a>
        <span class="menuBtn"></span>
    </div>
    <div class="hdr">
        <div class="searchBox">
            <form action="/search" method="post" onsubmit="return doSubmit();">
                <div class="nice-sel">
                    <input type="text" value="找设计师" name="cate" readonly>
                    <ul>
                        <li value="1">找设计师</li>
                        <li value="2">找设计团队</li>
                        <li value="3">抢市场标</li>
                        <li value="4">抢管家标</li>
                    </ul>
                </div>
                <input type="text" value="" name="keywords" id="keywords" class="so-inp">
                <input type="submit" value="搜索" class="so-btn ani">
                <input type="submit" value="" class="m-so-btn ani">
            </form>
        </div>
        <a href="/task/publish" class="a-subBtn ani">发布设计需求</a>
    </div>
</div>
 <script>
     function doSubmit() {
         var title = $("#keywords").val();
         if (title == '') {
             alert("请输入内容。");
             return false;
         }

         return true;
     }
</script>