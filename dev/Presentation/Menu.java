package Presentation;

import Domain.Category;
import Domain.CategoryController;
import Domain.ProductController;

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
        loadInitialData();
        while (true) {
            printMainMenu();
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
                    productPresentation.addProduct();
                    break;
                case 8:
                    productPresentation.viewProduct();
                    break;
                case 9:
                    productPresentation.removeProduct();
                    break;
                case 10:
                    productPresentation.showAllItems();
                    break;
                case 11:
                    productPresentation.addItem();
                    break;
                case 12:
                    productPresentation.viewItem();
                    break;
                case 13:
                    productPresentation.removeItem();
                    break;
                case 14:
                    productPresentation.updateProductDiscount();
                    break;
                case 15:
                    productPresentation.updateProductSale();
                    break;
                case 16:
                    productPresentation.moveItemToShelf();
                    break;
                case 17:
                    productPresentation.checkItemLocation();
                    break;
                case 18:
                    productPresentation.updateItemDamaged();
                    break;
                case 19:
                    reportPresentation.createInventoryReport();
                    break;
                case 20:
                    reportPresentation.createDamagedReport();
                    break;
                case 21:
                    reportPresentation.createExpiredReport();
                    break;
                case 22:
                    reportPresentation.displayReport();
                    break;
                case 23:
                    reportPresentation.removeReport();
                    break;
                case 24:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Adding a category");
        System.out.println("2. Adding a sub-category");
        System.out.println("3. Adding a sub-sub-category");
        System.out.println("4. Removing a category");
        System.out.println("5. Removing a sub-category");
        System.out.println("6. Removing a sub-sub-category");
        System.out.println("7. Adding a new product");
        System.out.println("8. Displaying an existing product");
        System.out.println("9. Removing an existing product");
        System.out.println("10. Show all items");
        System.out.println("11. Adding a new item");
        System.out.println("12. Displaying an existing item");
        System.out.println("13. Removing an existing item");
        System.out.println("14. Updating product discount from suppliers");
        System.out.println("15. Updating product sale for customers");
        System.out.println("16. Moving an item from the warehouse to the shelf");
        System.out.println("17. Checking item location");
        System.out.println("18. Updating item damaged");

        System.out.println("19. Creating an inventory report");
        System.out.println("20. Creating a damaged products report");
        System.out.println("21. Creating an expired products report");
        System.out.println("22. Displaying an exist report");
        System.out.println("23. Removing a report");
        System.out.println("24. Exit");
        System.out.print("Select an option: ");
    }

    private void loadInitialData() {
        csvReader.loadCategories("C:\\Users\\Danieln\\Documents\\GitHub\\ADSS_Group_I\\docs\\categories.csv", categoryPresentation);
        csvReader.loadProducts("C:\\Users\\Danieln\\Documents\\GitHub\\ADSS_Group_I\\docs\\products.csv", productPresentation);
        csvReader.loadItems("C:\\Users\\Danieln\\Documents\\GitHub\\ADSS_Group_I\\docs\\items.csv", productPresentation);
    }
}
