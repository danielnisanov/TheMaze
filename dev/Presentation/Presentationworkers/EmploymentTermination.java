package Presentation.Presentationworkers;
import Domain.Domainworkers.*;

import com.google.gson.JsonObject;
import java.util.Scanner;

public class EmploymentTermination {
    private WorkerController workerController;
    private static Scanner scanner = new Scanner(System.in);

    public EmploymentTermination(WorkerController workerController) {
        this.workerController = workerController;
    }

    public void Employment_Termination(Branch branch) {
        System.out.println("Enter the worker ID");
        int id_num = scanner.nextInt();


        JsonObject json = new JsonObject();
        json.addProperty("id", id_num);
        if (workerController.Employment_termination(json)) {
            System.out.println("Employment termination successful");
        } else {
            System.out.println("Employment termination failed");
        }
    }
}