package test;

import Dal.DatabaseConnection;
import Domain.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import Presentation.AddWorker;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import static Domain.Role.Storekeeper;
import static org.junit.Assert.*;

public class TestUnit {
    private WorkerController wc;
    private Branch branch;
    private WorkArrangementRepository workArrangementRepository;
    private BranchesRepository branchRepo;
    private ManagersRepository managerRepo;
    private WorkersRepository workersRepository;
    private ShiftHRepository shiftHRepository;
    private BranchesRepository branchesRepository;

    @Before
    public void setUp() {
        String dbPath = "C:\\Users\\ronig\\OneDrive\\שולחן העבודה\\ADSS\\Supermarket.db";
        DatabaseConnection dbConnection = new DatabaseConnection(dbPath);
        branchRepo = new BranchesRepository(dbConnection, workArrangementRepository);
        managerRepo = new ManagersRepository(dbConnection, branchRepo);
        workArrangementRepository = new WorkArrangementRepository(dbConnection);
        wc = new WorkerController(dbConnection, workArrangementRepository);
        workersRepository = new WorkersRepository(dbConnection, branchRepo);
        shiftHRepository = new ShiftHRepository();
        branchesRepository = new BranchesRepository(dbConnection, workArrangementRepository);
        branch = new Branch(1, workArrangementRepository);
    }

    @Test
    public void test_add_worker() {
        String input = "123456789\n" +  // ID number
                "JohnDoe\n" +     // Name
                "123456\n" +      // Bank account
                "50.0\n" +        // Hourly salary
                "20\n" +          // Vacation days
                "Full_time_job\n" + // Job type
                "123 Main St\n" +  // Address
                "1\n" +           // Branch number
                "Storekeeper\n";  // Role

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Branch branch1 = new Branch(1,workArrangementRepository);

        AddWorker addWorker = new AddWorker(wc);
        addWorker.Add_Worker(branch1);

        Map<Integer, Worker> workers = wc.getWorkers();
        assertEquals(1, workers.size());

        Worker worker = workers.get(123456789);
        assertEquals("JohnDoe", worker.getName());
        assertEquals("123 Main St", worker.getAddress());
        assertEquals(123456, worker.getBank_account_num());
        assertEquals(50.0, worker.getHourly_salary(), 0.0);
        assertEquals(20, worker.getVaction_days());
        assertEquals(JobType.Full_time_job, worker.getJob_type());
        assertEquals(1, worker.getBranchNum());
        assertTrue(worker.getRoles_permissions().contains(Storekeeper));
    }

    @Test
    public void test_add_worker_invalid_id() {
        JsonObject json = new JsonObject();
        json.addProperty("id", 12345);
        wc.add_worker(json);
        assertEquals(0, wc.getWorkers().size());
    }

    @Test
    public void test_add_worker_part_time() {
        JsonObject json = new JsonObject();
        json.addProperty("id", 987654321);
        json.addProperty("name", "JaneSmith");
        json.addProperty("address", "456 Elm St");
        json.addProperty("bank_account", 654321);
        json.addProperty("hourly_salary", 30.0);
        json.addProperty("vacation_days", 15);
        json.addProperty("job_type", "Part_time_job");
        json.addProperty("branch_num", 2);
        json.addProperty("roles", "Cashier");

        wc.add_worker(json);

        Worker worker = wc.getWorkers().get(987654321);
        assertEquals(2, worker.getBranchNum());
    }

    @Test
    public void test_update_salary() {
        // Adding a worker
        JsonObject jsonAdd = new JsonObject();
        jsonAdd.addProperty("id", 987654321);
        jsonAdd.addProperty("name", "JaneSmith");
        jsonAdd.addProperty("address", "456 Elm St");
        jsonAdd.addProperty("bank_account", 654321);
        jsonAdd.addProperty("hourly_salary", 30.0);
        jsonAdd.addProperty("vacation_days", 15);
        jsonAdd.addProperty("job_type", "Part_time_job");
        jsonAdd.addProperty("branch_num", 2);
        jsonAdd.addProperty("roles", "Cashier");

        wc.add_worker(jsonAdd);

        // Preparing JSON for updating salary
        JsonObject jsonUpdate = new JsonObject();
        jsonUpdate.addProperty("id", 987654321);
        jsonUpdate.addProperty("hourly_salary", 50.0);

        // Updating the salary
        boolean success = wc.update_salary(jsonUpdate);

        // Asserting the update was successful
        assertTrue(success);

        // Asserting the worker's salary was updated correctly
        Worker worker = wc.getWorkers().get(987654321);
        assertEquals(50.0, worker.getHourly_salary(), 0.0);
    }

    @Test
    public void test_update_job_type() {
        // Adding a worker
        JsonObject jsonAdd = new JsonObject();
        jsonAdd.addProperty("id", 987654321);
        jsonAdd.addProperty("name", "JaneSmith");
        jsonAdd.addProperty("address", "456 Elm St");
        jsonAdd.addProperty("bank_account", 654321);
        jsonAdd.addProperty("hourly_salary", 30.0);
        jsonAdd.addProperty("vacation_days", 15);
        jsonAdd.addProperty("job_type", "Part_time_job");
        jsonAdd.addProperty("branch_num", 2);
        jsonAdd.addProperty("roles", "Cashier");

        wc.add_worker(jsonAdd);

        // Preparing JSON for updating job type
        JsonObject jsonUpdate = new JsonObject();
        jsonUpdate.addProperty("id", 987654321);
        jsonUpdate.addProperty("job_type", "Full_time_job");

        // Updating the job type
        boolean success = wc.Update_job_type(jsonUpdate);

        // Asserting the update was successful
        assertTrue(success);

        // Asserting the worker's job type was updated correctly
        Worker worker = wc.getWorkers().get(987654321);
        assertEquals(JobType.Full_time_job, worker.getJob_type());
    }

    @Test
    public void test_update_branch() {
        // Adding a worker
        JsonObject jsonAdd = new JsonObject();
        jsonAdd.addProperty("id", 987654321);
        jsonAdd.addProperty("name", "JaneSmith");
        jsonAdd.addProperty("address", "456 Elm St");
        jsonAdd.addProperty("bank_account", 654321);
        jsonAdd.addProperty("hourly_salary", 30.0);
        jsonAdd.addProperty("vacation_days", 15);
        jsonAdd.addProperty("job_type", "Part_time_job");
        jsonAdd.addProperty("branch_num", 2);
        jsonAdd.addProperty("roles", "Cashier");

        wc.add_worker(jsonAdd);

        // Preparing JSON for updating branch
        JsonObject jsonUpdate = new JsonObject();
        jsonUpdate.addProperty("id", 987654321);
        jsonUpdate.addProperty("branch_num", 4);

        // Updating the branch
        boolean success = wc.Update_Branch(jsonUpdate);

        // Asserting the update was successful
        assertTrue(success);

        // Asserting the worker's branch was updated correctly
        Worker worker = wc.getWorkers().get(987654321);
        assertEquals(4, worker.getBranchNum());
    }

    @Test
    public void test_update_bank_account_num() {
        // Adding a worker
        JsonObject jsonAdd = new JsonObject();
        jsonAdd.addProperty("id", 987654321);
        jsonAdd.addProperty("name", "JaneSmith");
        jsonAdd.addProperty("address", "456 Elm St");
        jsonAdd.addProperty("bank_account", 654321);
        jsonAdd.addProperty("hourly_salary", 30.0);
        jsonAdd.addProperty("vacation_days", 15);
        jsonAdd.addProperty("job_type", "Part_time_job");
        jsonAdd.addProperty("branch_num", 2);
        jsonAdd.addProperty("roles", "Cashier");

        wc.add_worker(jsonAdd);

        // Preparing JSON for updating bank account number
        JsonObject jsonUpdate = new JsonObject();
        jsonUpdate.addProperty("id", 987654321);
        jsonUpdate.addProperty("bank_account", 123456);

        // Updating the bank account number
        boolean success = wc.Update_bank_account_num(jsonUpdate);

        // Asserting the update was successful
        assertTrue(success);

        // Asserting the worker's bank account number was updated correctly
        Worker worker = wc.getWorkers().get(987654321);
        assertEquals(123456, worker.getBank_account_num());
    }

    @Test
    public void test_appointment_manager() {
        // Adding a worker
        JsonObject jsonAdd = new JsonObject();
        jsonAdd.addProperty("id", 987654321);
        jsonAdd.addProperty("name", "JaneSmith");
        jsonAdd.addProperty("address", "456 Elm St");
        jsonAdd.addProperty("bank_account", 654321);
        jsonAdd.addProperty("hourly_salary", 30.0);
        jsonAdd.addProperty("vacation_days", 15);
        jsonAdd.addProperty("job_type", "Part_time_job");
        jsonAdd.addProperty("branch_num", 2);
        jsonAdd.addProperty("roles", "Cashier");

        wc.add_worker(jsonAdd);

        // Preparing JSON for appointing manager
        JsonObject jsonAppointment = new JsonObject();
        jsonAppointment.addProperty("id", 987654321);

        // Appointing the worker as a manager
        boolean success = wc.appointment_manager(jsonAppointment);

        // Asserting the appointment was successful
        assertTrue(success);

        // Asserting the worker's roles include Shift_manager
        Worker worker = wc.getWorkers().get(987654321);
        assertTrue(worker.getRoles_permissions().contains(Role.Shift_manager));
    }

    @Test
    public void test_add_worker_contractor() {
        JsonObject json = new JsonObject();
        json.addProperty("id", 135792468);
        json.addProperty("name", "AliceJohnson");
        json.addProperty("address", "789 Oak St");
        json.addProperty("bank_account", 987654);
        json.addProperty("hourly_salary", 25.0);
        json.addProperty("vacation_days", 10);
        json.addProperty("job_type", "Works_contractor");
        json.addProperty("branch_num", 3);
        json.addProperty("roles", "Sorter");

        wc.add_worker(json);

        Worker worker = wc.getWorkers().get(135792468);
        assertEquals(3, worker.getBranchNum());

        JsonObject json1 = new JsonObject();
        json1.addProperty("id", 987654321);
        json1.addProperty("name", "JaneSmith");
        json1.addProperty("address", "456 Elm St");
        json1.addProperty("bank_account", 654321);
        json1.addProperty("hourly_salary", 30.0);
        json1.addProperty("vacation_days", 15);
        json1.addProperty("job_type", "Part_time_job");
        json1.addProperty("branch_num", 2);
        json1.addProperty("roles", "Cashier");

        wc.add_worker(json1);

        Map<Integer, Worker> workers = wc.getWorkers();
        assertEquals(2, workers.size());
    }

    @Test
    public void testPresentWorkers() {
        Set<Role> rolesPermissions = new HashSet<>();
        rolesPermissions.add(Role.Cashier);
        branch = new Branch(1,workArrangementRepository);
        Worker worker = new Worker("Address 1", "John Doe", 1, 12345678, 20.0, 14, JobType.Full_time_job, branch, rolesPermissions);

        JsonArray jsonArray = new JsonArray();
        JsonObject workerJson = new JsonObject();
        workerJson.addProperty("ID", worker.getID_number());
        workerJson.addProperty("Name", worker.getName());
        workerJson.addProperty("Address", worker.getAddress());
        workerJson.addProperty("Bank Account Number", worker.getBank_account_num());
        workerJson.addProperty("Hourly Salary", worker.getHourly_salary());
        workerJson.addProperty("Vacation Days", worker.getVaction_days());
        workerJson.addProperty("Job Type", worker.getJob_type().toString());
        workerJson.addProperty("Branch", worker.getBranchNum());
        workerJson.addProperty("Total Hours", worker.getTotal_hours());
        workerJson.addProperty("Roles and Permissions", worker.getRoles_permissions().toString());
        workerJson.addProperty("Job Status", worker.getJob_status());
        jsonArray.add(workerJson);

        assertEquals(1, jsonArray.size());

        workerJson = jsonArray.get(0).getAsJsonObject();
        assertEquals(1, workerJson.get("ID").getAsInt());
        assertEquals("John Doe", workerJson.get("Name").getAsString());
        assertEquals("Address 1", workerJson.get("Address").getAsString());
        assertEquals(12345678, workerJson.get("Bank Account Number").getAsInt());
        assertEquals(20.0, workerJson.get("Hourly Salary").getAsDouble(), 0.0001);
        assertEquals(14, workerJson.get("Vacation Days").getAsInt());
        assertEquals("Full_time_job", workerJson.get("Job Type").getAsString());
        assertEquals(1, workerJson.get("Branch").getAsInt());
        assertEquals(0.0, workerJson.get("Total Hours").getAsDouble(), 0.0001);
        assertEquals("[Cashier]", workerJson.get("Roles and Permissions").getAsString());
        assertEquals("true", workerJson.get("Job Status").getAsString());
    }

    @Test
    public void testPresentPastWorkers() {
        Set<Role> rolesPermissions = new HashSet<>();
        rolesPermissions.add(Role.Cashier);
        branch = new Branch(1,workArrangementRepository);
        Worker worker1 = new Worker("Address 1", "John Doe", 1, 12345678, 20.0, 14, JobType.Full_time_job, branch, rolesPermissions);
        Worker worker2 = new Worker("Address 2", "Jane Smith", 2, 87654321, 25.0, 21, JobType.Part_time_job, branch, rolesPermissions);

        // Set the job status of worker2 to false
        worker2.setJob_status(false);

        JsonArray jsonArray = new JsonArray();

        // Add worker2's details to jsonArray
        JsonObject workerJson = new JsonObject();
        workerJson.addProperty("id", worker2.getID_number());
        workerJson.addProperty("name", worker2.getName());
        workerJson.addProperty("address", worker2.getAddress());
        workerJson.addProperty("bank_account", worker2.getBank_account_num());
        workerJson.addProperty("hourly_salary", worker2.getHourly_salary());
        workerJson.addProperty("vacation_days", worker2.getVaction_days());
        workerJson.addProperty("job_type", worker2.getJob_type().toString());
        workerJson.addProperty("branch_num", worker2.getBranchNum());
        workerJson.addProperty("roles", worker2.getRoles_permissions().toString());
        workerJson.addProperty("starting_day", worker2.getStarting_day().toString());
        workerJson.addProperty("total_hours", worker2.getTotal_hours());
        workerJson.addProperty("job_status", worker2.getJob_status());
        jsonArray.add(workerJson);

        assertEquals(1, jsonArray.size());

        JsonObject retrievedWorkerJson = jsonArray.get(0).getAsJsonObject();
        assertEquals(2, retrievedWorkerJson.get("id").getAsInt());
        assertEquals("Jane Smith", retrievedWorkerJson.get("name").getAsString());
        assertEquals("Address 2", retrievedWorkerJson.get("address").getAsString());
        assertEquals(87654321, retrievedWorkerJson.get("bank_account").getAsInt());
        assertEquals(25.0, retrievedWorkerJson.get("hourly_salary").getAsDouble(), 0.0001);
        assertEquals(21, retrievedWorkerJson.get("vacation_days").getAsInt());
        assertEquals("Part_time_job", retrievedWorkerJson.get("job_type").getAsString());
        assertEquals(1, retrievedWorkerJson.get("branch_num").getAsInt());
        assertEquals(0.0, retrievedWorkerJson.get("total_hours").getAsDouble(), 0.0001);
        assertEquals("[Cashier]", retrievedWorkerJson.get("roles").getAsString());
        assertEquals(worker2.getStarting_day().toString(), retrievedWorkerJson.get("starting_day").getAsString());
        assertEquals(false, retrievedWorkerJson.get("job_status").getAsBoolean());
    }

    @Test
    public void test_add_manager() {
        JsonObject json = new JsonObject();
        json.addProperty("name", "JohnDoe");
        json.addProperty("ID_number", 123456788);
        json.addProperty("branch_num", 1);

        wc.add_manager(json);

        HRManager manager = managerRepo.getManager(123456788);
        assertNotNull(manager);
        assertEquals("JohnDoe", manager.getName());
        assertEquals(123456788, manager.getID_number());

        Branch branch = branchRepo.Find(1);
        assertNotNull(branch);
        assertEquals(1, branch.getBranchNum());
        assertEquals(manager, branch.getHRManager());
    }



}