package DAL.DALworkers;

import Domain.Domainworkers.Worker;

import java.sql.SQLException;

public interface IDAO<T> {
    void Insert(T obj) throws SQLException;
    void Delete() throws SQLException;
    T Find(int num) throws SQLException; // Changed to return T
    boolean Update(int num,String field, String change)  throws SQLException;
}

