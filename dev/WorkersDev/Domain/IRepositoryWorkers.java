package Domain;

import java.sql.SQLException;

public interface IRepositoryWorkers<T> {
    public boolean Insert(T obj);
    boolean Update(int num,String field, String change)  throws SQLException;
    public  boolean Delete();
    T Find(int num) throws SQLException;
}
