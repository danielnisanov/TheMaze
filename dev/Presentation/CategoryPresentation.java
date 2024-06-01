package Presentation;

import Domain.CategoryController;

import java.util.Scanner;

public class CategoryPresentation {
    private static CategoryController categoryController;
    private final Scanner scanner;

    public CategoryPresentation(Scanner scanner) {
        categoryController = CategoryController.getInstance();
        this.scanner = scanner;
    }

    public void addCategory() {
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();
        try {
            categoryController.addCategory(categoryName);
            System.out.println("Category added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding category: " + e.getMessage());
        }
    }

    public void addSubCategory() {
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

    public void addSubSubCategory() {
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

    public void removeCategory() {
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();
        try {
            categoryController.removeCategory(categoryName);
            System.out.println("Category removed successfully.");
        } catch (Exception e) {
            System.out.println("Error removing category: " + e.getMessage());
        }
    }

    public void removeSubCategory() {
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();
        System.out.print("Enter sub-category name: ");
        String subCategoryName = scanner.nextLine();
        try {
            categoryController.removeSubCategory(subCategoryName, categoryName);
            System.out.println("Sub-category removed successfully.");
        } catch (Exception e) {
            System.out.println("Error removing sub-category: " + e.getMessage());
        }
    }

    public void removeSubSubCategory() {
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
}
