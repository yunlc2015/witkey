/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.condition.AbilityAuthCondition;
import com.kfayun.app.witkey.condition.RealAuthCondition;
import com.kfayun.app.witkey.condition.UserCondition;
import com.kfayun.app.witkey.dao.UserMapper;
import com.kfayun.app.witkey.model.AbilityAuthent;
import com.kfayun.app.witkey.model.BankAccount;
import com.kfayun.app.witkey.model.RealAuthent;
import com.kfayun.app.witkey.model.User;
import com.kfayun.app.witkey.service.UserService;

/**
 * 用户服务接口实现
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;

    // User
    @Override
    public List<User> findUserList(UserCondition cond, int num) {
        return userMapper.findUserList(cond, num);
    }

    @Override
    public PageList<User> findUserList(UserCondition cond, int pageNo, int pageSize) {
        PageList<User> pglist = new PageList<>(pageNo, pageSize);
        pglist.setTotal(userMapper.findUserCount(cond));
        pglist.setList(userMapper.findUserList(cond, pglist.getOffset(), pageSize));
        return pglist;
    }

    @Override
    public List<User> getUserListTopForProvinceGroup(
            int clazz, int type, String provinceSet, int topN) {
        
        return userMapper.getUserListTopForProvinceGroup(clazz, type, provinceSet, topN);
    }

    @Override
    public List<User> getUserListTopForCityGroup(
            int clazz, int type, String citySet, int topN) {
        
        return userMapper.getUserListTopForCityGroup(clazz, type, citySet, topN);
    }

    @Override
    public void saveUser(User user) {
        userMapper.insertUser( user);
    }

    @Override
    public int checkUserByMobile(String mobile) {
        return userMapper.checkUserByMobile(mobile);
    }

    @Override
    public User getUser(int id) {
        return userMapper.getUser(id);
    }

    @Override
    public User getUserByMobile(String mobile) {
        return userMapper.getUserByMobile(mobile);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Transactional
    @Override
    public int updateUser(User user, List<Integer> cates) {
        userMapper.updateUser( user);
        userMapper.deleteUserCategory(user.getId());
        for (int c : cates) {
            userMapper.insertUserCategory(user.getId(), c);
        }
        return 1;
    }

    @Override
    public int updateUserPwd(int id, String passwd) {
        return userMapper.updateUserPwd(id, passwd);
    }

    @Override
    public List<Integer> getUserCateIdList(int userId)
    {
        return userMapper.getUserCateIdList(userId);
    }

    @Override
    public int getUserCount(int clazz) {
        return userMapper.getUserCount(clazz);
    }

    @Override
    public List<BankAccount> getBankAccountList(int userId)
    {
        return userMapper.getBankAccountList(userId);
    }

    @Override
    public BankAccount getBankAccount(int bank, int userId) {
        return userMapper.getBankAccountByUser(bank, userId);
    }

    @Override
    public void saveBankAccount(BankAccount ba) {
        userMapper.insertBankAccount( ba);
    }

    // AbilityAuthent

    @Override
    public AbilityAuthent getAbilityAuthentByUser(int userId) {
        return userMapper.getAbilityAuthentByUser(userId);
    }

    @Override
    public void saveAbilityAuthent(AbilityAuthent ra) {
        userMapper.insertAbilityAuthent(ra);
    }

    @Override
    public AbilityAuthent getAbilityAuthent(int aid) {
        return userMapper.getAbilityAuthent(aid);
    }

    @Override
    public PageList<AbilityAuthent> findAbilityAuthentList(
        AbilityAuthCondition cond, int pageNo, int pageSize) {
            PageList<AbilityAuthent> pglist = new PageList<>(pageNo, pageSize);
        pglist.setTotal( userMapper.findAbilityAuthentCount( cond) );
        pglist.setList( userMapper.findAbilityAuthentList( cond, pglist.getOffset(), pageSize) );
        return pglist;
    }

    @Transactional
    @Override
    public int updateAbilityAuthent(AbilityAuthent ra) {
        userMapper.updateAbilityAuthent( ra);

        if (ra.getAuthState() == 2)
        {
            User user = getUser(ra.getUserId());
            user.setAbilityGrade( ra.getAuthGrade() );
            userMapper.updateUser( user);
        }

        return 1;
    }

    @Override
    public String getAbilityGradeText(int abilityGrade) {
        String text = "";
        switch (abilityGrade)
        {
            case 1:
                text = "认证设计师";
                break;
            case 2:
                text = "官方设计师";
                break;
            case 3:
                text = "明星设计师";
                break;
        }

        return text;
    }

    // RealAuthent

    @Override
    public RealAuthent getRealAuthentByUser(int userId) {
        return userMapper.getRealAuthentByUser(userId);
    }

    @Override
    public void saveRealAuthent(RealAuthent ra) {
        userMapper.insertRealAuthent(ra);
    }

    @Override
    public RealAuthent getRealAuthent(int aid) {
        return userMapper.getRealAuthent(aid);
    }

    @Override
    public PageList<RealAuthent> findRealAuthentList(
        RealAuthCondition cond, int pageNo, int pageSize) {
            PageList<RealAuthent> pglist = new PageList<>(pageNo, pageSize);
        pglist.setTotal(userMapper.findRealAuthentCount(cond) );
        pglist.setList(userMapper.findRealAuthentList(cond, pglist.getOffset(), pageSize));
        return pglist;
    }

    @Transactional
    @Override
    public int updateRealAuthent(RealAuthent ra) {
        userMapper.updateRealAuthent( ra);

        if (ra.getAuthState() == 2)
        {
            User user = getUser(ra.getUserId());
            user.setRealAuthState( 1);
            userMapper.updateUser( user);
        }

        return 1;
        
    }

    @Override
    public String getUserTz(int userId) {
        return userMapper.getUserCategory( userId); 
    }

    @Override
    public int getMaxTopNo() {
        return userMapper.getUserMaxTopNo();
    }

    @Override
    public int getMinTopNo() {
        return userMapper.getUserMinTopNo();
    }

    @Override
    public int updateUserTopNo(int id, int topNo) {

        return userMapper.updateUserTopNo(id, topNo);
    }

    @Override
    public int updateUserBalance(int id, BigDecimal amount) {

        return userMapper.updateUserBalance(id, amount);
    }
}
