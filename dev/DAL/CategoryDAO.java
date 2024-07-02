package DAL;
import DB.DataBase;
import Domain.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDAO implements IDAO <Category>{
    private Connection conn;

    public CategoryDAO() throws SQLException {
        conn = DataBase.connect();
    }
    @Override
    public void add(Category category) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO categories( categoryName) VALUES(?)");
        stmt.setString(1, category.getCategoryName());
        stmt.executeUpdate();
    }

    @Override
    public void remove(String name) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM categories WHERE categoryName = ?");
        stmt.setString(1, name);
        stmt.executeUpdate();
    }

    @Override
    public Category get(String name) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categories WHERE categoryName = ?");
        stmt.setString(1, name); //FIXME
        ResultSet rs = stmt.executeQuery();
        Category category = null;
        if (rs.next()) {
            category = new Category(
                    rs.getString("categoryName")
            );
            return category;
        } else {
            return null;
        }
    }

    @Override
    public void update(Category category) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE categories SET categoryName = ? WHERE categoryName = ?");
        stmt.setString(1, category.getCategoryName());
        stmt.executeUpdate();
    }

    public void showCategories() throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("SELECT categoryName FROM categories");
        ResultSet rs = stmt.executeQuery();
        System.out.println("Categories:");
        while (rs.next()) {
            System.out.println(rs.getString("categoryName"));
        }
    }

    public Boolean containCat(String name) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM categories WHERE categoryName = ?");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
