package com.project.training.assignment.employeedetails.service;

import com.project.training.assignment.employeedetails.model.employee.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface EmployeeService {
    List<Employee> getAllEmployee();
    Employee createEmployee(Employee employee);
    Employee getEmpById(int empId);
    Employee updateEmployee(int empId,Employee employee);
    void deleteEmployee(int empId);
}
