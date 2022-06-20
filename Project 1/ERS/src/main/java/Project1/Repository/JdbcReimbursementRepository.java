package Project1.Repository;

import Project1.Entity.Reimbursement;
import java.util.List;
import Project1.datasource.SQLConnectionFactory;
import Project1.Entity.reimbursementStatus;
import Project1.Entity.Employee;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;

public class JdbcReimbursementRepository implements ReimbursementRepository {
    private static final Logger LOG= Logger.getLogger("ERS");

    public void save(Reimbursement reimbursement) {
        LOG.info("Saving new expense reimbursement request: ");
        try (Connection connection= SQLConnectionFactory.getConnection();){

            String sql = "insert into reimbursementreq(reimbursementamount, reimbursementreason, reimbursementstatus, employeeid) values (?,?,'PENDING',?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, Reimbursement.getReimbursementAmount());
            ps.setString(2, Reimbursement.getReimbursementReason());
            ps.setInt(3, Integer.parseInt(Reimbursement.getEmployee().getEmployeeID()));
            int rows = ps.executeUpdate();

        } catch (SQLException e) {
            LOG.error("Error saving expense reimbursement request: ");
            e.printStackTrace();
        }
    }

    public List<Reimbursement> findAll() {
        LOG.info("Finding all expense reimbursement requests: ");
        List<Reimbursement> ReimbursementList = new ArrayList<>();
        try (Connection connection= SQLConnectionFactory.getConnection();){

            String sql = "select * from reimbursementreq";
            Statement ps = connection.createStatement();
            ResultSet rs= ps.executeQuery(sql);

            ReimbursementList = rsMagic(rs);

        } catch (SQLException e) {
            LOG.error("error finding all expense reimbursement requests: ");
            e.printStackTrace();
        }

        return ReimbursementList;
    }

    public List<Reimbursement> findAllByStatus(String status) {
        return findAllByStatusForEmployee(status, -1);
    }

    public List<Reimbursement> findAllByStatusForEmployee(String status, int employeeID) {
        if(employeeID != -1) {
            LOG.info("Finding all expense reimbursement requests by status for particular employee ID: " + employeeID);
        } else {
            LOG.info("Finding all expense reimbursement requests foll all employees: ");
        }

        List<Reimbursement> ReimbursementList = new ArrayList<>();
        try (Connection connection= SQLConnectionFactory.getConnection();){
            ResultSet rs;
            if (employeeID == -1) {
                String sql="select * from reimbursementreq where status=?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, status);
                rs= ps.executeQuery();
            } else {
                String sql="select * from reimbursement where status=? and employeeid=?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, status);
                ps.setInt(2, employeeID);
                rs= ps.executeQuery();
            }

            ReimbursementList= rsMagic(rs);
        } catch (SQLException e) {
            if(employeeID == -1){
                LOG.error("Error saving all expense reimbursement requests by status: ");
            }else {
                LOG.error("Error saving expense reimbursement requests by status for employeeID: " + employeeID);
            }
            e.printStackTrace();
        }

        return ReimbursementList;
    }

    public List<Reimbursement> rsMagic(ResultSet rs) throws SQLException {
        List<Reimbursement> ReimbursementList = new ArrayList<>();
        while (rs.next()) {
            Reimbursement reimbursement = new Reimbursement();
            Reimbursement.setId(rs.getInt("request_id"));
            Reimbursement.setAmount(rs.getDouble("amount"));
            Reimbursement.setReimReason(rs.getString("reimreason"));
            Reimbursement.setStatus(ERStatus.valueOf(rs.getString("status")));
            Reimbursement.setEmployee((new JdbcEmployeeRepository()).findByEmpID(rs.getString("emp_id")).get());
            ReimbursementList.add(reimbursement);
        }
        return ReimbursementList;
    }

    public List<Reimbursement> findAllResolved() {
        return findAllResolvedForEmployee(-1);
    }

    public List<Reimbursement> findAllResolvedForEmployee(int employeeID) {

        ResultSet rs;
        LOG.info("Finding all expense reimbursement requests by status for particular employee: ");
        List<Reimbursement> ReimbursementList = new ArrayList<>();
        try (Connection connection= SQLConnectionFactory.getConnection();){
            String sql;
            if(employeeID == -1) {
                sql = "select * from reimbursementreq where status!='PENDING'";
                Statement ps = connection.createStatement();
                rs = ps.executeQuery(sql);
            }else {
                sql = "select * from reimbursementreq where status!='PENDING' and employeeid=?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, employeeID);
                rs= ps.executeQuery();
            }

            ReimbursementList = rsMagic(rs);

        } catch (SQLException e) {
            LOG.error("Error saving expense reimbursement requests by status: ");
            e.printStackTrace();
        }

        return ReimbursementList;
    }



}
