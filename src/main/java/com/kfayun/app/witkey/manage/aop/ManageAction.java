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
 * @author billy (billy_zh@126.com)
 */
public enum ManageAction {

    NONE("none", "", "", 0),

    USER_LOGIN("UserLogin", "base", "用户登录", 1),
    USER_LOGOUT("UserLogout", "base", "用户退出", 1),

    USER_VIEW("UserView", "user", "用户查看", 0),
    USER_MANAGE("UserManage", "user", "用户管理", 1),
    ROLE_VIEW("RoleView", "user", "角色查看", 0),
    ROLE_MANAGE("RoleManage", "user", "角色管理", 1),

    ACTION_VIEW("ActionView", "system", "系统日志查看", 0),
    SETTINGS_VIEW("SettingsView", "system", "全局设置查看", 0),
    SETTINGS_UPDATE("SettingsUpdate", "system", "全局设置更新", 1);
    
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
            if (ManageAction.USER_LOGIN == oper ||
                    ManageAction.USER_LOGOUT == oper ||
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
