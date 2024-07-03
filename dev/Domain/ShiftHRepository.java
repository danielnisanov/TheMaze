package Domain;

import Dal.ShiftHDAO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class ShiftHRepository implements IRepository<Shift> {
    private final ArrayList<Shift> shiftHistory;
    private ShiftHDAO shiftHDAO;

    public ShiftHRepository() {
        this.shiftHistory = new ArrayList<>();
    }


    @Override
    public boolean Insert(Shift shift) {
        if (shiftHistory.contains(shift)) {
            return false; // Shift already exists
        }
        else {
            try {
                shiftHDAO.Insert(shift); // Add shift to the database
                shiftHistory.add(shift); // Add shift to the local list
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }


    @Override
    public boolean Update(int num, String field, String change) throws SQLException {
        return false;
    }

    @Override
    public boolean Delete() {
        return false;
    }

    @Override
    public Shift Find(int num) throws SQLException {
        return null;
    }

    public ArrayList<Shift> getShiftHistory() {
        if (!shiftHistory.isEmpty()) {
            return shiftHistory;
        } else {
            try {
                shiftHistory.addAll(shiftHDAO.getAllShifts());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return shiftHistory;
        }
    }
    public JsonArray presentShiftHistory(Branch branch) {
        getShiftHistory();
        JsonArray jsonArray = new JsonArray();
        for (Shift shift : branch.get_Shift_History()) {
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
