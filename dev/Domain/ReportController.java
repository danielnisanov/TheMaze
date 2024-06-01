package Domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportController {
    private int reportIndex;
    private Map<String,Report> reports;
    private static ReportController report_controller;
    private static ProductController product_controller;


    public static ReportController getInstance(){
        if (report_controller == null)
            report_controller = new ReportController();
        return report_controller;
    }

    public ReportController() {
        reportIndex = 0;
        reports = new HashMap<>();
    }

    public void removeReport(int id) throws Exception {
        if (!reports.containsKey(Integer.toString(id)))
            throw new Exception("Report id doesn't exists");
        else {
            reports.remove(Integer.toString(id));
        }
    }

    public Report getReport(int id) throws Exception {
        if (!reports.containsKey(Integer.toString(id)))
            throw new Exception("Report id doesn't exists");
        else {
            return reports.get(Integer.toString(id));
        }
    }


    public ReportOfDamaged createDamagedReport( int id) throws Exception {
        if (reports.containsKey(Integer.toString(id)))
            throw new Exception("The ReportId already exists in the system");
        else {
            List<Item> damagedProd = product_controller.getDamagedItems();
            ReportOfDamaged report = new ReportOfDamaged(id, LocalDateTime.now(), damagedProd);
            reports.put(Integer.toString(id), report);
            return report;
        }
    }

    public ReportOfExpired createExpiredReport( int id) throws Exception {
        if (reports.containsKey(Integer.toString(id)))
            throw new Exception("The ReportId already exists in the system");
        else {
            List<Item> expiredProd = product_controller.getExpiredItems();
            ReportOfExpired report = new ReportOfExpired(id, LocalDateTime.now(), expiredProd);
            reports.put(Integer.toString(id), report);
            return report;
        }
    }


}
