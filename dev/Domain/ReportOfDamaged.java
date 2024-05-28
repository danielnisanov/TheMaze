package Domain;

import java.time.LocalDateTime;
import java.util.List;

public class ReportOfDamaged extends Report{
    private List<Item> damagedItems;

    public ReportOfDamaged(int reportID, LocalDateTime reportDate, List<Item> damagedItems) {
        super(reportID, reportDate);
        this.damagedItems = damagedItems;
    }

    @Override
    public String toString() {
        String toPrint =  super.toString() + "\n" + "The damaged items are: \n ";
        for (Item i:damagedItems) {
            toPrint = toPrint + i.getItemID() + "\n";
        }
        return toPrint;
    }

}
