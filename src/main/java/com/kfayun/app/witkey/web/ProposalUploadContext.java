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
 * 任务提案上传上下文类
 * 暂存提案上传时的中间数据，信息存于HttpSession中。
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public class ProposalUploadContext {

    Map<String, String> fileDict;

    public static ProposalUploadContext getInstance(int uid, HttpServletRequest request) {
        String contextKey = "proposal_upload_" + uid;

        ProposalUploadContext tpc = (ProposalUploadContext) request.getSession().getAttribute(contextKey);
        if (tpc == null) {
            tpc = new ProposalUploadContext();
            request.getSession().setAttribute(contextKey, tpc);
        }

        return tpc;
    }

    private ProposalUploadContext() {
        fileDict = new HashMap<String, String>();
    }

    /*
     * 
     */
    public void addFile(String key, String filePath) {
        fileDict.put(key, filePath);
    }

    public void delFile(String key) {
        if (StrUtil.isEmpty(key))
            return;

        if (fileDict.containsKey(key)) {
            String filepath = fileDict.get(key);
            fileDict.remove(key);
            // File.Delete(HttpContext.Current.Server.MapPath(filepath));
        }
    }

    public void init() {
        fileDict.clear();
    }

    public String getFile(String key) {
        return fileDict.containsKey(key) ? fileDict.get(key) : "";
    }

}
