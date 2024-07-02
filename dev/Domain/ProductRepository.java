package Domain;
import DAL.ProductDAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
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
            throw new Exception("Product " + product.getName() + " already exists.");
        }
        else {
            Product productFromDB = productDAO.get(product.getName());
            if (productFromDB != null) {
                products.put(product.getName(), product);
            }
            else{
                productDAO.add(product);
                products.put(product.getName(), product);
            }
        }
    }

    @Override
    public void remove(String name) throws Exception {
        if (!products.containsKey(name)) { //not in repo
            Product productFromDB = productDAO.get(name);
            if (productFromDB == null) { //not in db
                throw new Exception("Product " + name + " doesn't exist.");
            }
            else{ //not in repo but in db
                productDAO.remove(name);
            }
        }
        else{
            products.remove(name);
            productDAO.remove(name);
        }
    }

    @Override
    public Product get(String name) throws Exception {
        Product product = products.get(name);
        if (product != null) {
            return product;
        } else {
            // If not found in repo, take from database
            product = productDAO.get(name);
            if (product != null) {
                products.put(name, product); // save in repo
                return product;
            } else {
                throw new Exception("Product " + name + " doesn't exist.");
            }
        }
    }


    public void updateDiscount(String name, double discount) throws Exception {
        Product product = products.get(name);
        if (product != null) {
            product.setDiscount(discount);
            productDAO.update(product);
        }
        else{
            product = productDAO.get(name);
            if (product != null) {
                product.setDiscount(discount);
                productDAO.update(product);
                products.put(name, product);
            }
            else{
                throw new Exception("Product " + name + " doesn't exist.");
            }
        }
    }


    public void updateSale(String name, double sale) throws Exception {
        Product product = products.get(name);
        if (product != null) {
            product.setSale(sale);
            productDAO.update(product);
        }
        else{
            product = productDAO.get(name);
            if (product != null) {
                product.setSale(sale);
                productDAO.update(product);
                products.put(name, product);
            }
            else{
                throw new Exception("Product " + name + " doesn't exist.");
            }
        }
    }

    public void proIsExist(String name) throws Exception {
        if (!products.containsKey(name)) { // Check in memory
            Product productFromDB = productDAO.get(name); // Check in database
            if (productFromDB == null) {
                throw new Exception("Product " + name + " doesn't exist.");
            }
        }
    }
    public List<Product> getProductsByCategory(String category, String subCategory, String subSubCategory){
        try {
            return productDAO.getProductsByCategory(category, subCategory, subSubCategory);
        }
        catch (Exception e) {
            System.out.println("Error get Products By Category: " + e.getMessage());
        }
        return null;
    }




    public List<Product> getMissingProductsByCategory(String category, String subCategory, String subSubCategory) {
        try {
            return productDAO.getMissingProductsByCategory(category, subCategory, subSubCategory);
        }
        catch (Exception e) {
            System.out.println("Error get missing Products By Category: " + e.getMessage());
        }
        return null;
    }

    public Map<Item, String> getDamagedItems() {
//
//        Map<Item, String> damagedItemsList = new HashMap<>();
//        for (Product product: productsList.values() ){
//            for (Item item: product.getItems().values()){
//                if (item.isDamaged()){
//                    damagedItemsList.put(item, product.getName());
//                }
//            }
//        }
//        return damagedItemsList;
//    }
        try {
            return productDAO.getDamagedItems();
        } catch (Exception e) {
            System.out.println("Error get damaged items: " + e.getMessage());
        }
        return null;
    }

    public Map<Item, String> getExpiredItems() {
        try {
            return productDAO.getExpiredItems();
        } catch (Exception e) {
            System.out.println("Error get expired items: " + e.getMessage());
        }
        return null;
    }

    public void showProducts() {
        try {
             productDAO.showProducts();
        } catch (Exception e) {
            System.out.println("Error show Products: " + e.getMessage());
        }
    }

    public void showAllItems() {
        try {
            productDAO.showAllItems();
        } catch (Exception e) {
            System.out.println("Error show Items: " + e.getMessage());
        }
    }
}
