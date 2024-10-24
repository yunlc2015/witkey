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

    public Admin getAdminByName(String adminName);

    public int updateAdmin(Admin admin);

    public void saveAdmin(Admin adm);

    public int deleteAdmin(int id);
    
    public List<Admin> getAdminList();

    public Admin getAdmin(int id);

}
