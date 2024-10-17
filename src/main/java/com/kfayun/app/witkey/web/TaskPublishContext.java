/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.kfayun.app.witkey.util.StrUtil;

/**
 * 任务发布上下文类
 * 暂存任务发布时的中间信息，信息存于HttpSession中。
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public class TaskPublishContext {

    Map<String, String> fileDict;

    public static TaskPublishContext getInstance(int uid, HttpServletRequest request) {
        String contextKey = "task_publish_" + uid;

        TaskPublishContext tpc = (TaskPublishContext)request.getSession().getAttribute(contextKey);
        if (tpc == null) {
            tpc = new TaskPublishContext();
            request.getSession().setAttribute(contextKey, tpc);
        }

        return tpc;
    }

    private TaskPublishContext() {
        fileDict = new HashMap<String, String>();
    }

    public void addFile(String key, String filePath) {
        fileDict.put(key, filePath);
    }

    public void delFile(String key) {
        if (StrUtil.isEmpty(key))
            return;

        if (fileDict.containsKey(key)) {
            String filepath = fileDict.get(key);
            fileDict.remove(key);
            //File.Delete(HttpContext.Current.Server.MapPath(filepath));
        }
    }

    public void init() {
        fileDict.clear();
    }

    public String getFile(String key) {
        return fileDict.containsKey(key) ? fileDict.get(key) : "";
    }

}
