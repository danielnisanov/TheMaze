package Domain;

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

//    private CategoryController() {
//        categoriesList = new HashMap<>();
//    }

    public CategoryController() {
        categoryRepo = new CategoryRepository();
       // this.categoryRepo = categoryRepo;
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
//        if (!categoriesList.containsKey(categoryName)){
//            throw new Exception("The Category isn't exist.");
//        }
//        else if (!categoriesList.get(categoryName).getSubList().containsKey(subCategoryName)){
//            throw new Exception("The SubCategory isn't exist.");
//        }
//        else{
//            Category c = categoriesList.get(categoryName);
//            SubCategory s = c.getSubCategory(subCategoryName);
//            s.addSubSubCategory(subSubCategoryName);
//        }
        Category c = categoryRepo.get(categoryName);
        SubCategory s = c.getSubCategory(subCategoryName);
        s.addSubSubCategory(subSubCategoryName);
    }

    public void removeSubSubCategory(String subSubCategoryName, String subCategoryName, String categoryName ) throws Exception {
//        if (!categoriesList.containsKey(categoryName)){
//            throw new Exception("The Category isn't exist.");
//        }
//        else if (!categoriesList.get(categoryName).getSubList().containsKey(subCategoryName)){
//            throw new Exception("The SubCategory isn't exist.");
//        }
//        else if (!categoriesList.get(categoryName).getSubList().get(subCategoryName).getSubSubList().containsKey(subSubCategoryName)){
//            throw new Exception("This SubSubCategory isn't exist.");
//        }
//        else{
//            Category c = categoriesList.get(categoryName);
//            SubCategory s = c.getSubCategory(subCategoryName);
//            s.removeSubSubCategory(subSubCategoryName);
//        }

        Category c = categoryRepo.get(categoryName);
        SubCategory s = c.getSubCategory(subCategoryName);
        s.removeSubSubCategory(subSubCategoryName);
    }

//    public SubSubCategory subSubCategoryName (String subSubCategoryName, String subCategoryName, String categoryName) throws Exception{
//        if (!categoriesList.containsKey(categoryName)){
//            throw new Exception("The Category isn't exist.");
//        }
//        else if (!categoriesList.get(categoryName).getSubList().containsKey(subCategoryName)){
//            throw new Exception("The SubCategory isn't exist.");
//        }
//        else if (!categoriesList.get(categoryName).getSubList().get(subCategoryName).getSubSubList().containsKey(subSubCategoryName)){
//            throw new Exception("This SubSubCategory isn't exist.");
//        }
//        else{
//            return categoriesList.get(categoryName).getSubList().get(subCategoryName).getSubSubList().get(subSubCategoryName);
//        }
//    }


    public CategoryRepository getCategoryRepo() {
        return categoryRepo;
    }

    public void setCategoryRepo(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public void showCategories(){
//        for (Category c: categoriesList.values()){
//            System.out.println(c.toString());
//        }
        categoryRepo.showCategories();
    }

    public void showSubCategories(String categoryName)throws Exception{
//        Map<String,SubCategory> sublist= categoriesList.get(categoryName).getSubList();
//        for (SubCategory s: sublist.values()){
//            System.out.println(s.toString());
//        }
        Category c = categoryRepo.get(categoryName);
        categoryRepo.showSubCategories(c);
    }
}