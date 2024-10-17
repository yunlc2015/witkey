/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.dao;

import java.util.List;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import java.util.Properties;

/**
 * Mapper拦载器
 * 记录操作的执行时长。
 * 
 * @author billy (billy_zh@126.com)
 */
@Intercepts({
    @Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
    )
})
public class DaoInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        
        long beginTime = System.currentTimeMillis();
        try {
            // 执行原始SQL语句
            return invocation.proceed();
        
        } catch (Exception ex) {
            throw ex;
        } finally {
            long duration = System.currentTimeMillis() - beginTime;
            List<Long> list = DaoContextHolder.getList();
            if (list!=null) { 
                list.add(duration); 
            }
        }
    }
 
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
 
    @Override
    public void setProperties(Properties properties) {
        // 可以接收配置的属性
    }

}
