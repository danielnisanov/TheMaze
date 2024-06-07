package Presentation;

import Domain.HRManager;
import Domain.ShiftController;
import Domain.Worker;
import Domain.WorkerController;

import java.util.ArrayList;
import java.util.Scanner;

public class MCVSystem {
    public static Scanner sc = new Scanner(System.in);

    private ManagerPresentation mp;
    private WorkerPresentation wp;


    private WorkerController worker_controler;
    private ShiftController shift_controler;
    private AppointmentManager appointmentManager;
    private AddWorker addWorker;
    private EmploymentTermination emplymenttermination;
    private UpdateWorkerDetails updatrDetails;
    private SubmitConstraints submitConstraints;


    public MCVSystem(String file) {
        worker_controler = new WorkerController(file);
        appointmentManager  = new AppointmentManager(worker_controler);
        addWorker  = new AddWorker(worker_controler);
        emplymenttermination  = new EmploymentTermination(worker_controler);
        updatrDetails  = new UpdateWorkerDetails(worker_controler);
        shift_controler = new ShiftController();
        submitConstraints  = new SubmitConstraints(worker_controler,shift_controler);
    }

    public void activate() {
        boolean exitSystem = false;
        while (!exitSystem) {
            System.out.println("Who are you? m - manager , w - worker, e - exit system");
            char input = sc.next().charAt(0);
            switch (input) {
                case 'm':
                    mp = new ManagerPresentation(worker_controler, appointmentManager, addWorker, emplymenttermination, updatrDetails, submitConstraints);
                    mp.menu();
                    break;
                case 'w':
                    wp = new WorkerPresentation(worker_controler, submitConstraints);
                    wp.WorkerMenu();
                    break;
                case 'e':
                    exitSystem = true;
                    System.out.println("Exiting system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }
    }
}