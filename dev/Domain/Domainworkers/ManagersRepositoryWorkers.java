package Domain.Domainworkers;
import DAL.DALworkers.DatabaseConnection;
import DAL.DALworkers.ManagersDAO;

import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;

public class ManagersRepositoryWorkers implements IRepositoryWorkers<HRManager> {
    private Map<Integer, HRManager> managers;
    private ManagersDAO managersDAO;

    public ManagersRepositoryWorkers(DatabaseConnection dbConnection, BranchesRepositoryWorkers BR) {
        this.managers = new HashMap<>();
        this.managersDAO = new ManagersDAO(dbConnection,BR); // Initialize managersDAO
    }

    @Override
    public boolean Insert(HRManager hrManager) {
        if (managers.containsKey(hrManager.getID_number())) {
            return false; // Manager already exists
        } else {
            managersDAO.Insert(hrManager); // Add manager to the database
            managers.put(hrManager.getID_number(), hrManager); // Add manager to the map
            return true;
        }
    }

    public HRManager getManager(int id) {
        HRManager hrManager = managers.get(id);

        if (hrManager == null) { // I did not find the worker in workers
            try {
                hrManager = managersDAO.Find(id); // look for him in DAO
                if (hrManager != null) { // found him in workers table
                    managers.put(id, hrManager); // add him to workers
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return hrManager;
    }

    @Override
    public HRManager Find(int num) throws SQLException {
        return null;
    }



    @Override
    public boolean Update(int id, String field, String newValue) {
        if ("password".equalsIgnoreCase(field)) {
            try {
                HRManager manager = managers.get(id);
                if (manager != null) {
                    manager.changePassword(newValue); // Assume there's a setPassword method
                    managersDAO.Update(id, field, newValue);
                    return true;
                } else {
                    manager = managersDAO.Find(id);
                    if (manager != null) {
                        manager.changePassword(newValue);
                        managers.put(id, manager);
                        managersDAO.Update(id, field, newValue);
                        return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle SQL exception
            }
        }
        return false;
    }


    @Override
    public boolean Delete() {
        return false;
    }




}

