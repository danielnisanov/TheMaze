package Presentation.Presentationworkers;

import Domain.Domainworkers.*;
import DAL.DALworkers.*;
import Presentation.Presentationworkers.SubmitConstraints;
import Presentation.Presentationworkers.UpdateWorkerDetails;
import Presentation.Presentationworkers.WorkerPresentation;

import java.util.Scanner;

public class MCVSystem {
    public static Scanner sc = new Scanner(System.in);

    private ManagerPresentation mp;
    private WorkerPresentation wp;

    private final WorkerController worker_controler;
    private final ShiftController shift_controler;
    private final AppointmentManager appointmentManager;
    private final AddWorker addWorker;
    private final EmploymentTermination emplymenttermination;
    private final UpdateWorkerDetails updateDetails;
    private final SubmitConstraints submitConstraints;

    public MCVSystem(String dataPath) {
        DatabaseConnection dbConnection = new DatabaseConnection(dataPath);
        WorkArrangementRepositoryWorkers workArrangementRepository = new WorkArrangementRepositoryWorkers(dbConnection);

        WorkersDAO workersDAO = new WorkersDAO(dbConnection, new BranchesRepositoryWorkers(dbConnection, workArrangementRepository));
        WorkArrangementDAO workArrangementDAO = new WorkArrangementDAO(dbConnection);
        workArrangementDAO.setWorkersDAO(workersDAO); // Setting WorkersDAO in WorkArrangementDAO

        worker_controler = new WorkerController(dbConnection, workArrangementRepository);
        BranchesRepositoryWorkers BR = new BranchesRepositoryWorkers(dbConnection, workArrangementRepository);
        ShiftHDAO shf = new ShiftHDAO(dbConnection, BR);
        ShiftHRepositoryWorkers shiftHRepository = new ShiftHRepositoryWorkers(shf);
        WorkersRepositoryWorkers workersRepository = new WorkersRepositoryWorkers(dbConnection, BR);
        WorkersOnShiftDAO wosd = new WorkersOnShiftDAO(dbConnection, BR);
        WorkersOnShiftRepositoryWorkers workersOnShiftRepository = new WorkersOnShiftRepositoryWorkers(wosd);

        appointmentManager = new AppointmentManager(worker_controler);
        addWorker = new AddWorker(worker_controler);
        emplymenttermination = new EmploymentTermination(worker_controler);
        updateDetails = new UpdateWorkerDetails(worker_controler);
        shift_controler = new ShiftController(shiftHRepository, workersRepository, workArrangementRepository, workersOnShiftRepository);
        submitConstraints = new SubmitConstraints(worker_controler, shift_controler);
    }

    public void Activate() {
        boolean exitSystem = false;
        while (!exitSystem) {
            System.out.println("Who are you? m - manager , w - worker, e - exit system");
            char input = sc.next().charAt(0);
            switch (input) {
                case 'm':
                    mp = new ManagerPresentation(worker_controler, appointmentManager, addWorker, emplymenttermination, updateDetails, submitConstraints, shift_controler);
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
