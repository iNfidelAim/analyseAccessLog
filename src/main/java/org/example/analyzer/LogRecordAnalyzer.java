package org.example.analyzer;

import org.example.model.ResultRecord;


import java.util.List;
import java.util.stream.Stream;

public interface LogRecordAnalyzer {

    void analyze(Stream<String> data);
    List<ResultRecord> getResultRecords();

    }

