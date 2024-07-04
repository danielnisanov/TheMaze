package Presentation;

import Domain.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Scanner;


public class ManagerPresentation {
    private static final Scanner scanner = new Scanner(System.in);
    private HRManager manager = null;
    private final WorkerController worker_controler;
    private AppointmentManager appointmentManager;
    private AddWorker addWorker;
    private EmploymentTermination emplymenttermination;
    private UpdateWorkerDetails updatrDetails;
    private SubmitConstraints submitConstraints;
    private ShiftController shiftController;

    public ManagerPresentation(WorkerController worker_controler, AppointmentManager appointmentManager, AddWorker addWorker,
                               EmploymentTermination emplymenttermination, UpdateWorkerDetails updatrDetails, SubmitConstraints submitConstraints, ShiftController shiftController) {

        this.worker_controler = worker_controler;
        this.appointmentManager = appointmentManager;
        this.addWorker = addWorker;
        this.emplymenttermination = emplymenttermination;
        this.updatrDetails = updatrDetails;
        this.submitConstraints = submitConstraints;
        this.shiftController = shiftController;
    }


    public void menu() {
        int choice;
        while (manager == null) {
            System.out.print("Enter your ID:");

            int id = scanner.nextInt();

            manager = worker_controler.get_Manager(id);
            if (manager == null) {
                System.out.print("Invalid ID, please insert a valid ID");
            }
        }
        scanner.nextLine();
        System.out.print("Enter HR manager password: ");
        String password = scanner.nextLine();

        if (manager.authenticate(password)) {
            System.out.println("Access granted.");
            boolean exit = false;
            while (!exit) {
                System.out.println("HR Manager Menu:");
                System.out.println("Please choose one of the following:");
                System.out.println("1. List of all the workers");
                System.out.println("2. Appointment of an additional shift manager");
                System.out.println("3. Add new worker");
                System.out.println("4. Termination of an employee's employment period");
                System.out.println("5. Changing a worker's salary");
                System.out.println("6. Update job type for a worker");
                System.out.println("7. Update brunch number for worker");
                System.out.println("8. Creating a work arrangement");
                System.out.println("9. Add new HR manager");
                System.out.println("10. Update bank account number for worker");
                System.out.println("11. Present work arrangement");
                System.out.println("12. Present past workers");
                System.out.println("13. Change password");
                System.out.println("14. Present past shifts ");
                System.out.println("15. present a worker");
                System.out.println("16. End of work day");
                System.out.println("17. Log out from the user");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();


                switch (choice) {
                    case 1:
                        present_workers(manager.getBranch());
                        break;
                    case 2:
                        Appointment_Manager(manager.getBranch());
                        break;
                    case 3:
                        add_worker(manager.getBranch());
                        break;
                    case 4:
                        Employment_Termination(manager.getBranch());
                        break;
                    case 5:
                        Update_Salary(manager.getBranch());
                        break;
                    case 6:
                        Update_Job_Type(manager.getBranch());
                        break;
                    case 7:
                        Update_branch();
                        break;
                    case 8:
                        Create_work_arrangement(manager.getBranch());
                        break;
                    case 9:
                        add_new_manager();
                        break;
                    case 10:
                        UpdateBankAccountNum(manager.getBranch());
                        break;
                    case 11:
                        Present_arrangement(manager.branch);
                        break;
                    case 12:
                        print_past_workers(manager.getBranch());
                        break;
                    case 13:
                        changePassword();
                        break;
                    case 14:
                        past_shifts(manager.getBranch());
                        break;
                    case 15:
                        Present_Worker(manager.getBranch());
                        break;
                    case 16:
                        worker_controler.finishDay();
                        break;
                    case 17:
                        exit = true;
                        System.out.println("Exiting.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose again.");
                        break;
                }
            }
        } else {
            System.out.println("Invalid password. Access denied.");
        }
    }



    private void past_shifts(Branch branch){
        JsonArray jsonArray = shiftController.present_Shift_History(branch);
        for (JsonElement workerElement : jsonArray) {
            System.out.println(workerElement.toString());
        }
    }

    private void changePassword() {
        scanner.nextLine();  // Consume the leftover newline
        System.out.print("Enter the new password: ");
        String newPassword = scanner.nextLine();
        int id = manager.getID_number();
        if (worker_controler.ChangePassword(id, "password", newPassword)) {
            System.out.println("Password updated successfully.");
        }
    }

    private void Present_Worker(Branch branch) {
        JsonObject json = worker_controler.present_worker(updatrDetails.PresentWorker(branch));
        System.out.println("  \"id\": " + json.get("id").getAsString() + ",");
        System.out.println("  \"name\": \"" + json.get("name").getAsString() + "\",");
        System.out.println("  \"address\": \"" + json.get("address").getAsString() + "\",");
        System.out.println("  \"bank_account\": " + json.get("bank_account").getAsInt() + ",");
        System.out.println("  \"hourly_salary\": " + json.get("hourly_salary").getAsDouble() + ",");
        System.out.println("  \"vacation_days\": " + json.get("vacation_days").getAsInt() + ",");
        System.out.println("  \"job_type\": \"" + json.get("job_type").getAsString() + "\",");
        System.out.println("  \"branch_num\": " + json.get("branch_num").getAsInt() + ",");
        System.out.println("  \"roles\": \"" + json.get("roles").getAsString() + "\",");
        System.out.println("  \"starting_day\": \"" + json.get("starting_day").getAsString() + "\",");
        System.out.println("  \"total_hours\": " + json.get("total_hours").getAsDouble() + ",");
        System.out.println("  \"job_status\": " + json.get("job_status").getAsBoolean());
    }

    private void UpdateBankAccountNum(Branch branch) {
        updatrDetails.UpdateBankAccountNum(branch);
    }

    private void present_workers(Branch branch) {
        JsonArray jsonArray = worker_controler.present_workers(branch);
        for (JsonElement workerElement : jsonArray) {
            System.out.println(workerElement.toString());
        }
    }

    private void Appointment_Manager(Branch branch) {

        appointmentManager.Appointment_Manager(branch);
    }

    private void add_worker(Branch branch) {

        addWorker.Add_Worker(branch);
    }

    private void Employment_Termination(Branch branch) {

        emplymenttermination.Employment_Termination(branch);
    }

    private void Update_Salary(Branch branch) {

        updatrDetails.updateSalary(branch);
    }

    private void Update_Job_Type(Branch branch) {


        updatrDetails.updateJobType(branch);
    }

    private void Update_branch() {
        updatrDetails.updateBranch();
    }

    private void Create_work_arrangement(Branch branch) {
        submitConstraints.Submit_Constraints(branch);
    }

    private void add_new_manager() {
        addWorker.AddManager();
    }

    public void print_past_workers(Branch branch){
        JsonArray jsonArray = worker_controler.present_past_workers(branch);
        for (JsonElement workerElement : jsonArray) {
            System.out.println(workerElement.toString());
        }
    }

    private void Present_arrangement(Branch branch) {
        List<Shift> weeklyWorkArrangement = branch.get_Weekly_Work_Arrangement();
        for (Shift s : weeklyWorkArrangement) {
            System.out.println(s);
        }
    }


    }



