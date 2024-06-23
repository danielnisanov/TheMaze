package Domain;

import Data.WorkerDAO;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.time.LocalDate;


public class Worker {
    protected String name;
    private int ID_number;
    private String address ;
    protected int bank_account_num;
    protected  double hourly_salary;
    protected  int vaction_days;
    public JobType job_type;
    private LocalDate starting_day;
    public Branch branch;
    protected double total_hours = 0;
    protected Set<Role> roles_permissions;
    public boolean job_status = true;
    private Map<String, List<String>> constraints = null;

    private WorkerDAO workerDAO = new WorkerDAO();

    public boolean getJob_status() {
        return job_status;
    }



    public Branch getbranch (Branch branch){
        return branch;
    }

    public void setJob_status(boolean job_status) {

        this.job_status = job_status;
    }


    public int getID_number() {
        return ID_number;

    }

    public void setID_number(int ID_number) {
        this.ID_number = ID_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBank_account_num() {
        return bank_account_num;
    }

    public void setBank_account_num(int bank_account_num) {
        this.bank_account_num = bank_account_num;
    }

    public double Global_salary(double hourly_salary, double total_hours) {

        return hourly_salary * total_hours;
    }

    public double getHourly_salary() {
        return hourly_salary;
    }

    public void setHourly_salary(double hourly_salary) {
        this.hourly_salary = hourly_salary;
    }

    public int getVaction_days() {
        return vaction_days;
    }

    public void setVaction_days(int vaction_days) {
        this.vaction_days = vaction_days;
    }

    public JobType getJob_type() {
        return job_type;

    }

    public void setJob_type(JobType job_type) {
        this.job_type = job_type;
    }

    public LocalDate getStarting_day() {
        return starting_day;
    }

    public void setStarting_day(int starting_day) {
        this.starting_day = LocalDate.ofEpochDay(starting_day);
    }

    public int getBranchNum() {
        if (branch != null) {
            return branch.getBranchNum();
        } else {
            return -1;
        }
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public double getTotal_hours() {
        return total_hours;
    }

    public void setTotal_hours(double total_hours) {
        this.total_hours = 0.0;
    }

    public Set<Role> getRoles_permissions() {
        return roles_permissions;
    }

    public void setRoles_permissions(Set<Role> role){
        this.roles_permissions = role;
    }


    public Worker(String address, String name, int ID_number, int bank_account_num, double hourly_salary, int vaction_days, JobType job_type,
                  Branch branch, Set<Role> roles_permissions) {
        this.address = address;
        this.name = name;
        this.ID_number = ID_number;
        this.bank_account_num = bank_account_num;
        this.hourly_salary = hourly_salary;
        this.vaction_days = vaction_days;
        this.job_type = job_type;
        this.starting_day = LocalDate.from(LocalDateTime.now()); // Get the current date
        this.branch = branch;
        branch.add_worker_brunch(this);
        this.roles_permissions = roles_permissions;


    }

    public void update_account_num(int new_account_num) {
        this.bank_account_num = new_account_num;
    }

    // Add a method to add a role to the employee
    public void addRole(Role role) {
        if (roles_permissions == null) {
            roles_permissions = new HashSet<>();
        }
        roles_permissions.add(role);
    }

    @Override
    public String toString() {
        return "Domain.Worker{" +
                "ID: " + ID_number +
                ", Name: '" + name + '\'' +
                ", Address: '" + address + '\'' +
                ", Bank Account Number: " + bank_account_num +
                ", Hourly Salary: " + hourly_salary +
                ", Vacation Days: " + vaction_days +
                ", Job Type: " + job_type +
                ", Starting Day: " + starting_day +
                ", Branch: " + branch +
                ", Total Hours: " + total_hours +
                ", Roles and Permissions: " + roles_permissions +
                ", Job Status: " + (job_status ? "True" : "False") +
                '}';
    }

    public Map<String, List<String>> getConstraints() {
        return constraints;

    }
    public void setConstraints(Map<String, List<String>> constraints) {
        this.constraints = constraints;

    }

    public Branch getBranch() {
        return branch;
    }

    public void save() {
        workerDAO.insertWorker(this);
    }

    public void update() {
        workerDAO.updateWorker(this);
    }

    public void delete() {
        workerDAO.deleteWorker(this.getID_number());
    }

}