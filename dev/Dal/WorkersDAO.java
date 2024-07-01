package Dal;

import Domain.Worker;
import Domain.Role;
import Domain.JobType;
import Domain.Branch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import com.google.gson.Gson;


public class WorkersDAO implements IDAO<Worker> {
    private DatabaseConnection dbConnection;
    private RolesDAO rolesDAO;
    private Gson gson;

    public WorkersDAO(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.rolesDAO = new RolesDAO(dbConnection);
        this.gson = new Gson();
    }

    @Override
    public void Insert(Worker worker) throws SQLException {
        String query = "INSERT INTO workers (ID_number, name, address, bank_account_num, hourly_salary, vacation_days, job_type, starting_day, branch, total_hours, job_status, roles) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, worker.getID_number());
            stmt.setString(2, worker.getName());
            stmt.setString(3, worker.getAddress());
            stmt.setInt(4, worker.getBank_account_num());
            stmt.setDouble(5, worker.getHourly_salary());
            stmt.setInt(6, worker.getVaction_days());
            stmt.setString(7, worker.getJob_type().toString());
            stmt.setDate(8, java.sql.Date.valueOf(worker.getStarting_day()));
            stmt.setString(9, String.valueOf(worker.getBranchNum()));
            stmt.setDouble(10, worker.getTotal_hours());
            stmt.setBoolean(11, worker.getJob_status());

            // Serialize roles to a comma-separated string
            String roles = worker.getRoles_permissions().stream()
                    .map(Role::name)
                    .collect(Collectors.joining(","));
            stmt.setString(12, roles);

            stmt.executeUpdate();
        }
    }


    @Override
    public void Delete(int id) throws SQLException {
        Update(id, "job_status", "false");

    }

    @Override
    public Worker Find(int id) throws SQLException {
        String query = "SELECT * FROM workers WHERE ID_number = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String rolesStr = rs.getString("roles");
                Set<Role> roles = Arrays.stream(rolesStr.split(","))
                        .map(Role::valueOf)
                        .collect(Collectors.toSet());

                Worker worker = new Worker(
                        rs.getString("address"),
                        rs.getString("name"),
                        rs.getInt("ID_number"),
                        rs.getInt("bank_account_num"),
                        rs.getDouble("hourly_salary"),
                        rs.getInt("vacation_days"),
                        JobType.valueOf(rs.getString("job_type")),
                        new Branch(rs.getInt("branch")),
                        roles
                );
                worker.setTotal_hours(rs.getDouble("total_hours"));
                worker.setJob_status(rs.getBoolean("job_status"));
                return worker;
            }
        }
        return null;
    }


    @Override
    public boolean Update(int id, String field, String value) throws SQLException {
        String query;
        boolean updated = false;

        switch (field) {
            case "bank_account_num":
                query = "UPDATE workers SET bank_account_num = ? WHERE ID_number = ?";
                try (Connection conn = dbConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setInt(1, Integer.parseInt(value));
                    stmt.setInt(2, id);
                    updated = stmt.executeUpdate() > 0;
                }
                break;
            case "job_type":
                query = "UPDATE workers SET job_type = ? WHERE ID_number = ?";
                try (Connection conn = dbConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, value);
                    stmt.setInt(2, id);
                    updated = stmt.executeUpdate() > 0;
                }
                break;
            case "hourly_salary":
                query = "UPDATE workers SET hourly_salary = ? WHERE ID_number = ?";
                try (Connection conn = dbConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setDouble(1, Double.parseDouble(value));
                    stmt.setInt(2, id);
                    updated = stmt.executeUpdate() > 0;
                }
                break;
            case "branch":
                query = "UPDATE workers SET branch = ? WHERE ID_number = ?";
                try (Connection conn = dbConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setInt(1, Integer.parseInt(value));
                    stmt.setInt(2, id);
                    updated = stmt.executeUpdate() > 0;
                }
                break;
            case "roles": // update worker roles - add Shift_manager
                rolesDAO.addRole(id, Role.Shift_manager);
                updated = true;
                break;
            case "job_status":
                query = "UPDATE workers SET job_status = ? WHERE ID_number = ?";
                try (Connection conn = dbConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setBoolean(1, Boolean.parseBoolean(value));
                    stmt.setInt(2, id);
                    updated = stmt.executeUpdate() > 0;
                }
                break;
        }
        return updated;
    }

    public List<Worker> getAllWorkers() throws SQLException {
        List<Worker> workersList = new ArrayList<>();
        String query = "SELECT * FROM workers";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Set<Role> roles = rolesDAO.findRoles(rs.getInt("ID_number"));
                Worker worker = new Worker(
                        rs.getString("address"),
                        rs.getString("name"),
                        rs.getInt("ID_number"),
                        rs.getInt("bank_account_num"),
                        rs.getDouble("hourly_salary"),
                        rs.getInt("vacation_days"),
                        JobType.valueOf(rs.getString("job_type")),
                        new Branch(rs.getInt("branch")),
                        roles
                );
                worker.setTotal_hours(rs.getDouble("total_hours"));
                worker.setJob_status(rs.getBoolean("job_status"));
                workersList.add(worker);
            }
        }
        return workersList;
    }
    public void updateConstraints(int id, Map<String, List<String>> constraints) throws SQLException {
        String query = "UPDATE workers SET constraints = ? WHERE ID_number = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, gson.toJson(constraints)); // Assuming a suitable method to serialize the map
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

}
