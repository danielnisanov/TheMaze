package Domain;

import Dal.DatabaseConnection;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.time.LocalDate;

import java.util.*;


public class WorkerController {
    private LocalDate currentDate = LocalDate.now();
    private final WorkersRepository Worker_Rep;
    private final ManagersRepository Manager_Rep;
    private final BranchesRepository Branch_Rep;

    public WorkerController(DatabaseConnection dbConnection) {
        Worker_Rep = new WorkersRepository(dbConnection);
        Manager_Rep = new ManagersRepository(dbConnection);
        Branch_Rep = new BranchesRepository(dbConnection);
    }


    public boolean add_worker(JsonObject json) {
        String address = json.get("address").getAsString();
        String name = json.get("name").getAsString();
        int id = json.get("id").getAsInt();
        String jobType = json.get("job_type").getAsString();
        int bankAccount = json.get("bank_account").getAsInt();
        double hourlySalary = json.get("hourly_salary").getAsDouble();
        int vacationDays = json.get("vacation_days").getAsInt();
        int branchNum = json.get("branch_num").getAsInt();

        String roleStr = json.get("roles").getAsString();
        String[] roles = roleStr.split(",");

        JobType jobTypeEnum = JobType.valueOf(jobType);

        HashSet<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(Role.valueOf(role.trim()));
        }

        Branch branch = getBranch(branchNum);

        Worker newWorker = new Worker(address, name, id, bankAccount, hourlySalary, vacationDays, jobTypeEnum, branch, roleSet);
        Worker_Rep.Insert(newWorker);
        branch.add_worker_brunch(newWorker);  // Add the worker to the branch
        newWorker.setBranch(branch);  // Set the branch for the worker

    return true;
    }



    public void add_manager(JsonObject json) {
        String name = json.get("name").getAsString();
        int ID_number = json.get("ID_number").getAsInt();
        int branch_num = json.get("branch_num").getAsInt();

        Branch branch = getBranch(branch_num);
        if (branch == null) {
            branch = new Branch(branch_num);
            Branch_Rep.Insert(branch);
        }

        HRManager newManager = new HRManager(name, branch, String.valueOf(ID_number), ID_number);
        branch.set_manager(newManager); // todo - not here
        Manager_Rep.Insert(newManager);


    }


    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public HRManager get_Manager(int id) {
        return Manager_Rep.getManager(id);
    }


    public Branch getBranch(int id) {
        return Branch_Rep.Find(id);
    }

    public JsonArray present_workers(Branch branch) {
        return  Worker_Rep.PresentWorkers(branch);
    }

    public JsonArray present_past_workers(Branch branch) {
        return  Worker_Rep.PresentPastWorkers(branch);

    }


    public boolean appointment_manager(JsonObject json) {
        int id = json.get("id").getAsInt();
        if (Worker_Rep.Update(id, "roles","Shift_manager" )){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean Employment_termination(JsonObject json) {
        int id = json.get("id").getAsInt();
        return Worker_Rep.Update(id, "job_status", "false");
    }



    public boolean update_salary(JsonObject json) {
        int id = json.get("id").getAsInt();
        int houerlySalary = json.get("hourly_salary").getAsInt();
        if (Worker_Rep.Update(id, "hourly_salary", "houerlySalary")){
            return true;
        }
        else {
            return false;
        }
    }


    public boolean Update_job_type(JsonObject json) {
        int id = json.get("id").getAsInt();
        int job_type = Integer.parseInt(json.get("job_type").getAsString());
        if (Worker_Rep.Update(id, "job_type", "job_type")) {
            return true;
        } else {
            return false;
        }
    }


    public boolean Update_Branch(JsonObject json) {
        int id = json.get("id").getAsInt();
        int branchNum = json.get("branch_num").getAsInt();
        if (Worker_Rep.Update(id, "branch"," branchNum")){
            return  true;
        }
        else {
            return false;
        }
    }


    public boolean Update_bank_account_num(JsonObject json) {
        int id = json.get("id").getAsInt();
        JsonElement bankAccountElement = json.get("bank_account");

        if( Worker_Rep.Update(id, "bank_account_num","bankAccountElement" )){
            return true;
        }else {
            return false;
        }
    }

    // Finding available workers based on constraints, day, shift, and role
    public List<Worker> AvailableWorkers(String day, String shiftType, Role role, Branch branch) {
        return Worker_Rep.Available_Workers(day,shiftType, role, branch);
    }

    //Checks if a person is a worker
    public boolean IsWorker(JsonObject json) {
        int id = json.get("id").getAsInt();
        return Worker_Rep.Is_Worker(id);
    }

    public double Find_global_salary(JsonObject json)  {
        int id = json.get("id").getAsInt();
        return Worker_Rep.FindGlobalSalary(id);

    }

    public int Find_vaction_day(JsonObject json) {
        int id = json.get("id").getAsInt();
        return Worker_Rep.FindVactionDay(id);

    }

    public LocalDate Find_Start_date(JsonObject json) {
        int id = json.get("id").getAsInt();
        return Worker_Rep.FindStartDate(id);
    }

    public void finishDay() {
        currentDate = NextDay(currentDate);
    }

    public static LocalDate NextDay(LocalDate currentDate) {
        // Get the current date
        System.out.println("Today's date: " + currentDate);
        LocalDate nextDay = currentDate.plusDays(1);
        System.out.println("Next day's date: " + nextDay);
        // Advance to the next day
        return nextDay;
    }


    public void SetWorkerConstraints(Map<String, List<String>> constraintMap, JsonObject json) {
        int id = json.get("id").getAsInt();
        Worker_Rep.Set_Worker_Constraints(constraintMap,id);


    }

    public JsonObject present_worker(JsonObject json) {
        int id = json.get("id").getAsInt();
        return Worker_Rep.PresentWorker(id);

    }

    public Map<Integer, Worker> getWorkers() {
        return Worker_Rep.get_Workers();
    }

    public Worker getWorker(int id) {
        return Worker_Rep.Find(id);
    }

    public boolean ChangePassword(int id, String password, String newValue){
        return Manager_Rep.Update(id, password, newValue);
    }

}




