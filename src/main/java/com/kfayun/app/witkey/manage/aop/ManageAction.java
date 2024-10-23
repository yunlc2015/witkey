/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage.aop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 管理操作
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public enum ManageAction {

    NONE("none", "", "", 0),

    USR_LOGIN("UsrLogin", "user", "用户登录", 1),
    USR_LOGOUT("UsrLogout", "user", "用户退出", 1),
    USR_VIEW("UsrView", "user", "用户查看", 0),
    USR_MANAGE("UsrManage", "user", "用户管理", 1),
    USR_REALAUTHENT_AUDIT("UsrRealAuthentAudit", "user", "用户实名认证审核", 1),
    USR_ABILITYAUTHENT_AUDIT("UsrAbilityAuthentAudit", "user", "用户实力认证审核", 1),

    OPS_BANNER_ADD("OpsBannerAdd", "operate", "Banner添加", 1),
    OPS_BANNER_EDIT("OpsBannerEdit", "operate", "Banner编辑", 1),
    OPS_BANNER_DELETE("OpsBannerDelete", "operate", "Banner删除", 1),
    OPS_ARTICLE_EDIT("OpsArticleEdit", "operate", "文章编辑", 1),
    OPS_BDINFO_ADD("OpsBdinfoAdd", "operate", "推广添加", 1),
    OPS_BDINFO_EDIT("OpsBdinfoEdit", "operate", "推广编辑", 1),
    OPS_BDINFO_DELETE("OpsBdinfoDelete", "operate", "推广删除", 1),
    
    TSK_MANAGE("TskManage", "task", "任务管理", 0),
    TSK_AUDIT("TskAudit", "task", "任务审核", 1),
    TSK_PAYMENT("TskPayment", "task", "任务付款", 1),
    TSK_REFUND("TskRefunc", "task", "任务退款", 1),
    TSK_SETTLE("TskSettle", "task", "任务结算", 1),

    FIN_WITHDRAWAL_SETTLE("FinWithdrawalSettle", "finance", "提现结算", 1),

    SYS_ACTIONLOG_VIEW("SysActionLogView", "system", "系统日志查看", 0),
    SYS_GENERALSETTINGS_VIEW("SysGeneralSettingsView", "system", "一般设置查看", 0),
    SYS_GENERALSETTINGS_UPDATE("SysGeneralSettingsUpdate", "system", "一般设置更新", 1),
    SYS_PAYSETTINGS_VIEW("SysPaySettingsView", "system", "支付设置查看", 0),
    SYS_PAYSETTINGS_UPDATE("SysPaySettingsUpdate", "system", "支付设置更新", 1),
    SYS_ADMIN_ADD("SysAdminAdd", "system", "管理员添加", 1),
    SYS_ADMIN_EDIT("SysAdminEdit", "system", "管理员编辑", 1),
    SYS_ADMIN_DELETE("SysAdminDelete", "system", "管理员删除", 1);
    
    private String name;
    private String category;
    private String descr;
    private int log;

    private ManageAction(String name, String category, String descr, int log) {
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

    public static Map<String, String> actionCateMap = new HashMap<String, String>() {{
        put("app", "应用管理模块");
        put("user", "用户管理模块");
        put("system", "系统管理模块");
    }};

    private static Map<String, List<ManageAction>> _actionGroupMap;

    public static Map<String, List<ManageAction>> actionGroupMap() {
        if (_actionGroupMap != null) {
            return _actionGroupMap;
        }

        List<ManageAction> list = new ArrayList<>();
        for (ManageAction oper : ManageAction.values()) {
            // 不需要权限控制的操作。
            if (ManageAction.USR_LOGIN == oper ||
                    ManageAction.USR_LOGOUT == oper ||
                    ManageAction.NONE == oper) {
                continue;
            }
            list.add(oper);
        }
        _actionGroupMap = list.stream().collect(Collectors.groupingBy(
            action -> action.getCategory()
        ));
        return _actionGroupMap;
    }
}
