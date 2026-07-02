package com.fpt.employees.service;

import com.fpt.employees.model.Employee;
import com.fpt.employees.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional
    public Employee addEmployees(Employee employee) {
        employee.setId(null);
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(Employee employee) {
        if (employee.getId() == null || !employeeRepository.existsById(employee.getId())) {
            throw new EmployeeNotFoundException("Employee not found with id: " + employee.getId());
        }
        return employeeRepository.save(employee);
    }
}
