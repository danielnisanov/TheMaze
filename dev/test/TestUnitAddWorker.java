package test;
import Domain.*;
import Presentation.ManagerPresentation;
import Presentation.SubmitConstraints;
import Presentation.UpdateWorkerDetails;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.Map;

import Presentation.AddWorker;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertEquals;

public class TestUnitAddWorker {

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

        WorkerController wc = new WorkerController("empty.csv");
        AddWorker addWorker = new AddWorker(wc);
        addWorker.add_worker();

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
        assertTrue(worker.getRoles_permissions().contains(Role.Storekeeper));


    }


    @Test
    public void test_add_worker_part_time() {
        WorkerController wc = new WorkerController("empty.csv");
        AddWorker addWorker = new AddWorker(wc);

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
    public void test_add_worker_contractor() {
        WorkerController wc = new WorkerController("empty.csv");
        AddWorker addWorker = new AddWorker(wc);

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

        AddWorker addWorker1 = new AddWorker(wc);

        JsonObject json1 = new JsonObject();
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
        Map<Integer, Worker> workers = wc.getWorkers();
        assertEquals(2, workers.size());

    }

    @Test
    public void test_add_worker_invalid_id() {
        WorkerController wc = new WorkerController("empty.csv");
        AddWorker addWorker = new AddWorker(wc);

        // Provide an invalid ID (less than 9 digits)
        JsonObject json = new JsonObject();
        json.addProperty("id", 12345);
        assertEquals(0, wc.getWorkers().size());
    }

    @Test
    public void tests_WorkerConstraint() throws FileNotFoundException {
        String str = new String("1 1 1 1 1 1 1 1 1 1 1 1 1 1");
        byte[] inputBytes = str.getBytes();
        InputStream IS = new ByteArrayInputStream(inputBytes);
        System.setIn(IS);

        JsonObject json = new JsonObject();
        json.addProperty("id", 1);

        Map<String, List<String>> worker_constraint=  SubmitConstraints.WorkerConstraint(json);
        assertEquals(worker_constraint.size(),0);
        String str2 = new String("2 2 2 2 2 2 2 2 2 2 2 2 2 2");
        inputBytes = str2.getBytes();
        IS = new ByteArrayInputStream(inputBytes);
        System.setIn(IS);
        worker_constraint=  SubmitConstraints.WorkerConstraint(json);
        assertEquals(worker_constraint.size(),7);
        String str3 = new String("1 1 2 2 2 2 2 2 2 2 2 2 2 2");
        inputBytes = str3.getBytes();
        IS = new ByteArrayInputStream(inputBytes);
        System.setIn(IS);
        worker_constraint=  SubmitConstraints.WorkerConstraint(json);
        assertEquals(worker_constraint.size(),6);
        String str4 = new String("1 1 2 2 2 2 1 2 2 2 2 2 2 2");
        inputBytes = str4.getBytes();
        IS = new ByteArrayInputStream(inputBytes);
        System.setIn(IS);
        worker_constraint=  SubmitConstraints.WorkerConstraint(json);
        assertEquals(worker_constraint.size(),6);

    }

    @Test
    public void test_update_salary() {
        WorkerController wc = new WorkerController("empty.csv");

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
        boolean success = wc.update_salary_success(jsonUpdate);

        // Asserting the update was successful
        assertTrue(success);

        // Asserting the worker's salary was updated correctly
        Worker worker = wc.getWorkers().get(987654321);
        assertEquals(50.0, worker.getHourly_salary(), 0.0);
    }

    @Test
    public void test_update_job_type() {
        WorkerController wc = new WorkerController("empty.csv");

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
        boolean success = wc.update_job_type_success(jsonUpdate);

        // Asserting the update was successful
        assertTrue(success);

        // Asserting the worker's job type was updated correctly
        Worker worker = wc.getWorkers().get(987654321);
        assertEquals(JobType.Full_time_job, worker.getJob_type());
    }

    @Test
    public void test_update_branch() {
        WorkerController wc = new WorkerController("empty.csv");

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
        jsonUpdate.addProperty("branch_num", "4");

        // Updating the job type
        boolean success = wc.update_branch_success(jsonUpdate);

        // Asserting the update was successful
        assertTrue(success);

        // Asserting the worker's job type was updated correctly
        Worker worker = wc.getWorkers().get(987654321);
        assertEquals(4, worker.getBranchNum());
    }

    @Test
    public void test_Bank_Account_Num() {
        WorkerController wc = new WorkerController("empty.csv");

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
        jsonUpdate.addProperty("bank_account", 123456);

        // Updating the job type
        boolean success = wc.Update_Bank_Account_Num_success(jsonUpdate);

        // Asserting the update was successful
        assertTrue(success);

        // Asserting the worker's job type was updated correctly
        Worker worker = wc.getWorkers().get(987654321);
        assertEquals(123456, worker.getBank_account_num());

    }

    @Test
    public void test_appointment_manager() {
        WorkerController wc = new WorkerController("empty.csv");
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

//    @Test
//    public void test_print_past_workers() {
//        WorkerController wc = new WorkerController("empty.csv");
//
//        // Adding current workers
//        JsonObject jsonAdd1 = new JsonObject();
//        jsonAdd1.addProperty("id", 123456789);
//        jsonAdd1.addProperty("name", "JohnDoe");
//        jsonAdd1.addProperty("address", "123 Main St");
//        jsonAdd1.addProperty("bank_account", 123456);
//        jsonAdd1.addProperty("hourly_salary", 25.0);
//        jsonAdd1.addProperty("vacation_days", 10);
//        jsonAdd1.addProperty("job_type", "Full_time_job");
//        jsonAdd1.addProperty("branch_num", 1);
//        jsonAdd1.addProperty("roles", "Shift_manager");
//        jsonAdd1.addProperty("job_status", true);  // Current worker
//        wc.add_worker(jsonAdd1);
//
//        // Adding past workers
//        JsonObject jsonAdd2 = new JsonObject();
//        jsonAdd2.addProperty("id", 987654321);
//        jsonAdd2.addProperty("name", "JaneSmith");
//        jsonAdd2.addProperty("address", "456 Elm St");
//        jsonAdd2.addProperty("bank_account", 654321);
//        jsonAdd2.addProperty("hourly_salary", 30.0);
//        jsonAdd2.addProperty("vacation_days", 15);
//        jsonAdd2.addProperty("job_type", "Part_time_job");
//        jsonAdd2.addProperty("branch_num", 2);
//        jsonAdd2.addProperty("roles", "Cashier");
//        jsonAdd2.addProperty("job_status", false);  // Past worker
//        wc.add_worker(jsonAdd2);
//
//        ManagerPresentation mp = new ManagerPresentation(wc, null, null, null, null, null, null);
//
//        // Capture the output
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PrintStream originalOut = System.out;
//        System.setOut(new PrintStream(outputStream));
//
//        // Call the method to print past workers
//        mp.present_workers();
//
//        // Restore the original System.out
//        System.setOut(originalOut);
//
//        // Expected output
//        JsonArray expectedJsonArray = new JsonArray();
//
//        JsonObject expectedWorkerJson = new JsonObject();
//        expectedWorkerJson.addProperty("id", 987654321);
//        expectedWorkerJson.addProperty("name", "JaneSmith");
//        expectedWorkerJson.addProperty("address", "456 Elm St");
//        expectedWorkerJson.addProperty("bank_account", 654321);
//        expectedWorkerJson.addProperty("hourly_salary", 30.0);
//        expectedWorkerJson.addProperty("vacation_days", 15);
//        expectedWorkerJson.addProperty("job_type", "Part_time_job");
//        expectedWorkerJson.addProperty("branch_num", 2);
//        expectedWorkerJson.addProperty("roles", "[Cashier]");
//        expectedWorkerJson.addProperty("starting_day", "2024-06-04");  // Use actual date if available
//        expectedWorkerJson.addProperty("total_hours", 0.0);   // Assuming default value, replace with actual if available
//        expectedWorkerJson.addProperty("job_status", false);   // Past worker
//        expectedJsonArray.add(expectedWorkerJson);
//
//        // Actual output
//        String actualOutput = outputStream.toString().trim();
//
//        // Asserting the output
//        assertEquals(expectedJsonArray.toString(), actualOutput);
//    }

    @Test
    public void test_manager_const_submited() throws IOException {
        WorkerController wc = new WorkerController("empty.csv");

        // Adding workers
        JsonObject jsonAdd1 = new JsonObject();
        jsonAdd1.addProperty("id", 987654321);
        jsonAdd1.addProperty("name", "JaneSmith");
        jsonAdd1.addProperty("address", "456 Elm St");
        jsonAdd1.addProperty("bank_account", 654321);
        jsonAdd1.addProperty("hourly_salary", 30.0);
        jsonAdd1.addProperty("vacation_days", 15);
        jsonAdd1.addProperty("job_type", "Part_time_job");
        jsonAdd1.addProperty("branch_num", 2);
        jsonAdd1.addProperty("roles", "Cashier");
        wc.add_worker(jsonAdd1);

        JsonObject jsonAdd2 = new JsonObject();
        jsonAdd2.addProperty("id", 123456789);
        jsonAdd2.addProperty("name", "JohnDoe");
        jsonAdd2.addProperty("address", "123 Main St");
        jsonAdd2.addProperty("bank_account", 123456);
        jsonAdd2.addProperty("hourly_salary", 25.0);
        jsonAdd2.addProperty("vacation_days", 10);
        jsonAdd2.addProperty("job_type", "Full_time_job");
        jsonAdd2.addProperty("branch_num", 1);
        jsonAdd2.addProperty("roles", "Shift_manager");
        wc.add_worker(jsonAdd2);
        JsonObject json = new JsonObject();
        json.addProperty("id",987654321);
        JsonObject ajson = new JsonObject();
        ajson.addProperty("id",123456789);

        String str = new String("1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 0 1 0 0 1 0 0");
        byte[] inputBytes = str.getBytes();
        InputStream IS = new ByteArrayInputStream(inputBytes);
        System.setIn(IS);
        ShiftController shift_controler = new ShiftController();
        SubmitConstraints sc = new SubmitConstraints(wc, shift_controler);
        sc.WorkerConstraint(json);
        IS.reset();
        IS.skip(28);
        sc.WorkerConstraint(ajson);
        IS.reset();
        IS.skip(56);
        assertTrue(sc.Submit_Constraints());

    }




}

//    public void tests_as() throws FileNotFoundException {
//        HRManager hrmanager = new HRManager("hello",2,"hi",2);
//        ManagerPresentation presentation = new ManagerPresentation(hrmanager);
//        presentation.menu();
//    }