package Dal;

import Domain.Shift;

import java.sql.SQLException;

public class ShiftHDAO implements IDAO<Shift> {
    @Override
    public void Insert(Shift shift) {

    }

    @Override
    public void Delete(int num) throws SQLException {

    }

    @Override
    public Shift Find(int num) throws SQLException {
        return null;
    }

    @Override
    public boolean Update(int num, String field, String change) throws SQLException {
        return false;
    }


}
