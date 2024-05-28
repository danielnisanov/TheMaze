import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Worker_Controller {
    private Map<Integer, Worker> workers;
    private Constraints constraints;

    public Worker_Controller() {
        workers = new HashMap<>();
        constraints = new Constraints();
        // Populate workers map with some initial workers for testing
        // Each worker must be added to constraints as well
//        Worker worker1 = new Worker("123 Main St", "John Doe", 12345, 67890, 0, 20.0, 10, "Cashier", 0, 0, 1, null);
//        workers.put(12345, worker1);
//        constraints.addWorker(worker1);
//
//        Worker worker2 = new Worker("456 Elm St", "Jane Smith", 54321, 98765, 0, 22.0, 12, "Manager", 0, 0, 1, null);
//        workers.put(54321, worker2);
//        constraints.addWorker(worker2);
//
    }

    public void Add_Worker(){
        Scanner scanner = new Scanner(System.in);

        int id_num;
        System.out.println("please enter the id_number of the new worker:");
        id_num = scanner.nextInt();

        String name;
        System.out.println("enter his name:");
        name= scanner.next(); ////fix

        String adress;
        System.out.println("please enter his address:");
        adress= scanner.next(); ///fix

        int bank_account_number;
        System.out.println("enter his bank account number:");
        bank_account_number= scanner.nextInt();

        double hourly_salary;
        System.out.println("enter his hurly salary:");
        hourly_salary= scanner.nextDouble();

        int vacation_days;
        System.out.println(" enter the amount of vacation days:");
        vacation_days = scanner.nextInt();

        GobType gob_type;
        System.out.println(" enter his job type: Full_time_job, Part_time_job, Works_contractor");
        gob_type = GobType.valueOf(scanner.next()); ///fix

        int branch;
        System.out.println("enter his branch");
        branch = scanner.nextInt();




    }

    public boolean isWorker(int workerID) {
        return workers.containsKey(workerID);
    }

    public Worker getWorkerByID(int workerID) {
        return workers.get(workerID);
    }

    public Constraints getConstraints() {
        return constraints;
    }
}
