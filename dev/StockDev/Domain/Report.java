package Domain;

import java.time.LocalDate;

public class Report {
    protected int reportID;
    protected LocalDate reportDate;


    public Report(int reportID, LocalDate reportDate) {
        this.reportID = reportID;
        this.reportDate = reportDate;
    }

    public int getReportID() {
        return reportID;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public void setReportDate(LocalDate reportDate) {
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
