package Domain;

import Dal.WorkArrangementDAO;
import Dal.WorkersOnShiftDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkersOnShiftRepository implements IRepository<Worker> {
    public ArrayList<Worker> workers_on_shift;
    public WorkersOnShiftDAO workersOnShiftDAO;

    public WorkersOnShiftRepository (){
        this.workers_on_shift = new ArrayList<>()  ;
    }

    @Override
    public boolean Insert(Worker worker) {
        if (workers_on_shift.contains(worker.getID_number())) {
            return false; // Worker already exists
        } else {
            try {
                workersOnShiftDAO.Insert(worker); // Add worker to the database
                workers_on_shift.add(worker); // Add worker to the map
                return true;
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exception
                return false;
            }
        }
    }

    @Override
    public boolean Update(int num, String field, String change) throws SQLException {
        return false;
    }

    @Override
    public boolean Delete() {
        try {
            // Delete all workers from the database
            workersOnShiftDAO.Delete();
            // Clear the list
            workers_on_shift.clear();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception
            return false;
        }
    }

    @Override
    public Worker Find(int num) throws SQLException {
        return null;
    }
}