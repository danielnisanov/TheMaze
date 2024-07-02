package Domain;
import Dal.WorkArrangementDAO;

import java.util.*;
import java.sql.SQLException;

public class WorkArrangementRepository implements IRepository<Shift> {
    private final ArrayList<Shift> weeklyWorkArrangement;
    public WorkArrangementDAO workArrangementDAO;

    public WorkArrangementRepository(){
        this.weeklyWorkArrangement= new ArrayList<>();


    }
    @Override
    public boolean Insert(Shift obj) {
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


    public ArrayList<Shift> getWeeklyWorkArrangement(int num ) {
        if (!weeklyWorkArrangement.isEmpty()) {
            return weeklyWorkArrangement;
        } else {
            try {
                ArrayList<Shift> foundShifts = workArrangementDAO.Find(num);
                if (foundShifts != null) {
                    weeklyWorkArrangement.addAll(foundShifts);
                }
                return weeklyWorkArrangement;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    @Override
    public Shift Find(int day){
        return null;
    }

    public void init_branch_week() {
        for (int i = 0; i < 7; i++) {
            Shift morningShift = new Shift(i + 1, "Morning", new ArrayList<>());
            Shift eveningShift = new Shift(i + 1, "Evening", new ArrayList<>());
            weeklyWorkArrangement.add(morningShift); // todo
            weeklyWorkArrangement.add(eveningShift);

        }
    }


}
