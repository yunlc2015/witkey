/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service;

import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.condition.VerifyCodeCondition;
import com.kfayun.app.witkey.model.VerifyCode;

/**
 * 校验码服务接口
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public interface VerifyService {

    /**
     * 获取校验码
     * 
     * @param sendTo
     * @param data
     * @param kind
     * @return
     */
    VerifyCode getVerifyCodeByData(
            String sendTo, String data, String kind);

    /**
     * 查找校验码列表（分页）
     * 
     * @param cond
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageList<VerifyCode> findVerifyCodeList(
            VerifyCodeCondition cond, int pageNo, int pageSize);

    /**
     * 发送校验码
     * 
     * @param vcode
     * @return
     */
    int sendVerifyCode(VerifyCode vcode);

}
