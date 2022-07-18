package com.project.training.assignment.employeedetails.controller;
import com.project.training.assignment.employeedetails.model.employee.Employee;
import com.project.training.assignment.employeedetails.model.owner.Owner;
import com.project.training.assignment.employeedetails.repository.employee.EmployeeRepository;
import com.project.training.assignment.employeedetails.repository.owner.OwnerRepository;
import com.project.training.assignment.employeedetails.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
public class EmployeeController {
    @Autowired
    public EmployeeService employeeService;
    @Autowired
    public EmployeeRepository employeeRepository;
    @Autowired
    public OwnerRepository ownerRepository;
    @GetMapping("/employeelist")
    public List<Employee> getAllEmployee(){

        return employeeService.getAllEmployee();
    }
    @PostMapping("/addemployee")
    public Employee createEmployee(@RequestBody Employee employee){
        return  employeeService.createEmployee(employee);
    }

    @GetMapping("/ownerlist")
    public List<Owner> getAllOwners() {

        return ownerRepository.findAll();
    }

    @PostMapping("/addowner")
    public Owner createOwner(@RequestBody Owner owner){
        return  ownerRepository.save(owner);
    }

    @PutMapping("/updateemployee/{empId}")
    public Employee updateEmployee(@PathVariable int empId,@RequestBody Employee employee){
        return  employeeService.updateEmployee(empId,employee);

    }
    @PutMapping("/updateowner/{ownId}")
    public Owner updateOwner(@PathVariable int ownId, @RequestBody Owner owner){
        return  ownerRepository.save(owner);

    }
    @DeleteMapping("/deleteemployee/{empId}")
    public void deleteEmployee(@PathVariable int empId){
            employeeService.deleteEmployee(empId);

    }

    @DeleteMapping("/deleteowner/{ownId}")
    public void deleteOwner(@PathVariable int ownId)  {

        ownerRepository.deleteById(ownId);
    }

    @GetMapping("/getbyid/{empId}")
    public Employee empById(@PathVariable int empId){
        return employeeService.getEmpById(empId);
    }

}