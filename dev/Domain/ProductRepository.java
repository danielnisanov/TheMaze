package Domain;
import DAL.ProductDAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository implements IRepository<Product>{
    private Map<String,Product> products;

    private ProductDAO productDAO;
    public ProductRepository() {
        products = new HashMap<>();
        try {
            productDAO = new ProductDAO();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize ProductDAO", e);
        }
    }

    @Override
    public void add(Product product) throws Exception {
        if (products.containsKey(product.getName())) {
            Product productFromDB = productDAO.get(product.getName()); // Check in database
            if (productFromDB != null) {
                throw new Exception("Product " + product.getName() + " already exists.");
            }
        }
        products.put(product.getName(), product);
        productDAO.add(product);
    }

    @Override
    public void remove(String name) throws Exception {
        proIsExist(name);
        products.remove(name);
        productDAO.remove(name);
    }

    @Override
    public Product get(String name) throws Exception {
        Product product = products.get(name);
        if (product == null) {
            // If not found in memory, take from database
            product = productDAO.get(name);
            if (product != null) {
                products.put(name, product); // save in memory
            }
        }
        proIsExist(name); // Check existence in either memory or database
        return product;
    }

    public void updateDiscount(String name, double discount) throws Exception {
        proIsExist(name);
        Product product = get(name);
        product.setDiscount(discount);
        productDAO.updateDiscount(name, discount); // Update in database
    }


    public void updateSale(String name, double sale) throws Exception {
        proIsExist(name);
        Product product = get(name);
        product.setSale(sale);
        productDAO.updateSale(name, sale); // Update in database
    }


    public void proIsExist(String name) throws Exception {
        if (!products.containsKey(name)) { // Check in memory
            Product productFromDB = productDAO.get(name); // Check in database
            if (productFromDB == null) {
                throw new Exception("Product " + name + " doesn't exist.");
            }
        }
    }
}
