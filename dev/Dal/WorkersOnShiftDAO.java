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


    public void InsertShift(Worker worker,int shift_id) throws SQLException {
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
