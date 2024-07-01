package Dal;

import Domain.Branch;
import Domain.HRManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BranchesDAO implements IDAO<Branch> {
    private DatabaseConnection dbConnection;

    public BranchesDAO(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    @Override
    public void Insert(Branch obj) {

    }

    @Override
    public void Delete(int num) throws SQLException {

    }

    @Override
    public Branch Find(int id) throws SQLException {
        String query = "SELECT * FROM branches WHERE branch_id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                HRManager manager = null;
                int managerId = rs.getInt("manager_id");
                if (managerId != 0) {
                    manager = new ManagersDAO(dbConnection).Find(managerId);
                }
                Branch branch = new Branch(
                        rs.getInt("branch_id"),
                        manager
                );
                return branch;
            }
        }
        return null;
    }

    @Override
    public boolean Update(int num, String field, String change) throws SQLException {
        return false;
    }

}
