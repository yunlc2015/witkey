/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理工具类
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public class DateUtil {

	public static int getYear() {
		
		return getYear(new Date());
	}
	
	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}


	public static boolean isSameDay(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		
		return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
				cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
	}
	
	public static boolean isSameYear(Date date1, Date date2) {
		return getYear(date1) == getYear(date2);
	}
	
	public static String toStringNoSep(Date date) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		return fmt.format(date);
	}
	
	public static String toDateStringNoSep(Date date) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		return fmt.format(date);
	}

	public static String toMonthStringNoSep(Date date) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM");
		return fmt.format(date);
	}

	public static String toDateTimeString(Date date) {
		if (date == null)
			return "";

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return fmt.format(date);
	}
	
	public static Date fromDateTimeString(String str) {
		if (str == null)
			return null;

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return fmt.parse(str);
		} catch (ParseException pe) {
			pe.printStackTrace();
			return null;
		}
	}
	
	public static Date fromDateString(String str) {
		if (str == null)
			return null;

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(str);
		} catch (ParseException pe) {
			pe.printStackTrace();
			return null;
		}
	}

	public static String toMonthString(Date date) {
		if (date == null)
			return "";

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM");
		return fmt.format(date);
	}
	
	public static String toDateString(Date date) {
		if (date == null)
			return "";

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(date);
	}
	
	public static String toMmDdString(Date date) {
		if (date == null)
			return "";

		SimpleDateFormat fmt = new SimpleDateFormat("MM-dd");
		return fmt.format(date);
	}
	
	public static String toHhMmString(Date date) {
		if (date == null)
			return "";

		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
		return fmt.format(date);
	}

	public static String toUTCString(Date date) {
		
		if (date == null)
			return "";

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		return fmt.format(date);
	}
	
}
