package Domain;

import java.util.HashMap;
import java.util.Map;

public class Branch {
    private int branch_num;
    private HRManager hr_manager;
    private Map<Integer, HRManager> worker_on_brunch; //check

    public Branch(int branch_num) {
        this.branch_num = branch_num;
        this.hr_manager = null;
        worker_on_brunch = new HashMap<Integer, HRManager>();
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
    public Map<Integer, HRManager> getWorker_on_brunch() {
        return worker_on_brunch;
    }


}