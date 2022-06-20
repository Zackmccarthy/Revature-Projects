package Project1.Repository;

import Project1.Entity.Employee;

import java.util.Optional;

public interface EmployeeRepository {

    Optional<Employee> findByEmployeeId(String employeeID);
    void update(Employee employee);


}
