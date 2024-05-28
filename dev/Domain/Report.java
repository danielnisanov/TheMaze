package Domain;

import java.time.LocalDateTime;

public class Report {
    protected int reportID;
    protected LocalDateTime reportDate;

    public Report(int reportID, LocalDateTime reportDate) {
        this.reportID = reportID;
        this.reportDate = reportDate;
    }

    public int getReportID() {
        return reportID;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportID='" + reportID + '\'' +
                ", reportDate=" + reportDate +
                '}';
    }
}
