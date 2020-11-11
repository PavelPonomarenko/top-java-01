package com.gmail.ponomarenko.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Converter(autoApply = true)
public class TimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

//    public static LocalDateTime convertToLocalDateTimeFromSqlTimestamp(Timestamp timestamp) {
//        return timestamp.toLocalDateTime();
//    }
//
//    public static Timestamp convertToSqlTimestampFromLocalDateTimeFrom(LocalDateTime localDateTime) {
//        return Timestamp.valueOf(localDateTime);
//    }

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {

        return Timestamp.valueOf(localDateTime);

    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        return timestamp.toLocalDateTime();

    }
}
