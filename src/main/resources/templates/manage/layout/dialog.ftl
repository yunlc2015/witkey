<#macro head>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台管理</title>
    <link rel="stylesheet" href="/lib/layui/css/layui.css">
    <link rel="stylesheet" href="/lib/css/manage.css">
    <#nested >
</head>
</#macro>
<#macro body>
<body>
	<#nested >
</#macro>
<#macro foot>
    <script type="text/javascript" src="/lib/js/jquery.js"></script>
    <script type="text/javascript" src="/lib/layui/layui.js"></script>
    <script>
        function ajaxSubmit(url, data) {
            $.ajax({
                type: "post",
                url: url,
                data: data.field,
                dataType: 'json',
                success: function (resp) {
                    if (resp.errCode == 0) {
                        layer.msg('保存成功。', {time:1000}, function() {parent.closeDialog(1);});
                    } else {
                        layer.msg(resp.errMsg);
                    }
                },
                error: function () {
                    layer.msg("出现错误，请稍后重试");
                }
            });
            return false;
        };
    </script>
    <#nested >
</body>
</html>
</#macro>