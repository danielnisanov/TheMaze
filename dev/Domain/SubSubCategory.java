package Domain;

public class SubSubCategory {

    private String subSubCategoryName;
    private String subCategoryName;
    private String categoryName;

    public SubSubCategory(String subSubCategoryName, String subCategoryName, String CategoryName) {
        this.subSubCategoryName = subSubCategoryName;
        this.subCategoryName = subCategoryName;
        this.categoryName = CategoryName;
    }

    public void setSubSubCategoryName(String subSubCategoryName) {
        this.subSubCategoryName = subSubCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getSubSubCategoryName() {
        return subSubCategoryName;
    }
}
