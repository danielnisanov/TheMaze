package Presentation;

import Domain.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.time.LocalDate;
import java.util.*;
import java.util.List;


public class SubmitConstraints {

    static Scanner scanner = new Scanner(System.in);
    private WorkerController wc;
    ShiftController sc;

    public SubmitConstraints(WorkerController wc, ShiftController sc)
    {
        this.wc = wc;
        this.sc = sc;
    }

    public boolean Submit_Constraints(Branch branch) {
        if(branch.get_submittion_days().contains(LocalDate.now().getDayOfWeek().getValue()))
        {
            System.out.println("Can't close shifts today");
            return false;
        }
        JsonArray constraintsArray = new JsonArray();
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] shifts = {"Morning", "Evening"};
        System.out.println("select day(1-7):");
        int day = scanner.nextInt();
        System.out.println("select shift(0-1):");
        int shift  = scanner.nextInt();
        for (Role role : Role.values()) {
            System.out.print("Enter the number of " + role + "s on " + days[day] + " at " + shifts[shift] + ": ");
            int number = scanner.nextInt();
            for (int i = 0; i < number; i++) {
                List<Worker> available = wc.AvailableWorkers(days[day], shifts[shift], role);
                System.out.println("Please choose one of the following workers:");
                int worker_number = 0;
                for (Worker worker : available) {
                    System.out.println("(" +worker_number + ") " + worker.getName());
                    worker_number++;
                }
                int Choice_worker = scanner.nextInt();
                Worker selectedWorker = available.get(Choice_worker);
                if (!sc.add_worker_to_weekly_arrangement(branch,selectedWorker, days[day - 1], shifts[shift], role)) {
                    System.out.println("Worker " + selectedWorker.getName() + " is already assigned to this shift.");
                    return false;
                }


            }
        }
        return true;
    }

    // Creating worker constraints
    public Map<String, List<String>> WorkerConstraint(JsonObject json, Branch branch) {
        if (branch == null) {
            System.out.println("Branch is null. Cannot submit constraints.");
            return new HashMap<>(); // Return an empty map if branch is null
        }

        if (!branch.get_submittion_days().contains(LocalDate.now().getDayOfWeek().getValue())) {
            System.out.println("Can't submit shifts today");
            return new HashMap<>(); // Return an empty map instead of null
        }

        int id = json.get("id").getAsInt();
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> constraintMap = new HashMap<>();
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] shifts = {"Morning", "Evening"};

        for (int day = 0; day < 7; day++) {
            for (int shift = 0; shift < 2; shift++) {
                while (true) {
                    System.out.println("Do you want to work on " + days[day] + " shift " + shifts[shift] + "?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");

                    int choice = scanner.nextInt();

                    if (choice == 1) {
                        break; // Available for work, so no need to add to constraint map
                    } else if (choice == 2) {
                        constraintMap.computeIfAbsent(days[day], k -> new ArrayList<>()).add(shifts[shift]);
                        break;
                    } else {
                        System.out.println("Invalid choice. Please select 1 for Yes or 2 for No.");
                    }
                }
            }
        }
        return constraintMap;
    }

}
