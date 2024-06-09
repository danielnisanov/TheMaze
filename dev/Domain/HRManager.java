package Domain;

public class HRManager {
    public String name;
    private int ID_number;
    public Branch branch;
    private String password;

    public HRManager(String name, Branch branch, String password, int ID_number) {
        this.name = name;
        this.branch = branch;
        this.branch.set_manager(this);
        this.ID_number = ID_number;
        this.password = password;
    }

    public HRManager(String name, int branch, String password, int ID_number) {
        this.name = name;
        this.ID_number = ID_number;
        this.password = password;
        this.branch = new Branch(branch);
    }


    public String getName() {
        return name;
    }

    public int getID_number() {
        return ID_number;
    }

    public int getBranchNum() {
        if (branch != null) {
            return branch.getBranchNum();
        } else {
            return -1;
        }
    }

    public Branch getBranch() {
        return branch;
    }

    public String getPassword() {
        return password;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public void setID_number(int ID_number) {
        this.ID_number = ID_number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

}
