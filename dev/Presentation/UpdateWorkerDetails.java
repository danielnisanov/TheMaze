package Presentation;

import Domain.*;
import com.google.gson.JsonObject;

import java.util.Scanner;

public class UpdateWorkerDetails {
    private static WorkerController workerController;
    static Scanner scanner = new Scanner(System.in);
    private WorkerController wc;
    HRManager hrManager;
    Worker worker;

    public UpdateWorkerDetails(WorkerController wc)
    {
        this.wc = wc;
    }
    public void Update_Job_Type(Branch branch) {
        System.out.println("Enter the worker ID");
        int id_num = scanner.nextInt();
        if(!branch.is_worker_in_branch((id_num)))
        {
            System.out.println("this worker is not in your branch");
            return;
        }
        // Check if the ID is a 9-digit number
        int temp_id = id_num;
        int sum = 0;
        while (temp_id != 0) {
            temp_id /= 10;
            sum++;
        }

        if (sum != 9) {
            System.out.println("The id number is incorrect");
            // HRmenu(); // Uncomment this when you add the menu
            return; // Exit the method if the ID number is incorrect
        }
        System.out.println("Enter his job type: Full_time_job, Part_time_job, Works_contractor");
        JobType job_type = JobType.valueOf(scanner.next());

        JsonObject json = new JsonObject();
        json.addProperty("id", id_num);
        json.addProperty("job_type", job_type.toString());
        wc.Update_job_type(json);
        if (wc.update_job_type_success(json)) {
            System.out.println("Update job type success");
        }
        else{
            System.out.println("Update job type failed");
        }


    }

    public JsonObject PresentWorker(Branch branch) {
        System.out.println("Enter the worker ID");
        int id_num = scanner.nextInt();
        if (!branch.is_worker_in_branch((id_num))) {
            System.out.println("this worker is not in your branch");
            return null;
        }
        // Check if the ID is a 9-digit number

        int temp_id = id_num;
        int sum = 0;
        while (temp_id != 0) {
            temp_id /= 10;
            sum++;
        }

        if (sum != 9) {
            System.out.println("The id number is incorrect");
            // HRmenu(); // Uncomment this when you add the menu
            return null; // Exit the method if the ID number is incorrect
        }
        JsonObject json = new JsonObject();
        json.addProperty("id", id_num);
        return wc.present_worker(json);
    }



    public void Update_Salary(Branch branch) {
        System.out.println("Enter the worker ID");
        int id_num = scanner.nextInt();

        if(!branch.is_worker_in_branch(id_num))
        {
            System.out.println("this worker is not in your branch");
            return;
        }

        // Check if the ID is a 9-digit number
        int temp_id = id_num;
        int sum = 0;
        while (temp_id != 0) {
            temp_id /= 10;
            sum++;
        }

        if (sum != 9) {
            System.out.println("The id number is incorrect1");
            // HRmenu(); // Uncomment this when you add the menu
            return; // Exit the method if the ID number is incorrect
        }

        System.out.println("Enter the new salary");
        int hourly_salary = scanner.nextInt();
        if (hourly_salary <= 0) {
            System.out.println("The hourly salary must be a positive number");
            return;
        }

        JsonObject json = new JsonObject();
        json.addProperty("id", id_num);
        json.addProperty("hourly_salary", hourly_salary);

        wc.update_salary(json);
        if (wc.update_salary_success(json)) {
            System.out.println("Update salary success");
        }
        else{
            System.out.println("Update salary failed");
        }

    }

    public void Update_branch() {
        System.out.println("Enter the worker ID");
        int id_num = scanner.nextInt();


        // Check if the ID is a 9-digit number
        int temp_id = id_num;
        int sum = 0;
        while (temp_id != 0) {
            temp_id /= 10;
            sum++;
        }

        if (sum != 9) {
            System.out.println("The id number is incorrect");
            // HRmenu(); // Uncomment this when you add the menu
            return; // Exit the method if the ID number is incorrect
        }

        System.out.println("Enter branch number:");
        int branch_num = scanner.nextInt();
        if (branch_num <= 0) {
            System.out.println("The branch number must be a positive number");
            return;
        }

        JsonObject json = new JsonObject();
        json.addProperty("id", id_num);
        json.addProperty("branch_num", branch_num);

        wc.Update_Branch(json);
        if (wc.update_branch_success(json)) {
            System.out.println("Update branch success");
        }
        else {
            System.out.println("Update branch failed");
        }
    }

    public void UpdateBankAccountNum(Branch branch) {
        System.out.println("Enter the worker ID");
        String id_num = scanner.next();
        if(!branch.is_worker_in_branch(Integer.parseInt(id_num)))
        {
            System.out.println("this worker is not in your branch");
            return;
        }

        // Check if the ID is a 9-digit number
        if (id_num.length() != 9) {
            System.out.println("The id number is incorrect");
            return; // Exit the method if the ID number is incorrect
        }

        // Convert the string to an integer after validation
        int id_num_int = Integer.parseInt(id_num);

        if(!branch.is_worker_in_branch(id_num_int))
        {
            return;
        }

        // Ask for the new bank account number
        System.out.println("Enter the new bank account number");
        int new_bank_account_num = scanner.nextInt();

        // Create a JSON object to send to the WorkerController
        JsonObject json = new JsonObject();
        json.addProperty("id", id_num_int);
        json.addProperty("bank_account", new_bank_account_num);

        // Call the controller method to update the bank account number
        if (wc.Update_bank_account_num(json)) {
            System.out.println("Bank account number updated successfully.");
        } else {
            System.out.println("Failed to update bank account number. Please check the worker ID and try again.");
        }
    }




}