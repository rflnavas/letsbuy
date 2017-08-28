package com.rnr.letsbuy.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {

	public static String DEFAULT_FORMAT = "dd/MM/yyyy";
	
	private static DateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);
	
	public static Date formatDate(String source){
		try {
			return df.parse(source);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
