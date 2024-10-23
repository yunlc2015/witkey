/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kfayun.app.witkey.model.Admin;

/**
 * 管理员相关实体Mapper
 * 
 * @author billy (billy_zh@126.com)
 */
public interface AdminMapper {

    public Admin getAdminByName(
        @Param("name")String name);

    public int updateAdmin(Admin admin);

    public void insertAdmin(Admin adm);

    public List<Admin> getAdminList();

    public int deleteAdmin(
        @Param("id")int id);

    public Admin getAdmin(
        @Param("id")int id);

    public List<Object[]> getPermissions();

    public List<Integer> getAdminPermissions(
        @Param("adminId")int adminId);

    public int deleteAdminPermissions(
        @Param("adminId")int adminId);

    public void insertAdminPermission(
        @Param("adminId")int adminId, 
        @Param("permission")int permission);

}
