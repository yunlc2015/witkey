/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kfayun.app.witkey.PageList;
import com.kfayun.app.witkey.dao.SysMapper;
import com.kfayun.app.witkey.model.ActionLog;
import com.kfayun.app.witkey.model.SettingItem;
import com.kfayun.app.witkey.model.Settings;
import com.kfayun.app.witkey.service.SysService;

/**
 * 系统服务接口实现
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Service
public class SysServiceImpl implements SysService {
    
     private static final Logger log = LoggerFactory.getLogger(SysServiceImpl.class);

    @Autowired
    private SysMapper sysMapper;

    @Override
	public List<SettingItem> getSettingItemList() {
		return sysMapper.getSettingItemList();
	}

	@Override
	public Settings getSettings() {
		List<SettingItem> list = sysMapper.getSettingItemList();
		
		Settings sett = new Settings();
		Map<String, SettingItem> map = list.stream()
				.collect(Collectors.toMap(SettingItem::getKey, (item)->item));

		for (String key : map.keySet()) {
			String value = map.get(key).getValue();

			if ("appName".equalsIgnoreCase(key)) {
				sett.setAppName(value);
			} else if ("appPcLogo".equalsIgnoreCase(key)) {
				sett.setAppPcLogo(value);
			} else if ("appMobLogo".equalsIgnoreCase(key)) {
				sett.setAppMobLogo(value);
			} else if ("appFootLogo".equalsIgnoreCase(key)) {
				sett.setAppFootLogo(value);
			} else if ("seoKeywords".equalsIgnoreCase(key)) {
				sett.setSeoKeywords(value);
			} else if ("seoDescription".equalsIgnoreCase(key)) {
				sett.setSeoDescription(value);
			} else if ("statisScript".equalsIgnoreCase(key)) {
				sett.setStatisScript(value);
			} else if ("cookieDomain".equalsIgnoreCase(key)) {
				sett.setCookieDomain(value);
			} else if ("cookieName".equalsIgnoreCase(key)) {
				sett.setCookieName(value);
			} else if ("cookieSecret".equalsIgnoreCase(key)) {
				sett.setCookieSecret(value);
			} else if ("serviceTel".equalsIgnoreCase(key)) {
				sett.setServiceTel(value);
			} else if ("icpBeianNo".equalsIgnoreCase(key)) {
				sett.setIcpBeianNo(value);
			} else if ("wxQrcode".equalsIgnoreCase(key)) {
				sett.setWxQrcode(value);

			} else if ("alipayEnable".equalsIgnoreCase(key)) {
				sett.setAlipayEnable(Integer.parseInt(value)); 
			} else if ("alipayVersion".equalsIgnoreCase(key)) {
				sett.setAlipayVersion(value);
			} else if ("alipaySellerEmail".equalsIgnoreCase(key)) {
				sett.setAlipaySellerEmail(value);
			} else if ("alipayAppid".equalsIgnoreCase(key)) {
				sett.setAlipayAppid(value);
			} else if ("alipaySigntype".equalsIgnoreCase(key)) {
				sett.setAlipaySigntype(value);
			} else if ("alipayRsaPrivatekey".equalsIgnoreCase(key)) {
				sett.setAlipayRsaPrivatekey(value);
			} else if ("alipayRsaPublickey".equalsIgnoreCase(key)) {
				sett.setAlipayRsaPublickey(value);
			} else if ("alipayMd5Key".equalsIgnoreCase(key)) {
				sett.setAlipayMd5Key(value);
			} else if ("alipayNotifyUrl".equalsIgnoreCase(key)) {
				sett.setAlipayNotifyUrl(value);
			} else if ("wxpayEnable".equalsIgnoreCase(key)) {
				sett.setWxpayEnable(Integer.parseInt(value)); 
			} else if ("wxpayAppid".equalsIgnoreCase(key)) {
				sett.setWxpayAppid(value);
			} else if ("wxpayAppsecret".equalsIgnoreCase(key)) {
				sett.setWxpayAppsecret(value);
			} else if ("wxpayMchid".equalsIgnoreCase(key)) {
				sett.setWxpayMchid(value);
			} else if ("wxpayMchkey".equalsIgnoreCase(key)) {
				sett.setWxpayMchkey(value);
			} else if ("wxpayNotifyUrl".equalsIgnoreCase(key)) {
				sett.setWxpayNotifyUrl(value);
			}
		}
		return sett;
	}

	@Override
	public int updateSettings(Map<String, String> data) {
		int n = 0;
		for (String key : data.keySet()) {
			n += sysMapper.updateSettingsByKey(key, data.get(key));
		}
		return n;
	}
    
    @Override
    public void saveActionLog(ActionLog log) {
        sysMapper.insertActionLog(log);
    }

    @Override
    public PageList<ActionLog> findActionLogList(
            String category, String keyword, int pageNo, int pageSize) {
        PageList<ActionLog> plist = new PageList<>(pageNo, pageSize);
        plist.setTotal(sysMapper.findActionLogCount(category, keyword));
        if (plist.getTotal() > 0) {
            plist.setList( sysMapper.findActionLogList(category, keyword, plist.getOffset(), pageSize) );
        }

        return plist;
    }

    @Override
    public ActionLog getActionLog(int id) {
        return sysMapper.getActionLog(id);
    }
    
}
