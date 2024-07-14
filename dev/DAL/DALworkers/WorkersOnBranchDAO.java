package DAL.DALworkers;

import Domain.Domainworkers.Worker;
import Domain.Domainworkers.WorkersRepositoryWorkers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WorkersOnBranchDAO implements IDAO<Worker> {
    private final DatabaseConnection dbConnection;
    WorkersRepositoryWorkers workersRepository;

    public WorkersOnBranchDAO(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    @Override
    public void Insert(Worker worker) throws SQLException {
        String query = "INSERT INTO workers_on_branch (worker_id, branch_id) VALUES (?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, worker.getID_number());
            stmt.setInt(2, worker.getBranchNum());
            stmt.executeUpdate();
        }
    }

    @Override
    public void Delete() throws SQLException {

    }

    @Override
    public Worker Find(int workerId) throws SQLException {
//        String query = "SELECT * FROM workers_on_branch WHERE worker_id = ?";
//        try (Connection conn = dbConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setInt(1, workerId);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                int branchId = rs.getInt("branch_id");
//                // Here you would retrieve and return the Worker object with the branch info
//                // For simplicity, assuming we have a method getWorkerById in another DAO
//                Worker worker = workersRepository.Find(workerId);
//                worker.setBranchNum(branchId);
//                return worker;
//            }
//        }
        return null;
    }

    @Override
    public boolean Update(int num, String field, String change) throws SQLException {
        return false;
    }


}
