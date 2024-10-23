<#import "/manage/layout/dialog.ftl" as layout>
<@layout.head>
    <style>
        div.layui-input-block {
            padding-top: 20px;
        }
    </style>
</@layout.head>
<@layout.body>
    <!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="post">
       		<input type="hidden" name="id" value="${authent.id}" />
       		<input type="hidden" name="state" id="state" value="0" />

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
                    <a href="/manage/${authent.idcard1}" target="_blank"><img src="/manage/${authent.idcard1}" style="height:200px"/></a>
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
                    <a href="/manage/${authent.idcard1}" target="_blank"><img src="/manage/${authent.idcard1}" style="height:200px"/></a>
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
                    <a href="/manage/${authent.company1}" target="_blank"><img src="/manage/${authent.company1}" style="height:200px"/></a>
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
                    <textarea id="remark" name="remark" class="layui-input" style="height:80px"></textarea>
                </div>
            </div>
            
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button lay-filter="approve" type="submit" lay-submit class="layui-btn layui-btn-normal ">通过</button>
                    <button lay-filter="reject" type="submit" lay-submit class="layui-btn layui-btn-normal ">不通过</button>
                </div>
            </div>
        </form>
    </div>

</@layout.body>
<@layout.foot>
	<script type="text/javascript">
		
        layui.use('form', function () {
	        var form = layui.form;
	
	        form.on('submit(approve)', function (data) {
                data.field.state=1;
	            ajaxSubmit('realauthentaudit', data);
	            return false;
	        });

            form.on('submit(reject)', function (data) {
	            ajaxSubmit('realauthentaudit', data);
	            return false;
	        });
	    });
        
    </script>    
</@layout.foot>