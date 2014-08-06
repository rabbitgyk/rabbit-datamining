package com.rabbit.vip.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateTimeUtil {

	public final static int TIME_DAY_MILLISECOND = 86400000;

	public final static int TIME_HOUR_MILLISECOND = 1000 * 60 * 60 * 1;

	public final static int TIME_MINUTE_MILLISECOND = 1000 * 60;

	public final static int TIME_SECONDS_MILLISECOND = 1000;

	// /
	// 定义时间日期显示格式
	// /
	public final static String DATE_FORMAT = "yyyy-MM-dd";

	public final static String DATE_FORMAT_CN = "yyyy年MM月dd日";

	public final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public final static String TIME_FORMAT_CN = "yyyy年MM月dd日 HH:mm:ss";

	public final static String MONTH_FORMAT = "yyyy-MM";

	public final static String DAY_FORMAT = "yyyyMMdd";

	public final static String TIME_FORMAT_NUM = "MMddHHmmss";

	public final static Date NEXT_THOUSAND_DAY = DateTimeUtil.getFormatDate("3014-01-01 00:00:00", TIME_FORMAT);

	// private final static String TIME_FORMAT_MILLI = "yyyy-MM-dd
	// HH:mm:ss.SSS";

	/**
	 * 取得当前系统时间，返回java.util.Date类型
	 * 
	 * @see java.util.Date
	 * @return java.util.Date 返回服务器当前系统时间
	 */
	public static java.util.Date getCurrDate() {
		return new java.util.Date();
	}

	/**
	 * 获得对应格式的当前时间
	 * 
	 * @param strFormat
	 * @return
	 */
	public static String getCurrentDateStr(String strFormat) {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();

		return format(currDate, strFormat);
	}

	/**
	 * 将Date类型的日期转换为系统参数定义的格式的字符串。
	 * 
	 * @param aTs_Datetime
	 * @param as_Pattern
	 * @return
	 */
	public static String format(Date aTs_Datetime, String as_Pattern) {
		if (aTs_Datetime == null || as_Pattern == null)
			return null;
		SimpleDateFormat dateFromat = new SimpleDateFormat();
		dateFromat.applyPattern(as_Pattern);

		return dateFromat.format(aTs_Datetime);
	}

	/**
	 * 取得当前系统时间戳
	 * 
	 * @see java.sql.Timestamp
	 * @return java.sql.Timestamp 系统时间戳
	 */
	public static java.sql.Timestamp getCurrTimestamp() {
		return new java.sql.Timestamp(System.currentTimeMillis());
	}

	/**
	 * 返回当前时间是上午还是下午
	 * 
	 * @see Calendar.AM 0
	 * @see Calendar.PM 1
	 * @return
	 */
	public static Integer getCurrDateAMorPM() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.AM_PM);
	}

	/**
	 * 得到格式化后的日期，格式为yyyy-MM-dd，如2009-10-15
	 * 
	 * @param currDate
	 *            要格式化的日期
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回格式化后的日期，默认格式为为yyyy-MM-dd，如2009-10-15
	 */
	public static String getFormatDate(java.util.Date currDate) {

		return getFormatDate(currDate, DATE_FORMAT);

	}

	/**
	 * 得到格式化后的日期，格式为yyyy-MM-dd，如2009-10-15
	 * 
	 * @param currDate
	 *            要格式化的日期
	 * @see #getFormatDate(java.util.Date)
	 * @return Date 返回格式化后的日期，默认格式为为yyyy-MM-dd，如2009-10-15
	 */
	public static Date getFormatDateToDate(java.util.Date currDate) {
		return getFormatDate(getFormatDate(currDate));
	}

	/**
	 * 得到格式化后的日期，格式为yyyy年MM月dd日，如2009年02月15日
	 * 
	 * @param currDate
	 *            要格式化的日期
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回格式化后的日期，默认格式为yyyy年MM月dd日，如2009年02月15日
	 */
	public static String getFormatDate_CN(java.util.Date currDate) {
		return getFormatDate(currDate, DATE_FORMAT_CN);
	}

	/**
	 * 得到格式化后的日期，格式为yyyy年MM月dd日，如2009年02月15日
	 * 
	 * @param currDate
	 *            要格式化的日期
	 * @see #getFormatDate_CN(String)
	 * @return Date 返回格式化后的日期，默认格式为yyyy年MM月dd日，如2009年02月15日
	 */
	public static Date getFormatDateToDate_CN(java.util.Date currDate) {
		return getFormatDate_CN(getFormatDate_CN(currDate));
	}

	/**
	 * 得到格式化后的日期，格式为yyyy-MM-dd，如2009-10-15
	 * 
	 * @param currDate
	 *            要格式化的日期
	 * @see #getFormatDate(String, String)
	 * @return Date 返回格式化后的日期，默认格式为yyyy-MM-dd，如2009-10-15
	 */
	public static Date getFormatDate(String currDate) {
		return getFormatDate(currDate, DATE_FORMAT);
	}

	/**
	 * 根据毫秒数来获取date
	 * @param millSecond
	 * @return
	 */
	public static Date getDate(long millSecond) {
		return new Date(millSecond);
	}

	/**
	 * 得到格式化后的日期，格式为yyyy年MM月dd日，如2009年02月15日
	 * 
	 * @param currDate
	 *            要格式化的日期
	 * @see #getFormatDate(String, String)
	 * @return 返回格式化后的日期，默认格式为yyyy年MM月dd日，如2009年02月15日
	 */
	public static Date getFormatDate_CN(String currDate) {
		return getFormatDate(currDate, DATE_FORMAT_CN);
	}

	/**
	 * 根据格式得到格式化后的日期
	 * 
	 * @param currDate
	 *            要格式化的日期
	 * @param format
	 *            日期格式，如yyyy-MM-dd
	 * @see java.text.SimpleDateFormat#format(java.util.Date)
	 * @return String 返回格式化后的日期，格式由参数<code>format</code>
	 *         定义，如yyyy-MM-dd，如2009-10-15
	 */
	public static String getFormatDate(java.util.Date currDate, String format) {
		SimpleDateFormat dtFormatdB = null;
		try {
			dtFormatdB = new SimpleDateFormat(format);
			return dtFormatdB.format(currDate);
		} catch (Exception e) {
			dtFormatdB = new SimpleDateFormat(DATE_FORMAT);
			try {
				return dtFormatdB.format(currDate);
			} catch (Exception ex) {
			}
		}
		return null;
	}

	/**
	 * 得到格式化后的时间，格式为yyyy-MM-dd HH:mm:ss，如2009-10-15 15:23:45
	 * 
	 * @param currDate
	 *            要格式化的时间
	 * @see #getFormatDateTime(java.util.Date, String)
	 * @return String 返回格式化后的时间，默认格式为yyyy-MM-dd HH:mm:ss，如2009-10-15 15:23:45
	 */
	public static String getFormatDateTime(java.util.Date currDate) {
		return getFormatDateTime(currDate, TIME_FORMAT);
	}

	/**
	 * 得到格式化后的时间，格式为yyyy-MM-dd HH:mm:ss，如2009-10-15 15:23:45
	 * 
	 * @param currDate
	 *            要格式环的时间
	 * @see #getFormatDateTime(String)
	 * @return Date 返回格式化后的时间，默认格式为yyyy-MM-dd HH:mm:ss，如2009-10-15 15:23:45
	 */
	public static Date getFormatDateTimeToTime(java.util.Date currDate) {
		return getFormatDateTime(getFormatDateTime(currDate));
	}

	/**
	 * 得到格式化后的时间，格式为yyyy-MM-dd HH:mm:ss，如2009-10-15 15:23:45
	 * 
	 * @param currDate
	 *            要格式化的时间
	 * @see #getFormatDateTime(String, String)
	 * @return Date 返回格式化后的时间，默认格式为yyyy-MM-dd HH:mm:ss，如2009-10-15 15:23:45
	 */
	public static Date getFormatDateTime(String currDate) {
		return getFormatDateTime(currDate, TIME_FORMAT);
	}

	/**
	 * 得到格式化后的时间，格式为yyyy年MM月dd日 HH:mm:ss，如2009年02月15日 15:23:45
	 * 
	 * @param currDate
	 *            要格式化的时间
	 * @see #getFormatDateTime(java.util.Date, String)
	 * @return String 返回格式化后的时间，默认格式为yyyy年MM月dd日 HH:mm:ss，如2009年02月15日 15:23:45
	 */
	public static String getFormatDateTime_CN(java.util.Date currDate) {
		return getFormatDateTime(currDate, TIME_FORMAT_CN);
	}

	/**
	 * 得到格式化后的时间，格式为yyyy年MM月dd日 HH:mm:ss，如2009年02月15日 15:23:45
	 * 
	 * @param currDate
	 *            要格式化的时间
	 * @see #getFormatDateTime_CN(String)
	 * @return Date 返回格式化后的时间，默认格式为yyyy年MM月dd日 HH:mm:ss，如2009年02月15日 15:23:45
	 */
	public static Date getFormatDateTimeToTime_CN(java.util.Date currDate) {
		return getFormatDateTime_CN(getFormatDateTime_CN(currDate));
	}

	/**
	 * 得到格式化后的时间，格式为yyyy年MM月dd日 HH:mm:ss，如2009年02月15日 15:23:45
	 * 
	 * @param currDate
	 *            要格式化的时间
	 * @see #getFormatDateTime(String, String)
	 * @return Date 返回格式化后的时间，默认格式为yyyy年MM月dd日 HH:mm:ss，如2009年02月15日 15:23:45
	 */
	public static Date getFormatDateTime_CN(String currDate) {
		return getFormatDateTime(currDate, TIME_FORMAT_CN);
	}

	/**
	 * 根据格式得到格式化后的时间
	 * 
	 * @param currDate
	 *            要格式化的时间
	 * @param format
	 *            时间格式，如yyyy-MM-dd HH:mm:ss
	 * @see java.text.SimpleDateFormat#format(java.util.Date)
	 * @return String 返回格式化后的时间，格式由参数<code>format</code>定义，如yyyy-MM-dd HH:mm:ss
	 */
	public static String getFormatDateTime(java.util.Date currDate, String format) {
		SimpleDateFormat dtFormatdB = null;
		try {
			dtFormatdB = new SimpleDateFormat(format);
			return dtFormatdB.format(currDate);
		} catch (Exception e) {
			dtFormatdB = new SimpleDateFormat(TIME_FORMAT);
			try {
				return dtFormatdB.format(currDate);
			} catch (Exception ex) {
			}
		}
		return null;
	}

	/**
	 * 根据格式得到格式化后的日期
	 * 
	 * @param currDate
	 *            要格式化的日期
	 * @param format
	 *            日期格式，如yyyy-MM-dd
	 * @see java.text.SimpleDateFormat#parse(java.lang.String)
	 * @return Date 返回格式化后的日期，格式由参数<code>format</code>
	 *         定义，如yyyy-MM-dd，如2009-10-15
	 */
	public static Date getFormatDate(String currDate, String format) {
		SimpleDateFormat dtFormatdB = null;
		try {
			dtFormatdB = new SimpleDateFormat(format);
			return dtFormatdB.parse(currDate);
		} catch (Exception e) {
			dtFormatdB = new SimpleDateFormat(DATE_FORMAT);
			try {
				return dtFormatdB.parse(currDate);
			} catch (Exception ex) {
			}
		}
		return null;
	}

	/**
	 * 根据格式得到格式化后的时间
	 * 
	 * @param currDate
	 *            要格式化的时间
	 * @param format
	 *            时间格式，如yyyy-MM-dd HH:mm:ss
	 * @see java.text.SimpleDateFormat#parse(java.lang.String)
	 * @return Date 返回格式化后的时间，格式由参数<code>format</code>定义，如yyyy-MM-dd HH:mm:ss
	 */
	public static Date getFormatDateTime(String currDate, String format) {
		SimpleDateFormat dtFormatdB = null;
		try {
			dtFormatdB = new SimpleDateFormat(format);
			return dtFormatdB.parse(currDate);
		} catch (Exception e) {
			dtFormatdB = new SimpleDateFormat(TIME_FORMAT);
			try {
				return dtFormatdB.parse(currDate);
			} catch (Exception ex) {
			}
		}
		return null;
	}

	/**
	 * 
	 * @param time
	 *            Seconds 传入参数 秒
	 * @return 返回 格式 hh:mm:ss 秒
	 */
	public static String getFormatHourAndMinuteTime(long time) {
		if (time < 60) {
			return String.valueOf(time);
		}
		if (time < 60 * 60) {
			int seconds = (int) (time % 60l);
			int minutes = (int) (time / (60l));
			return String.valueOf(minutes) + ":" + (seconds < 10 ? ("0" + String.valueOf(seconds)) : String.valueOf(seconds));
		}
		int seconds = (int) (time % 60l);
		int minutes = (int) ((time / 60l) % 60l);
		int hours = (int) (time / (60l * 60l));
		return hours + ":" + (minutes < 10 ? ("0" + String.valueOf(minutes)) : String.valueOf(minutes)) + ":"
				+ (seconds < 10 ? ("0" + String.valueOf(seconds)) : String.valueOf(seconds));
	}

	/**
	 * 得到本日的上月时间 如果当日为2007-9-1,那么获得2007-8-1
	 */
	public static String getDateBeforeMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return getFormatDate(cal.getTime(), DATE_FORMAT);
	}

	/**
	 * 得到本日的前几个月时间 如果number=2当日为2007-9-1,那么获得2007-7-1
	 */
	public static String getDateBeforeMonth(int number) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -number);
		return getFormatDate(cal.getTime(), DATE_FORMAT);
	}

	public static long getDaysOfDates(Date first, Date second) {
		Date d1 = getFormatDateTime(getFormatDate(first), DATE_FORMAT);
		Date d2 = getFormatDateTime(getFormatDate(second), DATE_FORMAT);

		long mils = d1.getTime() - d2.getTime();

		return mils / (TIME_DAY_MILLISECOND);
	}

	/**
	 * 获得两个Date型日期之间相差的天数（第2个减第1个）
	 * 
	 * @param Date
	 *            first, Date second
	 * @return int 相差的天数
	 */
	public static int getDaysBetweenDates(Date first, Date second) {
		Date d1 = getFormatDateTime(getFormatDate(first), DATE_FORMAT);
		Date d2 = getFormatDateTime(getFormatDate(second), DATE_FORMAT);

		Long mils = (d2.getTime() - d1.getTime()) / (TIME_DAY_MILLISECOND);

		return mils.intValue();
	}

	/**
	 * 获得两个Date型日期之间相差的小时数（第2个减第1个）
	 * 
	 * @param Date
	 *            first, Date second
	 * @return int 相差的小时数
	 */
	public static int getHoursBetweenDates(Date first, Date second) {

		Date d1 = getFormatDateTime(getFormatDate(first), DATE_FORMAT);
		Date d2 = getFormatDateTime(getFormatDate(second), DATE_FORMAT);

		Long mils = (d2.getTime() - d1.getTime()) / (TIME_HOUR_MILLISECOND);

		return mils.intValue();

	}

	public static int getSecondsBetweenDates(Date first, Date second) {
		Long mils = (second.getTime() - first.getTime()) / (TIME_SECONDS_MILLISECOND);
		return mils.intValue();
	}

	/**
	 * 获得两个String型日期之间相差的天数（第2个减第1个）
	 * 
	 * @param String
	 *            first, String second
	 * @return int 相差的天数
	 */
	public static int getDaysBetweenDates(String first, String second) {
		Date d1 = getFormatDateTime(first, DATE_FORMAT);
		Date d2 = getFormatDateTime(second, DATE_FORMAT);

		Long mils = (d2.getTime() - d1.getTime()) / (TIME_DAY_MILLISECOND);

		return mils.intValue();
	}

	/**
	 * @return 获取两个Date之间的天数的列表
	 */
	public static List<Date> getDaysListBetweenDates(Date first, Date second) {
		List<Date> dateList = new ArrayList<Date>();
		Date d1 = getFormatDateTime(getFormatDate(first), DATE_FORMAT);
		Date d2 = getFormatDateTime(getFormatDate(second), DATE_FORMAT);
		if (d1.compareTo(d2) > 0) {
			return dateList;
		}
		do {
			dateList.add(d1);
			d1 = getDateBeforeOrAfter(d1, 1);
		} while (d1.compareTo(d2) <= 0);
		return dateList;
	}

	public static String getDateBeforeDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return getFormatDate(cal.getTime(), DATE_FORMAT);
	}

	/**
	 * 得到格式化后的当前系统日期，格式为yyyy-MM-dd，如2009-10-15
	 * 
	 * @see #getFormatDate(java.util.Date)
	 * @return String 返回格式化后的当前服务器系统日期，格式为yyyy-MM-dd，如2009-10-15
	 */
	public static String getCurrDateStr() {
		return getFormatDate(getCurrDate());
	}

	public static int getCurrDateHour() {
		Calendar cal = Calendar.getInstance();

		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 得到格式化后的当前系统时间，格式为yyyy-MM-dd HH:mm:ss，如2009-10-15 15:23:45
	 * 
	 * @see #getFormatDateTime(java.util.Date)
	 * @return String 返回格式化后的当前服务器系统时间，格式为yyyy-MM-dd HH:mm:ss，如2009-10-15
	 *         15:23:45
	 */
	public static String getCurrDateTimeStr() {
		return getFormatDateTime(getCurrDate());
	}

	/**
	 * 得到格式化后的当前系统日期，格式为yyyy年MM月dd日，如2009年02月15日
	 * 
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回当前服务器系统日期，格式为yyyy年MM月dd日，如2009年02月15日
	 */
	public static String getCurrDateStr_CN() {
		return getFormatDate(getCurrDate(), DATE_FORMAT_CN);
	}

	/**
	 * 得到格式化后的当前系统时间，格式为yyyy年MM月dd日 HH:mm:ss，如2009年02月15日 15:23:45
	 * 
	 * @see #getFormatDateTime(java.util.Date, String)
	 * @return String 返回格式化后的当前服务器系统时间，格式为yyyy年MM月dd日 HH:mm:ss，如2009年02月15日
	 *         15:23:45
	 */
	public static String getCurrDateTimeStr_CN() {
		return getFormatDateTime(getCurrDate(), TIME_FORMAT_CN);
	}

	/**
	 * 得到系统当前日期的前或者后几天
	 * 
	 * @param iDate
	 *            如果要获得前几天日期，该参数为负数； 如果要获得后几天日期，该参数为正数
	 * @see java.util.Calendar#add(int, int)
	 * @return Date 返回系统当前日期的前或者后几天
	 */
	public static Date getDateBeforeOrAfter(int iDate) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, iDate);
		return cal.getTime();
	}

	/**
	 * 得到日期的前或者后几天
	 * 
	 * @param iDate
	 *            如果要获得前几天日期，该参数为负数； 如果要获得后几天日期，该参数为正数
	 * @see java.util.Calendar#add(int, int)
	 * @return Date 返回参数<code>curDate</code>定义日期的前或者后几天
	 */
	public static Date getDateBeforeOrAfter(Date curDate, int iDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		cal.add(Calendar.DAY_OF_MONTH, iDate);
		return cal.getTime();
	}

	/**
	 * 得到格式化后的月份，格式为yyyy-MM，如2009-02
	 * 
	 * @param currDate
	 *            要格式化的日期
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回格式化后的月份，格式为yyyy-MM，如2009-02
	 */
	public static String getFormatMonth(java.util.Date currDate) {
		return getFormatDate(currDate, MONTH_FORMAT);
	}

	/**
	 * 得到格式化后的日，格式为yyyyMMdd，如20090210
	 * 
	 * @param currDate
	 *            要格式化的日期
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回格式化后的日，格式为yyyyMMdd，如20090210
	 */
	public static String getFormatDay(java.util.Date currDate) {
		return getFormatDate(currDate, DAY_FORMAT);
	}

	public static String getFormatTime(java.util.Date currDate) {
		return getFormatDate(currDate, TIME_FORMAT_NUM);
	}

	/**
	 * 得到格式化后的当月第一天，格式为yyyy-MM-dd，如2009-10-01
	 * 
	 * @param currDate
	 *            要格式化的日期
	 * @see java.util.Calendar#getMinimum(int)
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回格式化后的当月第一天，格式为yyyy-MM-dd，如2009-10-01
	 */
	public static String getFirstDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		int firstDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		return getFormatDate(cal.getTime(), DATE_FORMAT);
	}

	/**
	 * 得到格式化后的下月第一天，格式为yyyy-MM-dd，如2009-10-01
	 * 
	 * @param currDate
	 *            要格式化的日期
	 * @see java.util.Calendar#getMinimum(int)
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回格式化后的下月第一天，格式为yyyy-MM-dd，如2009-10-01
	 */
	public static String getFirstDayOfNextMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, +1);
		int firstDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		return getFormatDate(cal.getTime(), DATE_FORMAT);
	}

	/**
	 * 得到格式化后的当月第一天，格式为yyyy-MM-dd，如2009-10-01
	 * 
	 * @param currDate
	 *            要格式化的日期
	 * @see java.util.Calendar#getMinimum(int)
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回格式化后的当月第一天，格式为yyyy-MM-dd，如2009-10-01
	 */
	public static String getFirstDayOfMonth(Date currDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(currDate);
		int firstDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		return getFormatDate(cal.getTime(), DATE_FORMAT);
	}

	/**
	 * 得到格式化后的当月最后一天，格式为yyyy-MM-dd，如2009-10-28
	 * 
	 * @param currDate
	 *            要格式化的日期
	 * @see java.util.Calendar#getMinimum(int)
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回格式化后的当月最后一天，格式为yyyy-MM-dd，如2009-10-28
	 */
	public static String getLastDayOfMonth(Date currDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(currDate);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		return getFormatDate(cal.getTime(), DATE_FORMAT);
	}

	/**
	 * 得到格式化后的当月最后一天，格式为yyyy-MM-dd，如2009-10-28
	 * 
	 * @param currDate
	 *            要格式化的日期
	 * @see java.util.Calendar#getMinimum(int)
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回格式化后的当月最后一天，格式为yyyy-MM-dd，如2009-10-28
	 */
	public static String getLastDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		return getFormatDate(cal.getTime(), DATE_FORMAT);
	}

	/**
	 * 得到日期的前或者后几小时
	 * 
	 * @param iHour
	 *            如果要获得前几小时日期，该参数为负数； 如果要获得后几小时日期，该参数为正数
	 * @see java.util.Calendar#add(int, int)
	 * @return Date 返回参数<code>curDate</code>定义日期的前或者后几小时
	 */
	public static Date getDateBeforeOrAfterHours(Date curDate, int iHour) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		cal.add(Calendar.HOUR_OF_DAY, iHour);
		return cal.getTime();
	}

	/**
	 * 判断日期是否在当前周内
	 * 
	 * @param curDate
	 * @param compareDate
	 * @return
	 */
	public static boolean isSameWeek(Date curDate, Date compareDate) {
		if (curDate == null || compareDate == null) {
			return false;
		}

		Calendar calSun = Calendar.getInstance();
		calSun.setTime(getFormatDateToDate(curDate));
		calSun.set(Calendar.DAY_OF_WEEK, 1);

		Calendar calNext = Calendar.getInstance();
		calNext.setTime(calSun.getTime());
		calNext.add(Calendar.DATE, 7);

		Calendar calComp = Calendar.getInstance();
		calComp.setTime(compareDate);
		if (calComp.after(calSun) && calComp.before(calNext)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isSameDay(Date currentDate, Date compareDate) {
		if (currentDate == null || compareDate == null) {
			return false;
		}
		String current = getFormatDate(currentDate);
		String compare = getFormatDate(compareDate);
		if (current.equals(compare)) {
			return true;
		}
		return false;
	}

	/**
	 * 时间查询时,结束时间的 23:59:59
	 */
	public static String addDateEndfix(String datestring) {
		if ((datestring == null) || datestring.equals("")) {
			return null;
		}
		return datestring + " 23:59:59";
	}

	/**
	 * 返回格式化的日期
	 * 
	 * @param datePre
	 *            格式"yyyy-MM-dd 23:59:59";
	 * @return
	 */
	public static Date getFormatDateEndfix(String dateStr) {
		dateStr = addDateEndfix(dateStr);
		return getFormatDateTime(dateStr);
	}

	/**
	 * 返回格式化的日期
	 * 
	 * @param datePre
	 *            格式"yyyy-MM-dd HH:mm:ss";
	 * @return
	 */
	public static Date formatEndTime(String datePre) {
		if (datePre == null)
			return null;
		String dateStr = addDateEndfix(datePre);
		return getFormatDateTime(dateStr);
	}

	// date1加上compday天数以后的日期与当前时间比较，如果大于当前时间返回true，否则false
	public static Boolean compareDay(Date date1, int compday) {
		if (date1 == null)
			return false;
		Date dateComp = getDateBeforeOrAfter(date1, compday);
		Date nowdate = new Date();
		if (dateComp.after(nowdate))
			return true;
		else
			return false;
	}

	/**
	 * 进行时段格式转换，对于输入的48位的01串，将进行如下操作： <li>
	 * 1.先将输入中每个0变成两个0，每个1变成2个1，形成一个96位的二进制串。</li> <li>
	 * 2.将上述的96位的二进制串分成3组，每组32位。</li> <li>3.将每个32位的二进制串转换成一个8位的16进制串。</li> <li>
	 * 4.将3个8位的16进制串合并成一个串，中间以","分割。</li>
	 * 
	 * @param timespan
	 *            一个48位的二进制串，如：
	 *            "011111111011111111111111111111111111111111111110"
	 * @return 一个16进制串，每位间以","分割。如："3fffcfff,ffffffff,fffffffc"
	 */
	public static String convertBinaryTime2Hex(String timespan) {
		if (timespan == null || timespan.equals("")) {
			return "";
		}

		String ret = "";
		String tmp = "";
		for (int i = 0; i < timespan.length(); i++) {
			tmp += timespan.charAt(i);
			tmp += timespan.charAt(i);
			// tmp += i;
			if ((i + 1) % 16 == 0) {
				if (!ret.equals("")) {
					ret += ",";
				}
				Long t = Long.parseLong(tmp, 2);
				String hexStr = Long.toHexString(t);
				if (hexStr.length() < 8) {
					int length = hexStr.length();
					for (int n = 0; n < 8 - length; n++) {
						hexStr = "0" + hexStr;
					}
				}

				ret += hexStr;
				tmp = "";
			}
		}

		return ret;
	}

	/**
	 * 进行时段格式转换，将输入的26位的2进制串转换成48位的二进制串。
	 * 
	 * @param timespan
	 *            一个16进制串，每位间以","分割。如："3fffcfff,ffffffff,fffffffc"
	 * @return 一个48位的二进制串，如："011111111011111111111111111111111111111111111110"
	 */
	public static String convertHexTime2Binary(String timespan) {
		if (timespan == null || timespan.equals("")) {
			return "";
		}

		String tmp = "";
		String ret = "";
		String[] strArr = timespan.split(",");
		for (int i = 0; i < strArr.length; i++) {
			String binStr = Long.toBinaryString(Long.parseLong(strArr[i], 16));
			if (binStr.length() < 32) {
				int length = binStr.length();
				for (int n = 0; n < 32 - length; n++) {
					binStr = "0" + binStr;
				}
			}
			tmp += binStr;
		}

		for (int i = 0; i < 48; i++) {
			ret += tmp.charAt(i * 2);
		}

		return ret;
	}

	/**
	 * 进行时段格式转换，将输入的32位的10进制串转换成48位的二进制串。
	 * 
	 * @param timespan
	 *            一个16进制串，每位间以","分割。如："1234567890,1234567890,1234567890c"
	 * @return 一个48位的二进制串，如："011111111011111111111111111111111111111111111110"
	 */
	public static String convertDecTime2Binary(String timespan) {
		if (timespan == null || timespan.equals("")) {
			return "";
		}

		String tmp = "";
		String ret = "";
		String[] strArr = timespan.split(",");
		for (int i = 0; i < strArr.length; i++) {
			String binStr = Long.toBinaryString(Long.parseLong(strArr[i], 10));
			if (binStr.length() < 32) {
				int length = binStr.length();
				for (int n = 0; n < 32 - length; n++) {
					binStr = "0" + binStr;
				}
			}
			tmp += binStr;
		}

		for (int i = 0; i < 48; i++) {
			ret += tmp.charAt(i * 2);
		}

		return ret;
	}

	/**
	 * 进行时段格式转换，对于输入的48位的01串，将进行如下操作： <li>
	 * 1.先将输入中每个0变成两个0，每个1变成2个1，形成一个96位的二进制串。</li> <li>
	 * 2.将上述的96位的二进制串分成3组，每组32位。</li> <li>3.将每个32位的二进制串转换成一个10位的10进制串。</li> <li>
	 * 4.将3个8位的16进制串合并成一个串，中间以","分割。</li>
	 * 
	 * @param timespan
	 *            一个48位的二进制串，如：
	 *            "011111111011111111111111111111111111111111111110"
	 * @return 一个16进制串，每位间以","分割。如："1234567890,1234567890,1234567890"
	 */
	public static String convertBinaryTime2Dec(String timespan) {
		if (timespan == null || timespan.equals("")) {
			return "";
		}

		String ret = "";
		String tmp = "";
		for (int i = 0; i < timespan.length(); i++) {
			tmp += timespan.charAt(i);
			tmp += timespan.charAt(i);
			// tmp += i;
			if ((i + 1) % 16 == 0) {
				if (!ret.equals("")) {
					ret += ",";
				}
				Long t = Long.parseLong(tmp, 2);
				String decStr = Long.toString(t);
				if (decStr.length() < 10) {
					int length = decStr.length();
					for (int n = 0; n < 10 - length; n++) {
						decStr = "0" + decStr;
					}
				}

				ret += decStr;
				tmp = "";
			}
		}

		return ret;
	}

	public static String secondToRender(int second) {
		if (second <= 0) {
			return "00:00";
		}
		int min = second / 60;
		int sec = second % 60;
		StringBuilder cs = new StringBuilder(5);
		if (min < 10) {
			cs.append("0");
		}
		cs.append(min);
		cs.append(":");
		if (sec < 10) {
			cs.append("0");
		}
		cs.append(sec);
		return cs.toString();
	}

	/**
	 * 计算指定日期+addMonth月+15号 返回格式"2009-10-15"
	 * 
	 * @param date
	 * @param addMonth
	 * @param monthDay
	 * @return
	 */
	public static String genericSpecdate(Date date, int addMonth, int monthDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, addMonth);
		cal.set(Calendar.DAY_OF_MONTH, monthDay);
		return getFormatDate(cal.getTime(), DATE_FORMAT);
	}

	/**
	 * 获得以今天为单位若干天以前或以后的日期的标准格式"Wed Feb 20 00:00:00 CST 2009"，是0点0分0秒。
	 * 
	 * @param idx
	 * @return
	 */
	public static Date getDateBeforeOrAfterV2(int idx) {
		return getDateBeforeOrAfter(getFormatDateToDate(getCurrDate()), idx);
	}

	/**
	 * 获得给定时间若干秒以前或以后的日期的标准格式。
	 * 
	 * @param curDate
	 * @param seconds
	 * @return curDate
	 */
	public static Date getSpecifiedDateTimeBySeconds(Date curDate, int seconds) {
		long time = (curDate.getTime() / 1000) + seconds;
		curDate.setTime(time * 1000);
		return curDate;
	}

	/**
	 * 获得给定日期当天23点59分59秒的标准格式。
	 * 
	 * @param curDate
	 * @return curDate
	 */
	public static Date getSpecifiedDateTime_235959(Date curDate) {
		return getSpecifiedDateTimeBySeconds(getFormatDateToDate(curDate), 24 * 60 * 60 - 1);
	}

	public static String getSpecifiedDateTime_month(Date curDate) {
		return getFormatDateTime(curDate, "MM.dd");
	}

	/**
	 * 获得年份
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获得月份
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}

	/**
	 * 获得天
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获得hour
	 * 
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * Feed 及 Comment 中间 取时间差
	 */
	private final static long SECOND = 60l;
	private final static long MINUTE = 60l * 60l;
	private final static long HOUR = 24l * 60l * 60l;

	public static String minusTime(String pubTime) {
		Date pubDate = DateTimeUtil.getFormatDateTime(pubTime);
		return minusTime(pubDate);
	}

	public static String minusDays(Date pubDate) {
		pubDate = getFormatDateToDate(pubDate);
		Date currentDate = getFormatDateToDate(new Date());
		long pL = pubDate.getTime();
		long cL = currentDate.getTime();

		long pass = (cL - pL);
		pass = pass / 1000;
		long result = pass / HOUR;
		//        // 考虑凌晨的问题
		//        if ( result ==0 && pL < getBeforeDawnOfYesterday().getTime()) {
		//            result++;
		//        }
		if (result == 0) {
			return "今天";
		} else {
			return result + " 天前";
		}

	}

	/**
	 * 昨天凌晨的时间
	 * 
	 * @return
	 */
	public static Date getBeforeDawnOfYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		System.out.println(getFormatDateTime(calendar.getTime()));
		return calendar.getTime();
	}

	//	public static void main(String[] args) {
	//		Date pubDate = DateTimeUtil.getFormatDateTime("2013-04-25 08:00:00");
	//		Date now = new Date();
	//		Date nextDay = DateTimeUtil.getDateBeforeOrAfter(now, -10000);
	//		System.out.println(DateTimeUtil.getFormatDate(nextDay));
	//	}

	public static Date setHour(Date pubTime, int hour) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(pubTime);
		calendar.set(Calendar.HOUR_OF_DAY, hour);

		return calendar.getTime();
	}

	public static String minusTime(Date pubDate) {
		String result = null;
		Date currentDate = new Date();
		long pL = pubDate.getTime();
		long cL = currentDate.getTime();
		long pass = (cL - pL);
		if (pass < 0) {
			result = "未知";
		} else {
			pass = pass / 1000;
			if (pass < SECOND) {
				result = "刚刚";
			} else if (pass < MINUTE) {
				result = (pass / SECOND) + "分钟前";
			} else if (pass < HOUR) {
				String pFuix = DateTimeUtil.isSameDay(currentDate, pubDate) ? "今天" : "昨天";
				String pubFormat = DateTimeUtil.getFormatDateTime(pubDate);
				try {
					pubFormat = pubFormat.substring((pubFormat.indexOf(" ") + 1), pubFormat.lastIndexOf(":"));
					result = pFuix + pubFormat;
				} catch (Exception e) {
					result = pass / MINUTE + "小时前";
				}
			} else {
				result = (pass / HOUR) + "天前";
			}
		}
		return result;
	}

	/**
	 * 对日期进行修改
	 * @param orgin 原始日期
	 * @param field 字段，Calendar.DAY_OF_MONTH,Calencar.MINUTES等
	 * @param amount 数量，正数表示增加，负数表示减少
	 * @return 新的日期
	 */
	public static Date add(Date orgin, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(orgin);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * 对日期进行修改
	 * @param orgin 原始日期
	 * @param field 字段，Calendar.DAY_OF_MONTH,Calencar.MINUTES等
	 * @return 新的日期
	 */
	public static Date set(Date orgin, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(orgin);
		calendar.set(field, amount);
		return calendar.getTime();
	}

	/**
	 * 得到两个时间毫秒的差的绝对值
	 * @param first 
	 * @param second
	 * @return 
	 */
	public static long millsDifferences(Date first, Date second) {
		return Math.abs(first.getTime() - second.getTime());
	}

	/**
	 * 得到两个时间day的差的绝对值
	 * @param first 
	 * @param second
	 * @return 
	 */
	public static int daysDifferences(Date first, Date second) {
		return (int) (millsDifferences(first, second) / TIME_DAY_MILLISECOND);
	}

	/**
	 * public static void main(String[] args) {
		Date now = new Date();
		System.out.println(hoursDifferences(now, add(now, Calendar.HOUR_OF_DAY, 10)));
	}
	 * 得到两个时间小时的差的绝对值
	 * @param first 
	 * @param second
	 * @return 
	 */
	public static int hoursDifferences(Date first, Date second) {
		return (int) (millsDifferences(first, second) / TIME_HOUR_MILLISECOND);
	}

	/**
	public static void main(String[] args) {
		Date now = new Date();
		System.out.println(minutesDifferences(now, add(now, Calendar.MINUTE, 10)));
	}
	 * 
	 * 得到两个时间分的差的绝对值
	 * @param first 
	 * @param second
	 * @return 
	 */
	public static int minutesDifferences(Date first, Date second) {
		return (int) (millsDifferences(first, second) / TIME_MINUTE_MILLISECOND);
	}

	/**
	 * 得到一天开始
		public static void main(String[] args) {
		System.out.println(DateTimeUtil.format(getOneDayBegin(new Date()), "yyyy-MM-dd HH:mm:ss SSS"));
	}

	 * @param date
	 * @return
	 */
	public static Date getOneDayBegin(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**得到一天结束
	public static void main(String[] args) {
	 	System.out.println(DateTimeUtil.format(getOneDayEnd(new Date()), "yyyy-MM-dd HH:mm:ss SSS"));
	}
	 * @param date
	 * @return
	 */
	public static Date getOneDayEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 判断日期是不是时，分，秒，毫秒都为0
	 * @param date
	 * @return
	 */
	public static boolean isDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if ((calendar.get(Calendar.HOUR_OF_DAY) != 0) || (calendar.get(Calendar.MINUTE) != 0) || (calendar.get(Calendar.SECOND) != 0)
				|| (calendar.get(Calendar.MILLISECOND) != 0)) {
			return false;
		}
		return true;
	}

	/**
	 * 判断日期是不是时，分，秒，毫秒都为0
	 * @param date
	 * @return
	 */
	public static void checkDay(Date date) {
		if (isDay(date)) {
			return;
		} else {
			throw new IllegalArgumentException("date:" + getFormatDate(date, TIME_FORMAT));
		}
	}

	/**
	 * 获得1000年之后的时间
	 * @return
	 */
	public static Date getRemoteDate() {
		return NEXT_THOUSAND_DAY;
	}

	/**
	 * max
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Date max(Date date1, Date date2) {
		if (date1.after(date2)) {
			return date1;
		} else {
			return date2;
		}
	}

	/**
	 * min
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Date min(Date date1, Date date2) {
		if (date1.after(date2)) {
			return date2;
		} else {
			return date1;
		}
	}
}
