package com.project.training.assignment.employeedetails.service;

import com.project.training.assignment.employeedetails.exception.Error;
import com.project.training.assignment.employeedetails.model.employee.Employee;
import com.project.training.assignment.employeedetails.repository.employee.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    public EmployeeRepository employeeRepository;

    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public List<Employee> getAllEmployee() {
        logger.info("Employee details fetched");
        return employeeRepository.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if(employee.getEmpName().isEmpty()||employee.getEmpName().length()==0) {
        throw new Error("Input is empty",HttpStatus.BAD_REQUEST);
        }
        else {
            logger.info("Employee is created");
            return employeeRepository.save(employee);
        }
    }

    @Override
    public Employee getEmpById(int empId) {
            return employeeRepository.findById(empId).get();

    }

    @Override
    public Employee updateEmployee(int empId,Employee employee) {
        employee.setEmpId(empId);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(int empId) {
            employeeRepository.deleteById(empId);
            logger.info("empId {} is deleted successfully @ {}", empId);
        }


}
