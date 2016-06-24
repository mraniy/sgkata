package com.kata.bank.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateUtils {
	/**
	 * transformer la date en String
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String fromDateToString(Date date, String format) {
		if (date != null) {
			DateFormat df = new SimpleDateFormat(format);
			return df.format(date);
		}
		return null;
	}

	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date fromStringToDate(String date, String format) {
		if (StringUtils.isNotEmpty(date)) {
			DateFormat df = new SimpleDateFormat(format);
			try {
				return df.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;

	}

}
