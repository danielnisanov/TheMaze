package Presentation;

import java.util.Scanner;
import Domain.ProductController;
import Domain.CategoryController;
import Domain.ReportController;
import java.time.LocalDateTime;

public class Menu {
    private Scanner scanner;
    private ProductController productController;
    private CategoryController categoryController;
    private ReportController reportController;

    public Menu() {
        scanner = new Scanner(System.in);
        productController = ProductController.getInstance();
        categoryController = CategoryController.getInstance();
        reportController = ReportController.getInstance();
    }

    public void start() {
        while (true) {
            printMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewProducts();
                    break;
                case 3:
                    generateReports();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Generate Reports");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addProduct() {
        try {
            System.out.print("Enter product name: ");
            String name = scanner.nextLine();
            System.out.print("Enter manufacturer: ");
            String manufacturer = scanner.nextLine();
            System.out.print("Enter manufacturer price: ");
            double manPrice = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter customer price: ");
            double cusPrice = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter minimum quantity: ");
            int minQty = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter supply time (days): ");
            int supplyTime = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter category: ");
            String category = scanner.nextLine();
            System.out.print("Enter sub-category: ");
            String subCategory = scanner.nextLine();
            System.out.print("Enter sub-sub-category: ");
            String subSubCategory = scanner.nextLine();

            productController.addProduct(name, manufacturer, manPrice, cusPrice, minQty, supplyTime, category, subCategory, subSubCategory);
            System.out.println("Product added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    private void viewProducts() {
        productController.getProducts().values().forEach(product -> {
            System.out.println(product.toString());
        });
    }

    private void generateReports() {
        System.out.println("Generating reports...");
        // Implement report generation
    }
}
