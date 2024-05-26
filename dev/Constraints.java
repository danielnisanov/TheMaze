
import java.util.HashMap;
import java.util.Map;

public class Constraints {
    private Map<Worker, boolean[][]> constraints;

    public Constraints() {
        this.constraints = new HashMap<>();
    }

    public void addWorker(Worker worker) {
        boolean[][] availability = new boolean[7][2]; // 7 days a week, 2 shifts per day
        constraints.put(worker, availability);
    }

    public void setAvailability(Worker worker, int day, int shift, boolean available) {
        if (constraints.containsKey(worker)) {
            constraints.get(worker)[day][shift] = available;
        }
    }

    public boolean[][] getAvailability(Worker worker) {
        return constraints.get(worker);
    }
}


