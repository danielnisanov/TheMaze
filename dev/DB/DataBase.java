package DB;
import java.sql.*;


public class DataBase {
     //private static final String DB_URL = "jdbc:sqlite:C:\\Users\\Danieln\\Documents\\GitHub\\ADSS_Group_I\\test.db";
    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\97252\\OneDrive\\Documents\\GitHub\\ADSS_Group_I\\test.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
