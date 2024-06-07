package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Branch {
    private int branch_num;
    private HRManager hr_manager;

    private final ArrayList<Integer> valid_days_for_subbmition;

    private final Map<Integer, Worker> workers_on_brunch;

    private final ArrayList<shift> weeklyWorkArrangement = new ArrayList<>(); // Weekly work arrangement array
    private final ArrayList<shift> Shift_History = new ArrayList<>(); // Map that saves all the past shifts and the workers in the shift

    public Branch(int branch_num, HRManager manager ) {
        this.branch_num = branch_num;
        this.hr_manager = manager; // Set the hr_manager field with the provided HRManager object
        workers_on_brunch = new HashMap<Integer, Worker>();
        init_branch_week();
        valid_days_for_subbmition = new ArrayList<Integer>();
        valid_days_for_subbmition.add(7);
        valid_days_for_subbmition.add(1);
        valid_days_for_subbmition.add(2);
        valid_days_for_subbmition.add(3);
        valid_days_for_subbmition.add(4);
    }
    public Branch(int branch_num) {
        this.branch_num = branch_num;
        this.hr_manager = null; // Set the hr_manager field with the provided HRManager object
        workers_on_brunch = new HashMap<>();
        init_branch_week();
        valid_days_for_subbmition = new ArrayList<>();
        valid_days_for_subbmition.add(7);
        valid_days_for_subbmition.add(1);
        valid_days_for_subbmition.add(2);
        valid_days_for_subbmition.add(3);
        valid_days_for_subbmition.add(4);
    }
    public ArrayList<Integer> get_submittion_days()
    {
        return valid_days_for_subbmition;
    }

    public void set_manager(HRManager manager)
    {
        hr_manager = manager;
    }
    public int getBranchNum() {
        return branch_num;
    }
    public void setBranchNum(int num)
    {
        branch_num = num;
    }
    public HRManager getHRManager() {
        return hr_manager;
    }
    public Map<Integer, Worker> getWorkers_on_brunch() {
        return workers_on_brunch;
    }
    public void add_worker(Worker value) {
        workers_on_brunch.put(value.getID_number(), value);
    }

    public boolean is_worker_in_branch(int id) {
        return workers_on_brunch.get(id) != null;
    }

    public ArrayList<shift> getWeeklyWorkArrangement() {
        return weeklyWorkArrangement;
    }

    public ArrayList<shift> getShift_History() {
        return Shift_History;
    }

    public void add_shift(shift st)
    {
        Shift_History.add(st);
    }

    public void init_branch_week()
    {
        String type = "Morning";
        for (int i = 1; i <= 14; i++) {
            shift sh = new shift((i + 1) / 2, type, new ArrayList<Worker>());
            weeklyWorkArrangement.add(sh);
            if (type.equals("Morning")) {
                type = "Evening";
            } else {
                type = "Morning";
            }
        }
    }

}