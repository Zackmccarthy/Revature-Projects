package Project1.Repository;

import Project1.Entity.Employee;
import Project1.Entity.Reimbursement;

import java.util.Optional;
import java.util.List;

public interface EmployeeRepository {

    boolean authenticate (String username, String password);
    List<Employee> findAll();
    Optional<Employee> findByEmployeeId(String employeeID);
    void save(Employee employee);
    void update(Employee employee);

    Optional<Employee> empAuthentication(String username, String password);


}
