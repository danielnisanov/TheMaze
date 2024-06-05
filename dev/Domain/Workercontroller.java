package Domain;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.*;

import static Domain.Role.Shift_manager;


public class WorkerController {
    private Map<Integer, HRManager> managers;
    private Map<Integer, Branch> branches;
    private Map<Integer, Worker> workers; // workers map<id,worker>
    private Map<Integer, Map<String, List<String>>> workerConstraints;//all workers available map<id,unavailable time>

    public WorkerController(String file) {
        workers = new HashMap<>();
        managers = new HashMap<>();
        try {
            managers = openFileIntoListsManagers(file, managers);
            workers = openFileIntoListsWorkers(file, workers);
            branches = openFileIntoListsBranch(file, branches);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private Map<Integer, HRManager> openFileIntoListsManagers(String file, Map<Integer, HRManager> managers) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(file));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String parts[] = line.split(",");
            if (parts[0].equals("manager")) {
                managers.put(Integer.valueOf(parts[4]), new HRManager(parts[1], Integer.valueOf(parts[2]), parts[3], Integer.valueOf(parts[4])));
            }
        }
        return managers;
    }

    private Map<Integer, Worker>  openFileIntoListsWorkers(String file, Map<Integer, Worker> workers) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(file));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String parts[] = line.split(",");
            if (parts[0].equals("worker")) {
                String address = parts[1];
                String name = parts[2];
                int ID_number = Integer.valueOf(parts[3]);
                int bank_account_num = Integer.valueOf(parts[4]);
                double hourly_salary = Double.valueOf(parts[5]);
                int vaction_days = Integer.valueOf(parts[6]);
                JobType job_type = JobType.valueOf(parts[7]);
                int branch = Integer.valueOf(parts[8]);

                Set<Role> roles_permissions = new HashSet<>();
                for (int i = 9; i < parts.length; i++)
                    roles_permissions.add(Role.valueOf(parts[i]));
                workers.put(Integer.valueOf(parts[3]), new Worker(address,
                        name,
                        ID_number,
                        bank_account_num,
                        hourly_salary,
                        vaction_days,
                        job_type,
                        branches.get(branch),
                        roles_permissions
                        //job_status
                ));


            }

        }
        // Print loaded workers for debugging
        System.out.println("Loaded workers: " + workers);
        return workers;
    }


    public void add_worker(JsonObject json) {
        String address = json.get("address").getAsString();
        String name = json.get("name").getAsString();
        int id = json.get("id").getAsInt();
        String jobType = json.get("job_type").getAsString();
        int bankAccount = json.get("bank_account").getAsInt();
        double hourlySalary = json.get("hourly_salary").getAsDouble();
        int vacationDays = json.get("vacation_days").getAsInt();
        int branchNum = json.get("branch_num").getAsInt();

        String roleStr = json.get("roles").getAsString();
        String roles[] = roleStr.split(",");

        JobType jobTypeEnum = JobType.valueOf(jobType);


        HashSet s = new HashSet();
        for(int i =0 ;i < roles.length; i++) {
            Role roleEnum = Role.valueOf(roles[i]);
            s.add(roleEnum);
        }
        Worker newWorker = new Worker(address, name , id , bankAccount, hourlySalary , vacationDays ,
                jobTypeEnum ,  branches.get(branchNum) , s);
        workers.put(id, newWorker);
    }

    public Map<Integer, Worker> getWorkers() {
        return workers;
    }

    public HRManager getManager(int id) {
        return managers.get(id);
    }

    public void present_workers() {
        JsonArray jsonArray = new JsonArray();

        for (Map.Entry<Integer, Worker> entry : workers.entrySet()) {
            Worker worker = entry.getValue();
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

        System.out.println(jsonArray.toString());
    }

    public boolean appointment_manager(JsonObject json) {
        int id = json.get("id").getAsInt();
        for (Map.Entry<Integer, Worker> entry : workers.entrySet()) {
            if (entry.getKey() == id) {
                Worker worker = entry.getValue();
                if (worker.getJob_status()) {
                    worker.getRoles_permissions().add(Shift_manager);
                    return true;
                }
            }

        }
        return false;
    }

    public boolean Appointment_success(JsonObject json) {
        boolean success = appointment_manager(json);
        return success;
    }

    public boolean Employment_termination(JsonObject json) {
        int id = json.get("id").getAsInt();
        for (Map.Entry<Integer, Worker> entry : workers.entrySet()) {
            if (entry.getKey() == id) {
                Worker worker = entry.getValue();
                if (worker.getJob_status()) {
                    worker.setJob_status(false);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean termination_success(JsonObject json) {
        boolean success = Employment_termination(json);
        return success;
    }

    public boolean update_salary(JsonObject json) {
        int id = json.get("id").getAsInt();
        for (Map.Entry<Integer, Worker> entry : workers.entrySet()) {
            if (entry.getKey() == id) {
                Worker worker = entry.getValue();
                if (worker.getJob_status()) {
                    worker.setHourly_salary(json.get("hourly_salary").getAsDouble());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean update_salary_success(JsonObject json) {
        boolean success = update_salary(json);
        return success;
    }

    public boolean Update_job_type(JsonObject json) {
        int id = json.get("id").getAsInt();
        for (Map.Entry<Integer, Worker> entry : workers.entrySet()) {
            if (entry.getKey() == id) {
                Worker worker = entry.getValue();
                if (worker.getJob_status()) {
                    worker.setJob_type(JobType.valueOf(json.get("job_type").getAsString()));
                    return true;
                }
            }
        }
        return false;
    }

    public boolean update_job_type_success(JsonObject json) {
        boolean success = Update_job_type(json);
        return success;
    }

    public boolean Update_Branch(JsonObject json) {
        int id = json.get("id").getAsInt();
        for (Map.Entry<Integer, Worker> entry : workers.entrySet()) {
            if (entry.getKey() == id) {
                Worker worker = entry.getValue();
                if (worker.getJob_status()) {
                    worker.setBranch(json.get("branch_num").getAsInt());
                    return true;

                }
            }
        }
        return false;
    }

    public boolean update_branch_success(JsonObject json) {
        boolean success = Update_Branch(json);
        return success;
    }

    public boolean Update_bank_account_num(JsonObject json) {
        int id = json.get("id").getAsInt();
        for (Map.Entry<Integer, Worker> entry : workers.entrySet()) {
            if (entry.getKey() == id) {
                Worker worker = entry.getValue();
                if (worker.getJob_status()) {
                    worker.setBank_account_num(json.get("bank_account").getAsInt());
                    return true;
                }
            }
        }
        return false;
    }


    public boolean Update_Bank_Account_Num_success(JsonObject json) {
        boolean success = Update_bank_account_num(json);
        return success;
    }



    // Finding available workers based on constraints, day, shift, and role
    public List<Worker> AvailableWorkers(String day, String shiftType, Role role) {
        List<Worker> availableWorkersList = new ArrayList<>();

        for (Map.Entry<Integer, Worker> entry : workers.entrySet()) {
            Worker worker = entry.getValue();
            Map<String, List<String>> constraints = worker.getConstraints();

            if (constraints == null || !constraints.containsKey(day) || !constraints.get(day).contains(shiftType)) {
                if (worker.getRoles_permissions().contains(role)) {
                    availableWorkersList.add(worker);
                }
            }
        }

        return availableWorkersList;

    }

    //Checks if a person is a worker
    public boolean IsWorker(JsonObject json){
        int id = json.get("id").getAsInt();
        for (Map.Entry<Integer, Worker> entry : workers.entrySet()) {
            if (entry.getKey() == id) {
                Worker worker = entry.getValue();
                if (worker.getJob_status()) {
                    return true;
                }
            }
        }
        return false;
    }

    public double Find_global_salary(JsonObject json) {
        int id = json.get("id").getAsInt();
        for (Map.Entry<Integer, Worker> entry : workers.entrySet()) {
            if (entry.getKey() == id) {
                Worker worker = entry.getValue();
                return worker.Global_salary(worker.getHourly_salary(), worker.getTotal_hours());
            }
        }
        return -1;
    }

    public int Find_vaction_day(JsonObject json) {
        int id = json.get("id").getAsInt();
        for (Map.Entry<Integer, Worker> entry : workers.entrySet()) {
            if (entry.getKey() == id) {
                Worker worker = entry.getValue();
                return worker.getVaction_days();
            }
        }
        return 0;
    }

    public LocalDate Find_Start_date(JsonObject json) {
        int id = json.get("id").getAsInt();
        for (Map.Entry<Integer, Worker> entry : workers.entrySet()) {
            if (entry.getKey() == id) {
                Worker worker = entry.getValue();
                return worker.getStarting_day();
            }
        }
        return null;
    }

    public void GetWorkerConstraints(Map<String, List<String>> constraintMap, JsonObject json) {
        int id = json.get("id").getAsInt();
        if (IsWorker(json)) {
            for (Map.Entry<Integer, Worker> entry : workers.entrySet()) {
                if (entry.getKey() == id) {
                    Worker worker = entry.getValue();
                    worker.setConstraints(constraintMap);
                }
            }
        }

    }
}