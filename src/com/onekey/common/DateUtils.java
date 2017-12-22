package com.onekey.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;

public final class DateUtils {
	/**
     * Ӣ�ļ�д��Ĭ�ϣ��磺2010-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";
    /**
     * Ӣ��ȫ�� �磺2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    public static String FORMAT_LONG1 = "yyyy-MM-dd HH:mm";
    /**
     * ��ȷ�����������ʱ�� �磺yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    public static String FORMAT_FULL_2 = "yyyyMMddHHmmss";
    /**
     * ���ļ�д �磺2010��12��01��
     */
    public static String FORMAT_SHORT_CN = "yyyy��MM��dd";
    public static String FORMAT_SHORT_TIME = "HH:mm:ss";
    /**
     * ����ȫ�� �磺2010��12��01�� 23ʱ15��06��
     */
    public static String FORMAT_LONG_CN = "yyyy��MM��dd��  HHʱmm��ss��";
    /**
     * ��ȷ���������������ʱ��
     */
    public static String FORMAT_FULL_CN = "yyyy��MM��dd��  HHʱmm��ss��SSS����";

	
	public static SimpleDateFormat mFormatter = new SimpleDateFormat("yyyy-MM-dd");
	public static Calendar mCalendar = Calendar.getInstance();

	/**
	 * 
	 * @param dateString
	 * @param formatStr
	 * @return
	 */
	public static String FormatDateStrng(String dateString) {
		if (dateString == null || dateString.equals("null")) {
			return "";
		}
		if (dateString.length() > 0) {
			dateString = dateString.substring(0, 10);
		}
		return dateString;
	}
		
	public static String getToday() {  
		Date curDate = new Date(System.currentTimeMillis());   
		return mFormatter.format(curDate);   
	}
	
	public static String getCurrentDate() {		
		Calendar c = Calendar.getInstance();
		return new SimpleDateFormat("yyyy-MM").format(c.getTime());
	}
	
	public static String getNextMonth(String current) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(stringToDate("yyyy-MM", current));
		calendar.set(2, calendar.get(Calendar.MONTH) + 1);
		return new SimpleDateFormat("yyyy-MM").format(calendar.getTime());
	}
	
	public static String getFirstDayOfMonth(String month) {
		mCalendar.setTime(stringToDate("yyyy-MM", month));
		mCalendar.add(Calendar.MONTH, 0);
		mCalendar.set(Calendar.DAY_OF_MONTH, 1);
		return mFormatter.format(mCalendar.getTime());
	}
	
	public static String getLastDayOfMonth(String month) {
		mCalendar.setTime(stringToDate("yyyy-MM", month));
		mCalendar.set(Calendar.DAY_OF_MONTH, mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return mFormatter.format(mCalendar.getTime());
	}
	
	public static Date stringToDate(String style, String date) {
		if (date == null || date.equals("")) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(style);
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	public static String dateToString(String style, Date date) {
		if ((date == null) || (date.equals(""))) {
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(style);
		return formatter.format(date);
	}
	
	public static String calculateDuration(Activity activity, Date startTime, Date endTime) {
		String duration = 1 + (endTime.getTime() - startTime.getTime())
				/ (24 * 3600 * 1000) + "";
		return duration;
	}
	
	public static int calculateDuration(Date startTime, Date endTime) {
		int duration = (int)(1 + (endTime.getTime() - startTime.getTime())
				/ (24 * 3600 * 1000));
		return duration;
	}
	
	/*
	 * �Ƿ�ͬһ��
	 */
	public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean isSameMonth = isSameYear && (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH));
        boolean isSameDate = isSameMonth && (cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH));

        return isSameDate;
    }  
	
	/*
	 * �Ƿ�ͬһ���ͬһ��
	 */
	public static boolean isSameMonth(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean isSameMonth = isSameYear && (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH));

        return isSameMonth;
    }    

	/*
	 * �Ƿ�ͬһ���ͬһ��
	 */
	public static boolean isSameWeek(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        
        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean isSameWeek = isSameYear && (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR));

        return isSameWeek;
    } 	

}
