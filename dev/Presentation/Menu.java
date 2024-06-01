package Presentation;

import java.util.Scanner;

import Domain.*;

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

    public void start() throws Exception {
        while (true) {
            printMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addCategory();
                    break;
                case 2:
                    addSubCategory();
                    break;
                case 3:
                    addSubSubCategory();
                    break;
                case 4:
                    removeCategory();
                    break;
                case 5:
                    removeSubCategory();
                    break;
                case 6:
                    removeSubSubCategory();
                    break;
                case 7:
                    addProduct();
                    break;
                case 8:
                    viewProduct();
                    break;
                case 9:
                    removeProduct();
                    break;
                case 10:
                    addItem();
                    break;
                case 11:
                    viewItem();
                    break;
                case 12:
                    removeItem();
                    break;
                case 13:
                    updateProductDiscount();
                    break;
                case 14:
                    updateProductSale();
                    break;
                case 15:
                    moveItemToShelf();
                    break;
                case 16:
                    checkItemLocation();
                    break;
                case 17:
                    createInventoryReport();
                    break;
                case 18:
                    createDamagedReport();
                    break;
                case 19:
                    createExpiredReport();
                    break;
                case 20:
                    displayReport();
                    break;
                case 21:
                    removeReport();
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
        System.out.println("8. Displaying of an existing product");
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

    private void addCategory() {
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();
        try {
            categoryController.addCategory(categoryName);
            System.out.println("Category added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding category: " + e.getMessage());
        }
    }
    private void addSubCategory() {
        System.out.print("Enter category name from the list: ");
        categoryController.showCategories();
        String categoryName = scanner.nextLine();
        System.out.print("Enter sub-category name: ");
        String subCategoryName = scanner.nextLine();
        try {
            categoryController.addSubCategory(subCategoryName, categoryName);
            System.out.println("Sub-category added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding sub-category: " + e.getMessage());
        }
    }

    private void addSubSubCategory() {
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();
        System.out.print("Enter sub-category name: ");
        String subCategoryName = scanner.nextLine();
        System.out.print("Enter sub-sub-category name: ");
        String subSubCategoryName = scanner.nextLine();
        try {
            categoryController.addSubSubCategory(subSubCategoryName, subCategoryName, categoryName);
            System.out.println("Sub-sub-category added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding sub-sub-category: " + e.getMessage());
        }
    }

    private void removeCategory() {
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();
        try {
            categoryController.removeCategory(categoryName);
            System.out.println("Category removed successfully.");
        } catch (Exception e) {
            System.out.println("Error removing category: " + e.getMessage());
        }
    }

    private void removeSubCategory() {
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();
        System.out.print("Enter sub-category name: ");
        String subCategoryName = scanner.nextLine();
        try {
            categoryController.removeSubCategory(subCategoryName,categoryName );
            System.out.println("Sub-category removed successfully.");
        } catch (Exception e) {
            System.out.println("Error removing sub-category: " + e.getMessage());
        }
    }

    private void removeSubSubCategory() {
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();
        System.out.print("Enter sub-category name: ");
        String subCategoryName = scanner.nextLine();
        System.out.print("Enter sub-sub-category name: ");
        String subSubCategoryName = scanner.nextLine();
        try {
            categoryController.removeSubSubCategory(subSubCategoryName, subCategoryName, categoryName);
            System.out.println("Sub-sub-category removed successfully.");
        } catch (Exception e) {
            System.out.println("Error removing sub-sub-category: " + e.getMessage());
        }
    }

    private void addProduct() {
        try {
            System.out.print("Enter product name: ");
            String name = scanner.nextLine();
            System.out.print("Enter area: ");
            String area = scanner.nextLine();
            System.out.print("Enter manufacturer: ");
            String manufacturer = scanner.nextLine();
            System.out.print("Enter minimum quantity: ");
            int minQuantity = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter cost price: ");
            double costPrice = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter selling price: ");
            double sellingPrice = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter discount: ");
            double discount = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter sale: ");
            double sale = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter category: ");
            String cat = scanner.nextLine();
            System.out.print("Enter sub-category: ");
            String subCat = scanner.nextLine();
            System.out.print("Enter sub-sub-category: ");
            String subSubCat = scanner.nextLine();

            productController.addProduct(name, area, manufacturer, minQuantity, costPrice, sellingPrice, discount, sale, cat, subCat, subSubCat);
            System.out.println("Product added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    private void viewProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        try {
            Product product = productController.getProduct(name);
            System.out.println(product.toString());
        } catch (Exception e) {
            System.out.println("Error viewing product: " + e.getMessage());
        }
    }

    private void removeProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        try {
            productController.removeProduct(name);
            System.out.println("Product removed successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting product: " + e.getMessage());
        }
    }

    private void addItem() {
        try {
            System.out.print("Enter product name: ");
            String name = scanner.nextLine();
            System.out.print("Enter if the item is on shelf (true/false): ");
            String onShelf = scanner.nextLine();
            boolean boolOnShelf = Boolean.parseBoolean(onShelf);
            System.out.print("Enter expiration date (yyyy-MM-dd): ");
            String expirationDate = scanner.nextLine();
            LocalDateTime dateExpirationDate = LocalDateTime.parse(expirationDate);
            productController.addItem(name, dateExpirationDate, boolOnShelf);
            System.out.println("Item added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding item: " + e.getMessage());
        }
    }

    private void viewItem() {
        System.out.print("Enter item id: ");
        int itemid = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        try {
            Item item = productController.getProduct(name).getItem(itemid);
            System.out.println(item.toString());
        } catch (Exception e) {
            System.out.println("Error viewing item: " + e.getMessage());
        }
    }

    private void removeItem() {
        System.out.print("Enter item id: ");
        int itemid = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        try {
            productController.getProduct(name).removeItem(itemid);
        } catch (Exception e) {
            System.out.println("Error removing item: " + e.getMessage());
        }
    }
    private void updateProductDiscount() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product discount: ");
        double dis = Double.parseDouble(scanner.nextLine());
        productController.getProduct(name).setDiscount(dis);
    }
    private void updateProductSale() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product sale: ");
        double sale = Double.parseDouble(scanner.nextLine());
        productController.getProduct(name).setSale(sale);
    }

    private void moveItemToShelf() {
        System.out.print("Enter item id: ");
        int itemid = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        try {
            productController.getProduct(name).moveItem(itemid);
        } catch (Exception e) {
            System.out.println("Error moving item: " + e.getMessage());
        }
    }

    private void checkItemLocation() {
        System.out.print("Enter item id: ");
        int itemid = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        try {
            boolean onShelf = productController.getProduct(name).isOnShelf(itemid);
            if (onShelf)
                System.out.println("The item is on shelf");
            else
                System.out.println("The item is in ware house");
        } catch (Exception e) {
            System.out.println("Error checking item location: " + e.getMessage());
        }
    }
    private void createInventoryReport() {
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();
        System.out.print("Enter sub category name: ");
        String subCategoryName = scanner.nextLine();
        System.out.print("Enter sub sub category name: ");
        String subSubCategoryName = scanner.nextLine();
        ReportByCategory report = reportController.createInventoryReport(  categoryName,  subCategoryName,  subSubCategoryName);
        System.out.println(report.toString());
    }

    private void createDamagedReport() {
        ReportOfDamaged report = reportController.createDamagedReport();
        System.out.println(report.toString());
    }

    private void createExpiredReport() {
        ReportOfExpired report = reportController.createExpiredReport();
        System.out.println(report.toString());
    }

    private void displayReport() throws Exception {
        System.out.print("Enter report id: ");
        int reportID = Integer.parseInt(scanner.nextLine());
        try {
            System.out.println(reportController.getReport(reportID).toString());
        }
        catch (Exception e) {
            System.out.println("Error displaying report: " + e.getMessage());
        }
    }

    private void removeReport()  throws Exception {
        System.out.print("Enter report id: ");
        int reportID = Integer.parseInt(scanner.nextLine());
        try {
            reportController.removeReport(reportID);
        }
        catch (Exception e) {
            System.out.println("Error displaying report: " + e.getMessage());
        }
    }




}
