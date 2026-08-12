package com.war.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 时间&日期服务
 */

public class DateService {

	private static Logger logger = Logger.getLogger(DateService.class);
	
	/**
	 * 获得当前时间，返回java.util.Date
	 * @return
	 */
	public static java.util.Date getCurrentUtilDate(){
		return Calendar.getInstance().getTime();
	}

	/**
	 * 获得当前时间，返回java.sql.Date
	 * @return
	 */
	public static java.sql.Date getCurrentSqlDate(){
		return new java.sql.Date(System.currentTimeMillis());
	}
	
	/**
	 * 获得当前时间，返回字符串(yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static String getCurrentDateAsString(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(getCurrentUtilDate());	
	}
	
	/**
	 * 获得当前时间，返回用户自定义格式字符串
	 * @param formatStr
	 * @return
	 */
	public static String getCurrentDateAsStringCustom(String formatStr){
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		return formatter.format(getCurrentUtilDate());	
	}
	
	/**
	 * 将日期转换为字符串默认格式:yyyy-MM-dd HH:mm:ss)
	 * @param date
	 * @return
	 */
	public static String parseDateToString(java.util.Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);	
	}
	
	/**
	 * 将日期转换成报告使用的日期字符串
	 * @param date
	 * @return
	 */
	public static String parseDateToReportTimeString(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		return formatter.format(date);
	}
	
	/**
	 * 将字符串转换为日期java.sql.Date)
	 * @param dateStr
	 * @return
	 */
	public static java.sql.Date parseStringToSqlDate(String dateStr){
		return java.sql.Date.valueOf(dateStr);
	}
	
	/**
	 * 获得指定格式的日期对象
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static java.util.Date changeDateFormat(Date date, String formatStr) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		try {
			date =  formatter.parse(formatter.format(date));
		} catch (ParseException e) {
			logger.error("转换日期格式失败!", e);
		}
		
		return date;
	}
	
	/**
	 * 将时间(毫秒)转化为字符串
	 * @param time
	 * @return 格式 (XX时XX分XX秒)
	 */
	public static String parseTimeToString(long time){
		StringBuffer stringBuffer = new StringBuffer();
		long hour = 0,minute = 0,second = 0;
		time = time/1000;
		if(time>=3600){
			hour = time/3600;
			stringBuffer.append(hour);
			stringBuffer.append("时");
		}
		if(time>=60){
			minute = (time-(hour*3600))/60;
			stringBuffer.append(minute);
			stringBuffer.append("分");
		}
		second = time-(hour*3600)-(minute*60);
		stringBuffer.append(second);
		stringBuffer.append("秒");
		return stringBuffer.toString();
	}
	
}
