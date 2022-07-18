package com.project.training.assignment.employeedetails.service;

import com.project.training.assignment.employeedetails.model.employee.Employee;
import com.project.training.assignment.employeedetails.repository.employee.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void whenSaveEmployee() {
        Employee employee = new Employee();
        employee.setEmpName("Ram");

        when(employeeRepository.save(ArgumentMatchers.any(Employee.class))).thenReturn(employee);

        Employee created = employeeService.createEmployee(employee);

        assertThat(created.getEmpName()).isSameAs(employee.getEmpName());
        verify(employeeRepository).save(employee);
    }
    @Test
    public void shouldReturnAllEmployees() {
        List<Employee> employee = new ArrayList();
        employee.add(new Employee());

        when(employeeRepository.findAll()).thenReturn(employee);

        List<Employee> expected = employeeService.getAllEmployee();

        assertEquals(expected,employee);
        verify(employeeRepository).findAll();
    }
    @Test
    public void whenGivenId_shouldDeleteUser_ifFound(){
        Employee employee = new Employee();
        employee.setEmpName("Ram");
        employee.setEmpId(1);

        when(employeeRepository.findById(employee.getEmpId())).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(employee.getEmpId());
        verify(employeeRepository).deleteById(employee.getEmpId());
    }

    @Test
    public void should_throw_exception_when_Employee_doesnt_exist() {
        Employee employee = new Employee();
        employee.setEmpName("Ram");
        employee.setEmpId(1);

        when(employeeRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
        employeeService.deleteEmployee(employee.getEmpId());
    }
    @Test
    public void whenGivenId_shouldUpdateEmployee() {
        Employee employee = new Employee();
        employee.setEmpName("Ram");
        employee.setEmpId(1);

        Employee employee2 = new Employee();
        employee2.setEmpId(1);
        employee2.setEmpName("New Ram");
        employee2.setEmpLoc("ind");
        when(employeeRepository.save(any())).thenReturn(employee2);
        employeeService.updateEmployee(employee.getEmpId(), employee2);

        verify(employeeRepository).save(employee2);
    }
    @Test
    public void whenGivenId_shouldReturnEmployee() {
        Employee employee = new Employee();
        employee.setEmpId(1);


        when(employeeRepository.findById(employee.getEmpId())).thenReturn(Optional.of(employee));

        Employee expected = employeeService.getEmpById(employee.getEmpId());

        assertThat(expected).isSameAs(employee);
        verify(employeeRepository).findById(employee.getEmpId());
    }

}
