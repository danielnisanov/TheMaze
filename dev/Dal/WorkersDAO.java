package Dal;

import Domain.Worker;

import java.sql.SQLException;

public class WorkersDAO implements IDAO<Worker> {


    @Override
    public void Insert(Worker obj) {
//        try{
//            obj.executeUpdate("INSERT INTO "+table+"(ID, Passengers, Destination, timeInAirfield) VALUES('"+ID+"', '"+ get_passengers+"', '"+ destination +"', '"+ time + "')");
//        }
//        catch(SQLException ex){
//            ex.printStackTrace();
//        }
    }

    @Override
    public void Delete() {

    }

    @Override
    public void Find() {

    }
}
