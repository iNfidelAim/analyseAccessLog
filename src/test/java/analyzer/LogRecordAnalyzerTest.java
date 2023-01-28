package analyzer;

import org.example.Main;
import org.example.analyzer.LogRecordAnalyzer;
import org.example.model.ResultRecord;
import org.example.util.DateFormatter;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogRecordAnalyzerTest {
    private static final Logger logger = LoggerFactory.getLogger(LogRecordAnalyzerTest.class);

    @Test
    void analyzeAboveTimeThreshold() {
        String[] args = new String[]{"-t", "32", "-a", "100.0", "-nl", "2", "-f", "src/test/resources/access-log-for-time-threshold-checks", "-tm", "1"};
        Main.main(args);

        LogRecordAnalyzer analyzer = Main.App.getAnalyzer();
        analyzer.getResultRecords().forEach(resultRecord -> logger.info("resultRecord = {}", resultRecord));

        List<ResultRecord> expected = Arrays.asList(
                new ResultRecord(DateFormatter.toDateFormat("14/06/2013:16:47:02"), DateFormatter.toDateFormat("14/06/2013:16:47:02"), 0.00F),
                new ResultRecord(DateFormatter.toDateFormat("14/06/2014:16:47:02"), DateFormatter.toDateFormat("14/06/2014:16:47:02"), 50.00F),
                new ResultRecord(DateFormatter.toDateFormat("14/06/2017:16:47:02"), DateFormatter.toDateFormat("14/06/2017:16:47:02"), 50.00F)
        );

        assertEquals(expected, analyzer.getResultRecords());
    }

    @Test
    void analyzeBelowTimeThreshold() {
        String[] args = new String[]{"-t", "50", "-a", "100.0", "-nl", "5", "-f", "src/test/resources/access-log-for-time-threshold-checks", "-tm", "1"};
        Main.main(args);

        LogRecordAnalyzer analyzer = Main.App.getAnalyzer();
        analyzer.getResultRecords().forEach(resultRecord -> logger.info("resultRecord = {}", resultRecord));

        List<ResultRecord> expected = new ArrayList<>();

        assertEquals(expected, analyzer.getResultRecords());
    }

    @Test
    void analyzeHttpStatus500() {
        String[] args = new String[]{"-t", "999", "-a", "100.0", "-nl", "5", "-f", "src/test/resources/access-log-for-http-status-checks", "-tm", "1"};
        Main.main(args);

        LogRecordAnalyzer analyzer = Main.App.getAnalyzer();
        analyzer.getResultRecords().forEach(resultRecord -> logger.info("resultRecord = {}", resultRecord));

        List<ResultRecord> expected = Arrays.asList(
                new ResultRecord(DateFormatter.toDateFormat("14/06/2001:16:47:03"), DateFormatter.toDateFormat("14/06/2005:16:47:03"), 0.00F),
                new ResultRecord(DateFormatter.toDateFormat("14/06/2020:16:47:03"), DateFormatter.toDateFormat("14/06/2040:16:47:03"), 60.00F)
        );

        assertEquals(expected, analyzer.getResultRecords());
    }
}