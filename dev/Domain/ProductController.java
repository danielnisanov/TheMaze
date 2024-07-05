package Domain;

import javax.swing.plaf.PanelUI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {

    private ProductRepository productRepo;

    private static ProductController product_controller;
    private static CategoryController category_controller;

    public static ProductController getInstance() {
        if (product_controller == null)
            product_controller = new ProductController();
        return product_controller;
    }

    private ProductController() {
        category_controller = CategoryController.getInstance();
         productRepo = new ProductRepository();
    }

    public void addProduct(String catNum, String name, String area, String manufacturer, int minQuantity, double costPrice, double sellingPrice, double discount, double sale, String cat, String subCat, String subSubCat) throws Exception{
        boolean category = category_controller.getCategoryRepo().containCat(cat);
        boolean subCategory = category_controller.getCategoryRepo().get(cat).getSubCategories().containSubCat(subCat);
        boolean subSubCategory = category_controller.getCategoryRepo().get(cat).getSubCategories().get(subCat).getSubSubCategories().containSubSubCat(subSubCat);

        if (!category || !subCategory || !subSubCategory) throw new Exception("Category or SubCategory or SubSubCategory isn't exists.");
        if(catNum == null || catNum.equals("")) throw new Exception("Product catNum is empty.");
        if(name == null || name.equals("")) throw new Exception("Product name is empty.");
        if(area == null || area.equals("")) throw new Exception("Product area is empty.");
        if(manufacturer == null || manufacturer.equals("")) throw new Exception("Product manufacturer is empty.");
        if(minQuantity < 0 ) throw new Exception("Product minQuantity is negative.");
        if (costPrice < 0 ) throw new Exception("Product costPrice is negative.");
        if (sellingPrice < 0 ) throw new Exception("Product sellingPrice is negative.");
        if (discount < 0 || discount > 1) throw new Exception("Product discount is illegal.");
        if (sale < 0 || sale > 1) throw new Exception("Product sale is illegal.");

        Product product = new Product(catNum, name, area, manufacturer, minQuantity, costPrice, sellingPrice, discount, sale, cat, subCat, subSubCat);
        productRepo.add(product);
    }

    public void removeProduct(String name) throws Exception{
        productRepo.remove(name);
    }

    public Product getProduct (String name) throws Exception {
        return productRepo.get(name);
    }

    public void addItem(String name, LocalDate expirationDate, boolean onShelf) throws Exception{
        productRepo.proIsExist(name);
        Product product = productRepo.get(name);
        product.addItem(productRepo.get(name).getName(), expirationDate, onShelf);
        productRepo.update(product);
    }

    public void removeItem(String name, int itemNum) throws Exception{
        productRepo.proIsExist(name);
        productRepo.get(name).removeItem(itemNum);

    }

    public Item getItem(String name, int itemNum) throws Exception{
        productRepo.proIsExist(name);
        return productRepo.get(name).getItem(itemNum);
    }


    public List<Product> getProductsByCategory(String category, String subCategory, String subSubCategory)  {
        return productRepo.getProductsByCategory( category, subCategory, subSubCategory);
    }

    public List<Product> getMissingProductsByCategory(String category, String subCategory, String subSubCategory) {
        return productRepo.getMissingProductsByCategory( category, subCategory, subSubCategory);
    }


    public Map<Item, String> getDamagedItems(){
        return productRepo.getDamagedItems();

    }

    public Map<Item, String> getExpiredItems(){
        return productRepo.getExpiredItems();
    }

    public void showProducts(){
        productRepo.showProducts();
    }

    public void showAllItems(){
        productRepo.showAllItems();

    }

    public String viewProduct(String name) throws Exception{
        Product product = productRepo.get(name);
        return product.toString();
    }

    public String viewItem(String name, int itemID) throws Exception {
        Product product = productRepo.get(name);
        Item item =  product.getItemRepo().get(Integer.toString(itemID));
        return item.toString();
    }

    public void updateProductDiscount(String name, double discount) throws Exception{
        productRepo.updateDiscount(name, discount);
    }

    public void updateProductSale(String name, double sale) throws Exception{
        productRepo.updateSale(name, sale);
    }

    public void moveItemToShelf(String name, int itemID) throws Exception{
        Product product = productRepo.get(name);
        product.getItemRepo().moveItemToShelf(Integer.toString(itemID));
    }


    public boolean checkItemLocation(String name, int itemID) throws Exception{
        Product product = productRepo.get(name);
        return product.getItemRepo().checkItemLocation(Integer.toString(itemID));
    }

    public void updateItemDamaged(String name, int itemID) throws Exception{
        Product product = productRepo.get(name);
        product.getItemRepo().updateItemDamaged(Integer.toString(itemID));
    }


}
