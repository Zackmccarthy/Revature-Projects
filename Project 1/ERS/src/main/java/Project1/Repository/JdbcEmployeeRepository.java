package Project1.Repository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import Project1.Entity.Employee;
import Project1.Entity.employeeRole;
import Project1.Entity.reimbursementStatus;
import Project1.Entity.Reimbursement;
import Project1.datasource.SQLConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcEmployeeRepository implements EmployeeRepository {

    private static final Logger LOG = Logger.getLogger("ERS"); //log4j singleton

    public boolean authenticate(String username, String password) {
        return false;
    }

    public List<Employee> findAll() {
        LOG.info("Finding all employees: ");
        List<Employee> employeeList = new ArrayList<>();
        try (Connection connection = SQLConnectionFactory.getConnection();){

            String sql = "select * from EmployeeInfo where employeerole='Employee'";
            Statement ps = connection.createStatement();
            ResultSet rs= ps.executeQuery(sql);
            while(rs.next()){
                Employee emp = new Employee();
                emp.setEmployeeID(rs.getString("employeeID"));
                emp.setEmployeeFirstName(rs.getString("firstname"));
                emp.setEmployeeLastName(rs.getString("lastname"));
                emp.setEmployeeDob(rs.getString("dateofbirth"));
                emp.setEmployeeAddress(rs.getString("address"));
                emp.setEmployeePhoneNum(rs.getString("phonenumber"));
                emp.setEmployeeEmail(rs.getString("email"));
                emp.setEmployeePass(rs.getString("employeepassword"));
                emp.setEmployeeRole(employeeRole.valueOf(rs.getString("employeerole")));
                employeeList.add(emp);
            }

        } catch (SQLException e) {
            LOG.error("Error finding all expense reimbursement requests: ");
            e.printStackTrace();
        }
        return employeeList;
    }

    public Optional<Employee> findByEmployeeId(String employeeID) {
        LOG.info("Loading employee: " + employeeID);
        try (Connection connection = SQLConnectionFactory.getConnection();) {

        String sql = "select * from EmployeeInfo where employeeID=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, Integer.parseInt(employeeID));
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Employee employee = new Employee();
            employee.setEmployeeID(rs.getString("employeeID"));
            employee.setEmployeeFirstName(rs.getString("firstname"));
            employee.setEmployeeLastName(rs.getString("lastname"));
            employee.setEmployeeDob(rs.getString("dateofbirth"));
            employee.setEmployeeAddress(rs.getString("address"));
            employee.setEmployeePhoneNum(rs.getString("phonenumber"));
            employee.setEmployeeEmail(rs.getString("email"));
            employee.setEmployeePass(rs.getString("employeepassword"));
            employee.setEmployeeRole(employeeRole.valueOf(rs.getString("employeerole")));
            return Optional.of(employee);
        }

        } catch (SQLException e) {
            LOG.error("Error loading employee: " + employeeID);
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void save(Employee employee) {
        LOG.info("Saving new employee: ");
        try (Connection connection = SQLConnectionFactory.getConnection();){

            String sql = "insert into EmployeeInfo(firstname, lastname, email, userpassword, employeerole) values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, employee.getEmployeeFirstName());
            ps.setString(2, employee.getEmployeeLastName());
            ps.setString(3, employee.getEmployeeEmail());
            ps.setString(4, BCrypt.withDefaults().hashToString(12, employee.getEmployeePass().toCharArray()));
            ps.setString(5, employee.getEmployeeRole().toString());
            int rows = ps.executeUpdate();


        } catch (SQLException e) {
            LOG.error("error updating employee: " + employee.getEmployeeID());
            e.printStackTrace();
        }

    }


    public void update(Employee employee) {
        LOG.info("updating employee: " + employee.getEmployeeID());
        try (Connection connection = SQLConnectionFactory.getConnection();) {

            String sql = "update EmployeeInfo set firstname=?, lastname=?, dateofbirth=?, address=?, phonenumber=?, email=?, employeepassword=?, employeerole=? where employeeID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, employee.getEmployeeFirstName());
            ps.setString(2, employee.getEmployeeLastName());
            ps.setString(3, employee.getEmployeeDob());
            ps.setString(4, employee.getEmployeeAddress());
            ps.setString(5, employee.getEmployeePhoneNum());
            ps.setString(6, employee.getEmployeeEmail());
            ps.setString(7, employee.getEmployeePass());
            ps.setString(8, employee.getEmployeeRole().toString());
            ps.setString(9, Integer.parseInt(employee.getEmployeeID()));
            int rows = ps.executeUpdate();

        } catch (SQLException e) {
            LOG.error("error loading employee: " + employee.getEmployeeID());
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Employee> empAuthentication(String username, String password) {
        LOG.info("Loading employee : " + username + " to authenticate password.");
        try (Connection connection = SQLConnectionFactory.getConnection();){

            String sql = "select * from EmployeeInfo where email=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(rs.getString("employeeid"));
                employee.setEmployeeFirstName(rs.getString("firstname"));
                employee.setEmployeeLastName(rs.getString("lastname"));
                employee.setEmployeeEmail(rs.getString("email"));
                employee.setEmployeePhoneNum(rs.getString("phonenumber"));
                employee.setEmployeeAddress(rs.getString("address"));
                employee.setEmployeeDob(rs.getString("dateofbirth"));
                employee.setEmployeePass(rs.getString("userpassword"));
                employee.setEmployeeRole(employeeRole.valueOf(rs.getString("employeerole")));

                if(BCrypt.verifyer().verify(password.toCharArray(),employee.getEmployeePass()).verified)
                {
                    System.out.println("Authentication Successful");
                    return Optional.of(employee);
                }
                else
                {
                    System.out.println("Authentication unsuccessful.");
                }
            }

        } catch (SQLException e) {
            LOG.error("Error loading account: " + username + " for authentication.");
            e.printStackTrace();
        }
        System.out.println("Username not found.");
        return Optional.empty();
    }
}
