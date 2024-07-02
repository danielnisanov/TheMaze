package Dal;

import Domain.HRManager;
import Domain.Shift;
import Domain.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class ShiftHDAO implements IDAO<Shift>  {
    private final DatabaseConnection dbConnection;

    public ShiftHDAO(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

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
    public void Delete(int num) throws SQLException {

    }

    @Override
    public Shift Find(int num) throws SQLException {
        return null;
    }

    @Override
    public boolean Update(int num, String field, String change) throws SQLException {
        return false;
    }


}
