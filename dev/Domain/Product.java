package Domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Product {
    private int itemIndex;
    private String catNum; //Catalog Number
    private String name;
    private String area; //location in the store
    private String manufacturer;
    private int currentQuantity;
    private int shelfQuantity;
    private int warehouseQuantity;
    private int minQuantity;


    private double costPrice;
    private double discount; //discount on the costPrice from the suppliers
    private double sellingPrice;
    private double sale; //sale on the sellingPrice from the store

    private String cat;
    private String subCat;
    private String subSubCat;
    private Map<String,Item> items;

    public Product(String catNum, String name, String area, String manufacturer, int minQuantity, double costPrice, double sellingPrice, double discount, double sale, String cat, String subCat, String subSubCat) {
        this.itemIndex = 0;
        this.catNum = catNum;
        this.name = name;
        this.area = area;
        this.manufacturer = manufacturer;
        this.currentQuantity = 0;
        this.shelfQuantity = 0;
        this.warehouseQuantity = 0;
        this.minQuantity = minQuantity;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.discount = discount;
        this.sale = sale;
        this.cat = cat;
        this.subCat = subCat;
        this.subSubCat = subSubCat;
        items = new HashMap<>();
    }

    public void isExists(int id) throws Exception{
        if (!items.containsKey(Integer.toString(id))){
            throw new Exception("Item with id " + id + " doesn't exist.");
        }
    }

    public Map<Item, String> findExpiredItems ()  {
        LocalDate today = LocalDate.now();
        Map<Item, String>  expiredItems = new HashMap<>();
        for (Item i: items.values()){
            if(i.getExpirationDate().isBefore(today)){
                expiredItems.put(i, name);
            }
        }
        return expiredItems;
    }

    public int getNumDamagedItems(){
        int counter = 0;
            for (Item item: this.getItems().values()){
                if (item.isDamaged()){
                    counter++;
                }
            }

        return counter;
    }

    public String getArea() {
        return area;
    }

    public double getCurrentCostPrice(){
        return costPrice*discount*0.01;
    }

    public double getCurrentSellingPrice(){
        return sellingPrice*discount*0.01;
    }

    public boolean isOnShelf(int id) throws Exception {
        isExists(id);
        for (Map.Entry<String, Item> entry : items.entrySet()) {
            if (entry.getValue().getItemID() == id) {
                return entry.getValue().isOnShelf();
            }
        }
        return false;
    }

    public void setDiscount(double discount) {
            this.discount = discount;
    }

    public void updateDamagedItem(int id, boolean damaged) throws Exception {
        isExists(id);
        items.get((Integer.toString(id))).setDamaged(damaged);
    }

    public void addItem(LocalDate expirationDate, boolean onShelf) throws Exception{
        if (expirationDate == null){
            throw new Exception("expirationDate is missing.");
        }
        itemIndex++;
        Item newItem = new Item(itemIndex, expirationDate,false, onShelf );
        items.put((Integer.toString(itemIndex)), newItem);
        if (onShelf)
            shelfQuantity++;
        else
            warehouseQuantity++;
        currentQuantity++;
    }

    public Item getItem(int id) throws Exception {
        isExists(id);
        return items.get(Integer.toString(id));
    }


    public void removeItem(int id) throws Exception{
        isExists(id);
        if (items.get((Integer.toString(id))).isOnShelf())
            shelfQuantity--;
        else
            warehouseQuantity--;
        items.remove((Integer.toString(id)));
        currentQuantity--;
        if (currentQuantity <= minQuantity){
            System.out.println("Please note, the quantity of items from this product has reached the minimum quantity:" + minQuantity);
        }
    }

    public void setItemIndex(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    public void setCatNum(String catNum) {
        this.catNum = catNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public void setShelfQuantity(int shelfQuantity) {
        this.shelfQuantity = shelfQuantity;
    }

    public void setWarehouseQuantity(int warehouseQuantity) {
        this.warehouseQuantity = warehouseQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public void setSubCat(String subCat) {
        this.subCat = subCat;
    }

    public void setSubSubCat(String subSubCat) {
        this.subSubCat = subSubCat;
    }

    public void setItems(Map<String, Item> items) {
        this.items = items;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public String getCatNum() {
        return catNum;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public int getShelfQuantity() {
        return shelfQuantity;
    }

    public int getWarehouseQuantity() {
        return warehouseQuantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public double getSale() {
        return sale;
    }

    public String getCat() {
        return cat;
    }

    public String getSubCat() {
        return subCat;
    }

    public String getSubSubCat() {
        return subSubCat;
    }

    public Map<String, Item> getItems() {
        return items;
    }

    public void moveItem(int id) throws Exception{
        isExists(id);
        if (!items.get((Integer.toString(id))).isOnShelf()) {
            items.get((Integer.toString(id))).setOnShelf(true);
            shelfQuantity++;
            warehouseQuantity--;
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "catNum='" + catNum + '\'' +
                ", name='" + name + '\'' +
                ", area='" + area + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", currentQuantity=" + currentQuantity +
                ", shelfQuantity=" + shelfQuantity +
                ", warehouseQuantity=" + warehouseQuantity +
                ", minQuantity=" + minQuantity +
                ", costPrice=" + costPrice*(1-discount) +
                ", sellingPrice=" + sellingPrice*(1-sale) +
                ", cat='" + cat + '\'' +
                ", subCat='" + subCat + '\'' +
                ", subSubCat='" + subSubCat + '\'' +
                ", items=" + items +
                '}';
    }

}
