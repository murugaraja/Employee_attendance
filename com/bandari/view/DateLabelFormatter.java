package com.bandari.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField;

public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
	private static final long serialVersionUID = 1L;
	private String datePattern = "yyyy-MM-dd";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}

		return "";
	}
}