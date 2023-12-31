package com.apress.prospring5.ch16.util;

import lombok.NonNull;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter implements Formatter<Date> {
	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Date parse(@NonNull String s, @NonNull Locale locale) throws ParseException {
		return FORMATTER.parse(s);
	}

	@Override
	public String print(@NonNull Date date, @NonNull Locale locale) {
		return FORMATTER.format(date);
	}

}