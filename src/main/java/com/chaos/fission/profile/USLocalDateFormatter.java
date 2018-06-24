package com.chaos.fission.profile;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.format.Formatter;

public class USLocalDateFormatter implements Formatter<LocalDate> {

	public static final String US_PATTERN = "MM/dd/yyyy";
	public static final String NORMAL_PATTERN = "yyyy/MM/dd";

	@Override
	public String print(LocalDate object, Locale locale) {
		return getOfPattern(locale).format(object);
	}

	@Override
	public LocalDate parse(String text, Locale locale) throws ParseException {
		return LocalDate.parse(text, getOfPattern(locale));
	}
	
	private DateTimeFormatter getOfPattern(Locale locale) {
		return DateTimeFormatter.ofPattern(getPattern(locale));
	}

	public static String getPattern(Locale locale) {
		return isUnitedStates(locale) ? US_PATTERN : NORMAL_PATTERN;
	}

	public static boolean isUnitedStates(Locale locale) {
		return Locale.US.getCountry().equals(locale.getCountry());
	}

}
