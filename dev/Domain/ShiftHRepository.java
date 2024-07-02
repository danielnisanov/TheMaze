package Domain;

import Dal.ShiftHDAO;

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
    public boolean Delete(int id) {
        return false;
    }

    @Override
    public boolean Find(int num) throws SQLException {
        return false;
    }

    public ArrayList<Shift> getShiftHistory() {
        if (!shiftHistory.isEmpty()) {
            return shiftHistory;
        } else {

        }
    }
}
