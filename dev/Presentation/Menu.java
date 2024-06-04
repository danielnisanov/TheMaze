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
        System.out.println("1. Add category");
        System.out.println("2. Add sub-category");
        System.out.println("3. Add sub-sub-category");
        System.out.println("4. Remove category");
        System.out.println("5. Remove sub-category");
        System.out.println("6. Remove sub-sub-category");
        System.out.println("7. Add new product");
        System.out.println("8. Display an existing product");
        System.out.println("9. Remove an existing product");
        System.out.println("10. Show all items");
        System.out.println("11. Add new item");
        System.out.println("12. Display an existing item");
        System.out.println("13. Remove an existing item");
        System.out.println("14. Update product discount from suppliers");
        System.out.println("15. Update product sale for customers");
        System.out.println("16. Move item from the warehouse to the shelf");
        System.out.println("17. Check item location");
        System.out.println("18. Update item damaged");
        System.out.println("19. Create an inventory report");
        System.out.println("20. Create damaged products report");
        System.out.println("21. Create an expired products report");
        System.out.println("22. Display an exist report");
        System.out.println("23. Remove an exist report");
        System.out.println("24. Exit");
        System.out.print("Select an option: ");
    }

    private void loadInitialData() {
        csvReader.loadCategories("dev/Data/categories.csv", categoryPresentation);
        csvReader.loadProducts("dev/Data/products.csv", productPresentation);
        csvReader.loadItems("dev/Data/items.csv", productPresentation);
    }
}
