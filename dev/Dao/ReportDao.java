//package Dao;
//
//import Domain.Report;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
////import java.sql.jdbc;
//
//public class ReportDao implements IDao<Report> {
//
//    private Connection connection;
//
//    public ReportDao(Connection connection) {
//        this.connection = connection;
//    }
//
//    @Override
//    public void add(Report report) {
//        try {
//            String query = "INSERT INTO reports (reportID, reportDate) VALUES (?, ?) ON CONFLICT(reportID) DO UPDATE SET reportDate = excluded.reportDate";
//            PreparedStatement stmt = connection.prepareStatement(query);
//            stmt.setInt(1, report.getReportID());
//            stmt.setDate(2, Date.valueOf(report.getReportDate()));
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public Report get(int reportID) {
//        try {
//            String query = "SELECT * FROM reports WHERE reportID = ?";
//            PreparedStatement stmt = connection.prepareStatement(query);
//            stmt.setInt(1, reportID);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                return new Report(
//                        rs.getInt("reportID"),
//                        rs.getDate("reportDate").toLocalDate()
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//}
