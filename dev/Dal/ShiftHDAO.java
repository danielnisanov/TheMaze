package Dal;

import Domain.BranchesRepository;
import Domain.HRManager;
import Domain.Shift;
import Domain.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShiftHDAO implements IDAO<Shift>  {
    private final DatabaseConnection dbConnection;
    WorkersDAO workersDAO;

    public ShiftHDAO(DatabaseConnection dbConnection, BranchesRepository BR) {
        this.dbConnection = dbConnection;
        this.workersDAO = new WorkersDAO(dbConnection,BR);
    }

    @Override
    public void Insert(Shift shift) throws SQLException {
        String query = "INSERT INTO shifts (shift_date, shift_type, workers_on_shift) VALUES (?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, shift.getShift_date()); // assuming shift_date is a string
            stmt.setString(2, shift.getShift_type());

            // Convert workers_on_shift to a string format, e.g., CSV
            String workers = shift.getWorkers_on_shift().stream()
                    .map(worker -> String.valueOf(worker.getID_number()))
                    .collect(Collectors.joining(","));

            stmt.setString(3, workers);

            stmt.executeUpdate();
        }
    }

    @Override
    public void Delete() throws SQLException {

    }

    @Override
    public Shift Find(int num) throws SQLException {
        return null;
    }

    @Override
    public boolean Update(int num, String field, String change) throws SQLException {
        return false;
    }

    public List<Shift> getAllShifts() throws SQLException {
        List<Shift> shifts = new ArrayList<>();
        String query = "SELECT * FROM shifts";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int shiftDate = rs.getInt("shift_date");
                String shiftType = rs.getString("shift_type");

                String workersStr = rs.getString("workers_on_shift");
                List<Worker> workers = new ArrayList<>();
                for (String workerId : workersStr.split(",")) {
                    // Assuming there's a method to find a worker by ID
                    Worker worker = findWorkerById(Integer.parseInt(workerId));
                    if (worker != null) {
                        workers.add(worker);
                    }
                }

                Shift shift = new Shift(shiftDate, shiftType, workers);
                shifts.add(shift);
            }
        }
        return shifts;
    }

    private Worker findWorkerById(int id) throws SQLException {
        return workersDAO.Find(id);
    }

}
