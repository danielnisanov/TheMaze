package Domain;

import DAL.ReportDAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ReportRepository implements IRepository<Report> {
    private Map<String, Report> reports = new HashMap<>();
    private ReportDAO reportDAO;

    public ReportRepository() {
        reports = new HashMap<>();
        try {
            reportDAO = new ReportDAO();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize ReportDAO", e);
        }
    }

    @Override
    public void add(Report report) throws Exception {
        if (reports.containsKey(Integer.toString(report.getReportID()))) {
            throw new Exception("Item " + report.getReportID() + " already exists.");
        }
        else {
            Report reportFromDB = reportDAO.get(Integer.toString(report.getReportID()));
            if (reportFromDB != null) {
                reports.put(Integer.toString(report.getReportID()), reportFromDB);
            }
            else {
                reportDAO.add(report);
                reports.put(Integer.toString(report.getReportID()), report);
            }
        }
    }

    @Override
    public void remove(String id) throws Exception {
        if (!reports.containsKey(id)) {
            Report reportFromDB = reportDAO.get(id);
            if (reportFromDB == null) {
                throw new Exception("Report " + id + " doesn't exist.");
            }
            else {
                reportDAO.remove(id);
            }
        }
        else {
            reports.remove(id);
            reportDAO.remove(id);
        }
    }

    @Override
    public Report get(String id) throws Exception {
        Report report = reports.get(id);
        if (report != null) {
            return report;
        }
        else {
            report = reportDAO.get(id);
            if (report != null) {
                reports.put(id, report);
                return report;
            }
            else {
                throw new Exception("Report " + id + " doesn't exist.");
            }
        }
    }

    public void reportIsExist (String name) throws Exception{
        if(!reports.containsKey(name)){
            throw new Exception("Report "+ name +" doesn't exist.");
        }
    }
}
