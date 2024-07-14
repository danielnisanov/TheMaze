package DAL;

import DB.DataBase;
import Domain.Item;
import Domain.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReportDAO implements IDAO<Report> {

    private Connection conn;

    public ReportDAO() throws SQLException {
        conn = DataBase.connect();
    }

    @Override
    public void add(Report report) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO reports (reportID,reportDate) VALUES (?,?)");
        stmt.setInt(1, report.getReportID());
        stmt.setDate(2, java.sql.Date.valueOf(report.getReportDate()));
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void remove(String id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM reports WHERE reportID = ?");
        stmt.setString(1, id);
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public Report get(String id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reports WHERE reportID = ?");
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        Report report = null;
        if (rs.next()) {
            LocalDate expirationDate = rs.getDate("reportDate").toLocalDate();
            report = new Report(
                    rs.getInt("reportID"),
                    expirationDate
            );
            rs.close();
            stmt.close();
            return report;
        }
        else {
            return null;
        }
    }

    @Override
    public void update(Report entity) throws SQLException {

    }
}
