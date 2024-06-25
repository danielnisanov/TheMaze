package Presentation;

import Domain.WorkerController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to initialize the system with data? yes/no");
        String answer = sc.nextLine();
        if(answer.equals("yes")) {
            MCVSystem my_sys = new MCVSystem("data.csv");
            my_sys.Activate();
        }
        else if(answer.equals("no")) {
            // HRManager NEWMANAGER = new HRManager("Roni",1,"1234", 123456787);
            MCVSystem my_sys = new MCVSystem("empty.csv");
            WorkerController workerController = new WorkerController("empty.csv");
            AddWorker addWorker = new AddWorker(workerController);
            addWorker.AddManager();
            my_sys.Activate();


        }

    }

}