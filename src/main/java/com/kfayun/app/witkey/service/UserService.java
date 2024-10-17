/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service;

import java.math.BigDecimal;
import java.util.List;

import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.condition.*;
import com.kfayun.app.witkey.model.*;

/**
 * 用户服务接口
 *
 * @author Billy Zhang (billy_zh@126.com)
 */
public interface UserService {

    // User

    /**
     * 查找用户
     *
     * @param cond 查询条件
     * @param num 读取条数
     * @return 用户列表
     */
    public List<User> findUserList(UserCondition cond, int num);

    /**
     * 查找用户列表（分页）
     *
     * @param cond 查询条件
     * @param pageNo 页数
     * @param pageSize 页记录数
     * @return 指定页用户列表
     */
    public PageList<User> findUserList(
            UserCondition cond, int pageNo, int pageSize);

    /**
     * 获取指定省份的用户TopN列表
     *
     * @param clazz
     * @param type 类别 1:设计师 2:设计团队
     * @param provinceSet 省份/直辖市集合
     * @param topN 数量
     * @return 用户列表
     */
    public List<User> getUserListTopForProvinceGroup(
            int clazz, int type, String provinceSet, int topN);

    /**
     * 获取指定城市的用户TopN列表 
     *
     * @param clazz 
     * @param type 类别 1:设计师 2:设计团队
     * @param citySet 市/区集合
     * @param topN 数量
     * @return 用户列表
     */
    public List<User> getUserListTopForCityGroup(
            int clazz, int type, String citySet, int topN);

    /**
     * 保存用户信息 
     *
     * @param user 用户
     */
    public void saveUser(User user);

    /**
     * 检查用户手机号
     *
     * @param mobile 手机号
     * @return 1:存在，0:不存在
     */
    public int checkUserByMobile(String mobile);

    /**
     * 获取用户
     *
     * @param id 用户ID
     * @return 用户
     */
    public User getUser(int id);

    /**
     * 通过手机号获取用户
     *
     * @param mobile 手机号
     * @return 用户
     */
    public User getUserByMobile(String mobile);

    /**
     * 更新用户信息
     *
     * @param user 用户
     * @return 1:更新成功，0:未更新
     */
    public int updateUser(User user);

    /**
     * 更新用户及类别信息
     *
     * @param user 用户
     * @param cates 类别
     * @return 1:更新成功，0:未更新
     */
    public int updateUser(User user, List<Integer> cates);

    /**
     * 更新用户密码
     *
     * @param id 用户ID
     * @param passwd
     * @return 1:更新成功，0:未更新
     */
    public int updateUserPwd(int id, String passwd);

    /**
     * 获取用户类别ID列表
     *
     * @param userId 用户ID
     * @return 类别ID列表
     */
    public List<Integer> getUserCateIdList(int userId);

    /**
     * 获取指定类型用户数
     *
     * @param clazz 类型
     * @return 统计数量
     */
    public int getUserCount(int clazz);

    // BankAccount

    /**
     * 获取用户银行帐号列表 
     *
     * @param userId 用户ID
     * @return 银行帐号列表
     */
    public List<BankAccount> getBankAccountList(int userId);

    /**
     * 获取银行帐号
     *
     * @param bankId 银行ID
     * @param userId 用户ID
     * @return 银行帐号
     */
    public BankAccount getBankAccount(int bankId, int userId);

    /**
     * 保存银行帐号 
     *
     * @param account 帐号
     */
    public void saveBankAccount(BankAccount account);

    // AbilityAuthent

    /**
     * 获取用户能力认证 
     *
     * @param userId 用户ID
     * @return 能力认证
     */
    public AbilityAuthent getAbilityAuthentByUser(int userId);

    /**
     * 保存能力认证
     *
     * @param authent 认证
     */
    public void saveAbilityAuthent(AbilityAuthent authent);

    /**
     * 获取能力认证
     *
     * @param id 认证ID
     * @return
     */
    public AbilityAuthent getAbilityAuthent(int id);

    /**
     * 分页查找能力认证
     *
     * @param cond 查询条件
     * @param pageNo 页数
     * @param pageSize 页记录数
     * @return 能力认证
     */
    public PageList<AbilityAuthent> findAbilityAuthentList(
            AbilityAuthCondition cond, int pageNo, int pageSize);

    /**
     * 更新能力认证
     *
     * @param authent 认证
     * @return 1:更新成功，0:未更新
     */
    public int updateAbilityAuthent(AbilityAuthent authent);

    /**
     * 获取能力等级描述文本
     *
     * @param grade 能力等级
     * @return 能力等级
     */
    public String getAbilityGradeText(int grade);

    // RealAuthent

    /**
     * 获取用户实名认证 
     *
     * @param userId 用户ID
     * @return 实名认证
     */
    public RealAuthent getRealAuthentByUser(int userId);

    /**
     * 保存实名认证 
     *
     * @param authent 认证
     */
    public void saveRealAuthent(RealAuthent authent);

    /**
     * 获取实名认证
     *
     * @param id 认证ID
     * @return 实名认证
     */
    public RealAuthent getRealAuthent(int id);

    /**
     * 分页查找实名认证
     *
     * @param cond 查询条件
     * @param pageNo 页数
     * @param pageSize 页记录数
     * @return 实名认证列表
     */
    public PageList<RealAuthent> findRealAuthentList(
            RealAuthCondition cond, int pageNo, int pageSize);

    /**
     * 更新实名认证
     *
     * @param authent 认证
     * @return 1:更新成功，0:未更新
     */
    public int updateRealAuthent(RealAuthent authent);

    /**
     * 
     * @param userId
     * @return
     */
    public String getUserTz(int userId);

    /**
     * 
     * @return
     */
    public int getMaxTopNo();

    /**
     * 
     * @return
     */
    public int getMinTopNo();

    /**
     * 更新用户余额
     *
     * @param userId 用户ID
     * @param amount 要增/减的金额
     * @return 1:更新成功，0:未更新
     */
    public int updateUserBalance(int userId, BigDecimal amount);

}
