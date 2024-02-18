package com.example.Employee.controller;

import com.example.Employee.entity.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public interface EmployeeController {

    ResponseEntity<List<Employee>> getAllEmployees();

    ResponseEntity<Employee> getEmployeeById(Long id);

    ResponseEntity<Employee> createEmployee(Employee employee);

    ResponseEntity<Employee> updateEmployee(Long id, Employee employee);

    ResponseEntity<Void> deleteEmployee(Long id);
}

