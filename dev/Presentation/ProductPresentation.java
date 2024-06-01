package Presentation;

import Domain.Item;
import Domain.Product;
import Domain.ProductController;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ProductPresentation {
    private static ProductController productController;
    private final Scanner scanner;

    public ProductPresentation(Scanner scanner) {
        productController = ProductController.getInstance();
        this.scanner = scanner;
    }

    public void addProduct() {
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

    public void viewProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        try {
            Product product = productController.getProduct(name);
            System.out.println(product.toString());
        } catch (Exception e) {
            System.out.println("Error viewing product: " + e.getMessage());
        }
    }

    public void removeProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        try {
            productController.removeProduct(name);
            System.out.println("Product removed successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting product: " + e.getMessage());
        }
    }

    public void addItem() {
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

    public void viewItem() {
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

    public void removeItem() {
        System.out.print("Enter item id: ");
        int itemid = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        try {
            productController.getProduct(name).removeItem(itemid);
            System.out.println("Item removed successfully.");
        } catch (Exception e) {
            System.out.println("Error removing item: " + e.getMessage());
        }
    }

    public void updateProductDiscount() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product discount: ");
        double discount = Double.parseDouble(scanner.nextLine());
        try {
            productController.getProduct(name).setDiscount(discount);
            System.out.println("Product discount updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating product discount: " + e.getMessage());
        }
    }

    public void updateProductSale() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product sale: ");
        double sale = Double.parseDouble(scanner.nextLine());
        try {
            productController.getProduct(name).setSale(sale);
            System.out.println("Product sale updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating product sale: " + e.getMessage());
        }
    }

    public void moveItemToShelf() {
        System.out.print("Enter item id: ");
        int itemid = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        try {
            productController.getProduct(name).moveItem(itemid);
            System.out.println("Item moved to shelf successfully.");
        } catch (Exception e) {
            System.out.println("Error moving item: " + e.getMessage());
        }
    }

    public void checkItemLocation() {
        System.out.print("Enter item id: ");
        int itemid = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        try {
            boolean onShelf = productController.getProduct(name).isOnShelf(itemid);
            if (onShelf) {
                System.out.println("The item is on shelf.");
            } else {
                System.out.println("The item is in warehouse.");
            }
        } catch (Exception e) {
            System.out.println("Error checking item location: " + e.getMessage());
        }
    }

    public ProductController getProductController() {
        return productController;
    }
}
