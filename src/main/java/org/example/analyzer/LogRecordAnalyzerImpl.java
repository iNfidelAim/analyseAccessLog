package org.example.analyzer;


import org.example.dto.LogRecordDTO;
import org.example.model.LogRecord;
import org.example.model.LogRecordMapper;
import org.example.model.ResultRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class LogRecordAnalyzerImpl implements LogRecordAnalyzer {

        private static final Logger logger = LoggerFactory.getLogger(LogRecordAnalyzerImpl.class);
        private List<ResultRecord> resultRecords;
        private List<ResultRecord> fullResultRecords = new ArrayList<>();

        private int minHttpStatusCode;
        private int maxHttpStatusCode;

        private Float time;
        private Float accessibility;
        private Integer linesCount;
        private Integer testMode;

        public LogRecordAnalyzerImpl(int minHttpStatusCode, int maxHttpStatusCode, Float time, Float accessibility, Integer linesCount, Integer testMode) {
            this.minHttpStatusCode = minHttpStatusCode;
            this.maxHttpStatusCode = maxHttpStatusCode;
            this.time = time;
            this.accessibility = accessibility;
            this.linesCount = linesCount;
            this.testMode = testMode;
        }

        @Override
        public void analyze(Stream<String> data) {
            resultRecords = new ArrayList<>(linesCount);
            Stream<LogRecord> logRecordStream = convert(data);
            Map<Boolean, List<LogRecord>> partitionedData = getPartitioningLogRecords(logRecordStream);
            collectFilteredResultRecords(partitionedData);
            printResult();
        }

        private Stream<LogRecord> convert(Stream<String> data) {
            return data
                    .map(s -> s.split(" "))
                    .map(strings -> LogRecordMapper.map(new LogRecordDTO(strings[3].substring(1), strings[8], strings[10])));
        }

        private Map<Boolean, List<LogRecord>> getPartitioningLogRecords(Stream<LogRecord> logRecordStream) {
            return logRecordStream.collect(
                    Collectors.partitioningBy(
                            logRecord ->
                                    (logRecord.getHttpStatusCode() >= minHttpStatusCode
                                            && logRecord.getHttpStatusCode() < maxHttpStatusCode)
                                            || logRecord.getProcessingTimeMs() > time
                    )
            );
        }

        private void collectFilteredResultRecords(Map<Boolean, List<LogRecord>> data) {
            data.forEach((aBoolean, logRecords) -> {
                if (aBoolean && !logRecords.isEmpty()) {

                    float successCount = data.get(false).size();
                    float failureCount = data.get(true).size();
                    float quantity = successCount + failureCount;

                    float currAccessibility = calcAccessibility(successCount, quantity);
                    if (currAccessibility <= accessibility) {
                        long from = getMinDate(getTimeStream(logRecords));
                        long to = getMaxDate(getTimeStream(logRecords));

                        resultRecords.add(new ResultRecord(new Date(from), new Date(to), currAccessibility));

                        if (testMode == 1) {
                            fullResultRecords.addAll(resultRecords);
                            printLogRecords(logRecords);
                        }
                    }
                }
            });
        }

        private float calcAccessibility(float current, float quantity) {
            return current/quantity*100;
        }

        private LongStream getTimeStream(List<LogRecord> records) {
            return records.stream().mapToLong(logRecord -> logRecord.getDate().getTime());
        }

        private long getMinDate(LongStream logRecordsDates) {
            return logRecordsDates.min().getAsLong();
        }

        private long getMaxDate(LongStream logRecordsDates) {
            return logRecordsDates.max().getAsLong();
        }

        private void printLogRecords(List<LogRecord> logRecords) {
            StringBuilder tmp = new StringBuilder();
            logRecords.forEach(logRecord -> {
                tmp.append(logRecord).append("\n");
            });
            if (!logRecords.isEmpty()) {
                logger.info("LogRecords:\n{}", tmp);
            }
        }

        private void printResult() {
            getAscSortedResult(resultRecords).forEach(System.out::println);
        }

        @Override
        public List<ResultRecord> getResultRecords() {
            return getAscSortedResult(fullResultRecords);
        }

        private List<ResultRecord> getAscSortedResult(List<ResultRecord> resultRecords) {
            resultRecords.sort(Comparator.comparing(o -> o.startDate));
            return resultRecords;
        }
    }


