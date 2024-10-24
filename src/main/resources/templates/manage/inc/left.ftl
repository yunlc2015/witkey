<ul class="layui-nav layui-nav-tree" lay-filter="test">

    <li class="layui-nav-item <#if module=="main">layui-this</#if>">
        <a href="/manage/main">控制台</a>
    </li>
    <#if auth.hasAnyPermission("Ops") >
	<li class="layui-nav-item <#if module=="ops">layui-nav-itemed</#if>">
        <a class="" href="javascript:;">运营管理</a>
        <dl class="layui-nav-child">
            <#if auth.hasPermission("OpsArticleView") >
            <dd <#if path?starts_with("/manage/ops/article")>class="layui-this"</#if>>
                <a href="/manage/ops/articlelist"><span>内容管理</span></a>
            </dd>
            </#if>
            <#if auth.hasPermission("OpsBannerView") >
            <dd <#if path?starts_with("/manage/ops/banner")>class="layui-this"</#if>>
                <a href="/manage/ops/bannerlist"><span>Banner管理</span></a>
            </dd>
            </#if>
            <#if auth.hasPermission("OpsBdinfoView") >
            <dd <#if path?starts_with("/manage/ops/bdinfo")>class="layui-this"</#if>>
                <a href="/manage/ops/bdinfolist"><span>推广管理</span></a>
            </dd>
            </#if>
            <#if auth.hasPermission("OpsRequireView") >
            <dd <#if path?starts_with("/manage/ops/require")>class="layui-this"</#if>>
                <a href="/manage/ops/requirelist"><span>需求列表</span></a>
            </dd>
            </#if>
            <#if auth.hasPermission("OpsTousuView") >
            <dd <#if path?starts_with("/manage/ops/tousu")>class="layui-this"</#if>>
                <a href="/manage/ops/tousulist"><span>投诉记录</span></a>
            </dd>
            </#if>
        </dl>
    </li>
    </#if>
    <#if auth.hasAnyPermission("Task") >
    <li class="layui-nav-item <#if module=="tsk">layui-nav-itemed</#if>">
        <a class="" href="javascript:;">任务管理</a>
        <dl class="layui-nav-child">
            <#if auth.hasPermission("TaskView") >
            <dd <#if path?starts_with("/manage/tsk/tasklist")>class="layui-this"</#if>>
                <a href="/manage/tsk/tasklist"><span>市场标列表</span></a>
            </dd>
            </#if>
            <#if auth.hasPermission("TaskView") >
            <dd <#if path?starts_with('/manage/tsk/task2')>class="layui-this"</#if>>
                <a href="/manage/tsk/task2list"><span>管家标列表</span></a>
            </dd>
            </#if>
            <#if auth.hasPermission("TaskProjectView") >
             <dd <#if path?starts_with('/manage/tsk/project')>class="layui-this"</#if>>
                <a href="/manage/tsk/projectlist"><span>提案(项目)列表</span></a>
            </dd>
            </#if>
            <#if auth.hasPermission("TaskRatingView") >
            <dd <#if path?starts_with("/manage//tsk/rating")>class="layui-this"</#if>>
                <a href="/manage/tsk/ratinglist"><span>评价记录</span></a>
            </dd>
            </#if>
        </dl>
    </li>
    </#if>
    <#if auth.hasAnyPermission("Fin") >
    <li class="layui-nav-item <#if module=="fin">layui-nav-itemed</#if>">
        <a class="" href="javascript:;">财务管理</a>
        <dl class="layui-nav-child">
            <#if auth.hasPermission("FinTradeView") >
            <dd <#if path?starts_with("/manage/fin/trade")>class="layui-this"</#if>>
                <a href="/manage/fin/tradelist">交易记录</a>
            </dd>
            </#if>
            <#if auth.hasPermission("FinPaymentView") >
            <dd <#if path?starts_with("/manage/fin/payment")>class="layui-this"</#if>>
                <a href="/manage/fin/paymentlist">付款记录</a>
            </dd>
            </#if>
            <#if auth.hasPermission("FinWithdrawalView") >
            <dd <#if path?starts_with("/manage/fin/withdrawal")>class="layui-this"</#if>>
                <a href="/manage/fin/withdrawallist">提现记录</a>
            </dd>
            </#if>
            <#if auth.hasPermission("FinInvoiceView") >
            <dd <#if path?starts_with("/manage/fin/invoice")>class="layui-this"</#if>>
                <a href="/manage/fin/invoicelist">发票记录</a>
            </dd>
            </#if>
        </dl>
    </li>
    </#if>
    <#if auth.hasAnyPermission("User") >
    <li class="layui-nav-item <#if module=="usr">layui-nav-itemed</#if>">
        <a class="" href="javascript:;">用户管理</a>
        <dl class="layui-nav-child">
            <#if auth.hasPermission("UserDesignerView") >
            <dd <#if path?starts_with("/manage/usr/designer")>class="layui-this"</#if>>
                <a href="/manage/usr/designerlist">设计师列表</a>
            </dd>
            </#if>
            <#if auth.hasPermission("UserEmployerView") >
            <dd <#if path?starts_with("/manage/usr/employer")>class="layui-this"</#if>>
                <a href="/manage/usr/employerlist">雇主列表</a>
            </dd>
            </#if>
            <#if auth.hasPermission("UserRealAuthentView") >
            <dd <#if path?starts_with("/manage/usr/realauthent")>class="layui-this"</#if>>
                <a href="/manage/usr/realauthentlist">实名认证记录</a>
            </dd>
            </#if>
            <#if auth.hasPermission("UserAbilityAuthentView") >
            <dd <#if path?starts_with("/manage/usr/abilityauthent")>class="layui-this"</#if>>
                <a href="/manage/usr/abilityauthentlist">实力认证记录</a>
            </dd>
            </#if>
            <#if auth.hasPermission("UserZuoPinView") >
            <dd <#if path?starts_with("/manage/usr/zuopin")>class="layui-this"</#if>>
                <a href="/manage/usr/zuopinlist"><span>作品列表</span></a>
            </dd>
            </#if>
        </dl>
    </li>
    </#if>
    <#if auth.hasAnyPermission("Sys") >
     <li class="layui-nav-item <#if module=="sys">layui-nav-itemed</#if>">
        <a class="" href="javascript:;">系统管理</a>
        <dl class="layui-nav-child">
            <#if auth.hasPermission("SysGeneralSettingsView") >
            <dd <#if path?starts_with("/manage/sys/settings")>class="layui-this"</#if>>
               <a href="/manage/sys/settings">一般设置</a>
            </dd>
            </#if>
            <#if auth.hasPermission("SysPaySettingsView") >
            <dd <#if path?starts_with("/manage/sys/paysettings")>class="layui-this"</#if>>
               <a href="/manage/sys/paysettings">支付设置</a>
            </dd>
            </#if>
            <#if auth.hasPermission("SysActionLogView") >
            <dd <#if path?starts_with("/manage/sys/action")>class="layui-this"</#if>>
                <a href="/manage/sys/actionlist">操作日志</a>
            </dd>
            </#if>
            <#if auth.hasPermission("SysVerifyCodeView") >
            <dd <#if path?starts_with("/manage/sys/vcode")>class="layui-this"</#if>>
                <a href="/manage/sys/vcodelist">校验码记录</a>
            </dd>
            </#if>
            <#if auth.hasPermission("SysAdminView") >
            <dd <#if path?starts_with("/manage/sys/admin")>class="layui-this"</#if>>
                <a href="/manage/sys/adminlist">管理员列表</a>
            </dd>
            </#if>
        </dl>
    </li>
    </#if>
</ul>
