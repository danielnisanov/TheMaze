package Domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ReportOfMissing  extends Report{
    private List<Product> missingProducts;
    public ReportOfMissing(int reportID, LocalDate reportDate,  List<Product> missingProducts) {
        super(reportID, reportDate);
        this.missingProducts = missingProducts;
    }


    @Override
    public String toString() {
        String toPrint =  super.toString() + "\n" +  "The missing products are:\n";
        for (Product p:missingProducts) {
            int missing = p.getMinQuantity() - p.getCurrentQuantity();
            toPrint = toPrint + "catalog number:" + p.getCatNum()+ ", name:" + p.getName() + " ,quantity:" + missing + "\n";
        }
        return toPrint;
    }

}
