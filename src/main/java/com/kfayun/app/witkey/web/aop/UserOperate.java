/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.aop;

import java.lang.annotation.*;

/**
 * 用户操作注解
 * 
 * @author billy zhang (billy_zh@126.com)
 */
@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME)  
@Documented
public @interface UserOperate {

	UserAction value() default UserAction.NONE;

	String description() default "";
}
