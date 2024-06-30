package DAL;

import java.sql.SQLException;

public interface IDAO<T> {
    void add(T entity)  throws SQLException;

    void remove(String id) throws SQLException;

    T get(String id) throws SQLException;

    //void update // FIXME ADD
}