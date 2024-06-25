package Presentation;

import Domain.Branch;
import Domain.WorkerController;
import com.google.gson.JsonObject;
import java.util.Scanner;

public class EmploymentTermination {
    private WorkerController workerController;
    private static Scanner scanner = new Scanner(System.in);

    public EmploymentTermination(WorkerController workerController) {
        this.workerController = workerController;
    }

    public void employment_Termination(Branch branch) {
        System.out.println("Enter the worker ID");
        int id_num = scanner.nextInt();

        if(!branch.is_worker_in_branch((id_num)))
        {
            System.out.println("this worker is not in your branch");
            return;
        }

        JsonObject json = new JsonObject();
        json.addProperty("id", id_num);
        if (workerController.Employment_termination(json)) {
            System.out.println("Employment termination successful");
        } else {
            System.out.println("Employment termination failed");
        }
    }
}
