package Domain;

import java.util.List;
import java.util.Map;

public class ProductRepository implements IRepository<Product>{
    private Map<String,Product> products;

    //private DAO //TODO


    @Override
    public void add(Product product) throws Exception{
        if(products.containsKey(product.getName())) {
            throw new Exception("Product " + product.getName() + " already exist.");
        }
        products.put(product.getName(), product);
    }

    @Override
    public void remove(String name)  throws Exception{
        proIsExist(name);
        products.remove(name);
    }

    @Override
    public Product get(String name) throws Exception{
        proIsExist(name);
        return products.get(name);
    }

    public void updateDiscount(String name, double discount) throws Exception{
        proIsExist(name);
        Product product = get(name);
        product.setDiscount(discount);
    }

    public void updateSale(String name, double sale) throws Exception{
        proIsExist(name);
        Product product = get(name);
        product.setSale(sale);
    }

    public void proIsExist (String name) throws Exception{
        if(!products.containsKey(name)){
            throw new Exception("Product "+ name +" doesn't exist.");
        }
    }
}
