/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.aop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户操作枚举
 *
 * @author billy (billy_zh@126.com)
 */
public enum UserAction {

    NONE("none", "", "", 0),

    USER_REGISTER("UserRegister", "user", "用户注册", 1),

    USER_SENDVALIDCODE("UserSendValidCode", "user", "发送验证码", 1),

    USER_LOGIN("UserLogin", "user", "用户登录", 1),

    USER_FORGETPWD("UserForgetPwd", "user", "用户找回密码", 1),

    USER_SETTING("UserSetting", "user", "用户设置", 1),

    USER_REAL_AUTH("UserRealAuth", "user", "用户实名认证", 1),

    USER_ABILITY_AUTH("UserAbilityAuth", "user", "用户能力认证", 1),

    USER_ADDBANK("UserAddBank", "user", "用户添加银行", 1),

    USER_WITHDRAW("UserWithdraw", "user", "用户提现", 1),

    ZUOPIN_ADD("ZuoPinAdd", "zuopin", "作品添加", 1),

    ZUOPIN_EDIT("ZuoPinAdd", "zuopin", "作品编辑", 1),

    TASK_PUBLISH("TaskPublish", "task", "发布任务", 1),
    
    TASK_CHANGE_AMOUNT("TaskChangeAmount", "task", "修改任务金额", 1),
    
    TASK_CHANGE_DATE("TaskChangeDate", "task", "修改任务天数", 1),

    TASK_EDIT("TaskEdit", "task", "修改任务", 1),

    TASK_DELETE("TaskDelete", "task", "删除任务", 1),

    PROPOSAL_SUBMIT("ProposalSubmit", "task", "提案提交", 1),

    PROPOSAL_CHOOSE("ProposalChoose", "task", "提案选取", 1),

    PROPOSAL_PAYMENT("ProposalPayment", "task", "提案付款", 1),

    PROPOSAL_RATING("ProposalRating", "task", "提案评价", 1);

    private String name;
    private String category;
    private String descr;
    private int log;

    private UserAction(String name, String category, String descr, int log) {
        this.name = name;
        this.category = category;
        this.descr = descr;
        this.log = log;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescr() {
        return descr;
    }

    public int getLog() { return log; }

    @Override
    public String toString() {
        return descr;
    }

}
