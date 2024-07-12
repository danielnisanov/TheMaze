package Domain;
import Dal.DatabaseConnection;
import Dal.WorkArrangementDAO;
import Dal.WorkersDAO;

import java.util.*;
import java.sql.SQLException;

public class WorkArrangementRepository implements IRepository<Shift> {
    private final ArrayList<Shift> weeklyWorkArrangement;
    public WorkArrangementDAO workArrangementDAO;

    public WorkArrangementRepository(DatabaseConnection dbConnection){
        this.weeklyWorkArrangement= new ArrayList<>();
        for (int i = 0 ; i < 14; i++)
            weeklyWorkArrangement.add(new Shift(i/7,(i%2 == 1)? "Evening" : "Morning",new ArrayList<Worker>()));
        this.workArrangementDAO = new WorkArrangementDAO(dbConnection);
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

//
//    public ArrayList<Shift> getWeeklyWorkArrangement() {
//        weeklyWorkArrangement.clear();
//        try {
//            for (int i = 1; i <= 14; i++) { // Loop through shift_id from 1 to 14
//                ArrayList<Shift> foundShifts = workArrangementDAO.Find(i);
//                if (foundShifts != null) {
//                    weeklyWorkArrangement.addAll(foundShifts);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        for (Shift shift : weeklyWorkArrangement) {
//            System.out.println("Shift in arrangement: " + shift); // Debug print
//        }
//        return weeklyWorkArrangement;
//    }

    public ArrayList<Shift> getWeeklyWorkArrangement() {
        if (!weeklyWorkArrangement.isEmpty()) {
            return weeklyWorkArrangement;
        } else {
            try {
                for(int i = 0; i < 14; i++) {
                    ArrayList<Shift> foundShifts = workArrangementDAO.Find(i);
                    if (foundShifts != null) {
                        weeklyWorkArrangement.addAll(foundShifts);
                    }
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

//    public void init_branch_week() {
//        for (int i = 0; i < 7; i++) {
//            Shift morningShift = new Shift(i + 1, "Morning", new ArrayList<>());
//            Shift eveningShift = new Shift(i + 1, "Evening", new ArrayList<>());
//            weeklyWorkArrangement.add(morningShift); // todo
//            weeklyWorkArrangement.add(eveningShift);
//
//        }
//    }


}
