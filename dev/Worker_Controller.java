import java.util.*;

public class Worker_Controller {
    private Map<Integer, Worker> workers;
    private Constraints constraints;

    public Worker_Controller() {
        workers = new HashMap<>();
        constraints = new Constraints();
        // Populate workers map with some initial workers for testing
        // Each worker must be added to constraints as well
//        Worker worker1 = new Worker("123 Main St", "John Doe", 12345, 67890, 0, 20.0, 10, GobType.Full_time_job, 1, 0, null, true);
//        workers.put(12345, worker1);
//        constraints.addWorker(worker1);
//
//        Worker worker2 = new Worker("456 Elm St", "Jane Smith", 54321, 98765, 0, 22.0, 12, GobType.Part_time_job, 1, 0, null, true);
//        workers.put(54321, worker2);
//        constraints.addWorker(worker2);
    }

    public void Add_Worker() {
        Scanner scanner = new Scanner(System.in);

        int id_num;
        System.out.println("Please enter the id_number of the new worker:");
        id_num = scanner.nextInt();

        String name;
        System.out.println("Enter his name:");
        name = scanner.next(); //fix

        String address;
        System.out.println("Please enter his address:");
        address = scanner.next(); ///fix

        int bank_account_number;
        System.out.println("Enter his bank account number:");
        bank_account_number = scanner.nextInt();

        double hourly_salary;
        System.out.println("Enter his hourly salary:");
        hourly_salary = scanner.nextDouble();

        int vacation_days;
        System.out.println("Enter the amount of vacation days:");
        vacation_days = scanner.nextInt();

        JobType gob_type;
        System.out.println("Enter his job type: Full_time_job, Part_time_job, Works_contractor");
        gob_type = JobType.valueOf(scanner.next());

        int branch;
        System.out.println("Enter his branch:");
        branch = scanner.nextInt();

        Set<Role> roles = new HashSet<>(); // Create a new HashSet to hold roles

        Role role;
        System.out.println("Enter his role:  Storekeeper, Cashier, Sorter, Shift_manager, Guard");
        role = Role.valueOf(scanner.next());
        roles.add(role); // Add the role to the set

        Worker worker = new Worker(address, name, id_num, bank_account_number, 0, hourly_salary, vacation_days, gob_type, 1, 0, branch, roles, true);
        workers.put(id_num, worker);
        constraints.addWorker(worker);

        System.out.println("Worker added successfully!");
    }

    public void stop_working(Worker worker) {
        worker.job_status = false;
    }

    public void create_new_manager(Worker worker) {
        worker.addRole(Role.Shift_manager);
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

    /**
     * Retrieves the details of a worker by their ID.
     */
    public Worker getWorkerDetailsByID(int workerID) {
        // Check if the workers map contains the provided worker ID
        if (workers.containsKey(workerID)) {
            // If the worker exists, return the Worker object
            return workers.get(workerID);
        } else {
            // If the worker does not exist, print a message and return null
            System.out.println("Worker with ID " + workerID + " not found.");
            return null;
        }
    }

    /**
     * Displays details about all workers.
     */
    public void displayWorkers() {
        System.out.println("Displaying all workers:");

        // Iterate through all entries in the workers map
        for (Worker worker : workers.values()) {
            // Use the toString method to display worker details
            System.out.println(worker.toString());
            System.out.println("--------------------------");
        }

    }

    public void update_worker_salary() {
        int ID;
        double new_salary;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the worker ID");
        ID = scanner.nextInt();
        System.out.println("Please enter the new salary");
        new_salary = scanner.nextDouble();
        for (Worker worker : workers.values()) {
            if (worker.getID_number() == ID) {
                worker.hourly_salary = new_salary;
            }
        }
    }

    public void update_job_type() {
        int ID;
        JobType new_job;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the worker ID");
        ID = scanner.nextInt();
        System.out.println("Please choose the new job type: Full_time_job, Part_time_job, Works_contractor");
        new_job = JobType.valueOf(scanner.next());
        for (Worker worker : workers.values()) {
            if (worker.getID_number() == ID) {
                worker.job_type = new_job;
            }
        }
    }
}

