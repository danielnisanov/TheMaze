package Presentation;

import Domain.WorkerController;
import Domain.JobType;
import Domain.Role;
import com.google.gson.JsonObject;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AddWorker {
    static Scanner scanner = new Scanner(System.in);

    private WorkerController wc;

    public AddWorker(WorkerController wc) {

        this.wc = wc;
    }

    public void add_worker() {
        System.out.println("Please enter the id_number of the new worker:");
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
            // HRmenu; // Uncomment this when you add the menu
            return; // Exit the method if the ID number is incorrect
        }

        scanner.nextLine(); // Consume the newline left-over
        System.out.println("Enter his name:");
        String name = scanner.nextLine();
        // Check if the name is non-empty and only contains letters and spaces
        if (name.isEmpty() || !name.matches("[a-zA-Z\\s]+")) {
            System.out.println("The name is incorrect");
            return;
        }

        System.out.println("Enter his bank account number:");
        int bank_account = scanner.nextInt();
        // Check if the bank account number is positive
        if (bank_account <= 0) {
            System.out.println("The bank account number must be a positive number");
            return;
        }

        System.out.println("Enter his hourly salary:");
        double hourly_salary = scanner.nextDouble();
        // Check if the hourly salary is positive
        if (hourly_salary <= 0) {
            System.out.println("The hourly salary must be a positive number");
            return;
        }

        System.out.println("Enter the amount of vacation days:");
        int vacation_days = scanner.nextInt();
        // Check if the vacation days are non-negative
        if (vacation_days < 0) {
            System.out.println("The amount of vacation days cannot be negative");
            return;
        }

        scanner.nextLine(); // Consume the newline left-over
        System.out.println("Enter his job type: Full_time_job, Part_time_job, Works_contractor");
        JobType job_type = JobType.valueOf(scanner.nextLine());

        System.out.println("Enter his address:");
        String address = scanner.nextLine();
        if (address.isEmpty()) {
            System.out.println("The address cannot be empty");
            return;
        }

        System.out.println("Enter his branch:");
        int branch_num = scanner.nextInt();
        if (branch_num <= 0) {
            System.out.println("The branch number must be a positive number");
            return;
        }

        scanner.nextLine(); // Consume the newline left-over
        Set<Role> roles = new HashSet<>(); // Create a new HashSet to hold roles

        System.out.println("Enter his role: Storekeeper, Cashier, Sorter, Shift_manager, Guard");
        Role role = Role.valueOf(scanner.nextLine());
        roles.add(role);

        JsonObject json = new JsonObject();
        json.addProperty("id", id_num);
        json.addProperty("name", name);
        json.addProperty("address", address);
        json.addProperty("bank_account", bank_account);
        json.addProperty("hourly_salary", hourly_salary);
        json.addProperty("vacation_days", vacation_days);
        json.addProperty("job_type", job_type.toString());
        json.addProperty("branch_num", branch_num);
        json.addProperty("roles", role.toString());
        wc.add_worker(json);

        System.out.println("Worker added successfully to branch " + branch_num );


    }

    public void add_manager() {
        System.out.println("Please enter the name of the new manager:");
        String name = scanner.nextLine();
        // Check if the name is non-empty and only contains letters and spaces
        if (name.isEmpty() || !name.matches("[a-zA-Z\\s]+")) {
            System.out.println("The name is incorrect");
            return;
        }

        System.out.println("Enter his branch:");
        int branch_num = scanner.nextInt();
        if (branch_num <= 0) {
            System.out.println("The branch number must be a positive number");
            return;
        }

        System.out.println("Please enter the id_number of the new manager:");
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
            return; // Exit the method if the ID number is incorrect
        }

        JsonObject json = new JsonObject();
        json.addProperty("name", name);
        json.addProperty("branch_num", branch_num);
        json.addProperty("ID_number", id_num);
        wc.add_manager(json);

        System.out.println("Manager added successfully with details: " + json);
    }


}


