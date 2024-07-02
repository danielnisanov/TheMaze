package Domain;

import java.sql.SQLException;

public class WorkersOnBranchRepository implements IRepository<Worker> {

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
    public boolean Find(int num) throws SQLException {
        return false;
    }
}
