package Presentation.Presentationstock;

import DB.DataBase;
import Presentation.Presentationstock.Menu;
import java.sql.Connection;

public class Main_stock {

    public static void main(String[] args) throws Exception {
        try {
            Menu menu = new Menu();
            menu.start();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Connection identifier = DataBase.connect();
    }
}


