package Domain;

//import Dao.IDao;

import java.util.HashMap;
import java.util.Map;

public class ReportRepository implements IRepository<Report>{
    //private IDao<Report> reportDao;
    private Map<String, Report> reports = new HashMap<>();

    //TODO
    @Override
    public void add(Report entity) throws Exception {

    }

    @Override
    public void remove(String id) throws Exception {
        reportIsExist(id);
        reports.remove(id);
    }

    @Override
    public Report get(String id) throws Exception {
        reportIsExist(id);
        return reports.get(id);
    }

    public void reportIsExist (String name) throws Exception{
        if(!reports.containsKey(name)){
            throw new Exception("Report "+ name +" doesn't exist.");
        }
    }
}
