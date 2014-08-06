package com.rabbit.vip.util;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class ParameterChecker {
	public static void notBlank(String key, String value) {
		if (StringUtils.isBlank(value)) {
			throw new IllegalArgumentException("{" + key + ":" + value + "} is blank");
		}
	}

	public static void notEmpty(String key, String value) {
		if (StringUtils.isEmpty(value)) {
			throw new IllegalArgumentException("{" + key + ":" + value + "} is blank");
		}
	}

	public static void notNull(String key, Object value) {
		if (value == null) {
			throw new IllegalArgumentException("{" + key + ":" + value + "} is null");
		}
	}

	public static void notLittleThan(String key, int value, int limit) {
		if (value < limit) {
			throw new IllegalArgumentException("{" + key + ":" + value + "} is little than " + limit);
		}
	}

	public static void notLittleThan(String key, long value, long limit) {
		if (value < limit) {
			throw new IllegalArgumentException("{" + key + ":" + value + "} is little than " + limit);
		}
	}

	public static void isTrue(String key, boolean actual) {
		if (!actual) {
			throw new IllegalArgumentException("{" + key + "}is not true");
		}
	}

	public static void notDateGt(Date date1, Date date2) {
		if (date1.after(date2)) {
			throw new IllegalArgumentException(DateTimeUtil.getFormatDateTime(date1) + " is greater than " + DateTimeUtil.getFormatDateTime(date2));
		}
	}

	/**
	 * 只包含天，时分秒毫秒都为0
	 * @param date
	 */
	public static void isDay(Date date) {
		if (!DateTimeUtil.isDay(date)) {
			throw new IllegalArgumentException("date is not day:" + DateTimeUtil.getFormatDate(date, "yyyy-MM-dd HH:mm:ss SSS"));
		}
	}
}
