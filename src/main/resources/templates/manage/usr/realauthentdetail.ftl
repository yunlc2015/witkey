<#import "/manage/layout/dialog.ftl" as layout>
<@layout.head>
    <style>
        div.layui-input-block {
            padding-top: 10px;
        }
    </style>
</@layout.head>
<@layout.body>
    <!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="post">

            <div class="layui-form-item ">
                 <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    ${authent.realname!}
                </div>
            </div>
            
            <div class="layui-form-item ">
                 <label class="layui-form-label">身份证</label>
                <div class="layui-input-block">
                    ${authent.idcard!}
                </div>
            </div>
            
            <div class="layui-form-item ">
                 <label class="layui-form-label">认证类别</label>
                <div class="layui-input-block">
                    <#if authent.category==1 > 个人 <#else> 公司 </#if>
                </div>
            </div>

            <div class="layui-form-item ">
                 <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <#if authent.sex==1 > 男 <#else> 女 </#if>
                </div>
            </div>

            <#if authent.category==1 >
            <div class="layui-form-item ">
                 <label class="layui-form-label">身份证正面</label>
                <div class="layui-input-block">
                    <a href="/manage/${authent.idcard1}" target="_blank"><img src="/manage/${authent.idcard1}" style="height:100px"/></a>
                </div>
            </div>
            <div class="layui-form-item ">
                 <label class="layui-form-label">身份证反面</label>
                <div class="layui-input-block">
                    <a href="/manage/${authent.idcard2}" target="_blank"><img src="/manage/${authent.idcard2}" style="height:100px"/></a>
                </div>
            </div>
            <div class="layui-form-item ">
                 <label class="layui-form-label">证件到期日期</label>
                <div class="layui-input-block">
                    <#if authent.dueDate==1 > ${authent.dueDate2} <#else> 长期 </#if>
                </div>
            </div>
            <div class="layui-form-item ">
                 <label class="layui-form-label">行业</label>
                <div class="layui-input-block">
                    ${authent.industry!}
                </div>
            </div>
            <div class="layui-form-item ">
                 <label class="layui-form-label">公司</label>
                <div class="layui-input-block">
                    ${authent.address!}
                </div>
            </div>
            </#if>
            <#if authent.category==2 >
            <div class="layui-form-item ">
                 <label class="layui-form-label">身份证正面</label>
                <div class="layui-input-block">
                    <a href="/manage/${authent.idcard1}" target="_blank"><img src="/manage/${authent.idcard1}" style="height:100px"/></a>
                </div>
            </div>
            <div class="layui-form-item ">
                 <label class="layui-form-label">身份证反面</label>
                <div class="layui-input-block">
                    <a href="/manage/${authent.idcard2}" target="_blank"><img src="/manage/${authent.idcard2}" style="height:100px"/></a>
                </div>
            </div>
            <div class="layui-form-item ">
                 <label class="layui-form-label">营业执照正面</label>
                <div class="layui-input-block">
                    <a href="/manage/${authent.company1}" target="_blank"><img src="/manage/${authent.company1}" style="height:100px"/></a>
                </div>
            </div>
            <div class="layui-form-item ">
                 <label class="layui-form-label">公司名称</label>
                <div class="layui-input-block">
                    ${authent.company!}
                </div>
            </div>
            <div class="layui-form-item ">
                 <label class="layui-form-label">营业执照期限</label>
                <div class="layui-input-block">
                    ${authent.licenseDueDate!}
                </div>
            </div>
            <div class="layui-form-item ">
                 <label class="layui-form-label">公司注册号</label>
                <div class="layui-input-block">
                    ${authent.registrationNo!}
                </div>
            </div>
            <div class="layui-form-item ">
                 <label class="layui-form-label">公司注册地址</label>
                <div class="layui-input-block">
                    ${authent.address!}
                </div>
            </div>
            </#if>
            <div class="layui-form-item ">
                 <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    ${authent.authMemo!}
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
                    ${authent.authOperator!}
                </div>
            </div>

            <div class="layui-form-item ">
                 <label class="layui-form-label">审核时间</label>
                <div class="layui-input-block">
                    ${authent.authTime?datetime}
                </div>
            </div>
            
        </form>
    </div>

</@layout.body>
<@layout.foot>
	<script type="text/javascript">
    </script>    
</@layout.foot>