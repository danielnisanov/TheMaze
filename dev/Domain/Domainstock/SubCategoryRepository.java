package Domain.Domainstock;

import DAL.DALstock.SubCategoryDAO;

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
        if (subCategories.containsKey(subCategory.getSubCategoryName())) {
            throw new Exception("SubCategory " + subCategory.getSubCategoryName() + " already exists.");
        } else {
            SubCategory subCategoryFromDB = subCategoryDAO.get(subCategory.getSubCategoryName());
            if (subCategoryFromDB != null) {
                subCategories.put(subCategory.getSubCategoryName(), subCategoryFromDB);
            } else {
                subCategoryDAO.add(subCategory);
                subCategories.put(subCategory.getSubCategoryName(), subCategory);
            }
        }
    }

    @Override
    public void remove(String name) throws Exception {
        if (!subCategories.containsKey(name)) {
            SubCategory subCategoryFromDB = subCategoryDAO.get(name);
            if (subCategoryFromDB == null) {
                throw new Exception("SubCategory " + name + " doesn't exist.");
            } else {
                subCategoryDAO.remove(name);
            }
        } else {
            subCategories.remove(name);
            subCategoryDAO.remove(name);
        }
    }

    @Override
    public SubCategory get(String name) throws Exception {
        SubCategory subCategory = subCategories.get(name);
        if (subCategory != null) {
            return subCategory;
        } else {
            subCategory = subCategoryDAO.get(name);
            if (subCategory != null) {
                subCategories.put(name, subCategory);
                return subCategory;
            } else {
                throw new Exception("SubCategory " + name + " doesn't exist.");
            }
        }
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
