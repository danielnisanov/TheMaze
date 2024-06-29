package Dal;

import Domain.Branch;
import Domain.Worker;

import java.sql.SQLException;

public class BranchsDAO implements IDAO<Branch> {

    @Override
    public void Insert(Branch obj) {

    }

    @Override
    public void Delete(int num) throws SQLException {

    }

    @Override
    public Branch Find(int num) throws SQLException {
        return null;
    }

    @Override
    public boolean Update(int num, String field, String change) throws SQLException {
        return false;
    }

}
