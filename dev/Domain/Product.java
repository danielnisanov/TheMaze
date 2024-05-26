package Domain;

public class Product {
    protected String catNum; //Catalog Number
    protected String area; //location in the store
    protected String manufacturer;
    protected int currentQuantity;
    protected int shelfQuantity;
    protected int warehouseQuantity;
    protected int minQuantity;
    protected double costPrice;
    protected double sellingPrice;
    protected double discount;
    protected SubSubCategory subSubCategory;

    @Override
    public String toString() {
        return "Product{" +
                "catNum='" + catNum + '\'' +
                ", area='" + area + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", currentQuantity=" + currentQuantity +
                ", shelfQuantity=" + shelfQuantity +
                ", warehouseQuantity=" + warehouseQuantity +
                ", minQuantity=" + minQuantity +
                ", costPrice=" + costPrice +
                ", sellingPrice=" + sellingPrice +
                ", discount=" + discount +
                ", subSubCategory=" + subSubCategory +
                '}';
    }
}
