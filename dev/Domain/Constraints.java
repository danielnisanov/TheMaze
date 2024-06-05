package Domain;

import java.util.Arrays;

public class Constraints {
    private boolean[][] shifts; // 2D array to represent shifts for each day of the week


    public Constraints() {
        // Initialize the shifts array with 7 days and 2 shifts per day
        this.shifts = new boolean[7][2];
    }

    // Method to set the availability for a specific shift
    public void setShift(int dayOfWeek, int shiftIndex, boolean available) {
        if (dayOfWeek >= 0 && dayOfWeek < 7 && shiftIndex >= 0 && shiftIndex < 2) {
            shifts[dayOfWeek][shiftIndex] = available;
        }
    }

    // Method to get the availability for a specific shift
    public boolean getShift(int dayOfWeek, int shiftIndex) {
        if (dayOfWeek >= 0 && dayOfWeek < 7 && shiftIndex >= 0 && shiftIndex < 2) {
            return shifts[dayOfWeek][shiftIndex];
        }
        return false;
    }

    // Override toString method to display the constraints
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            sb.append("Day ").append(i + 1).append(": ");
            sb.append("Morning Shift: ").append(shifts[i][0] ? "Available" : "Not Available").append(", ");
            sb.append("Evening Shift: ").append(shifts[i][1] ? "Available" : "Not Available").append("\n");
        }
        return sb.toString();
    }
}
