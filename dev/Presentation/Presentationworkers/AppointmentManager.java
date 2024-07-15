package Presentation.Presentationworkers;
import Domain.Domainworkers.*;

import com.google.gson.JsonObject;

import java.util.Scanner;

public class AppointmentManager {
    static Scanner scanner = new Scanner(System.in);
    private WorkerController wc;

    public AppointmentManager(WorkerController wc)
    {
        this.wc = wc;
    }

    public void Appointment_Manager(Branch branch){
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
        JsonObject json = new JsonObject();
        json.addProperty("id", id_num);

        if (wc.appointment_manager(json)) {
            System.out.println("Appointment success");
        }
        else {
            System.out.println("Appointment failed");
        }
    }
}