package Domain;

import java.sql.SQLException;

public class ShiftHRepository implements IRepository<Shift>{

    @Override
    public boolean Insert(Shift obj) {
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
