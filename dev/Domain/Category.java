package Domain;

import java.util.HashMap;
import java.util.Map;

public class Category {
    private String categoryName;
  //  private Map<String, SubCategory> subList;
    private SubCategoryRepository subCategories;

    public Category(String categoryName) {
        this.categoryName = categoryName;
        subCategories = new SubCategoryRepository();
        //subList = new HashMap<>();
    }

    public void addSubCategory(String subCategoryName) throws Exception {
//        if (subList.containsKey(subCategoryName)){
//            throw new Exception("This subCategory is already exist.");
//        }
//        else{
//            SubCategory newSub = new SubCategory(subCategoryName);
//            subList.put(subCategoryName, newSub);
//        }

        SubCategory newSub = new SubCategory(subCategoryName, categoryName);
        subCategories.add(newSub);
    }

    public void removeSubCategory(String subCategoryName) throws Exception {
//        if (!subList.containsKey(subCategoryName)){
//            throw new Exception("This subCategory isn't exist.");
//        }
//        else{
//            subList.remove(subCategoryName);
//        }
        subCategories.remove(subCategoryName);
    }

    public SubCategory getSubCategory (String subCategoryName) throws Exception{
//        if (!subList.containsKey(subCategoryName)){
//            throw new Exception("This subCategory isn't exist.");
//        }
//        else{
//            return subList.get(subCategoryName);
//
//        }
        return subCategories.get(subCategoryName);
    }


    public String getCategoryName() {
        return categoryName;
    }

    public SubCategoryRepository getSubCategories() {
        return subCategories;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setSubCategories(SubCategoryRepository subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public String toString(){
        return categoryName;
    }

}

