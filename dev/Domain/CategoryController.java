package Domain;

import java.util.HashMap;
import java.util.Map;

public class CategoryController {

    private Map<String, Category> categoriesList;

    private static CategoryController category_controller;

    public static CategoryController getInstance() {
        if (category_controller == null)
            category_controller = new CategoryController();
        return category_controller;
    }

    private CategoryController() {
        categoriesList = new HashMap<>();
    }

    public void addCategory(String categoryName) throws Exception {
        if (categoriesList.containsKey(categoryName)){
            throw new Exception("This Category is already exist.");
        }
        else{
            Category newcategory = new Category(categoryName);
            categoriesList.put(categoryName, newcategory);
        }
    }

    public void removeCategory(String categoryName) throws Exception {
        if (!categoriesList.containsKey(categoryName)){
            throw new Exception("This Category isn't exist.");
        }
        else{
            categoriesList.remove(categoryName);
        }
    }

    public Category getCategory (String categoryName) throws Exception{
        if (!categoriesList.containsKey(categoryName)){
            throw new Exception("This Category isn't exist.");
        }
        else{
            return categoriesList.get(categoryName);
        }
    }

    public void addSubCategory(String subCategoryName, String categoryName ) throws Exception {
        if (!categoriesList.containsKey(categoryName)){
            throw new Exception("The Category isn't exist.");
        }
        else{
            categoriesList.get(categoryName).addSubCategory(subCategoryName);
        }
    }

    public void removeSubCategory(String subCategoryName, String categoryName ) throws Exception {
        if (!categoriesList.containsKey(categoryName)){
            throw new Exception("The Category isn't exist.");
        }
        else if (!categoriesList.get(categoryName).getSubList().containsKey(subCategoryName)){
            throw new Exception("This SubCategory isn't exist.");
        }
        else{
            categoriesList.get(categoryName).getSubList().remove(subCategoryName);
        }
    }

    public SubCategory getSubCategory (String subCategoryName, String categoryName) throws Exception{
        if (!categoriesList.containsKey(categoryName)){
            throw new Exception("The Category isn't exist.");
        }
        else if (!categoriesList.get(categoryName).getSubList().containsKey(subCategoryName)){
            throw new Exception("This SubCategory isn't exist.");
        }
        else{
            return categoriesList.get(categoryName).getSubList().get(subCategoryName);
        }
    }


    public void addSubSubCategory(String subSubCategoryName, String subCategoryName, String categoryName ) throws Exception {
        if (!categoriesList.containsKey(categoryName)){
            throw new Exception("The Category isn't exist.");
        }
        else if (!categoriesList.get(categoryName).getSubList().containsKey(subCategoryName)){
            throw new Exception("The SubCategory isn't exist.");
        }
        else{
            Category c = categoriesList.get(categoryName);
            SubCategory s = c.getSubCategory(subCategoryName);
            s.addSubSubCategory(subSubCategoryName);
        }
    }

    public void removeSubSubCategory(String subSubCategoryName, String subCategoryName, String categoryName ) throws Exception {
        if (!categoriesList.containsKey(categoryName)){
            throw new Exception("The Category isn't exist.");
        }
        else if (!categoriesList.get(categoryName).getSubList().containsKey(subCategoryName)){
            throw new Exception("The SubCategory isn't exist.");
        }
        else if (!categoriesList.get(categoryName).getSubList().get(subCategoryName).getSubSubList().containsKey(subSubCategoryName)){
            throw new Exception("This SubSubCategory isn't exist.");
        }
        else{
            Category c = categoriesList.get(categoryName);
            SubCategory s = c.getSubCategory(subCategoryName);
            s.removeSubSubCategory(subSubCategoryName);
        }
    }

    public SubSubCategory subSubCategoryName (String subSubCategoryName, String subCategoryName, String categoryName) throws Exception{
        if (!categoriesList.containsKey(categoryName)){
            throw new Exception("The Category isn't exist.");
        }
        else if (!categoriesList.get(categoryName).getSubList().containsKey(subCategoryName)){
            throw new Exception("The SubCategory isn't exist.");
        }
        else if (!categoriesList.get(categoryName).getSubList().get(subCategoryName).getSubSubList().containsKey(subSubCategoryName)){
            throw new Exception("This SubSubCategory isn't exist.");
        }
        else{
            return categoriesList.get(categoryName).getSubList().get(subCategoryName).getSubSubList().get(subSubCategoryName);
        }
    }

    public void setCategoriesList(Map<String, Category> categoriesList) {
        this.categoriesList = categoriesList;
    }


    public static void setCategory_controller(CategoryController category_controller) {
        CategoryController.category_controller = category_controller;
    }

    public Map<String, Category> getCategoriesList() {
        return categoriesList;
    }
    public void showCategories(){
        for (Category c: categoriesList.values()){
            System.out.println(c.toString());
        }
    }

    public void showSubCategories(String categoryName){
        Map<String,SubCategory> sublist= categoriesList.get(categoryName).getSubList();
        for (SubCategory s: sublist.values()){
            System.out.println(s.toString());
        }
    }

    public static CategoryController getCategory_controller() {
        return category_controller;
    }

    public void restart() {
        categoriesList.clear();
    }

}