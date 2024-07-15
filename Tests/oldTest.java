import Domain.Domainstock.*;
import Domain.Domainstock.ProductController;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class oldTest {

    private ProductController productController;
    private ReportController reportController;

    @BeforeEach
    void setUp() {
        productController =  ProductController.getInstance();
        reportController = ReportController.getInstance();
    }

    @org.junit.jupiter.api.Test
    void testAddProduct() {
        try {
            productController.addProduct("1", "Test Product 1", "A1", "Test Manufacturer", 100, 10.0, 15.0, 0.0, 0.0, "Fruits", "Apple", "100 gram");
            Product retrievedProduct = productController.getProduct("Test Product 1");
            assertNotNull(retrievedProduct);

            assertEquals("Test Product 1", retrievedProduct.getName());
            assertEquals("A1", retrievedProduct.getArea());
            assertEquals("Test Manufacturer", retrievedProduct.getManufacturer());

        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void testRemoveProduct() {
        try {
            productController.addProduct("2", "Another Product 2", "B2", "Another Manufacturer", 50, 8.0, 12.0, 0.0, 0.0, "Fruits", "Apple", "100 gram");
            productController.removeProduct("Another Product 2");
            assertThrows(Exception.class, () -> {
                productController.getProduct("Another Product 2");
            });
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void testAddItemToProduct() {
        try {
            productController.addProduct("3", "Test Product 3", "A2", "Test Manufacturer", 80, 12.0, 18.0, 0.0, 0.0, "Fruits", "Apple", "100 gram");
            productController.addItem("Test Product 3", LocalDate.now().plusMonths(6), true);
            Product product = productController.getProduct("Test Product 3");
            assertEquals(1, product.getItemRepo().getItems().size());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void testRemoveItemFromProduct() {
        try {
            productController.addProduct("4", "Test Product 4", "A3", "Test Manufacturer", 70, 15.0, 20.0, 0.0, 0.0, "Fruits", "Apple", "100 gram");
            productController.addItem("Test Product 4", LocalDate.now().plusMonths(3), true);
            Product product = productController.getProduct("Test Product 4");
            int initialItemCount = product.getItemRepo().getItems().size();
            productController.removeItem("Test Product 4", 1); // Assuming item number 1
            assertEquals(initialItemCount - 1, product.getItemRepo().getItems().size());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void testUpdateProductDiscount() {
        try {
            productController.addProduct("5", "Test Product 5", "A4", "Test Manufacturer", 60, 20.0, 25.0, 0.0, 0.0, "Fruits", "Apple", "100 gram");
            productController.updateProductDiscount("Test Product 5", 0.1); // 10% discount
            Product product = productController.getProduct("Test Product 5");
            assertEquals(0.1, product.getDiscount());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void testUpdateProductSale() {
        try {
            productController.addProduct("6", "Test Product 6", "A5", "Test Manufacturer", 50, 18.0, 22.0, 0.0, 0.0, "Fruits", "Apple", "100 gram");
            productController.updateProductSale("Test Product 6", 0.2); // 20% sale
            Product product = productController.getProduct("Test Product 6");
            assertEquals(0.2, product.getSale());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }



    @org.junit.jupiter.api.Test
    void testCreateInventoryReport() {
        try {
            reportController.createInventoryReport("Fruits", "Apple", "100 gram");
            assertNotNull(reportController.getReport(1)); // Assuming it's the first report added
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void testCreateDamagedReport() {
        try {
            reportController.createDamagedReport();
            assertNotNull(reportController.getReport(2)); // Assuming it's the second report added
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void testGetMissingItemsForProduct() {
        try {
            productController.addProduct("7", "Test Product 7", "C3", "Yet Another Manufacturer", 10, 12.0, 18.0, 0.0, 0.0, "Fruits", "Apple", "100 gram");
            productController.addItem("Test Product 7", LocalDate.now().plusMonths(4), true);
            productController.addItem("Test Product 7", LocalDate.now().plusMonths(2), true);
            productController.addItem("Test Product 7", LocalDate.now().plusMonths(5), false); // On shelf item
            Product product = productController.getProduct("Test Product 7");
            assertEquals(7,product.getMinQuantity() - product.getCurrentQuantity());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void testGetExpiredItemsForProduct() {
        try {
            productController.addProduct("8", "Test Product 8", "C4", "Another Manufacturer", 20, 15.0, 20.0, 0.0, 0.0, "Fruits", "Apple", "100 gram");
            Map<Item, String> expiredItemsBefore = productController.getExpiredItems();
            int beforeSize = expiredItemsBefore.size();
            productController.addItem("Test Product 8", LocalDate.of(2020, 1, 1), true); // Expired item
            Map<Item, String> expiredItemsAfter = productController.getExpiredItems();
            int afterSize = expiredItemsAfter.size();
            assertEquals(beforeSize + 1, afterSize, "The expired number of items is not correct");
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void testUpdateItemOnShelf() {
        try {
            productController.addProduct("9", "Test Product 9", "C8", "Test Manufacturer", 4, 18.0, 22.0, 0.0, 0.0, "Fruits", "Apple", "100 gram");
            Product product = productController.getProduct("Test Product 9");
            int beforeOnShelf = product.getShelfQuantity();
            productController.addItem("Test Product 9", LocalDate.now().plusMonths(6), true);
            int afterOnShelf = product.getShelfQuantity();
            assertEquals( beforeOnShelf+1, afterOnShelf);
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

}
