package DAL.DALstock;
import DB.DataBase;
import Domain.Domainstock.Item;
import Domain.Domainstock.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;



public class ProductDAO implements IDAO<Product> {
    private Connection conn;

    public ProductDAO() throws SQLException {
        conn = DataBase.connect();
    }

    @Override
    public void add(Product product) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO products( itemIndex, catNum, name, area, manufacturer, currentQuantity, shelfQuantity, warehouseQuantity, minQuantity, costPrice, sellingPrice, discount, sale, Category, subCategory, subSubCategory) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
        stmt.setInt(1,product.getItemIndex());
        stmt.setString(2, product.getCatNum());
        stmt.setString(3, product.getName());
        stmt.setString(4, product.getArea());
        stmt.setString(5, product.getManufacturer());
        stmt.setInt(6, product.getCurrentQuantity());
        stmt.setInt(7, product.getShelfQuantity());
        stmt.setInt(8, product.getWarehouseQuantity());
        stmt.setInt(9, product.getMinQuantity());
        stmt.setDouble(10, product.getCostPrice());
        stmt.setDouble(11, product.getSellingPrice());
        stmt.setDouble(12, product.getDiscount());
        stmt.setDouble(13, product.getSale());
        stmt.setString(14, product.getCat());
        stmt.setString(15, product.getSubCat());
        stmt.setString(16, product.getSubSubCat());
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void remove(String name) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM products WHERE name = ?");
        stmt.setString(1, name);
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public Product get(String name) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM products WHERE name = ?");
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        Product product = null;
        if (rs.next()) {
            product = new Product(
                    rs.getInt("itemIndex"),
                    rs.getString("catNum"),
                    rs.getString("name"),
                    rs.getString("area"),
                    rs.getString("manufacturer"),
                    rs.getInt("currentQuantity"),
                    rs.getInt("shelfQuantity"),
                    rs.getInt("warehouseQuantity"),
                    rs.getInt("minQuantity"),
                    rs.getDouble("costPrice"),
                    rs.getDouble("sellingPrice"),
                    rs.getDouble("discount"),
                    rs.getDouble("sale"),
                    rs.getString("category"),
                    rs.getString("subCategory"),
                    rs.getString("subSubCategory")
            );
            rs.close();
            stmt.close();
            return product;
        } else {
            return null;
        }
    }

    @Override
    public void update(Product product) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE products SET itemIndex=?, catNum=?, name=?, area=?, manufacturer=?, currentQuantity=?, shelfQuantity=?, warehouseQuantity=?, minQuantity=?, costPrice=?, sellingPrice=?, discount=?, sale=?, Category=?, subCategory=?, subSubCategory=? WHERE name = ?") ;

      //  PreparedStatement stmt = conn.prepareStatement("UPDATE products SET catNum = ?, area = ?, manufacturer = ?, minQuantity = ?, costPrice = ?, sellingPrice = ?, discount = ?, sale = ?, category = ?, subCategory = ?, subSubCategory = ? WHERE name = ?");
        stmt.setInt(1, product.getItemIndex());
        stmt.setString(2, product.getCatNum());
        stmt.setString(3, product.getName());
        stmt.setString(4, product.getArea());
        stmt.setString(5, product.getManufacturer());
        stmt.setInt(6, product.getCurrentQuantity());
        stmt.setInt(7, product.getShelfQuantity());
        stmt.setInt(8, product.getWarehouseQuantity());
        stmt.setInt(9, product.getMinQuantity());
        stmt.setDouble(10, product.getCostPrice());
        stmt.setDouble(11, product.getSellingPrice());
        stmt.setDouble(12, product.getDiscount());
        stmt.setDouble(13, product.getSale());
        stmt.setString(14, product.getCat());
        stmt.setString(15, product.getSubCat());
        stmt.setString(16, product.getSubSubCat());
        stmt.setString(17, product.getName());
        stmt.executeUpdate();
        stmt.close();
    }


    public List<Product> getProductsByCategory(String category, String subCategory, String subSubCategory) throws SQLException {
        List<Product> products = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            StringBuilder query = new StringBuilder("SELECT * FROM products WHERE 1=1");

            if (category != null && !category.isEmpty()) {
                query.append(" AND category = ?");
            }
            if (subCategory != null && !subCategory.isEmpty()) {
                query.append(" AND subCategory = ?");
            }
            if (subSubCategory != null && !subSubCategory.isEmpty()) {
                query.append(" AND subSubCategory = ?");
            }

            stmt = conn.prepareStatement(query.toString());

            int parameterIndex = 1;
            if (category != null && !category.isEmpty()) {
                stmt.setString(parameterIndex++, category);
            }
            if (subCategory != null && !subCategory.isEmpty()) {
                stmt.setString(parameterIndex++, subCategory);
            }
            if (subSubCategory != null && !subSubCategory.isEmpty()) {
                stmt.setString(parameterIndex, subSubCategory);
            }

            rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("itemIndex"),
                        rs.getString("catNum"),
                        rs.getString("name"),
                        rs.getString("area"),
                        rs.getString("manufacturer"),
                        rs.getInt("currentQuantity"),
                        rs.getInt("shelfQuantity"),
                        rs.getInt("warehouseQuantity"),
                        rs.getInt("minQuantity"),
                        rs.getDouble("costPrice"),
                        rs.getDouble("sellingPrice"),
                        rs.getDouble("discount"),
                        rs.getDouble("sale"),
                        rs.getString("category"),
                        rs.getString("subCategory"),
                        rs.getString("subSubCategory")
                );
                products.add(product);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return products;
    }

    public List<Product> getMissingProductsByCategory(String category, String subCategory, String subSubCategory) throws SQLException {
        List<Product> products = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            StringBuilder query = new StringBuilder("SELECT * FROM products WHERE 1=1");

            if (category != null && !category.isEmpty()) {
                query.append(" AND category = ?");
            }
            if (subCategory != null && !subCategory.isEmpty()) {
                query.append(" AND subCategory = ?");
            }
            if (subSubCategory != null && !subSubCategory.isEmpty()) {
                query.append(" AND subSubCategory = ?");
            }

            stmt = conn.prepareStatement(query.toString());

            int parameterIndex = 1;
            if (category != null && !category.isEmpty()) {
                stmt.setString(parameterIndex++, category);
            }
            if (subCategory != null && !subCategory.isEmpty()) {
                stmt.setString(parameterIndex++, subCategory);
            }
            if (subSubCategory != null && !subSubCategory.isEmpty()) {
                stmt.setString(parameterIndex, subSubCategory);
            }

            rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("itemIndex"),
                        rs.getString("catNum"),
                        rs.getString("name"),
                        rs.getString("area"),
                        rs.getString("manufacturer"),
                        rs.getInt("currentQuantity"),
                        rs.getInt("shelfQuantity"),
                        rs.getInt("warehouseQuantity"),
                        rs.getInt("minQuantity"),
                        rs.getDouble("costPrice"),
                        rs.getDouble("sellingPrice"),
                        rs.getDouble("discount"),
                        rs.getDouble("sale"),
                        rs.getString("category"),
                        rs.getString("subCategory"),
                        rs.getString("subSubCategory")
                );

                if (product.getCurrentQuantity() <= product.getMinQuantity()) {
                    products.add(product);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return products;
    }

    public Map<Item, String> getDamagedItems() throws SQLException{
        Map<Item, String> damagedItemsList = new HashMap<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM items WHERE isDamaged = ?";
            stmt = conn.prepareStatement(query);
            stmt.setBoolean(1, true);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Item item = new Item(
                        rs.getString("productName"),
                        rs.getInt("itemID"),
                        rs.getDate("expirationDate").toLocalDate(),
                        rs.getBoolean("isDamaged"),
                        rs.getBoolean("onShelf")
                );
                damagedItemsList.put(item, rs.getString("productName"));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return damagedItemsList;
    }

    public Map<Item, String> getExpiredItems() throws SQLException{
        Map<Item, String> expiredItemsList = new HashMap<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM items WHERE expirationDate < ?";
            stmt = conn.prepareStatement(query);
            stmt.setDate(1, java.sql.Date.valueOf(LocalDate.now())); // Assuming you want to find items expired before today
            rs = stmt.executeQuery();

            while (rs.next()) {
                Item item = new Item(
                        rs.getString("productName"),
                        rs.getInt("itemID"),
                        rs.getDate("expirationDate").toLocalDate(),
                        rs.getBoolean("isDamaged"),
                        rs.getBoolean("onShelf")
                );
                expiredItemsList.put(item, rs.getString("productName"));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return expiredItemsList;
    }

    public void showProducts() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT name FROM products";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("name");
                System.out.println(productName);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void showAllItems() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT itemID, productName " +
                    "FROM items";

            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int itemID = rs.getInt("itemID");
                String productName = rs.getString("productName");
                System.out.println(itemID + " " + productName );

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}


