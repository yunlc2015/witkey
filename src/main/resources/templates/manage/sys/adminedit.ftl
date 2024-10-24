<#import "/manage/layout/dialog.ftl" as layout>
<@layout.head>
    <style>
        div.module {
            padding:5px;
            margin-top:10px;
            margin-bottom:10px;
            background-color:#eee;
        }
    </style>
</@layout.head>
<@layout.body>
    <!-- 内容主体区域 -->
    <div class="manage" style="padding: 15px;">
        <form class="layui-form" method="post">
       		<input type="hidden" name="id" value="${admin.id}" />

             <div class="layui-form-item ">
                 <label class="layui-form-label"><span style="color: red;">*</span>名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" disabled="disabled" placeholder=""
                           autocomplete="off" class="layui-input" value="${admin.name}">
                </div>
            </div>
            
            <div class="layui-form-item ">
                 <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    <input type="text" name="description" placeholder=""
                           autocomplete="off" class="layui-input" value="${admin.description}">
                </div>
            </div>
            
            <div class="layui-form-item ">
                 <label class="layui-form-label">状态</label>
                <div class="layui-input-block">
                    <select name="state" id="state" class="layui-input">
                        <option value="0">正常</option>
                        <option value="1">禁用</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item ">
                密码修改，留空则不修改。
            </div>

            <div class="layui-form-item ">
                 <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="password" name="password" placeholder=""
                           autocomplete="off" class="layui-input" value="">
                </div>
            </div>

            <div class="layui-form-item ">
                 <label class="layui-form-label">密码确认</label>
                <div class="layui-input-block">
                    <input type="password" name="password2" placeholder=""
                           autocomplete="off" class="layui-input" value="">
                </div>
            </div>

            <div class="layui-form-item ">
                 <label class="layui-form-label">权限</label>
                <div class="layui-input-block">
                    <div class="module">运营模块</div>
                    <div class="layui-row">
                        <#list actionMap['operate'] as action >
                        <div class="layui-col-xs4">
                        <input type="checkbox" name="perm" value="${action.name}" title="${action.descr}" lay-skin="primary" <#if action.grant >checked="checked"</#if> />
                        </div>
                        </#list>
                    </div>
                    <div class="module">任务模块</div>
                    <div class="layui-row">
                        <#list actionMap['task'] as action >
                        <div class="layui-col-xs4">
                        <input type="checkbox" name="perm" value="${action.name}" title="${action.descr}" lay-skin="primary" <#if action.grant >checked="checked"</#if> />
                        </div>
                        </#list>
                    </div>
                    <div class="module">财务模块</div>
                    <div class="layui-row">
                        <#list actionMap['finance'] as action >
                        <div class="layui-col-xs4">
                        <input type="checkbox" name="perm" value="${action.name}" title="${action.descr}" lay-skin="primary" <#if action.grant >checked="checked"</#if> />
                        </div>
                        </#list>
                    </div>
                    <div class="module">用户模块</div>
                    <div class="layui-row">
                        <#list actionMap['user'] as action >
                        <div class="layui-col-xs4">
                        <input type="checkbox" name="perm" value="${action.name}" title="${action.descr}" lay-skin="primary" <#if action.grant >checked="checked"</#if> />
                        </div>
                        </#list>
                    </div>
                    <div class="module">系统模块</div>
                    <div class="layui-row">
                        <#list actionMap['system'] as action >
                        <div class="layui-col-xs4">
                        <input type="checkbox" name="perm" value="${action.name}" title="${action.descr}" lay-skin="primary" <#if action.grant >checked="checked"</#if> />
                        </div>
                        </#list>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button lay-filter="save" type="submit" lay-submit class="layui-btn layui-btn-normal ">保存</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>

</@layout.body>
<@layout.foot>
     
	<script type="text/javascript">
	
        $('#state').val('${admin.state}');
        
        layui.use('form', function () {
	        var form = layui.form;
            
            form.on('submit(save)', function(data) {
                //获取checkbox数据,组装成数组. 解决只能取到最后一个选中值的问题
                var perms =[];
                $('input[name="perm"]:checked').each(function(index, domEle){
                    perms.push($(this).val());
                });
                data.field.perm = perms.join(",");

                ajaxSubmit('adminedit', data);
                return false;
            });
	    });
        
    </script>    
</@layout.foot>