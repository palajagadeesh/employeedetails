package com.project.training.assignment.employeedetails.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.training.assignment.employeedetails.model.user.JwtRequest;
import com.project.training.assignment.employeedetails.model.user.User;
import com.project.training.assignment.employeedetails.model.user.UserDto;
import com.project.training.assignment.employeedetails.repository.user.UserRepository;
import com.project.training.assignment.employeedetails.service.JwtUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class JwtAuthenticationControllerTest {
    @InjectMocks
    private JwtAuthenticationController jwtAuthenticationController;

    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    private ObjectMapper mapper = new ObjectMapper();
    private UserDto user;
    private JwtRequest request;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();

        user = new UserDto();
        user.setUsername("Ram");
        user.setPassword("ped");
        user.setRole("admin");
    }
        @Test
        public void whenCreateEmployee() throws Exception {

            user = new UserDto();
            user.setUsername("Ram");
            user.setPassword("ped");
            user.setRole("admin");

            User user1=new User(1,"Ram","ped","admin");
            when(jwtUserDetailsService.save(any())).thenReturn(user1);
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/register")
                                    .content(mapper.writeValueAsString(user))
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk());
        }

}
