package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Branch {
    private int branch_num;
    private HRManager hr_manager;
    private final ArrayList<Integer> valid_days_for_submission;
    private final Map<Integer, Worker> workers_on_brunch;
    private final ArrayList<Shift> weeklyWorkArrangement = new ArrayList<>();
    private final ArrayList<Shift> shiftHistory = new ArrayList<>();

    public Branch(int branch_num, HRManager manager) {
        this.branch_num = branch_num;
        this.hr_manager = manager;
        workers_on_brunch = new HashMap<>();
        init_branch_week();
        valid_days_for_submission = new ArrayList<>();
        valid_days_for_submission.add(7);
        valid_days_for_submission.add(1);
        valid_days_for_submission.add(2);
        valid_days_for_submission.add(3);
        valid_days_for_submission.add(4);
    }

    public Branch(int branch_num) {
        this.branch_num = branch_num;
        this.hr_manager = null;
        workers_on_brunch = new HashMap<>();
        init_branch_week();
        valid_days_for_submission = new ArrayList<>();
        valid_days_for_submission.add(7);
        valid_days_for_submission.add(1);
        valid_days_for_submission.add(2);
        valid_days_for_submission.add(3);
        valid_days_for_submission.add(4);
    }


    public ArrayList<Integer> get_submission_days() {
        return valid_days_for_submission;
    }

    public void set_manager(HRManager manager) {
        hr_manager = manager;
    }

    public int getBranchNum() {
        return branch_num;
    }


    public void setBranchNum(int num) {
        branch_num = num;
    }

    public HRManager getHRManager() {
        return hr_manager;
    }

    public Map<Integer, Worker> getWorkers_on_brunch() {
        return workers_on_brunch;
    }

    public void add_worker_brunch(Worker worker) {
        workers_on_brunch.put(worker.getID_number(), worker);
    }

    public boolean is_worker_in_branch(int id) {
        return workers_on_brunch.get(id) != null;
    }

    public ArrayList<Shift> getWeeklyWorkArrangement() {
        return weeklyWorkArrangement;
    }

    public ArrayList<Shift> getShiftHistory() {
        return shiftHistory;
    }

    public ArrayList<Shift> setShiftHistory(ArrayList<Shift> shiftHistory){
        return shiftHistory;
    }


    public void init_branch_week() {
        for (int i = 0; i < 7; i++) {
            Shift morningShift = new Shift(i + 1, "Morning", new ArrayList<>());
            Shift eveningShift = new Shift(i + 1, "Evening", new ArrayList<>());
            weeklyWorkArrangement.add(morningShift);
            weeklyWorkArrangement.add(eveningShift);
        }
    }
}
