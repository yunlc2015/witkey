/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kfayun.app.witkey.JsonResult;
import com.kfayun.app.witkey.Constants;
import com.kfayun.app.witkey.service.SysService;
import com.kfayun.app.witkey.model.ActionLog;
import com.kfayun.app.witkey.util.WebUtil;
import com.kfayun.app.witkey.util.StrUtil;
import com.kfayun.app.witkey.web.UserAuth;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 用户操作切面类
 *
 * @author billy zhang (billy_zh@126.com)
 */
@Aspect
@Component
public class UserOperateAspect {

    private static final Logger log = LoggerFactory.getLogger(UserOperateAspect.class);

    /**
     * 用户操作切入点
     * 对加了UserOperate注解的方法进行切入处理。
     */
    @Pointcut("@annotation(com.kfayun.app.witkey.web.aop.UserOperate)")
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
     * 切入处理，方法调用前触发
     *
     * @param joinPoint 切入点
     */
    @Before("operate()")
    public void before(JoinPoint joinPoint) {
        //记录方法开始执行的时间
        startTimeMillis.set(System.currentTimeMillis()); 

    }

    /**
     * 切入处理，方法调用后触发
     * 记录操作日志，并存入数据库。
     * 
     * @param joinPoint 切入点
     */
    @AfterReturning(value="operate()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        int duration = (int) (System.currentTimeMillis() - startTimeMillis.get());

        try {
            MethodInvocationProceedingJoinPoint mjPoint = (MethodInvocationProceedingJoinPoint) joinPoint;
            MethodSignature methodSign = (MethodSignature) mjPoint.getSignature();
            UserOperate oper = methodSign.getMethod().getAnnotation(UserOperate.class);
            if (oper == null) {
                return;
            }
            UserAction action = oper.value();
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
            log.setMethodName(methodName);
            log.setActionName(action.getName());
            log.setActionDescr( !StrUtil.isEmpty(opDescr) ? opDescr : action.getDescr() );
            log.setActionArgs(jsonMapper.writeValueAsString(data));
            log.setDuration(duration);
            log.setKind("user");
            log.setLogTime(new Date());
            log.setLogIp(WebUtil.getRealIP(request));

            if (result instanceof JsonResult) {
                log.setResult(jsonMapper.writeValueAsString(result));
            }

            UserAuth auth = (UserAuth)request.getAttribute(Constants.USER_AUTH);
            if (auth != null && auth.getUser() != null) {
                log.setUserId(auth.getUserId());
                log.setOperator(auth.getNickname());
            }

            sysService.saveActionLog(log);

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 切入处理，出现错误后触发
     *
     * @param joinPoint 切入点
     * @param ex
     */
    // @AfterThrowing(pointcut = "operate()", throwing = "ex")
    // public void afterThrowing(JoinPoint joinPoint, Exception ex) {
    // }

    /**
     * 切入处理，环绕触发
     *
     * @param joinPoint 切入点
     */
    //@Around("operate()")
    // public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    //     return null;
    // }

}
