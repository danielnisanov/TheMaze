package Domain;

import java.util.Map;

public class CategoryRepository  implements IRepository<Category>  {
    private Map<String,Category> categories;

    //private DAO //TODO

    @Override
    public void add(Category category) throws Exception{
        if(categories.containsKey(category.getCategoryName())) {
            throw new Exception("Category " + category.getCategoryName() + " already exist.");
        }
        categories.put(category.getCategoryName(), category);
    }

    @Override
    public void remove(String name)  throws Exception{

    }

    @Override
    public Category get(String name) throws Exception{}






    public void proIsExist (String name) throws Exception{
        if(!products.containsKey(name)){
            throw new Exception("Product "+ name +" doesn't exist.");
        }
    }


}
