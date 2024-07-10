package Dal;

import Domain.BranchesRepository;
import Domain.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//
public class WorkersOnShiftDAO implements IDAO<Worker> {

    private final DatabaseConnection dbConnection;
    WorkersDAO workersDAO;

    public WorkersOnShiftDAO(DatabaseConnection dbConnection, BranchesRepository BR) {
        this.dbConnection = dbConnection;
        this.workersDAO = new WorkersDAO(dbConnection,BR);
    }


    public void InsertShiftWA(Worker worker, int shift_id) throws SQLException {
        String val = "";
        String query = "SELECT * from WorkArrangement WHERE shift_id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, shift_id+1);
            ResultSet res = stmt.executeQuery();
            while(res.next()) {
                val = res.getString(4);
            }
        }
        query = "UPDATE WorkArrangement SET workers_on_shift = ? WHERE shift_id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            if(val != "")
            {
                val += ",";
            }
            stmt.setString(1, val+worker.getID_number());
            stmt.setInt(2, shift_id+1);
            stmt.executeUpdate();
        }
    }

    public void InsertShiftSH(Worker worker, int shift_date, String shift_type) throws SQLException {
        String val = "";
        String query = "SELECT workers_on_shift FROM ShiftHRepository WHERE shift_date = ? AND shift_type = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, shift_date);
            stmt.setString(2, shift_type);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                val = res.getString("workers_on_shift");
            }
        }

        if (val != null && !val.isEmpty()) {
            val += ",";
        }
        val += worker.getID_number();

        query = "INSERT OR REPLACE INTO ShiftHRepository (shift_date, shift_type, workers_on_shift) VALUES (?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, shift_date);
            stmt.setString(2, shift_type);
            stmt.setString(3, val);
            stmt.executeUpdate();
        }
    }



    @Override
    public void Insert(Worker obj) throws SQLException {

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
