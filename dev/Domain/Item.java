package Domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Item {
    private int itemID;
    private LocalDate expirationDate;
    private boolean isDamaged;

    public Item(int itemID, LocalDate expirationDate, boolean isDamaged, boolean onShelf) {
        this.itemID = itemID;
        this.expirationDate = expirationDate;
        this.isDamaged = isDamaged;
        this.onShelf = onShelf;
    }

    private boolean onShelf;

    public void setItemID(int itemID) {
        this.itemID = itemID;
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
