package Domain;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShiftController {

    public ShiftController()
    {

    }

    public boolean add_worker_to_weekly_arrangement(Branch branch,Worker worker, String day, String shiftType, Role role) {
        int dayIndex = getDayIndex(day);
        int shiftIndex = getShiftIndex(shiftType);

        if (dayIndex == -1 || shiftIndex == -1) {
            //System.out.println("Invalid day or shift.");
            return false;
        }


        shift shift = branch.getWeeklyWorkArrangement().get((dayIndex - 1) * 2 + shiftIndex);
        if (shift.workers_on_shift.contains(worker)) {
            return false;
        }

        shift.workers_on_shift.add(worker);

        // Save a copy of the shift to history
        branch.add_shift(shift);

        double cur_total_hours = worker.getTotal_hours();
        worker.setTotal_hours(cur_total_hours + 8);
        return true;
    }

    public boolean is_shift_already_booked(int day,int shift,Branch branch)
    {
        return branch.getWeeklyWorkArrangement().get((day-1)*2+shift).workers_on_shift.size() > 0;
    }



//    public static HashMap<String, String>[][] update_HRConstraints(JsonArray constraintsArray) {
//        HashMap<String, String>[][] HRconstraints = new HashMap[7][2];
//        for (int i = 0; i < 7; i++) {
//            for (int j = 0; j < 2; j++) {
//                HRconstraints[i][j] = new HashMap<>();
//            }
//        }
//
//        if (constraintsArray == null) return null;
//
//        for (int i = 0; i < constraintsArray.size(); i++) {
//            JsonObject shiftConstraints = constraintsArray.get(i).getAsJsonObject();
//            if (shiftConstraints == null) return null;
//
//            String day = shiftConstraints.get("day").getAsString();
//            String shift = shiftConstraints.get("shift").getAsString();
//            JsonObject rolesConstraints = shiftConstraints.get("roles").getAsJsonObject();
//            if (rolesConstraints == null) return null;
//
//            int dayIndex = getDayIndex(day);
//            int shiftIndex = getShiftIndex(shift);
//
//            if (dayIndex == -1 || shiftIndex == -1) return null;
//
//            for (String role : rolesConstraints.keySet()) {
//                String number = rolesConstraints.get(role).getAsString();
//                HRconstraints[dayIndex][shiftIndex].put(role, number);
//            }
//        }
//
//        return HRconstraints;
//    }

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
                return -1;  // Invalid day
        }
    }

    private static int getShiftIndex(String shift) {
        switch (shift) {
            case "Morning":
                return 0;
            case "Evening":
                return 1;
            default:
                return -1;  // Invalid shift
        }
    }
}