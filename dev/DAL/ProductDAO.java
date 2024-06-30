package DAL;
import DB.DataBase;
import Domain.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO implements IDAO<Product> {
    private Connection conn;

    public ProductDAO() throws SQLException {
        conn = DataBase.connect();
    }

    @Override
    public void add(Product product) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO products( catNum, name, area, manufacturer, minQuantity, costPrice, sellingPrice, discount, sale, Category, subCategory, subSubCategory) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        stmt.setString(1, product.getCat());
        stmt.setString(2, product.getName());
        stmt.setString(3, product.getArea());
        stmt.setString(4, product.getManufacturer());
        stmt.setInt(5, product.getMinQuantity());
        stmt.setDouble(6, product.getCostPrice());
        stmt.setDouble(7, product.getSellingPrice());
        stmt.setDouble(8, product.getDiscount());
        stmt.setDouble(9, product.getSale());
        stmt.setString(10, product.getCat());
        stmt.setString(11, product.getSubCat());
        stmt.setString(12, product.getSubSubCat());
        stmt.executeUpdate();

    }

    @Override
    public void remove(String name) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM products WHERE name = ?");
        stmt.setString(1, name); //FIXME
        stmt.executeUpdate();
    }

    @Override
    public Product get(String name) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE name = ?");
        stmt.setString(1, name); //FIXME
        ResultSet rs = stmt.executeQuery();
        Product product = null;
        if (rs.next()) {
            product = new Product(
                    rs.getString("catNum"),
                    rs.getString("name"),
                    rs.getString("area"),
                    rs.getString("manufacturer"),
                    rs.getInt("minQuantity"),
                    rs.getDouble("costPrice"),
                    rs.getDouble("sellingPrice"),
                    rs.getDouble("discount"),
                    rs.getDouble("sale"),
                    rs.getString("category"),
                    rs.getString("subCategory"),
                    rs.getString("subSubCategory")
            );
        return product;
        } else {
            return null;
        }
    }

    //FIXME ADD
    public void updateDiscount(String name, double discount) {
    }

    //FIXME ADD
    public void updateSale(String name, double sale) {
    }
}


