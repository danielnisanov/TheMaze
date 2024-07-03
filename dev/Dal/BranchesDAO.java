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
    public void Delete() throws SQLException {

    }

    @Override
    public Branch Find(int id) throws SQLException {
        String query = "SELECT * FROM branches WHERE branch_num = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Create the branch using the branch_num constructor
                Branch branch = new Branch(rs.getInt("branch_num"));
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


