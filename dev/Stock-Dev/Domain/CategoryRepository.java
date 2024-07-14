package Domain;

import DAL.CategoryDAO;
import DAL.ItemDAO;
import DAL.ProductDAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CategoryRepository  implements IRepository<Category>  {
    private Map<String,Category> categories;

    private CategoryDAO categoryDAO;


    public CategoryRepository() {
        categories =new HashMap<>();
        try {
            categoryDAO = new CategoryDAO();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize CategoryDAO", e);
        }
    }

    @Override
    public void add(Category category) throws Exception {
        if (categories.containsKey(category.getCategoryName())) {
            throw new Exception("Category " + category.getCategoryName() + " already exists.");
        } else {
            Category categoryFromDB = categoryDAO.get(category.getCategoryName());
            if (categoryFromDB != null) {
                categories.put(category.getCategoryName(), categoryFromDB);
            } else {
                categoryDAO.add(category);
                categories.put(category.getCategoryName(), category);
            }
        }
    }

    @Override
    public void remove(String name) throws Exception {
        if (!categories.containsKey(name)) {
            Category categoryFromDB = categoryDAO.get(name);
            if (categoryFromDB == null) {
                throw new Exception("Category " + name + " doesn't exist.");
            } else {
                categoryDAO.remove(name);
            }
        } else {
            categories.remove(name);
            categoryDAO.remove(name);
        }
    }

    @Override
    public Category get(String name) throws Exception {
        Category category = categories.get(name);
        if (category != null) {
            return category;
        } else {
            category = categoryDAO.get(name);
            if (category != null) {
                categories.put(name, category);
                return category;
            } else {
                throw new Exception("Category " + name + " doesn't exist.");
            }
        }
    }
    public void showCategories() throws SQLException{
        categoryDAO.showCategories();
    }

    public void showSubCategories(Category category) throws SQLException{
        SubCategoryRepository subCategoryRepo = category.getSubCategories();
        subCategoryRepo.showSubCategories(category);
    }

    public void catIsExist (String name) throws Exception{
        if(!categories.containsKey(name) && categoryDAO.get(name) == null){
            throw new Exception("Category "+ name +" doesn't exist.");
        }
    }
    public boolean containCat (String name) {
        if(!categories.containsKey(name) && !(categoryDAO.containCat(name))){
            return false;
        }
        return true;
    }
    public void close() throws SQLException {
        categoryDAO.close();
    }


}
