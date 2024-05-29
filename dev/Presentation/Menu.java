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

    public void start() {
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
                    updateProductPlacement();
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
                    createDefectiveReport();
                    break;
                case 19:
                    createExpiredReport();
                    break;
                case 20:
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
        System.out.println("8. Presentation of an existing product");
        System.out.println("9. Removing an existing product");
        System.out.println("10. Adding a new item");
        System.out.println("11. Displaying an existing item");
        System.out.println("12. Removing an existing item");
        System.out.println("13. Updating product placement from suppliers");
        System.out.println("14. Product sale update for customers");
        System.out.println("15. Moving an item from the warehouse to the shelf");
        System.out.println("16. Item location check");
        System.out.println("17. Creating an inventory report by categories");
        System.out.println("18. Creating a defective products report");
        System.out.println("19. Creating an expired products report");
        System.out.println("20. Exit");
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
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();
        System.out.print("Enter sub-category name: ");
        String subCategoryName = scanner.nextLine();
        try {
            categoryController.addSubCategory(categoryName, subCategoryName);
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
            categoryController.addSubSubCategory(categoryName, subCategoryName, subSubCategoryName);
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
            categoryController.removeSubCategory(categoryName, subCategoryName);
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
            categoryController.removeSubSubCategory(categoryName, subCategoryName, subSubCategoryName);
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
        System.out.print("Enter product catalog number: ");
        int catNum = Integer.parseInt(scanner.nextLine());
        try {
            Product product = productController.getProduct(catNum);
            System.out.println(product.toString());
        } catch (Exception e) {
            System.out.println("Error viewing product: " + e.getMessage());
        }
    }

    private void removeProduct() {
        System.out.print("Enter product ID: ");
        int productId = Integer.parseInt(scanner.nextLine());
        try {
            productController.removeProduct(productId);
            System.out.println("Product removed successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting product: " + e.getMessage());
        }
    }

    private void addItem() {
        try {
            System.out.print("Enter catalog number of the product: ");
            int catNum = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter if the item is on shelf (true/false): ");
            String onShelf = scanner.nextLine();
            boolean boolOnShelf = Boolean.parseBoolean(onShelf);
            System.out.print("Enter expiration date (yyyy-MM-dd): ");
            String expirationDate = scanner.nextLine();
            LocalDateTime dateExpirationDate = LocalDateTime.parse(expirationDate);
            productController.addItem(catNum, dateExpirationDate, boolOnShelf);
            System.out.println("Item added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding item: " + e.getMessage());
        }
    }

    private void viewItem() {
        System.out.print("Enter item id: ");
        int itemid = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter product catalog number: ");
        int catNum = Integer.parseInt(scanner.nextLine());
        try {
            Item item = productController.getProduct(catNum).getItem(itemid);
            System.out.println(item.toString());
        } catch (Exception e) {
            System.out.println("Error viewing item: " + e.getMessage());
        }
    }

    private void removeItem() {
        System.out.print("Enter item id: ");
        int itemid = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter product catalog number: ");
        int catNum = Integer.parseInt(scanner.nextLine());
        try {
            productController.getProduct(catNum).removeItem(itemid);
        } catch (Exception e) {
            System.out.println("Error removing item: " + e.getMessage());
        }
    }
}
