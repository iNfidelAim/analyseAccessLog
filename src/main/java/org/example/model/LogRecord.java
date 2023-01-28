package org.example.model;

import org.example.util.DateFormatter;

import java.util.Date;

public class LogRecord {

    private Date date;
    private short httpStatusCode;
    private float processingTimeMs;

    public LogRecord() {
    }

    public LogRecord(Date date, short httpStatusCode, float processingTimeMs) {
        this.date = date;
        this.httpStatusCode = httpStatusCode;
        this.processingTimeMs = processingTimeMs;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public short getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(short httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public float getProcessingTimeMs() {
        return processingTimeMs;
    }

    public void setProcessingTimeMs(float processingTimeMs) {
        this.processingTimeMs = processingTimeMs;
    }

    @Override
    public String toString() {
        return "LogRecord{" +
                "date=" + DateFormatter.toStringFormat(date) +
                ", httpStatusCode=" + httpStatusCode +
                ", processingTimeMs=" + processingTimeMs +
                '}';
    }
}
