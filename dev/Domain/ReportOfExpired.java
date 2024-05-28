package Domain;

import java.time.LocalDateTime;
import java.util.List;

public class ReportOfExpired extends Report{
    private List<Item> expiredItems;

    public ReportOfExpired(int reportID, LocalDateTime reportDate, List<Item> expiredItems) {
        super(reportID, reportDate);
        this.expiredItems = expiredItems;
    }

    @Override
    public String toString() {
        String toPrint =  super.toString() + "\n" + "The expired items are: \n ";
        for (Item i:expiredItems) {
            toPrint = toPrint + i.getItemID() + "\n";
        }
        return toPrint;
    }
}
