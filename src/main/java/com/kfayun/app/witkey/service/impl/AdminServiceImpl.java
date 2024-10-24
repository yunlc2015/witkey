/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service.impl;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kfayun.app.witkey.model.Admin;
import com.kfayun.app.witkey.service.AdminService;
import com.kfayun.app.witkey.dao.AdminMapper;

/**
 * 管理员服务接口实现
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin getAdminByName(String name) {
        return adminMapper.getAdminByName(name);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin);
    }

    @Override
    public int deleteAdmin(int id) {
        return adminMapper.deleteAdmin(id);
    }

    @Override
    public void saveAdmin(Admin adm) {
        adminMapper.insertAdmin(adm);
    }

    @Override
    public List<Admin> getAdminList() {
        return adminMapper.getAdminList();
    }

    @Override
    public Admin getAdmin(int id) {
        return adminMapper.getAdmin(id);
    }

}
