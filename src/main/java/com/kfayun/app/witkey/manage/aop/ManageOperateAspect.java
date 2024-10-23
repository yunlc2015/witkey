/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage.aop;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kfayun.app.witkey.Constants;
import com.kfayun.app.witkey.JsonResult;
import com.kfayun.app.witkey.service.SysService;
import com.kfayun.app.witkey.util.WebUtil;
import com.kfayun.app.witkey.util.StrUtil;
import com.kfayun.app.witkey.manage.AdminAuth;
import com.kfayun.app.witkey.manage.PermissionException;
import com.kfayun.app.witkey.model.ActionLog;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 管理操作切面类
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
@Aspect
@Component
public class ManageOperateAspect {

    private static final Logger log = LoggerFactory.getLogger(ManageOperateAspect.class);

    /**
     * 操作切入点
     */
    @Pointcut("@annotation(com.kfayun.app.witkey.manage.aop.ManageOperate)")
    private void operate() {
    }

    private ThreadLocal<Long> startTimeMillis = new ThreadLocal<>();

    @Autowired
    private ObjectMapper jsonMapper;
    @Autowired
    private SysService sysService;

    private HttpServletRequest getRequest() {
        RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
        return  (HttpServletRequest) attrs.resolveReference(RequestAttributes.REFERENCE_REQUEST);
    }

    /**
     * 方法调用前触发
     *
     * @param joinPoint
     */
    @Before("operate()")
    public void before(JoinPoint joinPoint) {
        startTimeMillis.set(System.currentTimeMillis()); //记录方法开始执行的时间

        MethodInvocationProceedingJoinPoint mjPoint = (MethodInvocationProceedingJoinPoint) joinPoint;
        MethodSignature methodSign = (MethodSignature) mjPoint.getSignature();
        ManageOperate oper = methodSign.getMethod().getAnnotation(ManageOperate.class);
        if (oper == null) {
            return;
        }

        ManageAction action = oper.value();
        if (ManageAction.USR_LOGIN == action ||
                ManageAction.USR_LOGOUT == action) {
            return;
        }

        AdminAuth auth = (AdminAuth)getRequest().getAttribute(Constants.ADMIN_AUTH);
        if (!auth.isLogon()) {
            log.warn("not login");
            throw new PermissionException(action.getName());
        }

        // permission check;
        if (!auth.hasPermission(action.getName())) {
            log.warn("{} no permission", methodSign.getMethod().getName());
            throw new PermissionException(action.getName());
        }
    }

    /**
     * 方法调用后触发
     *
     * @param joinPoint
     */
    @AfterReturning(value="operate()",returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        int duration = (int) (System.currentTimeMillis() - startTimeMillis.get());

        try {
            MethodInvocationProceedingJoinPoint mjPoint = (MethodInvocationProceedingJoinPoint) joinPoint;
            MethodSignature methodSign = (MethodSignature) mjPoint.getSignature();
            ManageOperate oper = methodSign.getMethod().getAnnotation(ManageOperate.class);
            if (oper == null) {
                return;
            }
            ManageAction action = oper.value();
            if (action.getLog() == 0) {
                // 不记录到数据库。
                return;
            }
            String methodName = methodSign.getName();
            Map<String, Object> data = new HashMap<>();
            String[] names = methodSign.getParameterNames();
            for (int i = 0; i < names.length; i++) {
                // 忽略密码参数
                if ("passwd".equalsIgnoreCase(names[i]) ||
                        "password".equalsIgnoreCase(names[i])) {
                    continue;
                }
                // 忽略Request, Response参数
                Object arg = joinPoint.getArgs()[i];
                if (Objects.nonNull(arg)) {
                    Class<?> argClazz = arg.getClass();
                    if (HttpServletRequest.class.isAssignableFrom(argClazz) ||
                            HttpServletResponse.class.isAssignableFrom(argClazz)) {
                        continue;
                    }
                }
                data.put(names[i], joinPoint.getArgs()[i]);
            }

            HttpServletRequest request = getRequest();

            String opDescr = oper.description();

            ActionLog log = new ActionLog();
            log.setCategory(action.getCategory());
            log.setActionName(action.getName());
            log.setActionDescr( !StrUtil.isEmpty(opDescr) ? opDescr : action.getDescr() );
            log.setActionArgs(jsonMapper.writeValueAsString(data));
            log.setMethodName(methodName);
            log.setDuration(duration);
            log.setKind("manage");
            log.setLogTime(new Date());
            log.setLogIp(WebUtil.getRealIP(request));

            if (result instanceof JsonResult) {
                log.setResult(jsonMapper.writeValueAsString(result));
            }

            AdminAuth auth = (AdminAuth)request.getAttribute(Constants.ADMIN_AUTH);
            if (ManageAction.USR_LOGIN == action) {
                if (auth == null) {
                    log.setResult("登录失败。");
                } else {
                    log.setResult("登录成功。");
                    log.setUserId(auth.getAdmin().getId());
                    log.setOperator(auth.getName());
                }
            } else {
                if (auth != null) {
                    log.setUserId(auth.getAdmin().getId());
                    log.setOperator(auth.getName());
                }
            }

            sysService.saveActionLog(log);

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 出现错误后触发
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut = "operate()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
    }

    /**
     * 环绕触发
     *
     * @param joinPoint
     */
    //@Around("operate()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return null;
    }

}
