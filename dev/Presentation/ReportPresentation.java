package Presentation;

import Domain.ReportController;
import Domain.ReportByCategory;
import Domain.ReportOfDamaged;
import Domain.ReportOfExpired;

import java.util.Scanner;

public class ReportPresentation {
    private static ReportController reportController;
    private final Scanner scanner;

    public ReportPresentation(Scanner scanner) {
        reportController = ReportController.getInstance();
        this.scanner = scanner;
    }

    public void createInventoryReport() {
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

    public void createDamagedReport() {
        ReportOfDamaged report = reportController.createDamagedReport();
        System.out.println();
        System.out.println(report.toString());
    }

    public void createExpiredReport() {
        ReportOfExpired report = reportController.createExpiredReport();
        System.out.println();
        System.out.println(report.toString());
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
