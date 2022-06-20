package Project1.Repository;
import Project1.Entity.Employee;
import Project1.Entity.employeeRole;
import Project1.datasource.SQLConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.apache.log4j.Logger;

public class JdbcEmployeeRepository implements EmployeeRepository {

    private static final Logger LOG = Logger.getLogger("ERS"); //log4j singleton

    public Optional<Employee> findByEmployeeId(String employeeID) {
        LOG.info("loading employee: " + employeeID);
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
            employee.setEmployeePass(rs.getString("userpassword"));
            employee.setEmployeeRole(employeeRole.valueOf(rs.getString("userrole")));
            return Optional.of(employee);
        }


        } catch (SQLException e) {
            LOG.error("error loading employee: " + employeeID);
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void update(Employee employee) {
        LOG.info("updating employee: " + employee.getEmployeeID());
        try (Connection connection = SQLConnectionFactory.getConnection();) {

            String sql = "update EmployeeInfo set firstname=? where employeeID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, employee.getEmployeeFirstName());
            ps.setString(2, employee.getEmployeeID());
            int rows = ps.executeUpdate();

            sql = "update EmployeeInfo set lastname=? where employeeID=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, employee.getEmployeeLastName());
            ps.setString(2, employee.getEmployeeID());
            rows = ps.executeUpdate();



//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                Employee employee = new Employee();
//                employee.setEmployeeID(Integer.parseInt(rs.getString("employeeID")));
//                employee.setEmployeeFirstName(rs.getString("firstname"));
//                employee.setEmployeeLastName(rs.getString("lastname"));
//                employee.setEmployeeDob(rs.getString("dateofbirth"));
//                employee.setEmployeeAddress(rs.getString("address"));
//                employee.setEmployeePhoneNum(rs.getString("phonenumber"));
//                employee.setEmployeeEmail(rs.getString("email"));
//                employee.setEmployeePass(rs.getString("userpassword"));
//                employee.setEmployeeRole(rs.getString("userrole"));
//                return Optional.of(employee);
//            }


        } catch (SQLException e) {
            LOG.error("error loading employee: " + employee.getEmployeeID());
            e.printStackTrace();
        }
        //return Optional.empty();
    }
}
