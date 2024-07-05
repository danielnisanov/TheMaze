package Domain;

import java.util.HashMap;
import java.util.Map;

public class Category {
    private String categoryName;
    private SubCategoryRepository subCategories;

    public Category(String categoryName) {
        this.categoryName = categoryName;
        subCategories = new SubCategoryRepository();
    }

    public void addSubCategory(String subCategoryName) throws Exception {
        SubCategory newSub = new SubCategory(subCategoryName, categoryName);
        subCategories.add(newSub);
    }

    public void removeSubCategory(String subCategoryName) throws Exception {
        subCategories.remove(subCategoryName);
    }

    public SubCategory getSubCategory (String subCategoryName) throws Exception{
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

