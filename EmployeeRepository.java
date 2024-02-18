package com.example.Employee.repository;

import com.example.Employee.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepository {

    List<Employee> findAllEmployees();

    Employee findEmployeeById(Long id);

    Employee saveEmployee(Employee employee);

    void deleteEmployee(Long id);
}
