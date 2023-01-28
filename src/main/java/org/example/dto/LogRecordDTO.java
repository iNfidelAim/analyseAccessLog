package org.example.dto;


public class LogRecordDTO {
    private String date;
    private String httpStatusCode;
    private String processingTimeMs;

    public LogRecordDTO() {
    }

    public LogRecordDTO(String date, String httpStatusCode, String processingTimeMs) {
        this.date = date;
        this.httpStatusCode = httpStatusCode;
        this.processingTimeMs = processingTimeMs;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(String httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getProcessingTimeMs() {
        return processingTimeMs;
    }

    public void setProcessingTimeMs(String processingTimeMs) {
        this.processingTimeMs = processingTimeMs;
    }

    @Override
    public String toString() {
        return "LogRecordDto{" +
                "date='" + date + '\'' +
                ", httpStatusCode='" + httpStatusCode + '\'' +
                ", processingTimeMs='" + processingTimeMs + '\'' +
                '}';
    }
}