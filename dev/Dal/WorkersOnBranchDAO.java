package Dal;

import Domain.Worker;

import java.sql.SQLException;
import java.util.Map;

public class WorkersOnBranchDAO implements IDAO<Worker> {


    public WorkersOnBranchDAO() {

    }

    @Override
    public void Insert(Worker obj) {

    }

    @Override
    public void Delete() throws SQLException {

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
