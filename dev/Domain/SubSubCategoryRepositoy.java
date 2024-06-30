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
        subSubCatIsExist(name);
        subSubCategories.remove(name);
    }


    @Override
    public SubSubCategory get(String name) throws Exception {
        subSubCatIsExist(name);
        return subSubCategories.get(name);
    }

    public void subSubCatIsExist (String name) throws Exception{
        if(!subSubCategories.containsKey(name)){
            throw new Exception("SubSubCategory "+ name +" doesn't exist.");
        }
    }

    public boolean containSubSubCat (String name) {
        if(!subSubCategories.containsKey(name)){
            return false;
        }
        return true;
    }
}
