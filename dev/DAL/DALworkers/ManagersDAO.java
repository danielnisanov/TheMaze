package DAL.DALworkers;

import Domain.Domainworkers.BranchesRepositoryWorkers;
import Domain.Domainworkers.HRManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagersDAO implements IDAO<HRManager> {
    private final DatabaseConnection dbConnection;
    private BranchesRepositoryWorkers BR = null;

    public ManagersDAO(DatabaseConnection dbConnection, BranchesRepositoryWorkers BR) {
        this.dbConnection = dbConnection;
        this.BR = BR;
    }

    public void Insert(HRManager hrManager) {
        String query = "INSERT INTO managers (ID_number, name, branch, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, hrManager.getID_number());
            stmt.setString(2, hrManager.name);
            stmt.setInt(3, hrManager.branch.getBranchNum());
            stmt.setString(4, hrManager.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete() throws SQLException {

    }

    @Override
    public HRManager Find(int id) throws SQLException {
        String query = "SELECT * FROM managers WHERE ID_number = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                HRManager manager = new HRManager(
                        rs.getString("name"),
                        BR.Find((rs.getInt("branch"))),
                        rs.getString("password"),
                        rs.getInt("ID_number")
                );
                return manager;
            }
        }
        return null;
    }

    @Override
    public boolean Update(int id, String field, String newValue) {
        if ("password".equalsIgnoreCase(field)) {
            String query = "UPDATE managers SET password = ? WHERE ID_number = ?";
            try (Connection conn = dbConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, newValue);
                stmt.setInt(2, id);
                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;
            } catch (SQLException e) {
                e.printStackTrace(); // Handle SQL exception
            }
        }
        return false;
    }

}
