/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service;

import java.util.List;
import java.util.Map;

import com.kfayun.app.witkey.model.Admin;

/**
 * 管理员服务接口
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public interface AdminService {

    public Admin getAdminByPasswd(String adminName, String encryptPasswd);

    public Admin getAdminByName(String adminName);

    public int updateAdmin(Admin admin);

    public void saveAdmin(Admin adm);

    public List<Admin> getAdmins();

    public Admin getAdmin(int id);

    public Map<Integer, String> getPermissions();

    public List<Integer> getAdminPermissions(int adminId);

    public int updateAdminPermissions(int adminId, int[] perms);

}
