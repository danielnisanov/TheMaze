package Dal;

import Domain.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WorkersOnShiftDAO implements IDAO<Worker> {

    private final DatabaseConnection dbConnection;
    WorkersDAO workersDAO;

    public WorkersOnShiftDAO(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.workersDAO = new WorkersDAO(dbConnection);
    }

    @Override
    public void Insert(Worker worker) throws SQLException {
        String query = "INSERT INTO workers_on_shift (id_number, ...) VALUES (?, ...)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, worker.getID_number());
            // Set other fields
            stmt.executeUpdate();
        }
    }

    @Override
    public void Delete() throws SQLException {
        String query = "DELETE FROM workers_on_shift";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.executeUpdate();
        }
    }

    @Override
    public Worker Find(int num) throws SQLException {
        return null;
    }

    @Override
    public boolean Update(int num, String field, String change) throws SQLException {
        return false;
    }

}
