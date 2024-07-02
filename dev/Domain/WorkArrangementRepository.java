package Domain;
import java.util.*;
import java.sql.SQLException;

public class WorkArrangementRepository implements IRepository<Shift> {
    private final ArrayList<Shift> weeklyWorkArrangement = new ArrayList<>();

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

    public ArrayList<Shift> getWeeklyWorkArrangement() {
        return weeklyWorkArrangement;
    }
}
