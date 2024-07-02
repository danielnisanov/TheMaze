package Dal;

import Domain.Worker;

import java.sql.SQLException;
import java.util.Map;

public class WorkersOnBranchDAO implements IDAO<Worker> {
    private final Map<Integer, Worker> workers_on_brunch;

    public WorkersOnBranchDAO(Map<Integer, Worker> workersOnBrunch) {
        workers_on_brunch = workersOnBrunch;
    }

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
