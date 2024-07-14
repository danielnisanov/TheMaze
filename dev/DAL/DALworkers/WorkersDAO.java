package DAL.DALworkers;
import Domain.Domainworkers.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WorkersDAO implements IDAO<Worker> {
    private final DatabaseConnection dbConnection;
    private final Gson gson;
    private BranchesRepositoryWorkers BR = null;

    public WorkersDAO(DatabaseConnection dbConnection, BranchesRepositoryWorkers BR) {
        this.dbConnection = dbConnection;
        this.gson = new Gson();
        this.BR = BR;
    }

    @Override
    public void Insert(Worker worker) throws SQLException {
        String query = "INSERT INTO workers (ID_number, name, address, bank_account_num, hourly_salary, vacation_days, job_type, starting_day, branch, total_hours, job_status, roles, constraints) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            stmt.setInt(9, worker.getBranchNum());
            stmt.setDouble(10, worker.getTotal_hours());
            stmt.setBoolean(11, worker.getJob_status());

            // Serialize roles to a comma-separated string
            String roles = worker.getRoles_permissions().stream()
                    .map(Role::name)
                    .collect(Collectors.joining(","));
            stmt.setString(12, roles);

            // Serialize constraints to JSON
            String constraintsJson = gson.toJson(worker.getConstraints());
            stmt.setString(13, constraintsJson);

            stmt.executeUpdate();
        }
    }

    @Override
    public void Delete() throws SQLException {

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

                String constraintsJson = rs.getString("constraints");
                Map<String, List<String>> constraints = gson.fromJson(constraintsJson, new TypeToken<Map<String, List<String>>>() {}.getType());

                Worker worker = new Worker(
                        rs.getString("address"),
                        rs.getString("name"),
                        rs.getInt("ID_number"),
                        rs.getInt("bank_account_num"),
                        rs.getDouble("hourly_salary"),
                        rs.getInt("vacation_days"),
                        JobType.valueOf(rs.getString("job_type")),
                        BR.Find((rs.getInt("branch"))),
                        roles
                );
                worker.setTotal_hours(rs.getDouble("total_hours"));
                worker.setJob_status(rs.getBoolean("job_status"));
                worker.setConstraints(constraints);
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
            case "roles":
                // Assuming the value is a comma-separated list of roles
                query = "SELECT roles FROM workers WHERE ID_number = ?";
                try (Connection conn = dbConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setInt(1, id);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        String currentRoles = rs.getString("roles");
                        Set<Role> roleSet = Arrays.stream(currentRoles.split(","))
                                .map(Role::valueOf)
                                .collect(Collectors.toSet());
                        roleSet.add(Role.Shift_manager);
                        // Adding new roles from value
                        Arrays.stream(value.split(","))
                                .map(Role::valueOf)
                                .forEach(roleSet::add);
                        String updatedRoles = roleSet.stream()
                                .map(Role::name)
                                .collect(Collectors.joining(","));
                        query = "UPDATE workers SET roles = ? WHERE ID_number = ?";
                        try (PreparedStatement updateStmt = conn.prepareStatement(query)) {
                            updateStmt.setString(1, updatedRoles);
                            updateStmt.setInt(2, id);
                            updated = updateStmt.executeUpdate() > 0;
                        }
                    }
                }
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
            case "constraints":
                query = "UPDATE workers SET constraints = ? WHERE ID_number = ?";
                try (Connection conn = dbConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, value);
                    stmt.setInt(2, id);
                    updated = stmt.executeUpdate() > 0;
                }
                break;
            case "Total_hours":
                query = "UPDATE workers SET total_hours = total_hours + ? WHERE ID_number = ?";
                try (Connection conn = dbConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setDouble(1, Double.parseDouble(value));
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
                String rolesStr = rs.getString("roles");
                Set<Role> roles = Arrays.stream(rolesStr.split(","))
                        .map(Role::valueOf)
                        .collect(Collectors.toSet());

                String constraintsJson = rs.getString("constraints");
                Map<String, List<String>> constraints = gson.fromJson(constraintsJson, new TypeToken<Map<String, List<String>>>() {}.getType());
                Branch b = BR.Find((rs.getInt("branch")));
                Worker worker = new Worker(
                        rs.getString("address"),
                        rs.getString("name"),
                        rs.getInt("ID_number"),
                        rs.getInt("bank_account_num"),
                        rs.getDouble("hourly_salary"),
                        rs.getInt("vacation_days"),
                        JobType.valueOf(rs.getString("job_type")),
                        b,
                        roles
                );
                b.add_worker_brunch(worker);
                worker.setTotal_hours(rs.getDouble("total_hours"));
                worker.setJob_status(rs.getBoolean("job_status"));
                worker.setConstraints(constraints);
                workersList.add(worker);
            }
        }
        return workersList;
    }

    public void updateConstraints(int id, Map<String, List<String>> constraints) throws SQLException {
        String query = "UPDATE workers SET constraints = ? WHERE ID_number = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, gson.toJson(constraints)); // Serialize the map to JSON
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }
}
