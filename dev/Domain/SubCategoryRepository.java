package Domain;

import java.util.HashMap;
import java.util.Map;

public class SubCategoryRepository implements IRepository<SubCategory> {
    private Map<String,SubCategory> subCategories;

    public SubCategoryRepository() {
        subCategories = new HashMap<>();
    }

    @Override
    public void add(SubCategory subCategory) throws Exception {
        if(subCategories.containsKey(subCategory.getSubCategoryName())) {
            throw new Exception("Category " + subCategory.getSubCategoryName() + " already exist.");
        }
        subCategories.put(subCategory.getSubCategoryName(), subCategory);
    }

    @Override
    public void remove(String name) throws Exception {
        subCatIsExist(name);
        subCategories.remove(name);
    }

    @Override
    public SubCategory get(String name) throws Exception {
        subCatIsExist(name);
        return subCategories.get(name);
    }

    public void subCatIsExist (String name) throws Exception{
        if(!subCategories.containsKey(name)){
            throw new Exception("SubCategory "+ name +" doesn't exist.");
        }
    }

    public boolean containSubCat (String name) {
        if(!subCategories.containsKey(name)){
            return false;
        }
        return true;
    }

    public Map<String, SubCategory> getSubCategories() {
        return subCategories;
    }
}
