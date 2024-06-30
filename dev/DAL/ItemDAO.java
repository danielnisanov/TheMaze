package DAL;
import Domain.Item;
import DB.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemDAO implements IDAO<Item> {
    private Connection conn;
    public ItemDAO() throws SQLException {
            conn = DataBase.connect();
        }

    @Override
    public void add(Item item) throws SQLException {
//        PreparedStatement stmt = conn.prepareStatement("INSERT INTO items (productName,dateExpirationDate,boolOnShelf) VALUES (?, ?, ?)");
//        stmt.setString(1, item.getName());
//        stmt.setString(2, item.getName());
//        stmt.setString(3, item.getName());
//        stmt.executeUpdate();
    }



    @Override
    public void remove(String id) throws SQLException {

    }

    @Override
    public Item get(String id) throws SQLException {
        return null;
    }
}
