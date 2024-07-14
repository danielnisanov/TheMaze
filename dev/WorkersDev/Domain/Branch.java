package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Branch {
    private int branch_num;
    private HRManager hr_manager;
    private final ArrayList<Integer> valid_days_for_submission;
    private final Map<Integer, Worker> workers_on_brunch;

    private final WorkArrangementRepositoryWorkers workArrangementRepository;
     ShiftHRepositoryWorkers shift_hRepository; //todo

    public Branch(int branch_num, HRManager manager, WorkArrangementRepositoryWorkers war) {
        this.branch_num = branch_num;
        this.hr_manager = manager;
        workers_on_brunch = new HashMap<>();
//        initBranchWeek();
        valid_days_for_submission = new ArrayList<>();
        valid_days_for_submission.add(7);
        valid_days_for_submission.add(1);
        valid_days_for_submission.add(2);
        valid_days_for_submission.add(3);
        valid_days_for_submission.add(4);
        workArrangementRepository = war;
    }

    public Branch(int branch_num, WorkArrangementRepositoryWorkers war){
        this.branch_num = branch_num;
        this.hr_manager = null;
        workers_on_brunch = new HashMap<>();
//        initBranchWeek();
        valid_days_for_submission = new ArrayList<>();
        valid_days_for_submission.add(7);
        valid_days_for_submission.add(1);
        valid_days_for_submission.add(2);
        valid_days_for_submission.add(3);
        valid_days_for_submission.add(4);
        workArrangementRepository = war;
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

    public boolean is_worker_in_branch(int workerId) {
        return workers_on_brunch.containsKey(workerId);
    }

    public ArrayList<Shift> get_Weekly_Work_Arrangement() {
        return workArrangementRepository.getWeeklyWorkArrangement();
    }

    public ArrayList<Shift> get_Shift_History() {
        return shift_hRepository.getShiftHistory();
    }

    public ArrayList<Shift> setShiftHistory(ArrayList<Shift> shiftHistory){
        return shiftHistory;
    }

//
//    public void initBranchWeek() {
//        workArrangementRepository.init_branch_week();
//    }
}
