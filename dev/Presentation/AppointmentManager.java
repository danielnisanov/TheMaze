package Presentation;

import Domain.WorkerController;
import com.google.gson.JsonObject;

import java.util.Scanner;

public class AppointmentManager {
    static Scanner scanner = new Scanner(System.in);
    private WorkerController wc;

    AppointmentManager(WorkerController wc)
    {
        this.wc = wc;
    }

    public void Appointment_Manager(){
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
        wc.appointment_manager(json);
        if (wc.Appointment_success(json)) {
            System.out.println("Appointment success");
        }
        else {
            System.out.println("Appointment failed");
        }
    }
}