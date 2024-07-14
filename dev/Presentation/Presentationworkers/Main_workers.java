package Presentation.Presentationworkers;

import Presentation.Presentationworkers.MCVSystem;

public class Main_workers {

    public static void main(String[] args) {
        String dataPath = "C:\\Users\\97252\\OneDrive\\Documents\\GitHub\\ADSS_Group_I\\Supermarket.db";
        MCVSystem my_sys = new MCVSystem(dataPath);
        my_sys.Activate();
    }
}
