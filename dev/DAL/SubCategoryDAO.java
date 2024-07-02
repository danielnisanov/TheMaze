package DAL;
import DB.DataBase;
import Domain.SubCategory;
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
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO subCategories(subCategoryName, categoryName) VALUES(?,?)");
        stmt.setString(1, subCategory.getSubCategoryName());
        stmt.setString(2, subCategory.getCategoryName());
        stmt.executeUpdate();
    }

    @Override
    public void remove(String name) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM subCategories WHERE name = ?");
        stmt.setString(1, name);
        stmt.executeUpdate();
    }

    @Override
    public SubCategory get(String name) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM subCategories WHERE name = ?");
        stmt.setString(1, name); //FIXME
        ResultSet rs = stmt.executeQuery();
        SubCategory subCategory = null;
        if (rs.next()) {
            subCategory = new SubCategory(
                    rs.getString("subCategoryName"),
                    rs.getString( "categoryName")
            );
            return subCategory;
        } else {
            return null;
        }
    }

    @Override
    public void update(SubCategory subCategory) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE subCategories SET subCategoryName = ? WHERE name = ?");
        stmt.setString(1, subCategory.getSubCategoryName());
        stmt.executeUpdate();
    }
}
