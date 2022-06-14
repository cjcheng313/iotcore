package com.ccj.common.utils;

import com.ccj.common.exception.BusinessException;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @since 2020/1/16
 */
public class DateUtils {

	/** 毫秒 */
	public final static long MS = 1;
	/** 每秒钟的毫秒数 */
	public final static long SECOND_MS = MS * 1000;
	/** 每分钟的毫秒数 */
	public final static long MINUTE_MS = SECOND_MS * 60;
	/** 每小时的毫秒数 */
	public final static long HOUR_MS = MINUTE_MS * 60;
	/** 每天的毫秒数 */
	public final static long DAY_MS = HOUR_MS * 24;
	
	public final static String NORM_YEAR_PATTERN = "yyyy";
	public final static String NORM_MONTH_PATTERN = "yyyy-MM";
	public final static String MONTH_DATE_PATTERN = "MM-dd";
	public final static String MONTH_NONE_DATE_PATTERN = "MMdd";
	/** 标准日期格式 */
	public final static String NORM_DATE_PATTERN = "yyyy-MM-dd";
	/** 标准时间格式 */
	public final static String NORM_TIME_PATTERN = "HH:mm:ss";
	/** 标准日期时间格式，精确到分 */
	public final static String NORM_DATETIME_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
	public final static String NORM_DATETIME_00_PATTERN = "yyyy-MM-dd HH:00";
	/** 标准日期时间格式，精确到秒 */
	public final static String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public final static String NORM_TIMESTAMP_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS Z";
	/** 标准日期时间格式，精确到毫秒 */
	public final static String NORM_DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
	public final static String NORM_DATETIME_MS_PATTERN_1 = "yyyyMMddHHmmss";
    public final static String NORM_DATETIME_MS_PATTERN_2 = "yyyyMMdd";
	/** HTTP头中日期时间格式 */
	public final static String HTTP_DATETIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";
	public final static String NORM_DATETIME_PATTERN1 = "M月d日 HH:mm:ss";

	public final static SimpleDateFormat YYYY_MM_DD_FORMAT = new SimpleDateFormat(NORM_DATE_PATTERN);
	public final static SimpleDateFormat YYYYMMDD_FORMAT = new SimpleDateFormat(NORM_DATETIME_MS_PATTERN_2);

	/**
	 * 获取当前时间
	 * @return: String
	 */
	public static String getNowToString(){
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.NORM_DATETIME_PATTERN);
		return sdf.format(new Date());
	}
	
	public static String getDateFormat(Date date , String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static String getDateFormat(String date , String format) {
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date2 = sdf.parse(date);
			return sdf.format(date2);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String getMonthDateFormat(String date){
		if(StringUtils.isEmpty(date)){
			return null;
		}
		return date.substring(5, date.length());
	}

	
	
	/**
	 * 按格式获取当前时间
	 * @return: String
	 */
	public static String getNowFormat(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
	/**
	 * 比较两个日期的大小(yyyy-MM-dd)
	 * 当date1大于date2 时返回1  等于返回0 小于返回-1
	 * @return: void
	 */
	public static int compareDate(Date date1 , Date date2){
		SimpleDateFormat sdf=new SimpleDateFormat(DateUtils.NORM_DATE_PATTERN);
		String str1 = sdf.format(date1);
		String str2 = sdf.format(date2);
		return compareDate(str1 , str2);
	}

	/**
	 * 比较两个日期的大小(yyyy-MM-dd HH:mm:ss)
	 * 当date1大于date2 时返回1  等于返回0 小于返回-1
	 * @return: void
	 */
	public static int compareTimestamp(Date date1 , Date date2){
		SimpleDateFormat sdf=new SimpleDateFormat(DateUtils.NORM_DATETIME_PATTERN);
		String str1 = sdf.format(date1);
		String str2 = sdf.format(date2);
		return compareTimestamp(str1 , str2);
	}

	/**
	 * 比较两个日期的大小(yyyy-MM-dd HH:mm:ss)
	 * 当date1大于date2 时返回1  等于返回0 小于返回-1
	 * @return: void
	 */
	public static int compareTimestamp(String date1 , String date2){
		try{
			SimpleDateFormat sdf=new SimpleDateFormat(DateUtils.NORM_DATETIME_PATTERN);
			Date bt=sdf.parse(date1);
			Date et=sdf.parse(date2);
			return bt.compareTo(et);
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 比较两个日期的大小(yyyy-MM-dd)
	 * 当date1大于date2 时返回1  等于返回0 小于返回-1
	 * @return: void
	 */
	public static int compareDate(String date1 , String date2){
		try{
			SimpleDateFormat sdf=new SimpleDateFormat(DateUtils.NORM_DATE_PATTERN);
			Date bt=sdf.parse(date1); 
			Date et=sdf.parse(date2);
			return bt.compareTo(et);
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 获取两个时间之间的所有时间
	 * @return: List<String>
	 */
	public static List<String> getMonthBetweenDate(String startTime , String endTime , String format){
		List<String> listDate = new ArrayList<String>();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);  
			listDate.add(startTime);
			Date start = sdf.parse(startTime);
			Date end = sdf.parse(endTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(start);
			while(end.getTime() > calendar.getTime().getTime()){
				calendar.add(Calendar.MONTH, 1);//增加一个月
				listDate.add(sdf.format(calendar.getTime()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listDate;
	}
	
	/**
	 * 获取两个时间之间的所有时间
	 * @return: List<String>
	 */
	public static List<String> getDayBetweenDate(String startTime , String endTime , String format){
		List<String> listDate = new ArrayList<String>();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);  
			listDate.add(startTime);
			Date start = sdf.parse(startTime);
			Date end = sdf.parse(endTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(start);
			while(end.getTime() > calendar.getTime().getTime()){
				calendar.add(Calendar.DATE, 0);//增加一个月
				listDate.add(sdf.format(calendar.getTime()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listDate;
	}
	
	
	/**
	 * 得到日期的年月日数组
	 * 年月日
	 * @return: void
	 */
	public static String[] separateDate(String date,String type){
		String[] strNow = new String[3];
		try {
			if("y".equals(type)){
				strNow[0] = new SimpleDateFormat("yyyy").format(new SimpleDateFormat("yyyy").parse(date)).toString();
			}
			if("m".equals(type)){
				String strdate = new SimpleDateFormat("yyyy-MM").format(new SimpleDateFormat("yyyy-MM").parse(date))+"-\"\"";
				strNow = strdate.toString().split("-");
				strNow[2] = "";
			} 
			if("d".equals(type)){
				strNow = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(date)).toString().split("-");
			}
			return strNow;
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 转成中文年月日
	 * @return: String
	 */
	public static String changeChinaDate(String dateTime){
		String[] dates = dateTime.split("-");
		return Integer.parseInt(dates[0])+"年"+Integer.parseInt(dates[1])+"月"+Integer.parseInt(dates[2])+"日";
	}

	
	/** 标准日期（不含时间）格式化器 */
	// private final static SimpleDateFormat NORM_DATE_FORMAT = new
	// SimpleDateFormat(NORM_DATE_PATTERN);
	private static ThreadLocal<SimpleDateFormat> NORM_DATE_FORMAT = new ThreadLocal<SimpleDateFormat>() {
		synchronized protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(NORM_DATE_PATTERN);
		};
	};
	/** 标准时间格式化器 */
	// private final static SimpleDateFormat NORM_TIME_FORMAT = new
	// SimpleDateFormat(NORM_TIME_PATTERN);
	private static ThreadLocal<SimpleDateFormat> NORM_TIME_FORMAT = new ThreadLocal<SimpleDateFormat>() {
		synchronized protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(NORM_TIME_PATTERN);
		};
	};
	/** 标准日期时间格式化器 */
	// private final static SimpleDateFormat NORM_DATETIME_FORMAT = new
	// SimpleDateFormat(NORM_DATETIME_PATTERN);
	private static ThreadLocal<SimpleDateFormat> NORM_DATETIME_FORMAT = new ThreadLocal<SimpleDateFormat>() {
		synchronized protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(NORM_DATETIME_PATTERN);
		};
	};
	/** HTTP日期时间格式化器 */
	// private final static SimpleDateFormat HTTP_DATETIME_FORMAT = new
	// SimpleDateFormat(HTTP_DATETIME_PATTERN, Locale.US);
	private static ThreadLocal<SimpleDateFormat> HTTP_DATETIME_FORMAT = new ThreadLocal<SimpleDateFormat>() {
		synchronized protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(HTTP_DATETIME_PATTERN, Locale.US);
		};
	};
	public static String format(Date date) {
		return format(date, NORM_DATE_PATTERN);
	}
	/**
	 * 当前时间，格式 yyyy-MM-dd HH:mm:ss
	 * @return 当前时间的标准形式字符串
	 */
	public static String now() {
		return formatDateTime(new Date());
	}
	/**
	 * 当前时间long
	 * @param isNano 是否为高精度时间
	 * @return 时间
	 */
	public static long current(boolean isNano) {
		return isNano ? System.nanoTime() : System.currentTimeMillis();
	}
	/**
	 * 当前日期，格式 yyyy-MM-dd
	 * @return 当前日期的标准形式字符串
	 */
	public static String today() {
		return formatDate(new Date());
	}
	/**
	 * @return 当前月份
	 */
	public static int thisMonth() {
		return month(date());
	}
	/**
	 * @return 今年
	 */
	public static int thisYear() {
		return year(date());
	}
	/**
	 * @return 当前时间
	 */
	public static Date date() {
		return new Date();
	}
	/**
	 * Long类型时间转为Date
	 * @param date Long类型Date（Unix时间戳）
	 * @return 时间对象
	 */
	public static Date date(long date) {
		return new Date(date);
	}
	/**
	 * 获取YYYYMMDD格式
	 * @return
	 */
	public static String getDays() {
		return format(new Date(), "yyyyMMdd");
	}
	/**
	 * 转换为Calendar对象
	 * @param date 日期对象
	 * @return Calendar对象
	 */
	public static Calendar toCalendar(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	/**
	 * 获得月份，从1月开始计数
	 * @param date 日期
	 * @return 月份
	 */
	public static int month(Date date) {
		return toCalendar(date).get(Calendar.MONTH) + 1;
	}
	/**
	 * 获得年
	 * @param date 日期
	 * @return 年
	 */
	public static int year(Date date) {
		return toCalendar(date).get(Calendar.YEAR);
	}
	/**
	 * 获得季节
	 * @param date 日期
	 * @return 第几个季节
	 */
	public static int season(Date date) {
		return toCalendar(date).get(Calendar.MONTH) / 3 + 1;
	}
	/**
	 * 获得指定日期年份和季节<br>
	 * 格式：[20131]表示2013年第一季度
	 * @param date 日期
	 * @return Season ，类似于 20132
	 */
	public static String yearAndSeason(Date date) {
		return yearAndSeason(toCalendar(date));
	}
	/**
	 * 获得指定日期区间内的年份和季节<br>
	 * @param startDate 其实日期（包含）
	 * @param endDate 结束日期（包含）
	 * @return Season列表 ，元素类似于 20132
	 */
	public static LinkedHashSet<String> yearAndSeasons(Date startDate, Date endDate) {
		final LinkedHashSet<String> seasons = new LinkedHashSet<String>();
		if (startDate == null || endDate == null) {
			return seasons;
		}
		final Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		while (true) {
			// 如果开始时间超出结束时间，让结束时间为开始时间，处理完后结束循环
			if (startDate.after(endDate)) {
				startDate = endDate;
			}
			seasons.add(yearAndSeason(cal));
			if (startDate.equals(endDate)) {
				break;
			}
			cal.add(Calendar.MONTH, 3);
			startDate = cal.getTime();
		}
		return seasons;
	}
	// ------------------------------------ Format start
	// ----------------------------------------------
	/**
	 * 根据特定格式格式化日期
	 * @param date 被格式化的日期
	 * @param format 格式
	 * @return 格式化后的字符串
	 */
	public static String format(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
	/**
	 * 格式 yyyy-MM-dd HH:mm:ss
	 * @param date 被格式化的日期
	 * @return 格式化后的日期
	 */
	public static String formatDateTime(Date date) {
		if (null == date) {
			return null;
		}
		return NORM_DATETIME_FORMAT.get().format(date);
	}
	/**
	 * 格式 yyyy-MM-dd
	 * @param date 被格式化的日期
	 * @return 格式化后的字符串
	 */
	public static String formatDate(Date date) {
		if (null == date) {
			return null;
		}
		return NORM_DATE_FORMAT.get().format(date);
	}
	/**
	 * 格式化为Http的标准日期格式
	 * @param date 被格式化的日期
	 * @return HTTP标准形式日期字符串
	 */
	public static String formatHttpDate(Date date) {
		if (null == date) {
			return null;
		}
		return HTTP_DATETIME_FORMAT.get().format(date);
	}
	// ------------------------------------ Format end
	// ----------------------------------------------
	// ------------------------------------ Parse start
	// ----------------------------------------------
	/**
	 * 构建DateTime对象
	 * @param dateStr Date字符串
	 * @param simpleDateFormat 格式化器
	 * @return DateTime对象
	 */
	public static Date parse(String dateStr, SimpleDateFormat simpleDateFormat) {
		try {
			return new Date(simpleDateFormat.parse(dateStr).getTime());
		} catch (Exception e) {
			throw new BusinessException(
				String.format("Parse [{}] with format [{}] error!", dateStr, simpleDateFormat.toPattern()), e);
		}
	}
	/**
	 * 将特定格式的日期转换为Date对象
	 * @param dateString 特定格式的日期
	 * @param format 格式，例如yyyy-MM-dd
	 * @return 日期对象
	 */
	public static Date parse(String dateString, String format) {
		return parse(dateString, new SimpleDateFormat(format));
	}
	/**
	 * 格式yyyy-MM-dd HH:mm:ss
	 * @param dateString 标准形式的时间字符串
	 * @return 日期对象
	 */
	public static Date parseDateTime(String dateString) {
		return parse(dateString, NORM_DATETIME_FORMAT.get());
	}
	/**
	 * 格式yyyy-MM-dd
	 * @param dateString 标准形式的日期字符串
	 * @return 日期对象
	 */
	public static Date parseDate(String dateString) {
		return parse(dateString, NORM_DATE_FORMAT.get());
	}
	/**
	 * 格式HH:mm:ss
	 * @param timeString 标准形式的日期字符串
	 * @return 日期对象
	 */
	public static Date parseTime(String timeString) {
		return parse(timeString, NORM_TIME_FORMAT.get());
	}
	/**
	 * 格式：<br>
	 * 1、yyyy-MM-dd HH:mm:ss<br>
	 * 2、yyyy-MM-dd<br>
	 * 3、HH:mm:ss<br>
	 * 4、yyyy-MM-dd HH:mm 5、yyyy-MM-dd HH:mm:ss.SSS
	 * @param dateStr 日期字符串
	 * @return 日期
	 */
	public static Date parse(String dateStr) {
		if (null == dateStr) {
			return null;
		}
		dateStr = dateStr.trim();
		int length = dateStr.length();
		try {
			if (length == NORM_DATETIME_PATTERN.length()) {
				return parseDateTime(dateStr);
			} else if (length == NORM_DATE_PATTERN.length()) {
				return parseDate(dateStr);
			} else if (length == NORM_TIME_PATTERN.length()) {
				return parseTime(dateStr);
			} else if (length == NORM_DATETIME_MINUTE_PATTERN.length()) {
				return parse(dateStr, NORM_DATETIME_MINUTE_PATTERN);
			} else if (length >= NORM_TIMESTAMP_PATTERN.length()) {
				return parse(dateStr, NORM_TIMESTAMP_PATTERN);
			}else if (length >= NORM_DATETIME_MS_PATTERN.length() - 2) {
				return parse(dateStr, NORM_DATETIME_MS_PATTERN);
			}
		} catch (Exception e) {
			throw new BusinessException(String.format("Parse [{}] with format normal error!", dateStr));
		}
		// 没有更多匹配的时间格式
		throw new BusinessException(String.format(" [{}] format is not fit for date pattern!", dateStr));
	}
	// ------------------------------------ Parse end ----------------------------------------------
	/**
	 * 获得指定日期年份和季节<br>
	 * 格式：[20131]表示2013年第一季度
	 * @param cal 日期
	 */
	private static String yearAndSeason(Calendar cal) {
		return new StringBuilder().append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH) / 3 + 1).toString();
	}
	public static Date getMon(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获取当月最后一天
	 * @return
	 */
	public static Date getMonLastDay() {
		// 获取当前月最后一天
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		// String last = format(ca.getTime());
		// System.out.println("===============last:"+last);
		return ca.getTime();
	}
	/**
	 * 获取当前月第一天
	 * @Description:TODO
	 * @return
	 */
	public static Date getMonFirstDay() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		return c.getTime();
	}
	/*
	 * 毫秒转化时分秒毫秒
	 */
	public static String formatTime(Long ms) {
		Integer ss = 1000;
		Integer mi = ss * 60;
		Integer hh = mi * 60;
		Integer dd = hh * 24;
		Long day = ms / dd;
		Long hour = (ms - day * dd) / hh;
		Long minute = (ms - day * dd - hour * hh) / mi;
		Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
		Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;
		StringBuffer sb = new StringBuffer();
		if (day > 0) {
			sb.append(day + "天");
		}
		if (hour > 0) {
			sb.append(hour + "小时");
		}
		if (minute > 0) {
			sb.append(minute + "分");
		}
		if (second > 0) {
			sb.append(second + "秒");
		}
		if (milliSecond > 0) {
			sb.append(milliSecond + "毫秒");
		}
		return sb.toString();
	}
	/**
	 * 时间戳转换成日期格式字符串
	 * @param seconds 精确到秒的字符串
	 * @param formatStr
	 * @return
	 */
	public static String timeStamp2Date(String seconds, String format) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return "";
		}
		if (format == null || format.isEmpty()) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds + "000")));
	}
	/**
	 * 日期格式字符串转换成时间戳
	 * @param date 字符串日期
	 * @param format 如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String date2TimeStamp(String date_str, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return String.valueOf(sdf.parse(date_str).getTime() / 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	/**
	 * 获取两个时间之间的日差
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int timePoor(Date startDate,Date endDate){		
		return (int)TimeUnit.DAYS.convert(endDate.getTime() - startDate.getTime(), TimeUnit.MILLISECONDS);
	}
	/**
	 * 获取两个时间的时差
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static int getHoursTimes(Date str1, Date str2){
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fromDate = simpleFormat.format(str1);
		String toDate = simpleFormat.format(str2);
		long from = 0l;
		long to = 0l;
		try {
			from = simpleFormat.parse(fromDate).getTime();
			to = simpleFormat.parse(toDate).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int hours = (int) ((to - from)/(1000 * 60 * 60));
		if((to - from)%(1000 * 60 * 60) != 0){
			hours = hours + 1;
		}
		
		return hours;
	} 
	
	/**
	 * 获取两个时间的分钟差
	 * @param firstTime
	 * @param lastTime
	 * @return
	 */
	public static long getMinutes(Date firstTime,Date lastTime) {
		try {
			long diff = firstTime.getTime() - lastTime.getTime();
			long minutes = diff / (1000 * 60);
			return minutes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 字符串转换时间
	 * @param time
	 * @param type
	 * @return
	 */
	public static Date timeToDate(String time,String type){		
		SimpleDateFormat sdf =   new SimpleDateFormat(type);		
		try {
			return sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	/**
	 * 获取某个日期 N天前的日期
	 * @param time
	 * @param days
	 * @return
	 */
	public static String getStringDateBeforeNDays(Date time,int days ){
		SimpleDateFormat sdf = new SimpleDateFormat(NORM_DATE_PATTERN);
		Calendar calendar = Calendar.getInstance();//获取日历实例  
		calendar.setTime(time); 
		calendar.add(Calendar.DAY_OF_MONTH, -days);  //设置为前一天
		return sdf.format(calendar.getTime());
	}
	
	public static String getStringDateBeforeNDays(Date time,int days , String formart){
		SimpleDateFormat sdf = new SimpleDateFormat(formart);
		Calendar calendar = Calendar.getInstance();//获取日历实例  
		calendar.setTime(time); 
		calendar.add(Calendar.DAY_OF_MONTH, -days);  //设置为前一天
		return sdf.format(calendar.getTime());
	}
	
	
	/**
	 * 获取某个日期 N天前的日期
	 * @param time
	 * @param days
	 * @return
	 */
	public static Date getDateBeforeNDays(Date time, int days){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(NORM_DATE_PATTERN);
			Calendar calendar = Calendar.getInstance();//获取日历实例  
			calendar.setTime(time); 
			calendar.add(Calendar.DAY_OF_MONTH, -days);  //设置为前一天
			String dateStr = sdf.format(calendar.getTime());
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取某个日期 N天前的日期
	 * @param time
	 * @param days
	 * @return
	 */
	public static Date getDateBeforeNDays(Date time, int days , String formart){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formart);
			Calendar calendar = Calendar.getInstance();//获取日历实例  
			calendar.setTime(time); 
			calendar.add(Calendar.DAY_OF_MONTH, -days);  //设置为前一天
			String dateStr = sdf.format(calendar.getTime());
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**Date
	 * 取得当前时间戳（精确到秒）
	 * @return
	 */
	public static String timeStamp() {
		long time = System.currentTimeMillis();
		String t = String.valueOf(time / 1000);
		return t;
	}
	/**
	 * 计算2个日期之间相差的  相差多少日
	 * 比如：2011-02-02 到  2017-03-02 相差 6年，1个月，0天
	 * @param begin_date
	 * @param end_date
	 * @return
	 */
	public static long getInterval(Date begin_date, Date end_date) throws Exception{
		long day = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if(begin_date != null){
			String begin = sdf.format(begin_date);
			begin_date  = sdf.parse(begin);
		}
		if(end_date!= null){
			String end= sdf.format(end_date);
			end_date= sdf.parse(end);
		}
		day = (end_date.getTime()-begin_date.getTime())/(24*60*60*1000);
		return day;
	}
	/**
	 * 返回当月第一天的日期
	 */
	public static Date firstDay(Date date) {
		Calendar calendar = convert(date);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * 返回当月第一天的日期,时间为零时零分零秒
	 */
	public static Date firstDayOfMonth(Date date) {
		Calendar calendar = convert(firstDay(date));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 返回当年第一天的日期,时间为零时零分零秒
	 */
	public static Date firstDayOfYear(Date date) {
		Calendar calendar = convert(date);
		calendar.set(Calendar.MONTH, calendar.getActualMinimum(Calendar.MONTH));
		return firstDayOfMonth(calendar.getTime());
	}

	/**
	 * 返回当年最后一天的日期,时间为零时零分零秒
	 */
	public static Date lastDayOfYear(Date date) {
		Calendar calendar = convert(date);
		calendar.set(Calendar.MONTH, calendar.getActualMaximum(Calendar.MONTH));
		return lastDayOfMonth(calendar.getTime());
	}
	/**
	 * 返回当月最后一天的日期,时间零时零分零秒
	 */
	public static Date lastDayOfMonth(Date date) {
		Calendar calendar = convert(lastDay(date));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	/**
	 * 返回当月最后一天的日期
	 */
	public static Date lastDay(Date date) {
		Calendar calendar = convert(date);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		return calendar.getTime();
	}
	
	public static Calendar convert(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar;
	}
	
	   /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     * 
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

	/**
	 * 时间
	 * @param date 相加前日期
	 * @param dateType 相加类型Calendar.MINUTE(分),Calendar.DATE(天),Calendar.HOUR(小时)
	 * @param num 数量
	 * @return
	 */
	public static Date addDate(Date date, int dateType,Integer num){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 日期加num,
		cal.add(dateType, num);
		Date afterDate = cal.getTime();
		return afterDate;
	}
    /**
     * 获取两个日期之间的所有日期
     *
     * @param startTime 开始日期
     * @param endTime 结束日期
     * @return
     */
    public static List<String> getDays(String startTime, String endTime) {
        // 返回的日期集合
        List<String> days = Lists.newArrayList();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

	/**
	 * @desc   获取两个日期中的天数，两个日期的间隔不能大于31天
	 * @author zhangxi
	 * @date   2019/11/7 10:01
	 * @param startDay 开始日期（必填），格式为：yyyy-MM-dd
	 * @param endDay 结束日期（必填），格式为：yyyy-MM-dd
	 * @return 返回结果，是不包含开始日期，但包含结束日期的数组，元素格式同样为：yyyy-MM-dd，如有数据异常则返回null
	 */
	public static String[] betweenTwoDays(String startDay, String endDay) throws ParseException {
		if (StringUtils.isEmpty(startDay) || StringUtils.isEmpty(endDay)) return null;
		SimpleDateFormat sdf = new SimpleDateFormat(NORM_DATE_PATTERN);
		sdf.setLenient(false);// 设置为false，就等于转化时，有了严格的校验，例如 Unparseable date: "2019-07-32"，校验不过会报错
		Date startDayDate = sdf.parse(startDay);
		Date endDayDate = sdf.parse(endDay);

		long startDayMills = startDayDate.getTime();
		long endDayMills = endDayDate.getTime();
		if (endDayMills < startDayMills) return null;
		long betweenMills = endDayMills - startDayMills;
		long oneDayMills = 1000 * 3600 * 24;
		long betweenDay = betweenMills / oneDayMills;
		if (betweenDay < 1 || betweenDay > 31) return null;

		Calendar calStart = Calendar.getInstance();
		Calendar calEnd = Calendar.getInstance();
		calStart.setTime(startDayDate);
		calEnd.setTime(endDayDate);

		String startYear = startDay.split("-")[0];
		String endYear = endDay.split("-")[0];
		String startMonth = startDay.split("-")[1];
		String endMonth = endDay.split("-")[1];
		int startDayInt = Integer.parseInt(startDay.split("-")[2]) + 1;

		boolean throughMonth = (Math.abs(calEnd.get(Calendar.MONTH) - calStart.get(Calendar.MONTH)) > 0) ? true : false;
		boolean throughYear = Integer.parseInt(endYear) > Integer.parseInt(startYear) ? true : false;
		String[] array = new String[Integer.parseInt(betweenDay + "")];

		if (throughMonth) {
			String endDayOfMonth = getEndDayOfMonth(startDay);
			int endDayNum = Integer.parseInt(endDayOfMonth.split("-")[2]);
			String prefix = startYear + "-" + startMonth + "-";
			for (int i = 0; i < betweenDay; i++) {
				int nextDay = startDayInt + i;
				if (i == betweenDay - 1) {
					array[i] = endDay;
					return array;
				}
				if (throughYear) {
					if (nextDay > endDayNum) {
						prefix = endYear + "-" + endMonth + "-";
						startDayInt = startDayInt - endDayNum;
						array[i] = prefix + StringUtil.FORMAT_ONE.format(i + startDayInt);
					} else {
						array[i] = prefix + StringUtil.FORMAT_ONE.format(nextDay);
					}
				} else {
					if (nextDay > endDayNum) {
						prefix = startYear + "-" + endMonth + "-";
						startDayInt = startDayInt - endDayNum;
						array[i] = prefix + StringUtil.FORMAT_ONE.format(i + startDayInt);
					} else {
						array[i] = prefix + StringUtil.FORMAT_ONE.format(nextDay);
					}
				}
			}
		} else {
			String prefix = startYear + "-" + startMonth + "-";
			for (int i = 0; i < betweenDay; i++) {
				array[i] = prefix + StringUtil.FORMAT_ONE.format(startDayInt + i);
			}
		}
		return array;
	}

	/**
	 * @desc   获取日期月的，当月最后一天日期字符串
	 * @author zhangxi
	 * @date   2019/11/7 10:05
	 * @param day 日期（必填），格式可为：yyyy-MM-dd
	 * @return 返回格式为：yyyy-MM-dd
	 */
	public static String getEndDayOfMonth(String day) throws ParseException {
		int year = Integer.parseInt(day.split("-")[0]);  //年
		int month = Integer.parseInt(day.split("-")[1]); //月
		Date date = YYYY_MM_DD_FORMAT.parse(day);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int lastDay = cal.getActualMaximum(Calendar.DATE);// 获取某月最大天数
		cal.set(Calendar.YEAR, year); // 设置年份
		cal.set(Calendar.MONTH, month - 1); // 设置月份
		cal.set(Calendar.DAY_OF_MONTH, lastDay);// 设置日历中月份的最大天数
		SimpleDateFormat sdf = new SimpleDateFormat(NORM_DATE_PATTERN);// 格式化日期
		return sdf.format(cal.getTime());
	}

	public static String getTimestamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	/**
	 *
	 * date转isoDATE
	 * @param date
	 * @return
	 */
	public static String getISO8601Timestamp(Date date){
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		df.setTimeZone(tz);
		String nowAsISO = df.format(date);
		System.out.println(nowAsISO);
		return nowAsISO;
	}

	/**
	 * 判断是否是同一天
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean judgeIsoneDay(Date startDate, Date endDate) {
		Calendar cal1 = Calendar.getInstance();

		Calendar cal2 = Calendar.getInstance();

		cal1.setTime(startDate);

		cal2.setTime(endDate);

		boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
		return sameDay;
	}
	// 获得某天最大时间 2018-03-20 23:59:59
	public static Date getEndOfDay(Date date) {
				Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.setTime(date);
		calendarEnd.set(Calendar.HOUR_OF_DAY, 23);
		calendarEnd.set(Calendar.MINUTE, 59);
		calendarEnd.set(Calendar.SECOND, 59);

		//防止mysql自动加一秒,毫秒设为0
		calendarEnd.set(Calendar.MILLISECOND, 0);
		return calendarEnd.getTime();

			}
	// 获得某天最小时间 2018-03-20 00:00:00
	public static Date getStartOfDay(Date date) {

		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
		LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());

	}

	public static Date getNowDate()
	{
		return new Date();
	}
}
