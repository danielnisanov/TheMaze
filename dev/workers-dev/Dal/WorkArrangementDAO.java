package Dal;

import Domain.Shift;
import Domain.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkArrangementDAO implements IDAO<ArrayList<Shift>> {
    private final DatabaseConnection dbConnection;
    public WorkersDAO workersDAO;


    public WorkArrangementDAO(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;

    }

    @Override
    public void Insert(ArrayList<Shift> obj) throws SQLException {
        // Implementation of Insert method if needed
    }

    public void setWorkersDAO(WorkersDAO workersDAO) {
        this.workersDAO = workersDAO;
    }

    @Override
    public void Delete() throws SQLException {
        // Implementation of Delete method if needed
    }

    @Override
    public ArrayList<Shift> Find(int shift_id) throws SQLException {
        ArrayList<Shift> shifts = new ArrayList<>();
        String query = "SELECT * FROM workArrangement WHERE shift_id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, shift_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int day = rs.getInt("day");
                String shiftType = rs.getString("shift_type");

                String workersStr = rs.getString("workers_on_shift");
                List<Worker> workers = new ArrayList<>();
                if (workersStr != null && !workersStr.isEmpty()) {
                    for (String workerId : workersStr.split(",")) {
                        if (!workerId.isEmpty()) { // Check if workerId is not empty
                            Worker worker = findWorkerById(Integer.parseInt(workerId));
                            if (worker != null) {
                                workers.add(worker);
                            }
                        }
                    }
                }

                Shift shift = new Shift(day, shiftType, workers);
                shifts.add(shift);
                System.out.println("Shift added: " + shift); // Debug print
            }
        }
        return shifts;
    }

    @Override
    public boolean Update(int num, String field, String change) throws SQLException {
        // Implementation of Update method if needed
        return false;
    }

    private Worker findWorkerById(int workerId) throws SQLException {
        return workersDAO.Find(workerId);
    }
}
