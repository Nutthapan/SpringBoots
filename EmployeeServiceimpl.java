package com.example.Employee.service;

import com.example.Employee.entity.Employee;
import com.example.Employee.repository.EmployeeRepositoryimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceimpl implements EmployeeService {

    @Autowired
    private EmployeeRepositoryimpl employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAllEmployees();
    }

    @Override
    public Employee getEmployeeById(Long id) {

        return employeeRepository.findEmployeeById(id);
    }

    @Override
    public Employee createEmployee(Employee employee) {

        return employeeRepository.saveEmployee(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        // Implement update logic if needed
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {

        employeeRepository.deleteEmployee(id);
    }
}

