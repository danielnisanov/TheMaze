package Domain;

import java.util.List;

public class ShiftController {

    public ShiftController() {
    }

    public boolean add_worker_to_weekly_arrangement(Branch branch, Worker worker, String day, String shiftType, Role role) {
        int dayIndex = getDayIndex(day);
        int shiftIndex = getShiftIndex(shiftType);

        if (dayIndex == -1 || shiftIndex == -1) {
            return false; // Invalid day or shift
        }

        int shiftArrayIndex = dayIndex * 2 + shiftIndex;

        if (shiftArrayIndex < 0 || shiftArrayIndex >= branch.getWeeklyWorkArrangement().size()) {
            throw new IndexOutOfBoundsException("Index " + shiftArrayIndex + " out of bounds for length " + branch.getWeeklyWorkArrangement().size());
        }

        shift currentShift = branch.getWeeklyWorkArrangement().get(shiftArrayIndex);

        if (currentShift.workers_on_shift.contains(worker)) {
            return false; // Worker is already assigned to this shift
        }

        currentShift.workers_on_shift.add(worker);

        // Save a copy of the shift to history
        branch.add_shift(currentShift);

        double cur_total_hours = worker.getTotal_hours();
        worker.setTotal_hours(cur_total_hours + 8);
        return true;
    }

    public boolean is_shift_already_booked(int day, int shift, Branch branch) {
        int shiftArrayIndex = (day - 1) * 2 + shift;
        return branch.getWeeklyWorkArrangement().get(shiftArrayIndex).workers_on_shift.size() > 0;
    }

    private static int getDayIndex(String day) {
        switch (day) {
            case "Sunday":
                return 0;
            case "Monday":
                return 1;
            case "Tuesday":
                return 2;
            case "Wednesday":
                return 3;
            case "Thursday":
                return 4;
            case "Friday":
                return 5;
            case "Saturday":
                return 6;
            default:
                return -1; // Invalid day
        }
    }

    private static int getShiftIndex(String shift) {
        switch (shift) {
            case "Morning":
                return 0;
            case "Evening":
                return 1;
            default:
                return -1; // Invalid shift
        }
    }
}
