package Dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final String dataPath;
    private final String userName;
    private final String password;

    public DatabaseConnection(String dataPath, String userName, String password) {
        this.dataPath = dataPath;
        this.userName = userName;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dataPath, userName, password);
    }
}
