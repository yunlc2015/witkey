/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web;

import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.kfayun.app.witkey.util.StrUtil;

/**
 * 作品上传上下文类
 * 暂存作品上传时的中间信息，信息存于HttpSession中。
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public class ZuopinUploadContext {

    Map<String, String> fileDict;
    private int zuopinId;
    private String cover;

    public static ZuopinUploadContext getInstance(int uid, HttpServletRequest request) {
        String contextKey = "zuopin_upload_" + uid;

        ZuopinUploadContext tpc = (ZuopinUploadContext)request.getSession().getAttribute(contextKey);
        if (tpc == null) {
            tpc = new ZuopinUploadContext();
            request.getSession().setAttribute(contextKey, tpc);
        }

        return tpc;
    }

    private ZuopinUploadContext() {
        fileDict = new HashMap<String, String>();
    }

    public int getZuopinId() {
        return zuopinId;
    }

    public void setZuopinId(int zuopinId) {
        this.zuopinId = zuopinId;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void addImage(String key, String filePath) {
        fileDict.put(key, filePath);
    }

    public void delImage(String key) {
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

    public String getImage(String key) {
        return fileDict.containsKey(key) ? fileDict.get(key) : "";
    }

}
