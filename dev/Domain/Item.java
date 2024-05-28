package Domain;

import java.time.LocalDateTime;

public class Item {
    private int itemID;
    private LocalDateTime expirationDate;
    private boolean isDamaged;

    public Item(int itemID, LocalDateTime expirationDate, boolean isDamaged, boolean onShelf) {
        this.itemID = itemID;
        this.expirationDate = expirationDate;
        this.isDamaged = isDamaged;
        this.onShelf = onShelf;
    }

    private boolean onShelf;

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
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

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public boolean isDamaged() {
        return isDamaged;
    }

    public boolean isOnShelf() {
        return onShelf;
    }


}
