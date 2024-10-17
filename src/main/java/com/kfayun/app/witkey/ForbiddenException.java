/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey;

/*
 * 访问禁止异常类
 * 控制器类的方法出现非法访问时，抛出此异常，由WebErrorHandler捕获后输出。
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message) {
        super(message);
    }

}
