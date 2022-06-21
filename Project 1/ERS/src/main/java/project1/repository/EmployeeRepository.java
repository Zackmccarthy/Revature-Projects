package project1.repository;

import project1.entity.Employee;
import project1.entity.ExpReimbursementReq;

import java.util.Optional;
import java.util.List;

public interface EmployeeRepository {

    boolean authenticate (String username, String password);
    List<Employee> findAll();
    Optional<Employee> findByEmpID(String number);

    void save(Employee employee);
    void update(Employee employee);

    Optional<Employee> empAuthentication(String username, String password);

}
