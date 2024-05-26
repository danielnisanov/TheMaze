
import java.util.Scanner;

public class ShiftController {
    private Constraints constraints;

    public ShiftController(Constraints constraints) {
        this.constraints = constraints;
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
}

