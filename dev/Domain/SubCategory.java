package Domain;

import java.util.HashMap;
import java.util.Map;

public class SubCategory{
    private String subCategoryName;
    private Map<String, SubSubCategory> subSubList;


    public SubCategory(String subCategoryName) {
        this.subCategoryName = subCategoryName;
        subSubList = new HashMap<>();
    }

    public void addSubSubCategory(String subSubCategoryName) throws Exception {
        if (subSubList.containsKey(subSubCategoryName)){
            throw new Exception("This subSubCategory is already exist.");
        }
        else{
            SubSubCategory newSubSub = new SubSubCategory(subSubCategoryName);
            subSubList.put(subSubCategoryName, newSubSub);
        }
    }

    public void removeSubSubCategory(String subSubCategoryName) throws Exception {
        if (!subSubList.containsKey(subSubCategoryName)){
            throw new Exception("This subSubCategory isn't exist.");
        }
        else{
            subSubList.remove(subSubCategoryName);
        }
    }

    public SubSubCategory getSubSubCategory (String subSubCategoryName) throws Exception{
        if (!subSubList.containsKey(subSubCategoryName)){
            throw new Exception("This subSubCategory isn't exist.");
        }
        else{
            return subSubList.get(subSubCategoryName);
        }
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public Map<String, SubSubCategory> getSubSubList() {
        return subSubList;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public void setSubSubList(Map<String, SubSubCategory> subSubList) {
        this.subSubList = subSubList;
    }

    @Override
    public String toString() {
        return subCategoryName;
    }
}
