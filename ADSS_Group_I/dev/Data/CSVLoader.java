package Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CSVLoader {
    private static final String CSV_FILE_PATH = "/mnt/data/data.csv";

    public void loadCSV() {
        String sql = "INSERT INTO WORKERS (address, name, social_security_number, bank_account_number, hourly_wage, vacation_days, job_type, branch, roles_permissions) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH));
             Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                ps.setString(1, values[0]);  // address
                ps.setString(2, values[1]);  // name
                ps.setString(3, values[2]);  // social_security_number
                ps.setString(4, values[3]);  // bank_account_number
                ps.setDouble(5, Double.parseDouble(values[4]));  // hourly_wage
                ps.setInt(6, Integer.parseInt(values[5]));  // vacation_days
                ps.setString(7, values[6]);  // job_type
                ps.setInt(8, Integer.parseInt(values[7]));  // branch
                ps.setString(9, values[8]);  // roles_permissions
                ps.addBatch();
            }

            ps.executeBatch();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
