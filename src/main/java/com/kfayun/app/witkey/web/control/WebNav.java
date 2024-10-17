/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.control;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import freemarker.cache.TemplateLoader;
import freemarker.core.Environment;
import freemarker.core._MiscTemplateException;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * WebNav控件
 * Freemarker自定义控件，需在Freemarker配置中声明。
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Component
public class WebNav implements TemplateDirectiveModel {

    @Override
    public void execute(Environment environment, @SuppressWarnings("rawtypes") Map params, TemplateModel[] templateModel,
            TemplateDirectiveBody directiveBody) throws TemplateException, IOException {
        TemplateLoader templateLoader = environment.getConfiguration().getTemplateLoader();

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.setAttribute("dropDown", 
            Integer.parseInt(params.getOrDefault("dropDown", "0").toString()), RequestAttributes.SCOPE_REQUEST);
        attr.setAttribute("current", params.getOrDefault("current", ""), RequestAttributes.SCOPE_REQUEST);

        String fullTemplatePath = "inc/webnav.ftl";
        if (templateLoader.findTemplateSource(fullTemplatePath) != null) {
            environment.include(environment.getTemplateForInclusion(fullTemplatePath, null, true));
        } else {
            throw new _MiscTemplateException(environment, "Missing template file path:" + fullTemplatePath);
        }
    }

    
}