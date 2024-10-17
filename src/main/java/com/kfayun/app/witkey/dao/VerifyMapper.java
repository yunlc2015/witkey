/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.kfayun.app.witkey.model.VerifyCode;
import com.kfayun.app.witkey.condition.VerifyCodeCondition;

/**
 * 校验码实体Mapper
 * 
 * @author billy (billy_zh@126.com)
 */
public interface VerifyMapper {

    public VerifyCode getVerifyCodeByData(
        @Param("sendTo")String sendTo, 
        @Param("data")String data, 
        @Param("kind")String kind);

    public int findVerifyCodeCount(
        @Param("cond")VerifyCodeCondition cond);


    public List<VerifyCode> findVerifyCodeList(
        @Param("cond")VerifyCodeCondition cond,
        @Param("offset")int offset,
        @Param("limit")int limit);

    public void insertVerifyCode(VerifyCode vcode);

    public void updateVerifyCode(VerifyCode vcode);
        
}
