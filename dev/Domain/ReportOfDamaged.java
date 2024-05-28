package Domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReportOfDamaged extends Report{
    private ArrayList<Item> damagedItems;

    public ReportOfDamaged(String reportID, LocalDateTime reportDate, ArrayList<Item> damagedItems) {
        super(reportID, reportDate);
        this.damagedItems = damagedItems;
    }
}
