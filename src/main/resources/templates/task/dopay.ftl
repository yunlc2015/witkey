<#--
  云联创威客系统
  Copyright 2015 云联创科技

  支付宝支付页
  author: Billy Zhang (billy_zh@126.com)
-->
<#import "/layout/page.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>

    <div style="margin: 30px;min-height:500px;">
        <#if errorMsg?has_content >
        ${errorMsg}
        <#else>
        正在处理订单，请等候...
        <br />
        ${payForm!}
        </#if>
    </div>

</@layout.body>
<@layout.foot>

    <script type="text/javascript">
        <#if !(errorMsg?has_content) >
        $(document).ready(function () {
            document.forms['alipaysubmit'].submit();
        });
        </#if>
    </script>
    
</@layout.foot>
