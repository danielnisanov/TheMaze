import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShiftController {
    private Constraints constraints;
    private Map<Role, Integer>[][] managerRequirements; // 2D array to store requirements

    @SuppressWarnings("unchecked")
    public ShiftController(Constraints constraints) {
        this.constraints = constraints;
        this.managerRequirements = new Map[7][2]; // Generic array creation
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 2; j++) {
                this.managerRequirements[i][j] = new HashMap<Role, Integer>();
            }
        }
    }


    public void submitConstraints(Scanner scanner, Worker worker) {
        System.out.println("Submit your constraints for the week.");
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] shifts = {"Morning", "Evening"};

        for (int day = 0; day < 7; day++) {
            for (int shift = 0; shift < 2; shift++) {
                System.out.print("Are you available on " + days[day] + " " + shifts[shift] + " shift? (true/false): ");
                boolean available = scanner.nextBoolean();
                constraints.setAvailability(worker, day, shift, available);
            }
        }
        System.out.println("Your availability has been updated.");
    }

    /**
     * Method to set the hr shift requirements for each role.
     */
    public void setShiftRequirements(Scanner scanner) {
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] shifts = {"Morning", "Evening"};

        for (int day = 0; day < 7; day++) {
            for (int shift = 0; shift < 2; shift++) {
                System.out.println("Enter requirements for " + days[day] + " " + shifts[shift] + " shift:");

                for (Role role : Role.values()) {
                    System.out.print("Number of " + role + ": ");
                    int count = scanner.nextInt();
                    managerRequirements[day][shift].put(role, count);
                }
            }
        }
        System.out.println("Shift requirements have been updated.");
    }

    /**
     * Method to get the hr shift requirements for a specific day and shift.
     */
    public Map<Role, Integer> getShiftRequirements(int day, int shift) {
        if (day < 0 || day >= 7 || shift < 0 || shift >= 2) {
            throw new IllegalArgumentException("Invalid day or shift.");
        }
        return managerRequirements[day][shift];
    }
}

