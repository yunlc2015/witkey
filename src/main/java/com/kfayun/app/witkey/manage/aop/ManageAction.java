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

import com.kfayun.app.witkey.util.StrUtil;
import com.kfayun.app.witkey.manage.vo.ManageActionVO;

/**
 * 管理操作
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public enum ManageAction {

    NONE("None", "", "", 0),
    ADM_LOGIN("AdmLogin", "", "用户登录", 1),
    ADM_LOGOUT("AdmLogout", "", "用户退出", 1),

    //***** Operate action */
    OPS_ARTICLE_VIEW("OpsArticleView", "operate", "文章查看", 0),
    OPS_ARTICLE_EDIT("OpsArticleEdit", "operate", "文章编辑", 1),

    OPS_BANNER_VIEW("OpsBannerView", "operate", "Banner查看", 0),
    OPS_BANNER_ADD("OpsBannerAdd", "operate", "Banner添加", 1),
    OPS_BANNER_EDIT("OpsBannerEdit", "operate", "Banner编辑", 1),
    OPS_BANNER_DELETE("OpsBannerDelete", "operate", "Banner删除", 1),

    OPS_BDINFO_VIEW("OpsBdinfoView", "operate", "推广查看", 0),
    OPS_BDINFO_ADD("OpsBdinfoAdd", "operate", "推广添加", 1),
    OPS_BDINFO_EDIT("OpsBdinfoEdit", "operate", "推广编辑", 1),
    OPS_BDINFO_DELETE("OpsBdinfoDelete", "operate", "推广删除", 1),
    
    OPS_REQUIRE_VIEW("OpsRequireView", "operate", "需求查看", 0),
    
    OPS_TOUSU_VIEW("OpsTousuView", "operate", "投诉查看", 0),

    //***** Task action */
    TSK_VIEW("TaskView", "task", "任务查看", 0),
    TSK_AUDIT("TaskAudit", "task", "任务审核", 1),
    TSK_PAYMENT("TaskPayment", "task", "任务付款", 1),
    TSK_REFUND("TaskRefunc", "task", "任务退款", 1),
    TSK_SETTLE("TaskSettle", "task", "任务结算", 1),

    TSK_PROJECT_VIEW("TaskProjectView", "task", "提案（项目）查看", 0),

    TSK_RATING_VIEW("TaskRatingView", "task", "评价查看", 0),

    //***** Finance action */
    FIN_TRADE_VIEW("FinTradeView", "finance", "交易查看", 0),

    FIN_PAYMENT_VIEW("FinPaymentView", "finance", "付款查看", 0),

    FIN_WITHDRAWAL_VIEW("FinWithdrawalView", "finance", "提现查看", 0),
    FIN_WITHDRAWAL_SETTLE("FinWithdrawalSettle", "finance", "提现结算", 1),

    FIN_INVOICE_VIEW("FinInvoiceView", "finance", "发票查看", 0),

    //***** User action */
    USR_DESIGNER_VIEW("UserDesignerView", "user", "设计师查看", 0),
    USR_EMPLOYER_VIEW("UserEmployerView", "user", "雇主查看", 0),

    USR_REALAUTHENT_VIEW("UserRealAuthentView", "user", "实名认证查看", 0),
    USR_REALAUTHENT_AUDIT("UserRealAuthentAudit", "user", "实名认证审核", 1),

    USR_ABILITYAUTHENT_VIEW("UserAbilityAuthentView", "user", "实力认证查看", 0),
    USR_ABILITYAUTHENT_AUDIT("UserAbilityAuthentAudit", "user", "实力认证审核", 1),

    USR_ZUOPIN_VIEW("UserZuoPinView", "user", "用户作品查看", 0),

    //***** System action */
    SYS_GENERALSETTINGS_VIEW("SysGeneralSettingsView", "system", "一般设置查看", 0),
    SYS_GENERALSETTINGS_UPDATE("SysGeneralSettingsUpdate", "system", "一般设置更新", 1),

    SYS_PAYSETTINGS_VIEW("SysPaySettingsView", "system", "支付设置查看", 0),
    SYS_PAYSETTINGS_UPDATE("SysPaySettingsUpdate", "system", "支付设置更新", 1),
    
    SYS_ACTIONLOG_VIEW("SysActionLogView", "system", "系统日志查看", 0),

    SYS_VERIFYCODE_VIEW("SysVerifyCodeView", "system", "校验码查看", 0),

    SYS_ADMIN_VIEW("SysAdminView", "system", "管理员查看", 0),
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
        put("operate", "运营管理模块");
        put("task", "任务管理模块");
        put("finance", "财务管理模块");
        put("user", "用户管理模块");
        put("system", "系统管理模块");
    }};

    private static Map<String, List<ManageActionVO>> _actionGroupMap;

    public static Map<String, List<ManageActionVO>> actionGroupMap() {
        if (_actionGroupMap != null) {
            return _actionGroupMap;
        }

        List<ManageActionVO> list = new ArrayList<>();
        for (ManageAction oper : ManageAction.values()) {
            // 不需要权限控制的操作。
            if (StrUtil.isEmpty(oper.category)) {
                continue;
            }
            list.add(new ManageActionVO(oper) );
        }
        _actionGroupMap = list.stream().collect(Collectors.groupingBy(
            action -> action.getCategory()
        ));
        return _actionGroupMap;
    }
}
