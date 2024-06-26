package Domain;

import java.util.HashMap;
import java.util.Map;

public class CategoryRepository  implements IRepository<Category>  {
    private Map<String,Category> categories;

    //private DAO //TODO


    public CategoryRepository() {
        categories =new HashMap<>();
    }

    @Override
    public void add(Category category) throws Exception{
        if(categories.containsKey(category.getCategoryName())) {
            throw new Exception("Category " + category.getCategoryName() + " already exist.");
        }
        categories.put(category.getCategoryName(), category);
    }

    @Override
    public void remove(String name)  throws Exception{
        catIsExist(name);
        categories.remove(name);
    }

    @Override
    public Category get(String name) throws Exception{
        catIsExist(name);
        return categories.get(name);
    }

    //TODO
    public void showCategories(){
        //printing of all the items from DB
    }

    //TODO
    public void showSubCategories(Category category){
        //printing of all the items from DB
    }

    public void catIsExist (String name) throws Exception{
        if(!categories.containsKey(name)){
            throw new Exception("Category "+ name +" doesn't exist.");
        }
    }


}
