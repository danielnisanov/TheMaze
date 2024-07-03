package Domain;

import java.sql.SQLException;

public interface IRepository<T> {
    public boolean Insert(T obj);
    boolean Update(int num,String field, String change)  throws SQLException;
    public  boolean Delete();
    T Find(int num) throws SQLException;
}
