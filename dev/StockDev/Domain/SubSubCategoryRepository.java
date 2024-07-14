package Domain;
import DAL.SubSubCategoryDAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SubSubCategoryRepository implements IRepository<SubSubCategory> {
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
        subSubCategoryDAO.add(subSubCategory);
        subSubCategories.put(subSubCategory.getSubSubCategoryName(), subSubCategory);
    }

    @Override
    public void remove(String name) throws Exception {
        if (!subSubCategories.containsKey(name)) {
            SubSubCategory subSubCategoryFromDB = subSubCategoryDAO.get(name);
            if (subSubCategoryFromDB == null) {
                throw new Exception("SubSubCategory " + name + " doesn't exist.");
            } else {
                subSubCategoryDAO.remove(name);
            }
        } else {
            subSubCategories.remove(name);
            subSubCategoryDAO.remove(name);
        }
    }

    @Override
    public SubSubCategory get(String name) throws Exception {
        SubSubCategory subSubCategory = subSubCategories.get(name);
        if (subSubCategory != null) {
            return subSubCategory;
        } else {
            subSubCategory = subSubCategoryDAO.get(name);
            if (subSubCategory != null) {
                subSubCategories.put(name, subSubCategory);
                return subSubCategory;
            } else {
                throw new Exception("SubSubCategory " + name + " doesn't exist.");
            }
        }
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
