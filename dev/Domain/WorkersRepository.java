package Domain;

import Dal.WorkersDAO;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class WorkersRepository implements IRepository<Worker> {

    private Map<Integer, Worker> workers;
    private WorkersDAO workersDAO;

    public WorkersRepository() {
        this.workers = new HashMap<>();

    }

    @Override
    public boolean Insert(Worker worker) {
        if (workers.containsKey(worker.getID_number())) {
            return false; // Worker already exists
        } else {
            try {
                workersDAO.Insert(worker); // Add worker to the database
                workers.put(worker.getID_number(), worker); // Add worker to the map
                return true;
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exception
                return false;
            }
        }
    }

    @Override
    public boolean Delete(int id) {
        Worker worker = workers.get(id);
        if (worker != null && worker.getJob_status()) {
            worker.setJob_status(false);
            try {
                workersDAO.Update(id, "false", "job_status");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                worker = workersDAO.Find(id);
                if (worker != null && worker.getJob_status()) {
                    worker.setJob_status(false);
                    workersDAO.Update(id, "false", "job_status");
                    workers.put(id, worker);
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    @Override
    public boolean Update(int id, String field, String value) {
        Worker worker = workers.get(id);
        if (worker != null) {
            try {
                boolean updated = workersDAO.Update(id, field, value);
                if (updated) {
                    updateWorkerField(worker, field, value);
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                worker = workersDAO.Find(id);
                if (worker != null) {
                    boolean updated = workersDAO.Update(id, field, value);
                    if (updated) {
                        updateWorkerField(worker, field, value);
                        workers.put(id, worker);
                        return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private void updateWorkerField(Worker worker, String field, String value) {
        switch (field) {
            case "bank_account_num":
                worker.setBank_account_num(Integer.parseInt(value));
                break;
            case "job_type":
                worker.setJob_type(JobType.valueOf(value));
                break;
            case "hourly_salary":
                worker.setHourly_salary(Double.parseDouble(value));
                break;
            case "branch": // todo - in the branch repository
//                int branchNum = Integer.parseInt(value);
//                Branch branch = branches.get(branchNum);
//                if (branch == null) {
//                    branch = new Branch(branchNum);
//                    branches.put(branchNum, branch);
//                }
//                worker.setBranch(branch);
                break;
            case "job_status":
                worker.setJob_status(Boolean.parseBoolean(value));
                break;
            case "roles":
                worker.getRoles_permissions().add(Role.Shift_manager);
                break;
        }
    }


    @Override
    public boolean Find(int id) throws SQLException {
        return workers.containsKey(id) || workersDAO.Find(id) != null;
    }


}
