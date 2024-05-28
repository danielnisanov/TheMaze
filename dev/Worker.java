import java.time.LocalDateTime;
import java.util.Set;
import java.time.LocalDate;


public class Worker {
    protected String name;
    private int ID_number;
    private String address ;
    protected int bank_account_num;
    protected  double hourly_salary;
    protected  int vaction_days;
    public GobType job_type;
    private LocalDate starting_day;
    public int brunch;
    protected double total_hours;
    protected Set<Role> roles_permissions;
    public boolean job_status;

    public boolean getJob_status() {
        return job_status;
    }

    public void setJob_status(boolean job_status) {
        this.job_status = true;
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

    public double Global_salary(double hourly_salary, int total_hours) {
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

    public GobType getJob_type() {
        return job_type;

    }

    public void setJob_type(GobType job_type) {
        this.job_type = job_type;
    }

    public LocalDate getStarting_day() {
        return starting_day;
    }

    public void setStarting_day(int starting_day) {
        this.starting_day = LocalDate.ofEpochDay(starting_day);
    }

    public int getBrunch() {
        return brunch;
    }

    public void setBrunch(int brunch) {
        this.brunch = brunch;
    }

    public int getTotal_hours() {
        return (int) total_hours;
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

    public Worker(String address, String name, int ID_number, int bank_account_num, double global_salary, double hourly_salary, int vaction_days, GobType job_type, int starting_day,
                  double total_hours, int brunch, Set<Role> roles_permissions, boolean job_status) {
        this.address = address;
        this.name = name;
        this.ID_number = ID_number;
        this.bank_account_num = bank_account_num;
        this.hourly_salary = hourly_salary;
        this.vaction_days = vaction_days;
        this.job_type = job_type;
        this.starting_day = LocalDate.from(LocalDateTime.now()); // Get the current date
        this.total_hours = total_hours;
        this.brunch = brunch;
        this.roles_permissions = roles_permissions;
        this.job_status = job_status;
    }

        public void update_account_num(int new_account_num) {
            this.bank_account_num = new_account_num;
        }



    }

