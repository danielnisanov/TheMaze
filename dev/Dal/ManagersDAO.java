package Dal;

import Domain.HRManager;

import java.sql.SQLException;

public class ManagersDAO implements IDAO<HRManager> {

    @Override
    public void Insert(HRManager obj) {

    }

    @Override
    public void Delete(int num) throws SQLException {

    }

    @Override
    public HRManager Find(int num) throws SQLException {
        return null;
    }

    @Override
    public boolean Update(int num, String field, String change) throws SQLException {
        return false;
    }


}
