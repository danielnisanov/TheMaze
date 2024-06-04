package Domain;

import java.time.LocalDate;
import java.util.List;

public class ReportByCategory extends Report {
    private List<Product> productList;
    private String categoryName;
    public ReportByCategory(int reportID, LocalDate reportDate, List<Product> productList,String categoryName ) {
        super(reportID, reportDate);
        this.productList = productList;
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        String toPrint =  super.toString() + "\n" +  "The products in the category: "+ categoryName+" are:\n";
        for (Product p:productList) {
            toPrint = toPrint + "catalog number: " + p.getCatNum()+ " name: " + p.getName() + " area: " + p.getArea() + " manufacturer: " + p.getManufacturer() + " quantity: " + p.getCurrentQuantity() + "\n";
        }
        return toPrint;
    }

}
