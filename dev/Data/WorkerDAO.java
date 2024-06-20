package Data;

import Domain.Worker;
import Domain.JobType;
import Domain.Branch;
import Domain.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorkerDAO {

    public List<Worker> getAllWorkers() {
        List<Worker> workers = new ArrayList<>();
        String sql = "SELECT * FROM WORKERS";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Worker worker = new Worker(
                        rs.getString("address"),
                        rs.getString("name"),
                        rs.getInt("social_security_number"),
                        rs.getInt("bank_account_number"),
                        rs.getDouble("hourly_wage"),
                        rs.getInt("vacation_days"),
                        JobType.valueOf(rs.getString("job_type")),
                        new Branch(rs.getInt("branch")),
                        convertRoles(rs.getString("roles_permissions"))
                );
                workers.add(worker);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }

    private Set<Role> convertRoles(String rolesPermissions) {
        Set<Role> roles = new HashSet<>();
        String[] rolesArray = rolesPermissions.split(",");
        for (String role : rolesArray) {
            roles.add(Role.valueOf(role.trim()));
        }
        return roles;
    }

    public void insertWorker(Worker worker) {
        String sql = "INSERT INTO WORKERS (address, name, social_security_number, bank_account_number, hourly_wage, vacation_days, job_type, branch, roles_permissions) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, worker.getAddress());
            ps.setString(2, worker.getName());
            ps.setInt(3, worker.getID_number());
            ps.setInt(4, worker.getBank_account_num());
            ps.setDouble(5, worker.getHourly_salary());
            ps.setInt(6, worker.getVaction_days());
            ps.setString(7, worker.getJob_type().name());
            ps.setInt(8, worker.getBranch().getBranchNum());
            ps.setString(9, String.join(",", worker.getRoles_permissions().stream().map(Enum::name).toArray(String[]::new)));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateWorker(Worker worker) {
        String sql = "UPDATE WORKERS SET address = ?, name = ?, bank_account_number = ?, hourly_wage = ?, vacation_days = ?, job_type = ?, branch = ?, roles_permissions = ? WHERE social_security_number = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, worker.getAddress());
            ps.setString(2, worker.getName());
            ps.setInt(3, worker.getBank_account_num());
            ps.setDouble(4, worker.getHourly_salary());
            ps.setInt(5, worker.getVaction_days());
            ps.setString(6, worker.getJob_type().name());
            ps.setInt(7, worker.getBranch().getBranchNum());
            ps.setString(8, String.join(",", worker.getRoles_permissions().stream().map(Enum::name).toArray(String[]::new)));
            ps.setInt(9, worker.getID_number());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWorker(int socialSecurityNumber) {
        String sql = "DELETE FROM WORKERS WHERE social_security_number = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, socialSecurityNumber);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
