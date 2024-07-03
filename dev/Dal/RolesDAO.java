//package Dal;
//
//import Domain.Role;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashSet;
//import java.util.Set;
//
//public class RolesDAO {
//    private DatabaseConnection dbConnection;
//
//    public RolesDAO(DatabaseConnection dbConnection) {
//        this.dbConnection = dbConnection;
//    }
//
//    public void addRole(int workerId, Role role) throws SQLException {
//        String query = "INSERT INTO roles (worker_id, role_name) VALUES (?, ?)";
//        try (Connection conn = dbConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setInt(1, workerId);
//            stmt.setString(2, role.toString());
//            stmt.executeUpdate();
//        }
//    }
//
//    public Set<Role> findRoles(int workerId) throws SQLException {
//        String query = "SELECT role_name FROM roles WHERE worker_id = ?";
//        Set<Role> roles = new HashSet<>();
//        try (Connection conn = dbConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setInt(1, workerId);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                roles.add(Role.valueOf(rs.getString("role_name")));
//            }
//        }
//        return roles;
//    }
//
//    public void deleteAllRoles(int workerId) throws SQLException {
//        String query = "DELETE FROM roles WHERE worker_id = ?";
//        try (Connection conn = dbConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setInt(1, workerId);
//            stmt.executeUpdate();
//        }
//    }
//}
