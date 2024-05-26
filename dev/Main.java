import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Worker_Controller workerController = new Worker_Controller();

        System.out.print("Enter your worker ID number: ");
        int workerID = scanner.nextInt();

        if (workerController.isWorker(workerID)) {
            displayMenu(scanner, workerController, workerID);
        } else {
            System.out.println("You are not a recognized worker in the system.");
        }

        scanner.close();
    }

    public static void displayMenu(Scanner scanner, Worker_Controller workerController, int workerID) {
        int choice;
        ShiftController shiftController = new ShiftController(workerController.getConstraints());

        do {
            System.out.println("Menu:");
            System.out.println("Please choose one of the following:");
            System.out.println("1. My global salary");
            System.out.println("2. My vacation days");
            System.out.println("3. My job type");
            System.out.println("4. My starting day");
            System.out.println("5. Update my bank account number");
            System.out.println("6. Submit my constraints");
            System.out.println("7. Manager options");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            Worker worker = workerController.getWorkerByID(workerID);

            switch (choice) {
                case 1:
                    double globalSalary = worker.Global_salary(worker.getHourly_salary(), worker.getTotal_hours());
                    System.out.println("Your global salary is: " + globalSalary);
                    break;
                case 2:
                    System.out.println("Your vacation days: " + worker.getVaction_days());
                    break;
                case 3:
                    System.out.println("Your job type: " + worker.getJob_type());
                    break;
                case 4:
                    System.out.println("Your starting day is: " + worker.getStarting_day());
                    break;
                case 5:
                    System.out.print("Enter your new bank account number: ");
                    int new_account_num = scanner.nextInt();
                    worker.update_account_num(new_account_num);
                    System.out.println("Your bank account number has been updated.");
                    break;
                case 6:
                    shiftController.submitConstraints(scanner, worker);
                    break;
                case 7:
                    // Add code to handle option 7
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println(); // Print a new line for better readability

        } while (choice != 4);
    }
}
