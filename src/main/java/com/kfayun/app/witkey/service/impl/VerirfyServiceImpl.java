/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.condition.VerifyCodeCondition;
import com.kfayun.app.witkey.config.AppConfig;
import com.kfayun.app.witkey.dao.VerifyMapper;
import com.kfayun.app.witkey.model.VerifyCode;
import com.kfayun.app.witkey.service.VerifyService;
import com.kfayun.app.witkey.spi.VerifyCodeSender;
import com.kfayun.app.witkey.spi.VerifySenderConfig;
import com.kfayun.app.witkey.util.StrUtil;

/**
 * 校验码服务接口实现
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Service
public class VerirfyServiceImpl implements VerifyService {

    @Autowired
    private VerifyMapper verifyMapper;

    @Autowired
    private AppConfig appConfig;

    @Override
    public VerifyCode getVerifyCodeByData(
        String sendTo, String code, String kind) {
        return verifyMapper.getVerifyCodeByData(sendTo, code, kind);
    }

    @Override
    public PageList<VerifyCode> findVerifyCodeList(
        VerifyCodeCondition cond, int pageNo, int pageSize) {
            PageList<VerifyCode> pglist = new PageList<>(pageNo, pageSize);
            pglist.setTotal(verifyMapper.findVerifyCodeCount(cond));
            pglist.setList(verifyMapper.findVerifyCodeList(cond, pglist.getOffset(), pageSize));
            return pglist;
    }

    @SuppressWarnings("unchecked")
    @Override
    public int sendVerifyCode(VerifyCode vcode) {
        vcode.setData(StrUtil.getRandomNumber(6));
        verifyMapper.insertVerifyCode(vcode);

        VerifySenderConfig senderConfig = appConfig.verifyCodeConfig().get(vcode.getType());
        try {
            Class<VerifyCodeSender> clazz = (Class<VerifyCodeSender>)Class.forName(senderConfig.getSenderClazz());
            VerifyCodeSender sender = clazz.newInstance();
            sender.init(senderConfig.getConfig());
            sender.sendVerifyCode(vcode);

            verifyMapper.updateVerifyCode(vcode);
        } catch (Exception ex) {
            vcode.setState(-1);
            vcode.setSendResult(ex.getMessage());
            verifyMapper.updateVerifyCode(vcode);
        }

        return vcode.getState();
    }

}
