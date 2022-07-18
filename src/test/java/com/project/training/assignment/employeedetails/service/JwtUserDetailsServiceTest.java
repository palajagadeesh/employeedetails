package com.project.training.assignment.employeedetails.service;

import com.project.training.assignment.employeedetails.model.user.User;
import com.project.training.assignment.employeedetails.model.user.UserDto;
import com.project.training.assignment.employeedetails.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest

public class JwtUserDetailsServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder bcryptEncoder;

    @InjectMocks
    private JwtUserDetailsService jwtUserDetailsService;

    private UserDetails userDetails;

    @Test
    public void whenSaveUser() {
        User user=new User();
        user.setUsername("CAT");
        user.setPassword(bcryptEncoder.encode("RAT"));
        user.setRole("admin");

        UserDto user1=new UserDto();
        user1.setUsername("CAT");
        user1.setPassword("RAT");
        user1.setRole("admin");

        when(userRepository.save(any())).thenReturn(user);

        User created = jwtUserDetailsService.save(user1);

        assertThat(created.getUsername()).isSameAs(user.getUsername());
        verify(userRepository).save(user);
    }
    @Test
    public void whenLoadUserByUserName(){
        User user=new User();
        user.setUsername("CAT");
        user.setPassword("RAT");
        user.setRole("admin");

        when(userRepository.findByUsername(any())).thenReturn(user);
        UserDetails created=jwtUserDetailsService.loadUserByUsername(user.getUsername());
        verify(userRepository).findByUsername(user.getUsername());

    }

}
