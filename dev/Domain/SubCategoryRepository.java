package Domain;

import DAL.CategoryDAO;
import DAL.SubCategoryDAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SubCategoryRepository implements IRepository<SubCategory> {
    private Map<String,SubCategory> subCategories;
    private SubCategoryDAO subCategoryDAO;


    public SubCategoryRepository() {
        subCategories = new HashMap<>();
        try {
            subCategoryDAO = new SubCategoryDAO();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize SubCategoryDAO", e);
        }
    }

    @Override
    public void add(SubCategory subCategory) throws Exception {
        if(subCategories.containsKey(subCategory.getSubCategoryName())) {
            throw new Exception("SubCategory " + subCategory.getSubCategoryName() + " already exist.");
        }
        subCategories.put(subCategory.getSubCategoryName(), subCategory);
        subCategoryDAO.add(subCategory);
    }

    @Override
    public void remove(String name) throws Exception {
        subCatIsExist(name);
        subCategories.remove(name);
        subCategoryDAO.remove(name);
    }

    @Override
    public SubCategory get(String name) throws Exception {
        SubCategory subCategory = subCategories.get(name);
        if (subCategory == null) {
            subCategory = subCategoryDAO.get(name);
            if (subCategory != null) {
                subCategories.put(name, subCategory);
            }
        }
        subCatIsExist(name);
        return subCategory;

    }

    public void subCatIsExist (String name) throws Exception{
        if(!subCategories.containsKey(name) && subCategoryDAO.get(name) == null){
            throw new Exception("SubCategory "+ name +" doesn't exist.");
        }
    }

    public boolean containSubCat (String name) {
        if(!subCategories.containsKey(name) && !(subCategoryDAO.containSubCat(name))){
            return false;
        }
        return true;
    }

    public Map<String, SubCategory> getSubCategories() {
        return subCategories;
    }


    public void showSubCategories(Category category) throws SQLException {
        subCategoryDAO.showSubCategories(category);

    }
}
