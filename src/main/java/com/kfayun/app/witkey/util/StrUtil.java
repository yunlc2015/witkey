/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.util;

import java.util.List;
import java.util.Random;
import java.util.Arrays;

/**
 * 字符串处理工具类
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public final class StrUtil {

	private static final String NUMBER = "123456789";
	private static final String ALPHA_NUMBER = "abcdefg123hijklmn456opqrst789uvwxyz0";
	
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	public static String maskMobile(String mobile) {
		return mobile.substring(0, 3) + "xxxx" + mobile.substring(7);
	}

	public static String getRandomNumber(int len) {
		
		Random rnd = new Random();
		
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<len; i++) {
			sb.append(NUMBER.charAt(rnd.nextInt(NUMBER.length())));
		}
		
		return sb.toString();
	}
	
	public static String getRandomString(int len) {
		
		Random rnd = new Random();
		
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<len; i++) {
			sb.append(ALPHA_NUMBER.charAt(rnd.nextInt(ALPHA_NUMBER.length())));
		}
		
		return sb.toString();
	}

	public static String join(String[] strs, String sep) {
		if (strs == null || strs.length == 0)
			return "";
		
		return join(Arrays.asList(strs), sep);
	}

	public static String join(List<String> list, String sep) {
		if (list == null || list.size() == 0)
			return "";
		
		int i=0;
		StringBuffer sb = new StringBuffer();
		for (String name : list) {
			if (i>0)
				sb.append(sep);
			sb.append(name);
			++i;
		}
		
		return sb.toString();
	}
}
