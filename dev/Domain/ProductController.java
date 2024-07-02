package Domain;

import javax.swing.plaf.PanelUI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {

  //  private Map<String,Product> productsList; //TODO DELETE
    private ProductRepository productRepo;

    private static ProductController product_controller;
    private static CategoryController category_controller;

    public static ProductController getInstance() {
        if (product_controller == null)
            product_controller = new ProductController();
        return product_controller;
    }

    private ProductController() {
     //   productsList = new HashMap<>();
        category_controller = CategoryController.getInstance();
         productRepo = new ProductRepository();
    }

    public void addProduct(String catNum, String name, String area, String manufacturer, int minQuantity, double costPrice, double sellingPrice, double discount, double sale, String cat, String subCat, String subSubCat) throws Exception{
        boolean category = category_controller.getCategoryRepo().containCat(cat);
        boolean subCategory = category_controller.getCategoryRepo().get(cat).getSubCategories().containSubCat(subCat);
        boolean subSubCategory = category_controller.getCategoryRepo().get(cat).getSubCategories().get(subCat).getSubSubCategories().containSubSubCat(subSubCat);

        //boolean category = category_controller.getCategoriesList().containsKey(cat);
        //boolean subCategory = category_controller.getCategoriesList().get(cat).getSubList().containsKey(subCat);
        //boolean subSubCategory = category_controller.getCategoriesList().get(cat).getSubList().get(subCat).getSubSubList().containsKey(subSubCat);

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
        productRepo.get(name).addItem(productRepo.get(name).getName(), expirationDate, onShelf);
    }

    public void removeItem(String name, int itemNum) throws Exception{
        productRepo.proIsExist(name);
        productRepo.get(name).removeItem(itemNum);

    }

    public Item getItem(String name, int itemNum) throws Exception{
        productRepo.proIsExist(name);
        return productRepo.get(name).getItem(itemNum);
    }


    //FIXME DAO
    public List<Product> getProductsByCategory(String category, String subCategory, String subSubCategory)  {
//        List<Product> products = new ArrayList<>();
//        if (category != null && !category.equals("")) {
//            if (subCategory != null && !subCategory.equals("")) {
//                if (subSubCategory != null && !subSubCategory.equals("")) {
//                    for (Product p : productsList.values())
//                        if (p.getCat().equals(category) && p.getSubCat().equals(subCategory) && p.getSubSubCat().equals(subSubCategory))
//                            products.add(p);
//                } else for (Product p : productsList.values())
//                    if (p.getCat().equals(category) && p.getSubCat().equals(subCategory)) products.add(p);
//            } else for (Product p : productsList.values())
//                if (p.getCat().equals(category)) products.add(p);
//        } else products = new ArrayList<>(productsList.values());
//        return products;

        return productRepo.getProductsByCategory( category, subCategory, subSubCategory);
    }

    //FIXME DAO
    public List<Product> getMissingProductsByCategory(String category, String subCategory, String subSubCategory) {
//        List<Product> products = new ArrayList<>();
//        if (category != null && !category.equals("")) {
//            if (subCategory != null && !subCategory.equals("")) {
//                if (subSubCategory != null && !subSubCategory.equals("")) {
//                    for (Product p : productsList.values())
//                        if (p.getCat().equals(category) && p.getSubCat().equals(subCategory) && p.getSubSubCat().equals(subSubCategory)) {
//                            if (p.getCurrentQuantity() <= p.getMinQuantity())
//                                products.add(p);
//                        }
//                }
//                else {
//                    for (Product p : productsList.values()) {
//                        if (p.getCat().equals(category) && p.getSubCat().equals(subCategory)) {
//                            if (p.getCurrentQuantity() <= p.getMinQuantity())
//                                products.add(p);
//                        }
//                    }
//                }
//            }
//            else {
//                for (Product p : productsList.values()) {
//                    if (p.getCat().equals(category)) {
//                        if (p.getCurrentQuantity() <= p.getMinQuantity())
//                            products.add(p);
//                    }
//                }
//            }
//        }
//        else
//            for (Product p : productsList.values()) {
//                if (p.getCurrentQuantity() <= p.getMinQuantity())
//                    products.add(p);
//            }
//        return products;
        return productRepo.getMissingProductsByCategory( category, subCategory, subSubCategory);

    }


    //FIXME DAO
    public Map<Item, String> getDamagedItems(){
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
        return productRepo.getDamagedItems();

    }

    //FIXME DAO
    public Map<Item, String> getExpiredItems(){
//        Map<Item, String> expiredItemsList = new HashMap<>();
//        for (Product product: productsList.values() ){
//            Map<Item, String> expiredItemsOfP;
//            expiredItemsOfP = product.findExpiredItems();
//            expiredItemsList.putAll(expiredItemsOfP);
//        }
//
//        return expiredItemsList;
        return productRepo.getExpiredItems();

    }


    //FIXME DAO
    public void showProducts(){
//        for (Product p: productsList.values()){
//            System.out.println(p.getName());
//        }
        productRepo.showProducts();
    }

    //FIXME DAO
    public void showAllItems(){
//        for (Product p: productsList.values()){
//            Map<String, Item> items =  p.getItems();
//            for (Item i: items.values()){
//                System.out.println( p.getName() +" "+ i.getItemID() +" "+i.getExpirationDate());
//            }
//        }
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
