package Presentation;

import Domain.*;
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

        int day = getUserInput("Select day (1-7):", 1, 7) - 1;
        int shift = getUserInput("Select shift (0-1):", 0, 1);

        for (day=0; day< 7; day++) {
            for (shift = 0; shift < 2; shift++) {
                for (Role role : Role.values()) {
                    System.out.print("Enter the number of " + role + "s on " + days[day] + " at " + shifts[shift] + ": ");
                    int number = scanner.nextInt();

                    for (int i = 0; i < number; i++) {
                        List<Worker> available = wc.AvailableWorkers(days[day], shifts[shift], role, branch);

                        if (available.isEmpty()) {
                            System.out.println("No available workers for role " + role + " on " + days[day] + " at " + shifts[shift]);
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

                            if (sc.add_worker_to_weekly_arrangement(branch, selectedWorker, days[day], shifts[shift], role)) {
                                availableWorkersOnBranch.remove(selectedWorker);
                                workerAssigned = true;
                            } else {
                                System.out.println("Worker " + selectedWorker.getName() + " is already assigned to this shift. Please select another worker.");
                            }

                        }
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

        for (int day = 0; day < 7; day++) {
            for (int shift = 0; shift < 2; shift++) {
                while (true) {
                    System.out.println("Do you want to work on " + days[day] + " shift " + shifts[shift] + "?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");

                    int choice = getUserInput("", 1, 2);

                    if (choice == 1) {
                        break;
                    } else if (choice == 2) {
                        constraintMap.computeIfAbsent(days[day], k -> new ArrayList<>()).add(shifts[shift]);
                        break;
                    } else {
                        System.out.println("Invalid choice. Please select 1 for Yes or 2 for No.");
                    }
                }
            }
        }

        if (!constraintMap.isEmpty()) {
            wc.SetWorkerConstraints(constraintMap, workerIdJson);
        }
        return constraintMap;
    }


}
