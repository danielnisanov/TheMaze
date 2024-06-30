package Domain;

import java.sql.SQLException;
import java.util.Map;

public class BranchesRepository implements IRepository<Branch>{
        private Map<Integer, Branch> branches;


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
