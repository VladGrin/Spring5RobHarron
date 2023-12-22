package org.example.rest.util;

import lombok.extern.slf4j.Slf4j;
import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Slf4j
public class DateTimeFieldHandler extends GeneralizedFieldHandler {
    private static String dateFormatPattern;

    @Override
    public void setConfiguration(Properties config) throws ValidityException {
        dateFormatPattern = config.getProperty("date-format");
    }

    @Override
    public Object convertUponGet(Object value) {
        Date dateTime = (Date) value;
        return format(dateTime);
    }

    @Override
    public Object convertUponSet(Object value) {
        String dateTimeString = (String) value;
        return parse(dateTimeString);

    }

    @Override
    public Class<Date> getFieldType() {
        return Date.class;
    }

    protected static String format(final Date dateTime) {
        String dateTimeString = "";
        if (dateTime != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormatPattern);
            dateTimeString = sdf.format(dateTime);
        }
        return dateTimeString;
    }

    protected static Date parse(final String dateTimeString) {
        Date dateTime = new Date();
        if (dateTimeString != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormatPattern);
            try {
                dateTime = sdf.parse(dateTimeString);
            } catch (ParseException e) {
                log.error("Not а valida date:" + dateTimeString, e);

            }
        }
        return dateTime;
    }
}
