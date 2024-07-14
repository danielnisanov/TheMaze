package Domain.Domainworkers;
import DAL.DALworkers.DatabaseConnection;
import DAL.DALworkers.WorkArrangementDAO;

import java.util.*;
import java.sql.SQLException;

public class WorkArrangementRepositoryWorkers implements IRepositoryWorkers<Shift> {
    private final ArrayList<Shift> weeklyWorkArrangement;
    public WorkArrangementDAO workArrangementDAO;

    public WorkArrangementRepositoryWorkers(DatabaseConnection dbConnection){
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




}
