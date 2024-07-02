package Domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportController {
    private int reportIndex;
    //private Map<String,Report> reports;

    private ReportRepository reportRepo;
    private static ReportController report_controller;
    private static ProductController product_controller;


    public static ReportController getInstance(){
        if (report_controller == null)
            report_controller = new ReportController();
        return report_controller;
    }

    public ReportController() {
        reportIndex = 1;
        //reports = new HashMap<>();
        product_controller = ProductController.getInstance();
        reportRepo = new ReportRepository();
    }

    public void removeReport(int id) throws Exception {
//        if (!reports.containsKey(Integer.toString(id)))
//            throw new Exception("Report id doesn't exists");
//        else {
//            reports.remove(Integer.toString(id));
//        }
        reportRepo.remove(Integer.toString(id));
    }

    public Report getReport(int id) throws Exception {
//        if (!reports.containsKey(Integer.toString(id)))
//            throw new Exception("Report id doesn't exists");
//        else {
//            return reports.get(Integer.toString(id));
//        }
        return reportRepo.get(Integer.toString(id));
    }

    //FIXME
    public ReportByCategory createInventoryReport( String categoryName, String subCategoryName, String subSubCategoryName) throws Exception {
        List<Product> byCategoryPro = product_controller.getProductsByCategory(categoryName, subCategoryName, subSubCategoryName);
        ReportByCategory report = new ReportByCategory(reportIndex, LocalDate.now(), byCategoryPro, categoryName);
        reportRepo.add(report);
        //reports.put(Integer.toString(reportIndex), report);
        reportIndex++;
        return report;
    }

    //FIXME
    public ReportOfDamaged createDamagedReport() throws Exception {
        Map<Item, String> damagedItems = product_controller.getDamagedItems();
        ReportOfDamaged report = new ReportOfDamaged(reportIndex, LocalDate.now(), damagedItems);
        reportRepo.add(report);
        //reports.put(Integer.toString(reportIndex), report);
        reportIndex++;
        return report;

    }

    //FIXME
    public ReportOfExpired createExpiredReport() throws Exception {
        Map<Item, String> expiredItems = product_controller.getExpiredItems();
        ReportOfExpired report = new ReportOfExpired(reportIndex, LocalDate.now(), expiredItems);
        reportRepo.add(report);
        //reports.put(Integer.toString(reportIndex), report);
        reportIndex++;
        return report;
    }

    //FIXME
    public ReportOfMissing createMissingReport( String categoryName, String subCategoryName, String subSubCategoryName) throws Exception {
        List<Product> byCategoryPro = product_controller.getMissingProductsByCategory(categoryName, subCategoryName, subSubCategoryName);
        ReportOfMissing report = new ReportOfMissing(reportIndex, LocalDate.now(), byCategoryPro);
        reportRepo.add(report);
        //reports.put(Integer.toString(reportIndex), report);
        reportIndex++;
        return report;
    }

}
