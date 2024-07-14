package Domain;

import Dal.DatabaseConnection;
import Dal.WorkersDAO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class WorkersRepositoryWorkers implements IRepositoryWorkers<Worker> {
    private Map<Integer, Worker> workers;
    private WorkersDAO workersDAO;
    public BranchesRepositoryWorkers branchesRepository;

    public WorkersRepositoryWorkers(DatabaseConnection dbConnection, BranchesRepositoryWorkers branchesRepository) {
        this.workers = new HashMap<>();
        this.branchesRepository = branchesRepository;
        this.workersDAO = new WorkersDAO(dbConnection, branchesRepository );

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
    public boolean Delete() {
        return false;
    }

    @Override
    public boolean Update(int id, String field, String value) {
        Worker worker = workers.get(id);
        if (worker != null) { // if we found the worker in the repository
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
                worker = workersDAO.Find(id); // if we did not find the worker in the repository
                if (worker != null) {
                    boolean updated = workersDAO.Update(id, field, value); // update in the DAO
                    if (updated) {
                        updateWorkerField(worker, field, value);
                        workers.put(id, worker); // add to workers
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
            case "branch":
                int branchNum = Integer.parseInt(value);
                Branch branch = branchesRepository.Find(branchNum);
                worker.setBranch(branch);
                break;
            case "job_status": // fire a worker
                worker.setJob_status(Boolean.parseBoolean(value));
                break;
            case "roles": // update worker roles - add Shift_manager
                worker.getRoles_permissions().add(Role.Shift_manager);
                break;
            case "constraints":
                // Deserialize the JSON string back to the map
                Map<String, List<String>> constraints = new Gson().fromJson(value, new TypeToken<Map<String, List<String>>>() {}.getType());
                worker.setConstraints(constraints);
                break;
            case "Total_hours":
                worker.setTotal_hours(worker.getTotal_hours() + Integer.parseInt(value));
        }
    }

    public Map<Integer, Worker> get_All_Workers() {
        getAllWorkers();
        return workers;
    }

    public Map<Integer, Worker> get_Workers() {
        return workers;
    }


    @Override
    public Worker Find(int id) {
        Worker worker = workers.get(id);

        if (worker == null) { // I did not find the worker in workers
            try {
                worker = workersDAO.Find(id); // look for him in DAO
                if (worker != null) { // found him in workers table
                    workers.put(id, worker); // add him to workers
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return worker;
    }

    public void getAllWorkers() {
        try {
            List<Worker> allWorkers = workersDAO.getAllWorkers();
            for (Worker worker : allWorkers) {
                workers.put(worker.getID_number(), worker); // Ensure all workers are added to the map
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JsonArray PresentWorkers(Branch branch) {
        getAllWorkers();

        JsonArray jsonArray = new JsonArray();
        for (Map.Entry<Integer, Worker> entry : workers.entrySet()) {
            if (branch.is_worker_in_branch(entry.getValue().getID_number())) {
                Worker worker = entry.getValue();
                if (worker.getJob_status()){
                    JsonObject workerJson = new JsonObject();
                    workerJson.addProperty("id", worker.getID_number());
                    workerJson.addProperty("name", worker.getName());
                    workerJson.addProperty("address", worker.getAddress());
                    workerJson.addProperty("bank_account", worker.getBank_account_num());
                    workerJson.addProperty("hourly_salary", worker.getHourly_salary());
                    workerJson.addProperty("vacation_days", worker.getVaction_days());
                    workerJson.addProperty("job_type", worker.getJob_type().toString());
                    workerJson.addProperty("branch_num", worker.getBranchNum());
                    workerJson.addProperty("roles", worker.getRoles_permissions().toString());
                    workerJson.addProperty("starting_day", worker.getStarting_day().toString());
                    workerJson.addProperty("total_hours", worker.getTotal_hours());
                    workerJson.addProperty("job_status", worker.getJob_status());
                    jsonArray.add(workerJson);
                }
            }
        }

        return jsonArray;
    }

    public JsonArray PresentPastWorkers(Branch branch) {
        getAllWorkers();

        JsonArray jsonArray = new JsonArray();
        for (Map.Entry<Integer, Worker> entry : workers.entrySet()) {
            if (branch.is_worker_in_branch(entry.getValue().getID_number())) {
                Worker worker = entry.getValue();
                if (!worker.getJob_status()) {
                    JsonObject workerJson = new JsonObject();
                    workerJson.addProperty("id", worker.getID_number());
                    workerJson.addProperty("name", worker.getName());
                    workerJson.addProperty("address", worker.getAddress());
                    workerJson.addProperty("bank_account", worker.getBank_account_num());
                    workerJson.addProperty("hourly_salary", worker.getHourly_salary());
                    workerJson.addProperty("vacation_days", worker.getVaction_days());
                    workerJson.addProperty("job_type", worker.getJob_type().toString());
                    workerJson.addProperty("branch_num", worker.getBranchNum());
                    workerJson.addProperty("roles", worker.getRoles_permissions().toString());
                    workerJson.addProperty("starting_day", worker.getStarting_day().toString());
                    workerJson.addProperty("total_hours", worker.getTotal_hours());
                    workerJson.addProperty("job_status", worker.getJob_status());
                    jsonArray.add(workerJson);
                }
            }
        }
        return jsonArray;
    }

    public List<Worker> Available_Workers(String day, String shiftType, Role role, Branch branch) {
        List<Worker> availableWorkersList = new ArrayList<>();

        getAllWorkers();

        for (Map.Entry<Integer, Worker> entry : workers.entrySet()) {
            Worker worker = entry.getValue();
            Map<String, List<String>> constraints = worker.getConstraints();

            if ((branch.getBranchNum() == worker.branch.getBranchNum()) &&
                    (worker.getRoles_permissions().contains(role)) &&
                    (constraints == null || !constraints.containsKey(day) || !constraints.get(day).contains(shiftType))) {
                availableWorkersList.add(worker);
            }
        }
        return availableWorkersList;
    }

    public void Set_Worker_Constraints(Map<String, List<String>> constraintMap, int id) {
        Worker worker = workers.get(id);

        if (worker == null) { // I did not find the worker in workers
            try {
                worker = workersDAO.Find(id); // look for him in DAO
                if (worker != null) { // found him in workers table
                    workers.put(id, worker); // add him to workers
                    worker.setConstraints(constraintMap);
                    workersDAO.updateConstraints(id, constraintMap); // update constraint in DAO
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (worker != null) { // if i found in workers
            worker.setConstraints(constraintMap); // set his constraints
            try {
                workersDAO.updateConstraints(id, constraintMap); // update constraint in DAO
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean Is_Worker(int id) {
        Worker worker = workers.get(id);
        if (worker == null) {
            try {
                worker = workersDAO.Find(id);
                if (worker != null) {
                    workers.put(id, worker); // Cache the worker
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return worker != null && worker.getJob_status();
    }

    public double FindGlobalSalary(int id)  {
        Worker worker = workers.get(id);
        if (worker == null) { // I did not find the worker in workers
            try {
                worker = workersDAO.Find(id); // look for him in DAO
                if (worker != null) { // found him in workers table
                    workers.put(id, worker); // add him to workers
                    return worker.Global_salary(worker.getHourly_salary(), worker.getTotal_hours());
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (worker != null) { // if i found him in workers
            return worker.Global_salary(worker.getHourly_salary(), worker.getTotal_hours());
        }
        return -1;
    }

    public int FindVactionDay(int id) {
        Worker worker = workers.get(id);
        if (worker == null) { // I did not find the worker in workers
            try {
                worker = workersDAO.Find(id); // look for him in DAO
                if (worker != null) { // found him in workers table
                    workers.put(id, worker); // add him to workers
                    return worker.getVaction_days();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (worker != null) { // if i found him in workers
            return worker.getVaction_days();
        }
        return 0;

    }

    public LocalDate FindStartDate(int id) {
        Worker worker = workers.get(id);
        if (worker == null) { // I did not find the worker in workers
            try {
                worker = workersDAO.Find(id); // look for him in DAO
                if (worker != null) { // found him in workers table
                    workers.put(id, worker); // add him to workers
                    return worker.getStarting_day();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (worker != null) { // if i found him in workers
            return worker.getStarting_day();
        }
        return null;

    }

    public JsonObject PresentWorker(int id) {
        Worker worker = workers.get(id);
        if (worker == null) { // I did not find the worker in workers
            try {
                worker = workersDAO.Find(id); // look for him in DAO
                if (worker != null) { // found him in workers table
                    workers.put(id, worker); // add him to workers
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // If worker is found and is currently employed, convert to JsonObject
        if (worker != null && worker.getJob_status()) {
            JsonObject workerJson = new JsonObject();
            workerJson.addProperty("id", worker.getID_number());
            workerJson.addProperty("name", worker.getName());
            workerJson.addProperty("address", worker.getAddress());
            workerJson.addProperty("bank_account", worker.getBank_account_num());
            workerJson.addProperty("hourly_salary", worker.getHourly_salary());
            workerJson.addProperty("vacation_days", worker.getVaction_days());
            workerJson.addProperty("job_type", worker.getJob_type().toString());
            workerJson.addProperty("branch_num", worker.getBranchNum());
            workerJson.addProperty("roles", worker.getRoles_permissions().toString());
            workerJson.addProperty("starting_day", worker.getStarting_day().toString());
            workerJson.addProperty("total_hours", worker.getTotal_hours());
            workerJson.addProperty("job_status", worker.getJob_status());
            return workerJson;
        }

        // Return null if worker is not found or not currently employed
        return null;
    }




}
