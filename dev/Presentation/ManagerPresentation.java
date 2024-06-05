package Presentation;

import Domain.HRManager;
import Domain.WorkerController;

import java.util.ArrayList;
import java.util.Scanner;


public class ManagerPresentation {
    private static Scanner scanner = new Scanner(System.in);

    private HRManager manager = null;
    private WorkerController worker_controler;
    private AppointmentManager appointmentManager;
    private AddWorker addWorker;
    private EmploymentTermination emplymenttermination;
    private UpdateWorkerDetails updatrDetails;
    private SubmitConstraints submitConstraints;


    public ManagerPresentation(WorkerController worker_controler, AppointmentManager appointmentManager,
                               AddWorker addWorker, EmploymentTermination emplymenttermination, UpdateWorkerDetails updatrDetails, SubmitConstraints submitConstraints) {

        this.worker_controler = worker_controler;
        this.appointmentManager = appointmentManager;
        this.addWorker = addWorker;
        this.emplymenttermination = emplymenttermination;
        this.updatrDetails = updatrDetails;
        this.submitConstraints = submitConstraints;
    }


    public void menu() {
        int choice;
        while (manager == null) {
            System.out.print("Enter your ID:");

            int id = scanner.nextInt();

            manager = worker_controler.getManager(id);
            if (manager == null) {
                System.out.print("Invalid ID, please insert a valid ID");
            }
        }
        scanner.nextLine();
        System.out.print("Enter HR manager password: ");
        String password = scanner.nextLine();

        if (manager.authenticate(password)) {
            System.out.println("Access granted. HR menu options:");

            System.out.println("HR Manager Menu:");
            System.out.println("Please choose one of the following:");
            System.out.println("1. List of all the workers in the company"); //
            System.out.println("2. Appointment of an additional shift manager"); //
            System.out.println("3. Add new worker");
            System.out.println("4. Termination of an employee's employment period");//
            System.out.println("5. Changing a worker's salary");//
            System.out.println("6. Update job type for a worker");//
            System.out.println("7. Update brunch number for worker");//
            System.out.println("8. Add a new Branch");
            System.out.println("9. Creating a work arrangement");//
            System.out.println("10. Add new HR manager");
            System.out.println("11. Update bank account number for worker");//
            System.out.println("12. Present work arrangement");
            System.out.println("13. Show me past workers");
            System.out.println("14. Exit");
            System.out.print("Enter your choice: ");
            // Here you would add the logic for handling each menu option

        } else {
            System.out.println("Invalid password. Access denied.");
            // Call the main menu method from MainMenu
//            Main.mainMenu();   //add this when i add the main
        }

        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                present_workers();
                break;
            case 2:
                Appointment_Manager();
                break;
            case 3:
                add_worker(); // להחזיר סטטוס
                break;
            case 4:
                Employment_Termination();
                break;
            case 5:
                Update_Salary();
                break;
            case 6:
                Update_Job_Type();
                break;
            case 7:
                Update_branch();
                break;
            case 8:
                break;
            case 9:
                Create_work_arrangement();
                break;
            case 10:
                break;
            case 11:
                UpdateBankAccountNum();
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;


        }
    }

    private void UpdateBankAccountNum() {
        updatrDetails.UpdateBankAccountNum();
    }

    private void present_workers() {
        worker_controler.present_workers();
    }

    private void Appointment_Manager() {
        appointmentManager.Appointment_Manager();
    }

    private void add_worker() {
        addWorker.add_worker();
    }

    private void Employment_Termination() {
        emplymenttermination.Employment_Termination();
    }

    private void Update_Salary() {
        updatrDetails.Update_Salary();
    }

    private void Update_Job_Type() {
        updatrDetails.Update_Job_Type();
    }

    private void Update_branch() {
        updatrDetails.Update_branch();
    }

    private void Create_work_arrangement() {
        submitConstraints.Submit_Constraints();
    }
}