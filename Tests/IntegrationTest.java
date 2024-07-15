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

public class IntegrationTest {
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
        private ProductController productController;
        private ReportController reportController;
        @Before
        public void setUp() {
            productController = ProductController.getInstance();
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
        }
  //      @Test
//        public void testWorkerNotStorekeeperOrManager() {
//            // Set up input and output streams for testing
//            String input = "100000009\n6\n7\n"; // Worker ID and options to select
//            InputStream in = new ByteArrayInputStream(input.getBytes());
//            System.setIn(in);
//            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//            System.setOut(new PrintStream(outContent));
//
//            // Run the worker menu
//            workerPresentation.WorkerMenu();
//
//            // Verify the output
//            String expectedOutput = "Enter your worker ID number: The worker ID number is incorrect, please try again.\n" +
//                    "Enter your worker ID number: The worker ID number is incorrect, please try again.\n" +
//                    "Enter your worker ID number: Menu:\n" +
//                    "Please choose one of the following:\n" +
//                    "1. My global salary\n" +
//                    "2. My vacation days\n" +
//                    "3. My starting day\n" +
//                    "4. Submit my constraints\n" +
//                    "5. Present work arrangement\n" +
//                    "6. Display inventory management menu\n" +
//                    "7. Log out from the user\n" +
//                    "Enter your choice: Not a storekeeper or shift manager\n" +
//                    "Menu:\n" +
//                    "Please choose one of the following:\n" +
//                    "1. My global salary\n" +
//                    "2. My vacation days\n" +
//                    "3. My starting day\n" +
//                    "4. Submit my constraints\n" +
//                    "5. Present work arrangement\n" +
//                    "6. Display inventory management menu\n" +
//                    "7. Log out from the user\n" +
//                    "Enter your choice: Returning to main menu.\n";
//
//            assertEquals(expectedOutput, outContent.toString());
//        }

//    @org.junit.jupiter.api.Test
//    void testUpdateItemDamaged() {
//        try {
//            // Step 1: Add a product
//            productController.addProduct("10", "Test Product 10", "C9", "Test Manufacturer", 5, 20.0, 25.0, 0.0, 0.0, "Vegetables", "Carrot", "200 gram");
//
//            // Step 2: Retrieve the product and add an item to it
//            Product product = productController.getProduct("Test Product 10");
//            productController.addItem("Test Product 10", LocalDate.now().plusMonths(6), true);
//
//            // Step 3: Get the item ID of the newly added item
//            Item item = product.getItemRepo().get("0"); // Assuming the item ID is 0 for the first item added
//            int itemID = item.getItemID();
//
//            // Verify that the item is not damaged initially
//            assertFalse(item.isDamaged());
//
//            // Step 4: Update the item to be damaged
//            productController.updateItemDamaged("Test Product 10", itemID);
//
//            // Verify that the item's damaged status has been updated
//            assertTrue(product.getItemRepo().get("0").isDamaged());
//
//        } catch (Exception e) {
//            fail("Exception thrown: " + e.getMessage());
//        }
//    }

    @org.junit.jupiter.api.Test
    void testCreateDamagedReport() {
        try {
            reportController.createDamagedReport();
            assertNotNull(reportController.getReport(1)); // Assuming it's the second report added
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

}