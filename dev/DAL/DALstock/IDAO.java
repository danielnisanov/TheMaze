package DAL.DALstock;
import Domain.Domainstock.Item;
import java.sql.SQLException;

public interface IDAO<T> {
    void add(T entity)  throws SQLException;

    void remove(String id) throws SQLException;

    T get(String id) throws SQLException;

    void update(T entity) throws SQLException;

}