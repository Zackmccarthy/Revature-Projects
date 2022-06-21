package com.example.repository;

import com.example.entity.Employee;

import java.util.Optional;
import java.util.List;

public interface EmployeeRepository {

    boolean authenticate (String username, String password);
    List<Employee> findAll();
    Optional<Employee> findByEmpID(String number);

    public Optional<Employee> findByEmpEmail(String username);

    void save(Employee employee);
    void update(Employee employee);

    Optional<Employee> empAuthentication(String username, String password);

}
