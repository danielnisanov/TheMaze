package Domain;

import java.sql.SQLException;

public class BranchesRepository implements IRepository<Branch>{


    @Override
    public boolean Insert(Branch obj) {
        return false;
    }

    @Override
    public boolean Update(int num, String field, String change) throws SQLException {
        return false;
    }

    @Override
    public boolean Delete(int id) {
        return false;
    }

    @Override
    public boolean Find(int num) throws SQLException {
        return false;
    }
}
