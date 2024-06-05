package Domain;

public class HRManager {
    public String name;
    private int ID_number;
    public int brunch;
    private String password;

    public HRManager(String name, int brunch, String password, int ID_number) {
        this.name = name;
        this.brunch = brunch;
        this.password = password;
        this.ID_number = ID_number;
    }

    public String getName() {
        return name;
    }

    public int getID_number() {
        return ID_number;
    }

    public int getBrunch() {
        return brunch;
    }

    public String getPassword() {
        return password;
    }

    public void setBrunch(int brunch) {
        this.brunch = brunch;
    }

    public void setID_number(int ID_number) {
        this.ID_number = ID_number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}