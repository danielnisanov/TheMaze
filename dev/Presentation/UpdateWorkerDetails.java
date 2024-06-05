package Presentation;

import Domain.WorkerController;
import Domain.JobType;
import com.google.gson.JsonObject;

import java.util.Scanner;

public class UpdateWorkerDetails {
    private static WorkerController workerController;
    static Scanner scanner = new Scanner(System.in);
    private WorkerController wc;

    UpdateWorkerDetails(WorkerController wc)
    {
        this.wc = wc;
    }
    public void Update_Job_Type() {
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
        System.out.println("Enter his job type: Full_time_job, Part_time_job, Works_contractor");
        JobType job_type = JobType.valueOf(scanner.next());

        JsonObject json = new JsonObject();
        json.addProperty("id", id_num);
        json.addProperty("job_type", job_type.toString());
        wc.Update_job_type(json);
        if (wc.update_job_type_success(json)) {
            System.out.println("Update job type success");
        }
        System.out.println("Update job type failed");

    }

    public void Update_Salary() {
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
        System.out.println("Update salary failed");
        // HRmenu();
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
        System.out.println("Update branch failed");
        // HRmenu();
    }

    public void UpdateBankAccountNum(){
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
        System.out.println("Enter bank account number:");
        int bank_num = scanner.nextInt();
        if (bank_num <= 0) {
            System.out.println("The bank account number must be a positive number");
        }
        JsonObject json = new JsonObject();
        json.addProperty("id", id_num);
        json.addProperty("bank_num", bank_num);

        wc.Update_bank_account_num(json);
        if (wc.Update_Bank_Account_Num_success(json)) {
            System.out.println("Update bank account number success");
        }
        System.out.println("Update bank account number failed");
        // HRmenu();
    }

}