package Domain;

public class SubSubCategory {

    private String subSubCategoryName;
    private String subCategoryName;
    private String CategoryName;

    public SubSubCategory(String subSubCategoryName, String subCategoryName, String CategoryName) {
        this.subSubCategoryName = subSubCategoryName;
        this.subCategoryName = subCategoryName;
        this.CategoryName = CategoryName;
    }

    public String getSubSubCategoryName() {
        return subSubCategoryName;
    }
}
