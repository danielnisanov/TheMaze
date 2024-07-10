package Domain;

import com.google.gson.JsonArray;


public class ShiftController {
    private final ShiftHRepository shiftHRepository;
    private final WorkersRepository workersRepository;
    private final WorkArrangementRepository workArrangementRepository;
    private final WorkersOnShiftRepository workersOnShiftRepository;

    public ShiftController(ShiftHRepository shiftHRepository,WorkersRepository workersRepository,WorkArrangementRepository workArrangementRepository, WorkersOnShiftRepository workersOnShiftRepository) {
        this.shiftHRepository = shiftHRepository;
        this.workersRepository = workersRepository;
        this.workArrangementRepository = workArrangementRepository;
        this.workersOnShiftRepository = workersOnShiftRepository;
    }

    public boolean add_worker_to_weekly_arrangement(Branch branch, Worker worker, String day, String shiftType, Role role) {
        boolean success = workersOnShiftRepository.InsertShift(worker,getDayIndex(day)*2 + getShiftIndex(shiftType)) ;

        if (!success) {
            return false; // Worker is already assigned to this shift
        }
        /*if(branch.get_Weekly_Work_Arrangement().isEmpty())
        {
            Shift currentShift = new Shift(getDayIndex(day), shiftType, new ArrayList<Worker>());
            currentShift.workers_on_shift.add(worker);// Add workers to shift
            workArrangementRepository.Insert(currentShift);
//        branch.get_Weekly_Work_Arrangement().add(currentShift);
            shiftHRepository.Insert(currentShift);
            int id = worker.getID_number();
            workersRepository.Update(id,"Total_hours", "8");

            branch.get_Weekly_Work_Arrangement().add(currentShift);
            return true;
        }*/


        int dayIndex = getDayIndex(day);
        int shiftIndex = getShiftIndex(shiftType);

        if (dayIndex == -1 || shiftIndex == -1) {
            return false; // Invalid day or shift
        }

        int shiftArrayIndex = dayIndex * 2 + shiftIndex;

        if (shiftArrayIndex < 0 || shiftArrayIndex > 14) {
            throw new IndexOutOfBoundsException("Index " + shiftArrayIndex + " out of bounds for length " + branch.get_Weekly_Work_Arrangement().size());
        }

        Shift currentShift = branch.get_Weekly_Work_Arrangement().get(shiftArrayIndex);
        currentShift.workers_on_shift.add(worker);// Add workers to shift
        workArrangementRepository.Insert(currentShift);
//        branch.get_Weekly_Work_Arrangement().add(currentShift);
        shiftHRepository.Insert(currentShift);
        int id = worker.getID_number();
        workersRepository.Update(id,"Total_hours", "8");
        return true;
    }

    public boolean clearShiftWorkers(Branch branch, String day, String shiftType) {
        int dayIndex = getDayIndex(day);
        int shiftIndex = getShiftIndex(shiftType);

        if (dayIndex == -1 || shiftIndex == -1) {
            return false; // Invalid day or shift
        }

        int shiftArrayIndex = dayIndex * 2 + shiftIndex;

        if (shiftArrayIndex < 0 || shiftArrayIndex > branch.get_Weekly_Work_Arrangement().size()) {
            throw new IndexOutOfBoundsException("Index " + shiftArrayIndex + " out of bounds for length " + branch.get_Weekly_Work_Arrangement().size());
        }

        if(!branch.get_Weekly_Work_Arrangement().isEmpty()) {
            Shift currentShift = branch.get_Weekly_Work_Arrangement().get(shiftArrayIndex);
            workersOnShiftRepository.Delete();
            currentShift.workers_on_shift.clear(); // todo- delete from repository
        }
        return true;
    }


//    public boolean is_shift_already_booked(int day, int shift, Branch branch) {
//        int shiftArrayIndex = (day - 1) * 2 + shift;
//        return !branch.get_Weekly_Work_Arrangement().get(shiftArrayIndex).workers_on_shift.isEmpty();
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

    public JsonArray present_Shift_History(Branch branch) {
        return shiftHRepository.presentShiftHistory(branch);
    }


}


