package Domain;

import java.util.Set;

public class Driver extends Worker{
    LicenseType licenseType;


    public Driver(String address, String name, int ID_number, int bank_account_num, double hourly_salary, int vaction_days, JobType job_type, Branch branch, Set<Role> roles_permissions) {
        super(address, name, ID_number, bank_account_num, hourly_salary, vaction_days, job_type, branch, roles_permissions);
        this.licenseType = null;
    }
}
