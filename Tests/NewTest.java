import Domain.Domainstock.*;
import Domain.Domainstock.ProductController;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import DAL.DALworkers.DatabaseConnection;
import DAL.DALworkers.ShiftHDAO;
import Domain.Domainstock.Item;
import Domain.Domainstock.Product;
import Domain.Domainstock.ProductController;
import Domain.Domainstock.ReportController;
import Presentation.Presentationworkers.*;
import Domain.Domainworkers.*;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class NewTest {

    private ProductController productController;
    private ReportController reportController;

    private WorkerController wc;
    private Branch branch;
    private WorkArrangementRepositoryWorkers workArrangementRepository;
    private BranchesRepositoryWorkers branchRepo;
    private ManagersRepositoryWorkers managerRepo;
    private WorkersRepositoryWorkers workersRepository;
    private ShiftHRepositoryWorkers shiftHRepository;
    private BranchesRepositoryWorkers branchesRepository;
    private WorkersOnShiftRepositoryWorkers workersOnShiftRepository;
    private ManagerPresentation managerPresentation;
    private AppointmentManager appointmentManager;
    private AddWorker addWorker;
    private EmploymentTermination employmentTermination;
    private UpdateWorkerDetails updateWorkerDetails;
    private SubmitConstraints submitConstraints;
    private ShiftController shiftController;
    private HRManager manager;
    private WorkerPresentation workerPresentation;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        productController =  ProductController.getInstance();
        reportController = ReportController.getInstance();
        String dbPath = "C:\\Users\\97252\\OneDrive\\Documents\\GitHub\\ADSS_Group_I\\Supermarket.db";
        DatabaseConnection dbConnection = new DatabaseConnection(dbPath);
        // Initialize repositories and controllers
        workArrangementRepository = new WorkArrangementRepositoryWorkers(dbConnection);
        branchRepo = new BranchesRepositoryWorkers(dbConnection, workArrangementRepository);
        managerRepo = new ManagersRepositoryWorkers(dbConnection, branchRepo);
        workersRepository = new WorkersRepositoryWorkers(dbConnection, branchRepo);
        ShiftHDAO shf = new ShiftHDAO(dbConnection, branchRepo);
        shiftHRepository = new ShiftHRepositoryWorkers(shf);
        branchesRepository = new BranchesRepositoryWorkers(dbConnection, workArrangementRepository);
        wc = new WorkerController(dbConnection, workArrangementRepository);
        branch = new Branch(1, workArrangementRepository);
        // Initialize Presentation dependencies
        appointmentManager = new AppointmentManager(wc);
        addWorker = new AddWorker(wc);
        employmentTermination = new EmploymentTermination(wc);
        updateWorkerDetails = new UpdateWorkerDetails(wc);
        submitConstraints = new SubmitConstraints(wc, shiftController);
        shiftController = new ShiftController(shiftHRepository, workersRepository, workArrangementRepository, workersOnShiftRepository);
        // Initialize ManagerPresentation
        managerPresentation = new ManagerPresentation(wc, appointmentManager, addWorker, employmentTermination, updateWorkerDetails, submitConstraints, shiftController);
        // Redirect output stream to capture print statements
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        workerPresentation = new WorkerPresentation(wc, submitConstraints);
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
    void testUpdateItemDamaged() {
        try {
            // Step 1: Add a product
            productController.addProduct("10", "Test Product 10", "C9", "Test Manufacturer", 5, 20.0, 25.0, 0.0, 0.0, "Vegtables", "Tomato", "50 gram");

            // Step 2: Retrieve the product and add an item to it
            Product product = productController.getProduct("Test Product 10");
            productController.addItem("Test Product 10", LocalDate.now().plusMonths(6), true);

            // Step 3: Get the item ID of the newly added item
            Item item = product.getItemRepo().get("1"); // Assuming the item ID is 0 for the first item added
            int itemID = item.getItemID();

            // Verify that the item is not damaged initially
            assertFalse(item.isDamaged());

            // Step 4: Update the item to be damaged
            productController.updateItemDamaged("Test Product 10", itemID);

            // Verify that the item's damaged status has been updated
            assertTrue(product.getItemRepo().get("1").isDamaged());

        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void testIsManagerOrStorekeeper() {
//        try {
//            // Step 1: Add a storekeeper to the repository
//            Worker storekeeper = new Worker("123 Main St", "John Doe", 666666666, 654321, 30.0, 15, JobType.Full_time_job, new Branch(1, workArrangementRepository), Collections.singleton(Role.Storekeeper));
//            workersRepository.Insert(storekeeper);
//
//            // Verify that the worker is recognized as a storekeeper
//            assertTrue(wc.is_Manager_Or_Storekeeper(666666666));
//
//            // Step 2: Add a shift manager to the repository
//            Worker shiftManager = new Worker("456 Elm St", "Jane Smith", 777777777, 987654, 25.0, 10, JobType.Part_time_job, new Branch(1, workArrangementRepository), Collections.singleton(Role.Shift_manager));
//            workersRepository.Insert(shiftManager);
//
//            // Verify that the worker is recognized as a shift manager
//            assertTrue(wc.is_Manager_Or_Storekeeper(777777777));
//
//            // Step 3: Add a worker who is neither a storekeeper nor a shift manager
//            Worker regularWorker = new Worker("789 Oak St", "Alice Johnson", 888888888, 112233, 20.0, 5, JobType.Works_contractor, new Branch(1, workArrangementRepository), Collections.singleton(Role.Cashier));
//            workersRepository.Insert(regularWorker);
//
//            // Verify that the worker is recognized as a regular worker
//            assertFalse(wc.is_Manager_Or_Storekeeper(888888888));
//
//            // Verify for a non-existent worker
//            assertFalse(wc.is_Manager_Or_Storekeeper(999999999));
//
//        } catch (Exception e) {
//            fail("Exception thrown: " + e.getMessage());
//        }

        String input = "989898989\n" +  // ID number
                "yori\n" +     // Name
                "123456\n" +      // Bank account
                "50.0\n" +        // Hourly salary
                "20\n" +          // Vacation days
                "Full_time_job\n" + // Job type
                "123 Main St\n" +  // Address
                "1\n" +           // Branch number
                "Storekeeper\n";  // Role

        System.out.println("hellow");
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.out.println("hi");
        System.setIn(in);
        Branch branch1 = new Branch(1,workArrangementRepository);
        System.out.println("here");

        AddWorker addWorker = new AddWorker(wc);
        System.out.println("here1");
        addWorker.Add_Worker(branch1);
        System.out.println("here2");
        assertTrue(wc.is_Manager_Or_Storekeeper(989898989));
    }

    @org.junit.jupiter.api.Test
    void testIsManagerOrStorekeeper1() {
        String input = "989898989\n" +  // ID number
                "yori\n" +     // Name
                "123456\n" +      // Bank account
                "50.0\n" +        // Hourly salary
                "20\n" +          // Vacation days
                "Full_time_job\n" + // Job type
                "123 Main St\n" +  // Address
                "1\n" +           // Branch number
                "Storekeeper\n";  // Role

        System.out.println("hellow");
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.out.println("hi");
        System.setIn(in);
        Branch branch1 = new Branch(1,workArrangementRepository);
        System.out.println("here");

        AddWorker addWorker = new AddWorker(wc);
        System.out.println("here1");
        addWorker.Add_Worker(branch1);
        System.out.println("here2");
        assertTrue(wc.is_Manager_Or_Storekeeper(989898989));

    }





}
