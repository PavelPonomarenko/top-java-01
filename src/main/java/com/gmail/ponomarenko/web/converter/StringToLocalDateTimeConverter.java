package com.gmail.ponomarenko.web.converter;

import com.gmail.ponomarenko.util.TimeUtil;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(String param) {
        return TimeUtil.toDateTime(param, DateTimeFormatter.ISO_DATE_TIME);
    }
}
