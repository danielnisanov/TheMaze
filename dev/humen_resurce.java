import java.util.Arrays;
import java.util.Set;

public class humen_resurce  extends worker{
    protected worker[] current_resurce;
    protected worker[] past_worker;

    public humen_resurce(String address, String name, int ID_number, int bank_account_num, double global_salary, double hourly_salary, int vaction_days, String job_type, int starting_day, double total_hours, int brunch, Set<Role> roles_permissions, worker[] current_resurce, worker[] past_worker) {
        super(address, name, ID_number, bank_account_num, global_salary, hourly_salary, vaction_days, job_type, starting_day, total_hours, brunch, roles_permissions);
        this.current_resurce = current_resurce;
        this.past_worker = past_worker;
    }

    public humen_resurce(String address, String name, int ID_number, int bank_account_num, double global_salary, double hourly_salary, int vaction_days, String job_type, int starting_day, double total_hours, int brunch, Set<Role> roles_permissions) {
        super(address, name, ID_number, bank_account_num, global_salary, hourly_salary, vaction_days, job_type, starting_day, total_hours, brunch, roles_permissions);
    }
}
