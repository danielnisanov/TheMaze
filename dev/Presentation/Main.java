package Presentation;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Menu menu = new Menu();
            menu.start();
        }
        catch (Exception e){
            System.out.println( e.getMessage());

        }

    }
}

