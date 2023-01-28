package org.example.model;

import org.example.util.DateFormatter;
import org.example.util.FloatFormatter;

import java.util.Date;
import java.util.Objects;

public class ResultRecord {

    public Date startDate;
    public Date endDate;
    public float accessibility;

    public ResultRecord() {
    }

    public ResultRecord(Date startDate, Date endDate, float accessibility) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.accessibility = accessibility;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public float getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(float accessibility) {
        this.accessibility = accessibility;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultRecord that = (ResultRecord) o;
        return Float.compare(FloatFormatter.transformFormat(that.accessibility), FloatFormatter.transformFormat(accessibility)) == 0 &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, accessibility);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s",
                DateFormatter.toStringFormat(startDate),
                DateFormatter.toStringFormat(endDate),
                FloatFormatter.toStringForamt(accessibility)
        );
    }
}
