package Domain;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CategoryController {

  //  private Map<String, Category> categoriesList;
    private CategoryRepository categoryRepo;
    private static CategoryController category_controller;

    public static CategoryController getInstance() {
        if (category_controller == null)
            category_controller = new CategoryController();
        return category_controller;
    }

    public CategoryController() {
        categoryRepo = new CategoryRepository();
    }

    public void addCategory(String name) throws Exception {
        Category newcategory = new Category(name);
        categoryRepo.add(newcategory);
    }

    public void removeCategory(String name) throws Exception {
        categoryRepo.remove(name);
    }

    public Category getCategory (String name) throws Exception{
        return categoryRepo.get(name);
    }

    public void addSubCategory(String subCategoryName, String categoryName ) throws Exception {
        categoryRepo.get(categoryName).addSubCategory(subCategoryName);
    }

    public void removeSubCategory(String subCategoryName, String categoryName ) throws Exception {
        categoryRepo.get(categoryName).removeSubCategory(subCategoryName);
    }

    public SubCategory getSubCategory (String subCategoryName, String categoryName) throws Exception{
        return categoryRepo.get(categoryName).getSubCategory(subCategoryName);
    }


    public void addSubSubCategory(String subSubCategoryName, String subCategoryName, String categoryName ) throws Exception {
        Category c = categoryRepo.get(categoryName);
        SubCategory s = c.getSubCategory(subCategoryName);
        s.addSubSubCategory(subSubCategoryName);
    }

    public void removeSubSubCategory(String subSubCategoryName, String subCategoryName, String categoryName ) throws Exception {
        Category c = categoryRepo.get(categoryName);
        SubCategory s = c.getSubCategory(subCategoryName);
        s.removeSubSubCategory(subSubCategoryName);
    }

    public CategoryRepository getCategoryRepo() {
        return categoryRepo;
    }

    public void setCategoryRepo(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public void showCategories() throws SQLException {
        categoryRepo.showCategories();
    }

    public void showSubCategories(String categoryName)throws Exception{
        Category c = categoryRepo.get(categoryName);
        categoryRepo.showSubCategories(c);
    }
}