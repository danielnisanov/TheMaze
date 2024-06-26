package Domain;

import java.util.HashMap;
import java.util.Map;

public class SubCategory{
    private String subCategoryName;
  //  private Map<String, SubSubCategory> subSubList;
    private SubSubCategoryRepositoy subSubCategories;



    public SubCategory(String subCategoryName) {
        this.subCategoryName = subCategoryName;
        subSubCategories = new SubSubCategoryRepositoy();
    }

    public void addSubSubCategory(String subSubCategoryName) throws Exception {
//        if (subSubList.containsKey(subSubCategoryName)){
//            throw new Exception("This subSubCategory is already exist.");
//        }
//        else{
//            SubSubCategory newSubSub = new SubSubCategory(subSubCategoryName);
//            subSubList.put(subSubCategoryName, newSubSub);
//        }

        SubSubCategory newSubSub = new SubSubCategory(subSubCategoryName);
        subSubCategories.add(newSubSub);

    }

    public void removeSubSubCategory(String subSubCategoryName) throws Exception {
//        if (!subSubList.containsKey(subSubCategoryName)){
//            throw new Exception("This subSubCategory isn't exist.");
//        }
//        else{
//            subSubList.remove(subSubCategoryName);
//        }

        subSubCategories.remove(subSubCategoryName);

    }

    public SubSubCategory getSubSubCategory (String subSubCategoryName) throws Exception{
//        if (!subSubList.containsKey(subSubCategoryName)){
//            throw new Exception("This subSubCategory isn't exist.");
//        }
//        else{
//            return subSubList.get(subSubCategoryName);
//        }

        return subSubCategories.get(subSubCategoryName);

    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public SubSubCategoryRepositoy getSubSubCategories() {
        return subSubCategories;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public void setSubSubCategories(SubSubCategoryRepositoy subSubCategories) {
        this.subSubCategories = subSubCategories;
    }

    @Override
    public String toString() {
        return subCategoryName;
    }
}
