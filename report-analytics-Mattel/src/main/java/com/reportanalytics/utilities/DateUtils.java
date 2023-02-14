package com.reportanalytics.utilities;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static Date getStartOfDay() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		calendar.set(year, month, day, 0, 0, 0);
		long approximateTimestamp = calendar.getTime().getTime();
		long extraMillis = (approximateTimestamp % 1000);
		long exactTimestamp = approximateTimestamp - extraMillis;
		return new Date(exactTimestamp);
	}

	public static Date getEndOfDay() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		calendar.set(year, month, day, 23, 59, 59);
		long approximateTimestamp = calendar.getTime().getTime();
		long extraMillis = (approximateTimestamp % 1000);
		long exactTimestamp = approximateTimestamp - extraMillis + 999;
		return new Date(exactTimestamp);
	}
}
