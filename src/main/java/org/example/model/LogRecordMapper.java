package org.example.model;

import org.example.dto.LogRecordDTO;
import org.example.util.DateFormatter;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.Date;

public class LogRecordMapper {


    private static final TypeMap<LogRecordDTO, LogRecord> mapper;

    static {
        mapper = new ModelMapper()
                .createTypeMap(LogRecordDTO.class, LogRecord.class)
                .addMappings(mapping -> mapping.using(new LogRecordDateConverter()).map(LogRecordDTO::getDate, LogRecord::setDate))
        ;
    }

    public static LogRecord map(LogRecordDTO logRecordDto) {
        LogRecord logRecord = mapper.map(logRecordDto);
        return logRecord;
    }

    private static class LogRecordDateConverter extends AbstractConverter<String, Date> {
        @Override
        protected Date convert(String source) {
            return DateFormatter.toDateFormat(source);
        }
    }
}
