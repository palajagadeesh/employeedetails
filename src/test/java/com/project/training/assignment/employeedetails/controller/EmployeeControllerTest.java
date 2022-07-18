package com.project.training.assignment.employeedetails.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.training.assignment.employeedetails.model.employee.Employee;
import com.project.training.assignment.employeedetails.model.owner.Owner;
import com.project.training.assignment.employeedetails.repository.employee.EmployeeRepository;
import com.project.training.assignment.employeedetails.repository.owner.OwnerRepository;
import com.project.training.assignment.employeedetails.service.EmployeeServiceImpl;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.*;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {
    @InjectMocks
    private EmployeeController employeeController;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private EmployeeServiceImpl employeeService;
    @Rule
    public MockitoRule rule= MockitoJUnit.rule();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    private Employee employee;
    private Owner owner;

    //private ObjectMapper objectMapper;
    private ObjectMapper mapper = new ObjectMapper();
    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();

        employee=new Employee(1,"Ram","Ind");
         }
    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "admin")
    public void whenCreateEmployee() throws Exception {

        Employee employee1 = new Employee(1, "Ram", "Ind");

            when(employeeService.createEmployee(any())).thenReturn(employee1);

            mockMvc.perform(
                            MockMvcRequestBuilders.post("/addemployee")
                                    .content(mapper.writeValueAsString(employee1))
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk());
        }
    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "admin")
    public void whenCreateOwner() throws Exception {

        Owner owner=new Owner(1,"Juli","Axxx");

        when(ownerRepository.save(any())).thenReturn(owner);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/addowner")
                                .content(mapper.writeValueAsString(owner))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "client")
    public void whenGetEmployeeList() throws Exception {

        List<Employee> customerList = Arrays.asList(
                new Employee(1, "Ram", "Ind"),
                new Employee(2, "Ravan", "USA")
        );

        when(employeeService.getAllEmployee()).thenReturn(customerList);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/employeelist"))
                .andExpect(status().isOk());
                //.andExpect(content().json("[{}, {}]"));
    }
    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "client")
    public void whenGetOwnerList() throws Exception {

        List<Owner> customerList = Arrays.asList(
                new Owner(1, "Ram", "Axx"),
                new Owner(2, "Ravan", "Wxx")
        );

        when(ownerRepository.findAll()).thenReturn(customerList);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/ownerlist"))
                .andExpect(status().isOk());
        //.andExpect(content().json("[{}, {}]"));
    }
    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "admin")
    public void whenUpdateEmployee() throws Exception {

        Employee employee1 = new Employee(1, "Ram", "Ind");

        when(employeeRepository.save(any())).thenReturn(employee1);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/updateemployee/1")
                                .content(mapper.writeValueAsString(employee1))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "admin")
    public void whenUpdateOwner() throws Exception {

        Owner owner=new Owner(1,"Juli","Axxx");

        when(ownerRepository.save(any())).thenReturn(owner);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/updateowner/1")
                                .content(mapper.writeValueAsString(owner))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "client")
    public void whenGetEmployeeById_Not_Found() throws Exception {
        int empId=1;
        when(employeeService.getEmpById(empId)).thenThrow(new RuntimeException("No Data found"));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/getbyid/1")
                                //.content(mapper.writeValueAsString(employee1))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "admin")
    public void whenDeleteEmployee() throws Exception {

        Employee employee1 = new Employee(1, "Ram", "Ind");
        when(employeeRepository.findById(employee.getEmpId())).thenReturn(Optional.of(employee));
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/deleteemployee/1")
                               // .content(mapper.writeValueAsString(employee1))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(401));

        employeeController.deleteEmployee(employee.getEmpId());


    }
}






