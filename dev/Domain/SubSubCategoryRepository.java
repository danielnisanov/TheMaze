package Domain;
import DAL.SubSubCategoryDAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SubSubCategoryRepository implements IRepository<SubSubCategory>{
    private Map<String,SubSubCategory> subSubCategories;
    private SubSubCategoryDAO subSubCategoryDAO;


    public SubSubCategoryRepository() {
        subSubCategories = new HashMap<>();
        try {
            subSubCategoryDAO = new SubSubCategoryDAO();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize SubSubCategoryDAO", e);
        }
    }

    @Override
    public void add(SubSubCategory subSubCategory) throws Exception {
        if(subSubCategories.containsKey(subSubCategory.getSubSubCategoryName())) {
            throw new Exception("SubSubCategory " + subSubCategory.getSubSubCategoryName() + " already exist.");
        }
        subSubCategories.put(subSubCategory.getSubSubCategoryName(), subSubCategory);
        subSubCategoryDAO.add(subSubCategory);
    }

    @Override
    public void remove(String name) throws Exception {
        subSubCatIsExist(name);
        subSubCategories.remove(name);
        subSubCategoryDAO.remove(name);
    }


    @Override
    public SubSubCategory get(String name) throws Exception {
        SubSubCategory subSubCategory = subSubCategories.get(name);
        if (subSubCategory == null) {
            subSubCategory = subSubCategoryDAO.get(name);
            if (subSubCategory != null) {
                subSubCategories.put(name, subSubCategory);
            }
        }
        subSubCatIsExist(name);
        return subSubCategory;
    }

    public void subSubCatIsExist (String name) throws Exception{
        if(!subSubCategories.containsKey(name) && subSubCategoryDAO.get(name) == null){
            throw new Exception("SubSubCategory "+ name +" doesn't exist.");
        }
    }

    public boolean containSubSubCat (String name) {
        if(!subSubCategories.containsKey(name)  && !(subSubCategoryDAO.containSubSubCat(name))){
            return false;
        }
        return true;
    }
}
