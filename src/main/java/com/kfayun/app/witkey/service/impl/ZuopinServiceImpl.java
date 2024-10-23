/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.condition.ZuopinCondition;
import com.kfayun.app.witkey.model.ZuoPinImage;
import com.kfayun.app.witkey.model.ZuoPinInfo;

import com.kfayun.app.witkey.service.ZuopinService;
import com.kfayun.app.witkey.dao.ZuopinMapper;

/**
 * 作品服务接口实现
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Service
public class ZuopinServiceImpl implements ZuopinService {

    @Autowired
    private ZuopinMapper zuopinMapper;

    @Transactional
    @Override
    public int saveZuoPinInfo(ZuoPinInfo info) {
        if (info.getImageList() == null || info.getImageList().isEmpty()) {
            return 0;
        }

        if (info.getId() > 0) {
            zuopinMapper.updateZuoPinInfo(info);
            zuopinMapper.deleteZuoPinImages(info.getId());
        } else {
            zuopinMapper.insertZuoPinInfo(info);
        }

        for (ZuoPinImage img : info.getImageList()) {
            img.setZuopinId(info.getId());
            zuopinMapper.insertZuoPinImage(img);
        }
        
        return 1;
       
    }

    @Override
    public int updateZuoPinInfo(ZuoPinInfo info) {
        return zuopinMapper.updateZuoPinInfo(info);
    }

    @Override
    public PageList<ZuoPinInfo> findZuoPinInfoList(
        ZuopinCondition cond, int pageNo, int pageSize) {
        if (cond.getSalesSort() == 0 && cond.getPriceSort() == 0 && cond.getCommentSort() == 0) {
            cond.setIdSort( 1);
        }

        PageList<ZuoPinInfo> pglist = new PageList<>(pageNo, pageSize);
        pglist.setTotal( zuopinMapper.findZuoPinInfoCount(cond) );
        pglist.setList( zuopinMapper.findZuoPinInfoList(cond, pglist.getOffset(), pageSize) );

        return pglist;
    }

    @Override
    public List<ZuoPinInfo> findZuoPinInfoList(ZuopinCondition cond, int num) {
        if (cond.getSalesSort() == 0 && cond.getPriceSort() == 0 && cond.getCommentSort() == 0) {
            cond.setIdSort( 1);
        }

        return zuopinMapper.findZuoPinInfoList( cond, 0, num);
    }

    @Override
    public ZuoPinInfo getZuoPinInfo(int zuopinId) {
        return zuopinMapper.getZuoPinInfo(zuopinId);
    }

    @Override
    public int getZuoPinTotalLikeByUser(int userId) {
        return zuopinMapper.getZuoPinTotalLikeByUser(userId);
    }

    @Override
    public List<ZuoPinImage> getZuoPinImageList(int zuopinId) {
        return zuopinMapper.getZuoPinImageList(zuopinId);
    }

    @Override
    public List<ZuoPinInfo> getZuoPinInfoListByUser(int userId, int num) {
        return zuopinMapper.getZuoPinInfoListByUser(userId, num);
    }

    @Transactional
    @Override
    public int deleteZuoPinInfo(int zuopinId, int userId) {
        
        int n = zuopinMapper.deleteZuoPinInfo(zuopinId, userId);
        if (n == 1) {
            zuopinMapper.deleteZuoPinImages(zuopinId);
        }
           
        return n;
    }

    @Override
    public int getMaxTopNo() {
        return zuopinMapper.getZuoPinMaxTopNo();
    }

    @Override
    public int getMinTopNo() {
        return zuopinMapper.getZuoPinMinTopNo();
    }
    
    @Override
    public int updateZuoPinTopNo(int id, int topNo) {
        return zuopinMapper.updateZuoPinTopNo(id, topNo);
    }

    @Override
    public int getZuoPinTotal() {
        return zuopinMapper.getZuoPinTotal();
    }
    
}
