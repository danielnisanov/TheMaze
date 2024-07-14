package Presentation.Presentationworkers;

import Domain.Domainworkers.*;
import com.google.gson.JsonObject;
import java.util.*;

public class SubmitConstraints {

    static Scanner scanner = new Scanner(System.in);
    private WorkerController wc;
    private ShiftController sc;

    public SubmitConstraints(WorkerController wc, ShiftController sc) {
        this.wc = wc;
        this.sc = sc;
    }

    public boolean Submit_Constraints(Branch branch) {
        if (branch.get_submission_days().contains(wc.getCurrentDate().getDayOfWeek().getValue())) {
            System.out.println("Can't close shifts today");
            return false;
        }

        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] shifts = {"Morning", "Evening"};

        while (true) {
            System.out.println("For which day do you want to build an arrangement:");
            for (int i = 0; i < days.length; i++) {
                System.out.println((i + 1) + ") " + days[i]);
            }
            System.out.println("8) I finished building the arrangement.");
            int dayChoice = getUserInput("Enter your choice (1-8):", 1, 8) - 1;

            if (dayChoice == 7) {
                break; // Exit loop if the user chooses "I finished building the arrangement."
            }

            String selectedDay = days[dayChoice];

            System.out.println("For which shift do you want to build a constraint:");
            System.out.println("1) Morning");
            System.out.println("2) Evening");
            int shiftChoice = getUserInput("Enter your choice (1-2):", 1, 2) - 1;

            String selectedShift = shifts[shiftChoice];

            // Clear current workers for the selected shift before assigning new ones
            if (!sc.clearShiftWorkers(branch, selectedDay, selectedShift)) {
                System.out.println("Failed to clear existing workers for " + selectedDay + " " + selectedShift + " shift.");
                return false;
            }

            int driverCount = 0;

            for (Role role : Role.values()) {
                int number;
                while (true) {
                    System.out.print("Enter the number of " + role + "s on " + selectedDay + " at " + selectedShift + ": ");
                    number = scanner.nextInt();
                    if (role == Role.Shift_manager && number == 0) {
                        System.out.println("Each shift must have a shift manager.");
                    } else {
                        break;
                    }
                }

                if (role == Role.Truck_driver) {
                    driverCount = number;
                }

                for (int i = 0; i < number; i++) {
                    List<Worker> available = wc.AvailableWorkers(selectedDay, selectedShift, role, branch);

                    if (available.isEmpty()) {
                        System.out.println("No available workers for role " + role + " on " + selectedDay + " at " + selectedShift);
                        return false;
                    }

                    List<Worker> availableWorkersOnBranch = new ArrayList<>(available);
                    boolean workerAssigned = false;

                    while (!workerAssigned) {
                        System.out.println("Please choose one of the following workers:");
                        for (int j = 0; j < availableWorkersOnBranch.size(); j++) {
                            System.out.println("(" + j + ") " + availableWorkersOnBranch.get(j).getName());
                        }

                        int choiceWorker = getUserInput("Enter your choice (0-" + (availableWorkersOnBranch.size() - 1) + "):", 0, availableWorkersOnBranch.size() - 1);
                        Worker selectedWorker = availableWorkersOnBranch.get(choiceWorker);

                        if (sc.add_worker_to_weekly_arrangement(branch, selectedWorker, selectedDay, selectedShift, role)) {
                            availableWorkersOnBranch.remove(selectedWorker);
                            workerAssigned = true;
                        } else {
                            System.out.println("Worker " + selectedWorker.getName() + " is already assigned to this shift. Please select another worker.");
                        }
                    }
                }
            }

            // Ensure that a warehouseman is selected if there are any truck drivers
            if (driverCount > 0) {
                boolean warehousemanAssigned = false;

                while (!warehousemanAssigned) {
                    List<Worker> availableWarehousemen = wc.AvailableWorkers(selectedDay, selectedShift, Role.Storekeeper, branch);

                    if (availableWarehousemen.isEmpty()) {
                        System.out.println("No available Storekeeper for " + selectedDay + " at " + selectedShift);
                        return false;
                    }

                    System.out.println("Since there are truck drivers, you must select a Storekeeper:");
                    for (int j = 0; j < availableWarehousemen.size(); j++) {
                        System.out.println("(" + j + ") " + availableWarehousemen.get(j).getName());
                    }

                    int choiceWarehouseman = getUserInput("Enter your choice (0-" + (availableWarehousemen.size() - 1) + "):", 0, availableWarehousemen.size() - 1);
                    Worker selectedWarehouseman = availableWarehousemen.get(choiceWarehouseman);

                    if (sc.add_worker_to_weekly_arrangement(branch, selectedWarehouseman, selectedDay, selectedShift, Role.Storekeeper)) {
                        warehousemanAssigned = true;
                    } else {
                        System.out.println("Storekeeper " + selectedWarehouseman.getName() + " is already assigned to this shift. Please select another Storekeeper.");
                    }
                }
            }
        }

        return true;
    }


    private int getUserInput(String prompt, int min, int max) {
        int input;
        do {
            System.out.println(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
                scanner.next();
            }
            input = scanner.nextInt();
        } while (input < min || input > max);
        return input;
    }

    public Map<String, List<String>> WorkerConstraint(JsonObject workerIdJson, Branch branch) {
        if (branch == null) {
            System.out.println("Branch is null. Cannot submit constraints.");
            return new HashMap<>();
        }

        if (!branch.get_submission_days().contains(wc.getCurrentDate().getDayOfWeek().getValue())) {
            System.out.println("Can't submit shifts today");
            return new HashMap<>();
        }

        Map<String, List<String>> constraintMap = new HashMap<>();
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] shifts = {"Morning", "Evening"};

        while (true) {
            System.out.println("Choose a day for which you have a constraint:");
            for (int i = 0; i < days.length; i++) {
                System.out.println((i + 1) + ") " + days[i]);
            }
            System.out.println("8) I have no more constraints");

            int dayChoice = getUserInput("Enter your choice (1-8):", 1, 8) - 1;

            if (dayChoice == 7) {
                break; // Exit loop if the user chooses "I have no more constraints"
            }

            String selectedDay = days[dayChoice];

            System.out.println("When do you have a constraint?");
            System.out.println("1) Morning");
            System.out.println("2) Evening");
            System.out.println("3) Both");

            int shiftChoice = getUserInput("Enter your choice (1-3):", 1, 3);

            switch (shiftChoice) {
                case 1:
                    constraintMap.computeIfAbsent(selectedDay, k -> new ArrayList<>()).add(shifts[0]);
                    break;
                case 2:
                    constraintMap.computeIfAbsent(selectedDay, k -> new ArrayList<>()).add(shifts[1]);
                    break;
                case 3:
                    constraintMap.computeIfAbsent(selectedDay, k -> new ArrayList<>()).add(shifts[0]);
                    constraintMap.computeIfAbsent(selectedDay, k -> new ArrayList<>()).add(shifts[1]);
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }

        if (!constraintMap.isEmpty()) {
            wc.SetWorkerConstraints(constraintMap, workerIdJson);
        }
        return constraintMap;
    }



}
