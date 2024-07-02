package DAL;
import Domain.Item;
import DB.DataBase;
import Domain.Product;

import java.sql.*;
import java.time.LocalDate;

public class ItemDAO implements IDAO<Item> {
    private Connection conn;
    public ItemDAO() throws SQLException {
            conn = DataBase.connect();
        }


    @Override
    public void add(Item item) throws SQLException {
        java.sql.Date sqlDate = java.sql.Date.valueOf(item.getExpirationDate());
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO items (productName,itemID, expirationDate,isDamaged, onShelf) VALUES (?,?, ?, ?,?)");
        stmt.setString(1, item.getProductName());
        stmt.setInt(2, item.getItemID());
        stmt.setDate(3, sqlDate);
        stmt.setBoolean(4, item.isOnShelf());
        stmt.setBoolean(5, item.isDamaged());
        stmt.executeUpdate();
    }

    @Override
    public void remove(String id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM items WHERE itemID = ?");
        stmt.setString(1, id);
        stmt.executeUpdate();
    }


    @Override
    public Item get(String id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM items WHERE itemID = ?");
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        Item item = null;
        if (rs.next()) {
            LocalDate expirationDate = rs.getDate("expirationDate").toLocalDate();
            item = new Item(
                    rs.getString("productName"),
                    rs.getInt("itemID"),
                    expirationDate,
                    rs.getBoolean("isDamaged"),
                    rs.getBoolean("onShelf")
            );
            return item;
        } else {
            return null;
        }
    }

    @Override
    public void update(Item item) throws SQLException {
        Date sqlDate = Date.valueOf(item.getExpirationDate());
        PreparedStatement stmt = conn.prepareStatement("UPDATE items SET productName = ?,itemID=?, expirationDate = ?, onShelf = ?, isDamaged = ? WHERE itemID = ?");
        stmt.setString(1, item.getProductName());
        stmt.setInt(2, item.getItemID());
        stmt.setDate(3, sqlDate);
        stmt.setBoolean(4, item.isOnShelf());
        stmt.setBoolean(5, item.isDamaged());
        stmt.executeUpdate();
    }
}
