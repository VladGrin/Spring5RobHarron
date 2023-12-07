package org.example.spring_type_converter;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.PostConstruct;

public class StringToDateTimeConverter implements Converter<String, DateTime> {

//    private static final String DEFAULT_DATE_PATTERN = "MM-dd-yyyy";

    private DateTimeFormatter dateFormat;

    @Getter
    @Setter
    private String datePattern;// = DEFAULT_DATE_PATTERN;

    @PostConstruct
    public void init() {
        dateFormat = DateTimeFormat.forPattern(datePattern);
    }

    @Override
    public DateTime convert(@NonNull String dateString) {
        return dateFormat.parseDateTime(dateString);
    }
}
