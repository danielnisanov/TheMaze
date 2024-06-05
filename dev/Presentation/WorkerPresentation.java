package Presentation;

import Domain.WorkerController;
import com.google.gson.JsonObject;

import java.util.Scanner;

public class WorkerPresentation {
    JsonObject json = new JsonObject();

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
            json.addProperty("id", workerID);
            if (worker_controler.IsWorker(json)) {
                isValid = true;
            } else {
                System.out.println("The worker ID number is incorrect, please try again.");
            }
        }

        // Continue with the menu options
        while (true) {
            System.out.println("Menu:");
            System.out.println("Please choose one of the following:");
            System.out.println("1. My global salary");
            System.out.println("2. My vacation days");
            System.out.println("3. My starting day");
            System.out.println("4. Submit my constraints");
            System.out.println("0. Exit");
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
                    Submit_constraints();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private void Submit_constraints() {
        SubmitConstraints.WorkerConstraint(json);
    }

    private void Find_global_salary(){
        double salary;
        if((salary = worker_controler.Find_global_salary(json)) < 0)
        {
            System.out.println("Error occurred");
        }
        System.out.println("The global salary for you is:" + salary);
    }

    private void Find_vaction_day(){
        worker_controler.Find_vaction_day(json);

    }

    private void Find_Start_date(){
        worker_controler.Find_Start_date(json);
    }
}