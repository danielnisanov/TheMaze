import java.util.Set;

public class shiftmanager extends Worker {
    public shiftmanager(String address, String name, int ID_number, int bank_account_num, double global_salary, double hourly_salary, int vaction_days, GobType job_type, int starting_day, double total_hours, int brunch, Set<Role> roles_permissions, boolean job_status) {
        super(address, name, ID_number, bank_account_num, global_salary, hourly_salary, vaction_days, job_type, starting_day, total_hours, brunch, roles_permissions,job_status);
    }


}
