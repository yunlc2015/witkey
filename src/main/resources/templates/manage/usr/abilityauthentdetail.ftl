<#import "/manage/layout/dialog.ftl" as layout>
<@layout.head>
</@layout.head>
<@layout.body>
    <!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="post">

            <div class="layui-form-item ">
                 <label class="layui-form-label">链接</label>
                <div class="layui-input-block">
                    <a href="${authent.link}" target="_blank">${authent.link!}</a>
                </div>
            </div>
            
            <div class="layui-form-item ">
                 <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    ${authent.describe!}
                </div>
            </div>
            
            <div class="layui-form-item ">
                 <label class="layui-form-label">认定级别</label>
                <div class="layui-input-block">
                   <#if authent.authGrade==1 >认证设计师
                    <#elseif authent.authGrade==2 >官方设计师</span>
                    <#elseif authent.authGrade==3 >明星设计师</span>
                    </#if>
                </div>
            </div>

            <div class="layui-form-item ">
                 <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    ${authent.auditMemo!}
                </div>
            </div>
            
            <div class="layui-form-item ">
                 <label class="layui-form-label">状态</label>
                <div class="layui-input-block">
                    <#if authent.authState==1 >待审核
                    <#elseif authent.authState==2 ><span style="color:green">审核通过</span>
                    <#elseif authent.authState==3 ><span style="color:red">审核不通过</span>
                    </#if>
                </div>
            </div>
            
            <div class="layui-form-item ">
                 <label class="layui-form-label">审核人</label>
                <div class="layui-input-block">
                    ${authent.auditOperator!}
                </div>
            </div>

            <div class="layui-form-item ">
                 <label class="layui-form-label">审核时间</label>
                <div class="layui-input-block">
                    ${authent.auditTime?datetime}
                </div>
            </div>

        </form>
    </div>

</@layout.body>
<@layout.foot>
	  
</@layout.foot>