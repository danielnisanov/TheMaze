package Domain;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


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

        Shift currentShift = branch.getWeeklyWorkArrangement().get(shiftArrayIndex);

        if (currentShift.workers_on_shift.contains(worker)) {
            return false; // Worker is already assigned to this shift
        }

        currentShift.workers_on_shift.add(worker);
        if (!branch.getShiftHistory().contains(currentShift)) {
            branch.getShiftHistory().add(currentShift);
        }


        double cur_total_hours = worker.getTotal_hours();
        worker.setTotal_hours(cur_total_hours + 8);
        return true;
    }

    public boolean clearShiftWorkers(Branch branch, String day, String shiftType) {
        int dayIndex = getDayIndex(day);
        int shiftIndex = getShiftIndex(shiftType);

        if (dayIndex == -1 || shiftIndex == -1) {
            return false; // Invalid day or shift
        }

        int shiftArrayIndex = dayIndex * 2 + shiftIndex;

        if (shiftArrayIndex < 0 || shiftArrayIndex >= branch.getWeeklyWorkArrangement().size()) {
            throw new IndexOutOfBoundsException("Index " + shiftArrayIndex + " out of bounds for length " + branch.getWeeklyWorkArrangement().size());
        }

        Shift currentShift = branch.getWeeklyWorkArrangement().get(shiftArrayIndex);
        currentShift.workers_on_shift.clear();
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

    public JsonArray presentShiftHistory(Branch branch) {
        JsonArray jsonArray = new JsonArray();
        for (Shift shift : branch.getShiftHistory()) {
            JsonObject jsonShift = new JsonObject();
            jsonShift.addProperty("shift_date", shift.getShift_date());
            jsonShift.addProperty("shift_type", shift.getShift_type());
            JsonArray workersArray = new JsonArray();
            for (Worker worker : shift.getWorkers_on_shift()) {
                JsonObject jsonWorker = new JsonObject();
                jsonWorker.addProperty("worker_name", worker.getName());
                jsonWorker.addProperty("worker_id", worker.getID_number());
                // Add more worker attributes as needed
                workersArray.add(jsonWorker);
            }
            jsonShift.add("workers_on_shift", workersArray);
            jsonArray.add(jsonShift);
        }
        return jsonArray;
    }

}


