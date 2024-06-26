package Domain;
import java.util.ArrayList;
import java.util.List;

public class Shift {
    public int shift_date;
    public String shift_type;
    public List<Worker> workers_on_shift;

    public Shift(int shift_date, String shift_type, List<Worker> workers_on_shift) {
        this.shift_date = shift_date;
        this.shift_type = shift_type;
        this.workers_on_shift = workers_on_shift;

    }
    // Copy constructor
    public Shift(Shift other) {
        this.shift_date = other.shift_date;
        this.shift_type = other.shift_type;
        this.workers_on_shift = new ArrayList<>(other.workers_on_shift);
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

    public void setShift_date(int shift_date) {
        this.shift_date = shift_date;
    }

    public void setShift_type(String shift_type) {
        this.shift_type = shift_type;
    }

    public void setWorkers_on_shift(List<Worker> workers_on_shift) {
        this.workers_on_shift = workers_on_shift;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Day: ").append(shift_date).append(", Shift: ").append(shift_type).append(", Workers: [");
        for (Worker worker : workers_on_shift) {
            sb.append(worker.getName()).append(" (ID: ").append(worker.getID_number()).append("), ");
        }
        if (!workers_on_shift.isEmpty()) {
            sb.setLength(sb.length() - 2); // Remove the trailing comma and space
        }
        sb.append("]");
        return sb.toString();
    }


}