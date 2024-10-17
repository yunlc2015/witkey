/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.dao;

import java.util.List;

/**
 * DaoContext线程变量
 * 用于保存Mapper的执行时长。
 * 注意：如在多线程内执行Mapper内的方法，用此方式存在缺陷！
 * 
 * @author billy (billy_zh@126.com)
 */
public class DaoContextHolder {

    private static final ThreadLocal<List<Long>> holder = new ThreadLocal<>();
 
    public static List<Long> getList() {
        return holder.get();
    }
 
    public static void setList(List<Long> list) {
        holder.set(list);
    }
 
    public static void clear() {
        holder.remove();
    }
}
