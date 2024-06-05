package Presentation;

import Domain.WorkerController;
import com.google.gson.JsonObject;

import java.util.Scanner;

public class EmploymentTermination {
    private static WorkerController workerController;
    static Scanner scanner = new Scanner(System.in);

    private WorkerController wc;

    EmploymentTermination(WorkerController wc)
    {
        this.wc = wc;
    }

    public void Employment_Termination(){
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
        JsonObject json = new JsonObject();
        json.addProperty("id", id_num);
        wc.Employment_termination(json);
        if (workerController.termination_success(json)) {
            System.out.println("Employment Termination success");
        }
        System.out.println("EmploymentTermination failed");
        // HRmenu();
    }
}