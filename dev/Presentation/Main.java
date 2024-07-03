package Presentation;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String dataPath = "C:\\Users\\ronig\\OneDrive\\שולחן העבודה\\ADSS\\Supermarket.db";
        MCVSystem my_sys = new MCVSystem(dataPath);
        my_sys.Activate();
    }
}
