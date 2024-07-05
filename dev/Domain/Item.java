package Domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Item {
    private String productName;
    static int itemIDidx = 0;
    private int itemID;
    private LocalDate expirationDate;
    private boolean isDamaged;
    private boolean onShelf;

    public Item(String productName, LocalDate expirationDate, boolean isDamaged, boolean onShelf) {
        this.productName = productName;
        itemIDidx++;
        this.itemID = itemIDidx;
        this.expirationDate = expirationDate;
        this.isDamaged = isDamaged;
        this.onShelf = onShelf;
    }

    public Item(String productName, int itemid, LocalDate expirationDate, boolean isDamaged, boolean onShelf) {
        this.productName = productName;
        this.itemID = itemid;
        this.expirationDate = expirationDate;
        this.isDamaged = isDamaged;
        this.onShelf = onShelf;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setDamaged(boolean damaged) {
        this.isDamaged = damaged;
    }

    public void setOnShelf(boolean onShelf) {
        this.onShelf = onShelf;
    }

    public int getItemID() {
        return itemID;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public boolean isDamaged() {
        return isDamaged;
    }

    public boolean isOnShelf() {
        return onShelf;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", expirationDate=" + expirationDate +
                ", isDamaged=" + isDamaged +
                ", onShelf=" + onShelf +
                '}';
    }
}
