package Domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ReportOfExpired extends Report{
    private Map<Item, String> expiredItems;

    public ReportOfExpired(int reportID, LocalDate reportDate, Map<Item, String> expiredItems) {
        super(reportID, reportDate);
        this.expiredItems = expiredItems;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
                .append("\nThe expired items are:\n");

        for (Map.Entry<Item, String> entry : expiredItems.entrySet()) {
            sb.append("Product name: ")
                    .append(entry.getValue())
                    .append(", Item ID: ")
                    .append(entry.getKey().getItemID())
                    .append("\n");
        }

        return sb.toString();
    }
}
