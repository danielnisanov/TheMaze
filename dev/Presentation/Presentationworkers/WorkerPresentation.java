package Presentation.Presentationworkers;

import Domain.Domainworkers.*;
import com.google.gson.JsonObject;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class WorkerPresentation {
    JsonObject json = new JsonObject();
    private Worker worker = null;

    private static Scanner scanner = new Scanner(System.in);
    private WorkerController worker_controler;
    private SubmitConstraints submitConstraints;


    public WorkerPresentation( WorkerController worker_controler, SubmitConstraints submitConstraints) {

        this.worker_controler = worker_controler;
        this.submitConstraints = submitConstraints;
    }

    public void WorkerMenu() {
        int choice;
        boolean isValid = false;
        int workerID = -1;
        while (!isValid) {
            System.out.print("Enter your worker ID number: ");
            workerID = scanner.nextInt();
            worker = worker_controler.getWorker(workerID);

            json.addProperty("id", workerID);

            if (worker_controler.IsWorker(json)) {
                isValid = true;
            } else {
                System.out.println("The worker ID number is incorrect, please try again.");
            }
        }

        // Continue with the menu options
        boolean exit = false;
        while (!exit) {
            System.out.println("Menu:");
            System.out.println("Please choose one of the following:");
            System.out.println("1. My global salary");
            System.out.println("2. My vacation days");
            System.out.println("3. My starting day");
            System.out.println("4. Submit my constraints");
            System.out.println("5. Present work arrangement");
            System.out.println("6. Display inventory management menu");
            System.out.println("7. Log out from the user");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Find_global_salary();
                    break;
                case 2:
                    Find_vaction_day();
                    break;
                case 3:
                    Find_Start_date();
                    break;
                case 4:
                    Submit_constraints(worker_controler.getBranch(worker_controler.getAllWorkers().get(workerID).getBranchNum()), workerID);
                    break;
                case 5:
                    Present_arrangement(worker.getbranch());
                    break;
                case 6:
                    //implement the call for the menu from Stock-Dev:Presentation:Menu
                    break;
                case 7:
                    exit = true;
                    System.out.println("Returning to main menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private void Submit_constraints(Branch branch, int workerId) {
        JsonObject workerIdJson = new JsonObject();
        workerIdJson.addProperty("id", workerId);
        if (!submitConstraints.WorkerConstraint(workerIdJson, branch).isEmpty()) {
            System.out.println("Constraints submitted successfully.");
        } else {
            System.out.println("Error occurred while submitting constraints.");
        }
    }

    private void Find_global_salary(){
        double salary;
        if((salary = worker_controler.Find_global_salary(json)) < 0)
        {
            System.out.println("Error occurred");
        }
        System.out.println("The global salary for you is:" + salary);
    }

    private void Find_vaction_day() {
        int vacationDays;
        if ((vacationDays = worker_controler.Find_vaction_day(json)) < 0) {
            System.out.println("Error occurred");
        } else {
            System.out.println("The number of vacation days for you is: " + vacationDays);
        }
    }

    private void Find_Start_date() {
        LocalDate startDate;
        if ((startDate = worker_controler.Find_Start_date(json)) == null) {
            System.out.println("Error occurred");
        } else {
            System.out.println("Your start date is: " + startDate.toString());
        }
    }

    private void Present_arrangement(Branch branch) {
        List<Shift> weeklyWorkArrangement = branch.get_Weekly_Work_Arrangement();
        for (Shift s : weeklyWorkArrangement) {
            System.out.println(s);
        }
    }
}