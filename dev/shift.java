import java.util.List;

public class shift {
    public int shift_date;
    public String shift_type;
    public List<Worker> workers_on_shift;
    public shiftmanager shiftmanager;

    public shift(int shift_date, String shift_type, List<Worker> workers_on_shift, shiftmanager shift_man) {
        this.shift_date = shift_date;
        this.shift_type = shift_type;
        this.workers_on_shift = workers_on_shift;
        this.shiftmanager = shift_man;
    }

    public int getShift_date() {
        return shift_date;
    }

    public String getShift_type() {
        return shift_type;
    }

    public List<Worker> getWorkers_on_shift() {
        return workers_on_shift;
    }

    public shiftmanager getShiftmanager() {
        return shiftmanager;
    }

    public void setShift_date(int shift_date) {
        this.shift_date = shift_date;
    }

    public void setShift_type(String shift_type) {
        this.shift_type = shift_type;
    }

    public void setWorkers_on_shift(List<Worker> workers_on_shift) {
        this.workers_on_shift = workers_on_shift;
    }

    public void setShiftmanager(shiftmanager shiftmanager) {
        this.shiftmanager = shiftmanager;
    }
}
