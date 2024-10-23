    layui.use(['laypage'], function () {
        var laypage = layui.laypage
            
        var count = [[${pager.totalCount}]];
        var pageNum = [[${pager.pageNo}]];
        var pageSize = [[${pager.pageSize}]];
        //调用分页

        laypage.render({
            elem: 'pager'
            , theme: '#1E9FFF'
            , count: count
            , curr: pageNum
            , limit: pageSize //每页显示的条数
            , layout: ['count', 'prev', 'page', 'next', 'skip']
            , jump: function (obj, first) {
                if (!first) {
                    location.href = "${pager.baseUrl}pageno=" + obj.curr;
                }
            }
        });
    })