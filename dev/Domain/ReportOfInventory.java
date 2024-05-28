package Domain;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReportOfInventory extends Report{
    public ReportOfInventory(String reportID, LocalDateTime reportDate, ArrayList<Item> inventoryItems) {
        super(reportID, reportDate);
        this.inventoryItems = inventoryItems;
    }

    private ArrayList<Item> inventoryItems;

}

