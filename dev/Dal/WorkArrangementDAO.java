package Dal;

import Domain.Shift;
import Domain.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WorkArrangementDAO implements IDAO<ArrayList<Shift>>{
    private final DatabaseConnection dbConnection;
    public WorkersDAO workersDAO;

    public WorkArrangementDAO(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection; // Update with actual path
    }

    @Override
    public void Insert(ArrayList<Shift> obj) throws SQLException {

    }

    @Override
    public void Delete() throws SQLException {

    }

    @Override
    public ArrayList<Shift> Find(int day) throws SQLException {
        ArrayList<Shift> shifts = new ArrayList<>();
        String query = "SELECT * FROM workArrangement WHERE day = ?"; // Corrected table name and field name
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, day);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int shiftDate = rs.getInt("shift_id"); // Assuming shift_id is used as shiftDate
                String shiftType = rs.getString("shift_type");

                String workersStr = rs.getString("workers_on_shift");
                List<Worker> workers = new ArrayList<>();
                for (String workerId : workersStr.split(",")) {
                    if (!workerId.isEmpty()) { // Check if workerId is not empty
                        Worker worker = findWorkerById(Integer.parseInt(workerId));
                        if (worker != null) {
                            workers.add(worker);
                        }
                    }
                }

                Shift shift = new Shift(shiftDate, shiftType, workers); // Using the constructor as defined
                shifts.add(shift);
            }
        }
        return shifts;
    }



    @Override
    public boolean Update(int num, String field, String change) throws SQLException {
        return false;
    }

    private Worker findWorkerById(int workerId) throws SQLException {
        return workersDAO.Find(workerId);
    }

}
