package Domain.Domainstock;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ReportOfDamaged extends Report{
    private Map<Item, String> damagedItems;

    public ReportOfDamaged(int reportID, LocalDate reportDate, Map<Item, String> damagedItems) {
        super(reportID, reportDate);
        this.damagedItems = damagedItems;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
                .append("\nThe damaged items are:\n");

        for (Map.Entry<Item, String> entry : damagedItems.entrySet()) {
            sb.append("Product name: ")
                    .append(entry.getValue())
                    .append(", Item ID: ")
                    .append(entry.getKey().getItemID())
                    .append("\n");
        }

        return sb.toString();
    }

}
