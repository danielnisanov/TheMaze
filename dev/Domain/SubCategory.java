package Domain;
import java.util.HashMap;
import java.util.Map;

public class SubCategory{
    private String subCategoryName;
    private String categoryName;
    private SubSubCategoryRepository subSubCategories;



    public SubCategory(String subCategoryName, String CategoryName) {
        this.subCategoryName = subCategoryName;
        this.categoryName = CategoryName;
        subSubCategories = new SubSubCategoryRepository();
    }

    public void addSubSubCategory(String subSubCategoryName) throws Exception {
        SubSubCategory newSubSub = new SubSubCategory(subSubCategoryName, subCategoryName, categoryName );
        subSubCategories.add(newSubSub);
    }

    public void removeSubSubCategory(String subSubCategoryName) throws Exception {
        subSubCategories.remove(subSubCategoryName);
    }

    public SubSubCategory getSubSubCategory (String subSubCategoryName) throws Exception{
        return subSubCategories.get(subSubCategoryName);
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public SubSubCategoryRepository getSubSubCategories() {
        return subSubCategories;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public void setSubSubCategories(SubSubCategoryRepository subSubCategories) {
        this.subSubCategories = subSubCategories;
    }

    public void setCategoryName(String categoryName) {
        categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return subCategoryName;
    }
}
