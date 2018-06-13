package com.chaos.fission.frameworks.tool.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.chaos.fission.config.params.CommonConfig;

public class TimeTool {
	public static Timestamp getCurrentTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	private static SimpleDateFormat getSDF(String format) {
		String fmtString = !format.isEmpty()? format: CommonConfig.COMMON_DEFAULT_TIME_FORMAT;
        return new SimpleDateFormat(fmtString);
	}
	
	public static String formatDate(Date date, String format) {
        return getSDF(format).format(date);
	}
	
	public static String currentDate(String format) {
		return formatDate(new Date(), format);
	}
	
	public static String simpleDate() {
		return currentDate(CommonConfig.SIMPLE_DEFAULT_TIME_FORMAT);
	}
   
	public static Date convertStr(String strDate, String format) {
		try {
			return getSDF(format).parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return null;
	}
	
	public static Date currentParse(String format) {
		return convertStr(currentDate(format), format);
	}
	
	public static Date simpleParse() {
		return convertStr(simpleDate(), CommonConfig.SIMPLE_DEFAULT_TIME_FORMAT);
	}
}
