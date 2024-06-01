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
        reportIndex = 1;
        reports = new HashMap<>();
        product_controller = ProductController.getInstance();

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


    public ReportByCategory createInventoryReport( String categoryName, String subCategoryName, String subSubCategoryName) {
        List<Product> byCategoryPro = product_controller.getProductsByCategory(categoryName, subCategoryName, subSubCategoryName);
        ReportByCategory report = new ReportByCategory(reportIndex, LocalDateTime.now(), byCategoryPro, categoryName);
        reports.put(Integer.toString(reportIndex), report);
        reportIndex++;
        return report;
    }

    public ReportOfDamaged createDamagedReport()  {
        List<Item> damagedProd = product_controller.getDamagedItems();
        ReportOfDamaged report = new ReportOfDamaged(reportIndex, LocalDateTime.now(), damagedProd);
        reports.put(Integer.toString(reportIndex), report);
        reportIndex++;
        return report;

    }

    public ReportOfExpired createExpiredReport()  {
        List<Item> expiredItems = product_controller.getExpiredItems();
        ReportOfExpired report = new ReportOfExpired(reportIndex, LocalDateTime.now(), expiredItems);
        reports.put(Integer.toString(reportIndex), report);
        reportIndex++;
        return report;
    }

}
