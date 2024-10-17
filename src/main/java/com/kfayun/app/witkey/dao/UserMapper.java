/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.math.BigDecimal;

import com.kfayun.app.witkey.condition.AbilityAuthCondition;
import com.kfayun.app.witkey.condition.RealAuthCondition;
import com.kfayun.app.witkey.condition.UserCondition;
import com.kfayun.app.witkey.model.AbilityAuthent;
import com.kfayun.app.witkey.model.BankAccount;
import com.kfayun.app.witkey.model.RealAuthent;
import com.kfayun.app.witkey.model.User;

/**
 * 用户相关实体Mapper
 * 
 * @author billy (billy_zh@126.com)
 */
public interface UserMapper {
    // User
    public List<User> findUserList(
        @Param("cond")UserCondition cond, 
        @Param("limit")int limit);

    public int findUserCount(
        @Param("cond")UserCondition cond);

    public List<User> findUserList(
        @Param("cond")UserCondition cond, 
        @Param("offset")int offset,
        @Param("limit")int limit);

    public List<User> getUserListTopForProvinceGroup(
        @Param("clazz")int clazz,
        @Param("type")int type,
        @Param("provinceSet")String provinceSet,
        @Param("topN")int topN);

    public List<User> getUserListTopForCityGroup(
        @Param("clazz")int clazz,
        @Param("type")int type,
        @Param("citySet")String citySet,
        @Param("topN")int topN);

    public void insertUser(User user);

    public int checkUserByMobile(
        @Param("mobile")String mobile);

    public User getUser(
        @Param("id")int id);

    public User getUserByMobile(
        @Param("mobile")String mobile);

    public int updateUser(User user);

    public int deleteUserCategory(
        @Param("userId")int userId);

    public void insertUserCategory(
        @Param("userId")int userId, 
        @Param("cateId")int cateId);

    public int updateUserPwd(
        @Param("id")int id, 
        @Param("passwd")String passwd);

    public List<Integer> getUserCateIdList(
        @Param("userId")int userId);

    public int updateUserBalance(
        @Param("id")int userId, 
        @Param("amount")BigDecimal amount);

    public int getUserCount(
        @Param("clazz")int clazz);

    public List<BankAccount> getBankAccountList(
        @Param("userId")int userId);

    public BankAccount getBankAccountByUser(
        @Param("bankId")int bankId, 
        @Param("userId")int userId);

    public void insertBankAccount(BankAccount ba);

    // AbilityAuthent

    public AbilityAuthent getAbilityAuthentByUser(
        @Param("userId")int userId);

    public void insertAbilityAuthent(AbilityAuthent ra);

    public AbilityAuthent getAbilityAuthent(
        @Param("aid")int aid);

    public int findAbilityAuthentCount(
        @Param("cond")AbilityAuthCondition cond);

    public List<AbilityAuthent> findAbilityAuthentList(
        @Param("cond")AbilityAuthCondition cond, 
        @Param("offset")int offset,
        @Param("limit")int limit);

    public int updateAbilityAuthent(AbilityAuthent ra);

    // RealAuthent

    public RealAuthent getRealAuthentByUser(
        @Param("userId")int userId);

    public void insertRealAuthent(RealAuthent ra);

    public RealAuthent getRealAuthent(
        @Param("aid")int aid);

    public int findRealAuthentCount(
        @Param("cond")RealAuthCondition cond);

    public List<RealAuthent> findRealAuthentList(
        @Param("cond")RealAuthCondition cond, 
        @Param("offset")int offset, 
        @Param("limit")int limit);

    public int updateRealAuthent(RealAuthent ra);

    public String getUserCategory(
        @Param("userId")int userId);

    public int getUserMaxTopNo();

    public int getUserMinTopNo();
}
