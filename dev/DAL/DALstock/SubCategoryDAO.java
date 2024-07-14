package DAL.DALstock;

import DB.DataBase;
import Domain.Domainstock.Category;
import Domain.Domainstock.SubCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubCategoryDAO implements IDAO<SubCategory> {
    private Connection conn;

    public SubCategoryDAO() throws SQLException {
        conn = DataBase.connect();
    }

    @Override
    public void add(SubCategory subCategory) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO subCategories(subCategoryName, categoryName) VALUES(?, ?)");
        stmt.setString(1, subCategory.getSubCategoryName());
        stmt.setString(2, subCategory.getCategoryName());
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void remove(String name) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM subCategories WHERE subCategoryName = ?");
        stmt.setString(1, name);
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public SubCategory get(String name) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM subCategories WHERE subCategoryName = ?");
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        SubCategory subCategory = null;
        if (rs.next()) {
            subCategory = new SubCategory(rs.getString("subCategoryName"), rs.getString("categoryName"));
        }
        rs.close();
        stmt.close();
        return subCategory;
    }

    @Override
    public void update(SubCategory subCategory) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE subCategories SET subCategoryName = ? WHERE subCategoryName = ?");
        stmt.setString(1, subCategory.getSubCategoryName());
        stmt.executeUpdate();
        stmt.close();
    }

    public void showSubCategories(Category category) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT subCategoryName FROM subCategories WHERE categoryName = ?");
        stmt.setString(1, category.getCategoryName());
        ResultSet rs = stmt.executeQuery();
        System.out.println("Subcategories for category: " + category.getCategoryName());
        while (rs.next()) {
            System.out.println(rs.getString("subCategoryName"));
        }
        rs.close();
        stmt.close();
    }

    public boolean containSubCat(String name) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM subCategories WHERE subCategoryName = ?");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            boolean exists = rs.next();
            rs.close();
            stmt.close();
            return exists;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
