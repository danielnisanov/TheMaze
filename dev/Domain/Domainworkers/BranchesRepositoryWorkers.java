package Domain.Domainworkers;

import DAL.DALworkers.BranchesDAO;
import DAL.DALworkers.DatabaseConnection;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BranchesRepositoryWorkers implements IRepositoryWorkers<Branch> {
    private final Map<Integer, Branch> branches;
    private final BranchesDAO branchesDAO;
    private final WorkArrangementRepositoryWorkers war;



    public BranchesRepositoryWorkers(DatabaseConnection dbConnection, WorkArrangementRepositoryWorkers war) {
        this.war = new WorkArrangementRepositoryWorkers(dbConnection);
        this.branches = new HashMap<>();
        this.branchesDAO = new BranchesDAO(dbConnection,war); // Initialize branchesDAO with dbConnection
    }
    @Override
    public boolean Insert(Branch branch) {
        if (branches.containsKey(branch.getBranchNum())) {
            return false; // branch already exists
        } else {
            branchesDAO.Insert(branch); // Add branch to the database
            branches.put(branch.getBranchNum(), branch); // Add branch to the map
            return true;
        }
    }

    @Override
    public boolean Update(int num, String field, String change) throws SQLException {
        return false;
    }

    @Override
    public boolean Delete() {
        return false;
    }


    @Override
    public Branch Find(int id) {
        Branch branch = branches.get(id);

        if (branch == null) { // I did not find the worker in workers
            try {
                branch = branchesDAO.Find(id); // look for him in DAO
                if (branch != null) { // found him in workers table
                    branches.put(id, branch); // add him to workers
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return branch;

    }

}