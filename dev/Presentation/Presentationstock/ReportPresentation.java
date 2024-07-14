package Presentation.Presentationstock;
import Domain.Domainstock.*;

import java.util.Scanner;

public class ReportPresentation {
    private static ReportController reportController;
    private final Scanner scanner;

    public ReportPresentation(Scanner scanner) {
        reportController = ReportController.getInstance();
        this.scanner = scanner;
    }

    public void createInventoryReport() {
        try {
            System.out.print("Enter category name: ");
            String categoryName = scanner.nextLine();
            System.out.print("Enter sub category name: ");
            String subCategoryName = scanner.nextLine();
            System.out.print("Enter sub sub category name: ");
            String subSubCategoryName = scanner.nextLine();
            System.out.println();
            ReportByCategory report = reportController.createInventoryReport(categoryName, subCategoryName, subSubCategoryName);
            System.out.println(report.toString());
        }
        catch (Exception e){
            System.out.println("Error creating inventory report: " + e.getMessage());
        }
    }

    public void createDamagedReport() {
        try {
            ReportOfDamaged report = reportController.createDamagedReport();
            System.out.println();
            System.out.println(report.toString());
        }
        catch (Exception e){
            System.out.println("Error creating damaged report: " + e.getMessage());
        }
    }

    public void createExpiredReport() {
        try {
            ReportOfExpired report = reportController.createExpiredReport();
            System.out.println();
            System.out.println(report.toString());
        }
        catch (Exception e){
            System.out.println("Error creating expired report: " + e.getMessage());
        }
    }

    public void createMissingReport(){
        try {
            System.out.print("Enter category name: ");
            String categoryName = scanner.nextLine();
            System.out.print("Enter sub category name: ");
            String subCategoryName = scanner.nextLine();
            System.out.print("Enter sub sub category name: ");
            String subSubCategoryName = scanner.nextLine();
            System.out.println();
            ReportOfMissing report = reportController.createMissingReport(categoryName, subCategoryName, subSubCategoryName);
            System.out.println(report.toString());
        }
        catch (Exception e){
            System.out.println("Error creating missing report: " + e.getMessage());
        }
    }

    public void displayReport() throws Exception {
        System.out.print("Enter report id: ");
        int reportID = Integer.parseInt(scanner.nextLine());
        try {
            System.out.println(reportController.getReport(reportID).toString());
        } catch (Exception e) {
            System.out.println("Error displaying report: " + e.getMessage());
        }
    }

    public void removeReport() throws Exception {
        System.out.print("Enter report id: ");
        int reportID = Integer.parseInt(scanner.nextLine());
        try {
            reportController.removeReport(reportID);
        } catch (Exception e) {
            System.out.println("Error displaying report: " + e.getMessage());
        }
    }
}
