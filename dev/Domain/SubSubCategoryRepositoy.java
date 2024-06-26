package Domain;

import java.util.HashMap;
import java.util.Map;

public class SubSubCategoryRepositoy implements IRepository<SubSubCategory>{
    private Map<String,SubSubCategory> subSubCategories;
    //private DAO //TODO


    public SubSubCategoryRepositoy() {
        subSubCategories = new HashMap<>();
    }

    @Override
    public void add(SubSubCategory subSubCategory) throws Exception {
        if(subSubCategories.containsKey(subSubCategory.getSubSubCategoryName())) {
            throw new Exception("Category " + subSubCategory.getSubSubCategoryName() + " already exist.");
        }
        subSubCategories.put(subSubCategory.getSubSubCategoryName(), subSubCategory);
    }

    @Override
    public void remove(String name) throws Exception {
        subCatIsExist(name);
        subSubCategories.remove(name);
    }


    @Override
    public SubSubCategory get(String name) throws Exception {
        subCatIsExist(name);
        return subSubCategories.get(name);
    }

    public void subCatIsExist (String name) throws Exception{
        if(!subSubCategories.containsKey(name)){
            throw new Exception("SubCategory "+ name +" doesn't exist.");
        }
    }
}
