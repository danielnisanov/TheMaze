package Domain;

import java.util.HashMap;
import java.util.Map;

public class Category {
    private String categoryName;
    private Map<String, SubCategory> subList;

    public Category(String categoryName) {
        this.categoryName = categoryName;
        subList = new HashMap<>();
    }

    public void addSubCategory(String subCategoryName) throws Exception {
        if (subList.containsKey(subCategoryName)){
            throw new Exception("This subCategory is already exist.");
        }
        else{
            SubCategory newsub = new SubCategory(subCategoryName);
            subList.put(subCategoryName, newsub);
        }
    }

    public void removeSubCategory(String subCategoryName) throws Exception {
        if (!subList.containsKey(subCategoryName)){
            throw new Exception("This subCategory isn't exist.");
        }
        else{
            subList.remove(subCategoryName);
        }
    }

    public SubCategory getSubCategory (String subCategoryName) throws Exception{
        if (!subList.containsKey(subCategoryName)){
            throw new Exception("This subCategory isn't exist.");
        }
        else{
            return subList.get(subCategoryName);

        }
    }


        public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setSubList(Map<String, SubCategory> subList) {
        this.subList = subList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Map<String, SubCategory> getSubList() {
        return subList;
    }
}

