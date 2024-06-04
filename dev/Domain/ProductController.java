package Domain;

import javax.swing.plaf.PanelUI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {

    private int productIndex;
    private Map<String,Product> productsList;

    private static ProductController product_controller;
    private static CategoryController category_controller;

    public static ProductController getInstance() {
        if (product_controller == null)
            product_controller = new ProductController();
        return product_controller;
    }

    private ProductController() {
        productsList = new HashMap<>();
        productIndex = 0;
        category_controller = CategoryController.getInstance();
    }

    public void addProduct(String name, String area, String manufacturer, int minQuantity, double costPrice, double sellingPrice, double discount, double sale, String cat, String subCat, String subSubCat) throws Exception{
        boolean category = category_controller.getCategoriesList().containsKey(cat);
        boolean subCategory = category_controller.getCategoriesList().get(cat).getSubList().containsKey(subCat);
        boolean subSubCategory = category_controller.getCategoriesList().get(cat).getSubList().get(subCat).getSubSubList().containsKey(subSubCat);

        if (!category || !subCategory || !subSubCategory) throw new Exception("Category or SubCategory or SubSubCategory isn't exists.");
        if(name == null || name.equals("")) throw new Exception("Product name is empty.");
        if(area == null || area.equals("")) throw new Exception("Product area is empty.");
        if(manufacturer == null || manufacturer.equals("")) throw new Exception("Product manufacturer is empty.");
        if(minQuantity < 0 ) throw new Exception("Product minQuantity is negative.");
        if (costPrice < 0 ) throw new Exception("Product costPrice is negative.");
        if (sellingPrice < 0 ) throw new Exception("Product sellingPrice is negative.");
        if (discount <= 0 || discount > 1) throw new Exception("Product discount is illegal.");
        if (sale <= 0 || sale > 1) throw new Exception("Product sale is illegal.");

        Product product = new Product(Integer.toString(productIndex), name, area, manufacturer, minQuantity, costPrice, sellingPrice, discount, sale, cat, subCat, subSubCat);
        productsList.put(product.getName(), product);
        productIndex++;
    }
    public Product getProduct (String name){
        return productsList.get(name);
    }


    public void removeProduct(String name) throws Exception{
        proIsExist(name);
        productsList.remove(name);
    }

    public void addItem(String name, LocalDate expirationDate, boolean onShelf) throws Exception{
        proIsExist(name);
        productsList.get(name).addItem(expirationDate, onShelf);
    }

    public Item getItem(String name, int itemNum) throws Exception{
        proIsExist(name);
        return productsList.get(name).getItem(itemNum);
    }

    public void removeItem(String name, int itemNum) throws Exception{
        proIsExist(name);
        productsList.get(name).removeItem(itemNum);

    }

    public List<Product> getProductsByCategory(String category, String subCategory, String subSubCategory) {
        List<Product> products = new ArrayList<>();
        if (category != null && !category.equals("")) {
            if (subCategory != null && !subCategory.equals("")) {
                if (subSubCategory != null && !subSubCategory.equals("")) {
                    for (Product p : productsList.values())
                        if (p.getCat().equals(category) && p.getSubCat().equals(subCategory) && p.getSubSubCat().equals(subSubCategory))
                            products.add(p);
                } else for (Product p : productsList.values())
                    if (p.getCat().equals(category) && p.getSubCat().equals(subCategory)) products.add(p);
            } else for (Product p : productsList.values())
                if (p.getCat().equals(category)) products.add(p);
        } else products = new ArrayList<>(productsList.values());
        return products;
    }

    public List<Item> getDamagedItems(){
        List<Item> damagedItemsList = new ArrayList<>();
        for (Product product: productsList.values() ){
            for (Item item: product.getItems().values()){
                if (item.isDamaged()){
                    damagedItemsList.add(item);
                }
            }
        }
        return damagedItemsList;
    }

    public List<Item> getExpiredItems(){
        List<Item> expiredItemsList = new ArrayList<>();
        for (Product product: productsList.values() ){
            List<Item> expiredItemsOfP = new ArrayList<>();
            expiredItemsOfP = product.findExpiredItems();
            expiredItemsList.addAll(expiredItemsOfP);
        }

        return expiredItemsList;
    }


    public void proIsExist (String name) throws Exception{
        if(!productsList.containsKey(name)){
            throw new Exception("Product "+ name +" doesn't exist.");
        }
    }

    public void showProducts(){
        for (Product p: productsList.values()){
            System.out.println(p.getName());
        }
    }

    public void showAllItems(){
        for (Product p: productsList.values()){
            Map<String, Item> items =  p.getItems();
            for (Item i: items.values()){
                System.out.println( p.getName() +" "+ i.getItemID() +" "+i.getExpirationDate());
            }
        }
    }

    public void restart() {
        productsList.clear();
    }

}
