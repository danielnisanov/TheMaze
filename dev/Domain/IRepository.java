package Domain;

import java.sql.SQLException;

public interface IRepository<T> {
    public boolean Insert(T obj);
    boolean Update(int num,String field, String change)  throws SQLException;
    public  boolean Delete(int id);
    boolean Find(int num) throws SQLException;
}
