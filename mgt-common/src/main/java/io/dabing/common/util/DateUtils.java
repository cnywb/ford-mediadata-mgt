package io.dabing.common.util;

import org.apache.commons.lang.StringUtils;
import org.joda.time.Interval;
import org.joda.time.Period;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	
	public static String FORMAT_DATE_TIME_DEFAULT="yyyy-MM-dd HH:mm:ss";
	
	public static String FORMAT_DATE_DEFAULT="yyyy-MM-dd";
	
	public static String FORMAT_DATE_TIME_YYYYMMDDHHMMSS="yyyyMMddHHmmss";
	
	public static String FORMAT_DATE_YYYYMMDD="yyyyMMdd";
	
	public static Date parseDate(String text,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		try {
			return sdf.parse(text);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String format(Date date,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
	    return sdf.format(date);
	}
	
	
	public static String getYear(Date date){
		String dateStr=format(date, FORMAT_DATE_DEFAULT);
		String[] array=dateStr.split("-");
		return array[0];
	}
	public static String getMonth(Date date){
		String dateStr=format(date, FORMAT_DATE_DEFAULT);
		String[] array=dateStr.split("-");
		return array[1];
	}
	public static String getDate(Date date){
		String dateStr=format(date, FORMAT_DATE_DEFAULT);
		String[] array=dateStr.split("-");
		return array[2];
	}
	
	 public static Date getMonthStart(Date date) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        int index = calendar.get(Calendar.DAY_OF_MONTH);
	        calendar.add(Calendar.DATE, (1 - index));
	        return calendar.getTime();
	    }
	 
	 public static Date getMonthEnd(Date date) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.add(Calendar.MONTH, 1);
	        int index = calendar.get(Calendar.DAY_OF_MONTH);
	        calendar.add(Calendar.DATE, (-index));
	        return calendar.getTime();
	  }
	 /**
	  * 得到上个月的月初
	  * @param date
	  * @return
	  */
	 public static Date getLastMonthStart(Date date) {
		 Calendar calendar = Calendar.getInstance();
         calendar.setTime(date);
         calendar.add(Calendar.MONTH, -1);
         int index = calendar.get(Calendar.DAY_OF_MONTH);
         calendar.add(Calendar.DATE, (1 - index));
         return calendar.getTime();
	  }
	 /**
	  * 得到上个月的月末
	  * @param date
	  * @return
	  */
	 public static Date getLastMonthEnd(Date date) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.add(Calendar.MONTH,0);
	        int index = calendar.get(Calendar.DAY_OF_MONTH);
	        calendar.add(Calendar.DATE, (-index));
	        return calendar.getTime();
	  }
	 
	 public static Date getNext(Date date) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.add(Calendar.DATE, 1);
	        return calendar.getTime();
	 }
	 
	 public static Date getYesterday(Date date){
		  Calendar cal=Calendar.getInstance();
		  cal.setTime(date);
		  cal.add(Calendar.DATE,-1);
		  return cal.getTime();
	 }

	public static Date getDayBefore(Date date,int offset){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE,-offset);
		return cal.getTime();
	}

	/***
	 *
	 * @param startDate
	 * @param endDate
     */
	public static int daysBetween(Date startDate,Date endDate){
		if (startDate == null || endDate == null) {
			return -1;
		}
		Interval interval=null;
		try {
			// 毫秒mse
			interval = new Interval (startDate.getTime (), endDate.getTime ());
		}catch (Exception e){

		}
		if(interval==null){
			return -1;
		}
		return  interval.toPeriod ().getDays ();
	}

	/***
	 * 计算时间
	 * @param startDate
	 * @param endDate
     * @return
     */
	public static String calcDate(Date startDate,Date endDate){
		if (startDate == null || endDate == null) {
			return StringUtils.EMPTY;
		}
		Interval interval=null;
		try {
			// 毫秒mse
			 interval = new Interval (startDate.getTime (), endDate.getTime ());
		}catch (Exception e){

		}
		if(interval==null){
			return null;
		}
		Period period=interval.toPeriod ();
		StringBuffer  buffer=new StringBuffer ();
		//天
		if(period.getDays ()>0){
			buffer.append (period.getDays ());
			buffer.append ("天");
		}
		//小时
		if(period.getHours ()>0){
			buffer.append (period.getHours ());
			buffer.append ("时");
		}
		//分
		if(period.getMinutes ()>0){
			buffer.append (period.getMinutes ());
			buffer.append ("分");
		}
		//秒
		if(period.getSeconds ()>0){
			buffer.append (period.getSeconds ());
			buffer.append ("秒");
		}
		//秒
		if(period.getMillis ()>0){
			buffer.append (period.getMillis ());
			buffer.append ("毫秒");
		}
		return buffer.toString ();
	}


	public static void main(String[] args) throws ParseException {
		String dateStart = "2013-08-12 16:29:58";
		String dateStop = "2013-08-13 16:31:48";

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date d1 = format.parse(dateStart);
		Date d2 = format.parse(dateStop);
		String str=DateUtils.calcDate (d1,d2);

		System.out.println(str);
	}
	 
	 /**
	  * 得到上周一
	  * @param date
	  * @return
	  */
	 public static Date getLastMonday(Date date) {
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    cal.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
		    cal.add(Calendar.DATE, -1*7);
		    cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		    return cal.getTime();
	}
	 /**
	  * 得到上周日
	  * @param date
	  * @return
	  */
	 public static Date getLastSunday(Date date) {
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    cal.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
		    cal.add(Calendar.DATE, -1*7);
		    cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		    return cal.getTime();
	 }

	/**
	 * 校验日期格式
	 * @param text
	 * @param format
     * @return
     */
	public static boolean isValidDate(String text,String format) {
		return parseDate(text,format)!=null;
	}


	public static Date formatEndDate(Date endDate){
		if(endDate==null){
			return null;
		}
		String endDateStr = DateUtils.format(endDate,DateUtils.FORMAT_DATE_DEFAULT);
		endDateStr+=" 23:59:59";
		return parseDate(endDateStr,DateUtils.FORMAT_DATE_TIME_DEFAULT);
	}

}
