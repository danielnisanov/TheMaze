package DB;
import java.sql.*;


public class DataBase {

     private static final String DB_URL = "jdbc:sqlite:test.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
