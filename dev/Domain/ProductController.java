package Domain;

import javax.swing.plaf.PanelUI;
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
    }

    public Product getProduct (int catNum){
        return productsList.get(Integer.toString(catNum));
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
            expiredItemsList = product.findExpiredItems();
        }
        return expiredItemsList;
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
        productsList.put(product.getCatNum(), product);
        productIndex++;
    }

    public void removeProduct(int catNum) throws Exception{
        proIsExist(catNum);
        productsList.remove(Integer.toString(catNum));
    }

    public void proIsExist (int catNum) throws Exception{
        if(!productsList.containsKey(Integer.toString(catNum))){
            throw new Exception("Product with catalog number "+ catNum +" doesn't exist.");
        }
    }

    public void restart() {
        productsList.clear();
    }

}
