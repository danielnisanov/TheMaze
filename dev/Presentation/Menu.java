package Presentation;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private ProductPresentation productPresentation;
    private CategoryPresentation categoryPresentation;
    private ReportPresentation reportPresentation;

    public Menu() {
        scanner = new Scanner(System.in);
        productPresentation = new ProductPresentation(scanner);
        categoryPresentation = new CategoryPresentation(scanner);
        reportPresentation = new ReportPresentation(scanner);
    }

    public void start() throws Exception {
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
                    productPresentation.addItem();
                    break;
                case 11:
                    productPresentation.viewItem();
                    break;
                case 12:
                    productPresentation.removeItem();
                    break;
                case 13:
                    productPresentation.updateProductDiscount();
                    break;
                case 14:
                    productPresentation.updateProductSale();
                    break;
                case 15:
                    productPresentation.moveItemToShelf();
                    break;
                case 16:
                    productPresentation.checkItemLocation();
                    break;
                case 17:
                    reportPresentation.createInventoryReport();
                    break;
                case 18:
                    reportPresentation.createDamagedReport();
                    break;
                case 19:
                    reportPresentation.createExpiredReport();
                    break;
                case 20:
                    reportPresentation.displayReport();
                    break;
                case 21:
                    reportPresentation.removeReport();
                    break;
                case 22:
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
        System.out.println("10. Adding a new item");
        System.out.println("11. Displaying an existing item");
        System.out.println("12. Removing an existing item");
        System.out.println("13. Updating product discount from suppliers");
        System.out.println("14. Updating product sale for customers");
        System.out.println("15. Moving an item from the warehouse to the shelf");
        System.out.println("16. Checking item location");
        System.out.println("17. Creating an inventory report");
        System.out.println("18. Displaying an exist inventory report");
        System.out.println("19. Removing an inventory report");
        System.out.println("20. Creating a damaged products report");
        System.out.println("21. Displaying an exist damaged products report");
        System.out.println("22. Removing a damaged products report");
        System.out.println("23. Creating an expired products report");
        System.out.println("24. Displaying an exist expired products report");
        System.out.println("25. Removing an expired products report");
        System.out.println("26. Exit");
        System.out.print("Select an option: ");
    }
}
