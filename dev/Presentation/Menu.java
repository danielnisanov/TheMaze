package Presentation;

import Domain.Category;
import Domain.CategoryController;
import Domain.ProductController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private ProductPresentation productPresentation;
    private CategoryPresentation categoryPresentation;
    private ReportPresentation reportPresentation;

    private CSVReader csvReader;


    public Menu() {
        scanner = new Scanner(System.in);
        productPresentation = new ProductPresentation(scanner);
        categoryPresentation = new CategoryPresentation(scanner);
        reportPresentation = new ReportPresentation(scanner);
        csvReader = new CSVReader();
    }

    public void start() throws Exception {

        System.out.print("Do you want to load initial data? (Y/N): ");
        String loadChoice = scanner.nextLine().trim().toUpperCase();
        if (loadChoice.equals("Y")) {
            loadInitialData();
        }

        checkAndGenerateReport();
        MainMenu();
    }


    private void printMainMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Categories Menu");
        System.out.println("2. Products and Items Menu");
        System.out.println("3. Reports Menu");
        System.out.println("4. Exit");
        System.out.print("Select an option: ");
    }

    private void MainMenu() throws Exception {
        while (true) {
            printMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    CategoryMenu();
                    break;
                case 2:
                    ProductsItemsMenu();
                    break;
                case 3:
                    ReportsMenu();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }

    private void printCategoryMenu() {
        System.out.println("\n Category Menu:");
        System.out.println("1. Add category");
        System.out.println("2. Add sub-category");
        System.out.println("3. Add sub-sub-category");
        System.out.println("4. Remove category");
        System.out.println("5. Remove sub-category");
        System.out.println("6. Remove sub-sub-category");
        System.out.println("7. Return to Main Menu");
        System.out.print("Select an option: ");
    }


    private void printProductsItemsMenu() {
        System.out.println("\n Products and Items Menu:");
        System.out.println("1. Add new product");
        System.out.println("2. Display an existing product");
        System.out.println("3. Remove an existing product");
        System.out.println("4. Show all items");
        System.out.println("5. Add new item");
        System.out.println("6. Display an existing item");
        System.out.println("7. Remove an existing item");
        System.out.println("8. Update product discount from suppliers");
        System.out.println("9. Update product sale for customers");
        System.out.println("10. Move item from the warehouse to the shelf");
        System.out.println("11. Check item location");
        System.out.println("12. Update item damaged");
        System.out.println("13. Return to Main Menu");
        System.out.print("Select an option: ");
    }
    private void printReportsMenu() {
        System.out.println("\n Reports Menu:");
        System.out.println("1. Create an inventory report");
        System.out.println("2. Create damaged products report");
        System.out.println("3. Create an expired products report");
        System.out.println("4. Display an exist report");
        System.out.println("5. Remove an exist report");
        System.out.println("6. Return to Main Menu");
        System.out.print("Select an option: ");
    }



    private void CategoryMenu() throws Exception {
        printCategoryMenu();
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                categoryPresentation.addCategory();
                break;
            case 2:
                categoryPresentation.addSubCategory();
                break;
            case 3:
                categoryPresentation.addSubSubCategory();
                break;
            case 4:
                categoryPresentation.removeCategory();
                break;
            case 5:
                categoryPresentation.removeSubCategory();
                break;
            case 6:
                categoryPresentation.removeSubSubCategory();
                break;
            case 7:
                MainMenu();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void ProductsItemsMenu() throws Exception{
        printProductsItemsMenu();
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                productPresentation.addProduct();
                break;
            case 2:
                productPresentation.viewProduct();
                break;
            case 3:
                productPresentation.removeProduct();
                break;
            case 4:
                productPresentation.showAllItems();
                break;
            case 5:
                productPresentation.addItem();
                break;
            case 6:
                productPresentation.viewItem();
                break;
            case 7:
                productPresentation.removeItem();
                break;
            case 8:
                productPresentation.updateProductDiscount();
                break;
            case 9:
                productPresentation.updateProductSale();
                break;
            case 10:
                productPresentation.moveItemToShelf();
                break;
            case 11:
                productPresentation.checkItemLocation();
                break;
            case 12:
                productPresentation.updateItemDamaged();
                break;
            case  13:
                MainMenu();
                break;
            default:
                System.out.println("Invalid option. Please try again.");

        }
    }

    private void ReportsMenu() throws Exception {
        printReportsMenu();
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                reportPresentation.createInventoryReport();
                break;
            case 2:
                reportPresentation.createDamagedReport();
                break;
            case 3:
                reportPresentation.createExpiredReport();
                break;
            case 4:
                reportPresentation.displayReport();
                break;
            case 5:
                reportPresentation.removeReport();
                break;
            case  6:
                MainMenu();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }



        private void loadInitialData() {
        csvReader.loadCategories("dev/Data/categories.csv", categoryPresentation);
        csvReader.loadProducts("dev/Data/products.csv", productPresentation);
        csvReader.loadItems("dev/Data/items.csv", productPresentation);
    }

    public void checkAndGenerateReport() {
        LocalDate today = LocalDate.now();
        if (today.getDayOfWeek() == DayOfWeek.SUNDAY) {
            System.out.println("Today is Sunday. Generating inventory report...");
            reportPresentation.createInventoryReport();
        } else {
            System.out.println("Today is not Sunday. No inventory report is generated.");
        }
    }
}
