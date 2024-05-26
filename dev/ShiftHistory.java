import java.util.List;


public class ShiftHistory {
    private List<shift> past_shifts;

    public ShiftHistory(List<shift> past_shift) {
        this.past_shifts = past_shift;
    }

    public List<shift> getShiftHistory() {
        return past_shifts;
    }

    public void setShiftHistory(List<shift> past_shift) {
        this.past_shifts = past_shift;
    }
}