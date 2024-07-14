package DAL.DALstock;

import DB.DataBase;
import Domain.Domainstock.SubSubCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubSubCategoryDAO implements IDAO<SubSubCategory> {
    private Connection conn;

    public SubSubCategoryDAO() throws SQLException {
        conn = DataBase.connect();
    }

    @Override
    public void add(SubSubCategory subSubCategory) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO subSubCategories(subSubCategoryName, subCategoryName, categoryName) VALUES(?, ?, ?)");
        stmt.setString(1, subSubCategory.getSubSubCategoryName());
        stmt.setString(2, subSubCategory.getSubCategoryName());
        stmt.setString(3, subSubCategory.getCategoryName());
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void remove(String name) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM subSubCategories WHERE subSubCategoryName = ?");
        stmt.setString(1, name);
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public SubSubCategory get(String name) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM subSubCategories WHERE subSubCategoryName = ?");
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        SubSubCategory subSubCategory = null;
        if (rs.next()) {
            subSubCategory = new SubSubCategory(
                    rs.getString("subSubCategoryName"),
                    rs.getString("subCategoryName"),
                    rs.getString("categoryName")
            );
        }
        rs.close();
        stmt.close();
        return subSubCategory;
    }

    @Override
    public void update(SubSubCategory subSubCategory) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE subSubCategories SET subSubCategoryName = ? WHERE subSubCategoryName = ?");
        stmt.setString(1, subSubCategory.getSubSubCategoryName());
        stmt.executeUpdate();
        stmt.close();
    }

    public boolean containSubSubCat(String name) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM subSubCategories WHERE subSubCategoryName = ?");
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


