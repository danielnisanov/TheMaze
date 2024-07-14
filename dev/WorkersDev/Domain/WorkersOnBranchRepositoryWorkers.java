package Domain;

import java.sql.SQLException;
import java.util.Map;

public class WorkersOnBranchRepositoryWorkers implements IRepositoryWorkers<Worker> {
    private final Map<Integer, Worker> workers_on_brunch;

    public WorkersOnBranchRepositoryWorkers(Map<Integer, Worker> workersOnBrunch){
        workers_on_brunch = workersOnBrunch;
    }
    @Override
    public boolean Insert(Worker obj) {
        return false;
    }

    @Override
    public boolean Update(int num, String field, String change) throws SQLException {
        return false;
    }

    @Override
    public boolean Delete() {
        return false;
    }

    @Override
    public Worker Find(int num) throws SQLException {
        return null;
    }
}
