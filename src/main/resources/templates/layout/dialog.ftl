<#--
  云联创威客系统
  Copyright 2015 云联创科技

  弹出页母板
  author: Billy Zhang (billy_zh@126.com)
-->
<#macro head>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>Witkey平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta name="keywords" content="Witkey" />
    <meta name="description" content="Witkey Desc" />
    <meta http-equiv="Content-Type" context="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="/lib/css/cui.css" />
    <link rel="stylesheet" href="/lib/css/lib.css" />
    <link rel="stylesheet" href="/lib/css/style.css" />
    <link rel="stylesheet" href="/lib/css/style-rel.css" />
    <#nested >
</head>
</#macro>
<#macro body class="">
<body class=${class}>
    <#nested >
</#macro>
<#macro foot>
    <script src="/lib/js/jquery.js"></script>
    <script src="/lib/js/lib.js"></script>
    <#nested >
</body>
</html>
</#macro>