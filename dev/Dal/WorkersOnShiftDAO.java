package Dal;

import Domain.Worker;

import java.sql.SQLException;

public class WorkersOnShiftDAO implements IDAO<Worker> {
    @Override
    public void Insert(Worker obj) {

    }

    @Override
    public void Delete(int num) throws SQLException {

    }

    @Override
    public Worker Find(int num) throws SQLException {
        return null;
    }

    @Override
    public boolean Update(int num, String field, String change) throws SQLException {
        return false;
    }

}
