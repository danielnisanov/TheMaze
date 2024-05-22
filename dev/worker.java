import java.util.HashSet;
import java.util.Set;

public abstract class worker {
    protected String name;
    private int ID_number;
    private String address ;
    protected int bank_account_num;
    protected double global_salary;
    protected  double hourly_salary;
    protected  int vaction_days;
    public String job_type;
    public int starting_day;
    public int brunch;
    protected double total_hours;
    protected Set<Role> roles_permissions;

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

    public double getGlobal_salary() {
        return global_salary;
    }

    public void setGlobal_salary(double global_salary) {
        this.global_salary = hourly_salary * total_hours;
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

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public int getStarting_day() {
        return starting_day;
    }

    public void setStarting_day(int starting_day) {
        this.starting_day = starting_day;
    }

    public int getBrunch() {
        return brunch;
    }

    public void setBrunch(int brunch) {
        this.brunch = brunch;
    }

    public double getTotal_hours() {
        return total_hours;
    }

    public void setTotal_hours(double total_hours) {
        this.total_hours = total_hours;
    }

    public Set<Role> getRoles_permissions() {
        return roles_permissions;
    }

    public void setRoles_permissions(Set<Role> roles_permissions) {
        this.roles_permissions = roles_permissions;
    }

    public worker(String address, String name, int ID_number, int bank_account_num, double global_salary, double hourly_salary, int vaction_days, String job_type, int starting_day,
                  double total_hours, int brunch, Set<Role> roles_permissions) {
        this.address = address;
        this.name = name;
        this.ID_number = ID_number;
        this.bank_account_num = bank_account_num;
        this.global_salary = global_salary;
        this.hourly_salary = hourly_salary;
        this.vaction_days = vaction_days;
        this.job_type = job_type;
        this.starting_day = starting_day;
        this.total_hours = total_hours;
        this.brunch = brunch;
        this.roles_permissions = roles_permissions;



    }
}
