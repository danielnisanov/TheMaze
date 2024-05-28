package Domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReportOfExpired extends Report{
    private ArrayList<Item> expiredItems;

    public ReportOfExpired(String reportID, LocalDateTime reportDate, ArrayList<Item> expiredItems) {
        super(reportID, reportDate);
        this.expiredItems = expiredItems;
    }
}
